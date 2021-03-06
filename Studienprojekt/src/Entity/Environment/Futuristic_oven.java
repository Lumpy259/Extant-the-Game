package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Futuristic_oven_item;
import Manager.Content;


public class Futuristic_oven extends Environment{
	
	public static String NAME = "Ofen";
	public static String PATH = "Resources/Usable/futuristic_oven.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(0, 0, 32, 32); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Futuristic_oven_item.class, 100);
	}};
	
	public Futuristic_oven(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_FUTURISTIC_OVEN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
