package application.DBservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DatabaseConnection;
import application.mainclasses.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class updateregisterdatabase 
{
	
	
	
    private TextField user_role;

    
    private TextField user_name;

   
    private TextField user_phone;

    
    private TextField Password_field;

    
    private TextField user_id;

    // Add setters for the TextField fields
    public void setUserRole( TextField role) 
    {
        this.user_role=role;
    }

    public void setUserName( TextField name) {
      this.user_name=name;
        
        
    }

    public void setUserPhone( TextField phone) {
        this.user_phone=phone;
        
    }

    public void setUserPassword( TextField password) {
      
        this.Password_field=password;
    }

    public void setUserId( TextField id) {
        
        this.user_id=id;
    }
	
	
	
	public ObservableList<Employee> searchEmployeeByName(String name) {
        ObservableList<Employee> searchResults = FXCollections.observableArrayList();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM db.employee_table2 WHERE user_name LIKE '%" + name + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("user_id");
                String employeeName = resultSet.getString("user_name");
                String role = resultSet.getString("user_role");
                String phone = resultSet.getString("user_phone");
                String password = resultSet.getString("user_password");

                Employee employee = new Employee(id, employeeName, role, phone, password);
                searchResults.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }
	
	
	
	 public ObservableList<Employee> getemployeelist(TableView<Employee> viewfieldid) throws SQLException
	    {
	    	ObservableList<Employee> employeeList= FXCollections.observableArrayList();
	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();

	        // Create a statement to execute SQL queries
	        Statement statement = connection.createStatement();

	        // Execute the query to fetch employee data
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM db.employee_table2");
	    try 
	    {
	    	 viewfieldid.getItems().clear();
	         // Iterate over the result set and create Employee objects
	    	 Employee employee;
	    	 while (resultSet.next()) 
	         {
	             String id = resultSet.getString("user_id");
	             String name = resultSet.getString("user_name");
	             String role = resultSet.getString("user_role");
	             String phone = resultSet.getString("user_phone");
	             String password = resultSet.getString("user_password");

	             // Create an Employee object with the fetched data
	             employee = new Employee(id, name, role, phone, password);
	             employeeList.add(employee);
	           
	         }
	    }catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
		return employeeList;
	        
	    }
	
	
	 public void updateRecord(TableColumn<Employee, String> col_phone_id,TableColumn<Employee, String> col_NAME_ID,TableColumn<Employee, String> col_password_id,TableColumn<Employee, String> col_id_id,TableColumn<Employee, String> role_id_id,TableView<Employee> viewfieldid,TextField user_id,TextField Password_field,TextField user_phone,TextField user_name,TextField user_role) 
	    {
	        Employee selectedEmployee = viewfieldid.getSelectionModel().getSelectedItem();
	        if (selectedEmployee != null) {
	            String id = selectedEmployee.getEmployeeId();
	            String newId = user_id.getText(); // New ID input from user
	            String name = user_name.getText();
	            String role = user_role.getText();
	            String phone = user_phone.getText();
	            String password = Password_field.getText();

	            // Update the employee record in the database
	            DatabaseConnection connectNow = new DatabaseConnection();
	            Connection connection = connectNow.getConnection();

	            try {
	                Statement statement = connection.createStatement();
	                String query = "UPDATE db.employee_table2 SET user_id='" + newId + "', user_name='" + name + "', user_role='" + role + "', user_phone='" + phone + "', user_password='" + password + "' WHERE user_id='" + id + "'";
	                statement.executeUpdate(query);

	                // Refresh the TableView to reflect the updated record
	                showDetails(col_phone_id,col_NAME_ID,col_password_id,col_id_id,role_id_id,viewfieldid);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	 
	 public void showDetails(TableColumn<Employee, String> col_phone_id, TableColumn<Employee, String> col_NAME_ID,TableColumn<Employee, String> col_password_id, TableColumn<Employee, String> col_id_id, TableColumn<Employee, String> role_id_id, TableView<Employee> viewfieldid) throws SQLException {
		    ObservableList<Employee> employeeList = getemployeelist(viewfieldid);
		    col_id_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeId"));
		    col_NAME_ID.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
		    role_id_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeRole"));
		    col_phone_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("userPhoneNumber"));
		    col_password_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("userPassword"));
		    viewfieldid.setItems(employeeList);

		    // Set a listener to handle row selection events
		    viewfieldid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		        if (newSelection != null) {
		            // Get the selected employee
		            Employee selectedEmployee = viewfieldid.getSelectionModel().getSelectedItem();

		            // Update the labels with the selected employee's data
		            user_id.setText(selectedEmployee.getEmployeeId());
		            user_name.setText(selectedEmployee.getEmployeeName());
		            user_role.setText(selectedEmployee.getEmployeeRole());
		            user_phone.setText(selectedEmployee.getUserPhoneNumber());
		            Password_field.setText(selectedEmployee.getUserPassword());
		        } else {
		            // Clear the labels if no employee is selected
		            user_id.setText("");
		            user_name.setText("");
		            user_role.setText("");
		            user_phone.setText("");
		            Password_field.setText("");
		        }
		    });
		}

	
	public void delete(TableColumn<Employee, String> col_phone_id, TableColumn<Employee, String> col_NAME_ID,TableColumn<Employee, String> col_password_id, TableColumn<Employee, String> col_id_id,TableColumn<Employee, String> role_id_id, TableView<Employee> viewfieldid)
	{
		Employee selectedEmployee = viewfieldid.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            String id = selectedEmployee.getEmployeeId();

            // Delete the employee record from the database
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connection = connectNow.getConnection();

            try {
                Statement statement = connection.createStatement();
                String query = "DELETE FROM db.employee_table2 WHERE user_id='" + id + "'";
                statement.executeUpdate(query);

                // Refresh the TableView to reflect the deleted record
                showDetails(col_phone_id,col_NAME_ID,col_password_id,col_id_id,role_id_id,viewfieldid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}



	
	

}
