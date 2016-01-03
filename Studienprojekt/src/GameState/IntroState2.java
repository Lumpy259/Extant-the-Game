package GameState;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import Entity.Entity;
import Entity.Player;
import Entity.Monster.Baby_dragon;
import Entity.Monster.Blob_blue;
import Entity.Monster.Blob_gold;
import Entity.Monster.Blob_green;
import Entity.Monster.Blob_red;
import Entity.Monster.Monster;
import Entity.Monster.Spider;
import Entity.NPC.NPC;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Music;
import Manager.Rand;

public class IntroState2 extends GameState {

	public ArrayList<Integer> name = new ArrayList<>();
	private Content planet, space1, spaceship;
	private Content wakeup1, wakeup2, wakeup3;
	private Content boss1, boss2, boss3, boss4;
	private Content explosion;
	private int time;
	private int wait;
	private int introNumber = 0;
	
	public IntroState2(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		time = 0;
		wait = 100;
		
		planet = new Content("Planet", "Resources/Intro2/Planet.png", 384, 384, new Point(1,1));
		space1 = new Content("Hintergrund", "Resources/Intro2/Space1.png", 512, 384, new Point(1,1));
		spaceship = new Content("Raumschiff", "Resources/Intro2/Spaceship.png", 512, 384, new Point(1,1));
		
		wakeup1 = new Content("Aufwachen1", "Resources/Intro2/wakeup1.png", 512, 384, new Point(1,1));
		wakeup2 = new Content("Aufwachen2", "Resources/Intro2/wakeup2.png", 512, 384, new Point(1,1));
		wakeup3 = new Content("Aufwachen3", "Resources/Intro2/wakeup3.png", 512, 384, new Point(1,1));
		
		boss1 = new Content("Boss1", "Resources/Intro2/boss1.png", 200, 200, new Point(1,1));
		boss2 = new Content("Boss2", "Resources/Intro2/boss2.png", 200, 200, new Point(1,1));
		boss3 = new Content("Boss3", "Resources/Intro2/boss3.png", 200, 200, new Point(1,1));
		boss4 = new Content("Boss4", "Resources/Intro2/boss4.png", 200, 200, new Point(1,1));
		
		explosion = new Content("Explosion", "Resources/Intro2/explosion.png", 350, 350, new Point(1,1));
	}

	public void update(InputHandler input) {
		time++;
	}

	
	public void draw(Graphics2D g) {
		
		
		switch(introNumber) {
			case 0: // fertig (michi synchro fehlt)
				g.setColor(Color.black);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
				g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
				
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(time>750 ? (time-750<300 ? 1 - (time-750) / 300.0 : 0) : 1)));
			    
				g.drawImage(space1.getImage(), 1-time, 1-time, 512+time*4, 384+time*4, null);
				g.drawImage(planet.getImage(), Game.WIDTH/2-50-(int)(time/4), 0, 25+(int)time/2, 25+(int)time/2, null);
				g.drawImage(spaceship.getImage(), (time>750 ? time%3*3 : 0), 0, null);
				
				if (time > 1100) {
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
					time = 0;
					introNumber++;
				}
			break;
			
			case 1: // Kreisen um Planet
				g.setColor(Color.black);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
				g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
				
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(time>1400 ? (time-1400<300 ? 1 - (time-1400) / 300.0 : 0) : 1)));
				
				if (time <= 1200) {
					Game.playerX = 260*32 + (15*32) * Math.cos(Math.toRadians((1150+time)/4.0));
					Game.playerY = 260*32 + (15*32) * Math.sin(Math.toRadians((1150+time)/4.0));
				}
				Collections.sort(PlayState.entities.get(Game.playerZ));
				
				PlayState.world.draw(g);
				
				for (Entity e : PlayState.entities.get(Game.playerZ))
					if (!(e instanceof Monster))
						e.draw(g);
				
				if (time > 1200) 
					g.drawImage(explosion.getImage(), 281-(int)((time-1200)*5/2+50), 219-(int)((time-1200)*5/2+50), (time-1200)*5+50, (time-1200)*5+50, null);
				
				if (time > 1800) {
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
					time = 0;
					introNumber++;
				}
			break;
			
			
			case 2:	// Monster kreisen (Monster synchro fehlt (michi?))
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
				
				double speed = (time < 300 ? 21 - time/15.0 + 21 : 0);
				int radius = (int)(time < 300 ? time*0.4 : 120);
				
				

				g.drawImage(boss1.getImage(), (int)(156 + (radius + (time > 400 ? 5*(time-400) : 0)) * Math.cos(Math.toRadians(time * speed))), (int)(92 + (radius + (time > 400 ? 5*(time-400) : 0)) * Math.sin(Math.toRadians(time * speed))), null);
				g.drawImage(boss2.getImage(), (int)(156 + (radius + (time > 500 ? 5*(time-500) : 0)) * Math.cos(Math.toRadians(90+time * speed))), (int)(92 + (radius + (time > 500 ? 5*(time-500) : 0)) * Math.sin(Math.toRadians(90+time * speed))), null);
				g.drawImage(boss3.getImage(), (int)(156 + (radius + (time > 600 ? 5*(time-600) : 0)) * Math.cos(Math.toRadians(180+time * speed))), (int)(92 + (radius + (time > 600 ? 5*(time-600) : 0)) * Math.sin(Math.toRadians(180+time * speed))), null);
				g.drawImage(boss4.getImage(), (int)(156 + (radius + (time > 700 ? 5*(time-700) : 0)) * Math.cos(Math.toRadians(270+time * speed))), (int)(92 + (radius + (time > 700 ? 5*(time-700) : 0)) * Math.sin(Math.toRadians(270+time * speed))), null);
			
				g.setColor(Color.WHITE);
				if (time > 300 && time < 400) g.drawString("Endlich frei!", 330, 150);
				if (time > 400 && time < 500) g.drawString("MUAHAHHAHAHAH", 210, 270);
				if (time > 500 && time < 600) g.drawString("Das gibt Rache", 90, 150);
				if (time > 600 && time < 700) g.drawString("Ich bin böse!", 210, 35);
				
				if (time > 800) {
					time = 0;
					introNumber++;
				}
			break;
			
			case 3:
				Game.playerX = (time < 200 ? 238 : (time < 400 ? 262 : 238)) * 32;
				Game.playerY = (time < 200 ? 238 : (time < 400 ? 262 : 262)) * 32;
				
				Collections.sort(PlayState.entities.get(Game.playerZ));
				PlayState.world.draw(g);
			
				
				for (Entity e : PlayState.entities.get(Game.playerZ))
					if (!(e instanceof Monster))
						e.draw(g);
			
				if (time < 200) {
					if (time < 50) g.drawImage(explosion.getImage(), 97-(int)(time/2+50), 330-(int)(time/2+50), time+50, time+50, null);
					if (time > 50) new Blob_green((int)Game.playerX - 200, (int)Game.playerY + 90).draw(g);
					
					if (time < 70 && time > 20) g.drawImage(explosion.getImage(), 147-(int)((time-20)/2+50), 90-(int)((time-20)/2+50), (time-20)+50, (time-20)+50, null);
					if (time > 70) new Blob_gold((int)Game.playerX - 150, (int)Game.playerY - 150).draw(g);
					 
					if (time < 90 && time > 40) g.drawImage(explosion.getImage(), 287-(int)((time-40)/2+50), 310-(int)((time-40)/2+50), (time-40)+50, (time-40)+50, null);
					if (time > 90) new Blob_blue((int)Game.playerX - 10, (int)Game.playerY + 70).draw(g);
					
					if (time < 110 && time > 60) g.drawImage(explosion.getImage(), 441-(int)((time-60)/2+50), 140-(int)((time-60)/2+50), (time-60)+50, (time-60)+50, null);
					if (time > 110) new Blob_green((int)Game.playerX + 145, (int)Game.playerY - 100).draw(g);
					
					if (time < 130 && time > 80) g.drawImage(explosion.getImage(), 321-(int)((time-80)/2+50), 210-(int)((time-80)/2+50), (time-80)+50, (time-80)+50, null);
					if (time > 130) new Blob_red((int)Game.playerX + 25, (int)Game.playerY - 30).draw(g);
				}
				else if (time < 400) {
					new Baby_dragon((int)Game.playerX - 200, (int)Game.playerY + 90).draw(g);
					new Baby_dragon((int)Game.playerX - 150, (int)Game.playerY - 150).draw(g);
					new Baby_dragon((int)Game.playerX - 10, (int)Game.playerY + 70).draw(g);
					new Baby_dragon((int)Game.playerX + 145, (int)Game.playerY - 50).draw(g);
					new Baby_dragon((int)Game.playerX + 25, (int)Game.playerY - 30).draw(g);
				}
				else if (time < 600) {
					new Spider((int)Game.playerX - 200, (int)Game.playerY + 30).draw(g);
					new Spider((int)Game.playerX - 150, (int)Game.playerY - 150).draw(g);
					new Spider((int)Game.playerX - 10, (int)Game.playerY + 130).draw(g);
					new Spider((int)Game.playerX + 145, (int)Game.playerY - 50).draw(g);
					new Spider((int)Game.playerX + 25, (int)Game.playerY - 30).draw(g);
				}
				if (time > 600) {
					time = 0;
					introNumber++;
				}
			break;
				
			
			case 4:	// Intro Teil III  - Augen auf und zu (Fee synchro fehlt (myhanh?))
				g.setColor(Color.black);
				
				double open = 0;

				if ((int)(time/100)%2 == 0)
					open =  (1 - (time%200/100.0));
				else
					open =  ((time-100)%200/100.0);
				
				if (time < 200)
					g.drawImage(wakeup1.getImage(), 0, 0, null);
				else if (time < 400)
					g.drawImage(wakeup2.getImage(), 0, 0, null);
				else
					g.drawImage(wakeup3.getImage(), 0, 0, null);
				
				
				if (time < 500) {
					g.fillRect(0, 0, Game.WIDTH, (int)(Game.HEIGHT/2 * open));
					g.fillRect(0, Game.HEIGHT/2 + (Game.HEIGHT/2-(int)(Game.HEIGHT/2 * open)), Game.WIDTH, (int)(Game.HEIGHT/2 * open));
					
					if (time%200 == 0 && wait > 0) {wait--; time--;}
					else if (time%100 == 0 && time%200 != 0 && wait > 75) {wait--; time--;}
					else wait = 100;
				}
				
				if (time > 700) {
					time = 0;
					introNumber++;
				}
			break;
			
			case 5:
				gsm.setState(gsm.PLAY);
			break;
		
			default:
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
				g.setFont(new Font("Courier New", Font.PLAIN, 11));
				g.setColor(Color.black);
				g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
				g.setColor(Color.white);
				g.drawString("You broke the Game...", 150, 150);
				g.drawString("WOW!!! such broken, very fail, much bugs", 100, 200);
		}
		
		
		
		
		
		
		
				
		
	}

	public void handleInput(InputHandler input) {	
	
	}
}
