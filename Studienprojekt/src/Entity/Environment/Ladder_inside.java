package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Wood;
import Manager.Content;


public class Ladder_inside extends Environment{
	
	public static String NAME = "Leiterinnen";
	public static String PATH = "Resources/Usable/Exit1.png";
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 28, 28); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Ladder_inside(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_LADDEREXIT);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
