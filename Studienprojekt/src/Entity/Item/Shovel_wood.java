package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Shovel_wood extends Item {

	public static String NAME = "Holzschaufel";
	public static String PATH = "Resources/Items/shovel_wood.png";
	public static int TYPE = Item.SHOVEL;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 25;
	public static int damage = 2;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Shovel_wood(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_SHOVEL_WOOD);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
