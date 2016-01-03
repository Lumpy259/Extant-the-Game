package Entity.Monster;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.String_item;
import Manager.Content;

public class Spider extends Monster {

	public static String NAME = "Spider";
	public static String PATH = "Resources/Enemy/spider_wip.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	public static int MonsterXP = 50;
	
	static int damage = 20;
	static int maxLife = 20;
	static double moveSpeed = 1;
	static HitBox hitBox = new HitBox(8,-25,16,7);
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(String_item.class, 100);
	}};
	//public static Content image2 = Content.MONSTER_ORC;
	public Spider(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.MONSTER_SPIDER, attackrange);
		super.MonsterXP = this.MonsterXP;
		super.loot = this.loot;
			}
}
