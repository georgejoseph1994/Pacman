package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WallElement extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WallElement(int x, int y) {
		super(y*32, x*32, 32, 32);
	}
	
	public void render(GraphicsContext g) {
		g.setFill(Color.BLUE);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
