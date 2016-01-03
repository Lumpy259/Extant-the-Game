package Entity.Boss;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Animation;
import Entity.Entity;
import Entity.HitBox;
import Entity.Player;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Entity.Projectile.Projectile;
import Entity.Projectile.ShadowAttack1;
import GameState.BossState;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import Manager.Rand;

public class Blob extends Boss {

	public static String NAME = "Blob";
	public static String PATH = "Resources/Enemy/blob_green.png";
	public static String PATH2 = "";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int BossXP = 50;
	int count = 0;
	static int damage = 10;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 100;
	public boolean spawn = false;
	
	Content attack1 = new Content("Attack1", "Resources/Animations/shadowattack1.png", 10, 10, new Point(1,1));
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bronze_chunk.class, 100);
		put(Bronze.class, 30);
	}};

	public Blob(double x, double y, int scale) {
		super(NAME, PATH, PATH2, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, new HitBox(8*scale,-25*scale,16*scale,7*scale), Content.MONSTER_BLOBGREEN, Content.MONSTER_BLOBGREEN, attackrange);
		super.BossXP = this.BossXP;
		super.loot = this.loot;
		super.scale = scale;
	}
	
	public void update(InputHandler input, ArrayList<Entity> entities) {
		animation.update();
		if (bosscooldown > 0) bosscooldown -= 10;
		
		if(super.currentLife <= 0) {
			PlayState.removeEntity.add(this);
		}
		
		if (super.currentLife <= 0 && spawn == false && scale > 1) 
			for (int i=0; i<4; i++) 
				BossState.addEntity.add(new Blob(Rand.Random(50, 300), Rand.Random(50, 300), super.scale-1));
			
		
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
