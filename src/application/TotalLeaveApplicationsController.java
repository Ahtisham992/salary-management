package application;



import java.sql.SQLException;

import application.DBservices.TotalLeaveDatabase;
import application.mainclasses.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class TotalLeaveApplicationsController
{
    // Controller code goes here
	@FXML
	private TableView<Employee> tableView;

	@FXML
	private TableColumn<Employee, String> col_employee_id;

	@FXML
	private TableColumn<Employee, String> col_employeename_id;

	@FXML
	private TableColumn<Employee, String> col_employee_subject_id;

	@FXML
	private TableColumn<Employee, String> col_description_id;

	@FXML
	private TableColumn<Employee, String> col_status_id;
	
	 private TotalLeaveDatabase totleavedb;

	@FXML
	private Button select_leave;
	
	@FXML
	private Button backbutton;

	// Add more fields for other elements as needed
	
	
	   public void initialize() throws SQLException 
	   {
		   totleavedb= new TotalLeaveDatabase();
		   totleavedb.setCol_description_id(col_description_id);
		   totleavedb.setCol_employee_id(col_employee_id);
		   totleavedb.setCol_employee_subject_id(col_employee_subject_id);
		   totleavedb.setCol_employeename_id(col_employeename_id);
		   totleavedb.setCol_status_id(col_status_id);
		   totleavedb.setSelect_leave(select_leave);
		   totleavedb.setTableView(tableView);
		   totleavedb.showdetails();
	    	
	    }
	
	   
	   @FXML
	    private void handleselectAction()
	   {
		   totleavedb.select();
	   }
	
	
	
	@FXML
	private void backbutton() 
    {
        
    	
        Buttons b=new Buttons("adminpage.fxml",backbutton);
       	b.location();
    	
    }
	

}

