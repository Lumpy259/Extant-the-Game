package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Manager.Content;


public class Mountaincave extends Environment{
	
	public static String NAME = "Mountaincave";
	public static String PATH = "Resources/Environment/mountaincave.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 64;
	static int maxLife = 10000000;
	public static int environmentXP = 30;
	public int boss;
	static HitBox hitBox = new HitBox(0,0, 32, 64); 

	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{

	}};
	
	public Mountaincave(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_MOUNTAINCAVE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
	}
	
	public Mountaincave(double x, double y, int boss) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_MOUNTAINCAVE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
		this.boss = boss;
	}
}
