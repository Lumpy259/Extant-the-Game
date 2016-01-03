package Entity.NPC;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Entity.Animation;
import Entity.Entity;
import Entity.HitBox;
import Entity.Player;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import Manager.Music;
import World.World;

public abstract class NPC extends Entity {

	private int maxLife, width, heigth;
	public  int currentLife;
	private int damage;
	private double x, y;
	private Random rnd = new Random();
	private HitBox hitBox;
	private double moveSpeed;
	private int attackrange;
	protected int NpcXP;
	int direction;
	private int a = rnd.nextInt(3);
	private int laenge = 0;
	public static int viewrange = (int)(Game.WIDTH*0.66);
	public Content image;	
	public int cooldown;
	public int mknockBackX = 0;
	public int mknockBackY = 0;
	public int runcountdown = 0;
	public int breedcountdown = 0;
	public double tempmoveSpeed = 0;
	public boolean animalfollow;
	public HashMap<Class, Integer> loot;
	public static boolean checknpc = false;

	public NPC(String name, String path, int width, int height, int damage, int maxLife, double x, double y, double moveSpeed, HitBox hitBox, Content image2, int attackrange) {
		super();
		this.maxLife = maxLife;
		this.damage = damage;	
		this.currentLife = maxLife;
		this.image = image2;
		super.x = x;
		super.y = y;
		super.height = height;
		super.width = width;
		super.hitBox = hitBox;
		this.moveSpeed = moveSpeed;
		this.attackrange = attackrange;
		this.tempmoveSpeed = moveSpeed;
		}
	
	public void update(InputHandler input, ArrayList<Entity> entities) {
		super.update(input, entities);
		if(this.currentLife <= 0) {
			Player.xp += this.NpcXP;
			PlayState.removeEntity.add(this);
		}
		
		if (cooldown > 0) cooldown -= 10;
		runcountdown -= (runcountdown > 0 ? 1 : 0);
		breedcountdown -= (breedcountdown > 0 ? 1 : 0);
		if (mknockBackX != 0 || mknockBackY != 0) {
			if (mknockBackX > 0) {
				if(!validateNextPosition(entities, super.x+10, super.y ))
					super.x += 10;
				mknockBackX -= 10;
			}
			else if (mknockBackX < 0) {
				if(!validateNextPosition(entities, super.x-10, super.y))
					super.x -= 10;
				mknockBackX += 10;
			}
			else if (mknockBackY > 0) {
				if(!validateNextPosition(entities, super.x, super.y+10))
					super.y += 10;
				mknockBackY -= 10;
			}
			else if (mknockBackY < 0) {
				if(!validateNextPosition(entities, super.x, super.y-10))
					super.y -= 10;
				mknockBackY += 10;
			}
			return;
		}

		if(super.x > Game.playerX - viewrange && super.y < Game.playerY +viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){	
			if (animalfollow == true){
				if (Player.isanimalfollowing == true){
					animation.setFreeze(false);
					if (input.keyState[input.UP] && input.keyState[input.RIGHT]) { 
						if(!validateNextPosition(entities, super.x+0.707*Player.moveSpeed, super.y-0.707*Player.moveSpeed)) {
							if(Player.stopped == false){
								super.x+=0.707*Player.moveSpeed;
								super.y-=0.707*Player.moveSpeed;
								direction=3;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.UP] && input.keyState[input.LEFT]) { 
						if(!validateNextPosition(entities, super.x-0.707*Player.moveSpeed, super.y-0.707*Player.moveSpeed)) {
							if(Player.stopped == false){
								super.x-=0.707*Player.moveSpeed;
								super.y-=0.707*Player.moveSpeed;
								direction=3;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.DOWN] && input.keyState[input.RIGHT]) { 
						if(!validateNextPosition(entities, super.x+0.707*moveSpeed, super.y+0.707*Player.moveSpeed)) {
							if(Player.stopped == false){
								super.x+=0.707*Player.moveSpeed;
								super.y+=0.707*Player.moveSpeed;
								direction=0;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.DOWN] && input.keyState[input.LEFT]) { 
						if(!validateNextPosition(entities, super.x-0.707*Player.moveSpeed, super.y+0.707*Player.moveSpeed)) {
							if(Player.stopped == false){
								super.x-=0.707*Player.moveSpeed;
								super.y+=0.707*Player.moveSpeed;
								direction=0;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.UP]) { 
						if(!validateNextPosition(entities, super.x, super.y-Player.moveSpeed)) {
							if(Player.stopped == false){
								super.y-=Player.moveSpeed;	
								direction=3;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.DOWN]) { 
						if(!validateNextPosition(entities, super.x, super.y+Player.moveSpeed)) {
							if(Player.stopped == false){
								super.y+=Player.moveSpeed;
								direction=0;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.LEFT]) { 
						if(!validateNextPosition(entities, super.x-Player.moveSpeed, super.y)) {
							if(Player.stopped == false){
								super.x-=Player.moveSpeed;
								direction=1;
							}
						}	
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
					else if (input.keyState[input.RIGHT]) { 
						if(!validateNextPosition(entities, super.x+Player.moveSpeed, super.y)) {
							if(Player.stopped == false){
								super.x+=Player.moveSpeed;
								direction=2;
							}
						}
						else {
							animalfollow = false;
							Player.isanimalfollowing = false;
						}
					}
				}
				else animalfollow=false;
			}
			else 
				animation.setFreeze(false);
				if(laenge>0){	
				checknpc = true;
				if(runcountdown != 0) moveSpeed= 1;
				if(runcountdown == 0) moveSpeed = tempmoveSpeed;
				if(a == 0) {
					if(!validateNextPosition(entities, super.x-moveSpeed, super.y)) {
						super.x-=moveSpeed;	
						direction=1;
					}		
				}
				else if(a == 1) { 
					if(!validateNextPosition(entities, super.x, super.y-moveSpeed)) {
						super.y-=moveSpeed;	
						direction=3;
					}
				}		
				else if(a == 2) {
					if(!validateNextPosition(entities, super.x+moveSpeed, super.y)) {
						super.x+=moveSpeed;
						direction=2;
					}
				}
				else if(a == 3) { 
					if(!validateNextPosition(entities, super.x, super.y+moveSpeed)) {
						super.y+=moveSpeed;
						direction=0;
					}
				}
				else if(a == 4) {
					if(!validateNextPosition(entities, super.x+0.707*moveSpeed, super.y+0.707*moveSpeed)) {
						super.x+=0.707*moveSpeed;
						super.y+=0.707*moveSpeed;
						direction=0;
					}
				}
				else if(a == 5) {
					if(!validateNextPosition(entities, super.x-0.707*moveSpeed, super.y-0.707*moveSpeed)) {
						super.x-=0.707*moveSpeed;
						super.y-=0.707*moveSpeed;
						direction=3;
					}
				}
				else if(a == 6) {
					if(!validateNextPosition(entities, super.x+0.707*moveSpeed, super.y-0.707*moveSpeed)) {
						super.x+=0.707*moveSpeed;
						super.y-=0.707*moveSpeed;
						direction=3;
					}
				}
				else if(a == 7) {
					if(!validateNextPosition(entities, super.x-0.707*moveSpeed, super.y+0.707*moveSpeed)) {
						super.x-=0.707*moveSpeed;
						super.y+=0.707*moveSpeed;
						direction=0;
					}
				}
				else if(a == 8){
					animation.setFreeze(true);
				}
				laenge-=2;
				}
				else {
					laenge= rnd.nextInt(250)+50;
					a = rnd.nextInt(9);
				}	
				checknpc = false;
		}
	}
	

	public void draw(Graphics2D g) {

		if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
			g.drawImage(image.getImage(direction,animation.getFrame()), (int)(super.x - (Game.playerX - (World.numTilesWidth-2)/2*32)),(int) (super.y - (Game.playerY - (World.numTilesHeight-2)/2*32)), null);
			
			//Lebensanzeige Monster
			if (currentLife < this.maxLife){
			g.setColor(Color.BLACK);
			g.fillRect((int)(super.x - (Game.playerX - (World.numTilesWidth-2)/2*32)), (int) (super.y - (Game.playerY - (World.numTilesHeight-2)/2*32))-5, 34, 4);
			g.setColor(Color.GRAY);
			g.fillRect((int)(super.x - (Game.playerX - (World.numTilesWidth-2)/2*32))+1, (int) (super.y - (Game.playerY - (World.numTilesHeight-2)/2*32))-4, 32, 2);
			if(maxLife/currentLife <= 1) g.setColor(Color.GREEN);
			if(maxLife/currentLife > 1)  g.setColor(Color.ORANGE);
			if(maxLife/currentLife >=4)  g.setColor(Color.RED);
			g.fillRect((int)(super.x - (Game.playerX - (World.numTilesWidth-2)/2*32))+1, (int) (super.y - (Game.playerY - (World.numTilesHeight-2)/2*32))-4, (int)(currentLife*0.32), 2);
			}	
		}	
			//System.out.println(x+"|"+y);
	}
	
	public void getNextPosition() {
		super.getNextPosition();
	}
	
	public boolean validateNextPosition(ArrayList<Entity> entities, double xNew, double yNew) {
		return super.validateNextPosition(entities, xNew, yNew);
	}
}

