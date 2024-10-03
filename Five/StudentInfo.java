package Five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Course class adhering to SRP (Single Responsibility Principle)
// It manages only the course information.
class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}

// Base Student class adhering to SRP
// Handles student information and course enrollment functionality.
class Student {
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public void printInfo() {
        System.out.println("Student ID: " + studentId + ", Name: " + name);
        System.out.println("Enrolled courses: ");
        for (Course course : enrolledCourses) {
            System.out.println(course.getCourseName());
        }
    }
}

// UndergraduateStudent class that inherits from Student
// Demonstrates LSP (Liskov Substitution Principle) and OCP (Open/Closed Principle)
class UndergraduateStudent extends Student {
    public UndergraduateStudent(String studentId, String name) {
        super(studentId, name);
    }

    @Override
    public void printInfo() {
        System.out.println("Undergraduate Student:");
        super.printInfo();
    }
}

// GraduateStudent class that inherits from Student
// Demonstrates LSP and OCP
class GraduateStudent extends Student {
    public GraduateStudent(String studentId, String name) {
        super(studentId, name);
    }

    @Override
    public void printInfo() {
        System.out.println("Graduate Student:");
        super.printInfo();
    }
}

// Interface for student management adhering to ISP (Interface Segregation Principle)
interface StudentManagement {
    void registerStudent(Student student);
    void removeStudent(String studentId);
    Student getStudentById(String studentId);
}

// Interface for course registration adhering to ISP
interface CourseRegistration {
    void enrollStudentInCourse(String studentId, Course course);
    void dropStudentFromCourse(String studentId, Course course);
}

// SISManager class that implements both StudentManagement and CourseRegistration interfaces
// Adheres to DIP (Dependency Inversion Principle) by depending on interfaces.
class SISManager implements StudentManagement, CourseRegistration {
    private Map<String, Student> students;

    public SISManager() {
        students = new HashMap<>();
    }

    @Override
    public void registerStudent(Student student) {
        students.put(student.getStudentId(), student);
        System.out.println("Student registered: " + student.getName());
    }

    @Override
    public void removeStudent(String studentId) {
        students.remove(studentId);
        System.out.println("Student with ID " + studentId + " removed.");
    }

    @Override
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }

    @Override
    public void enrollStudentInCourse(String studentId, Course course) {
        Student student = students.get(studentId);
        if (student != null) {
            student.enrollCourse(course);
            System.out.println("Student " + student.getName() + " enrolled in course " + course.getCourseName());
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public void dropStudentFromCourse(String studentId, Course course) {
        Student student = students.get(studentId);
        if (student != null) {
            student.dropCourse(course);
            System.out.println("Student " + student.getName() + " dropped course " + course.getCourseName());
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }
}

// Main class to test the Student Information System
public class StudentInfo {
    public static void main(String[] args) {
        SISManager sisManager = new SISManager();

        // Create courses
        Course course1 = new Course("CS101", "Introduction to Computer Science");
        Course course2 = new Course("CS102", "Data Structures");

        // Create students
        Student undergrad = new UndergraduateStudent("U1001", "Alice");
        Student grad = new GraduateStudent("G2001", "Bob");

        // Register students
        sisManager.registerStudent(undergrad);
        sisManager.registerStudent(grad);

        // Enroll students in courses
        sisManager.enrollStudentInCourse("U1001", course1);
        sisManager.enrollStudentInCourse("G2001", course2);

        // Print student information
        undergrad.printInfo();
        grad.printInfo();

        // Drop a course for a student
        sisManager.dropStudentFromCourse("U1001", course1);

        // Remove a student from the system
        sisManager.removeStudent("G2001");
    }
}

