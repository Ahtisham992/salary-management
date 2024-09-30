package application;




import application.DBservices.userRegisterdatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserRegisterController
{
	@FXML
    private TextField user_name;

    @FXML
    private TextField user_phone;

    @FXML
    private TextField Password_textfield ;

    @FXML
    private TextField user_id;

    @FXML
    private TextField user_role;

    @FXML
    private Button Register_user;
    
    @FXML
    private Button clearscreen;
    
    @FXML
    private Button backbutton;
    
    private userRegisterdatabase userregister;
    
    @FXML 
    private Label registrationsuccessfullmessage;

    @FXML
    private void clearscreenonaction()
    {
    	user_name.clear();
    	user_phone.clear();
    	Password_textfield.clear();
    	user_id.clear();
    	user_role.clear();
    	registrationsuccessfullmessage.setText("");
    }
    
    @FXML
    private void registerbuttononaction()
    {
    	userregister=new userRegisterdatabase();
    	userregister.registeruser( user_id,user_name,Password_textfield, user_phone,user_role,registrationsuccessfullmessage);	
    }
    
    
    
    
    @FXML
	private void backbutton() 
    {
        
        Buttons b=new Buttons("adminpage.fxml",backbutton);
       	b.location();
    	
    }

  
}
