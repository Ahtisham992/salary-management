package application.DBservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Buttons;
import application.DatabaseConnection;
import application.mainclasses.DataSingleton;
import application.mainclasses.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TotalLeaveDatabase {
	
	private TableView<Employee> tableView;

	
	private TableColumn<Employee, String> col_employee_id;

	
	private TableColumn<Employee, String> col_employeename_id;

	
	private TableColumn<Employee, String> col_employee_subject_id;

	
	private TableColumn<Employee, String> col_description_id;


	private TableColumn<Employee, String> col_status_id;


	private Button select_leave;
	
	
	DataSingleton data= DataSingleton.getInstance();

	
	public Button getSelect_leave() {
		return select_leave;
	}

	public void setSelect_leave(Button select_leave) {
		this.select_leave = select_leave;
	}
	
	public TableView<Employee> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Employee> tableView) {
		this.tableView = tableView;
	}

	public TableColumn<Employee, String> getCol_employee_id() {
		return col_employee_id;
	}

	public void setCol_employee_id(TableColumn<Employee, String> col_employee_id) {
		this.col_employee_id = col_employee_id;
	}

	public TableColumn<Employee, String> getCol_employeename_id() {
		return col_employeename_id;
	}

	public void setCol_employeename_id(TableColumn<Employee, String> col_employeename_id) {
		this.col_employeename_id = col_employeename_id;
	}

	public TableColumn<Employee, String> getCol_employee_subject_id() {
		return col_employee_subject_id;
	}

	public void setCol_employee_subject_id(TableColumn<Employee, String> col_employee_subject_id) {
		this.col_employee_subject_id = col_employee_subject_id;
	}

	public TableColumn<Employee, String> getCol_description_id() {
		return col_description_id;
	}

	public void setCol_description_id(TableColumn<Employee, String> col_description_id) {
		this.col_description_id = col_description_id;
	}

	public TableColumn<Employee, String> getCol_status_id() {
		return col_status_id;
	}

	public void setCol_status_id(TableColumn<Employee, String> col_status_id) {
		this.col_status_id = col_status_id;
	}
	
	
	public ObservableList<Employee> getemployeelist() throws SQLException
    {
    	ObservableList<Employee> employeeList= FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        // Create a statement to execute SQL queries
        Statement statement = connection.createStatement();

        // Execute the query to fetch employee data
        ResultSet resultSet = statement.executeQuery("SELECT username, leave_subject, leave_description, status_leave FROM db.leavedescription");
    try 
    {
    	tableView.getItems().clear();
         // Iterate over the result set and create Employee objects
    	 Employee employee;
    	 while (resultSet.next()) 
         {
             
             String employeeId = "";
             String employeeName = resultSet.getString("username");
             String subject = resultSet.getString("leave_subject");
             String description = resultSet.getString("leave_description");
             String status = resultSet.getString("status_leave");
             
             employeeId = retrieveEmployeeName(employeeName);

             if (employeeId != null)
             {
             // Create an Employee object with the fetched data
             employee = new Employee();
             employee.setEmployeeId(employeeId);
             employee.setEmployeeName(employeeName);
             employee.setSubject(subject);
             employee.setDescription(description);
             employee.setStatus(status);
             employeeList.add(employee);
             }
         }
    }catch(Exception ex)
    {
    	ex.printStackTrace();
    }
	return employeeList;
        
    }
	
	private String retrieveEmployeeName(String employeeName) {
	    String employeeId = null;

	    DatabaseConnection connectNow = new DatabaseConnection();
        
	    
	    try (
	    		Connection connection = connectNow.getConnection()) 
	    {
	        // Prepare the SQL statement
	        String sql = "SELECT user_id FROM db.employee_table2 WHERE user_name = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, employeeName);

	        // Execute the query
	        ResultSet resultSet = statement.executeQuery();

	        // Retrieve the employee ID from the result set
	        if (resultSet.next()) {
	            employeeId = resultSet.getString("user_id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return employeeId;
	}
	
	
	public void showdetails() throws SQLException
	{
		ObservableList<Employee>List =getemployeelist();
		col_employee_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeId"));
        col_employeename_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        col_employee_subject_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("subject"));
        col_description_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("description"));
        col_status_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("status"));
        tableView.setItems(List);
	}
	
	public void select() {
		System.out.println("Hello");

		Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
		if (selectedEmployee != null) {
			String id = selectedEmployee.getEmployeeId();
			String name = selectedEmployee.getEmployeeName();

			// Check if employeeId and name are not null before proceeding
			if (id != null && name != null) {
				data.setid(id);
				data.setUsername(name);

				Buttons b = new Buttons("leavedecision.fxml", select_leave);
				b.location();
			}
		}
	}
	
	
	
	
}
