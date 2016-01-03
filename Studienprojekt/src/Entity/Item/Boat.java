package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Boat extends Item {

	public static String NAME = "Boot";
	public static String PATH = "Resources/Items/boat.png";
	public static int TYPE = Item.BOAT;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 0;
	public static int damage = 0;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Boat(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_BOAT);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}