package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bookshelf_space_item;
import Manager.Content;


public class Bookshelf_space extends Environment{
	
	public static String NAME = "Futuristisches Bücherregal";
	public static String PATH = "Resources/Furniture/space_bookshelf.png";
	public static int WIDTH = 45;
	public static int HEIGHT = 45;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,-35, 47, 10); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Bookshelf_space_item.class, 100);
	}};
	
	public Bookshelf_space(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_BOOKSHELF_SPACE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}