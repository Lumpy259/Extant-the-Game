package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import Entity.Animation;
import Entity.Entity;
import Entity.Player;
import Entity.Environment.Door_inside;
import Entity.Environment.Environment;
import Entity.Environment.Hitbox_environment;
import Entity.Item.InventoryItem;
import Entity.Item.Item;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Hud;
import Manager.InputHandler;
import Manager.Music;
import Manager.Rand;
import World.Tile.Tile;

public class PuzzleState extends GameState {

	ArrayList<Entity> entities = new ArrayList<>();
	public static ArrayList<Entity> removeEntity = new ArrayList<>();
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
	public static boolean entered = false;
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
	private Point lastRegularPosition;
	private boolean solved;
	private int movement = -1;
	public static boolean startriddle = false;
	public static Content riddle = new Content ("RIDDLE", "Resources/Usable/riddle.png", 56, 56, new Point(4,4));
	public static HashMap<Point, Point> riddlemap = new HashMap<Point, Point>();
	public static int curserx = 0;
	public static int cursery =0;
	public static Point temp3;
	public static Point temp4;
	public static boolean changed = false;
	public static boolean riddeling = false;
	public static boolean solved2 = false;
	private Content eating;
	private Content drinking;
	public static int eatingtime=0;
	public static int drinkingtime=0;
	
	public static int[][] map = new int[][] {//16x11
		{19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19},
		{18, 23, 23, 25, 23, 23, 23, 22, 23, 23, 23, 23, 23, 23, 23, 18},
		{18, 23, 23, 25, 23, 23, 23, 23, 23, 23, 23, 23, 23, 25, 23, 18},
		{18, 23, 23, 23, 23, 25, 23, 23, 25, 23, 23, 25, 23, 23, 23, 18},
		{18, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 18},
		{18, 23, 23, 25, 25, 23, 23, 23, 23, 23, 25, 23, 23, 23, 23, 18},
		{18, 23, 23, 23, 23, 23, 25, 23, 23, 23, 23, 23, 25, 23, 23, 18},
		{18, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 18},
		{18, 23, 23, 23, 23, 25, 23, 23, 23, 23, 23, 25, 23, 23, 23, 18},
		{18, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 18},
		{19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19}
	};
	
	public static int[][] map2 = new int[][] {//16x11
		{19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19},
		{18, 40, 7, 7, 7, 7, 7, 22, 7, 40, 7, 7, 7, 7, 7, 18},
		{18, 7, 7, 40, 7, 7, 40, 7, 40, 7, 7, 7, 7, 7, 7, 18},
		{18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 40, 7, 7, 18},
		{18, 7, 7, 40, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 18},
		{18, 7, 7, 7, 7, 7, 7, 7, 40, 7, 40, 7, 7, 7, 7, 18},
		{18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 18},
		{18, 7, 7, 7, 7, 7, 7, 40, 7, 7, 7, 7, 7, 7, 7, 18},
		{18, 40, 7, 7, 7, 7, 40, 40, 7, 7, 7, 7, 7, 40, 40, 18},
		{18, 7, 7, 40, 7, 7, 7, 7, 40, 7, 7, 7, 7, 7, 7, 18},
		{19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19}
	};
	
	public PuzzleState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		lastRegularPosition = new Point(7, 1);
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
		entities.add(PlayState.player);
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
		entities.add(new Door_inside(Game.WIDTH /2 -25, -3));
		arrow = new Content("SCHIESEN", "Resources/Items/arrow.png", 28, 28, new Point(1,1));
		for (int x=0; x < Game.WIDTH / 32; x++)
			for (int y=0; y< Game.HEIGHT / 32; y++)
				if(x==0 || x==Game.WIDTH / 32-1 || y==0 || y==Game.HEIGHT /32-2)
					entities.add(new Hitbox_environment(x*32, y*32));
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
	
	public void riddle(InputHandler input, ArrayList<Entity> entities) {
		if (GameStateManager.currentState != GameStateManager.PLAY)
			return;
		
		if (input.isPressed(input.DOWN))
			cursery++;
		else if (input.isPressed(input.UP))
			cursery--;
		else if (input.isPressed(input.LEFT))
			curserx--;
		else if (input.isPressed(input.RIGHT))
			curserx++;
		
		if (input.isPressed(input.BACKSPACE)){
			if(curserx!=3){ 
				if (riddlemap.get(new Point(curserx+1, cursery)).equals(new Point(0,0))&& changed==false) {
					temp3 = riddlemap.get(new Point(curserx, cursery));
					riddlemap.remove(new Point(curserx+1, cursery));
					riddlemap.remove(new Point(curserx, cursery));
					riddlemap.put(new Point(curserx+1, cursery), temp3);
					riddlemap.put(new Point(curserx, cursery), new Point(0,0));
					changed=true;
				}
			}
			if(cursery!=3){ 
				if (riddlemap.get(new Point(curserx, cursery+1)).equals(new Point(0,0))&& changed==false) {
					temp3 = riddlemap.get(new Point(curserx, cursery));
					riddlemap.remove(new Point(curserx, cursery+1));
					riddlemap.remove(new Point(curserx, cursery));
					riddlemap.put(new Point(curserx, cursery+1), temp3);
					riddlemap.put(new Point(curserx, cursery), new Point(0,0));
					changed=true;
				}
			}
			if(curserx!=0){ 
				if (riddlemap.get(new Point(curserx-1, cursery)).equals(new Point(0,0))&& changed==false) {
					temp3 = riddlemap.get(new Point(curserx, cursery));
					riddlemap.remove(new Point(curserx-1, cursery));
					riddlemap.remove(new Point(curserx, cursery));
					riddlemap.put(new Point(curserx-1, cursery), temp3);
					riddlemap.put(new Point(curserx, cursery), new Point(0,0));
					changed=true;
					changed=true;
				}
			}
			if(cursery!=0){ 
				if (riddlemap.get(new Point(curserx, cursery-1)).equals(new Point(0,0)) && changed==false){
					temp3 = riddlemap.get(new Point(curserx, cursery));
					riddlemap.remove(new Point(curserx, cursery-1));
					riddlemap.remove(new Point(curserx, cursery));
					riddlemap.put(new Point(curserx, cursery-1), temp3);
					riddlemap.put(new Point(curserx, cursery), new Point(0,0));
					changed=true;
				}
			}
		}
			
		if(changed==true){
			for(int j = 0; j < 4; j ++){
				for(int i = 0; i < 4; i ++){
					if (riddlemap.get(new Point(i,j)).equals(new Point(i,j))) solved = true;
					else{
						solved=false;
						break;
					}
				}
				if(solved==false) break;
			}
		}

		if (cursery < 0) cursery = 3;
		if (cursery > 3) cursery = 0;
		if (curserx < 0) curserx = 3;
		if (curserx > 3) curserx = 0;

		changed=false;
		if (solved == true){
			System.out.println("gelöst");
			riddeling = false;
		}
	}
	
	public void useWhat() {
		if (Player.usewhat==Entity.DOOR_INSIDE){
			Game.playerX = temp1;
			Game.playerY = temp2; 
			gsm.setState(gsm.PLAY);
		}
		if (Player.usewhat==Entity.NOAMMO){
			gsm.setState(gsm.DIALOG);
			//keine Muntion mehr
		}
	}

	public void movement2(InputHandler input, ArrayList<Entity> entities) {
		
		if (movement == -1) {
			if (input.keyState[input.UP]) movement = 1;
			else if (input.keyState[input.DOWN]) movement = 0;
			else if (input.keyState[input.LEFT]) movement = 3;
			else if (input.keyState[input.RIGHT]) movement = 2;
			else movement = -1;
		}
		else {

			if (movement == 1) { 
				if(!PlayState.player.validateNextPosition(entities, x, y-PlayState.player.moveSpeed)) {
					y -= PlayState.player.moveSpeed;
					PlayState.player.direction = 1;
				} 
				else
					movement = -1;
			}
			else if (movement == 0) { 
				if(!PlayState.player.validateNextPosition(entities, x, y+PlayState.player.moveSpeed)) {
					y += PlayState.player.moveSpeed; 
					PlayState.player.direction = 0; 
				}
				else
					movement = -1;
			}
			
			else if (movement == 3) { 
				if(!PlayState.player.validateNextPosition(entities, x-PlayState.player.moveSpeed, y)) {
					x -= PlayState.player.moveSpeed; 
					PlayState.player.direction = 3; 
				}
				else
					movement = -1;
			}
			
			else if (movement == 2) { 
				if(!PlayState.player.validateNextPosition(entities, x+PlayState.player.moveSpeed, y)) {
					x += PlayState.player.moveSpeed; 
					PlayState.player.direction = 2; 
				}
				else movement = -1;
			}
			
			if (map[(int)Game.playerY/32][(int)Game.playerX/32] == 22)
				movement = -1;
		}
	}
	public void movement3(InputHandler input, ArrayList<Entity> entities) {
		
		if (movement == -1) {
			if (input.keyState[input.UP]) movement = 1;
			else if (input.keyState[input.DOWN]) movement = 0;
			else if (input.keyState[input.LEFT]) movement = 3;
			else if (input.keyState[input.RIGHT]) movement = 2;
			else movement = -1;
		}
		
		PlayState.player.animation.setFreeze(false);
		if (eatingtime != 0 || drinkingtime != 0 || PlayState.player.rhitAnimation != 0 || hoeAnimation != 0 || pickaxeAnimation != 0 || shovelAnimation != 0|| axeAnimation != 0 || PlayState.player.hitAnimation != 0 || BossState.bossrhitAnimation != 0 || BossState.hitAnimation != 0 || HouseState.sleepanimation != 0) {
			PlayState.player.animation.setFreeze(true);
		}
		else if (movement == 1 && input.keyState[input.UP] && input.keyState[input.RIGHT]) { 
			if(!PlayState.player.validateNextPosition(entities, x+0.707 * PlayState.player.moveSpeed, y-0.707*PlayState.player.moveSpeed)) {
				x += 0.707 * PlayState.player.moveSpeed; 
				y -= 0.707 * PlayState.player.moveSpeed; 
				PlayState.player.direction = 1;
			}
		}
		
		else if (movement == 1 && input.keyState[input.UP] && input.keyState[input.LEFT]) { 
			if(!PlayState.player.validateNextPosition(entities, x-0.707 *PlayState.player.moveSpeed, y-0.707*PlayState.player.moveSpeed)) {
				x -= 0.707 * PlayState.player.moveSpeed; 
				y -= 0.707 * PlayState.player.moveSpeed;
				PlayState.player.direction = 1;
			}
		}
		
		else if (movement == 0 && input.keyState[input.DOWN] && input.keyState[input.RIGHT]) { 
			if(!PlayState.player.validateNextPosition(entities, x+0.707 * PlayState.player.moveSpeed, y+0.707*PlayState.player.moveSpeed)) {
				x += 0.707 * PlayState.player.moveSpeed; 
				y += 0.707 * PlayState.player.moveSpeed; 
				PlayState.player.direction = 0;
			}
		}
		
		else if (movement == 0 && input.keyState[input.DOWN] && input.keyState[input.LEFT]) { 
			if(!PlayState.player.validateNextPosition(entities, x-0.707 * PlayState.player.moveSpeed, y+0.707*PlayState.player.moveSpeed)) {
				x -= 0.707 * PlayState.player.moveSpeed; 
				y += 0.707 * PlayState.player.moveSpeed;
				PlayState.player.direction = 0;
			}
		}
		
		else if (movement == 1 || (input.keyState[input.UP] && movement == -1)) { 
			if(!PlayState.player.validateNextPosition(entities, x, y-PlayState.player.moveSpeed)) {
				y -= PlayState.player.moveSpeed;
				PlayState.player.direction = 1;
			} 
			else
				movement = -1;
		}
		
		else if (movement == 0 || (input.keyState[input.DOWN] && movement == -1)) { 
			if(!PlayState.player.validateNextPosition(entities, x, y+PlayState.player.moveSpeed)) {
				y += PlayState.player.moveSpeed; 
				PlayState.player.direction = 0; 
			}
			else
				movement = -1;
		}
		
		else if (movement == 3 || (input.keyState[input.LEFT] && movement == -1)) {
			if(!PlayState.player.validateNextPosition(entities, x-PlayState.player.moveSpeed, y)) {
				x -= PlayState.player.moveSpeed; 
				PlayState.player.direction = 3; 
			}
			else
				movement = -1;
		}
		
		else if (movement == 2 || (input.keyState[input.RIGHT] && movement == -1)) {
			if(!PlayState.player.validateNextPosition(entities, x+PlayState.player.moveSpeed, y)) {
				x += PlayState.player.moveSpeed; 
				PlayState.player.direction = 2; 
			}
			else
				movement = -1;
		}
		else 
			PlayState.player.animation.setFreeze(true);
		
		if ((int)(Game.playerY+8)/32 < 11 && (int)Game.playerX/32 < 16)
			if (Tile.tiles[map[(int)(Game.playerY+8)/32][(int)Game.playerX/32]].id != Tile.ICE.id) {
				movement = -1;
				PlayState.player.animation.update();
			}
			else
				PlayState.player.animation.setFreeze(true);	
		

	}
	
	public void movement(InputHandler input, ArrayList<Entity> entities) {
		
		PlayState.player.animation.setFreeze(false);
		if (eatingtime != 0 || drinkingtime != 0 || PlayState.player.rhitAnimation != 0 || hoeAnimation != 0 || pickaxeAnimation != 0 || shovelAnimation != 0|| axeAnimation != 0 || PlayState.player.hitAnimation != 0 || BossState.bossrhitAnimation != 0 || BossState.hitAnimation != 0 || HouseState.sleepanimation != 0) {
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
		
		else if (input.keyState[input.UP]) { 
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

	}
	
	public void update(InputHandler input) {
		Game.playerX = x;
		Game.playerY = y;
		
		
		//*******************************   Rätsel 1   *******************************
		int xNew = (int)Game.playerX/32;
		int yNew = ((int)Game.playerY/32 == 0 ? 1 : (int)Game.playerY/32);
		
		if ((lastRegularPosition.x == xNew && lastRegularPosition.y+1 == yNew) || (lastRegularPosition.x == xNew && lastRegularPosition.y-1 == yNew) || (lastRegularPosition.x+1 == xNew && lastRegularPosition.y == yNew) || (lastRegularPosition.x-1 == xNew && lastRegularPosition.y == yNew))
			if (map[yNew][xNew] == 23) {
				map[yNew][xNew] = 22;
				lastRegularPosition = new Point(xNew, yNew);
			}
		
		solved = true;
		for (int x=1; x<Game.WIDTH/32-1; x++) {
			for (int y=1; y<Game.HEIGHT/32-2; y++) {
				solved = true;
				if (map[y][x] == 23) {solved = false; break;}
			}
			if (!solved) break;
		}
		
		if (solved) System.out.println("true");
		//****************************************************************************
		
		
		//*******************************   Rätsel 2   *******************************
		
		/*
		if(startriddle==true){
			for(int j = 0; j < 4; j ++)
				for(int i = 0; i < 4; i ++)
						riddlemap.put(new Point(i,j), new Point(i,j));
			for(int j = 0; j < 16; j ++){
				Random rand = new Random();
				int Randx1;
				int Randy1;
				int Randx2;
				int Randy2;
				Randx1 = rand.nextInt(4);
				Randy1 = rand.nextInt(4);
				Randx2 = rand.nextInt(4);
				Randy2 = rand.nextInt(4);
				temp3 = Player.riddlemap.get(new Point(Randx1, Randx1));
				temp4 = Player.riddlemap.get(new Point(Randx2, Randy2));
				riddlemap.remove(new Point(Randx1, Randx1));
				riddlemap.remove(new Point(Randx2, Randy2));
				riddlemap.put(new Point(Randx1, Randx1), temp4);
				riddlemap.put(new Point(Randx2, Randy2), temp3);
			}	
			startriddle=false;
			riddeling = true;
		}
		if(riddeling == true){
			riddle(input, entities);
		}
		 */
		
		
		//****************************************************************************
		
		useWhat();
		handleInput(input);
		
		
		for (Entity e : entities){
			e.update(input, entities);
		}
		
		if (input.isPressed(input.SPACE) && activeItem != null && activeItem.getType() == Item.FLASK){
			if (PlayState.player.currentlife < PlayState.player.maxlife){	
				PlayState.player.healing = 25;
				drinkingtime=60;
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
			if (PlayState.inventory.searchforItem("Munition") == null){Player.usewhat = Entity.NOAMMO;}
			else{
				rhitAnimation = 30;
				if (PlayState.inventory.searchforItem("Munition").getCount()==1)
					PlayState.inventory.removeItem(PlayState.inventory.searchforPoint("Munition"));
				else PlayState.inventory.searchforItem("Munition").setCount(-1);
			}
		}
		else if (input.isPressed(input.SPACE) && rhitAnimation == 0 && activeItem != null && (activeItem.getType() == Item.RWEAPON) && activeItem.getName().equals("Bogen") && used == false){
			Music.getLaserWeaponSound();
			if (PlayState.inventory.searchforItem("Pfeile") == null){Player.usewhat = Entity.NOAMMO;}
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
		
		
		
		InventoryItem[] inventoryShortCuts = PlayState.inventory.getShortCuts();
		activeItem = (inventoryShortCuts[PlayState.activeShortCut-1] != null ? inventoryShortCuts[PlayState.activeShortCut-1].getItem() : null);
		
		if (input.isPressed(input.USE)){
			if (PlayState.player.direction == 0)  Player.usewhat = PlayState.player.validateUse(entities, Game.playerX, Game.playerY + 38, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 1) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX, Game.playerY - 33, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 2) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX+21, Game.playerY, Game.playerX, Game.playerY);
			else if (PlayState.player.direction == 3) Player.usewhat = PlayState.player.validateUse(entities, Game.playerX-21, Game.playerY, Game.playerX, Game.playerY);
		}

		movement(input, entities);
		
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
	
	public void draw(Graphics2D g) {
		Collections.sort(entities);
		
		for (int x=0; x<Game.WIDTH/32; x++)
			for (int y=0; y<Game.HEIGHT/32-1; y++) {
				if(x==0 && y==0)						
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 0), x*32, y*32, null);
				else if (x==Game.WIDTH / 32-1 && y==0)					
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 90), x*32, y*32, null);
				else if (x==0 && y==Game.HEIGHT /32-2)					
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 270), x*32, y*32, null);
				else if (x==Game.WIDTH / 32-1 && y==Game.HEIGHT /32-2)	
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 180), x*32, y*32, null);
				else if(x>0 && !(x==Game.WIDTH / 32-1) && y==0) 
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 90), x*32, y*32, null);
				else if (x==Game.WIDTH / 32-1 && y>0 && !(y>=Game.HEIGHT /32-2))
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 180), x*32, y*32, null);
				else if (x>0 && y==Game.HEIGHT /32-2 && !(x>=Game.WIDTH /32-1))
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 270), x*32, y*32, null);
				else if (x==0 && y>0 && !(y>=Game.HEIGHT /32-2))
					g.drawImage(Tile.tiles[map[y][x]].getrotatedImage(Tile.tiles[map[y][x]].getImage(0,0), 0), x*32, y*32, null);
				else 				
					g.drawImage(Tile.tiles[map[y][x]].getImage(), x*32, y*32, null);
			}
		g.drawImage(Tile.tiles[24].getImage(), lastRegularPosition.x*32, lastRegularPosition.y*32, null);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 352, 512, 32);
		
		for (Entity e : entities){
			if(!(e instanceof Player)) e.draw(g);
		}
		int x2 = 0;
		int y2 = 0;
		/*
		if(Player.riddeling==true){
			for(int j = 0; j < 4; j ++){
				for(int i = 0; i < 4; i ++){
						if (Player.riddlemap.get(new Point(j,i)).equals(new Point(0,0))){
							g.setColor(Color.black);
							g.fillRect(144+j*56, 50+i*56, 56, 56);
						}
						else g.drawImage(riddle.getImage(Player.riddlemap.get(new Point(j,i)).y, Player.riddlemap.get(new Point(j,i)).x), 144+j*56, 50+i*56, null);
				}
			}
			g.setColor(Color.YELLOW);
			g.drawRect(144 + Player.curserx * 56, 50+ Player.cursery * 56, 56, 56);
		}
		else 
		*/
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
				
		g.drawString((int)Game.playerX + "x" + (int) Game.playerY, 20, 30);
		Hud.draw(g);
	}

	public void handleInput(InputHandler input) {
		if (input.isPressed(input.INVENTORY))			gsm.setState(gsm.INVENTORY);
		else if (input.isPressed(input.STATUS))			gsm.setState(gsm.STATUS);
		else if (input.isPressed(input.INGAMEMENUE))	gsm.setState(gsm.INGAMEMENUE);
		else if (input.isPressed(input.TALK)) {			DialogState.yuidialog=true; gsm.setState(gsm.DIALOG); }
		
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
		if (input.isPressed(input.ZERO)){ PlayState.activeShortCut = 10; Player.activeItem = null; }
	}
}
