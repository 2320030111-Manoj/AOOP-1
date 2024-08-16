package rough;
import java.util.*;
public class Ride {
	public static int code = 1972;
	private static Ride instance;
	
	private Ride() {};
	
	public static Ride Instance()
	{
		if(instance==null)
		{
			instance = new Ride();
		}
		return instance;
	}
	
	public void authentication()
	{
		System.out.println("pls enter the verification code :");
		Scanner input = new Scanner(System.in);
		int u = input.nextInt();
		if(u==code)
		{
			System.out.println("verification Successful");
			
		}
		else
		{
			System.out.println("unsuccessful");
			System.exit(1);
		}
	}
	
}
class Selection{
	public static String ask()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("pls enter the vehicle of your choice");
		String a = input.nextLine();
		return a;
	}
}

class VehicleFactory {
	
	public static void vehicletype() {}
	public static void amount() {}
}

class car extends VehicleFactory
{
	public static void vehicletype()
	{
		System.out.println("user selected car");
	}
	public static void amount()
	{
		System.out.println("pls pay 10$");
		
	}
}
class bike extends VehicleFactory
{
	public static void vehicletype()
	{
		System.out.println("user selected bike");
	}
	public static void amount()
	{
		System.out.println("pls pay 5$");
		
	}
}

class PaymentSelection {
	public static String selection()
	{
		System.out.println("pls enter the payment mode");
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		return s;
	}
	
}

abstract class paymentMethod {
	abstract void cash();
	abstract void online();
}

class car1 extends paymentMethod {
	public void cash()
	{
		System.out.println("amount paid via cash");
	}
	public void online()
	{
		System.out.println("amount paid via online");
	}
}

class bike1 extends paymentMethod {
	public void cash()
	{
		System.out.println("amount paid via cash");
	}
	public void online()
	{
		System.out.println("amount paid via online");
	}
}

class main2 {
	
	public static void main(String[] args)
	{
		Ride obj = Ride.Instance();
		//System.out.println("pls enter the verification code");
		obj.authentication();
		String w = Selection.ask();
		if(w.equals("car"))
		{
			car.vehicletype();
			car.amount();
		}
		else
		{
			bike.vehicletype();
			bike.amount();
		}
		String s1 = PaymentSelection.selection();
		if(s1.equals("cash") && w.equals("car"))
		{
			car1 a = new car1();
			a.cash();
		}
		else if(s1.equals("online") && w.equals("car"))
		{
			car1 a1 = new car1();
			a1.online();
		}
		else if(s1.equals("cash") && w.equals("bike"))
		{
			bike1 b = new bike1();
			b.online();
		}
		else if(s1.equals("online") && w.equals("bike"))
		{
			bike1 b1 = new bike1();
			b1.online();
		}
		
		
	}
}




