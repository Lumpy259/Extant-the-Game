package Entity.Monster;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Slime_red;
import Manager.Content;

public class Treeguard extends Monster {

	public static String NAME = "Treeguard";
	public static String PATH = "Resources/Enemy/treeguard.png";
	public static int WIDTH = 89;
	public static int HEIGHT = 92;
	public static int MonsterXP = 500;
	
	static int damage = 5;
	static int maxLife = 100;
	static double moveSpeed = 0.5;
	static HitBox hitBox = new HitBox(0,-41, 85, 50);
	static int attackrange = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Slime_red.class, 100);
	}};
	//public static Content image2 = Content.MONSTER_ORC;
	public Treeguard(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, maxLife, x , y, moveSpeed, hitBox, Content.MONSTER_TREEGUARD, attackrange);
		super.MonsterXP = this.MonsterXP;
		super.loot = this.loot;
			}
}
