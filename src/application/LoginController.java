package application;



import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;



public class LoginController
{
    @FXML
    private Button admin_login;

    @FXML
    private Button employee_login;
    
    @FXML
    private Button exitbutton;
  
    @FXML
    private void initialize()
    {
    	admin_login.setOnAction(e->
    	{
    		
    		
    		 Buttons b=new Buttons("adminlogin.fxml",admin_login);
            	b.location();
    	});
    	employee_login.setOnAction(e->
    	{
    		
    		 Buttons b=new Buttons("employeelogin.fxml",employee_login);
         	b.location();
    	});
    }
    
    @FXML
    private void exitbutton() 
    {
        
    	Stage currentStage = (Stage) exitbutton.getScene().getWindow();
        currentStage.close();
    }
    
}
