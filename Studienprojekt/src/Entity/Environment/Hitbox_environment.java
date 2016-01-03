package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Flower_item;
import Manager.Content;


public class Hitbox_environment extends Environment{
	
	public static String NAME = "Hitbox";
	public static String PATH = "Resources/Environment/hitbox.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 10000000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(2, 0, 32, 32); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Hitbox_environment(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_HITBOX);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
