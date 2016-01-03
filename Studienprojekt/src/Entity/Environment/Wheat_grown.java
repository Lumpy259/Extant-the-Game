package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Seeds;
import Entity.Item.Wheat;
import Manager.Content;


public class Wheat_grown extends Environment{
	
	public static String NAME = "Weizen ausgewachsen";
	public static String PATH = "Resources/Environment/wheat_grown.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Wheat.class, 100);
		put(Seeds.class, 60);
	}};
	
	public Wheat_grown(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_WHEAT_GROWN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
