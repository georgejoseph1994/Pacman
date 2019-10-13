package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.PlayerClient;

public class PlayScene extends GameScene {
	private static final String[] playerColors = { "BLUE", "RED", "PINK", "ORANGE" };
	public PlayScene(Stage stage, String[][] map) {
		super(stage);
		updateGameScene(map);
	}
	
	
	public void updateGameScene(String[][] map) {
		System.out.println("Map Changed");
		HBox hbox = this.generateHeaderHBox();
		Group root = new Group();
		final Canvas canvas = new Canvas(610,670);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		 
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,608,660);
		if(map!=null) {
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					if(map[i][j].charAt(0)=='W') {
						WallElement wall = new WallElement(i,j);
						wall.render(gc);
					}else if(map[i][j].charAt(0)=='M') {
						MonsterElement monster = new MonsterElement(i,j);
						monster.render(gc);
					}else if(map[i][j].charAt(0)=='A') {
						PlayerElement player = new PlayerElement(i, j, playerColors[Character.getNumericValue(map[i][j].charAt(1))-1]);
						player.render(gc);
					}
				}
			}
		}
		 
		root.getChildren().add(canvas);
		
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
        hbox.setAlignment(Pos.CENTER);
        gridPane.add(root, 0, 1);
        Label label = new Label("Use arrow keys to move your player");
        gridPane.add(label, 0, 2);
        GridPane.setHalignment(label, HPos.CENTER);

        this.scene = new Scene(gridPane);
        this.scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
    		public void handle(KeyEvent event) {
    			String direction = event.getCode().toString();
    			PlayerClient.changePlayerDirection(direction);
    		}
    	});
	}
	
}
