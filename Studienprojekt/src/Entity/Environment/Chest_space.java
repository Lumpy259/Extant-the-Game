package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Chest_space_item;
import Manager.Content;

public class Chest_space extends Environment{

	public static String NAME = "Futuristische Kiste";
	public static String PATH = "Resources/Environment/space_chest.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 1;
	public static int environmentXP = 0;
			
	static HitBox hitBox = new HitBox(6,-10, 26, 22); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Chest_space_item.class, 100);
	}};
	
	public Chest_space(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_CHEST_SPACE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
