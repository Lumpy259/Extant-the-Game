package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Chair_space_item;
import Manager.Content;


public class Chair_space extends Environment{
	
	public static String NAME = "Futuristischer Stuhl";
	public static String PATH = "Resources/Furniture/space_chair.png";
	public static int WIDTH = 16;
	public static int HEIGHT = 16;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(2,0, 12, 16); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Chair_space_item.class, 100);
	}};
	
	public Chair_space(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_CHAIR_SPACE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}