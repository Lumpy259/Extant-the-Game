package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Melon_seeds extends Item {

	public static String NAME = "Melonen Saamen";
	public static String PATH = "Resources/Items/melon_seeds.png";
	public static int TYPE = Item.PLACEABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 64;
	public static HitBox hitBox = new HitBox(11, -14, 18, 10);
	
	public Melon_seeds(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_MELON_SEEDS);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
