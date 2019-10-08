package ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WaitingScene extends GameScene{

	public WaitingScene(Stage stage) {
		super(stage);
		HBox hbox = this.generateHeaderHBox();
		//creating label for Number of Players
		Text text1 = new Text("Waiting for other players to join");
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
		GridPane.setHalignment(text1, HPos.CENTER);
		this.scene = new Scene(gridPane);
	}
}
