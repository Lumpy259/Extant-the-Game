package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Chest_leather extends Item {

	public static String NAME = "Lederbrustharnisch";
	public static String PATH = "Resources/Items/chest_leather.png";
	public static int TYPE = Item.CHEST;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 10;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Chest_leather(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_CHESTLEATHER);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
