package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Sword_diamond extends Item {

	public static String NAME = "Diamantschwert";
	public static String PATH = "Resources/Items/sword_diamond.png";
	public static int TYPE = Item.WEAPON;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 9;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Sword_diamond(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_SWORD_DIAMOND);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}