package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Slime_green extends Item {

	public static String NAME = "Grüner Schleim";
	public static String PATH = "Resources/Items/slime_green.png";
	public static int TYPE = Item.RESSOURCE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 64;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Slime_green(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_SLIMEGREEN);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}