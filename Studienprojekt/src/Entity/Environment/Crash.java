package Entity.Environment;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Animation;
import Entity.Entity;
import Entity.HitBox;
import Entity.Player;
import Entity.Item.Wood;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import World.World;

public class Crash extends Environment{
	
	
	public static String NAME = "Absturzstelle";
	public static String PATH = "Resources/Animations/Crashanimation.png";
	public static int WIDTH = 256;
	public static int HEIGHT = 256;
	static int maxLife = 1000000;
	private Animation crashAnimation;
			
	static HitBox hitBox = new HitBox(109,-75,48,88); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{

	}};
	
	public Crash(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_CRASH);
		super.environmentXP = this.environmentXP;
		crashAnimation = new Animation(50, 3);
		super.loot = this.loot;
			}
	
	public void update(InputHandler input, ArrayList<Entity> entities) {
		if(this.currentLife <= 0) {
			Player.xp += this.environmentXP;
			PlayState.removeEntity.add(this);
		}
		crashAnimation.update();
		super.update(input, entities);
	}
		
	public void draw(Graphics2D g) {
		if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
		//g.drawImage(image.getImage(0, 0), (int)(this.x - (Game.playerX - 160)),(int) (this.y - (Game.playerY - 160)), null);
			g.drawImage(Content.ENV_CRASH.getImage(crashAnimation.getFrame(), 0),(int)(this.x - (Game.playerX - (World.numTilesWidth-2)/2*32)),(int) (this.y - (Game.playerY - (World.numTilesHeight-2)/2*32)),null);
			//g.fillRect((int)(this.x - (Game.playerX - 160))+hitBox.x, (int) ((this.y - (Game.playerY - 160))-hitBox.y), hitBox.width, hitBox.height);
		}
	}

}
