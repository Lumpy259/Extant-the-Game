package Entity.NPC;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Leather;
import Entity.Item.Meat;
import Manager.Content;

public class Merchant extends NPC {

	public static String NAME = "Händler";
	public static String PATH = "Resources/NPC/merchant.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int NpcXP = 20;
	public boolean animalfollow = false;
	
	
	static int damage = 0;
	static int maxLife = 100;
	static double moveSpeed = 0;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Meat.class, 100);
	}};
		
	public Merchant(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.NPC_MERCHANT, attackrange);
		super.NpcXP = this.NpcXP;
		super.loot = this.loot;
		super.animalfollow = this.animalfollow;
		}
}
