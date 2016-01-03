package Entity.Boss;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Manager.Content;

public class Beast extends Boss {

	public static String NAME = "Herr der Flammen";
	public static String PATH = "Resources/Boss/beast_walk.png";
	public static String PATH2 = "";
	public static int WIDTH = 128;
	public static int HEIGHT = 64;
	public static int BossXP = 50;
	
	static int damage = 10;
	static int maxLife =200;
	static double moveSpeed = 0;
	static HitBox hitBox = new HitBox(0,0,128,54);
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bronze_chunk.class, 100);
		put(Bronze.class, 30);
	}};

	public Beast(double x, double y) {
		super(NAME, PATH, PATH2, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.BOSS_BEAST, Content.BOSS_BEAST,attackrange);
		super.BossXP = this.BossXP;
		super.loot = this.loot;
		
			}
}
