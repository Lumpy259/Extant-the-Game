package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import Entity.Animation;
import Entity.Entity;
import Entity.Player;
import Entity.Environment.Bed_blue;
import Entity.Environment.Bed_gold;
import Entity.Environment.Bed_green;
import Entity.Environment.Bed_purple;
import Entity.Environment.Bed_red;
import Entity.Environment.Bookshelf;
import Entity.Environment.Chair;
import Entity.Environment.Chest;
import Entity.Environment.Door_inside;
import Entity.Environment.Environment;
import Entity.Environment.Futuristic_oven;
import Entity.Environment.Hitbox_environment;
import Entity.Environment.Ladder;
import Entity.Environment.Oven;
import Entity.Environment.Pumpkin_lantern;
import Entity.Environment.Table;
import Entity.Environment.Tent_exit;
import Entity.Item.InventoryItem;
import Entity.Item.Item;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Hud;
import Manager.InputHandler;
import Manager.Music;
import Manager.OvenandMerchantuse;
import Manager.Rand;
import World.Tile.Tile;

public class HouseState extends GameState {

	ArrayList<Entity> entities = new ArrayList<>();
	public static ArrayList<Entity> removeEntity = new ArrayList<>();
	public static HashMap<Point, InventoryItem[][]> chest = new HashMap<Point, InventoryItem[][]>();
	public static HashMap<Point, InventoryItem[][]> oven = new HashMap<Point, InventoryItem[][]>();
	public static double x;
	public static double y;
	Content hud;
	public static double temp1 = 0;
	public static double temp2 = 0;
	private Animation zapAnimation;
	private Content zap;
	private Animation HitAnimation;
	private Content hit;
	private Content Playerhit, playersleep;
	public static int hitAnimation = 0; //player
	public static int rhitAnimation = 0; //player
	public static Item activeItem;
	public Animation bossAnimation;
	public int BossAnimation = 0;//boss
	public boolean used = false;
	public int flightrotation = 0;
	public static int BowhitAnimation = 0;
	private Content arrow;
	public double flightpathx = 0;
	public double flightpathy = 0;
	public double tempx = 0;
	public double tempy = 0;
	public static boolean bighouseentered = false;
	public static boolean tententered = false;
	int transparency = 0;
	public static int sleepanimation = 0;
	int radius = 0;
	public int hoeAnimation = 0;
	public int axeAnimation = 0;
	public int pickaxeAnimation = 0;
	public int shovelAnimation = 0;
	private Animation HoeAnimation;
	private Content hoehit;
	private Animation AxeAnimation;
	private Content axehit;
	private Animation ShovelAnimation;
	private Content shovelhit;
	private Animation PickaxeAnimation;
	private Content pickaxehit;
	private Content eating;
	private Content drinking;
	public static int eatingtime=0;
	public static int drinkingtime=0;
	
	public HouseState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		temp1 = Game.playerX;
		temp2 = Game.playerY;
		Game.playerX = Game.WIDTH/2-8;
		Game.playerY = +32;
		x = Game.WIDTH/2-8;
		y = +32;
		PlayState.player.direction = 0;
		Player.activeItem = null; 
		eating = new Content("EATING", "Resources/Sprites/eating.png", 16, 32, new Point(1,4));
		drinking = new Content("DRINKING", "Resources/Sprites/drinking.png", 16, 32, new Point(1,4));
		hud = new Content ("HUD", "Resources/HUD/HUD.png", 512, 80, new Point(1,1));
		zapAnimation = new Animation(10, 3);
		zap = new Content("ZAP", "Resources/Animations/Zapanimation.png", 160, 160, new Point(3,1));
		HitAnimation = new Animation(5, 3);
		hit = new Content("HIT", "Resources/Animations/swordanimation.png", 48, 60, new Point(3,4));
		Playerhit = new Content("PLAYERHIT", "Resources/Animations/attack.png",16, 32, new Point(1,4));
		HoeAnimation = new Animation(5, 3);
		hoehit = new Content("HIT", "Resources/Animations/hoeanimation.png", 48, 60, new Point(3,4));
		AxeAnimation = new Animation(5, 3);
		axehit = new Content("HIT", "Resources/Animations/axeanimation.png", 48, 60, new Point(3,4));
		PickaxeAnimation = new Animation(5, 3);
		pickaxehit = new Content("HIT", "Resources/Animations/pickaxeanimation.png", 48, 60, new Point(3,4));
		ShovelAnimation = new Animation(5, 3);
		shovelhit = new Content("HIT", "Resources/Animations/shovelanimation.png", 48, 60, new Point(3,4));
		arrow = new Content("SCHIESEN", "Resources/Items/arrow.png", 28, 28, new Point(1,1));
		playersleep = new Content("SCHLAFEN", "Resources/Sprites/sleeping.png", 14, 15, new Point(1,1));
		}
	public static void initentered() {
		if (GameStateManager.getPrevState() == 1){
			temp1 = Game.playerX;
			temp2 = Game.playerY;
			Game.playerX = Game.WIDTH/2-8;
			Game.playerY = +32;
			x = Game.WIDTH/2-8;
			y = +32;
			PlayState.player.direction = 0;
			Player.activeItem = null; 		
		}
	}
	public void update(InputHandler input) {
		if (Player.usewhat==Entity.DOOR_OUTSIDE){
			entities = PlayState.house.get(Entity.house);
			entities.add(PlayState.player);
			entities.add(new Door_inside(Game.WIDTH /2 -18, -3));
			for (int x=0; x < Game.WIDTH / 32; x++)
				for (int y=0; y< Game.HEIGHT / 32; y++)
					if(x==0 || x==Game.WIDTH / 32-1 || y==0 || y==Game.HEIGHT /32-2)
						entities.add(new Hitbox_environment(x*32, y*32));
			Player.usewhat=Entity.ZERO;
		}
		if (Player.usewhat==Entity.TENT_ENTRANCE){
			entities = PlayState.house.get(Entity.house);
			entities.add(PlayState.player);
			entities.add(new Tent_exit(Game.WIDTH /2 -18, 5));
			for (int x=4; x < Game.WIDTH / 32 -4; x++)
				for (int y=0; y< Game.HEIGHT / 32-4; y++)
					if(x==4 || x==Game.WIDTH / 32-1-4 || y==0 || y==Game.HEIGHT /32-2-4)
						entities.add(new Hitbox_environment(x*32, y*32));
			Player.usewhat=Entity.ZERO;
		}
		
		Game.playerX = x;
		Game.playerY = y;
		
		if (Player.usewhat==Entity.OVEN){
			OvenState.ovenentered=true;
			PlayState.isthisaoven=true;
			gsm.setState(gsm.OVEN);
			Player.usewhat=Entity.ZERO;
		}
		if (Player.usewhat==Entity.FUTURISTIC_OVEN){
			OvenState.ovenentered=true;
			OvenandMerchantuse.futureoven=true;
			PlayState.isthisaoven=true;
			gsm.setState(gsm.OVEN);
			Player.usewhat=Entity.ZERO;
		}
		
		if (Player.usewhat==Entity.CHEST){
			ChestState.chestentered = true;
			gsm.setState(gsm.CHEST);
			Player.usewhat=Entity.ZERO;
		}
		if (Player.usewhat==Entity.BED){
			if (!(Manager.Weather.timeNow <= 0.6*Game.DAY)){
				Game.TIME += Game.DAY - Game.getremainingtime();
				sleepanimation=300;
				radius=100;
				transparency = 0;
				Player.usewhat=Entity.ZERO;
			}
			else gsm.setState(gsm.DIALOG);
		}
		if (Player.usewhat==Entity.NOAMMO){
			gsm.setState(gsm.DIALOG);
		}
		if (input.isPressed(input.INVENTORY))			gsm.setState(gsm.INVENTORY);
		else if (input.isPressed(input.STATUS))			gsm.setState(gsm.STATUS);
		else if (input.isPressed(input.INGAMEMENUE))	gsm.setState(gsm.INGAMEMENUE);
		else if (input.isPressed(input.TALK)){
			DialogState.yuidialog=true;
			gsm.setState(gsm.DIALOG);
		}
		for (Entity e : entities){
			e.update(input, entities);
		}
		if (input.isPressed(input.SPACE) && activeItem != null && activeItem.getType() == Item.FLASK){
			if (PlayState.player.currentlife < PlayState.player.maxlife){	
				drinkingtime=60;
				PlayState.player.healing = 25;
				if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getCount() <= 1)
					PlayState.inventory.removeItem(new Point (PlayState.activeShortCut-1, 0));
				else PlayState.inventory.inventory[0][PlayState.activeShortCut-1].setCount(-1);
			}
		}
		if (input.isPressed(input.SPACE) && activeItem != null && activeItem.getType() == Item.CONSUMABLE){
			if (PlayState.player.currentlife < PlayState.player.maxlife){	
				Music.getPowerupSound();
				eatingtime=60;
				PlayState.player.healing = 12;
				//essanimation fehlt noch + animation.setFreeze(false); während animation
				if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getCount() <=  1)
					PlayState.inventory.removeItem(new Point (PlayState.activeShortCut-1, 0));
				else PlayState.inventory.inventory[0][PlayState.activeShortCut-1].setCount(-1);
			}
		}
		if (PlayState.player.healing != 0) {
			PlayState.player.currentlife += 1;
			PlayState.player.healing--;
			if (PlayState.player.currentlife > PlayState.player.maxlife) PlayState.player.currentlife = PlayState.player.maxlife;
		}
		
		eatingtime -= (eatingtime > 0 ? 1 : 0);
		drinkingtime -= (drinkingtime > 0 ? 1 : 0);
		rhitAnimation -= (rhitAnimation > 0 ? 1 : 0);
		hitAnimation -= (hitAnimation > 0 ? 1 : 0);
		BowhitAnimation -= (BowhitAnimation  > 0 ? 1 : 0);
		sleepanimation -= (sleepanimation  > 0 ? 1 : 0);
		hoeAnimation -= (hoeAnimation > 0 ? 1 : 0);
		axeAnimation -= (axeAnimation > 0 ? 1 : 0);
		shovelAnimation -= (shovelAnimation > 0 ? 1 : 0);
		pickaxeAnimation -= (pickaxeAnimation > 0 ? 1 : 0);

		if (hitAnimation != 0){
			if (PlayState.player.direction == 0)
				PlayState.player.validateAttack(entities, Game.playerX, Game.playerY + 38, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 1)
				PlayState.player.validateAttack(entities, Game.playerX, Game.playerY - 33, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 2)
				PlayState.player.validateAttack(entities, Game.playerX+21, Game.playerY, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 3)
				PlayState.player.validateAttack(entities, Game.playerX-21, Game.playerY, Game.playerX, Game.playerY);
		}	
		
		if (input.isPressed(input.SPACE) && rhitAnimation == 0 && activeItem != null && activeItem.getType() == Item.RWEAPON && activeItem.getName().equals("Pistole")){
			//Music.getPlayerHitSound();
			if (PlayState.inventory.searchforItem("Munition") == null){Player.usewhat = 22;}
			else{
				rhitAnimation = 30;
				if (PlayState.inventory.searchforItem("Munition").getCount()==1)
					PlayState.inventory.removeItem(PlayState.inventory.searchforPoint("Munition"));
				else PlayState.inventory.searchforItem("Munition").setCount(-1);
			}
		}
		else if (input.isPressed(input.SPACE) && rhitAnimation == 0 && activeItem != null && (activeItem.getType() == Item.RWEAPON) && activeItem.getName().equals("Bogen") && used == false){
			Music.getLaserWeaponSound();
			if (PlayState.inventory.searchforItem("Pfeile") == null){Player.usewhat = 22;}
			else{
				BowhitAnimation = 50;
				if (used == false){
					if (PlayState.player.direction == 0) flightrotation = 180;
					else if (PlayState.player.direction == 1)flightrotation = 0;
					else if (PlayState.player.direction == 2) flightrotation = 90;
					else if (PlayState.player.direction == 3) flightrotation = 270;
					flightpathy = y-16;
					flightpathx = x-8;
					tempx= x-16;
					tempy= y-8;
					used = true;
				}	
				if (PlayState.inventory.searchforItem("Pfeile").getCount()==1)
					PlayState.inventory.removeItem(PlayState.inventory.searchforPoint("Pfeile"));
				else PlayState.inventory.searchforItem("Pfeile").setCount(-1);
			}
		}
		else if (input.isPressed(input.SPACE) && hitAnimation == 0 && rhitAnimation == 0 && activeItem != null && (activeItem.getType() == Item.HOE || activeItem.getType() == Item.AXE || activeItem.getType() == Item.SHOVEL || activeItem.getType() == Item.PICKAXE || activeItem.getType() == Item.WEAPON)){
			Music.getPlayerHitSound();
			//if (activeItem != null && activeItem.getType() == Item.WEAPON)
			if (activeItem.getType() == Item.WEAPON) hitAnimation = 16;
			if (activeItem.getType() == Item.AXE) axeAnimation = 16;
			if (activeItem.getType() == Item.SHOVEL) shovelAnimation = 16;
			if (activeItem.getType() == Item.PICKAXE) pickaxeAnimation = 16;
			if (activeItem.getType() == Item.HOE) hoeAnimation = 16;
			if (PlayState.player.direction == 0)
				PlayState.player.validateAttack(entities, Game.playerX, Game.playerY + 38, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 1)
				PlayState.player.validateAttack(entities, Game.playerX, Game.playerY - 33, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 2)
				PlayState.player.validateAttack(entities, Game.playerX+21, Game.playerY, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 3)
				PlayState.player.validateAttack(entities, Game.playerX-21, Game.playerY, Game.playerX, Game.playerY);
		}
		if (input.isPressed(input.SPACE) && hitAnimation == 0 && BossState.hitAnimation == 0 && rhitAnimation == 0 && activeItem != null) {
			if (activeItem.getType() == Item.PLACEABLE)
				placeEntity(entities);
		}
		if (BowhitAnimation != 0){
			if (flightrotation == 0){
				flightpathy-=5;
				tempy-=5;
			}
			else if (flightrotation == 180){
				flightpathy+=5;
				tempy+=5;
			}
			else if (flightrotation == 90) {
				flightpathx+=5;
				tempx+=5;
			}
			else if (flightrotation == 270){
				flightpathx-=5;
				tempx-=5;
			}
		}
		else used = false;
		
		if (input.isPressed(input.ONE)){
			PlayState.activeShortCut = 1;
			Player.activeItem = null;
		}
		if (input.isPressed(input.TWO)){
			PlayState.activeShortCut = 2;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.THREE)){
			PlayState.activeShortCut = 3;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.FOUR)){
			PlayState.activeShortCut = 4;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.FIVE)){
			PlayState.activeShortCut = 5;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.SIX)){
			PlayState.activeShortCut = 6;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.SEVEN)){
			PlayState.activeShortCut = 7;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.EIGHT)){
			PlayState.activeShortCut = 8;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.NINE)){
			PlayState.activeShortCut = 9;
			Player.activeItem = null; 
		}
		if (input.isPressed(input.ZERO)){
			PlayState.activeShortCut = 10;
			Player.activeItem = null; 
		}
		
		InventoryItem[] inventoryShortCuts = PlayState.inventory.getShortCuts();
		activeItem = (inventoryShortCuts[PlayState.activeShortCut-1] != null ? inventoryShortCuts[PlayState.activeShortCut-1].getItem() : null);
		
		if (input.isPressed(input.USE)){
			if (PlayState.player.direction == 0)  Player.usewhat = PlayState.player.validateUse(entities, Game.playerX, Game.playerY + 38, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 1) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX, Game.playerY - 33, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 2) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX+21, Game.playerY, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 3) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX-21, Game.playerY, Game.playerX, Game.playerY);			
		}

		PlayState.player.animation.setFreeze(false);
		
		if (Player.usewhat==Entity.DOOR_INSIDE || Player.usewhat==Entity.TENT_EXIT){
			Game.playerX = temp1;
			Game.playerY = temp2+3; 
			Player.direction = 0;
			tententered = false;
			bighouseentered = false;
			gsm.setState(gsm.PLAY);
		}
		else if (eatingtime != 0 || drinkingtime != 0 || PlayState.player.rhitAnimation != 0 || hoeAnimation != 0 || pickaxeAnimation != 0 || shovelAnimation != 0|| axeAnimation != 0 || PlayState.player.hitAnimation != 0 || BossState.bossrhitAnimation != 0 || BossState.hitAnimation != 0 || HouseState.sleepanimation != 0) {
			PlayState.player.animation.setFreeze(true);
		}
		else if (input.keyState[input.UP] && input.keyState[input.RIGHT]) { 
			if(!PlayState.player.validateNextPosition(entities, x+0.707 * PlayState.player.moveSpeed, y-0.707*PlayState.player.moveSpeed)) {
				x += 0.707 * PlayState.player.moveSpeed; 
				y -= 0.707 * PlayState.player.moveSpeed; 
				PlayState.player.direction = 1;
			}
		}
		else if (input.keyState[input.UP] && input.keyState[input.LEFT]) { 
			if(!PlayState.player.validateNextPosition(entities, x-0.707 *PlayState.player.moveSpeed, y-0.707*PlayState.player.moveSpeed)) {
				x -= 0.707 * PlayState.player.moveSpeed; 
				y -= 0.707 * PlayState.player.moveSpeed;
				PlayState.player.direction = 1;
			}
		}
		else if (input.keyState[input.DOWN] && input.keyState[input.RIGHT]) { 
			if(!PlayState.player.validateNextPosition(entities, x+0.707 * PlayState.player.moveSpeed, y+0.707*PlayState.player.moveSpeed)) {
				x += 0.707 * PlayState.player.moveSpeed; 
				y += 0.707 * PlayState.player.moveSpeed; 
				PlayState.player.direction = 0;
			}
		}
		else if (input.keyState[input.DOWN] && input.keyState[input.LEFT]) { 
			if(!PlayState.player.validateNextPosition(entities, x-0.707 * PlayState.player.moveSpeed, y+0.707*PlayState.player.moveSpeed)) {
				x -= 0.707 * PlayState.player.moveSpeed; 
				y += 0.707 * PlayState.player.moveSpeed;
				PlayState.player.direction = 0;
			}
		}
		else if (input.keyState[input.UP] && GameStateManager.currentState == GameStateManager.HOUSE) { 
			if(!PlayState.player.validateNextPosition(entities, x, y-PlayState.player.moveSpeed)) {
				y -= PlayState.player.moveSpeed;
				PlayState.player.direction = 1;
			} 
		}
		else if (input.keyState[input.DOWN]) { 
			if(!PlayState.player.validateNextPosition(entities, x, y+PlayState.player.moveSpeed)) {
				y += PlayState.player.moveSpeed; 
				PlayState.player.direction = 0; 
			}
		}
		else if (input.keyState[input.LEFT]) { 
			if(!PlayState.player.validateNextPosition(entities, x-PlayState.player.moveSpeed, y)) {
				x -= PlayState.player.moveSpeed; 
				PlayState.player.direction = 3; 
			}
		}
		
		else if (input.keyState[input.RIGHT]) { 
			if(!PlayState.player.validateNextPosition(entities, x+PlayState.player.moveSpeed, y)) {
				x += PlayState.player.moveSpeed; 
				PlayState.player.direction = 2; 
			}
		}
		else 
			PlayState.player.animation.setFreeze(true);
		
		PlayState.player.animation.update();
		
		for (Entity e : PlayState.addEntity) {
			entities.add(e);
		}
		PlayState.addEntity.clear();
		ArrayList<Entity> items = new ArrayList<>();
		
		for (Entity e: PlayState.removeEntity) {
			if (!(e instanceof Item)) {
				//Random rand = new Random();
			    try { 
			    	if (e instanceof Environment) {
			    		int abc = 0;
			    		Environment environment = (Environment) e;
			    		
				    	for(Class key : environment.loot.keySet()) 
				    		if (Rand.weightedRandom(new int[]{100-environment.loot.get(key),environment.loot.get(key)}) == 1) 
				    			items.add((Item)key.getConstructors()[0].newInstance(e.getX(), e.getY()));			  
			    	}			    	
			    } catch (Exception ex) { ex.printStackTrace(); }	
			}
			else
				PlayState.inventory.addItem((Item)e);
			entities.remove(e);
		}
		PlayState.removeEntity.clear();
		
		for (Entity e: items) {
			entities.add(e);
		}
		items.clear();
	}
	public void placethisEntity(ArrayList<Entity> entities, String itemname, double xu, double yu, double yo, double xl, double xr, double yl){
		if (PlayState.player.direction == 0) {
			if (!PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getItem().validateNextPosition(entities, Game.playerX+xu, Game.playerY+yu)) {
				if(itemname == "Ofen") entities.add(new Oven(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Industrieofen") entities.add(new Futuristic_oven(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Leiter") entities.add(new Ladder(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Stuhl") entities.add(new Chair(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Bücherregal") entities.add(new Bookshelf(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Truhe") entities.add(new Chest(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Bett grün") entities.add(new Bed_green(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Bett lila") entities.add(new Bed_purple(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Bett gold") entities.add(new Bed_gold(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Bett rot") entities.add(new Bed_red(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Bett blau") entities.add(new Bed_blue(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Kürbislaterne") entities.add(new Pumpkin_lantern(Game.playerX +xu, Game.playerY +yu));
				if(itemname == "Tisch") entities.add(new Table(Game.playerX +xu, Game.playerY +yu));
				if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getCount() <=  1)
					PlayState.inventory.removeItem(new Point (PlayState.activeShortCut-1, 0));
				else 
					PlayState.inventory.inventory[0][PlayState.activeShortCut-1].setCount(-1);
			}
		}
		else if (PlayState.player.direction == 1) {
			if (!PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getItem().validateNextPosition(entities, Game.playerX+xu, Game.playerY+yo)) {
				if(itemname == "Ofen") entities.add(new Oven(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Industrieofen") entities.add(new Futuristic_oven(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Leiter") entities.add(new Ladder(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Stuhl") entities.add(new Chair(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Bücherregal") entities.add(new Bookshelf(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Truhe") entities.add(new Chest(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Bett blau") entities.add(new Bed_blue(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Bett lila") entities.add(new Bed_purple(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Bett rot") entities.add(new Bed_red(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Bett gold") entities.add(new Bed_gold(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Bett grün") entities.add(new Bed_green(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Kürbislaterne") entities.add(new Pumpkin_lantern(Game.playerX +xu, Game.playerY +yo));
				if(itemname == "Tisch") entities.add(new Table(Game.playerX +xu, Game.playerY +yo));
				if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getCount() <=  1)
					PlayState.inventory.removeItem(new Point (PlayState.activeShortCut-1, 0));
				else 
					PlayState.inventory.inventory[0][PlayState.activeShortCut-1].setCount(-1);
			}
		}
		else if (PlayState.player.direction == 2) {
			if (!PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getItem().validateNextPosition(entities, Game.playerX+xl, Game.playerY+yl)) {
				if(itemname == "Ofen") entities.add(new Oven(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Industrieofen") entities.add(new Futuristic_oven(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Leiter") entities.add(new Ladder(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Stuhl") entities.add(new Chair(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Bücherregal") entities.add(new Bookshelf(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Truhe") entities.add(new Chest(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Bett grün") entities.add(new Bed_green(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Bett lila") entities.add(new Bed_purple(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Bett blau") entities.add(new Bed_blue(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Bett gold") entities.add(new Bed_gold(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Bett rot") entities.add(new Bed_red(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Kürbislaterne") entities.add(new Pumpkin_lantern(Game.playerX +xl, Game.playerY +yl));
				if(itemname == "Tisch") entities.add(new Table(Game.playerX +xl, Game.playerY +yl));
				if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getCount() <=  1)
					PlayState.inventory.removeItem(new Point (PlayState.activeShortCut-1, 0));
				else 
					PlayState.inventory.inventory[0][PlayState.activeShortCut-1].setCount(-1);
			}
		}
		else if (PlayState.player.direction == 3) {
			if (!PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getItem().validateNextPosition(entities, Game.playerX+xr, Game.playerY+yl)) {
				if(itemname == "Ofen") entities.add(new Oven(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Industrieofen") entities.add(new Futuristic_oven(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Leiter") entities.add(new Ladder(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Stuhl") entities.add(new Chair(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Bücherregal") entities.add(new Bookshelf(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Truhe") entities.add(new Chest(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Bett grün") entities.add(new Bed_green(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Bett rot") entities.add(new Bed_red(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Bett lila") entities.add(new Bed_purple(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Bett blau") entities.add(new Bed_blue(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Bett gold") entities.add(new Bed_gold(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Kürbislaterne") entities.add(new Pumpkin_lantern(Game.playerX +xr, Game.playerY +yl));
				if(itemname == "Tisch") entities.add(new Table(Game.playerX +xr, Game.playerY +yl));
				if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getCount() <=  1)
					PlayState.inventory.removeItem(new Point (PlayState.activeShortCut-1, 0));
				else 
					PlayState.inventory.inventory[0][PlayState.activeShortCut-1].setCount(-1);
			}
		}
	}
	public void placeEntity(ArrayList<Entity> entities) {
		//xu,yu,yo,xl,xr,yl
		if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1] == null)
			return;
		if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Zaun") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Tor") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Melonen Saamen") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Saamen") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Kürbis Saamen") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Karotte") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Kartoffel") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Leiter") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Haus") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Kartoffel") || 
				PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Steckling Baum")){}
		//else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Leiter")){
			//placethisEntity(entities, "Leiter", -14,16,-30,10,-46,-12);
		//}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Ofen")){
			placethisEntity(entities, "Ofen", -16,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Industrieofen")){
			placethisEntity(entities, "Industrieofen", -16,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Kürbislaterne Item")){
			placethisEntity(entities, "Kürbislaterne", -16,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Tisch")){
			placethisEntity(entities, "Tisch", -12,16,-25,10,-36,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Stuhl")){
			placethisEntity(entities, "Stuhl", -8,16,-15,12,-26,0);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Bücherregal")){
			placethisEntity(entities, "Bücherregal", -21,16,-40, 18,-61,-18);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Bett grün")){
			placethisEntity(entities, "Bett grün", -14,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Bett gold")){
			placethisEntity(entities, "Bett gold", -14,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Bett rot")){
			placethisEntity(entities, "Bett rot", -14,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Bett lila")){
			placethisEntity(entities, "Bett lila", -14,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Bett blau")){
			placethisEntity(entities, "Bett blau", -14,16,-40,10,-46,-12);
		}
		else if (PlayState.inventory.inventory[0][PlayState.activeShortCut-1].getName().equals("Truhe")){
			placethisEntity(entities, "Truhe", -16,16,-40,10,-46,-12);
			
		}
		//xu,yu,yo,xr,xl,yl
	}
	
	
	public void draw(Graphics2D g) {
		Collections.sort(entities);
		int tile = 3;
		
		if(bighouseentered == true){
			for (int x=0; x < Game.WIDTH / 32; x++){
				for (int y=0; y< Game.HEIGHT / 32; y++) {
					if(x==0 && y==0) {
						tile = 19;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 0), x*32, y*32, null);
					}
					else if (x==Game.WIDTH / 32-1 && y==0){
						tile = 19;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 90), x*32, y*32, null);
					}
					else if (x==0 && y==Game.HEIGHT /32-2){
						tile = 19;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 270), x*32, y*32, null);
					}
					else if (x==Game.WIDTH / 32-1 && y==Game.HEIGHT /32-2){
						tile = 19;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 180), x*32, y*32, null);
					}
					else if(x>0 && !(x==Game.WIDTH / 32-1) && y==0) {
						tile = 18;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 90), x*32, y*32, null);
					}
					else if (x==Game.WIDTH / 32-1 && y>0 && !(y>=Game.HEIGHT /32-2)){
						tile = 18;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 180), x*32, y*32, null);
					}
					else if (x>0 && y==Game.HEIGHT /32-2 && !(x>=Game.WIDTH /32-1)){
						tile = 18;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 270), x*32, y*32, null);
					}
					else if (x==0 && y>0 && !(y>=Game.HEIGHT /32-2)){
						tile = 18;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 0), x*32, y*32, null);
					}
					else 	{				
						tile = 20;
						g.drawImage(Tile.tiles[tile].getImage(), x*32, y*32, null);
					}
				}
			}
		g.setColor(Color.BLACK);
		g.fillRect(0, 352, 512, 32);
		}
		if(tententered == true){
			for (int x=4; x < Game.WIDTH / 32-4; x++){
				for (int y=0; y< Game.HEIGHT / 32-5; y++) {
					if(x==4 && y==0) {
						tile = 42;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 0), x*32, y*32, null);
					}
					else if (x==Game.WIDTH / 32-1-4 && y==0){
						tile = 42;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 90), x*32, y*32, null);
					}
					else if (x==4 && y==Game.HEIGHT /32-2-4){
						tile = 42;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 270), x*32, y*32, null);
					}
					else if (x==Game.WIDTH / 32-1-4 && y==Game.HEIGHT /32-2-4){
						tile = 42;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 180), x*32, y*32, null);
					}
					else if(x>4 && !(x==Game.WIDTH / 32-1-4) && y==0) {
						tile = 41;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 90), x*32, y*32, null);
					}
					else if (x==Game.WIDTH / 32-1-4 && y>0 && !(y>=Game.HEIGHT /32-2-4)){
						tile = 41;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 180), x*32, y*32, null);
					}
					else if (x>0 && y==Game.HEIGHT /32-2-4 && !(x>=Game.WIDTH /32-1-4)){
						tile = 41;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 270), x*32, y*32, null);
					}
					else if (x==4 && y>0 && !(y>=Game.HEIGHT /32-2-4)){
						tile = 41;
						g.drawImage(Tile.tiles[tile].getrotatedImage(Tile.tiles[tile].getImage(0,0), 0), x*32, y*32, null);
					}
					else 	{				
						tile = 43;
						g.drawImage(Tile.tiles[tile].getImage(), x*32, y*32, null);
					}
				}
			}
			g.setColor(Color.BLACK);
			g.fillRect(0, 352-4*32, 512, 5*32);
			g.fillRect(0, 0, 4*32, 7*32);
			g.fillRect(12*32, 0, 4*32, 7*32);
		}
		for (Entity e : entities){
			if(!(e instanceof Player)) e.draw(g);
		}
		int x2 = 0;
		int y2 = 0;
		
		if (eatingtime > 0){
			g.drawImage(eating.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
		}
		else if (drinkingtime > 0){
			g.drawImage(drinking.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
		}
		else if (sleepanimation > 0){
			if (sleepanimation > 200 && sleepanimation < 300){
				transparency+=2.8;
				if (!(radius ==10)) radius--;
			}
			else if (sleepanimation > 100 && sleepanimation < 200){}
			else if (sleepanimation > 2){
				transparency-=2;
				radius++;
			}
			g.drawImage(playersleep.getImage(0,0), (int)Entity.bed.getX()+6, (int)Entity.bed.getY()+2, null);
			RadialGradientPaint p = new RadialGradientPaint(new Point((int) Entity.bed.getX()+12, (int) Entity.bed.getY()+12), radius, new float[]{0.0f, 0.55f, 0.75f, 1.0f}, new Color[]{new Color(0,0,0,0), new Color(0,0,0,(transparency-90 > 0 ? (transparency-90) : 0)), new Color(0,0,0,(transparency-45 > 0 ? (transparency-45) : 0)), new Color(0,0,0,transparency)});
			g.setPaint(p);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setPaint(null);
			PlayState.player.direction = 0;
		}
		else if (hitAnimation > 0) {	
			if (PlayState.player.direction == 0){
				x2 = -16;
				y2 = -16;
				HitAnimation.update();
			}
			else if (PlayState.player.direction == 1){
				x2 = -16;
				y2 = -16;
				HitAnimation.update();
			}
			else if (PlayState.player.direction == 2){
				x2 = -16;
				y2 = -16;
				HitAnimation.update();
			}
			else if (PlayState.player.direction == 3){
				x2 = -16;
				y2 = -16;
				HitAnimation.update();
			}
			g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
			g.drawImage(hit.getImage(PlayState.player.direction,HitAnimation.getFrame()), (int)x-8+x2, (int)y-16+y2, null);
		}
		else if (hoeAnimation > 0) {	
			if (PlayState.player.direction == 0){
				x2 = -16;
				y2 = -16;
				HoeAnimation.update();
			}
			else if (PlayState.player.direction == 1){
				x2 = -16;
				y2 = -16;
				HoeAnimation.update();
			}
			else if (PlayState.player.direction == 2){
				x2 = -16;
				y2 = -16;;
				HoeAnimation.update();
			}
			else if (PlayState.player.direction == 3){
				x2 = -16;
				y2 = -16;
				HoeAnimation.update();
			}
			g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
			g.drawImage(hoehit.getImage(PlayState.player.direction,HoeAnimation.getFrame()), (int)x-8+x2, (int)y-16+y2, null);
		}
		else if (axeAnimation > 0) {	
			if (PlayState.player.direction == 0){
				x2 = -16;
				y2 = -16;
				AxeAnimation.update();
			}
			else if (PlayState.player.direction == 1){
				x2 = -16;
				y2 = -16;
				AxeAnimation.update();
			}
			else if (PlayState.player.direction == 2){
				x2 = -16;
				y2 = -16;
				AxeAnimation.update();
			}
			else if (PlayState.player.direction == 3){
				x2 = -16;
				y2 = -16;
				AxeAnimation.update();
			}
			g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
			g.drawImage(axehit.getImage(PlayState.player.direction,AxeAnimation.getFrame()), (int)x-8+x2, (int)y-16+y2, null);
		}
		else if (pickaxeAnimation > 0) {	
			if (PlayState.player.direction == 0){
				x2 = -16;
				y2 = -16;
				PickaxeAnimation.update();
			}
			else if (PlayState.player.direction == 1){
				x2 = -16;
				y2 = -16;
				PickaxeAnimation.update();
			}
			else if (PlayState.player.direction == 2){
				x2 = -16;
				y2 = -16;
				PickaxeAnimation.update();
			}
			else if (PlayState.player.direction == 3){
				x2 = -16;
				y2 = -16;
				PickaxeAnimation.update();
			}
			g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
			g.drawImage(pickaxehit.getImage(PlayState.player.direction,PickaxeAnimation.getFrame()), (int)x-8+x2, (int)y-16+y2, null);
		}
		else if (shovelAnimation > 0) {	
			if (PlayState.player.direction == 0){
				x2 = -16;
				y2 = -16;
				ShovelAnimation.update();
			}
			else if (PlayState.player.direction == 1){
				x2 = -16;
				y2 = -16;
				ShovelAnimation.update();
			}
			else if (PlayState.player.direction == 2){
				x2 = -16;
				y2 = -16;
				ShovelAnimation.update();
			}
			else if (PlayState.player.direction == 3){
				x2 = -16;
				y2 = -16;
				ShovelAnimation.update();
			}
			g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
			g.drawImage(shovelhit.getImage(PlayState.player.direction,ShovelAnimation.getFrame()), (int)x-8+x2, (int)y-16+y2, null);
		}
		else if (rhitAnimation > 0) {			
			if (PlayState.player.direction == 0){
				y2 = 32;
				x2 = -72;
				zapAnimation.update();
				g.drawImage(zap.getrotatedImage(zap.getImage(0,zapAnimation.getFrame()), 180), (int)x-8+x2, (int)y-16+y2, null);
			}
			else if (PlayState.player.direction == 1){
				y2 = -162;
				x2 = -72;
				zapAnimation.update();
				g.drawImage(zap.getrotatedImage(zap.getImage(0,zapAnimation.getFrame()), 0), (int)x-8+x2, (int)y-16+y2, null);
			}
			else if (PlayState.player.direction == 2){
				x2 = 16;
				y2 = -64;	
				zapAnimation.update();
				g.drawImage(zap.getrotatedImage(zap.getImage(0,zapAnimation.getFrame()), 90), (int)x-8+x2, (int)y-16+y2, null);
			}
			else if (PlayState.player.direction == 3){
				x2 = -160;
				y2 = -64;
				zapAnimation.update();
				g.drawImage(zap.getrotatedImage(zap.getImage(0,zapAnimation.getFrame()), 270), (int)x-8+x2, (int)y-16+y2, null);
			}
			g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);	
			//ourl
		}
		else if (BowhitAnimation > 0) {
			if (flightrotation == 0){
				g.drawImage(arrow.getrotatedImage(arrow.getImage(0,0), flightrotation-45), (int)flightpathx,(int)flightpathy, null);
			}
			else if (flightrotation == 180){
				g.drawImage(arrow.getrotatedImage(arrow.getImage(0,0), flightrotation-45), (int)flightpathx,(int)flightpathy, null);
			}
			else if (flightrotation == 90){
				g.drawImage(arrow.getrotatedImage(arrow.getImage(0,0), flightrotation-45), (int)flightpathx,(int)flightpathy, null);
			}
			else if (flightrotation == 270){
				g.drawImage(arrow.getrotatedImage(arrow.getImage(0,0), flightrotation-45),(int)flightpathx,(int)flightpathy, null);
			}
			if (BowhitAnimation <=50 && BowhitAnimation >= 40) g.drawImage(Playerhit.getImage(PlayState.player.direction, 0), (int)x-8, (int)y-16, null);
			else g.drawImage(PlayState.player.image.getImage(PlayState.player.direction, PlayState.player.animation.getFrame()), (int)x-8, (int)y-16, null);
			//ourl
		}
		else g.drawImage(PlayState.player.image.getImage(PlayState.player.direction, PlayState.player.animation.getFrame()), (int)x-8, (int)y-16, null);
		Hud.draw(g);
		g.setColor(Color.RED);
		g.drawRect(97 + (PlayState.activeShortCut-1) * 32, 344, 29, 29);
	}

	public void handleInput(InputHandler input) {
		
	}
}
