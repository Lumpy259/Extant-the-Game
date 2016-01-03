package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.House_item;
import Manager.Content;


public class House extends Environment{
	
	public static String NAME = "Haus";
	public static String PATH = "Resources/Environment/house.png";
	public static int WIDTH = 162;
	public static int HEIGHT = 180;
	static int maxLife = 1000000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,-50, 162, 107); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(House_item.class, 100);
	}};
	
	public House(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_HOUSE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
