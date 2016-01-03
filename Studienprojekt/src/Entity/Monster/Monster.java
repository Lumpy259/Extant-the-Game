package Entity.Monster;

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
import Entity.Item.Boots_leather;
import Entity.Item.Chest_leather;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import Manager.Music;
import World.World;
import World.Tile.Tile;

public abstract class Monster extends Entity {

	private int maxLife, width, heigth;
	public  int currentLife;
	private int damage;
	public double x;
	public double y;
	private Random rnd = new Random();
	private HitBox hitBox;
	private double moveSpeed;
	private int attackrange;
	protected int MonsterXP;
	int direction;
	private int a = rnd.nextInt(3);
	private int laenge = 0;
	private int count = 0;
	public static int viewrange = (int)(Game.WIDTH*0.66);
	public Content image;	
	public  int mknockBackX = 0;
	public  int mknockBackY = 0;
	public int cooldown = 0;
	public int monstercooldown = 0;
	public HashMap<Class, Integer> loot;
	public static boolean checkmonster = false;
	
	
	public Monster(String name, String path, int width, int height, int damage, int maxLife, double x, double y, double moveSpeed, HitBox hitBox, Content image2, int attackrange) {
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
		}
	
	public void attack(ArrayList<Entity> entities) {
			Player.currentlife = Player.currentlife - ( (int)(damage*( 1-(((Math.log(Player.armor)/Math.log(2.0))*5 )/100))));
			/*
			this.currentLife = this.currentLife - Player.getPlayerDamage();
			if(this.currentLife <= 0) {
				Player.xp += this.MonsterXP;
				PlayState.removeEntity.add(this);
				
			}
			 */
				
			
	}


	public void update(InputHandler input, ArrayList<Entity> entities) {
		super.update(input, entities);
		if(this.currentLife <= 0) {
			Player.xp += this.MonsterXP;
			PlayState.removeEntity.add(this);
		}
		
		if (cooldown > 0)
			cooldown -= 10;
		if (monstercooldown > 0)
			monstercooldown -= 10;
		
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
		//if (validateNextPosition(entities, xNew, yNew))
		//	return;
		if(super.x > Game.playerX - viewrange && super.y < Game.playerY +viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){	
			if(super.x > Game.playerX - attackrange && super.y < Game.playerY + attackrange && super.x < Game.playerX + attackrange && super.y > Game.playerY - attackrange	){
				animation.setFreeze(false);
				checkmonster = true;
				if(super.y < Game.playerY-17 && !validateNextPosition(entities, super.x, super.y+moveSpeed)){
					super.y+=moveSpeed;	
					direction=0;
					//17
				}
				if(super.y > Game.playerY-17 && !validateNextPosition(entities, super.x, super.y-moveSpeed)){
					super.y-=moveSpeed;
					direction=3;
				}
	
				if(super.x < Game.playerX-16 && !validateNextPosition(entities, super.x+moveSpeed*1.2, super.y)){
					if(super.y < Game.playerY-7 && super.y > Game.playerY-27){
						super.x+=moveSpeed*1.2;	
						direction=2;
					}
					else{ super.x+=moveSpeed;
					}
					
					//System.out.println("r");
				}
	
				if(super.x > Game.playerX-16 && !validateNextPosition(entities, super.x-moveSpeed*1.2, super.y)){
					if(super.y < Game.playerY-7 && super.y > Game.playerY-27){
						super.x-=moveSpeed*1.2;	
						direction=1;
					}
					else{ super.x-=moveSpeed;
					}
				}
				if(super.y < Game.playerY-18 && super.y > Game.playerY-30 && super.x < Game.playerX+3 && super.x > Game.playerX-24 && monstercooldown == 0){
					Music.getMonsterHitSound();
					attack(entities);
					Player.knockBackY = 30;
					monstercooldown = 350;
					//direction=0;
				
				}
				if(super.y < Game.playerY && super.y > Game.playerY-16 && super.x < Game.playerX+3 && super.x > Game.playerX-24 && monstercooldown == 0){
					Music.getMonsterHitSound();
					attack(entities);
					Player.knockBackY = -30;
					monstercooldown = 350;
					//direction=3;
				}
				if(super.y < Game.playerY-7 && super.y > Game.playerY-27){
					if(super.x < Game.playerX-7 && super.x > Game.playerX-32 && monstercooldown == 0){
						Music.getMonsterHitSound();
						attack(entities);
						Player.knockBackX = 30;
						monstercooldown = 350;
						//direction=2;
					}
					if(super.x < Game.playerX+4 && super.x > Game.playerX-4 && monstercooldown == 0){
						Music.getMonsterHitSound();
						attack(entities);
						Player.knockBackX = -30;
						monstercooldown = 350;
						//direction=1;
					}
				}
				checkmonster = false;
			}
			else{
				animation.setFreeze(false);
				if(laenge>0){	
					checkmonster = true;
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
					checkmonster = false;
			}
		}
	}
	

	public void draw(Graphics2D g) {

		if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
			g.drawImage(image.getImage(direction,animation.getFrame()), (int)(super.x - (Game.playerX - (World.numTilesWidth-2)/2*32)),(int) (super.y - (Game.playerY - (World.numTilesHeight-2)/2*32)), null);
			
			//hitbox//g.setColor(Color.red);
			//hitbox//g.drawRect((int)(super.x - (Game.playerX - (World.numTilesWidth-2)/2*32)) + super.hitBox.x, (int) (super.y - super.hitBox.y - (Game.playerY - (World.numTilesHeight-2)/2*32)), this.width + super.hitBox.width, this.height + super.hitBox.y);
			//g.drawImage(image.getImage(direction,animation.getFrame()), (int)(super.x - (Game.playerX - 160)),(int) (super.y - (Game.playerY - 160)), null);
			//g.fillRect((int)(super.x - (Game.playerX - 160))+Slime.hitBox.x, (int) ((super.y - (Game.playerY - 160))-Slime.hitBox.y), Slime.hitBox.width, Slime.hitBox.height);
		
			
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

			/*
			g.setColor(Color.BLACK);
			g.fillRect((int)(super.x - (Game.playerX - 160)), (int) ((super.y - (Game.playerY - 160))-5), 34, 4);
			g.setColor(Color.GRAY);
			g.fillRect((int)(super.x - (Game.playerX - 160)+1), (int) ((super.y - (Game.playerY - 160))-4), 32, 2);
			if(maxLife/currentLife <= 1) g.setColor(Color.GREEN);
			if(maxLife/currentLife > 1)  g.setColor(Color.ORANGE);
			if(maxLife/currentLife >=4)  g.setColor(Color.RED);
			g.fillRect((int)(super.x - (Game.playerX - 160)+1), (int) ((super.y - (Game.playerY - 160))-4), (int)(currentLife*0.32), 2);
			*/
			
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

