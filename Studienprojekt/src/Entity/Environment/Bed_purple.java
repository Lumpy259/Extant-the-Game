package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bedpurple_item;
import Manager.Content;


public class Bed_purple extends Environment{
	
	public static String NAME = "Bett lila";
	public static String PATH = "Resources/Furniture/bed_purple.png";
	public static int WIDTH = 26;
	public static int HEIGHT = 45;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 26, 45); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bedpurple_item.class, 100);
	}};
	
	public Bed_purple(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_BEDPURPLE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}