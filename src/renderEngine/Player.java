package renderEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

	/**
	 * 
	 */
	
	private Color color;
	private static final long serialVersionUID = 1L;

	public Player(int x, int y, Color color) {
		setBounds(x*32, y*32, 32, 32);
		this.color = color;
	}
	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
	}
}
