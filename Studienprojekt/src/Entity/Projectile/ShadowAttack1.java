package Entity.Projectile;

import java.awt.Point;
import java.util.HashMap;

import Entity.HitBox;
import Entity.Item.Bronze;
import Entity.Item.Bronze_chunk;
import Manager.Content;

public class ShadowAttack1 extends Projectile {

	public static String NAME = "Attack1";
	public static String PATH = "Resources/Animations/shadowattack1.png";
	public static int WIDTH = 10;
	public static int HEIGHT = 10;
	
	static int damage = 25;
	static HitBox hitBox = new HitBox(0, 0, 10,10);
	static int flyrange = 1000;
	
	public ShadowAttack1(double x, double y, double moveSpeed, int angle) {
		super(NAME, PATH, WIDTH, HEIGHT, damage, x , y, moveSpeed, angle, hitBox, new Content("Attack1", "Resources/Animations/shadowattack1.png", 10, 10, new Point(1,1)), flyrange);
	}
}
