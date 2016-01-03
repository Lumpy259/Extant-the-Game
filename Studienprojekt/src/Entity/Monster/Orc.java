package Entity.Monster;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Manager.Content;

public class Orc extends Monster {

	public static String NAME = "Orc";
	public static String PATH = "Resources/Enemy/orc.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int MonsterXP = 30;
	
	static int damage = 5;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 200;
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bronze_chunk.class, 100);
		put(Bronze.class, 30);
	}};
	
	public Orc(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.MONSTER_ORC, attackrange);
		super.MonsterXP = this.MonsterXP;
		super.loot = this.loot;
		
	}
}
