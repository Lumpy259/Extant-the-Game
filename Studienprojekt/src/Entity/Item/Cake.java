package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Cake extends Item {

	public static String NAME = "Kuchen";
	public static String PATH = "Resources/Items/cake.png";
	public static int TYPE = Item.CONSUMABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 64;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Cake(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_CAKE);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}