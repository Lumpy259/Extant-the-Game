package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Pickaxe_iron extends Item {

	public static String NAME = "Eisenspitzhacke";
	public static String PATH = "Resources/Items/pickaxe_iron.png";
	public static int TYPE = Item.PICKAXE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 25;
	public static int damage = 5;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Pickaxe_iron(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_PICKAXE_IRON);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
