package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.PlayerClient;

public class ConnectScene extends GameScene{

	public ConnectScene(Stage stage) {
		super(stage);

		HBox hbox = this.generateHeaderHBox();
		//creating label IP
		Text text1 = new Text("Server IP");


		//Creating Text Filed for IP
		TextField textField1 = new TextField();

		//creating label Player Name
		Text text2 = new Text("Player Name");


		//Creating Text Filed for Player Name
		TextField textField2 = new TextField();


		//Creating Button
		Button button1 = new Button("Connect");
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(textField1.getText()!=null && textField2.getText()!=null) {
					System.out.println("Connect attempt with IP: " + textField1.getText());
					if(!PlayerClient.connectServer(textField1.getText(), textField2.getText())) {
						System.out.println("Connection Refused");
					}else {
						System.out.println("Connection Established");

					}
				}
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		
		Button button2 = new Button("View Scoreboard");
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
		gridPane.add(text1, 0, 1);
		gridPane.add(textField1, 1, 1, 2, 1);
		gridPane.add(text2, 0, 2);
		gridPane.add(textField2, 1, 2, 2, 1);
		gridPane.add(button1, 1, 5);
		gridPane.add(button2, 2, 5);

		//Creating a scene object
		this.scene = new Scene(gridPane);
	}
	
	public ConnectScene(Stage stage, boolean Error) {
		super(stage);

		HBox hbox = this.generateHeaderHBox();
		//creating label IP
		Text error = new Text("Game Room is Full. Please try after some time");
		
		Text text1 = new Text("Server IP");


		//Creating Text Filed for IP
		TextField textField1 = new TextField();

		//creating label Player Name
		Text text2 = new Text("Player Name");


		//Creating Text Filed for Player Name
		TextField textField2 = new TextField();


		//Creating Button
		Button button1 = new Button("Connect");
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(textField1.getText()!=null && textField2.getText()!=null) {
					System.out.println("Connect attempt with IP: " + textField1.getText());
					if(!PlayerClient.connectServer(textField1.getText(), textField2.getText())) {
						System.out.println("Connection Refused");
					}else {
						System.out.println("Connection Established");

					}
				}
				// Write code here to actually send the IP to server
				// and call the next stage on successful connection
			}
		});
		
		Button button2 = new Button("View Scoreboard");
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

		gridPane.add(text1, 0, 1);
		gridPane.add(textField1, 1, 1, 1, 2);
		gridPane.add(text2, 0, 2);
		gridPane.add(textField2, 1, 2, 1, 2);
		gridPane.add(button1, 1, 5);
		gridPane.add(button2, 2, 5);
		gridPane.add(error, 0, 6,3,1);

		//Creating a scene object
		this.scene = new Scene(gridPane);
	}

}