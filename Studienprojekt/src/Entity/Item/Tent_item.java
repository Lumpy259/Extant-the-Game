package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Tent_item extends Item {

	public static String NAME = "Zelt";
	public static String PATH = "Resources/Items/tent_item.png";
	public static int TYPE = Item.PLACEABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,-30,67,50);
	
	public Tent_item(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_TENTITEM);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
