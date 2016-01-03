package Entity.Boss;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Manager.Content;

public class Muffin extends Boss {

	public static String NAME = "König Muffin III";
	public static String PATH = "Resources/Boss/king_muff_n.png";
	public static String PATH2 = "Resources/Boss/king_muff_rage.png";
	public static int WIDTH = 64;
	public static int HEIGHT = 64;
	public static int BossXP = 50;
	
	static int damage = 10;
	static int maxLife =100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(11,-8,44,52 );
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bronze_chunk.class, 100);
		put(Bronze.class, 30);
	}};

	public Muffin(double x, double y) {
		super(NAME, PATH, PATH2, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.BOSS_MUFFIN, Content.BOSS_MUFFINRAGE,attackrange);
		super.BossXP = this.BossXP;
		super.loot = this.loot;
		
			}
}
