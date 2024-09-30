package application;





import application.DBservices.Attendancedatabase;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;


public class AttendanceController {
    
    @FXML
    private CheckBox check_attendance;

    @FXML
    private Button update_attendance;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorLabel2;
    
    @FXML
    private Label attendanceshow;
    
    @FXML
    private Button Backbutton;
    
    private Attendancedatabase attdatabase;
    
  

    public void initialize() 
    {
    	
    	attdatabase=new Attendancedatabase();
    	
    	attdatabase.setAttendanceshow(attendanceshow);
    	attdatabase.setBackbutton(Backbutton);
    	attdatabase.setCheck_attendance(check_attendance);
    	attdatabase.setErrorLabel(errorLabel);
    	attdatabase.setErrorLabel2(errorLabel2);
    	attdatabase.setUpdate_attendance(update_attendance);
    	attdatabase.load();
        
    	
        Backbutton.setOnAction(event -> 
        {
            Buttons b=new Buttons("employeepage.fxml",Backbutton);
         	b.location();
            
            
        });
        
        
    }
    
   
   

    
    
    
}
