package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Wood;
import Manager.Content;


public class Tent_entrance extends Environment{
	
	public static String NAME = "Zelteingang, innen";
	public static String PATH = "Resources/Furniture/tent_entrance.png";
	public static int WIDTH = 26;
	public static int HEIGHT = 29;
	static int maxLife = 1000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 26, 29); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Tent_entrance(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_TENTENTRANCE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}