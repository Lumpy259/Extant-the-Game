package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Carrot;
import Manager.Content;


public class Carrot_growstage3 extends Environment{
	
	public static String NAME = "Karottenpflanze Wachstumsstufe3";
	public static String PATH = "Resources/Environment/carrot_growstage3.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(11, -14, 18, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Carrot.class, 100);
	}};
	
	public Carrot_growstage3(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_CARROT_GROWSTAGE3);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
