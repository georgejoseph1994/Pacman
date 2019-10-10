package ui;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MonsterElement extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MonsterElement(int x, int y) {
		super(y*32, x*32, 32, 32);
	}
	
	public void render(GraphicsContext g) {
//		g.setFill(Color.RED);
//		g.fillRect(getX(), getY(), getWidth(), getHeight());
//		g.setFill(Color.BLACK);
//		g.fillRect(getX(), getY(), getWidth(), getHeight());

//        g.setGlobalBlendMode(BlendMode.SCREEN);
		File file = new File("assets/monster.jpg");
		Image monster = new Image(file.toURI().toString(), 32, 32, true, true);
		g.drawImage(monster, getX(), getY());
	}

}
