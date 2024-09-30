package application.DBservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DatabaseConnection;
import application.mainclasses.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class chnageleavedb {

	
	private TextField paid_leaves;

	
	private TextField sick_leaves;

	
	private TextField casual_leaves;

	
	private Button leave_update;

	
	private TableView<Employee> tableView;

	
	private TableColumn<Employee, String> col_id;

	
	private TableColumn<Employee, String> col_name;

	
	private TableColumn<Employee, String> col_paid;

	
	private TableColumn<Employee, String> col_casual;

	
	private TableColumn<Employee, String> col_sick;

	
	private Button backbutton;

	private Employee selectedEmployee;

	public TextField getPaid_leaves() {
		return paid_leaves;
	}

	public void setPaid_leaves(TextField paid_leaves) {
		this.paid_leaves = paid_leaves;
	}

	public TextField getSick_leaves() {
		return sick_leaves;
	}

	public void setSick_leaves(TextField sick_leaves) {
		this.sick_leaves = sick_leaves;
	}

	public TextField getCasual_leaves() {
		return casual_leaves;
	}

	public void setCasual_leaves(TextField casual_leaves) {
		this.casual_leaves = casual_leaves;
	}

	public Button getLeave_update() {
		return leave_update;
	}

	public void setLeave_update(Button leave_update) {
		this.leave_update = leave_update;
	}

	public TableView<Employee> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Employee> tableView) {
		this.tableView = tableView;
	}

	public TableColumn<Employee, String> getCol_id() {
		return col_id;
	}

	public void setCol_id(TableColumn<Employee, String> col_id) {
		this.col_id = col_id;
	}

	public TableColumn<Employee, String> getCol_name() {
		return col_name;
	}

	public void setCol_name(TableColumn<Employee, String> col_name) {
		this.col_name = col_name;
	}

	public TableColumn<Employee, String> getCol_paid() {
		return col_paid;
	}

	public void setCol_paid(TableColumn<Employee, String> col_paid) {
		this.col_paid = col_paid;
	}

	public TableColumn<Employee, String> getCol_casual() {
		return col_casual;
	}

	public void setCol_casual(TableColumn<Employee, String> col_casual) {
		this.col_casual = col_casual;
	}

	public TableColumn<Employee, String> getCol_sick() {
		return col_sick;
	}

	public void setCol_sick(TableColumn<Employee, String> col_sick) {
		this.col_sick = col_sick;
	}

	public Button getBackbutton() {
		return backbutton;
	}

	public void setBackbutton(Button backbutton) {
		this.backbutton = backbutton;
	}
	
	
	
	public ObservableList<Employee> getemployeelist() throws SQLException
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
    	tableView.getItems().clear();
         // Iterate over the result set and create Employee objects
    	 Employee employee;
    	 while (resultSet.next()) {
             String id = resultSet.getString("user_id");
             String name = resultSet.getString("user_name");
             String paid = resultSet.getString("user_paidleaves");
             String casual = resultSet.getString("user_casualleaves");
             String sick = resultSet.getString("user_Sickleaves");

             employee = new Employee();
             employee.setEmployeeId(id);
             employee.setEmployeeName(name);
             employee.setPaidLeaves(paid);
             employee.setCasualLeaves(casual);
             employee.setSickLeaves(sick);
              
             employeeList.add(employee);
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
		ObservableList<Employee>List =getemployeelist();
		col_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeId"));
		col_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
		col_paid.setCellValueFactory(new PropertyValueFactory<Employee, String>("paidLeaves"));
		col_casual.setCellValueFactory(new PropertyValueFactory<Employee, String>("casualLeaves"));
		col_sick.setCellValueFactory(new PropertyValueFactory<Employee, String>("sickLeaves"));
		tableView.setItems(List);
	}
    
    
    public void setupTableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedEmployee = newSelection;
                paid_leaves.setText(selectedEmployee.getPaidLeaves());
                casual_leaves.setText(selectedEmployee.getCasualLeaves());
                sick_leaves.setText(selectedEmployee.getSickLeaves());
            }
        });
    }

    
    

    private void clearFields() {
        paid_leaves.clear();
        casual_leaves.clear();
        sick_leaves.clear();
    }

    private void refreshTableView() {
        try {
            showdetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void update()
    {
    	if (selectedEmployee != null) {
            selectedEmployee.setPaidLeaves(paid_leaves.getText());
            selectedEmployee.setCasualLeaves(casual_leaves.getText());
            selectedEmployee.setSickLeaves(sick_leaves.getText());

            // Update the database table with the modified leave data
            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connection = connectNow.getConnection();

                Statement statement = connection.createStatement();
                String query = "UPDATE db.employee_table2 SET user_paidleaves = '" + selectedEmployee.getPaidLeaves() +
                        "', user_casualleaves = '" + selectedEmployee.getCasualLeaves() +
                        "', user_Sickleaves = '" + selectedEmployee.getSickLeaves() +
                        "' WHERE user_id = " + selectedEmployee.getEmployeeId();
                statement.executeUpdate(query);

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Clear the text fields and refresh the table view
            clearFields();
            refreshTableView();
        }
    }



  
    
	
	
	
	
}
