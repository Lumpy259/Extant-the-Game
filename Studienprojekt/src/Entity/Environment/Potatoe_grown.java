package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Potatoe;
import Manager.Content;


public class Potatoe_grown extends Environment{
	
	public static String NAME = "Kartoffelpflanze ausgewachsen";
	public static String PATH = "Resources/Environment/potatoe_grown.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Potatoe.class, 100);
	}};
	
	public Potatoe_grown(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_POTATOE_GROWN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
