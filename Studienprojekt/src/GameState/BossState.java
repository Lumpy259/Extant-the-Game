package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

import Entity.Animation;
import Entity.Entity;
import Entity.Player;
import Entity.Boss.Beast;
import Entity.Boss.Blob;
import Entity.Boss.Boss;
import Entity.Boss.Muffin;
import Entity.Boss.Shadow;
import Entity.Boss.Troll_priest;
import Entity.Environment.Bossdoor_inside;
import Entity.Environment.Environment;
import Entity.Environment.Hitbox_environment;
import Entity.Item.InventoryItem;
import Entity.Item.Item;
import Entity.Monster.Monster;
import Entity.NPC.NPC;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Hud;
import Manager.InputHandler;
import Manager.Music;
import Manager.Rand;
import Manager.Statistics;
import World.Tile.Tile;

public class BossState extends GameState {

	ArrayList<Entity> entities = new ArrayList<>();
	public static ArrayList<Entity> removeEntity = new ArrayList<>();
	public static ArrayList<Entity> addEntity = new ArrayList<>();
	public static double x;
	public static double y;
	Content hud;
	public static double temp1 = 0;
	public static double temp2 = 0;
	private Animation zapAnimation;
	private Content zap;
	private Animation HitAnimation;
	private Content hit;
	private Content Playerhit;
	public static int hitAnimation = 0; //player
	public static int bossrhitAnimation = 0; //player
	public static Item activeItem;
	private Content bosshit;
	public Animation bossAnimation;
	public int BossAnimation = 0;//boss
	public static int bosscount = 0;
	private Content arrow;
	public int monsterhitAnimation = 0;
	public static int usewhat = 0;
	public double flightpathx = 0;
	public double flightpathy = 0;
	public double tempx = 0;
	public double tempy = 0;
	public boolean used = false;
	public int flightrotation = 0;
	public static int BowhitAnimation = 0;
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
	
	public BossState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		temp1 = Game.playerX;
		temp2 = Game.playerY;
		Player.activeItem = null; 
		Game.playerX = Game.WIDTH/2-8;
		Game.playerY = Game.HEIGHT/2+32;
		PlayState.player.x = Game.playerX;
		PlayState.player.y = Game.playerY;
		x = Game.WIDTH/2-8;
		y = Game.HEIGHT/2+32;
		eating = new Content("EATING", "Resources/Sprites/eating.png", 16, 32, new Point(1,4));
		drinking = new Content("DRINKING", "Resources/Sprites/drinking.png", 16, 32, new Point(1,4));
		hud = new Content ("HUD", "Resources/HUD/HUD.png", 512, 80, new Point(1,1));
		entities.add(PlayState.player);
		zapAnimation = new Animation(10, 3);
		zap = new Content("ZAP", "Resources/Animations/Zapanimation.png", 160, 160, new Point(3,1));
		HitAnimation = new Animation(5, 3);
		hit = new Content("HIT", "Resources/Animations/swordanimation.png", 48, 60, new Point(3,4));
		Playerhit = new Content("PLAYERHIT", "Resources/Animations/attack.png",16, 32, new Point(1,4));
		bossAnimation = new Animation(5, 3);
		bosshit = new Content("ATTACK", "Resources/Animations/Monsteranimation.png", 20, 20, new Point(1,3));
		HoeAnimation = new Animation(5, 3);
		hoehit = new Content("HIT", "Resources/Animations/hoeanimation.png", 48, 60, new Point(3,4));
		AxeAnimation = new Animation(5, 3);
		axehit = new Content("HIT", "Resources/Animations/axeanimation.png", 48, 60, new Point(3,4));
		PickaxeAnimation = new Animation(5, 3);
		pickaxehit = new Content("HIT", "Resources/Animations/pickaxeanimation.png", 48, 60, new Point(3,4));
		ShovelAnimation = new Animation(5, 3);
		shovelhit = new Content("HIT", "Resources/Animations/shovelanimation.png", 48, 60, new Point(3,4));
		arrow = new Content("SCHIESEN", "Resources/Items/arrow.png", 28, 28, new Point(1,1));
		for (int x=0; x < Game.WIDTH / 32; x++)
			for (int y=0; y< Game.HEIGHT / 32; y++) 
				if(x==0 || x==Game.WIDTH / 32-1 || y==0 || y==Game.HEIGHT /32-2) 
					entities.add(new Hitbox_environment(x*32, y*32));
		
		entities.add(PlayState.player);
		//entities.add(new Shadow(Game.WIDTH/2-32, Game.HEIGHT/2-64));
		
		if (Entity.boss == 0)
			entities.add(new Shadow(Game.WIDTH/2-32, Game.HEIGHT/2-64));
		if (Entity.boss == 1)
			entities.add(new Blob(Game.WIDTH/2-32, Game.HEIGHT/2-64, 3));
		/*
		if (bosscount == 0) entities.add(new Muffin(Game.WIDTH/2-92, Game.HEIGHT/2-5));
		else if (bosscount == 1) entities.add(new Beast(Game.WIDTH/2-64, 32));
		else if (bosscount == 2)entities.add(new Troll_priest(Game.WIDTH/2-64, 32));
		else if (bosscount == 3) gsm.setState(gsm.OUTRO);
		*/
	}
	public void update(InputHandler input) {
		
		
		for (Entity e : BossState.addEntity) {
			entities.add(e);
		}
		addEntity.clear();
		
		for (Entity e: BossState.removeEntity) {
			entities.remove(e);
		}
		removeEntity.clear();
		
		Player.activeItem = null; 
		Game.playerX = x;
		Game.playerY = y;
		PlayState.player.x = x;
		PlayState.player.y = y;
		if (Player.usewhat==Entity.BOSSDOOR_INSIDE){
			Game.playerX = temp1;
			Game.playerY = temp2; 
			PlayState.player.x = temp1;
			PlayState.player.y = temp2;
			Player.usewhat=Entity.ZERO;
			gsm.setState(gsm.PLAY);
			//Bossraum verlassen
		}
		if (PlayState.player.currentlife <= 0){
			Statistics.deaths += 1;
			gsm.setState(gsm.GAMEOVER);
		}
		
		// update entities
		for (Entity e : entities){
			e.update(input, entities);
		}
		
		if (input.isPressed(input.SPACE) && activeItem != null && activeItem.getType() == Item.FLASK){
			if (PlayState.player.currentlife < PlayState.player.maxlife){	
				PlayState.player.healing = 25;
				drinkingtime=60;
				Music.getPowerupSound();
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
		bossrhitAnimation -= (bossrhitAnimation > 0 ? 1 : 0);
		hitAnimation -= (hitAnimation > 0 ? 1 : 0);
		BowhitAnimation -= (BowhitAnimation > 0 ? 1 : 0);
		Boss.knockupanimation -= (Boss.knockupanimation > 0 ? 1 : 0);
		hoeAnimation -= (hoeAnimation > 0 ? 1 : 0);
		axeAnimation -= (axeAnimation > 0 ? 1 : 0);
		shovelAnimation -= (shovelAnimation > 0 ? 1 : 0);
		pickaxeAnimation -= (pickaxeAnimation > 0 ? 1 : 0);
		
		if (bossrhitAnimation != 0){
			if (PlayState.player.direction == 0)
				PlayState.player.validateRangedAttack(entities, Game.playerX, Game.playerY + 170, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 1)
				PlayState.player.validateRangedAttack(entities, Game.playerX, Game.playerY - 170, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 2)
				PlayState.player.validateRangedAttack(entities, Game.playerX+170, Game.playerY, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 3)
				PlayState.player.validateRangedAttack(entities, Game.playerX-170, Game.playerY, Game.playerX, Game.playerY);
		}
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
		
		if (input.isPressed(input.SPACE) && bossrhitAnimation == 0 && activeItem != null && activeItem.getType() == Item.RWEAPON && activeItem.getName().equals("Pistole")){
			Music.getLaserWeaponSound();
			if (PlayState.inventory.searchforItem("Munition") == null){}
			else{
				bossrhitAnimation = 30;
				if (PlayState.player.direction == 0)
					PlayState.player.validateRangedAttack(entities, Game.playerX, Game.playerY + 170, Game.playerX, Game.playerY);
				else if (PlayState.player.direction == 1)
					PlayState.player.validateRangedAttack(entities, Game.playerX, Game.playerY - 170, Game.playerX, Game.playerY);
				else if (PlayState.player.direction == 2)
					PlayState.player.validateRangedAttack(entities, Game.playerX+170, Game.playerY, Game.playerX, Game.playerY);
				else if (PlayState.player.direction == 3)
					PlayState.player.validateRangedAttack(entities, Game.playerX-170, Game.playerY, Game.playerX, Game.playerY);
				if (PlayState.inventory.searchforItem("Munition").getCount()==1)
					PlayState.inventory.removeItem(PlayState.inventory.searchforPoint("Munition"));
				else PlayState.inventory.searchforItem("Munition").setCount(-1);
			}
		}
		else if (input.isPressed(input.SPACE) && hitAnimation == 0 && bossrhitAnimation == 0 && activeItem != null && (activeItem.getType() == Item.AXE || activeItem.getType() == Item.SHOVEL || activeItem.getType() == Item.HOE || activeItem.getType() == Item.PICKAXE ||activeItem.getType() == Item.WEAPON)){
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
		}else if (input.isPressed(input.SPACE) && bossrhitAnimation == 0 && activeItem != null && (activeItem.getType() == Item.RWEAPON) && activeItem.getName().equals("Bogen") && used == false){
			Music.getLaserWeaponSound();
			if (PlayState.inventory.searchforItem("Pfeile") == null){}
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
			PlayState.player.validatebowAttack(entities, tempx, tempy);
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
		
		if (input.isPressed(input.USE)){
			if (PlayState.player.direction == 0)  Player.usewhat = PlayState.player.validateUse(entities, Game.playerX, Game.playerY + 38, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 1) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX, Game.playerY - 33, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 2) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX+21, Game.playerY, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 3) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX-21, Game.playerY, Game.playerX, Game.playerY);
		}	
		
		InventoryItem[] inventoryShortCuts = PlayState.inventory.getShortCuts();
		activeItem = (inventoryShortCuts[PlayState.activeShortCut-1] != null ? inventoryShortCuts[PlayState.activeShortCut-1].getItem() : null);
		BossAnimation -= (BossAnimation > 0 ? 1 : 0);
		if ((PlayState.player.knockBackX != 0 || PlayState.player.knockBackY != 0) && BossAnimation == 0){
			BossAnimation = 15;
		}
		if (PlayState.player.knockBackX != 0 || PlayState.player.knockBackY != 0) {
			if (PlayState.player.knockBackX > 0) {
				if(!PlayState.player.validateNextPosition(entities, x+3, y)){
					x+= 3;
					//Game.playerX += 3;
				}
				PlayState.player.knockBackX -= 3;
			}
			else if (PlayState.player.knockBackX < 0) {
				if(!PlayState.player.validateNextPosition(entities, x-3, y)){
					x -= 3;
					//Game.playerX -= 3;
				}
				PlayState.player.knockBackX += 3;
			}
			else if (PlayState.player.knockBackY > 0) {
				if(!PlayState.player.validateNextPosition(entities, x, y+3)){
					y += 3;
					//Game.playerY += 3;
				}
				PlayState.player.knockBackY -= 3;
			}
			else if (PlayState.player.knockBackY < 0) {
				if(!PlayState.player.validateNextPosition(entities, x, y-3)){
					y -= 3;
					//Game.playerY -= 3;
				}
				PlayState.player.knockBackY += 3;
			}
			return;
		}
		
		//PlayState.player.update(input, entities);
		PlayState.player.animation.setFreeze(false);
		 if (Boss.knockupanimation !=0){
			 	PlayState.player.animation.setFreeze(true);
				if (Boss.knockupanimation <=40 && Boss.knockupanimation >=20) y-=2;
				else y+=2;
		 }
		else if (eatingtime != 0 || drinkingtime != 0 || PlayState.player.knockBackY != 0 || PlayState.player.knockBackX != 0||bossrhitAnimation != 0 || hitAnimation != 0 || hoeAnimation != 0 || pickaxeAnimation != 0 || shovelAnimation != 0|| axeAnimation != 0 || PlayState.player.rhitAnimation != 0 || PlayState.player.hitAnimation != 0){
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
		
		else if (input.keyState[input.UP] && GameStateManager.currentState == GameStateManager.BOSS) { 
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
		ArrayList<Entity> items = new ArrayList<>();
		
		for (Entity e: PlayState.removeEntity) {
			if (!(e instanceof Item)){
			    try { 
			    	if (e instanceof Boss) {	
			    		bosscount++;
						entities.add(new Bossdoor_inside(Game.WIDTH/2-18, -3));
						Boss boss = (Boss) e;
				    	for(Class key : boss.loot.keySet())
				    		if (Rand.weightedRandom(new int[]{100-boss.loot.get(key),boss.loot.get(key)}) == 1)
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

	public void draw(Graphics2D g) {
		Collections.sort(entities);
		int tile = 3;
		for (int x=0; x < Game.WIDTH / 32; x++)
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
					tile = 21;
					g.drawImage(Tile.tiles[tile].getImage(), x*32, y*32, null);
				}
			}
		g.setColor(Color.BLACK);
		g.fillRect(0, 352, 512, 32);
		
		
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
				y2 = -16;
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
				y2 = -16;;
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
				y2 = -16;;
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
		else if (bossrhitAnimation > 0) {			
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
		else if (BossAnimation > 0) {
			x2 = -2;
			y2 = 6;
			bossAnimation.update();
			g.drawImage(PlayState.player.image.getImage(PlayState.player.direction, PlayState.player.animation.getFrame()), (int)x-8, (int)y-16, null);
			g.drawImage(bosshit.getImage(bossAnimation.getFrame(), 0), null,(int)( x-8 +x2), (int)(int)(y-16+ y2));
		}
		else g.drawImage(PlayState.player.image.getImage(PlayState.player.direction, PlayState.player.animation.getFrame()), (int)x-8, (int)y-16, null);
	
		/*
		g.setColor(Color.ORANGE);
		g.fillRect(97, 338, 318, 5);
		g.setColor(Color.RED);
		g.fillRect(97,338, ((int)(318.0 / (Player.level*70)*Player.xp) <= 318 ? (int)(318.0 / (Player.level*70)*Player.xp) : 318), 5);
				
		g.drawImage(hud.getImage(0,0), 0, Game.HEIGHT-80, null);
		
		
		g.drawString((int)Game.playerX + "|" + (int)Game.playerY, 0, 10);
		g.drawString((int)x + "|" + (int)y, 0, 20);
		
		g.setColor(Color.RED);
		g.setClip (new Rectangle (20, Game.HEIGHT-(int)(80.0 / Player.maxlife * Player.currentlife), 74, 74));
		g.fillOval(20,Game.HEIGHT-80,73,73);
		g.setClip(null);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 11));
		g.setColor(Color.WHITE);
		InventoryItem[] inventoryShortCuts = PlayState.inventory.getShortCuts();
		for (int i=0; i<10; i++)
			if(inventoryShortCuts[i] != null) {
				g.drawImage(inventoryShortCuts[i].getImage(), 98 + i * 32, 345, null);
				if (inventoryShortCuts[i].getStackable() > 1)
					g.drawString(""+inventoryShortCuts[i].getCount(), 114 + i * 32 + (inventoryShortCuts[i].getCount() < 10 ? 6 : 0), 373);
			}
		g.setColor(Color.RED);
		g.drawRect(97 + (PlayState.activeShortCut-1) * 32, 344, 29, 29);
		
		g.setColor(Color.WHITE);
		for (int i=1;i<11;i++)
			g.drawString(""+i%10, 110 + (i-1) * 32, 384);
			
			*/
		Hud.draw(g);
	}

	public void handleInput(InputHandler input) {
		
	}
}
