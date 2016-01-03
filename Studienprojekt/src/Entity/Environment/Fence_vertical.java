package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Fence_item;
import Manager.Content;


public class Fence_vertical extends Environment{
	
	public static String NAME = "Zaun vertikal";
	public static String PATH = "Resources/Environment/fence_vertical.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 0;
			
	static HitBox hitBox = new HitBox(14, 0, 3, 32); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Fence_item.class, 100);
	}};
	
	public Fence_vertical(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_FENCE_VERTICAL);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
