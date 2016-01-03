package Main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.swing.JFrame;

import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Statistics;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 512;
	public static final int HEIGHT = 384;
	public static final int SCALE = 2;
	
	public static double playerX;
	public static double playerY;
	public static int playerZ;
	
	public static long TIME = 0;
	public static long DAY = 30*60*100;
	
	public static JFrame frame = new JFrame("Extant");
	
	public static boolean Debug = false;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	public static BufferedImage image;
	public static Graphics2D g;
	
	private GameStateManager gsm;
	public InputHandler input = new InputHandler(this);
	public static Game game;
	
	public static void main(String [] args) {
		game = new Game();
		
		initGamePanel(game);

		game.start();
	}
	
	
	public static void initGamePanel(Game game) {
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame.add(game, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setFocusable(true);
		game.requestFocus();
	}
	
	public void start() {
		running = true;
		
		new Thread(this).start();
	}
	
	public void stop() {
		running = false;
	}	
	
	public static double getremainingtime(){
		return TIME% DAY;
	}
	
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 1;
			sleep(wait);
			//try { Thread.sleep(wait); } 
			//catch(Exception e) { e.printStackTrace(); }
			
			if (gsm.getCurrentState() == GameStateManager.PLAY || gsm.getCurrentState() == GameStateManager.HOUSE) {
				TIME += targetTime;
				Statistics.playTime += targetTime;
			}
		}
	}
	
	public void sleep(long wait) {
		long start;
		long delay = wait*1000;
		while (delay-100 > 0) {
			start = System.nanoTime();
			try { Thread.sleep(1); } 
			catch(Exception e) { e.printStackTrace(); }
			delay -= (System.nanoTime() - start) / 1000;
		}
	}
	
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
		
	}
	
	private void update() {
		gsm.update(input);
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	static void invis(){
		frame.setVisible(true);
	}

	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
}
