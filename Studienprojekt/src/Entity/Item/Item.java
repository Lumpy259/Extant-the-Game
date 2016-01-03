package Entity.Item;



import java.awt.Graphics2D;
import java.util.ArrayList;

import Entity.Entity;
import GameState.GameState;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import World.World;

public abstract class Item extends Entity implements Cloneable {

	public static int HELMET = 0;
	public static int CHEST = 1;
	public static int TROUSERS = 2;
	public static int BOOTS = 3;
	public static int WEAPON = 4;
	public static int AXE = 5;
	public static int FLASK = 6;
	public static int PLACEABLE = 7;
	public static int RWEAPON = 8;
	public static int RESSOURCE = 9;
	public static int CONSUMABLE = 10;
	public static int SHOVEL = 11;
	public static int PICKAXE = 12;
	public static int BUCKET = 13;
	public static int HOE = 14;
	public static int COMPASS = 15;
	public static int BOAT = 16;
	public static int FISHINGROD = 17;
	
	private int attackRange;
	private int damage;
	private int armor;
	
	public String name;
	private String path;
	
	private int type;
	public int stackable;
	
	private Content image;
	public static int viewrange = Game.WIDTH;
	
	public Item(String name, String path, int type, int attackRange, int damage, int armor, int stackable, Content image2) {
		this.name = name;
		this.path = path;
		this.type = type;
		this.attackRange = attackRange;
		this.damage = damage;
		this.armor = armor;
		this.stackable = stackable;
		image = image2;
	}

	public void update(InputHandler input, ArrayList<Entity> entities) {
		
	}
	
	public void draw(Graphics2D g) {
		if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
			if (GameStateManager.currentState == GameStateManager.PLAY){
				if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
					//Items auf Map kleiner
					g.drawImage(image.getImage(0, 0), (int)(this.x - (Game.playerX - (World.numTilesWidth-2)/2*32)),(int) (this.y - (Game.playerY - (World.numTilesHeight-2)/2*32)), 28, 28, null);
				}
			}
			else g.drawImage(image.getImage(0, 0), (int)this.x,(int) this.y, null);
		}
	}
	
	public Object clone() {
        try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
	public String getName() { return this.name; }
	public int getStackable() { return this.stackable; }
	public Content getImage() { return this.image; }
	public int getType() { return this.type; }
	public int getArmor() { return this.armor; }
	public int getDamage() { return this.damage; }
	//public Item newItem() { return new Item(this.name, this.path, this.type, this.attackRange, this.damage, this.armor, this.stackable, this.image);}
}
