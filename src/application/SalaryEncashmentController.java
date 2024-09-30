package application;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.mainclasses.DataSingleton;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class SalaryEncashmentController {
    
	
	@FXML
    private Label employee_id;

    @FXML
    private Label employee_name;

    @FXML
    private Label employee_role;

    @FXML
    private Label after_basicsalary;

    @FXML
    private Label after_bonus;

    @FXML
    private Label after_sickleave;

    @FXML
    private Label after_casualleaves;

    @FXML
    private Label after_extratime;

    @FXML
    private Label after_tax;

    @FXML
    private Label extra_time;

    @FXML
    private Label casualleaves_taken;

    @FXML
    private Label bonus;

    @FXML
    private Label sickleaves_taken;

    @FXML
    private Label tax;

    @FXML
    private Label basic_salary;

    @FXML
    private Label total;
    
    @FXML
    private Label transermessage;

    @FXML
    private Button transfer_salary;
    @FXML
    private Button backbtton;

    private  double basicSalary;
    
    //private static boolean isTransferred = false;
    
   

    private DataSingleton data = DataSingleton.getInstance();

    @FXML
    private void initialize() {
        // Fetch and display login user data
        fetchUserData();
    }

    private void fetchUserData() 
    {
    	
    	
        String username = data.getusername(); // Replace with actual username

        String query = "SELECT user_id, user_name, user_role, user_casualleaves, user_sickleaves, bonus, salary FROM db.employee_table2 WHERE user_name = '" + username + "'";

        
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) 
            {
                String userId = resultSet.getString("user_id");
                String userName = resultSet.getString("user_name");
                String userRole = resultSet.getString("user_role");
                int casualLeaves = resultSet.getInt("user_casualleaves");
                int sickLeaves = resultSet.getInt("user_sickleaves");
                double salary = resultSet.getDouble("salary");
                double bonus1 =resultSet.getDouble("bonus");

                employee_id.setText(userId);
                employee_name.setText(userName);
                employee_role.setText(userRole);
                
                if(data.getistransferedcheck())
                {
                	clearLabels();
                }
                else 
                {
                	// Generate and display the basic salary based on the employee's role
                     basicSalary = salary;
                    
                    
                    basic_salary.setText(String.valueOf(basicSalary));
                    after_basicsalary.setText(String.valueOf(basicSalary));
                    
                    
                 // Calculate and display the bonus based on employee attendance
                    int attendanceCount = getAttendanceCount(username);
                    
                    double bonusAmount = calculateBonus(attendanceCount)+ bonus1 ;
                    
                    bonus.setText(String.valueOf(bonusAmount));
                    after_bonus.setText(String.valueOf(basicSalary + bonusAmount));
                    basicSalary=basicSalary + bonusAmount;
                    

                    // Deduct sick leave and casual leave randomly if the leave threshold has ended
                    if (casualLeaves <= 0) {
                        double casualLeaveDeduction = generateLeaveDeduction();
                        casualleaves_taken.setText(String.valueOf(casualLeaveDeduction));
                        after_casualleaves.setText(String.valueOf(basicSalary - casualLeaveDeduction));
                        basicSalary=basicSalary - casualLeaveDeduction;
                    } else {
                        casualleaves_taken.setText("0.0");
                        after_casualleaves.setText(String.valueOf(basicSalary));
                        
                    }

                    if (sickLeaves <= 0) {
                        double sickLeaveDeduction = generateLeaveDeduction();
                        sickleaves_taken.setText(String.valueOf(sickLeaveDeduction));
                        after_sickleave.setText(String.valueOf(basicSalary - sickLeaveDeduction));
                        basicSalary=basicSalary - sickLeaveDeduction;
                    } else {
                        sickleaves_taken.setText("0.0");
                        after_sickleave.setText(String.valueOf(basicSalary));
                    }

                    if(basicSalary>0)
                    {
                    	  // Generate and display the extra time randomly
                    	double extraTime = generateExtraTime();
                        extra_time.setText(String.format("%.2f", extraTime));
                        after_extratime.setText(String.format("%.2f", basicSalary + extraTime));
                        basicSalary=basicSalary + extraTime;
                        // Generate and display the government tax randomly
                        double taxAmount = generateTax();
                        tax.setText(String.format("%.2f", taxAmount));
                        after_tax.setText(String.format("%.2f", basicSalary - taxAmount));
                        basicSalary=basicSalary - taxAmount;
                    }
                    else
                    {
                    	extra_time.setText(String.format("%.2f", 0.0));
                        after_extratime.setText(String.format("%.2f", basicSalary + 0.0));
                        basicSalary=basicSalary + 0.0;
                        // Generate and display the government tax randomly
                        tax.setText(String.format("%.2f",0.0));
                        after_tax.setText(String.format("%.2f", basicSalary - 0.0));
                    	
                    }
                    
                    
          
                    // Calculate and display the total salary
                    double totalSalary = basicSalary ;
                    
                    total.setText(String.format("%.2f", totalSalary));
     
                    transfer_salary.setOnAction(event -> transferSalary());
                }

                
                backbtton.setOnAction(event -> back());
                
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

    
    @FXML
    private void back() {
        

        Buttons b=new Buttons("employeepage.fxml",backbtton);
       	b.location();
        
    }
    
    
    private void updateSalaryInDatabase() {
        String username = data.getusername(); // Replace with actual username

        String query = "UPDATE db.employee_table2 SET salary = 0.0, bonus = 0.0 WHERE user_name = '" + username + "'";

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
    private void transferSalary() 
    {
        if (!data.getistransferedcheck() && basicSalary !=0.0 ) 
        {
            // Display transfer message
        	updateSalaryInDatabase();
            transermessage.setText("Funds have been transferred.");

            // Set the flag to indicate transfer has been made
            data.setistransferedcheck(true);

            // Clear all label values
            clearLabels();
            
            
        }
        else 
        {
        	transermessage.setText("Your Balance is Zero Unable to transfer");

            // Set the flag to indicate transfer has been made
            data.setistransferedcheck(true);
        }
    }
    
    
    private void clearLabels() {
        after_basicsalary.setText("0");
        after_bonus.setText("0");
        after_sickleave.setText("0");
        after_casualleaves.setText("0");
        after_extratime.setText("0");
        after_tax.setText("0");
        extra_time.setText("0");
        casualleaves_taken.setText("0");
        bonus.setText("0");
        sickleaves_taken.setText("0");
        tax.setText("0");
        basic_salary.setText("0");
        total.setText("0");
    }
    
    
    private double generateLeaveDeduction() {
        // Generate a random leave deduction amount within a specified range
        double minDeduction = 100.0;
        double maxDeduction = 500.0;
        double leaveDeduction = minDeduction + Math.random() * (maxDeduction - minDeduction);

        // Round the deduction amount to two decimal places
        leaveDeduction = Math.round(leaveDeduction * 100.0) / 100.0;

        return leaveDeduction;
    }


    private int getAttendanceCount(String username) {
        String query = "SELECT COUNT(*) AS attendance_count FROM db.attendance WHERE username = '" + username + "'";
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int attendanceCount = resultSet.getInt("attendance_count");
                return attendanceCount;
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

        return 0;
    }

    private double calculateBonus(int attendanceCount) {
        // Define the bonus calculation based on attendance count
        double bonusPercentage;

        if (attendanceCount >= 20) {
            bonusPercentage = 0.1; // 10% bonus
        } else if (attendanceCount >= 15) {
            bonusPercentage = 0.07; // 7% bonus
        } else if (attendanceCount >= 10) {
            bonusPercentage = 0.05; // 5% bonus
        } else {
            bonusPercentage = 0.0; // No bonus
        }

        // Calculate the bonus amount based on basic salary
        double basicSalary = Double.parseDouble(basic_salary.getText());
        double bonusAmount = basicSalary * bonusPercentage;

        // Round the bonus amount to two decimal places
        bonusAmount = Math.round(bonusAmount * 100.0) / 100.0;

        return bonusAmount;
    }


//    private double generateBasicSalary(String userRole) {
//        // Define the basic salary ranges for different roles
//        double minSalary;
//        double maxSalary;
//
//     // Set the salary ranges based on the user role (case-insensitive)
//        if (userRole.toLowerCase().equals("manager")) {
//            minSalary = 5000.0;
//            maxSalary = 8000.0;
//        } else if (userRole.toLowerCase().equals("developer")) {
//            minSalary = 4000.0;
//            maxSalary = 6000.0;
//        } else if (userRole.toLowerCase().equals("designer")) {
//            minSalary = 3500.0;
//            maxSalary = 5500.0;
//        } else {
//            minSalary = 3000.0;
//            maxSalary = 5000.0;
//        }
//
//        // Generate a random basic salary within the specified range
//        double basicSalary = minSalary + Math.random() * (maxSalary - minSalary);
//
//        // Round the salary to two decimal places
//        basicSalary = Math.round(basicSalary * 100.0) / 100.0;
//
//        return basicSalary;
//    }
    
    
    private double generateExtraTime() {
        // Generate a random extra time value within a specified range
        double minExtraTime = 0.0;
        double maxExtraTime = 20.0;
        double extraTime = minExtraTime + Math.random() * (maxExtraTime - minExtraTime);

        // Round the extra time to two decimal places
        extraTime = Math.round(extraTime * 100.0) / 100.0;

        return extraTime;
    }

    private double generateTax() {
        // Generate a random tax amount within a specified range
        double minTax = 100.0;
        double maxTax = 500.0;
        double taxAmount = minTax + Math.random() * (maxTax - minTax);

        // Round the tax amount to two decimal places
        taxAmount = Math.round(taxAmount * 100.0) / 100.0;

        return taxAmount;
    }

}
