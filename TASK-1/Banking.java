package rough;

public class Banking {
	
	private static Banking instance;
	
	private Banking() {};
	
	public static Banking Instance()
	{
		if(instance==null)
		{
			instance = new Banking();
		}
		return instance;
	}
	
	public void Balance(int check)
	{
		System.out.println("Checking Balance_user: "+ check);
	}
	public void Deposit(int check)
	{
		System.out.println("Deposited _user: "+ check);
	}
}

class main1 {
	
	public static void main(String[] args)
	{
		Banking bank = Banking.Instance();
		
		bank.Balance(1000);
		bank.Deposit(0);
	}
}
