package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Energy extends Item {

	public static String NAME = "Energie";
	public static String PATH = "Resources/Items/energy.png";
	public static int TYPE = Item.RESSOURCE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 0;
	private static int damage = 0;
	private static int armor = 0;
	private static int stackable = 64;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Energy(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_ENERGY);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}