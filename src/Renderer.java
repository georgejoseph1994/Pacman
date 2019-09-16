import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import renderEngine.Monster;
import renderEngine.Player;
import renderEngine.Wall;

public class Renderer extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	
	public static final int WIDTH = 640, HEIGHT = 480;
	public static final String TITLE = "Pac-Men";
	private static final Color[] playerColors = { Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW };
	
	private Thread thread;
	
	private String[][] map;
	
	public void setMap(String[][] map) {
		System.out.println("Map Set");
		this.map = map;
	}
	
	public Renderer() {
		Dimension dimension = new Dimension(Renderer.WIDTH,Renderer.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		this.map = null;
		
	}
	
	public synchronized void start() {
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void tick() {
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Renderer.WIDTH, Renderer.HEIGHT);
		if(map!=null) {
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					if(map[i][j].charAt(0)=='W') {
						Wall wall = new Wall(i,j);
						wall.render(g);
					}else if(map[i][j].charAt(0)=='M') {
						Monster monster = new Monster(i,j);
						monster.render(g);
					}else if(map[i][j].charAt(0)=='A') {
						Player player = new Player(i, j, playerColors[Character.getNumericValue(map[i][j].charAt(1))-1]);
						player.render(g);
					}
				}
			}
		}
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		requestFocus();
		int fps = 0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 60.0;
		double delta = 0;
		double ns = 1000000000 / targetTick;
		
		while (isRunning) {
			long now = System.nanoTime();
			delta+= ( now-lastTime ) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}

			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println(fps);
				fps = 0;
				timer+=1000;
			}
		}
		
		stop();
		
	}



}
