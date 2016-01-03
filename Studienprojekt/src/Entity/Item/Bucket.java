package Entity.Item;

import Entity.HitBox;
import Manager.Content;

public class Bucket extends Item {

	public static String NAME = "Eimer";
	public static String PATH = "Resources/Items/bucket.png";
	public static int TYPE = Item.BUCKET;
	public static int WIDTH = 28;
	public static int HEIGHT = 28;
	public static int attackRange = 0;
	public static int damage = 0;
	public static int armor = 0;
	public static int stackable = 64;
	public static HitBox hitBox = new HitBox(0,0, 28, 28);
	
	public Bucket(double x, double y) {
		super(NAME, PATH, TYPE, attackRange, damage, armor, stackable, Content.ITEM_BUCKET);
		super.x = x;
		super.y = y;
		super.hitBox = hitBox;
	}

}