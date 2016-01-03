package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Excalibur extends Item {

	public static String NAME = "Eisenschwert";
	public static String PATH = "Resources/Items/excalibur_n.png";
	public static int TYPE = Item.WEAPON;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	private static int attackRange = 25;
	private static int damage = 4;
	private static int armor = 0;
	private static int stackable = 1;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Excalibur(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_EXCALIBUR);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}
