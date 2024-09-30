package application;



import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class AdminPageController 
{
	@FXML
    private Button registeremployee;

    @FXML
    private Button changeleaves;

    @FXML
    private Button viewrequests;

    @FXML
    private Button updateemployee;
    
    @FXML
    private Button updatepayrate;
    
    @FXML
    private Button logoutbutton;
    @FXML
    private void initialize()
    {
    	registeremployee.setOnAction(e->
    	{
    		
    	
    		Buttons b=new Buttons("userregister.fxml",registeremployee);
         	b.location();
    		
    	});
    	changeleaves.setOnAction(e->
    	{
    		
    		Buttons b=new Buttons("changeleaves.fxml",changeleaves);
         	b.location();
    	});
    	viewrequests.setOnAction(e->
    	{
    		
    		Buttons b=new Buttons("leaveapplications.fxml",viewrequests);
         	b.location();
    	});
    	updateemployee.setOnAction(e->
    	{
    		
    		Buttons b=new Buttons("updateemployee.fxml",updateemployee);
         	b.location();
    		
    		
    	}
    	);
    	
    	updatepayrate.setOnAction(e->
    	{
    		
    		Buttons b=new Buttons("modifyrates_roles_bonus.fxml",updatepayrate);
         	b.location();
    		
    		
    	}
    	);
    	
    	
    }
    
    
    @FXML
	private void logoutbutton() 
    {
        
    	
        Buttons b=new Buttons("adminlogin.fxml",logoutbutton);
     	b.location();
    	
    	
    }
    
}
