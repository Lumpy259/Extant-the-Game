package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Manager.Content;


public class Riddlebox extends Environment{
	
	public static String NAME = "Rätselbox";
	public static String PATH = "Resources/Environment/riddlebox.png";
	public static int WIDTH = 21;
	public static int HEIGHT = 33;
	static int maxLife = 10000000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 21, 33); 

	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{

	}};
	
	public Riddlebox(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_RIDDLEBOX);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
