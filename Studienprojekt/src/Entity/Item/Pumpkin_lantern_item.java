package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Pumpkin_lantern_item extends Item {

	public static String NAME = "Kürbislaterne Item";
	public static String PATH = "Resources/Items/pumpkin_lantern_item.png";
	public static int TYPE = Item.PLACEABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 64;
	public static HitBox hitBox = new HitBox(11, -14, 18, 10);
	
	public Pumpkin_lantern_item(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_PUMPKIN_LANTERN);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}