package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Gate_item;
import Manager.Content;


public class Gate_open extends Environment{
	
	public static String NAME = "Tor offen";
	public static String PATH = "Resources/Environment/gate_open.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	static int maxLife = 100;
	public static int environmentXP = 0;
			
	static HitBox hitBox = new HitBox(0, 0, 0, 0); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Gate_open(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_GATE_OPEN);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
