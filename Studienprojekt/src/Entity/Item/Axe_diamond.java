package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Axe_diamond extends Item {

	public static String NAME = "Diamantaxt";
	public static String PATH = "Resources/Items/axe_diamond.png";
	public static int TYPE = Item.AXE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 25;
	public static int damage = 8;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Axe_diamond(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_AXE_DIAMOND);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
