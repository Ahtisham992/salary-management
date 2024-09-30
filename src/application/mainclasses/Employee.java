package application.mainclasses;

import java.time.LocalDate;

public class Employee {

	private String employeeId;
	private String employeeName;
	private String employeeRole;
	private String userPhoneNumber;
	private String userPassword;
	private String paidLeaves;
	private String sickLeaves;
	private String casualLeaves;
	private String subject;
    private String description;
    private String status;
    private String leavestatus;
    private LocalDate date;
    
    
	public Employee(String id, String name, String role, String phone, String pass, String paid, String casual,
			String sick) {
		this.employeeId = id;
		this.employeeName = name;
		this.employeeRole = role;
		this.userPhoneNumber = phone;
		this.userPassword = pass;
		this.paidLeaves = paid;
		this.sickLeaves = sick;
		this.casualLeaves = casual;
		
	}
	
	

	public Employee(String id, String name, String role, String phone, String pass) {
		this.employeeId = id;
		this.employeeName = name;
		this.employeeRole = role;
		this.userPhoneNumber = phone;
		this.userPassword = pass;
		
		
	}
	
	
	
	public Employee() {
		this.employeeId = "";
		this.employeeName = "";
		this.employeeRole = "";
		this.userPhoneNumber = "";
		this.userPassword = "";
		this.subject="";
		this.description="";
		this.status="";
		this.leavestatus="";
		 
	}
	
	public void setLeavestatus(String s)
	{
		this.leavestatus=s;
	}
	public String  getLeavestatus()
	{
		return this.leavestatus;
	}
	
	public void setdate(LocalDate s)
	{
		this.date=s;
	}
	public LocalDate  getDate()
	{
		return this.date;
	}
	
	
	public void setEmployeeId(String id) {
		this.employeeId = id;
	}

	public void setEmployeeName(String name) {
		this.employeeName = name;
	}

	public void setEmployeeRole(String role) {
		this.employeeRole = role;
	}

	public void setUserPhoneNumber(String phone) {
		this.userPhoneNumber = phone;
	}

	public void setUserPassword(String pass) {
		this.userPassword = pass;
	}

	public void setPaidLeaves(String paid) {
		this.paidLeaves = paid;
	}

	public void setCasualLeaves(String casual) {
		this.casualLeaves = casual;
	}

	public void setSickLeaves(String sick) {
		this.sickLeaves = sick;
	}

	public String getPaidLeaves() {
		return this.paidLeaves;
	}

	public String getCasualLeaves() {
		return this.casualLeaves;
	}

	public String getSickLeaves() {
		return this.sickLeaves;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}
	
	
	
	
	

	public String getEmployeeName() {
		return this.employeeName;
	}

	public String getEmployeeRole() {
		return this.employeeRole;
	}

	public String getUserPhoneNumber() {
		return this.userPhoneNumber;
	}

	public String getUserPassword() {
		return this.userPassword;
	}
	
	
	 
	    public String getSubject() {
	        return subject;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getStatus() {
	        return status;
	    }
	    
	    
	 

	    public void setSubject(String subject) {
	        this.subject = subject;
	    }

	   
	    public void setDescription(String description) {
	        this.description = description;
	    }

	    

	    public void setStatus(String status) {
	        this.status = status;
	    }

}
