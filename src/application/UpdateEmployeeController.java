package application;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.SQLException;

import application.DBservices.updateregisterdatabase;
import application.mainclasses.Employee;



public class UpdateEmployeeController {
    @FXML
    private Button update_user;

    @FXML
    private TextField user_role;

    @FXML
    private TextField user_name;

    @FXML
    private TextField user_phone;

    @FXML
    private TextField Password_field;

    @FXML
    private TextField user_id;

    @FXML
    private TableView<Employee> viewfieldid;

    @FXML
    private TableColumn<Employee, String> col_NAME_ID;

    @FXML
    private TableColumn<Employee, String> col_phone_id;

    @FXML
    private TableColumn<Employee, String> col_password_id;

    @FXML
    private TableColumn<Employee, String> col_id_id;

    @FXML
    private TableColumn<Employee, String> role_id_id;

    @FXML
    private TextField search_user;

    @FXML
    private Button searchbutton;
    @FXML
    private Button deletebutton;
    @FXML
    private Button refreshbutton;
    
    @FXML
    private Button backbutton;
    
    private updateregisterdatabase dbupdate;
    
    
    @FXML
    private void handleSearchButtonAction() 
    {
    	
    		
            String searchName = search_user.getText();

            // Fetch data for the searched user from the database
            ObservableList<Employee> searchResults = dbupdate.searchEmployeeByName(searchName);

            // Display the searched user's details in the TableView
            viewfieldid.setItems(searchResults);
    	
    	
    }
    @FXML
    private void handleRefreshButtonAction() throws SQLException
    {
    	dbupdate.showDetails(col_phone_id,col_NAME_ID,col_password_id,col_id_id,role_id_id,viewfieldid);
    }
    @FXML
    private void handlebackButtonAction() throws SQLException
    {
    	
        
        Buttons b=new Buttons("adminpage.fxml",backbutton);
       	b.location();
    }

    
    @FXML
    private void handleDeleteButtonAction() 
    {
    	dbupdate.delete( col_phone_id, col_NAME_ID, col_password_id, col_id_id, role_id_id, viewfieldid);
    }


    
    
    
    
    public void initialize() throws SQLException 
    {
    	dbupdate= new updateregisterdatabase();
    	
    	dbupdate.setUserId(user_id);
    	dbupdate.setUserName(user_name);
    	dbupdate.setUserPassword(Password_field);
    	dbupdate.setUserPassword(Password_field);
    	dbupdate.setUserPhone(user_phone);
    	dbupdate.setUserRole(user_role);
    	
   
    	dbupdate.showDetails(col_phone_id,col_NAME_ID,col_password_id,col_id_id,role_id_id,viewfieldid);
    	update_user.setOnAction((e -> {
    		dbupdate.updateRecord(col_phone_id,col_NAME_ID,col_password_id,col_id_id,role_id_id,viewfieldid,user_id,Password_field,user_phone,user_name, user_role);
    	}));
    	
    	
    	
         
    	
    }

    
   
    

    
   


    
   
    
}
