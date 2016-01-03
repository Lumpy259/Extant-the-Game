package Manager;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

import Entity.Entity;
import Entity.Item.Torch;
import Main.Game;
import World.World;

public class Light {

	Content light160;
	Content light80;
	Content light160_2;
	Content light160_3;
	private BufferedImage lightMap;
	private int[] resultPixels;
	private float alpha;
	Graphics2D g;
	private ArrayList<Entity> lights = new ArrayList<>();
	
	public Light() {
		resultPixels = new int[Game.WIDTH*Game.HEIGHT];
		alpha = 1.0f;
		light160 = new Content("Light160", "Resources/Light/light160.png", 160, 160, new Point(1,1));
		light80 = new Content("Light80", "Resources/Light/light80.png", 80, 80, new Point(1,1));
		light160_2 = new Content("light160_2", "Resources/Light/light160_2.png", 160, 160, new Point(1,1));
		light160_3 = new Content("light160_3", "Resources/Light/light160_3.png", 160, 160, new Point(1,1));
	}
	
	public void addLight(Entity e) {
		lights.add(e);
	}
	
	public void update() {
		lightMap = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) lightMap.getGraphics();
		g.setColor(new Color(0,0,0,50));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		for (Entity e : lights)
			if(e.x > Game.playerX - World.numTilesWidth/2 * 32 && e.x < Game.playerX + World.numTilesWidth/2 * 32 && e.y > Game.playerY - World.numTilesHeight/2 * 32 && e.y < Game.playerY + World.numTilesHeight/2 * 32)	
				g.drawImage(light160_2.getImage(), (int)(e.x - (Game.playerX - (World.numTilesWidth-2)/2*32)-64),(int) (e.y - (Game.playerY - (World.numTilesHeight-2)/2*32)-64), null);
		
		//if (lights.size() > 0)
			//multiplyLight();
		g.drawImage(light160.getImage(), Game.WIDTH/2-80, Game.HEIGHT/2-80, null);
		lights.clear();
		/*	
		g.drawImage(light160_2.getImage(), Game.WIDTH/2-80, Game.HEIGHT/2-80, null);
		g.drawImage(light160_3.getImage(), 40, 40, null);
		g.drawImage(light160.getImage(), 75, 65, null);
		g.drawImage(light160.getImage(), 333, 33, null);
		g.drawImage(light160.getImage(), 476, 88, null);
		g.drawImage(light160.getImage(), 358, 285, null);
		g.drawImage(light160.getImage(), 455, 67, null);
		g.drawImage(light80.getImage(), 400, 250, null);
		*/
		multiplyLight();
	}
	
	public void multiplyLight() {
		long start = System.nanoTime();
		int[] imagePixels = new int[Game.WIDTH*Game.HEIGHT];
	    int[] lightPixels = new int[Game.WIDTH*Game.HEIGHT];
	      
	    PixelGrabber pixelGrabber1 = new PixelGrabber(Game.image, 0, 0, Game.WIDTH, Game.HEIGHT, imagePixels, 0, Game.WIDTH);
	    PixelGrabber pixelGrabber2 = new PixelGrabber(lightMap, 0, 0, Game.WIDTH, Game.HEIGHT, lightPixels, 0, Game.WIDTH);
	    
	    try {
	    	pixelGrabber1.grabPixels();
	    	pixelGrabber2.grabPixels();
	    } catch (Exception e) { e.printStackTrace(); }
	      
	    int imagePixel[] = new int[4];
	    int lightPixel[] = new int[4];
	    int resultPixel[] = new int[4];
	     
	    for (int i = 0; i < imagePixels.length; i++) {
	    	imagePixel[0] = (imagePixels[i] >> 16) & 0xFF;
	        imagePixel[1] = (imagePixels[i] >> 8) & 0xFF;
	        imagePixel[2] = (imagePixels[i]) & 0xFF;
	        imagePixel[3] = (imagePixels[i] >> 24) & 0xFF;
	            	
	        lightPixel[0] = (lightPixels[i] >> 16) & 0xFF;
	        lightPixel[1] = (lightPixels[i] >> 8) & 0xFF;
	        lightPixel[2] = (lightPixels[i]) & 0xFF;
	        lightPixel[3] = (lightPixels[i] >> 24) & 0xFF;
	            	
	        resultPixel[0] = (imagePixel[0] * lightPixel[0]) >> 8;
	        resultPixel[1] = (imagePixel[1] * lightPixel[1]) >> 8;
	        resultPixel[2] = (imagePixel[2] * lightPixel[2]) >> 8;
	        resultPixel[3] = Math.min(255, imagePixel[3] + lightPixel[3]);
	            	
	        resultPixels[i] = 	((int) (lightPixel[3] + (resultPixel[3] - lightPixel[3]) * alpha) & 0xFF) << 24 |
	                        	((int) (lightPixel[0] + (resultPixel[0] - lightPixel[0]) * alpha) & 0xFF) << 16 |
	                        	((int) (lightPixel[1] + (resultPixel[1] - lightPixel[1]) * alpha) & 0xFF) <<  8 |
	                        	(int) (lightPixel[2] + (resultPixel[2] - lightPixel[2]) * alpha) & 0xFF;
	    }
	    
	    MemoryImageSource mis = new MemoryImageSource(Game.WIDTH, Game.HEIGHT, resultPixels, 0, Game.WIDTH);
	    Image newImage = Toolkit.getDefaultToolkit().createImage(mis);
	    Game.g.drawImage(newImage, 0, 0, null);
	}
}
