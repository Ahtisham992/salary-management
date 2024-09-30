package application.mainclasses;

public class DataSingleton {
	
	private static final DataSingleton instance= new DataSingleton();
	
	private String username;
	private boolean istransfered=false;
	private String status="";
	private String id="";
	private DataSingleton() {}
	
	public static DataSingleton getInstance()
	{
		return instance;
	}
	
	public String getusername()
	{
		return username;
	}
	
	
	public void setUsername(String Name)
	{
		this.username=Name;
	}
	
	public void setistransferedcheck(boolean check)
	{
		this.istransfered=check;
	}
	public boolean getistransferedcheck()
	{
		return this.istransfered;
	}
	
	public void setstatus(String status)
	{
		this.status=status;
	}
	public String getstatus()
	{
		return this.status;
	}
	
	public void setid(String id)
	{
		this.id=id;
	}
	public String getid()
	{
		return this.id;
	}
	

}
