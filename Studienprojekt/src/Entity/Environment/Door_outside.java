package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Flower_item;
import Manager.Content;


public class Door_outside extends Environment{
	
	public static String NAME = "Hauseigang, auﬂen";
	public static String PATH = "Resources/Environment/door_outside.png";
	public static int WIDTH = 21;
	public static int HEIGHT = 35;
	static int maxLife = 10000000;
	public static int environmentXP = 30;
	public int boss;
	
	static HitBox hitBox = new HitBox(0,0, 25, 35); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Door_outside(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_DOOROUTSIDE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
	}
	
	public Door_outside(double x, double y, int boss) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_DOOROUTSIDE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
		this.boss = boss;
	}
}
