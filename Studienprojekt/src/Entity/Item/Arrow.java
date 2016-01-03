package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Arrow extends Item {

	public static String NAME = "Pfeile";
	public static String PATH = "Resources/Items/arrow.png";
	public static int TYPE = Item.RESSOURCE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 99;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Arrow(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_ARROW);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
