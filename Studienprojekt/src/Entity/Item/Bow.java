package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Bow extends Item {

	public static String NAME = "Bogen";
	public static String PATH = "Resources/Items/bow.png";
	public static int TYPE = Item.RWEAPON;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 300;
	private static int damage = 4;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Bow(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_BOW);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
