package Entity.NPC;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Meat;
import Entity.Item.Wool;
import Manager.Content;

public class Sheep extends NPC {

	public static String NAME = "Schaaf";
	public static String PATH = "Resources/NPC/sheep.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int NpcXP = 20;
	
	static int damage = 0;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 100;
	//public static Content image2 = Content.MONSTER_ORC;
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Meat.class, 100);
		put(Wool.class, 100);
	}};
	
	public Sheep(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.NPC_SHEEP, attackrange);
		super.NpcXP = this.NpcXP;
		super.loot = this.loot;
		}
}
