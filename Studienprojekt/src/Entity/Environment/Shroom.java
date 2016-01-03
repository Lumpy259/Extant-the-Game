package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Shroom_item;
import Manager.Content;


public class Shroom extends Environment{
	
	public static String NAME = "Shroom";
	public static String PATH = "Resources/Environment/Shroom.png";
	public static int WIDTH = 24;
	public static int HEIGHT = 24;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(6, -4, 18, 20); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Shroom_item.class, 100);
	}};
	
	public Shroom(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_SHROOM);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}

