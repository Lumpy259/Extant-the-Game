package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class House_item extends Item {

	public static String NAME = "Haus";
	public static String PATH = "Resources/Items/house_item.png";
	public static int TYPE = Item.PLACEABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,-50, 162, 107);
	
	public House_item(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_HOUSEITEM);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
