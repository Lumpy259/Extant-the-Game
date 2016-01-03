package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Apple;
import Entity.Item.Sapling_item;
import Entity.Item.Wood;
import Manager.Content;


public class Tree_blue extends Environment{
	
	public static String NAME = "Blauer Baum";
	public static String PATH = "Resources/Environment/tree_blue.png";
	public static int WIDTH = 74;
	public static int HEIGHT = 96;
	static int maxLife = 100;
	public static int environmentXP = 30;
			
	static HitBox hitBox = new HitBox(28,-74, 17, 7); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Wood.class, 100);
		put(Apple.class, 30);
		put(Sapling_item.class, 50);
	}};
		
	public Tree_blue(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_TREEBLUE);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
			}
}
