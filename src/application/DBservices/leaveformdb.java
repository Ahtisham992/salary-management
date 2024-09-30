package application.DBservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import application.DatabaseConnection;
import application.mainclasses.DataSingleton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class leaveformdb 
{
    private TextField leave_subject;
    private TextField leave_description;
  
    private Label errormessagelabel;
    private Label paidleavenumber;
    private Label casualleavenumber;
    DataSingleton data = DataSingleton.getInstance();
    private Label Sickleavenumber;

    
    public void setLeaveSubject(TextField leave_subject) {
        this.leave_subject = leave_subject;
    }

  
    public void setLeaveDescription(TextField leave_description) {
        this.leave_description = leave_description;
    }

    
    
    
    public void setErrorMessageLabel(Label errormessagelabel) {
        this.errormessagelabel = errormessagelabel;
    }

   
    public void setPaidLeaveNumber(Label paidleavenumber) {
        this.paidleavenumber = paidleavenumber;
    }

   
    public void setCasualLeaveNumber(Label casualleavenumber) {
        this.casualleavenumber = casualleavenumber;
    }

   
    public void setSickLeaveNumber(Label Sickleavenumber) {
        this.Sickleavenumber = Sickleavenumber;
    }
    
    
    public boolean checkLeaveBalances() 
    {
        String username = data.getusername();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();
        String query = "SELECT user_casualleaves, user_Sickleaves, user_paidleaves FROM db.employee_table2 WHERE user_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int casualLeaves = resultSet.getInt("user_casualleaves");
                int sickLeaves = resultSet.getInt("user_Sickleaves");
                int paidLeaves = resultSet.getInt("user_paidleaves");

                casualleavenumber.setText(String.valueOf(casualLeaves));
                Sickleavenumber.setText(String.valueOf(sickLeaves));
                paidleavenumber.setText(String.valueOf(paidLeaves));

                // Check if all leave balances are greater than zero
                return (casualLeaves > 0 && sickLeaves > 0 && paidLeaves > 0);
            }

            resultSet.close();
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

        return false;
    }
    

    public void storeLeaveData() 
    {
        String username = data.getusername();
        String leaveSubject = leave_subject.getText();
        String leaveDescription = leave_description.getText();
        LocalDateTime currentDateTime = LocalDateTime.now();

        String query = "INSERT INTO db.leavedescription (username, leave_subject, leave_description, created_at) VALUES (?, ?, ?, ?)";

        DatabaseConnection connectNow = new DatabaseConnection();

        try (Connection connection = connectNow.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, leaveSubject);
            statement.setString(3, leaveDescription);
            statement.setObject(4, currentDateTime);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                errormessagelabel.setText("Form has been submitted");
                errormessagelabel.setVisible(true);
            } else {
                errormessagelabel.setText("Failed to submit the form");
                errormessagelabel.setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
   
    

    public void fetchLeaveNumbers() 
    {
        // Fetch leave numbers from the database for the logged-in user
        String username =data.getusername();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();
        String query = "SELECT user_casualleaves, user_Sickleaves, user_paidleaves FROM db.employee_table2 WHERE user_name = '" + username + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String casualLeaves = resultSet.getString("user_casualleaves");
                String sickLeaves = resultSet.getString("user_Sickleaves");
                String paidLeaves = resultSet.getString("user_paidleaves");
                
                casualleavenumber.setText(casualLeaves);
                Sickleavenumber.setText(sickLeaves);
                paidleavenumber.setText(paidLeaves);
               
                
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
    
    
}
