package World.Tile;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Entity.Animation;
import Entity.Item.Bow;
import Entity.Item.Slime_gold;
import Manager.Content;
import World.Tile.Dirt;
import World.Tile.Grass;
import World.Tile.Ice;
import World.Tile.Sand;
import World.Tile.Snow;
import World.Tile.Stone;
import World.Tile.Water;

public class Tile {

	public static Tile[] tiles = new Tile[69];
	public static Tile WATER = new Water(0);
	public static Tile GRASS = new Grass(1);
	public static Tile DIRT = new Dirt(2);
	public static Tile SAND = new Sand(3);
	public static Tile STONE = new Stone(4);
	public static Tile LAVA = new Lava(5);
	public static Tile DIRT_HOED = new Dirt_hoed(6);
	
	public static Tile ICE = new Ice(7);
	public static Tile SNOW = new Snow(8);
	
	public static Tile DIRT_WATERED = new Dirt_watered(9);
	
	public static Tile U_DIRT = new UDirt(10);
	public static Tile U_SAND = new USand(11);
	public static Tile U_STONE = new UStone(12);
	public static Tile U_COAL = new UCoal(13);
	public static Tile U_IRON = new UIron(14);
	public static Tile U_RUBY = new URuby(15);
	public static Tile U_GOLD = new UGold(16);
	public static Tile U_DIAMOND = new UDiamond(17);
	
	public static Tile H_WALL = new House_wall(18);
	public static Tile H_CORNER = new House_corner(19);
	public static Tile H_FLOOR = new House_floor(20);
	
	public static Tile B_FLOOR = new Boss_floor(21);
	
	public static Tile C_RED = new CarpetRed(22);
	public static Tile C_BLUE = new CarpetBlue(23);
	public static Tile C_YELLOW = new CarpetYellow(24);
	public static Tile C_BROKEN = new CarpetBroken(25);
	
	public static Tile MOUNTAIN11_GRASS = new Mountain11Grass(26);
	public static Tile MOUNTAIN12_GRASS = new Mountain12Grass(27);
	public static Tile MOUNTAIN13_GRASS = new Mountain13Grass(28);
	public static Tile MOUNTAIN21 = new Mountain21(29);
	public static Tile MOUNTAIN23 = new Mountain23(30);
	public static Tile MOUNTAIN31 = new Mountain31(31);
	public static Tile MOUNTAIN32 = new Mountain32(32);
	public static Tile MOUNTAIN33 = new Mountain33(33);
	public static Tile MOUNTAIN41 = new Mountain41(34);
	public static Tile MOUNTAIN42 = new Mountain42(35);
	public static Tile MOUNTAIN43 = new Mountain43(36);
	public static Tile MOUNTAIN51_GRASS = new Mountain51Grass(37);
	public static Tile MOUNTAIN52_GRASS = new Mountain52Grass(38);
	public static Tile MOUNTAIN53_GRASS = new Mountain53Grass(39);
	
	public static Tile ICEROCK = new IceRock(40);
	
	public static Tile H_WALL_TENT = new Tent_wall(41);
	public static Tile H_CORNER_TENT = new Tent_corner(42);
	public static Tile H_FLOOR_TENT = new Tent_floor(43);

	public static Tile MOUNTAIN51_STONE = new Mountain51Stone(65);
	public static Tile MOUNTAIN52_STONE = new Mountain52Stone(66);
	public static Tile MOUNTAIN53_STONE = new Mountain53Stone(67);
	

	public static Tile MOUNTAIN51_DIRT = new Mountain51Dirt(44);
	public static Tile MOUNTAIN52_DIRT = new Mountain52Dirt(45);
	public static Tile MOUNTAIN53_DIRT = new Mountain53Dirt(46);
	

	public static Tile MOUNTAIN51_SAND = new Mountain51Sand(47);
	public static Tile MOUNTAIN52_SAND = new Mountain52Sand(48);
	public static Tile MOUNTAIN53_SAND = new Mountain53Sand(49);
	

	public static Tile MOUNTAIN51_WATER = new Mountain51Water(50);
	public static Tile MOUNTAIN52_WATER = new Mountain52Water(51);
	public static Tile MOUNTAIN53_WATER = new Mountain53Water(52);
	
	public static Tile MOUNTAIN11_STONE = new Mountain11Stone(53);
	public static Tile MOUNTAIN12_STONE = new Mountain12Stone(54);
	public static Tile MOUNTAIN13_STONE = new Mountain13Stone(55);
	
	public static Tile MOUNTAIN11_DIRT = new Mountain11Dirt(56);
	public static Tile MOUNTAIN12_DIRT = new Mountain12Dirt(57);
	public static Tile MOUNTAIN13_DIRT = new Mountain13Dirt(58);
	
	public static Tile MOUNTAIN11_SAND = new Mountain11Sand(59);
	public static Tile MOUNTAIN12_SAND = new Mountain12Sand(60);
	public static Tile MOUNTAIN13_SAND = new Mountain13Sand(61);
	
	public static Tile MOUNTAIN11_WATER = new Mountain11Water(62);
	public static Tile MOUNTAIN12_WATER = new Mountain12Water(63);
	public static Tile MOUNTAIN13_WATER = new Mountain13Water(64);
	public static Tile GRASS2 = new Grass2(68);
	
	public final int id;
	public Animation animation;
	public boolean accessable;
	public double moveSpeed;
	public int damage;
	public Content content;
	public int maxLife;
	public int currentLife;
	public static int coolDown = 0;
	public static Point hitTile = null;
	public HashMap<Class, Integer> loot;
	public Color color;
	
	public Tile(int id, Animation animation, int maxLife, boolean accessable, double moveSpeed, int damage, Content content) {
		this.id = id;
		tiles[id] = this;
		this.animation = animation;
		this.maxLife = maxLife;
		this.currentLife = maxLife;
		this.accessable = accessable;
		this.moveSpeed = moveSpeed;
		this.damage = damage;
		this.content = content;
	}
	
	public BufferedImage getImage() { return content.getImage(); }
	public BufferedImage getImage(int y, int x) { return content.getImage(y, x); }
	public BufferedImage getrotatedImage(BufferedImage image, double degrees) {
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(degrees), image.getWidth() / 2, image.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
		return image;
	}
	public Animation getAnimation() { return animation; }
	
}
