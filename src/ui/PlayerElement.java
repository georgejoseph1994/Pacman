package ui;

import java.io.File;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerElement extends Rectangle {

	/**
	 * 
	 */
	
	private String color;
	private static final long serialVersionUID = 1L;

	public PlayerElement(int x, int y, String color) {
		super(y*32, x*32, 32, 32);
		this.color = color;
	}
	
	public void render(GraphicsContext g) {
//		g.setFill(this.color);
//		g.fillRect(getX(), getY(), getWidth(), getHeight());
		File file; 
		if(color == "BLUE")
			file = new File("assets/blue.gif");
		else if(color=="RED")
			file = new File("assets/red.gif");
		else if(color=="PINK")
			file = new File("assets/pink.gif");
		else 
			file = new File("assets/orange.gif");
		Image monster = new Image(file.toURI().toString(), 32, 32, true, true);
		g.drawImage(monster, getX(), getY());
	}
}
