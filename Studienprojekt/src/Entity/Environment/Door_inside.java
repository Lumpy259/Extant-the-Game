package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Wood;
import Manager.Content;


public class Door_inside extends Environment{
	
	public static String NAME = "Hauseigang, innen";
	public static String PATH = "Resources/Furniture/door_inside.png";
	public static int WIDTH = 21;
	public static int HEIGHT = 35;
	static int maxLife = 10000000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 25, 35); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Door_inside(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_DOORINSIDE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}