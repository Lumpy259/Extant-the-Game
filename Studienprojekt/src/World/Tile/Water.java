package World.Tile;

import java.awt.Color;
import java.awt.Point;

import Entity.Animation;
import Manager.Content;

public class Water extends Tile {


	public static String NAME = "Wasser";
	public static String PATH = "Resources/Tiles/water_anim.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	
	public static boolean accessable = false;
	public static double moveSpeed = 1;
	public static int damage = 0;
	public static int maxLife = 100;
	public static Color color = new Color(0, 0, 128);
	
	public Water(int id) {
		super(id, new Animation(50, 3), maxLife, accessable, moveSpeed, damage, new Content(NAME, PATH, WIDTH, HEIGHT, new Point(3,1)));
		super.color = this.color;
	}
}
