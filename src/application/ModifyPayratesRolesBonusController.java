package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.mainclasses.Employee;
import application.mainclasses.payrate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModifyPayratesRolesBonusController {

    @FXML
    private TextField Roles;

    @FXML
    private TextField Salaryfield;

    @FXML
    private TextField Bonous;

    @FXML
    private Button payrate_update;

    @FXML
    private TableView<payrate> tableView;

    @FXML
    private TableColumn<payrate, String> col_id;

    @FXML
    private TableColumn<payrate, String> col_name;

    @FXML
    private TableColumn<payrate, String> col_Role;

    @FXML
    private TableColumn<payrate, String> col_salary;

    @FXML
    private TableColumn<payrate, String> col_bonus;

    @FXML
    private Button backbutton;

   // private Employee selectedEmployee;
    private payrate selectedpayrate;
    
    @FXML
    private void updatepayrate(ActionEvent event) {
        // Get the updated values from the text fields
        String updatedRole = Roles.getText();
        String updatedSalary = Salaryfield.getText();
        String updatedBonus = Bonous.getText();

        // Check if a payrate is selected from the table view
        if (selectedpayrate != null) {
            // Update the selected payrate object with the new values
            selectedpayrate.setRole(updatedRole);
            selectedpayrate.setSalary(updatedSalary);
            selectedpayrate.setBonus(updatedBonus);

            // Update the database with the modified payrate
            updatePayrateInDatabase(selectedpayrate);

            // Refresh the table view to reflect the changes
            tableView.refresh();
        }
    }

    private void updatePayrateInDatabase(payrate updatedPayrate) {
        // Code to update the payrate in the database based on the updatedPayrate object
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connection = connectNow.getConnection();

            // Create a statement to execute SQL queries
            Statement statement = connection.createStatement();

            // Construct the update query
            String query = "UPDATE db.employee_table2 SET user_role = '" + updatedPayrate.getRole()
                    + "', salary = '" + updatedPayrate.getSalary()
                    + "', bonus = '" + updatedPayrate.getBonus()
                    + "' WHERE user_id = '" + updatedPayrate.getEmployeeId() + "'";

            // Execute the update query
            statement.executeUpdate(query);

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Clear the text fields and refresh the table view
        
    }


    
    
    @FXML
    private void initialize() throws SQLException
    {
    	
    	showdetails();
    	setupTableListener();
    	
    }
    
    

    @FXML
    private void backbutton(ActionEvent event) {
        // Code to handle the back button action
    	Buttons b=new Buttons("adminpage.fxml",backbutton);
     	b.location();
    }
    
    
    
    public ObservableList<payrate> getemployeelist() throws SQLException
    {
    	ObservableList<payrate> employeeList= FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        // Create a statement to execute SQL queries
        Statement statement = connection.createStatement();

        // Execute the query to fetch employee data
        ResultSet resultSet = statement.executeQuery("SELECT * FROM db.employee_table2");
    try 
    {
    	tableView.getItems().clear();
         // Iterate over the result set and create Employee objects
    	 Employee employee;
    	 payrate Payrate;
    	 while (resultSet.next()) {
             
    		 String id =resultSet.getString("user_id");
             String name = resultSet.getString("user_name");
             String bonus= resultSet.getString("bonus");
             String salary=resultSet.getString("salary");
             String role=   resultSet.getString("user_role");
             
             employee = new Employee();
             Payrate =new payrate(); 
             employee.setEmployeeId(id);
             employee.setEmployeeName(name);
             
             Payrate.setBonus(bonus);
             Payrate.setSalary(salary);
             Payrate.setEmployeeId(id);
             Payrate.setEmployeeName(name);
             employee.setEmployeeRole(role);
             Payrate.setRole(role);
              
             employeeList.add(Payrate);
         }
    	 resultSet.close();
         statement.close();
         connection.close();
    }catch(Exception ex)
    {
    	ex.printStackTrace();
    }
	return employeeList;
        
    }
    
    
    public void showdetails() throws SQLException
	{
		ObservableList<payrate>List =getemployeelist();
		col_id.setCellValueFactory(new PropertyValueFactory<payrate, String>("employeeId"));
		col_name.setCellValueFactory(new PropertyValueFactory<payrate, String>("employeeName"));
		col_Role.setCellValueFactory(new PropertyValueFactory<payrate, String>("role"));
		col_salary.setCellValueFactory(new PropertyValueFactory<payrate, String>("salary"));
		col_bonus.setCellValueFactory(new PropertyValueFactory<payrate, String>("bonus"));
		tableView.setItems(List);
	}
    
    public void setupTableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                //selectedEmployee = newSelection;
                selectedpayrate= newSelection;
                Roles.setText(selectedpayrate.getRole());
                Salaryfield.setText(selectedpayrate.getSalary());
                Bonous.setText(selectedpayrate.getBonus());
            }
        });
    }
    
    
    
    

    // Add any additional methods or event handlers as needed

}
