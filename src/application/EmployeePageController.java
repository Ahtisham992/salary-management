package application;



import application.mainclasses.DataSingleton;
import javafx.fxml.FXML;

import javafx.scene.control.Button;


public class EmployeePageController {
	@FXML
    private Button mark_attendance;

    @FXML
    private Button request_leave;

    @FXML
    private Button salary_encashment;

    @FXML
    private Button Showattendance1;
    
    
    @FXML
    private Button logoutbutton;
    
    private DataSingleton data = DataSingleton.getInstance();
    
    @FXML
    private void initialize() {
    	
        mark_attendance.setOnAction(event->{
        	
        	 Buttons b=new Buttons("attendance.fxml",mark_attendance);
           	b.location();
        });
        
        
        
        Showattendance1.setOnAction(event->{
        	
       	 Buttons b=new Buttons("AttendenceShow.fxml",mark_attendance);
          	b.location();
       });

        request_leave.setOnAction(event -> {
        	
        	
        	Buttons b=new Buttons("leaveform.fxml",request_leave);
           	b.location();
        	
        });

        salary_encashment.setOnAction(event -> {
        	
            
            Buttons b=new Buttons("salaryencashment.fxml",salary_encashment);
           	b.location();
            
            
        });
        
        
        logoutbutton.setOnAction(event -> 
        {
          
         
            data.setistransferedcheck(false);
            data.setUsername(null);
            data.setid(null);
            data.setstatus(null);

            Buttons b=new Buttons("employeelogin.fxml",logoutbutton);
           	b.location();
        }
        );
        
        
    }
}
