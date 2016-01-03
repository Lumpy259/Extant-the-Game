package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Boots_leather extends Item {

	public static String NAME = "Lederschuhe";
	public static String PATH = "Resources/Items/boots_leather.png";
	public static int TYPE = Item.BOOTS;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 2;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Boots_leather(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_BOOTSLEATHER);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
