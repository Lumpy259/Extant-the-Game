package World.Tile;

import java.awt.Color;
import java.awt.Point;

import Manager.Content;

public class Mountain32 extends Tile {

	public static String NAME = "Gebirge";
	public static String PATH = "Resources/Tiles/mountain32.png";
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	
	public static boolean accessable = false;
	public static double moveSpeed = 1;
	public static int damage = 0;
	public static int maxLife = 100;
	public static Color color = new Color(0, 40, 0);
	
	public Mountain32(int id) {
		super(id, null, maxLife, accessable, moveSpeed, damage, new Content(NAME, PATH, WIDTH, HEIGHT, new Point(1,1)));
		super.color = this.color;
	}
}

