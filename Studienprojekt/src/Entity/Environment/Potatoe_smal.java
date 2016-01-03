package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Potatoe;
import Manager.Content;


public class Potatoe_smal extends Environment{
	
	public static String NAME = "Kartoffelpflanze klein";
	public static String PATH = "Resources/Environment/potatoe_smal.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 0;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Potatoe.class, 100);
	}};
	
	public Potatoe_smal(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_POTATOE_SMAL);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
