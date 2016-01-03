package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bedgreen_item;
import Manager.Content;


public class Bed_green extends Environment{
	
	public static String NAME = "Bett grün";
	public static String PATH = "Resources/Furniture/bed_green.png";
	public static int WIDTH = 26;
	public static int HEIGHT = 45;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 26, 45); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bedgreen_item.class, 100);
	}};
	
	public Bed_green(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_BEDGREEN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}