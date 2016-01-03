package Entity.Projectile;

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
import GameState.BossState;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import Manager.Music;
import World.World;
import World.Tile.Tile;

public abstract class Projectile extends Entity {

	private int maxLife, width, heigth;
	public  int currentLife;
	public int damage;
	private double x, y;
	private Random rnd = new Random();
	private HitBox hitBox;
	private double moveSpeed;
	private double moveSpeedY;
	private int flyrange;
	int direction;
	private int a = rnd.nextInt(3);
	private int laenge = 0;
	private int count = 0;
	public static int viewrange = (int)(Game.WIDTH*0.66);
	public Content image;	
	public  int mknockBackX = 0;
	public  int mknockBackY = 0;
	public int cooldown = 0;
	public static boolean checkmonster = false;
	public int angle;
	
	
	public Projectile(String name, String path, int width, int height, int damage, double x, double y, double moveSpeed, int angle, HitBox hitBox, Content image2, int flyrange) {
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
		this.angle = angle;
		this.moveSpeedY = moveSpeedY;
		this.flyrange = flyrange;
	}
	
	public void attack(ArrayList<Entity> entities) {
		Player.currentlife = Player.currentlife - ( (int)(damage*( 1-(((Math.log(Player.armor)/Math.log(2.0))*5 )/100))));			
	}


	public void update(InputHandler input, ArrayList<Entity> entities) {
		if (cooldown > 0) cooldown-=10;
		flyrange -= 1;//Math.abs(moveSpeed) + Math.abs(moveSpeedY);
		if (flyrange <= 0)	BossState.removeEntity.add(this);
		super.update(input, entities);
		validateNextPosition(entities, super.x, super.y);
		//super.x += 3*moveSpeedX;
		//super.y += 3*moveSpeedY;
		super.x += moveSpeed*Math.sin(Math.toRadians(angle));
		super.y += moveSpeed*Math.cos(Math.toRadians(angle));
		
	}
	

	public void draw(Graphics2D g) {
		g.drawImage(image.getImage(), (int)super.x, (int)super.y, null);
	}
	
	public void getNextPosition() {
		super.getNextPosition();
	}
	
	public boolean validateNextPosition(ArrayList<Entity> entities, double xNew, double yNew) {
		return super.validateNextPosition(entities, xNew, yNew);
	}
}

