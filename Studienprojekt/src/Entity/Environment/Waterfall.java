package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Wood;
import Manager.Content;


public class Waterfall extends Environment{
	
	public static String NAME = "Wasserfall";
	public static String PATH = "Resources/Environment/waterfall.png";
	public static int WIDTH = 96;
	public static int HEIGHT = 90;
	static int maxLife = Integer.MAX_VALUE;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0, 0, 0, 0); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Waterfall(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_WATERFALL);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
	}
}