package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Ladder_item;
import Manager.Content;


public class Ladder extends Environment{
	
	public static String NAME = "Leiter";
	public static String PATH = "Resources/Usable/Entrance.png";
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 28, 28); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
	
	public Ladder(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_ENTRANCE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
	
	/*
	public void use() {
		System.out.println("USEUSEUSE");
	}*/
}
