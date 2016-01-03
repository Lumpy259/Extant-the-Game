package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Seeds;
import Entity.Item.Wheat;
import Manager.Content;


public class Wheat_growstage2 extends Environment{
	
	public static String NAME = "Weizen Wachstumsstufe 2";
	public static String PATH = "Resources/Environment/wheat_growstage2.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Seeds.class, 60);
	}};
	
	public Wheat_growstage2(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_WHEAT_GROWSTAGE2);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
