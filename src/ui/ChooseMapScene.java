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

public class ChooseMapScene extends GameScene{
	public ChooseMapScene(Stage stage) {
		super(stage);
		GameScene hostJoinScene = new HostJoinScene(stage);
		HBox hbox = this.generateHeaderHBox();
		//creating label for choosing map
		Text text1 = new Text("You are the host of the game");
		Text text2 = new Text("Please select a map");
		//Creating Button
		Button button1 = new Button("Map 1");
		button1.setMaxWidth(Double.MAX_VALUE);
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Map 1 selected");
				PlayerClient.setMap(1);
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		//Creating Button
		Button button2 = new Button("Map 2");
		button2.setMaxWidth(Double.MAX_VALUE);
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Map 2 selected");
				PlayerClient.setMap(2);
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
		gridPane.add(text1, 0, 1, 2, 1);
		gridPane.add(text2, 0, 2, 2, 1);
		gridPane.add(button1, 0, 3, 2, 1);
		gridPane.add(button2, 0, 4, 2, 1);
		GridPane.setHalignment(text1, HPos.CENTER);
		GridPane.setHalignment(text2, HPos.CENTER);
		GridPane.setFillWidth(button1, true);
		GridPane.setFillHeight(button1, true);
		GridPane.setFillWidth(button2, true);
		GridPane.setFillHeight(button2, true);


		//Creating a scene object
		this.scene = new Scene(gridPane);
	}
}
