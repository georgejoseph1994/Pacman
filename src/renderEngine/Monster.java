package renderEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Monster(int x, int y) {
		setBounds(x*32, y*32, 32, 32);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

}
