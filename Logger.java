package rough;

public class Logger {

	private static Logger instance;
	
	private Logger(){ }
	
	public static Logger Instance()
	{
		if(instance==null)
		{
			instance = new Logger();
		}
		return instance;
	}
	
	public void log(String msg)
	{
		System.out.println("Log message: "+ msg);
	}

}

class main {
	public static void main(String[] args)
	{
		Logger logger = Logger.Instance();
	
		logger.log("This is the first log");
		logger.log("This is the second log");
	}
	
}