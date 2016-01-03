package Entity.Environment;

import Entity.HitBox;
import Manager.Content;


public class Bossdoor_inside extends Environment{
	
	public static String NAME = "Bosseingang, auﬂen";
	public static String PATH = "Resources/Usable/Bossdoor_inside.png";
	public static int WIDTH = 21;
	public static int HEIGHT = 35;
	static int maxLife = 10000000;
	public static int environmentXP = 30;
	
	static HitBox hitBox = new HitBox(0,0, 25, 35); 

		
	public Bossdoor_inside(double x, double y) {
		super(NAME, PATH, WIDTH, HEIGHT, maxLife, x , y, hitBox, Content.ENV_BOSSDOORINSIDE);
		super.environmentXP = this.environmentXP;
			}
}