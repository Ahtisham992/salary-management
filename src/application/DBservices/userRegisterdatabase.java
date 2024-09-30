package application.DBservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DatabaseConnection;
import application.mainclasses.Employee;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class userRegisterdatabase 

{
	
	
	
	public void registeruser(TextField user_id, TextField user_name, TextField password_textfield, TextField user_phone,TextField user_role, Label registrationsuccessfullmessage) 
	{
    	
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();
        Employee emp = new Employee();

        emp.setEmployeeId(user_id.getText());
        emp.setEmployeeName(user_name.getText());
        emp.setUserPassword(password_textfield.getText());
        emp.setUserPhoneNumber(user_phone.getText());
        emp.setEmployeeRole(user_role.getText());
        emp.setCasualLeaves("5");
        emp.setSickLeaves("5");
        emp.setPaidLeaves("5");

        // Check if the employee with the same ID or username already exists
        String checkQuery = "SELECT COUNT(*) AS count FROM db.employee_table2 WHERE user_id = '" + emp.getEmployeeId()
                + "' OR user_name = '" + emp.getEmployeeName() + "'";

        try {
            Statement checkStatement = connection.createStatement();
            ResultSet checkResult = checkStatement.executeQuery(checkQuery);

            if (checkResult.next()) {
                int count = checkResult.getInt("count");
                if (count > 0) {
                    // Employee with the same ID or username already exists
                    registrationsuccessfullmessage.setText("Employee with the same ID or username already exists");
                    registrationsuccessfullmessage.setVisible(true);
                    return;
                }
            }

            // Insert the new employee into the database
            String insertField = "INSERT INTO db.employee_table2(user_id, user_name, user_role, user_phone, user_password, user_casualleaves, user_Sickleaves, user_paidleaves) VALUES ('";
            String insertValues = emp.getEmployeeId() + "','" + emp.getEmployeeName() + "','" + emp.getEmployeeRole()
                    + "','" + emp.getUserPhoneNumber() + "','" + emp.getUserPassword() + "','"
                    + emp.getCasualLeaves() + "','" + emp.getSickLeaves() + "','"
                    + emp.getPaidLeaves() + "')";

            String insertToregister = insertField + insertValues;

            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToregister);

            registrationsuccessfullmessage.setText("User Registered Successfully");
            registrationsuccessfullmessage.setVisible(true);

            statement.close();
        } catch (Exception e) {
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
