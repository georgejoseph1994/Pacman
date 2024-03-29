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

public class HostJoinScene extends GameScene{
	public HostJoinScene(Stage stage) {
		super(stage);
		HBox hbox = this.generateHeaderHBox();
		//creating label for Number of Players
		Text text1 = new Text("Please select the number of players");
		//Creating Button
		Button button1 = new Button("2 Players");
		button1.setMaxWidth(Double.MAX_VALUE);
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("2 players selected");
				PlayerClient.setMaxCount(2);
			}
		});
		//Creating Button
		Button button2 = new Button("3 Players");
		button2.setMaxWidth(Double.MAX_VALUE);
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("3 players selected");
				PlayerClient.setMaxCount(3);
			}
		});
		//Creating Button
		Button button3 = new Button("4 Players");
		button3.setMaxWidth(Double.MAX_VALUE);
		button3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("4 players selected");
				PlayerClient.setMaxCount(4);
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
		gridPane.add(button1, 0, 4, 2, 1);
		gridPane.add(button2, 0, 5, 2, 1);
		gridPane.add(button3, 0, 6, 2, 1);
		GridPane.setHalignment(text1, HPos.CENTER);
		GridPane.setFillWidth(button1, true);
		GridPane.setFillHeight(button1, true);
		GridPane.setFillWidth(button2, true);
		GridPane.setFillHeight(button2, true);
		GridPane.setFillWidth(button3, true);
		GridPane.setFillHeight(button3, true);


		//Creating a scene object
		this.scene = new Scene(gridPane);
	}
}
