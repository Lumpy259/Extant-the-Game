package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Wood;
import Manager.Content;


public class Tent extends Environment{
	
	public static String NAME = "Zelt";
	public static String PATH = "Resources/Environment/tent.png";
	public static int WIDTH = 67;
	public static int HEIGHT = 81;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(0,-30,67,50); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Tent(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_TENT);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}

