package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.PlayerClient;

public class ChooseStartingScene extends GameScene{
	public ChooseStartingScene (Stage stage, boolean[] availableLocation) {
		super(stage);
		GameScene waitingScene = new WaitingScene(stage);
		HBox hbox = this.generateHeaderHBox();
		//creating label for Number of Players
		Text text1 = new Text("Please choose starting position");
		//Creating Button
		Button button1 = new Button("Top Left");
		button1.setMaxWidth(Double.MAX_VALUE);
		button1.setMinWidth(150);
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Top Left selected");
				if(PlayerClient.setLocation(0)==true)
					stage.setScene(waitingScene.scene);
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		if(availableLocation[0]!=true) {
			button1.setDisable(false);
		}
		//Creating Button
		Button button2 = new Button("Top Right");
		button2.setMaxWidth(Double.MAX_VALUE);
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Top Right selected");
				if(PlayerClient.setLocation(1)==true)
					stage.setScene(waitingScene.scene);
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		if(availableLocation[1]!=true) {
			button2.setDisable(false);
		}
		//Creating Button
		Button button3 = new Button("Bottom Left");
		button3.setMaxWidth(Double.MAX_VALUE);
		button3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Bottom Left selected");
				if(PlayerClient.setLocation(2)==true)
					stage.setScene(waitingScene.scene);
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		if(availableLocation[2]!=true) {
			button3.setDisable(false);
		}
		
		//Creating Button
		Button button4 = new Button("Bottom Right");
		button4.setMaxWidth(Double.MAX_VALUE);
		button4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Bottom Right selected");
				if(PlayerClient.setLocation(3)==true)
					stage.setScene(waitingScene.scene);
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		if(availableLocation[3]!=true) {
			button4.setDisable(false);
		}

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
		gridPane.add(text1, 0, 1, 2, 1);
		gridPane.add(button1, 0, 4,1,1); 
		gridPane.add(button2, 1, 4,1,1); 
		gridPane.add(button3, 0, 5,1,1);
		gridPane.add(button4, 1, 5,1,1);
		GridPane.setHalignment(text1, HPos.CENTER);
		GridPane.setFillWidth(button1, true);
		GridPane.setFillHeight(button1, true);
		GridPane.setFillWidth(button2, true);
		GridPane.setFillHeight(button2, true);
		GridPane.setFillWidth(button3, true);
		GridPane.setFillHeight(button3, true);
		GridPane.setFillWidth(button4, true);
		GridPane.setFillHeight(button4, true);

		//Creating a scene object 
		this.scene = new Scene(gridPane);
	}
}
