package Manager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import GameState.PlayState;
import Main.Game;

public class Weather {

	public static long timeNow;
	int transparency = 0;
	Content rain;
	boolean soundNight = true;
	boolean soundDay = false;
	
	public Weather() {
		rain = new Content("Rain", "Resources/Weather/rain.png", 512, 384, new Point(2,1));
	}
	
	public void updateDayNight() {
		
		if (timeNow <= 0.6*Game.DAY) {
			transparency = 0;
			if (soundDay)
				Music.getHahnSound();
			
			soundNight = true;
			soundDay = false;
		}
		else if (timeNow >  0.6*Game.DAY && timeNow <= 0.7*Game.DAY)
			transparency = (int) (245.0 / (0.1*Game.DAY) * (timeNow-0.6*Game.DAY));
		else if (timeNow >= 0.9*Game.DAY)
			transparency = (int) (245.0 / (0.1*Game.DAY) * (Game.DAY-timeNow));
		else {
			transparency = 245;
			if (soundNight)
				Music.getWolfSound();
				
			soundNight = false;
			soundDay = true;
			
		}
	}
	
	public void update() {
		timeNow = Game.TIME % Game.DAY;
		rain.update();
		updateDayNight();
	}
	
	
	public void draw(Graphics2D g) {
	
		//g.setColor(new Color(0,0,0,10));
		//g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		//g.drawImage(rain.getImage(0, rain.animation.getFrame()), 0, 0, null);
		
		if (transparency > 0 && Game.playerZ == 0) {
			RadialGradientPaint p = new RadialGradientPaint(new Point(Game.WIDTH/2, Game.HEIGHT/2), 80, new float[]{0.0f, 0.55f, 0.75f, 1.0f}, new Color[]{new Color(0,0,0,0), new Color(0,0,0,(transparency-90 > 0 ? (transparency-90) : 0)), new Color(0,0,0,(transparency-45 > 0 ? (transparency-45) : 0)), new Color(0,0,0,transparency)});
			g.setPaint(p);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setPaint(null);	
		}
	}
}
