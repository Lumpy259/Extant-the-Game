package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Sword_emerald extends Item {

	public static String NAME = "Smaragdtschwert";
	public static String PATH = "Resources/Items/sword_emerald.png";
	public static int TYPE = Item.WEAPON;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 7;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Sword_emerald(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_SWORD_EMERALD);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}