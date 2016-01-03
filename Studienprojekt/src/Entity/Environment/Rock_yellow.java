package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Flower_item;
import Entity.Item.Stone_item;
import Manager.Content;


public class Rock_yellow extends Environment{
	
	public static String NAME = "Sandstein";
	public static String PATH = "Resources/Environment/stone1.png";
	public static int WIDTH = 64;
	public static int HEIGHT = 64;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(12,-52, 40, 11); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Stone_item.class, 100);
	}};
	
	public Rock_yellow(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_CACTUS);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
