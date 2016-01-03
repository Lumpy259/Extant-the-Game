package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Meat_grilled extends Item {

	public static String NAME = "Fleisch gegrillt";
	public static String PATH = "Resources/Items/meat_grilled.png";
	public static int TYPE = Item.CONSUMABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 64;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Meat_grilled(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_MEAT_GRILLED);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}