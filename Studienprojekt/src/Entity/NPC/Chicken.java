package Entity.NPC;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Feather;
import Entity.Item.Leather;
import Entity.Item.Meat;
import Manager.Content;

public class Chicken extends NPC {

	public static String NAME = "Huhn";
	public static String PATH = "Resources/NPC/chicken.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int NpcXP = 20;
		
	static int damage = 0;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Meat.class, 100);
		put(Feather.class, 80);
	}};
		
	public Chicken(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.NPC_CHICKEN, attackrange);
		super.NpcXP = this.NpcXP;
		super.loot = this.loot;
		}
}
