package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Stem;
import Manager.Content;


public class Plant extends Environment{
	
	public static String NAME = "Pflanze";
	public static String PATH = "Resources/Environment/plant_big.png";
	public static int WIDTH = 64;
	public static int HEIGHT = 64;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(26,-56, 17, 7); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Stem.class, 100);
		put(Stem.class, 100);
	}};
	
	public Plant(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_PLANT);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}

