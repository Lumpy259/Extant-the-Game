package World.Tile;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

import Entity.Item.Diamond;
import Entity.Item.Stone_item;
import Manager.Content;

public class UDiamond extends Tile {

	public static String NAME = "Diamant";
	public static String PATH = "Resources/UndergroundTile/diamond.png";
	public static int WIDTH = 37;
	public static int HEIGHT = 42;
	
	public static boolean accessable = false;
	public static double moveSpeed = 1;
	public static int damage = 0;
	public static int maxLife = 100;
	
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Diamond.class, 100);
	}};
	
	public static Color color = new Color(82, 190, 218);
	
	public UDiamond(int id) {
		super(id, null, maxLife, accessable, moveSpeed, damage, new Content(NAME, PATH, WIDTH, HEIGHT, new Point(1,1)));
		super.loot = this.loot;
		super.color = this.color;
	}
}
