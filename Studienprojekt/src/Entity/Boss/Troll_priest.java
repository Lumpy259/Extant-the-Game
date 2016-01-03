package Entity.Boss;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Manager.Content;

public class Troll_priest extends Boss {
	//Boss 2, eingefügt um alte Fähigkeiten ruzuweißen
	public static String NAME = "Zorek, Herrscher über die Elemente";
	public static String PATH = "Resources/Boss/troll_priest.png";
	public static String PATH2 = "";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int BossXP = 50;
	
	static int damage = 10;
	static int maxLife =200;
	static double moveSpeed = 0;
	static HitBox hitBox = new HitBox(0,0,32,32 );
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bronze_chunk.class, 100);
		put(Bronze.class, 30);
	}};

	public Troll_priest(double x, double y) {
		super(NAME, PATH, PATH2, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.BOSS_TROLLPRIEST, Content.BOSS_TROLLPRIEST,attackrange);
		super.BossXP = this.BossXP;
		super.loot = this.loot;
		
			}
}
