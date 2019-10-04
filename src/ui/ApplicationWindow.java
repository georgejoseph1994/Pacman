package ui;


import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApplicationWindow  extends Application {
	 public static void main(String[] args) {
	        launch(args);
	    }
	    
	    @Override
	    public void start(Stage stage) {
	    	GameScene connectScene = new ConnectScene(stage);
	        
	        //Setting title to the Stage 
	        stage.setTitle("Pacman - The Game"); 
	           
	        //Adding scene to the stage 
	        stage.setScene(connectScene.scene); 
	           
	        //Displaying the contents of the stage 
	        stage.show();
	    }
		
}
