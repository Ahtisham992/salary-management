package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import application.mainclasses.DataSingleton;
import application.mainclasses.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class AttendenceShowController 
{
	
	 

	    @FXML
	    private TableView<Employee> tableView;

	    @FXML
		private TableColumn<Employee, String> col_employee_id;

	    @FXML
		private TableColumn<Employee, String> col_employeename_id;

	    @FXML
		private TableColumn<Employee, LocalDate> Datacol;

	    @FXML
		private TableColumn<Employee, String> col_status_id;


	    
	    

	    @FXML
	    private Button backbutton;
	    
	    private DataSingleton data = DataSingleton.getInstance();

	    public void initialize() throws SQLException 
	    {
	    
	    	showDetails();
	    	
	        // Initialization logic, if needed
	    }
	    
	    
	    private ObservableList<Employee> getEmployeeList() throws SQLException {
	        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

	        String username = data.getusername(); // Replace with actual username

	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();

	        // Create a statement to execute SQL queries
	        Statement statement = connection.createStatement();

	        // Execute the query to fetch employee data
	        String query = "SELECT username, date, status FROM db.attendance WHERE username = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, username);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        try {
	            tableView.getItems().clear();
	            // Iterate over the result set and create Employee objects
	            Employee employee;
	            while (resultSet.next()) {
	                String employeeId = "";
	                String employeeName = resultSet.getString("username");
	                LocalDate date = resultSet.getDate("date").toLocalDate();
	                String status = resultSet.getString("status");

	                employeeId = retrieveEmployeeName(username);

	                if (employeeId != null) {
	                    // Create an Employee object with the fetched data
	                    employee = new Employee();
	                    employee.setEmployeeId(employeeId);
	                    employee.setEmployeeName(employeeName);
	                    employee.setdate(date);
	                    employee.setLeavestatus(status);
	                    employeeList.add(employee);
	                }
	            }
	        } catch (Exception ex) {
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
	    
	    
	    
	    private void showDetails() throws SQLException {
	        ObservableList<Employee> list = getEmployeeList();
	        col_employee_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeId"));
	        col_employeename_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
	        Datacol.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("date"));
	        col_status_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("leavestatus"));
	        tableView.setItems(list);
	    }

		
	    

	    @FXML
	    private void backbutton() 
	    {
	    	Buttons b=new Buttons("employeepage.fxml",backbutton);
	       	b.location();
	        // Handle back button action
	    }

}
