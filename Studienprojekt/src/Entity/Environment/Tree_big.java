package Entity.Environment;

import java.util.HashMap;

import Entity.HitBox;
import Manager.Content;


public class Tree_big extends Environment{
	
	public static String NAME = "Groﬂer Baum";
	public static String PATH = "Resources/Environment/treeBig.png";
	public static int WIDTH = 260;
	public static int HEIGHT = 340;
	static int maxLife = 100000000;
	public static int environmentXP = 0;
			
	static HitBox hitBox = new HitBox(110,-220, 65, 40); 

	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
	}};
		
	public Tree_big(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_TREEBIG);
		super.environmentXP = this.environmentXP;
		super.loot = this.loot;
	}
}
