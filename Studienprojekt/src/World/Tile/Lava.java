package World.Tile;

import java.awt.Color;
import java.awt.Point;

import Entity.Animation;
import Manager.Content;

public class Lava extends Tile {

	public static String NAME = "Lava";
	public static String PATH = "Resources/Tiles/lava_anim.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	
	public static boolean accessable = false;
	public static double moveSpeed = 1;
	public static int damage = 5;
	public static int maxLife = -1;
	public static Color color = new Color(200, 45, 45);
	
	public Lava(int id) {
		super(id, new Animation(50, 3), maxLife, accessable, moveSpeed, damage, new Content(NAME, PATH, WIDTH, HEIGHT, new Point(3,1)));
		super.color = this.color;
	}
}
