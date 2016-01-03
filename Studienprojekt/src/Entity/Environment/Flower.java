package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Flower_item;
import Manager.Content;


public class Flower extends Environment{
	
	public static String NAME = "Flower";
	public static String PATH = "Resources/Environment/Flower.png";
	public static int WIDTH = 24;
	public static int HEIGHT = 24;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Flower_item.class, 100);
	}};
	
	public Flower(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_FLOWER);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
