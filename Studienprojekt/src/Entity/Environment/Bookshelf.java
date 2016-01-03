package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bookshelf_item;
import Manager.Content;


public class Bookshelf extends Environment{
	
	public static String NAME = "Bücherregal";
	public static String PATH = "Resources/Furniture/bookshelf.png";
	public static int WIDTH = 45;
	public static int HEIGHT = 45;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,-35, 47, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bookshelf_item.class, 100);
	}};
	
	public Bookshelf(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_BOOKSHELF);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}