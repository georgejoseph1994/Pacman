package ui;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameScene {
public Stage rootStage;
public Scene scene;
public GameScene(Stage stage) {
	this.rootStage = stage;
}
public HBox generateHeaderHBox() {
	//create header image
	File file = new File("assets/pacman.jpg");
    Image image = new Image(file.toURI().toString());
    ImageView iv1 = new ImageView();
    iv1.setImage(image);
    iv1.setFitWidth(300);
    iv1.setPreserveRatio(true);
    iv1.setSmooth(true);
    iv1.setCache(true);
    HBox hbox = new HBox(iv1);
	return hbox;
} 
}
