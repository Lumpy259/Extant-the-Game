package Entity.Monster;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Slime_purple;
import Manager.Content;

public class Blob_purple extends Monster {

	public static String NAME = "Slimie lila";
	public static String PATH = "Resources/Enemy/blob_purple.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int MonsterXP = 20;
	
	static int damage = 5;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Slime_purple.class, 100);
	}};
	//public static Content image2 = Content.MONSTER_ORC;
	public Blob_purple(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.MONSTER_BLOBPURPLE, attackrange);
		super.MonsterXP = this.MonsterXP;
		super.loot = this.loot;
			}
}
