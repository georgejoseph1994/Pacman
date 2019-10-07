package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnectScene extends GameScene{
	
	ConnectScene(Stage stage) {
		super(stage);
    	GameScene chooseMapScene = new ChooseMapScene(stage);
    	
		HBox hbox = this.generateHeaderHBox();
		//creating label IP 
        Text text1 = new Text("Server IP");       
        
  	  
        //Creating Text Filed for IP        
        TextField textField1 = new TextField();       
          
         
        //Creating Button
        Button button1 = new Button("Connect");
        button1.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Connect attempt with IP: " + textField1.getText());
                rootStage.setScene(chooseMapScene.scene);
                // Write code here to actually send the IP to server
                // and call the next stage on successful connection
            }
        });
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        
        //Setting size for the pane  
        gridPane.setMinSize(400, 200); 
         
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
         
        //Arranging all the nodes in the grid 
        gridPane.add(hbox, 0, 0, 2, 1);
        gridPane.add(text1, 0, 1); 
        gridPane.add(textField1, 1, 1); 
        gridPane.add(button1, 1, 5); 
        
        
        //Creating a scene object 
        this.scene = new Scene(gridPane);
	
	}
}
