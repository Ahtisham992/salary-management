package application;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.mainclasses.DataSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class EmplloyeeLoginController 
{
	  @FXML
	    private TextField employee_name;

	    @FXML
	    private TextField employee_password;

	    @FXML
	    private Button login;
	    
	    @FXML
	    private Button backbutton;
	    
	    @FXML
	    private Label error1;
	    
	    DataSingleton data= DataSingleton.getInstance(); 
	    
	    @FXML
	    private void initialize() 
	    {
	        error1.setVisible(false); // Set the label initially invisible

	        
	        login();
	    	 
	    	 backbutton.setOnAction(event -> {
	             
	              Buttons b=new Buttons("Login.fxml",backbutton);
	              	b.location();
	          }
	          );
	    	 
	    	 
	     }
	    
	    
	    
	    @FXML
	    private void login()
	    {
	    	login.setOnAction(e -> {
	            String enteredUsername = employee_name.getText();
	            String enteredPassword = employee_password.getText();

	            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
	                error1.setText("Username or password is empty");
	                error1.setVisible(true); // Show the error message
	            } else if (validateLogin(enteredUsername, enteredPassword)) {
	                data.setUsername(enteredUsername);
	                Buttons b = new Buttons("employeepage.fxml", login);
	                b.location();
	            } else {
	                error1.setText("Invalid username or password");
	                error1.setVisible(true); // Show the error message
	            }
	        });
	    }
	    

	   	    
	    private boolean validateLogin(String username, String password) {
	        boolean isValid = false;
	        DatabaseConnection connectNow = new DatabaseConnection();
	        Connection connection = connectNow.getConnection();
	        String query = "SELECT * FROM db.employee_table2 WHERE user_name = '" + username + "'";
	        
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(query);
	            
	            if (resultSet.next()) {
	                String dbUsername = resultSet.getString("user_name");
	                String dbPassword = resultSet.getString("user_password");
	                
	                isValid = username.equals(dbUsername) && password.equals(dbPassword);
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
	        
	        return isValid;
	    }


}
