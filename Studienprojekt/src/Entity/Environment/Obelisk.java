package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.House_item;
import Manager.Content;


public class Obelisk extends Environment{
	
	public static String NAME = "Obelisk";
	public static String PATH = "Resources/Environment/obelisk.png";
	public static int WIDTH = 40;
	public static int HEIGHT = 118;
	static int maxLife = 1000000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(3,-78, 34, 40); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Obelisk(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_OBELISK);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
