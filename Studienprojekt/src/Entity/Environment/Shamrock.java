package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Shamrock_item;
import Entity.Item.Slime_green;
import Manager.Content;


public class Shamrock extends Environment{
	
	public static String NAME = "Rock";
	public static String PATH = "Resources/Environment/shamrock.png";
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(0,0, 28, 28);

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Shamrock_item.class, 100);
	}};
	
	public Shamrock(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_SHAMROCK);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
