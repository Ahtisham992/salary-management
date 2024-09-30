package application.mainclasses;





public class payrate  {
    private String employeeId;
    private String employeeName;
    
    private String role;
    private String salary;
    private String bonus;

    public payrate () {
        this.employeeId = "";
        this.employeeName = "";
        this.bonus = " ";
        this.salary = " ";
        this.role = " ";
    }

    public payrate (String employeeId, String employeeName, String bonus, String salary, String role) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.bonus = bonus;
        this.salary = salary;
        this.role = role;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}







