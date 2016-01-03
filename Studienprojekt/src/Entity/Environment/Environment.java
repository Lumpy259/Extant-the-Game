package Entity.Environment;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Entity;
import Entity.HitBox;
import Entity.Player;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import World.World;

public abstract class Environment extends Entity {
	
	private int maxLife;
	public int currentLife;
	public static int viewrange = Game.WIDTH;
	public boolean remove = false;
	protected int environmentXP;
	public Content image;
	public int cooldown;
	public HashMap<Class, Integer> loot;
	
	public Environment(String name, String path, int width, int height, int maxLife, double x, double y, HitBox hitBox, Content image2) {
		super();
		this.maxLife = maxLife;
		this.currentLife = maxLife;
		//image = new Content(name, path, width, height, true);
		this.image = image2;
		super.x = x;
		super.y = y;
		super.height = height;
		super.width = width;
		super.hitBox  = hitBox;
		}
	
	public void update(InputHandler input, ArrayList<Entity> entities) {
		if(this.currentLife <= 0) {
			Player.xp += this.environmentXP;
			PlayState.removeEntity.add(this);
		}
		super.update(input, entities);
		if (cooldown > 0)
			cooldown -= 10;
	}
	
	public void draw(Graphics2D g) {//4x cast auf int reingemacht
		if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
			if (GameStateManager.currentState == GameStateManager.PLAY || GameStateManager.currentState == GameStateManager.TUTORIAL2 || GameStateManager.currentState == GameStateManager.INTRO){
				if(super.x > Game.playerX - viewrange && super.y < Game.playerY + viewrange && super.x < Game.playerX + viewrange && super.y > Game.playerY - viewrange){
					g.drawImage(image.getImage(0, 0), (int)((int)this.x - ((int)Game.playerX - (World.numTilesWidth-2)/2*32)),(int) ((int)this.y - ((int)Game.playerY - (World.numTilesHeight-2)/2*32)), null);
				}
			}
			else g.drawImage(image.getImage(0, 0), (int)this.x,(int) this.y, null);
		}
	}
	
	public void getNextPosition() {
		super.getNextPosition();
	}
	
	public boolean validateNextPosition(ArrayList<Entity> entities, double xNew, double yNew) {
		return super.validateNextPosition(entities, xNew, yNew);
	}
}
