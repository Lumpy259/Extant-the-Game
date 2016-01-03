package Entity.NPC;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Leather;
import Entity.Item.Meat;
import Manager.Content;

public class Cow extends NPC {

	public static String NAME = "Kuh";
	public static String PATH = "Resources/NPC/cow.png";
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
		put(Leather.class, 80);
	}};
		
	public Cow(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.NPC_COW, attackrange);
		super.NpcXP = this.NpcXP;
		super.loot = this.loot;
		}
}
