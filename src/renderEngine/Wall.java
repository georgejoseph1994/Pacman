package renderEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wall(int x, int y) {
		setBounds(y*32, x*32, 32, 32);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
