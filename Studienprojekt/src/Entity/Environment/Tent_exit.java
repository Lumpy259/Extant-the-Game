package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Manager.Content;


public class Tent_exit extends Environment{
	
	public static String NAME = "Bosseingang, auﬂen";
	public static String PATH = "Resources/Environment/tent_exit.png";
	public static int WIDTH = 28;
	public static int HEIGHT = 26;
	static int maxLife = 1000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 28, 26); 

	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{

	}};
	
	public Tent_exit(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_TENTEXIT);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
