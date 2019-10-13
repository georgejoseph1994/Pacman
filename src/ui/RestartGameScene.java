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
import main.PlayerClient;

public class RestartGameScene extends GameScene{

	public RestartGameScene(Stage stage, boolean hasWon) {
		super(stage);

		HBox hbox = this.generateHeaderHBox();
		//creating label IP
		Text text1 = new Text("Game Over. You Won!!");


		//creating label Player Name
		Text text2 = new Text("Do you want to play again?");



		//Creating Button
		Button button1 = new Button("Yes");
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		Button button2 = new Button("No");
		button2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
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
		gridPane.add(hbox, 0, 0, 3, 1);
		gridPane.add(text1, 0, 1, 2, 1);
		gridPane.add(text2, 0, 2, 2, 1);
		gridPane.add(button1, 1, 5);
		gridPane.add(button2, 2, 5);


		//Creating a scene object
		this.scene = new Scene(gridPane);

	}
}