package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.io.IOException;







import Main.Game;

public class MainMenue {
	
	private Content hud1, hud2, hud3, hudtest, hudtest2, logo, hud1neu;
	private int state;
	private GameStateManager gsm;
	private int start;
	private Music music;
	private InputHandler input;
	Graphics g;
	boolean laden = false;
	int count= 0;
	int transparency = 0;
	int nightTime = 0;
	
	public MainMenue(GameStateManager gsm){
		this.gsm = gsm;
		hud1 = new Content("MainMenu", "Resources/Menu/MainMenu1.png", 130, 89, new Point(1,1));
		hud2 = new Content("MainMenu", "Resources/Menu/MainMenu2.png", 130, 89, new Point(1,1));
		hud3 = new Content("MainMenu", "Resources/Menu/MainMenu3.png", 130, 89, new Point(1,1));
		hudtest = new Content("MainMenu", "Resources/Menu/backgroundStartMenu.png", 1152, 384, new Point(1,1));
		hudtest2 = new Content("MainMenu", "Resources/Menu/backgroundStartMenu2.png", 1152, 384, new Point(1,1));
		logo = new Content("MainMenu", "Resources/Menu/extant.png", 295, 130, new Point(1,1));

		Music.getMenuMusic();
		state = 3;
		start = 1;		
		
	}
	
	public void update(InputHandler input) {
		this.input=input;
		if(!Music.getClip().isActive()) Music.getMenuMusic();
		if (input.isPressed(input.DOWN)){			
			state--;
			if(state == 0) state = 3;
			
			Music.getSelectSound();
			
		}
		else if (input.isPressed(input.UP)){			
			state++;
			if(state == 4) state = 1;
			Music.getSelectSound();
		}		
		if (state == 3 && input.isPressed(input.ENTER)){
			//Spiel starten	
			Music.stopMenuMusic();
			Music.getGameMusic();
			gsm.setState(gsm.LOADGAME);
							
		}
		if (state == 2 && input.isPressed(input.ENTER)){
			laden = true;

			
			//Spiel laden
		}
		if (state == 1 && input.isPressed(input.ENTER)){
			gsm.setState(gsm.OPTIONSMENU);
			//Optionen
		}
		
	}
	
	public void draw(Graphics2D g)  {		
		if(state == 3){
			g.drawImage(hud1.getImage(0,0), 0, 0, null);
			laden = false;
		}
		if(state == 2)		g.drawImage(hud2.getImage(0,0), 0, 0, null);
		if(state == 1){
			g.drawImage(hud3.getImage(0,0), 0, 0, null);
			laden = false;
		}
		if(laden == true){
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setFont(new Font("Courier New", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("Laden zurzeit nicht moeglich!", 80, 330);			
		}
		
		count++;
		if (count%2 == 0 && transparency < 240 && nightTime < 500) {
			transparency++;
		}
		if (transparency == 240) {
			nightTime++;
		}
		if (count%2 == 0 && transparency > 0 && nightTime >= 500) {
			transparency--;
		}
		if (transparency == 0 && nightTime >= 500) {
			nightTime = 0;
		}
		if (count >= 1151) {
			count = 0;
		}
		
		g.drawImage((transparency < 240 ? hudtest.getImage() : hudtest2.getImage()), -count, 0, null);
		g.drawImage((transparency < 240 ? hudtest.getImage() : hudtest2.getImage()), 1151-count, 0, null);	
		
		
		g.setColor(new Color(0,0,0, transparency));
		if (transparency < 240)
			g.fillRect(0, 0, 512, 384);
		
		g.drawImage(logo.getImage(0,0), 135, 0, null);
		
		if(state == 3){
			g.drawImage(hud1.getImage(0,0), 10, 200, null);
			laden = false;
		}
		if(state == 2)		g.drawImage(hud2.getImage(0,0), 10, 200, null);
		if(state == 1){
			g.drawImage(hud3.getImage(0,0), 10, 200, null);
			laden = false;
		}
		if(laden == true){
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setFont(new Font("Courier New", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("Laden zurzeit nicht moeglich!", 80, 330);			
		}
		

	}
	
	
	

}
