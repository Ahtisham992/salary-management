package application;




import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminLoginController 
{
	@FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button login;
    
    @FXML
    private Label errorLabel;

    @FXML
    private Button CancelButton;
    
    private Admin admin;
    
    @FXML
    private void CancelButtononaction( )
    {
    	
    	Buttons b=new Buttons("Login.fxml",CancelButton);
    	b.location();
    	
    }
    
    @FXML
    private void initialize()
    {
    	 login.setOnAction(e -> 
    	 {
    		
    		 
             String enteredUsername = username.getText();
             String enteredPassword = password.getText();

             if (validateLogin(enteredUsername, enteredPassword)) 
             {
            	 
                 Buttons b=new Buttons("adminpage.fxml",login);
             	b.location();
                 
             } 
             else 
             {
            	 errorLabel.setText("Invalid username or password");
                 errorLabel.setVisible(true);
             }
         });
     }

     private boolean validateLogin(String username, String password) 
     {
    	 admin=new Admin();
     
     return username.equals(admin.getName()) && password.equals(admin.getPassword()); 
    	 
     }
}

