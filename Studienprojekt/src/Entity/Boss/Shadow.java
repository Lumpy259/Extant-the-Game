package Entity.Boss;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Animation;
import Entity.Entity;
import Entity.HitBox;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Entity.Projectile.Projectile;
import Entity.Projectile.ShadowAttack1;
import GameState.BossState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import Manager.Rand;

public class Shadow extends Boss {

	public static String NAME = "Schatten";
	public static String PATH = "Resources/Boss/Shadow.png";
	public static String PATH2 = "";
	public static int WIDTH = 64;
	public static int HEIGHT = 64;
	public static int BossXP = 50;
	int count = 0;
	static int damage = 10;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(11,-8,44,52);
	static int attackrange = 100;
	
	Content attack1 = new Content("Attack1", "Resources/Animations/shadowattack1.png", 10, 10, new Point(1,1));
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bronze_chunk.class, 100);
		put(Bronze.class, 30);
	}};

	public Shadow(double x, double y) {
		super(NAME, PATH, PATH2, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.BOSS_SHADOW, Content.BOSS_SHADOW, attackrange);
		super.BossXP = this.BossXP;
		super.loot = this.loot;
	}
	
	public void update(InputHandler input, ArrayList<Entity> entities) {
		double speed = 0.5;
		
		
		
		animation.update();
		//if (count%10 == 0)
			//BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, count));
		if (count%100 == 0 || count%500 == 0)	{
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 0));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 45));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 90));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 135));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 180));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 225));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 270));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 315));
		}
		if (count%100 == 50 || count%500 == 0)	{
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 22));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 67));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 112));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 157));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 202));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 247));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 292));
			BossState.addEntity.add(new ShadowAttack1(Game.WIDTH/2-5, Game.HEIGHT/2-32, speed, 337));
		}
		count++;
		//if (count%360==0) count = 0;
	}
	
	public void draw(Graphics2D g) {
		/*g.drawImage(attack1.getImage(), Game.WIDTH/2-5, Game.HEIGHT/2-70, null);
		g.drawImage(attack1.getImage(), Game.WIDTH/2-5, Game.HEIGHT/2+5, null);
		g.drawImage(attack1.getImage(), Game.WIDTH/2-40, Game.HEIGHT/2-32, null);
		g.drawImage(attack1.getImage(), Game.WIDTH/2+30, Game.HEIGHT/2-32, null);
		
		g.drawImage(attack1.getImage(), Game.WIDTH/2-30, Game.HEIGHT/2-60, null);	
		g.drawImage(attack1.getImage(), Game.WIDTH/2+20, Game.HEIGHT/2-60, null);
		g.drawImage(attack1.getImage(), Game.WIDTH/2-30, Game.HEIGHT/2-8, null);
		g.drawImage(attack1.getImage(), Game.WIDTH/2+20, Game.HEIGHT/2-8, null);
		*/
		
		super.draw(g);
	}
}
