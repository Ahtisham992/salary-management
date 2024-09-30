package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.mainclasses.DataSingleton;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeaveDecisionController 
{
	 @FXML
	    private TextField username;

	    @FXML
	    private TextField leavedescription;

	    @FXML
	    private TextField leavesubject;

	    @FXML
	    private Button Accept_leave;

	    @FXML
	    private Button Reject_leave;
	    
	    
	    @FXML
	    private Button paid;
	    
	    @FXML
	    private Button casual;
	    
	    @FXML
	    private Button sick;
	    
	    @FXML
	    private Button backbutton;

	    @FXML
	    private TextField employeeid;
	    
	    @FXML 
	    private Label paidleavenumber1;
	    
	    @FXML 
	    private Label casualleavenumber2;
	    
	    @FXML 
	    private Label Sickleavenumber3;
	    
	    DataSingleton data= DataSingleton.getInstance(); 
	    private int paidLeavesCount;
	    private int casualLeavesCount;
	    private int sickLeavesCount;
	    
	    private boolean acceptLeaveClicked = false;
	    private boolean casualLeaveClicked= false;
	    private boolean sickLeaveClicked= false;
	    private boolean paidLeaveClicked= false;

	    @FXML
	    private void initialize() {
	        // Initialization code goes here
	    	String username1 =data.getusername();
	        String id=data.getid();
	    	
	    	fetchLeaveNumbers(username1,id);
	    	fetchSelectedUserData();
	    	
	    }
	    
	    
	    private void fetchLeaveNumbers(String username1, String id) {
	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();
	        String query = "SELECT user_casualleaves, user_Sickleaves, user_paidleaves FROM db.employee_table2 WHERE user_name = '" + username1 + "'";

	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(query);

	            if (resultSet.next()) {
	                String casualLeaves = resultSet.getString("user_casualleaves");
	                String sickLeaves = resultSet.getString("user_Sickleaves");
	                String paidLeaves = resultSet.getString("user_paidleaves");

	                casualleavenumber2.setText(casualLeaves);
	                Sickleavenumber3.setText(sickLeaves);
	                paidleavenumber1.setText(paidLeaves);
	                username.setText(username1);
	                employeeid.setText(id);

	                casualLeavesCount = Integer.parseInt(casualLeaves);
	                sickLeavesCount = Integer.parseInt(sickLeaves);
	                paidLeavesCount = Integer.parseInt(paidLeaves);
	            }

	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    
	    private void fetchSelectedUserData() {
	        

	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();
	        String query = "SELECT leave_subject, leave_description FROM db.leavedescription WHERE username = '"
	                + data.getusername() + "'";

	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(query);

	            if (resultSet.next()) {
	                String leaveSubject = resultSet.getString("leave_subject");
	                String leaveDescription = resultSet.getString("leave_description");

	                leavesubject.setText(leaveSubject);
	                leavedescription.setText(leaveDescription);
	            }

	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    

	    // Add event handlers for Accept_leave and Reject_leave buttons
	    @FXML
	    private void handleAcceptLeave() 
	    {
	        acceptLeaveClicked = true;
	        updateLeaveStatus("Accepted");
	        Reject_leave.setDisable(true);
	        
	    }
	    @FXML
	    private void handleRejectLeave() 
	    {
	    	System.out.println("reject");
	        // Handle Reject_leave button action
	        updateLeaveStatus("Rejected");
	        disableLeaveButtons();
	        Accept_leave.setDisable(true);
	    }

	    private void updateLeaveStatus(String status) {
	        // Update the status_leave column in the leavedescription table for the selected user
	        String query = "UPDATE db.leavedescription SET status_leave = '" + status + "' WHERE username = '"
	                + data.getusername() + "'";

	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();

	        try {
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(query);
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    
	    
	 
	    
	    @FXML
	    private void casualLeave() {
	        if (acceptLeaveClicked && !casualLeaveClicked) 
	        {
	            decrementLeaveCount("user_casualleaves");
	            casualLeaveClicked=true;
		        paid.setDisable(true);
		        sick.setDisable(true);
	            
	        }
	    }

	    @FXML
	    private void sickLeave() {
	        if (acceptLeaveClicked && !sickLeaveClicked) {
	            decrementLeaveCount("user_Sickleaves");
	            sickLeaveClicked=true;
	            casual.setDisable(true);
		        paid.setDisable(true);
		        
	        }
	    }

	    @FXML
	    private void paidLeave1() 
	    {
	        if (acceptLeaveClicked && !paidLeaveClicked) 
	        {
	            decrementLeaveCount("user_paidleaves");
	            paidLeaveClicked=true;
	            casual.setDisable(true);
		        sick.setDisable(true);
	        }
	    }
	    
	    
	    @FXML
	    private void backbutton() 
	    {
	        
	    	
	        
	        Buttons b=new Buttons("leaveapplications.fxml",backbutton);
          	b.location();
	    	
	    }
	    

	    private void decrementLeaveCount(String leaveType) 
	    {
	        if (leaveType.equals("user_paidleaves") && paidLeavesCount>0 ) 
	        {
	            paidLeavesCount--;
	            paidleavenumber1.setText(Integer.toString(paidLeavesCount));
	        } 
	        else if (leaveType.equals("user_casualleaves") ) 
	        {
	        	casualLeavesCount--;
	        	casualleavenumber2.setText(Integer.toString(casualLeavesCount));
	        	}
	        else if (leaveType.equals("user_Sickleaves") )
	        {
	        	sickLeavesCount--;
	        	Sickleavenumber3.setText(Integer.toString(sickLeavesCount));
	        }
	        // Update the leave count in the database for the selected user
	        String query = "UPDATE db.employee_table2 SET " + leaveType + " = " + leaveType + " - 1 WHERE user_name = '"
	                + data.getusername() + "'";

	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();

	        try {
	            Statement statement = connection.createStatement();
	            statement.executeUpdate(query);
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	

	    private void disableLeaveButtons() {
	        casual.setDisable(true);
	        paid.setDisable(true);
	        sick.setDisable(true);
	    }
	        
	    
	    
}
