package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Pumpkin;
import Manager.Content;


public class Pumpkin_grown extends Environment{
	
	public static String NAME = "Kürbis ausgewachsen";
	public static String PATH = "Resources/Environment/pumpkin_grown.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Pumpkin.class, 100);
	}};
	
	public Pumpkin_grown(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_PUMPKIN_GROWN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
