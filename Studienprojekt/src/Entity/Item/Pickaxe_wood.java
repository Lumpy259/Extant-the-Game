package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Pickaxe_wood extends Item {

	public static String NAME = "Holzspitzhacke";
	public static String PATH = "Resources/Items/axe_wood.png";
	public static int TYPE = Item.PICKAXE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 25;
	public static int damage = 2;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Pickaxe_wood(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_PICKAXE_WOOD);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
