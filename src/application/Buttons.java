package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Buttons 
{
	
	@FXML
	private Button b;
	
	private String movename;
	Buttons()
	{
		movename="";	
	}
	public Buttons(String move,Button c)
	{
		movename=move;	
		this.b=c;
	}
	public void setmovename(String m)
	{
		this.movename=m;
	}
	
	public String getmovename()
	{
		return this.movename;
	}
	
	public void location()
	{
		
		try {
			Stage stage2 =(Stage) b.getScene().getWindow();
	    	stage2.close();
            // Load the Login Page
            FXMLLoader loader = new FXMLLoader(getClass().getResource(movename));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

}
