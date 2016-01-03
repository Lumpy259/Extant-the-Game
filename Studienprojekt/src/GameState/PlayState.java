package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import Entity.Animation;
import Entity.Entity;
import Entity.Player;
import Entity.Environment.*;
import Entity.Item.*;
import Entity.Monster.*;
import Entity.NPC.*;
import Main.Game;
import Manager.Chestuse;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Hud;
import Manager.InputHandler;
import Manager.Inventory;
import Manager.Light;
import Manager.Music;
import Manager.OvenandMerchantuse;
import Manager.Rand;
import Manager.Settings;
import Manager.Statistics;
import Manager.Weather;
import World.World;
import World.Tile.Tile;


public class PlayState extends GameState {

	public static World world;
	public static Player player;
	public static boolean boolmonster = false;

	public static ArrayList<ArrayList<Entity>> entities = new ArrayList<>();
	public ArrayList<Boolean[]> minimap = new ArrayList<>();
	
	private Content hud;
	private Content map;
	private Content fairy;
	private Content uLight;
	public static ArrayList<Entity> removeEntity = new ArrayList<>();
	public static ArrayList<Entity> addEntity = new ArrayList<>();
	public static Inventory inventory = new Inventory();
	public static Chestuse chestuse = new Chestuse();
	public static OvenandMerchantuse ovenuse = new OvenandMerchantuse();
	public static int activeShortCut = 1;
	private Animation fairyAnimation;
	public static ArrayList<Entity> items = new ArrayList<>();
	public static HashMap<Long, Point> gatemap = new HashMap<Long, Point>();
	public static ArrayList<Long> gatearray = new ArrayList<Long>();
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Gold.class, 1);
		put(Chest_leather.class, 1);
	}};
	public static HashMap<Point, ArrayList<Entity>> house = new HashMap<Point, ArrayList<Entity>>();
	public static HashMap<Point, InventoryItem[][]> chest = new HashMap<Point, InventoryItem[][]>();
	public static HashMap<Point, InventoryItem[][]> oven = new HashMap<Point, InventoryItem[][]>();
	public long timeNow;
	public Weather weather;
	public Light light;
	public static boolean mappressed = false;
	public static Content compass = new Content ("COMPASS", "Resources/HUD/compass.png", 100, 100, new Point(1,1));
	public static Content compassneedlered = new Content ("COMPASSNEEDLE", "Resources/HUD/compass_needle.png", 75, 75, new Point(1,1));
	public static Content compassneedleblue = new Content ("COMPASSNEEDLEBLUE", "Resources/HUD/clock.png", 75, 75, new Point(1,1));
	public double degreered = 0;
	public double degreeblue = 0;
	public static boolean houseplaced = false;
	public static boolean isthisaoven = false;
	//public ArrayList<Point> rain = new ArrayList<>();
	
	public static int tutorialnumber = 0;
	public static boolean wasInInventory = false;
	public static boolean wasInStatus = false;
	
	public static int merchantspawnpointx;
	public static int merchantspawnpointy;
	public static int tentspawnpointx;
	public static int tentspawnpointy;
	
	public static int waterfallY;
	
	public static int obelsikyRand;
	public static int obelsikxRand;
	
	public static int bossstoneyRand;
	public static int bossstonexRand;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void createMonsters() {
		LoadState.generateLoadingScreen();
		HashMap<Point, Integer> layer = world.world.get(0);
		Random rand = new Random(); 
		int xRand;
		int yRand;
		int eRand;
		int blobRand;
		int envRand;
		int xmerchantRand;
		int ymerchantRand;
		
		merchantspawnpointx = rand.nextInt(500*32-16*32)+8*32;
		merchantspawnpointy = rand.nextInt(500*32-16*32)+8*32;
		tentspawnpointx= merchantspawnpointx-19;
		tentspawnpointy= merchantspawnpointy-32*2-6;
		while(!(layer.get(new Point((int)merchantspawnpointx/32, (int)merchantspawnpointy/32)) == Tile.GRASS.id || 
				layer.get(new Point((int)merchantspawnpointx/32, (int)merchantspawnpointy/32)) == Tile.DIRT.id || 
				layer.get(new Point((int)merchantspawnpointx/32, (int)merchantspawnpointy/32)) == Tile.SAND.id ||
				layer.get(new Point((int)merchantspawnpointx/32, (int)merchantspawnpointy/32)) == Tile.STONE.id) && 
				!(layer.get(new Point((int)tentspawnpointx/32, (int)tentspawnpointy/32)) == Tile.GRASS.id || 
				layer.get(new Point((int)tentspawnpointx/32, (int)tentspawnpointy/32)) == Tile.DIRT.id || 
				layer.get(new Point((int)tentspawnpointx/32, (int)tentspawnpointy/32)) == Tile.SAND.id ||
				layer.get(new Point((int)tentspawnpointx/32, (int)tentspawnpointy/32)) == Tile.STONE.id)){
			merchantspawnpointx = rand.nextInt(500*32-16*32)+8*32;
			merchantspawnpointy = rand.nextInt(500*32-16*32)+8*32;
			tentspawnpointx= merchantspawnpointx-19;
			tentspawnpointy= merchantspawnpointy-32*2-6;
		}
		
		entities.get(Game.playerZ).add(new Merchant(merchantspawnpointx, merchantspawnpointy));		
		entities.get(Game.playerZ).add(new Tent(tentspawnpointx, tentspawnpointy));
		
		entities.get(Game.playerZ).add(new Bossdoor_outside(8050, 8050));
		
		for (int i=0; i<2500;i++) {
			
			xRand = rand.nextInt(500*32-16*32)+8*32;
			yRand = rand.nextInt(500*32-16*32)+8*32;
			eRand = rand.nextInt(7);
			blobRand = rand.nextInt(5);
			
			while(((xRand>Player.spawnpointX-15*32 && xRand<Player.spawnpointX+15*32) && (yRand>Player.spawnpointY-15*32 && yRand<Player.spawnpointY+15*32)) ||
				((xRand>merchantspawnpointx-5*32 && xRand<merchantspawnpointx+5*32) && (yRand>merchantspawnpointy-5*32 && yRand<merchantspawnpointy+5*32)))  {
				xRand = rand.nextInt(500*32-16*32)+8*32;
				yRand = rand.nextInt(500*32-16*32)+8*32;
			}
			
			if (layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.GRASS.id || layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.DIRT.id || layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.SAND.id || layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.STONE.id){
				if (eRand == 0)
					entities.get(Game.playerZ).add(new Orc(xRand, yRand));
				else if (eRand == 1){
					if(blobRand == 0) entities.get(Game.playerZ).add(new Blob_green(xRand, yRand));
					if(blobRand == 1) entities.get(Game.playerZ).add(new Blob_red(xRand, yRand));
					if(blobRand == 2) entities.get(Game.playerZ).add(new Blob_purple(xRand, yRand));
					if(blobRand == 3) entities.get(Game.playerZ).add(new Blob_blue(xRand, yRand));
					if(blobRand == 4) entities.get(Game.playerZ).add(new Blob_gold(xRand, yRand));
				}
				else if (eRand == 2)
					entities.get(Game.playerZ).add(new Spider(xRand, yRand));
				else if (eRand == 3)
					entities.get(Game.playerZ).add(new Sheep(xRand, yRand));
				else if (eRand == 4)
					entities.get(Game.playerZ).add(new Cow(xRand, yRand));
				else if (eRand == 5)
					entities.get(Game.playerZ).add(new Chicken(xRand, yRand));
				else if (eRand == 6)
					entities.get(Game.playerZ).add(new Baby_dragon(xRand, yRand));
				
				if (entities.get(Game.playerZ).get(entities.get(Game.playerZ).size()-1).validateNextPosition(entities.get(Game.playerZ), xRand, yRand)) {
					entities.get(Game.playerZ).remove(entities.get(Game.playerZ).size()-1);
					i--;
				}
			}
			else i--;
		}
	}
	
	public void createEnvironment() {
		LoadState.generateLoadingScreen();
		HashMap<Point, Integer> layer = world.world.get(0);
		Random rand = new Random(); 
		int xRand;
		int yRand;
		int eRand;
		int blobRand;
		int envRand;
		
		obelsikxRand = rand.nextInt(500*32-16*32)+8*32;
		obelsikyRand = rand.nextInt(500*32-16*32)+8*32;
		
		entities.get(Game.playerZ).add(new Obelisk(obelsikxRand, obelsikyRand));
		entities.get(Game.playerZ).add(new Bossdoor_outside(obelsikxRand+10, obelsikyRand+83, 2));
		
		bossstonexRand = rand.nextInt(500*32-16*32)+8*32;
		bossstoneyRand = rand.nextInt(500*32-16*32)+8*32;
		
		entities.get(Game.playerZ).add(new Rock(bossstonexRand, bossstoneyRand));
		entities.get(Game.playerZ).add(new Bossdoor_outside(bossstonexRand+16, bossstoneyRand+28, 3));
		
		for (int i=0; i<10000;i++) {
			xRand = rand.nextInt(500*32-16*32)+8*32;
			yRand = rand.nextInt(500*32-16*32)+8*32;
			envRand = rand.nextInt(7);
			
			while(((xRand>Player.spawnpointX-15*32 && xRand<Player.spawnpointX+15*32) && (yRand>Player.spawnpointY-15*32 && yRand<Player.spawnpointY+15*32))||
			((xRand>merchantspawnpointx-5*32 && xRand<merchantspawnpointx+5*32) && (yRand>merchantspawnpointy-5*32 && yRand<merchantspawnpointy+5*32)))  {
				xRand = rand.nextInt(500*32-16*32)+8*32;
				yRand = rand.nextInt(500*32-16*32)+8*32;
			}
			if (layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.GRASS.id){
				if (envRand == 0) entities.get(Game.playerZ).add(new Shamrock(xRand, yRand));
				if (envRand == 1) entities.get(Game.playerZ).add(new Tree_green(xRand, yRand));
				if (envRand == 2) entities.get(Game.playerZ).add(new Tree_purple(xRand, yRand));
				if (envRand == 3) entities.get(Game.playerZ).add(new Tree_blue(xRand, yRand));
				if (envRand == 4) entities.get(Game.playerZ).add(new Tree_red(xRand, yRand));
				if (envRand == 5) entities.get(Game.playerZ).add(new Shroom(xRand, yRand));
				if (envRand == 6) entities.get(Game.playerZ).add(new Flower(xRand, yRand));
			}
			else if (layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.SAND.id)
				entities.get(Game.playerZ).add(new Rock_yellow(xRand, yRand));
			else if (layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.STONE.id)
				entities.get(Game.playerZ).add(new Rock(xRand, yRand));
			else if (layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.WATER.id){}
				//entities.get(Game.playerZ).add(new Plant(xRand, yRand));
			else if (layer.get(new Point((int)xRand/32, (int)yRand/32)) == Tile.DIRT.id)
				entities.get(Game.playerZ).add(new Plant(xRand, yRand));
			else {i--; continue;}
				
			if (entities.get(Game.playerZ).get(entities.get(Game.playerZ).size()-1).validateNextPosition(entities.get(Game.playerZ), xRand, yRand)) {
				entities.get(Game.playerZ).remove(entities.get(Game.playerZ).size()-1);
				i--;
			}
		}
		
		
		entities.get(Game.playerZ).add(new Mountaincave(30*32+33, waterfallY*32+31, 1));
		entities.get(Game.playerZ).add(new Waterfall(30*32+1, waterfallY*32+10));
	}
	
	public void createSpawn() {
        
		for(int j = 0; j < 30; j ++){
			for(int i = 0; i < 30; i ++){
				if (j == 0 && (i>21)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (j == 1 && (i>15)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (j == 2 && (i>8)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (j == 28 && (i<21)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (j == 28 && (i<15)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (j == 29 && (i<8)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i == 0 && (j>21)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i == 1 && (j>15)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i == 1 && (j>8)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i == 27 && (j<21)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i == 28 && (j<15)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i == 29 && (j<8)) PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				if (i >1 && i <28 && j >1 && j < 28) {
					PlayState.world.world.get(Game.playerZ).put(new Point((int)Player.spawnpointX/32-15+i, (int)Player.spawnpointY/32-15+j), Tile.GRASS.id);
				}
			}
		}
		Game.playerX+=150;
		
		entities.get(Game.playerZ).add(new Tree_big(Player.spawnpointX-100, Player.spawnpointY-300));
		
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+5*32, Player.spawnpointY-15*32+6*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+12*32, Player.spawnpointY-15*32+5*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+17*32, Player.spawnpointY-15*32+3*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+21*32, Player.spawnpointY-15*32+3*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+18*32, Player.spawnpointY-15*32+7*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+10*32, Player.spawnpointY-15*32+7*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+27*32, Player.spawnpointY-15*32+2*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+7*32, Player.spawnpointY-15*32+2*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+3*32, Player.spawnpointY-15*32+17*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+6*32, Player.spawnpointY-15*32+12*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+2*32, Player.spawnpointY-15*32+28*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+14*32, Player.spawnpointY-15*32+1*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+3*32, Player.spawnpointY-15*32+10*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+8*32, Player.spawnpointY-15*32+28*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+11*32, Player.spawnpointY-15*32+18*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+26*32, Player.spawnpointY-15*32+11*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+12*32, Player.spawnpointY-15*32+23*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+8*32, Player.spawnpointY-15*32+17*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+23*32, Player.spawnpointY-15*32+7*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+15*32, Player.spawnpointY-15*32+16*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+23*32, Player.spawnpointY-15*32+9*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+16*32, Player.spawnpointY-15*23+2*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+20*32, Player.spawnpointY-15*32+16*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+23*32, Player.spawnpointY-15*32+19*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+24*32, Player.spawnpointY-15*32+24*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+26*32, Player.spawnpointY-15*32+17*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+4*32, Player.spawnpointY-15*32+23*32));
		entities.get(Game.playerZ).add(new Tree_green(Player.spawnpointX-15*32+25*32, Player.spawnpointY-15*32+21*32));
		entities.get(Game.playerZ).add(new Tree_purple(Player.spawnpointX-15*32+12*32, Player.spawnpointY-15*32+26*32));
		entities.get(Game.playerZ).add(new Tree_blue(Player.spawnpointX-15*32+16*32, Player.spawnpointY-26*32));
		entities.get(Game.playerZ).add(new Tree_red(Player.spawnpointX-15*32+17*32, Player.spawnpointY-15*32+23*32));
		entities.get(Game.playerZ).add(new Shroom(Player.spawnpointX-15*32+7*32, Player.spawnpointY-15*32+22*32));
		entities.get(Game.playerZ).add(new Shroom(Player.spawnpointX-15*32+19*32, Player.spawnpointY-15*32+24*32));
		entities.get(Game.playerZ).add(new Shroom(Player.spawnpointX-15*32+5*32, Player.spawnpointY-15*32+14*32));
		entities.get(Game.playerZ).add(new Shroom(Player.spawnpointX-15*32+9*32, Player.spawnpointY-15*32+7*32));
		entities.get(Game.playerZ).add(new Shroom(Player.spawnpointX-15*32+20*32, Player.spawnpointY-15*32+5*32));
		entities.get(Game.playerZ).add(new Shroom(Player.spawnpointX-15*32+23*32, Player.spawnpointY-15*32+8*32));
		entities.get(Game.playerZ).add(new Flower(Player.spawnpointX-15*32+4*32, Player.spawnpointY-15*32+25*32));
		entities.get(Game.playerZ).add(new Flower(Player.spawnpointX-15*32+6*32, Player.spawnpointY-15*32+18*32));
		entities.get(Game.playerZ).add(new Flower(Player.spawnpointX-15*32+17*32, Player.spawnpointY-15*16+25*32));
		entities.get(Game.playerZ).add(new Flower(Player.spawnpointX-15*32+26*32, Player.spawnpointY-15*32+17*32));
		entities.get(Game.playerZ).add(new Flower(Player.spawnpointX-15*32+27*32, Player.spawnpointY-15*32+6*32));
		entities.get(Game.playerZ).add(new Flower(Player.spawnpointX-15*32+16*32, Player.spawnpointY-15*32+7*32));
		entities.get(Game.playerZ).add(new Shamrock(Player.spawnpointX-15*32+11*32, Player.spawnpointY-15*32+26*32));
		entities.get(Game.playerZ).add(new Shamrock(Player.spawnpointX-15*32+18*32, Player.spawnpointY-15*32+18*32));
		entities.get(Game.playerZ).add(new Shamrock(Player.spawnpointX-15*32+24*32, Player.spawnpointY-15*32+20*32));
		entities.get(Game.playerZ).add(new Shamrock(Player.spawnpointX-15*32+7*32, Player.spawnpointY-15*32+10*32));
		entities.get(Game.playerZ).add(new Shamrock(Player.spawnpointX-15*32+21*32, Player.spawnpointY-15*32+8*32));
		entities.get(Game.playerZ).add(new Shamrock(Player.spawnpointX-15*32+26*32, Player.spawnpointY-15*32+3*32));
	}
	
	public void createInventory() {	
		HashMap<Class, Integer> items = new HashMap<Class, Integer>() {{
			put(Ladder_item.class, 10);
			put(Axe_diamond.class, 1);
			put(Sword_diamond.class, 1);
			put(Tent_item.class, 2);
			put(Chest_item.class, 12);
		}};
		
    	for(Class key : items.keySet())
    		try {
    			for (int i=0; i < items.get(key); i++)
    				inventory.addItem((Item)key.getConstructors()[0].newInstance(1,1));
			} catch (Exception e) {e.printStackTrace();}
	}
	
	public void init() {
		entities.add(new ArrayList());
		player = new Player();
		entities.get(Game.playerZ).add(player);
		
		weather = new Weather();

		world = new World();

		
		createMonsters();
		createEnvironment();
		createSpawn();
		createInventory();
		
		hud = new Content("HUD", "Resources/HUD/HUD.png", 512, 80, new Point(1,1));
		fairy = new Content("FAIRY", "Resources/HUD/fairy2.png", 32, 32, new Point(3,4));
		map = new Content("KARTE", "Resources/HUD/frame.png", 300, 300, new Point(1,1));
		
		Game.playerX = Settings.SpawnPosition.x * 32;
		Game.playerY = Settings.SpawnPosition.y * 32;
		
		if (Settings.Tutorial == true) gsm.setState(gsm.TUTORIAL2);
		else if (Settings.Intro == true) gsm.setState(gsm.INTRO);
	}

	public void handleInput(InputHandler input) {	
		if (input.isPressed(input.BOSS))		{	gsm.setState(gsm.BOSS); Player.usewhat=Entity.ZERO;}
		if (input.isPressed(input.INVENTORY))			gsm.setState(gsm.INVENTORY);
		else if (input.isPressed(input.STATUS))			gsm.setState(gsm.STATUS2);
		else if (input.isPressed(input.INGAMEMENUE))	gsm.setState(gsm.INGAMEMENUE);
		else if (input.isPressed(input.TALK)){
			DialogState.yuidialog=true;
			gsm.setState(gsm.DIALOG);
		}
		else if(input.isPressed(input.TUTORIAL)){
			TutorialState.dialogCounter=tutorialnumber;
			gsm.setState(gsm.TUTORIAL);
			tutorialnumber++;

		}
		
		if (input.isPressed(input.MAP) && Player.compass == false) mappressed = (mappressed == true ? false : true);
		if(Player.compass == true || Player.isanimalfollowing == true){}
		else if (input.isPressed(input.ONE))	activeShortCut = 1;
		else if (input.isPressed(input.TWO))	activeShortCut = 2;
		else if (input.isPressed(input.THREE))	activeShortCut = 3;
		else if (input.isPressed(input.FOUR))	activeShortCut = 4;
		else if (input.isPressed(input.FIVE))	activeShortCut = 5;
		else if (input.isPressed(input.SIX))	activeShortCut = 6;
		else if (input.isPressed(input.SEVEN))	activeShortCut = 7;
		else if (input.isPressed(input.EIGHT))	activeShortCut = 8;
		else if (input.isPressed(input.NINE))	activeShortCut = 9;
		else if (input.isPressed(input.ZERO))	activeShortCut = 10;
		if (input.isPressed(input.DEBUG)) {
			if (Game.Debug) Game.Debug = false;
			else Game.Debug = true;
			gsm.setState(gsm.ADMIN);
		}
	}
	
	public void update(InputHandler input) {
		if(Player.placechicken == true){
			if (!new Cow(Player.tempx , Player.tempy).validateNextPosition(entities.get(Game.playerZ), Player.tempx, Player.tempy)){
				Random rand = new Random(); 
				int random;
				random = rand.nextInt(9);
				if(random == 1) entities.get(Game.playerZ).add(new Cow(Player.tempx , Player.tempy));
				//Chicken statt Kuh booooy
				Player.placechicken = false;
			}
			Player.placechicken = false;
		}
		if(Player.cowbreeding == true){
			for(int j = 0; j < 96*2; j ++){
				for(int i = 0; i < 96*2; i ++){
					if(!new Cow(Entity.animal.x-96+i, Entity.animal.y-96+j).validateNextPosition(entities.get(Game.playerZ), Entity.animal.x-96+i, Entity.animal.y-96+j)){
						entities.get(Game.playerZ).add(new Cow(Entity.animal.x-96+i,Entity.animal.y-96+j));
						j=96*2; 
						i=96*2;
					}
				}
			}
			Player.cowbreeding = false;
		}
		if(Player.sheepbreeding == true){
			for(int j = 0; j < 96*2; j ++){
				for(int i = 0; i < 96*2; i ++){
					if(!new Sheep(Entity.animal.x-96+i, Entity.animal.y-96+j).validateNextPosition(entities.get(Game.playerZ), Entity.animal.x-96+i, Entity.animal.y-96+j)){
						entities.get(Game.playerZ).add(new Sheep(Entity.animal.x-96+i,Entity.animal.y-96+j));
						j=96*2; 
						i=96*2;
					}
				}
			}
			Player.sheepbreeding = false;
		}
		if(!gatearray.isEmpty()){
			if(gatearray.get(0)+1600<=Game.TIME && ((gatemap.get(gatearray.get(0)).getX()+32 < Game.playerX || gatemap.get(gatearray.get(0)).getX() > Game.playerX) && (gatemap.get(gatearray.get(0)).getY()+32 > Game.playerY ||  gatemap.get(gatearray.get(0)).getY()+10 < Game.playerY))){
				entities.get(Game.playerZ).add(new Gate(gatemap.get(gatearray.get(0)).getX(), gatemap.get(gatearray.get(0)).getY()));
				Entity.deletefence(entities.get(Game.playerZ), gatemap.get(gatearray.get(0)).getX(), gatemap.get(gatearray.get(0)).getY());
				gatemap.remove(gatearray.get(0));
				gatearray.remove(0);
			}
		}
		
		if (minimap.size() <= Game.playerZ) {
			minimap.add(new Boolean[250000]);
			for(int i=0;i<250000;i++)
				minimap.get(Game.playerZ)[i] = false;
		}
		int tempCounter = 0;
		for (int x = (int)Game.playerX/32-5; x<(int)Game.playerX/32+5; x++)
			for (int y = (int)Game.playerY/32-5; y<(int)Game.playerY/32+5; y++) {
				 //Kreisf�rmig
				if ( tempCounter < 3 || (tempCounter > 6 && tempCounter < 11) ||  (tempCounter > 18 && tempCounter < 21) || tempCounter == 29
					|| tempCounter == 70 || (tempCounter > 78 && tempCounter < 81) || (tempCounter > 88 && tempCounter < 93) || (tempCounter > 96 && tempCounter < 100)	 
				){}
				else
				minimap.get(Game.playerZ)[x%500 + y*500] = true;
				tempCounter++;
			}
				
	
		
		
		if (chest.containsKey(Entity.chest)){}
		else chest.put(Entity.chest, new InventoryItem[8][10]);
		if (oven.containsKey(Entity.oven)){}
		else oven.put(Entity.oven, new InventoryItem[8][10]);
		if (oven.containsKey(Entity.merchant)){}
		else oven.put(Entity.merchant, new InventoryItem[8][10]);
		if (house.containsKey(Entity.house)){}
		else house.put(Entity.house, new ArrayList<Entity>());
		
		handleInput(input);
		Tile.WATER.animation.update();
		Tile.coolDown-=10;
		//Tile.tiles[0].animation.update();
		//Tile.tiles[1].animation.update();
		//Tile.tiles[5].animation.update();
		
		InventoryItem[] inventoryArmor = this.inventory.getArmor();
		Player.armor = 0;
		Player.armor += 10 * Player.defense;
				
		for(int i=0;i <4; i++){
			if (inventoryArmor[i] != null)
				Player.armor += inventoryArmor[i].getArmor();
		}
		
		if (Player.currentlife <= 0){
			Statistics.deaths += 1;
			gsm.setState(gsm.GAMEOVER);
		}
		
		if(Player.compass == true || mappressed == true) Player.usewhat=Entity.ZERO;
		else if (Player.usewhat==Entity.DOOR_OUTSIDE) gsm.setState(gsm.HOUSE);
		else if (Player.usewhat==Entity.TENT_ENTRANCE) gsm.setState(gsm.HOUSE);
		else if (Player.usewhat==Entity.LADDER){
			if (world.world.size() < Game.playerZ+2) {
				world.generateLayer(Game.playerZ+1);
				entities.add(new ArrayList());
				entities.get(Game.playerZ+1).add(player);
			}
			world.setLadder(Game.playerZ+1);
			//if (!new Ladder_inside(Entity.ladder.x, Entity.ladder.y).validateNextPosition(entities.get(Game.playerZ+1), Entity.ladder.x, Entity.ladder.y))
				entities.get(Game.playerZ+1).add(new Ladder_inside(Entity.ladder.x, Entity.ladder.y));
			Game.playerZ++;
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.LADDER_INSIDE) {
			Game.playerZ--;
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.OVEN){
			OvenState.ovenentered=true;
			isthisaoven=true;
			gsm.setState(gsm.OVEN);
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.FUTURISTIC_OVEN){
			OvenState.ovenentered=true;
			OvenandMerchantuse.futureoven=true;
			isthisaoven=true;
			gsm.setState(gsm.OVEN);
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.CHEST){
			ChestState.chestentered = true;
			gsm.setState(gsm.CHEST);
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.BOSSDOOR_OUTSIDE){
			gsm.setState(gsm.BOSS);
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.SHEEP && Player.isanimalfollowing == false){
			gsm.setState(gsm.DIALOG);
		}
		else if (Player.usewhat==Entity.COW && Player.isanimalfollowing == false) {
			gsm.setState(gsm.DIALOG);
		}	
		else if (Player.usewhat==Entity.MERCHANT){
			OvenState.ovenentered=true;
			isthisaoven=false;
			gsm.setState(gsm.MERCHANT);
			Player.usewhat=Entity.ZERO;
		}	
		else if (Player.usewhat==Entity.NOTPLACEABLE){
			gsm.setState(gsm.DIALOG);
		}	
		else if (Player.usewhat==Entity.NOAMMO){
			gsm.setState(gsm.DIALOG);
		}
		InventoryItem[] inventoryShortCuts = this.inventory.getShortCuts();
		Player.activeItem = (inventoryShortCuts[activeShortCut-1] != null ? inventoryShortCuts[activeShortCut-1].getItem() : null);
		
		for (Entity e : entities.get(Game.playerZ)){
			//if (e instanceof Torch)
				//light.addLight(e);
			//else
			if (Player.compass == true){
				if (e instanceof Bossdoor_outside){
					if ((int)e.y+Bossdoor_outside.HEIGHT/2 == (int)Game.playerY && Game.playerX > e.x) degreered=270;
					else if ((int)e.y+Bossdoor_outside.HEIGHT/2 == (int)Game.playerY && Game.playerX < e.x) degreered=90;
					else if ((int)e.x+Bossdoor_outside.WIDTH/2 == (int)Game.playerX && Game.playerY > e.y) degreered=0;
					else if ((int)e.x+Bossdoor_outside.WIDTH/2== (int)Game.playerX && Game.playerY < e.y) degreered=180;
					else if (Game.playerX < e.x+Bossdoor_outside.WIDTH/2)
						degreered = Math.toDegrees(Math.atan((e.y+Bossdoor_outside.HEIGHT/2-Game.playerY)/(e.x+Bossdoor_outside.WIDTH/2-Game.playerX)))-90-180;
					else if (Game.playerX > e.x+Bossdoor_outside.WIDTH/2)
						degreered = Math.toDegrees(Math.atan((e.y+Bossdoor_outside.HEIGHT/2-Game.playerY)/(e.x+Bossdoor_outside.WIDTH/2-Game.playerX)))-90;
	
				}
				if (e instanceof House){
					houseplaced=true;
					if ((int)e.y+House.HEIGHT/2 == (int)Game.playerY && Game.playerX > e.x) degreeblue=270;
					else if ((int)e.y+House.HEIGHT/2 == (int)Game.playerY && Game.playerX < e.x) degreeblue=90;
					else if ((int)e.x+House.WIDTH/2 == (int)Game.playerX && Game.playerY > e.y) degreeblue=0;
					else if ((int)e.x+House.WIDTH/2== (int)Game.playerX && Game.playerY < e.y) degreeblue=180;
					else if (Game.playerX < e.x+House.WIDTH/2)
						degreeblue = Math.toDegrees(Math.atan((e.y+House.HEIGHT/2-Game.playerY)/(e.x+House.WIDTH/2-Game.playerX)))-90-180;
					else if (Game.playerX > e.x+House.WIDTH/2)
						degreeblue = Math.toDegrees(Math.atan((e.y+House.HEIGHT/2-Game.playerY)/(e.x+House.WIDTH/2-Game.playerX)))-90;
	
				}
			}
			e.update(input, entities.get(Game.playerZ));
		}
		
		for (Entity e : PlayState.addEntity) {
			entities.get(Game.playerZ).add(e);
		}
		addEntity.clear();
		
		
		for (Entity e: PlayState.removeEntity) {
			if (!(e instanceof Item)) {
				//Random rand = new Random();
			    try { 
					Random rand = new Random();
					int plantRand;
					plantRand = rand.nextInt((int)Game.DAY/6);
					Environment environment = null;
					if (!(e instanceof Monster) && !(e instanceof NPC))
						environment = (Environment) e;
			    	if (e instanceof Sapling && environment.remove==true){
			    		int envRand;
						envRand = rand.nextInt(3)+1;
						if (envRand == 1) entities.get(Game.playerZ).add(new Tree_green(environment.x-23, environment.y-60));
						if (envRand == 2) entities.get(Game.playerZ).add(new Tree_purple(environment.x-23, environment.y-60));
						if (envRand == 3) entities.get(Game.playerZ).add(new Tree_blue(environment.x-23, environment.y-60));
						if (envRand == 4) entities.get(Game.playerZ).add(new Tree_red(environment.x-23, environment.y-60));
						environment.remove=false;
						Statistics.plantTrees += 1;
			    	}
			    	else if (e instanceof Potatoe_smal && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Potatoe_growstage2(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Potatoe_growstage2 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Potatoe_growstage3(environment.x, environment.y));
			    		environment.remove=false;
				    	Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Potatoe_growstage3 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Potatoe_grown(environment.x, environment.y));
			    		entities.get(Game.playerZ).add(new Potatoe_grown(environment.x, environment.y));
			    		entities.get(Game.playerZ).add(new Potatoe_grown(environment.x, environment.y));
			    		environment.remove=false;
			    	}
			    	else if (e instanceof Melon_smal && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Melon_growstage2(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Melon_growstage2 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Melon_growstage3(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Melon_growstage3 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Melon_grown(environment.x, environment.y));
			    		environment.remove=false;
			    	}
			    	else if (e instanceof Carrot_smal && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Carrot_growstage2(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Carrot_growstage2 && environment.remove==true){;
			    		entities.get(Game.playerZ).add(new Carrot_growstage3(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Carrot_growstage3 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Carrot_grown(environment.x, environment.y));
			    		entities.get(Game.playerZ).add(new Carrot_grown(environment.x, environment.y));
			    		entities.get(Game.playerZ).add(new Carrot_grown(environment.x, environment.y));
			    		environment.remove=false;
			    	}
			    	else if (e instanceof Wheat_smal && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Wheat_growstage2(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Wheat_growstage2 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Wheat_grown(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Wheat_growstage3 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Wheat_grown(environment.x, environment.y));
			    		environment.remove=false;
			    	}
			    	else if (e instanceof Pumpkin_smal && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Pumpkin_growstage2(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Pumpkin_growstage2 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Pumpkin_growstage3(environment.x, environment.y));
			    		environment.remove=false;
			    		Entity.temptime = Game.TIME+plantRand;
				    	Entity.plantarray.add(Entity.temptime);
				    	Entity.plant.put(Entity.temptime, new Point((int)e.x,(int)e.y));
			    	}
			    	else if (e instanceof Pumpkin_growstage3 && environment.remove==true){
			    		entities.get(Game.playerZ).add(new Pumpkin_grown(environment.x, environment.y));
			    		environment.remove=false;
			    	}
			    	else if (e instanceof Monster) {	
				    	for(Class key : loot.keySet())
				    		if (Rand.weightedRandom(new int[]{100-loot.get(key),loot.get(key)}) == 1)
				    			items.add((Item)key.getConstructors()[0].newInstance(e.getX() + e.width/2-14, e.getY() + e.height/2-14));
				    	
				       	Monster monster = (Monster) e;
				    	for(Class key : monster.loot.keySet())
				    		if (Rand.weightedRandom(new int[]{100-monster.loot.get(key),monster.loot.get(key)}) == 1)
				    			items.add((Item)key.getConstructors()[0].newInstance(e.getX() + e.width/2-14, e.getY() + e.height/2-14));
				    	boolmonster = true;
				    	add();
				    	Statistics.killedMonsters += 1;
			    	}
			    	else if (e instanceof Environment) {
			    		if (e instanceof Tree_green || e instanceof Tree_purple || e instanceof Tree_blue || e instanceof Tree_red) {
			    			Statistics.choppedTrees += 1;
			    			if (Statistics.choppedTrees - Statistics.plantTrees >= Settings.TreeBoss)
			    				System.out.println("B�ser boss Baum");
			    		}
			    		if (e instanceof Potatoe_grown) PlayState.world.world.get(Game.playerZ).put(new Point((int)e.getX()/32-1, (int)e.getY()/32-1), Tile.GRASS.id);	
			    		if (e instanceof Wheat_grown) PlayState.world.world.get(Game.playerZ).put(new Point((int)e.getX()/32-1, (int)e.getY()/32-1), Tile.GRASS.id);
			    		if (e instanceof Melon_grown) PlayState.world.world.get(Game.playerZ).put(new Point((int)e.getX()/32-1, (int)e.getY()/32-1), Tile.GRASS.id);
			    		if (e instanceof Carrot_grown) PlayState.world.world.get(Game.playerZ).put(new Point((int)e.getX()/32-1, (int)e.getY()/32-1), Tile.GRASS.id);
			    		if (e instanceof Pumpkin_grown) PlayState.world.world.get(Game.playerZ).put(new Point((int)e.getX()/32-1, (int)e.getY()/32-1), Tile.GRASS.id);
			    		if (e instanceof Gate && Entity.gateopened == true){
			    			Gate gate = (Gate) e;
			    			Entity.gateopened=false;
			    			gatemap.put(Game.TIME, new Point((int)e.x,(int)e.y));
			    			gatearray.add(Game.TIME);
			    			entities.get(Game.playerZ).add(new Gate_open(e.x, e.y));
			    		} 			
			    		else {
			    			for(Class key : environment.loot.keySet())
			    				if (Rand.weightedRandom(new int[]{100-environment.loot.get(key),environment.loot.get(key)}) == 1)
				    			items.add((Item)key.getConstructors()[0].newInstance(e.getX() + e.width/2-14, e.getY() +e.height/2-14));
			    		}
			    	}
			    	else if (e instanceof NPC) {
			    		NPC npc = (NPC) e;
				    	for(Class key : npc.loot.keySet())
				    		if (Rand.weightedRandom(new int[]{100-npc.loot.get(key),npc.loot.get(key)}) == 1)
				    			items.add((Item)key.getConstructors()[0].newInstance(e.getX() + e.width/2-14, e.getY() + e.height/2-14));			    		
				    	boolmonster = true;
				    	add();
			    	}
			    	
			    } catch (Exception ex) { ex.printStackTrace(); }	
			}
			else 
				PlayState.inventory.addItem((Item)e);
			entities.get(Game.playerZ).remove(e);
		}
		PlayState.removeEntity.clear();
		
		for (Entity e: items) {
			entities.get(Game.playerZ).add(e);
		}
		items.clear();
		//Hintergrundmusik wiederholen
		if(!Music.getBackgroundMusic().isActive()) Music.getGameMusic();
		
		weather.update();
		
	}
	
	public void add(){
		int blobRand;
    	int xRand;
    	int yRand;
    	int eRand;
    	int xdirection;
    	int ydirection;
		Random rand = new Random(); 
		xRand = rand.nextInt(640);
		yRand = rand.nextInt(640);
		xdirection = rand.nextInt(2);
		ydirection = rand.nextInt(2);
		if (xdirection ==0) xRand += (int)(Game.playerX)+420;
		else xRand = (int)(Game.playerX)-xRand-420;
		if (xdirection ==1) yRand += (int)(Game.playerY)+420;
		else yRand = (int)(Game.playerY)-yRand-420;
		blobRand = rand.nextInt(5);
		if (boolmonster == true){
			eRand = rand.nextInt(3);
			if (eRand == 0)
				entities.get(Game.playerZ).add(new Orc(xRand, yRand));
			else if (eRand == 1){
				if(blobRand == 0) entities.get(Game.playerZ).add(new Blob_green(xRand, yRand));
				if(blobRand == 1) entities.get(Game.playerZ).add(new Blob_red(xRand, yRand));
				if(blobRand == 2) entities.get(Game.playerZ).add(new Blob_purple(xRand, yRand));
				if(blobRand == 3) entities.get(Game.playerZ).add(new Blob_blue(xRand, yRand));
				if(blobRand == 4) entities.get(Game.playerZ).add(new Blob_gold(xRand, yRand));
			}
			else if (eRand == 2)
				entities.get(Game.playerZ).add(new Spider(xRand, yRand));
		}
		else {
			eRand = rand.nextInt(2);
			if (eRand == 0) entities.get(Game.playerZ).add(new Sheep(xRand, yRand));
			else if (eRand == 1) entities.get(Game.playerZ).add(new Cow(xRand, yRand));
		}
		if (entities.get(Game.playerZ).get(entities.get(Game.playerZ).size()-1).validateNextPosition(entities.get(Game.playerZ), xRand, yRand)) {
			entities.get(Game.playerZ).remove(entities.get(Game.playerZ).size()-1);
			add();
		}
	}

	public void draw(Graphics2D g) {
		Collections.sort(entities.get(Game.playerZ));
		world.draw(g);
 
		for (Entity e : entities.get(Game.playerZ))
			e.draw(g);
			//if (!(e instanceof Player))
			
		
		
			//uLight.update();
			//g.drawImage(uLight.getrotatedImage(uLight.getImage(0,0), uLight.animation.getFrame()), -69, -133, null);
			
		
		weather.draw(g);
		
		if (Game.playerZ > 0) {
			RadialGradientPaint p = new RadialGradientPaint(new Point(Game.WIDTH/2, Game.HEIGHT/2), 80, new float[]{0.0f, 0.55f, 0.75f, 1.0f}, new Color[]{new Color(0,0,0,0), new Color(0,0,0,165), new Color(0,0,0,180), new Color(0,0,0,255)});
			g.setPaint(p);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setPaint(null);	
		}
		
		//light.update();
		
		Hud.draw(g);

		g.drawString((int)Game.playerX/32 + "|" + (int)Game.playerY/32 + "|" + Game.playerZ, 0, 10);
		g.drawString(""+Game.TIME/1000, 0, 20);
		//Kompass
		if(Player.compass==true){
			g.drawImage(compass.getImage(0,0),Game.WIDTH /2-37-13, Game.HEIGHT/2-33-13, null);
			if(houseplaced == true) g.drawImage(compassneedleblue.getrotatedImage(compassneedleblue.getImage(0,0), (int)degreeblue), Game.WIDTH/2-37, Game.HEIGHT/2-33, null);
			g.drawImage(compassneedlered.getrotatedImage(compassneedlered.getImage(0,0), (int)degreered), Game.WIDTH/2-37, Game.HEIGHT/2-33, null);
		}
				
		//Minimap
		if(mappressed == true){
			HashMap<Point, Integer> layer = world.world.get(Game.playerZ);
			for (int x=(int)Game.playerX/32-25*5; x<(int)Game.playerX/32+25*5; x++) {
				for (int y=(int)Game.playerY/32-25*5; y<(int)Game.playerY/32+25*5; y++) {
					if (x>10 && x<490 && y>10 && y<490)
						g.setColor(Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point(x, y))].color);
					else
						g.setColor(Color.black);

					if (x%500 + y*500 < 0 || x%500 + y*500 >= 250000 || minimap.get(Game.playerZ)[x%500 + y*500] == false)
						g.setColor(Color.black);
					
					g.fillRect((x-(int)Game.playerX/32+25*5)*1+129, (y-(int)Game.playerY/32+25*5)*1+55, 1, 1);
				}
			}
			g.setColor(Color.red);
			g.fillRect(252, 178, 4, 4);
			g.drawImage(map.getImage(0,0),Game.WIDTH /2-150, Game.HEIGHT/2-160, null);
		}
	}
	

	

}
