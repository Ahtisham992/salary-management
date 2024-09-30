package application;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBservices.chnageleavedb;
import application.mainclasses.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class ChangeLeavesController {

    @FXML
    private TextField paid_leaves;

    @FXML
    private TextField sick_leaves;

    @FXML
    private TextField casual_leaves;

    @FXML
    private Button leave_update;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> col_id;

    @FXML
    private TableColumn<Employee, String> col_name;

    @FXML
    private TableColumn<Employee, String> col_paid;

    @FXML
    private TableColumn<Employee, String> col_casual;

    @FXML
    private TableColumn<Employee, String> col_sick;

    private chnageleavedb chngedb;
    
    @FXML
    private Button backbutton;
    

    @FXML
    private void initialize() throws SQLException {
        // Initialization code goes here
    	chngedb=new chnageleavedb();
    	chngedb.setBackbutton(backbutton);
    	chngedb.setCasual_leaves(casual_leaves);
    	chngedb.setCol_casual(col_casual);
    	chngedb.setCol_id(col_id);
    	chngedb.setCol_name(col_name);
    	chngedb.setCol_paid(col_paid);
    	chngedb.setCol_sick(col_sick);
    	chngedb.setLeave_update(leave_update);
    	chngedb.setPaid_leaves(paid_leaves);
    	chngedb.setSick_leaves(sick_leaves);
    	chngedb.setTableView(tableView);
    	
    	
    	
    	chngedb.showdetails();
    	chngedb.setupTableListener();
    }
    
    @FXML
    private void updateLeaves() 
    {
    	chngedb.update();
        
    }

    
    @FXML
	private void backbutton() 
    {
      
        Buttons b=new Buttons("adminpage.fxml",backbutton);
     	b.location();
    	
    }
    
    
    
  
    
  }

