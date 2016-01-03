package World.Tile;

import java.awt.Color;
import java.awt.Point;

import Manager.Content;

public class Dirt_watered extends Tile {

	public static String NAME = "Erde gegossen";
	public static String PATH = "Resources/Tiles/dirt_watered.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	
	public static boolean accessable = true;
	public static double moveSpeed = 1;
	public static int damage = 0;
	public static int maxLife = 100;
	public static Color color = new Color(90, 50, 35);
	
	public Dirt_watered(int id) {
		super(id, null, maxLife, accessable, moveSpeed, damage, new Content(NAME, PATH, WIDTH, HEIGHT, new Point(1,1)));
		super.color = this.color;
	}
}

