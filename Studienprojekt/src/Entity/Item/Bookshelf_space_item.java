package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Bookshelf_space_item extends Item {

	public static String NAME = "Futuristisches B�cherregal";
	public static String PATH = "Resources/Items/space_bookshelf_item.png";
	public static int TYPE = Item.PLACEABLE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 10;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Bookshelf_space_item(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_BOOKSHELF_SPACE);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
