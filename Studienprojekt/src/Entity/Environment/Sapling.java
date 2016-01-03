package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Sapling_item;
import Manager.Content;


public class Sapling extends Environment{
	
	public static String NAME = "Steckling Baum";
	public static String PATH = "Resources/Environment/sapling.png";
	public static int WIDTH = 24;
	public static int HEIGHT = 24;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Sapling_item.class, 100);
	}};
	
	public Sapling(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_SAPLING);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
