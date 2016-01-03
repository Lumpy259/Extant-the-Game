package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Sword_gold extends Item {

	public static String NAME = "Goldschwert";
	public static String PATH = "Resources/Items/sword_gold.png";
	public static int TYPE = Item.WEAPON;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 6;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Sword_gold(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_SWORD_GOLD);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}