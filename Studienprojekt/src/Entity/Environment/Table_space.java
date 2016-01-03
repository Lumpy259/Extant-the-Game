package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Table_space_item;
import Manager.Content;


public class Table_space extends Environment{
	
	public static String NAME = "Futuristischer Tisch";
	public static String PATH = "Resources/Furniture/table.png";
	public static int WIDTH = 25;
	public static int HEIGHT = 25;
	static int maxLife = 100;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 25, 25); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Table_space_item.class, 100);
	}};
	
	public Table_space(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_TABLE_SPACE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}