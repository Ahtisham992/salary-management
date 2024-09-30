package application.DBservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import application.DatabaseConnection;
import application.mainclasses.DataSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class Attendancedatabase {
	
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

	private DataSingleton data = DataSingleton.getInstance();

	public CheckBox getCheck_attendance() {
		return check_attendance;
	}

	public void setCheck_attendance(CheckBox check_attendance) {
		this.check_attendance = check_attendance;
	}

	public Button getUpdate_attendance() {
		return update_attendance;
	}

	public void setUpdate_attendance(Button update_attendance) {
		this.update_attendance = update_attendance;
	}

	public Label getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(Label errorLabel) {
		this.errorLabel = errorLabel;
	}

	public Label getErrorLabel2() {
		return errorLabel2;
	}

	public void setErrorLabel2(Label errorLabel2) {
		this.errorLabel2 = errorLabel2;
	}

	public Label getAttendanceshow() {
		return attendanceshow;
	}

	public void setAttendanceshow(Label attendanceshow) {
		this.attendanceshow = attendanceshow;
	}

	public Button getBackbutton() {
		return Backbutton;
	}

	public void setBackbutton(Button Backbutton) {
		this.Backbutton = Backbutton;
	}
	
	
	
	 public void markAttendance() 
	    {
	        String username = data.getusername(); // Replace with actual username
	        LocalDate currentDate = LocalDate.now();

	        // Check if the user has already marked attendance for the current date
	        String checkQuery = "SELECT * FROM db.attendance WHERE username = ? AND date = ?";
	        String insertQuery = "INSERT INTO db.attendance (username, date, status) VALUES (?, ?, 'present')";
	        String errorMessage = "Attendance not updated";

	        DatabaseConnection connectNow = new DatabaseConnection();

	        try (
	                Connection connection = connectNow.getConnection();
	                PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)
	        ) {
	            // Check if the user has already marked attendance
	            checkStatement.setString(1, username);
	            checkStatement.setObject(2, currentDate);

	            // Execute the query to check for existing attendance
	            ResultSet resultSet = checkStatement.executeQuery();

	            if (resultSet.next()) {
	                // User has already marked attendance for the current date
	                errorLabel2.setText("Attendance already marked for today");
	                errorLabel2.setVisible(true);
	            } else {
	                // User has not marked attendance for the current date, mark attendance now
	                insertStatement.setString(1, username);
	                insertStatement.setObject(2, currentDate);

	                int rowsAffected = insertStatement.executeUpdate();

	                if (rowsAffected > 0) {
	                    errorLabel.setText("Attendance updated.");
	                    errorLabel.setVisible(true);
	                } else {
	                    errorLabel2.setText(errorMessage);
	                    errorLabel2.setVisible(true);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void load()
	 {
		 LocalDate currentDate1 = LocalDate.now();
	    	
	    	attendanceshow.setText(currentDate1.toString());
	    	attendanceshow.setVisible(true);
	        update_attendance.setOnAction(event -> {
	            if (check_attendance.isSelected()) {
	            	
	            	
	            	
	              markAttendance();
	                
	            } else {
	                errorLabel2.setText("Attendance not updated");
	                errorLabel2.setVisible(true);
	            }
	        });
	 }
	
	
}
