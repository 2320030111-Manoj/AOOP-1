package Five;

//Employee class adhering to SRP (Single Responsibility Principle)
//Manages employee details.
class Employee {
 private String employeeId;
 private String name;
 private String position;

 public Employee(String employeeId, String name, String position) {
     this.employeeId = employeeId;
     this.name = name;
     this.position = position;
 }

 public String getEmployeeId() {
     return employeeId;
 }

 public String getName() {
     return name;
 }

 public String getPosition() {
     return position;
 }

 // Print employee details
 public void printDetails() {
     System.out.println("Employee ID: " + employeeId);
     System.out.println("Name: " + name);
     System.out.println("Position: " + position);
 }
}

//Interface for calculating salaries adhering to SRP and OCP (Open/Closed Principle)
//Allows different salary calculation implementations.
interface SalaryCalculator {
 double calculateSalary(double baseSalary);
}

//FullTimeSalaryCalculator class for calculating full-time employee salaries
//Demonstrates SRP and follows OCP by implementing SalaryCalculator.
class FullTimeSalaryCalculator implements SalaryCalculator {
 @Override
 public double calculateSalary(double baseSalary) {
     // Full-time employees receive a base salary with bonuses
     double bonus = 0.20 * baseSalary;
     return baseSalary + bonus;
 }
}

//PartTimeSalaryCalculator class for calculating part-time employee salaries
//Demonstrates SRP and follows OCP by implementing SalaryCalculator.
class PartTimeSalaryCalculator implements SalaryCalculator {
 @Override
 public double calculateSalary(double baseSalary) {
     // Part-time employees receive only the base salary without bonuses
     return baseSalary;
 }
}

//ContractSalaryCalculator class for calculating contract employee salaries
//Demonstrates SRP and follows OCP by implementing SalaryCalculator.
class ContractSalaryCalculator implements SalaryCalculator {
 @Override
 public double calculateSalary(double baseSalary) {
     // Contract employees receive a base salary with additional contract fees
     double contractFee = 0.10 * baseSalary;
     return baseSalary + contractFee;
 }
}

//SalaryManager class adhering to SRP and DIP (Dependency Inversion Principle)
//Manages salary calculation logic by depending on abstractions (interfaces).
class SalaryManager {
 private SalaryCalculator salaryCalculator;

 // Constructor that injects the salary calculation strategy (Dependency Injection)
 public SalaryManager(SalaryCalculator salaryCalculator) {
     this.salaryCalculator = salaryCalculator;
 }

 public double calculateEmployeeSalary(Employee employee, double baseSalary) {
     System.out.println("Calculating salary for employee: " + employee.getName());
     return salaryCalculator.calculateSalary(baseSalary);
 }
}

//Main class to test the employee and salary system
public class EmployeeD {
 public static void main(String[] args) {
     // Create employees
     Employee fullTimeEmployee = new Employee("E1001", "John Doe", "Software Engineer");
     Employee partTimeEmployee = new Employee("E1002", "Jane Smith", "Data Analyst");
     Employee contractEmployee = new Employee("E1003", "Mike Johnson", "Consultant");

     // Define base salaries
     double baseSalaryFullTime = 5000;
     double baseSalaryPartTime = 3000;
     double baseSalaryContract = 7000;

     // Create SalaryCalculator instances
     SalaryCalculator fullTimeCalculator = new FullTimeSalaryCalculator();
     SalaryCalculator partTimeCalculator = new PartTimeSalaryCalculator();
     SalaryCalculator contractCalculator = new ContractSalaryCalculator();

     // SalaryManager for full-time employees
     SalaryManager fullTimeManager = new SalaryManager(fullTimeCalculator);
     System.out.println("Full-time Employee Salary: $" + fullTimeManager.calculateEmployeeSalary(fullTimeEmployee, baseSalaryFullTime));

     // SalaryManager for part-time employees
     SalaryManager partTimeManager = new SalaryManager(partTimeCalculator);
     System.out.println("Part-time Employee Salary: $" + partTimeManager.calculateEmployeeSalary(partTimeEmployee, baseSalaryPartTime));

     // SalaryManager for contract employees
     SalaryManager contractManager = new SalaryManager(contractCalculator);
     System.out.println("Contract Employee Salary: $" + contractManager.calculateEmployeeSalary(contractEmployee, baseSalaryContract));
 }
}

