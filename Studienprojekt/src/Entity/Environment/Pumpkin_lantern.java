package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Pumpkin_lantern_item;
import Manager.Content;


public class Pumpkin_lantern extends Environment{
	
	public static String NAME = "Kürbislaterne";
	public static String PATH = "Resources/Environment/pumpkin_lantern.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Pumpkin_lantern_item.class, 100);
	}};
	
	public Pumpkin_lantern(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_PUMPKIN_LANTERN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
