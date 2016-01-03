package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import Entity.Animation;
import Entity.Entity;
import Entity.Player;
import Entity.Environment.Chest;
import Entity.Environment.Environment;
import Entity.Environment.Hitbox_environment;
import Entity.Environment.Oven;
import Entity.Environment.Potatoe_grown;
import Entity.Environment.Tree_green;
import Entity.Item.Ammonition;
import Entity.Item.Axe_iron;
import Entity.Item.Boat;
import Entity.Item.Chest_leather;
import Entity.Item.Coal;
import Entity.Item.Compass;
import Entity.Item.Fishingrod;
import Entity.Item.Gold;
import Entity.Item.HealthPot;
import Entity.Item.Hoe_iron;
import Entity.Item.House_item;
import Entity.Item.InventoryItem;
import Entity.Item.Iron_ore;
import Entity.Item.Item;
import Entity.Item.Ladder_item;
import Entity.Item.Oven_item;
import Entity.Item.Pickaxe_bronze;
import Entity.Item.Pistol;
import Entity.Item.Sapling_item;
import Entity.Item.Shovel_bronze;
import Entity.Item.Sword_Iron;
import Entity.Monster.Treeguard;
import Entity.NPC.Cow;
import Main.Game;
import Manager.Chestuse;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Hud;
import Manager.InputHandler;
import Manager.Light;
import Manager.Music;
import Manager.OvenandMerchantuse;
import Manager.Rand;
import Manager.Weather;
import World.Spaceship;
import World.Tile.Tile;


public class TutorialState2 extends GameState {

	public static Spaceship spaceship;
	public static Player player;
	public static boolean boolmonster = false;

	private ArrayList<ArrayList<Entity>> entities = new ArrayList<>();
	private Content hud;
	private Content map;
	private Content fairy;
	//private Content uLight;
	public static ArrayList<Entity> removeEntity = new ArrayList<>();
	public static ArrayList<Entity> addEntity = new ArrayList<>();
	public static Chestuse chestuse = new Chestuse();
	public static OvenandMerchantuse ovenuse = new OvenandMerchantuse();
	public static int activeShortCut = 1;
	private Animation fairyAnimation;
	public static ArrayList<Entity> items = new ArrayList<>();
	//public static ArrayList<Class> loot = new ArrayList<Class>(Arrays.asList(Boots_leather.class, Chest_leather.class));
	public static HashMap<Class, Integer> loot = new HashMap<Class, Integer>() {{
		put(Gold.class, 1);
		put(Chest_leather.class, 1);
	}};
	public static HashMap<Point, InventoryItem[][]> chest = new HashMap<Point, InventoryItem[][]>();
	public static HashMap<Point, InventoryItem[][]> oven = new HashMap<Point, InventoryItem[][]>();
	public long timeNow;
	public Weather weather;
	public Light light;
	public static boolean mappressed = false;
	public static Content compass = new Content ("COMPASS", "Resources/HUD/compass.png", 100, 100, new Point(1,1));
	public static Content compassneedlered = new Content ("COMPASSNEEDLE", "Resources/HUD/compass_needle.png", 75, 75, new Point(1,1));
	public static Content compassneedleblue = new Content ("COMPASSNEEDLEBLUE", "Resources/HUD/clock.png", 75, 75, new Point(1,1));
	public static Content background = new Content ("BACKGROUND", "Resources/Intro/background.png", 1000, 1000, new Point(1,1));
	public double degreered = 0;
	public double degreeblue = 0;
	public static boolean houseplaced = false;
	public static boolean isthisaoven = false;
	//public ArrayList<Point> rain = new ArrayList<>();
	
	public static int tutorialnumber = 0;
	public static boolean wasInInventory = false;
	public static boolean wasInStatus = false;
	private Music music;
	private static int zaehler = 1;
	private static int zaehler2 = 0;
	
	public static boolean wasinchest = false;
	public static boolean isback = false;
	private boolean doneonce = false;

	
	public TutorialState2(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		
		entities.add(new ArrayList());
		player = new Player();
		entities.get(Game.playerZ).add(player);
		weather = new Weather();
		light = new Light();
		
		fairyAnimation = new Animation(12, 2);
		spaceship = new Spaceship();

		Random rand = new Random(); 
		
		ArrayList<Point> koordinaten = new ArrayList<>();
		int xRand;
		int yRand;
		int eRand;
		int blobRand;
		int envRand;
        
		hud = new Content("HUD", "Resources/HUD/HUD.png", 512, 80, new Point(1,1));
		fairy = new Content("FAIRY", "Resources/HUD/fairy2.png", 32, 32, new Point(3,4));
		map = new Content("KARTE", "Resources/HUD/frame.png", 300, 300, new Point(1,1));
	
		if(wasinchest == false && wasInInventory == false && wasInStatus == false){
		PlayState.inventory.addItem(new Oven_item(1, 1));
		PlayState.inventory.addItem(new Fishingrod(1, 1));
		PlayState.inventory.addItem(new Coal(1, 1));
		PlayState.inventory.addItem(new Iron_ore(1, 1));
		PlayState.inventory.addItem(new Boat(1, 1));
		PlayState.inventory.addItem(new Hoe_iron(1, 1));
		PlayState.inventory.addItem(new Compass(1, 1));
		PlayState.inventory.addItem(new Axe_iron(1, 1));
		PlayState.inventory.addItem(new Sapling_item(1, 1));
		PlayState.inventory.addItem(new Sword_Iron(1, 1));
		PlayState.inventory.addItem(new Pickaxe_bronze(1,1));
		PlayState.inventory.addItem(new Shovel_bronze(1,1));
	

		//for (int i=0;i<50;i++)
			//inventory.addItem(new Bucket(1,1));
		for (int i=0; i<5;i++)	PlayState.inventory.addItem(new Ladder_item(1, 1));
		PlayState.inventory.addItem(new Pistol(1, 1)); 
		PlayState.inventory.addItem(new House_item(1, 1));
		for (int i=0;i < 99; i++)
			PlayState.inventory.addItem(new Ammonition(1, 1));
		}
		for (double x=241.8; x < 256; x++)
			for (double y=246.3; y< 252; y++)
				if(x==241.8 || y==246.3)
					entities.get(Game.playerZ).add(new Hitbox_environment(x*32, y*32));
		for (double x=240.9; x < 256; x++)
			for (double y=245.9; y< 252; y++)
				if(x==255.9 || y==251.9)
					entities.get(Game.playerZ).add(new Hitbox_environment(x*32, y*32));
		
		
		entities.get(Game.playerZ).add(new Chest(Player.spawnpointX+10, Player.spawnpointY-100));
		entities.get(Game.playerZ).add(new Chest(Player.spawnpointX+40, Player.spawnpointY-100));
		entities.get(Game.playerZ).add(new Oven(Player.spawnpointX-30, Player.spawnpointY-90));


	}

	public void handleInput(InputHandler input) {	
		if (input.isPressed(input.INVENTORY))			gsm.setState(gsm.INVENTORY);
		else if (input.isPressed(input.STATUS))			gsm.setState(gsm.STATUS);
		else if (input.isPressed(input.INGAMEMENUE))	gsm.setState(gsm.INGAMEMENUE);
		else if (input.isPressed(input.TALK)){
			DialogState.yuidialog=true;
			gsm.setState(gsm.DIALOG);
		}
		
		
		if (input.isPressed(input.ONE))	activeShortCut = 1;
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
	
	long alarm_end, alarmplan_end, checklist1_end, checklist2_end, checklist3_end, checklist4_end;
	public void update(InputHandler input) {

		if(zaehler == 1){
			music.getAlertSound();
			alarm_end = System.currentTimeMillis()+(music.clipAlertSound.getMicrosecondLength()/1000);
//			System.out.println("current "+System.currentTimeMillis());
//			System.out.println("clip lenght "+music.clipAlertSound.getMicrosecondLength()/1000);
//			System.out.println("Alarm ende "+alarm_end);
		}
		if (chest.containsKey(Entity.chest)){}
		else chest.put(Entity.chest, new InventoryItem[8][10]);
		if (oven.containsKey(Entity.oven)){}
		else oven.put(Entity.oven, new InventoryItem[8][10]);
		if (oven.containsKey(Entity.merchant)){}
		else oven.put(Entity.merchant, new InventoryItem[8][10]);
		handleInput(input);
		Tile.WATER.animation.update();
		Tile.coolDown-=10;
		//Tile.tiles[0].animation.update();
		//Tile.tiles[1].animation.update();
		//Tile.tiles[5].animation.update();
		
		Player.armor = 0;
		Player.armor += 10 * Player.defense;
				
		if (Player.currentlife <= 0){
			gsm.setState(gsm.GAMEOVER);
		}

		if (Player.usewhat==Entity.OVEN){
			OvenState.ovenentered=true;
			isthisaoven=true;
			gsm.setState(gsm.OVEN);
			Player.usewhat=Entity.ZERO;
		}
		else if (Player.usewhat==Entity.CHEST){
			ChestState.chestentered = true;
			gsm.setState(gsm.CHEST);
			Player.usewhat=Entity.ZERO;
			wasinchest = true;
		}	
		else if (Player.usewhat==Entity.NOTPLACEABLE){
			gsm.setState(gsm.DIALOG);
		}	
		else if (Player.usewhat==Entity.NOAMMO){
			gsm.setState(gsm.DIALOG);
		}
		InventoryItem[] inventoryShortCuts = PlayState.inventory.getShortCuts();
		Player.activeItem = (inventoryShortCuts[activeShortCut-1] != null ? inventoryShortCuts[activeShortCut-1].getItem() : null);
		
		for (Entity e : entities.get(Game.playerZ))
			e.update(input, entities.get(Game.playerZ));
		
		for (Entity e : PlayState.addEntity) {
			entities.get(Game.playerZ).add(e);
		}
		addEntity.clear();
		
		for (Entity e: PlayState.removeEntity) {
			if (!(e instanceof Item)) {
				//Random rand = new Random();
			    try { 
			    	if (e instanceof Environment) {
			    		Environment environment = (Environment) e;
			    		if (e instanceof Potatoe_grown) PlayState.world.world.get(Game.playerZ).put(new Point((int)e.getX()/32-1, (int)e.getY()/32-1), Tile.GRASS.id);	
				    	for(Class key : environment.loot.keySet())
				    		if (Rand.weightedRandom(new int[]{100-environment.loot.get(key),environment.loot.get(key)}) == 1)
				    			items.add((Item)key.getConstructors()[0].newInstance(e.getX() + e.width/2-14, e.getY() +e.height/2-14));			    		
			    	}			    	
			    } catch (Exception ex) { ex.printStackTrace(); }	
			}
			else 
				PlayState.inventory.addItem((Item)e);
			entities.get(Game.playerZ).remove(e);
		}
		PlayState.removeEntity.clear();
		
		if (Player.usewhat==Entity.CHEST){
			ChestState.chestentered = true;
			gsm.setState(gsm.CHEST);
			Player.usewhat=Entity.ZERO;
		}
		for (Entity e: items) {
			entities.get(Game.playerZ).add(e);
		}
		items.clear();
		
		if (zaehler < 1200) zaehler++;
		if(System.currentTimeMillis() > alarm_end && System.currentTimeMillis() < alarm_end+20){
			music.getAlertplanSound();
			alarmplan_end = System.currentTimeMillis()+(music.clipAlertplanSound.getMicrosecondLength()/1000);
		}
		if(System.currentTimeMillis() > alarmplan_end && System.currentTimeMillis() < alarmplan_end+20){
			music.getChecklist1();
			checklist1_end = System.currentTimeMillis()+(music.clipChecklist1.getMicrosecondLength()/1000);
		}
		if(System.currentTimeMillis() > checklist1_end && System.currentTimeMillis() < checklist1_end+20){
			TutorialState.dialogCounter=1;
			gsm.setState(gsm.TUTORIAL);
		}
		if(wasinchest == true && System.currentTimeMillis() > checklist1_end+50 && isback == true){
			isback=false;
			music.getChecklist2();
			checklist2_end = System.currentTimeMillis()+(music.clipChecklist2.getMicrosecondLength()/1000);
		}
		
		//if (zaehler == 1199) music.getAlertplanSound();
		//if (zaehler == 1199 && zaehler2 < 500) zaehler2++;
		//if(music.isPlayingAlertSound()==false) music.getAlertplanSound();
		
		fairyAnimation.update();
		weather.update();
		
	}

	public void draw(Graphics2D g) {
		Collections.sort(entities.get(Game.playerZ));
		spaceship.draw(g);
		
		g.drawImage(background.getImage(0,0), (int)(250*32-16*32 - (Game.playerX - (Spaceship.numTilesWidth-2)/2*32)),(int) (250*32-16*32 - (Game.playerY - (Spaceship.numTilesHeight-2)/2*32)), null);

		for (Entity e : entities.get(Game.playerZ))
			e.draw(g);
		
		weather.draw(g);
		
		Hud.draw(g);

		g.drawString((int)Game.playerX/32 + "|" + (int)Game.playerY/32 + "|" + Game.playerZ, 0, 10);
		g.drawString(""+Game.TIME/1000, 0, 20);
	}
	

	

}
