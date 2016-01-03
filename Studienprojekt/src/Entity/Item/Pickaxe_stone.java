package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Pickaxe_stone extends Item {

	public static String NAME = "Steinspitzhacke";
	public static String PATH = "Resources/Items/pickaxe_stone.png";
	public static int TYPE = Item.PICKAXE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 25;
	public static int damage = 3;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Pickaxe_stone(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_PICKAXE_STONE);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
