package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Hoe_iron extends Item {

	public static String NAME = "Eisenharke";
	public static String PATH = "Resources/Items/hoe_iron.png";
	public static int TYPE = Item.HOE;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 25;
	public static int damage = 3;
	public static int armor = 0;
	public static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Hoe_iron(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_HOE_IRON);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
