package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Futuristic_oven_item extends Item {

	public static String NAME = "Industrieofen";
	public static String PATH = "Resources/Items/futuristic_oven_item.png";
	public static int TYPE = Item.PLACEABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 12;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Futuristic_oven_item(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_FUTURISTIC_OVEN);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
