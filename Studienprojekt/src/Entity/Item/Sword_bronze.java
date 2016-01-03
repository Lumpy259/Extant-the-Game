package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Sword_bronze extends Item {

	public static String NAME = "Bronzeschwert";
	public static String PATH = "Resources/Items/sword_bronze.png";
	public static int TYPE = Item.BOOTS;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 4;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Sword_bronze(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_SWORD_BRONZE);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}