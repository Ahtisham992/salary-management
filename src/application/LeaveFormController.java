package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import application.DBservices.leaveformdb;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LeaveFormController {

    @FXML
    private TextField leave_subject;

    @FXML
    private TextField leave_description;

    @FXML
    private Button submit_leave;
    
    @FXML
    private Button backbutton;

    @FXML
    private Label errormessagelabel;

    @FXML
    private Label paidleavenumber;

    @FXML
    private Label casualleavenumber;
    
   

    @FXML
    private Label Sickleavenumber;
 
    private leaveformdb leavedb;
    
    @FXML
    private void initialize() 
    {
    	
    	 leavedb=new leaveformdb();
    	 leavedb.setCasualLeaveNumber(casualleavenumber);
    	 leavedb.setErrorMessageLabel(errormessagelabel);
    	 leavedb.setLeaveDescription(leave_description);
    	 leavedb.setLeaveSubject(leave_subject);
    	 leavedb.setPaidLeaveNumber(paidleavenumber);
    	 leavedb.setSickLeaveNumber(Sickleavenumber);
    	
    	
        // Fetch and display leave numbers for the logged-in user
    	 leavedb.fetchLeaveNumbers();

        submit_leave.setOnAction(event -> {
            if (leave_description.getText().isEmpty() && leave_subject.getText().isEmpty()) {
                errormessagelabel.setText("The form is not filled");
                errormessagelabel.setVisible(true);
            } else if (leave_description.getText().isEmpty()) {
                errormessagelabel.setText("Leave Description is empty");
                errormessagelabel.setVisible(true);
            } else if (leave_subject.getText().isEmpty()) {
                errormessagelabel.setText("Leave Subject is empty");
                errormessagelabel.setVisible(true);
            } else {
                if ( leavedb.checkLeaveBalances()) {
                	 leavedb.storeLeaveData();
                    errormessagelabel.setText("Form has been submitted");
                    errormessagelabel.setVisible(true);
                    leave_description.clear();
                    leave_subject.clear();
                } else {
                    errormessagelabel.setText("Insufficient leave balance");
                    errormessagelabel.setVisible(true);
                }
            }
        });
        
        backbutton.setOnAction(event ->
        {
        	 
             
             Buttons b=new Buttons("employeepage.fxml",backbutton);
           	b.location();
        }
        );
    }

    
}
