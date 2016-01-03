package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Compass extends Item {

	public static String NAME = "Kompass";
	public static String PATH = "Resources/Items/compass_item.png";
	public static int TYPE = Item.COMPASS;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Compass(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_COMPASS);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}