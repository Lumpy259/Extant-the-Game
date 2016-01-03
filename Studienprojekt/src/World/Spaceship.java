package World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Entity;
import Entity.Player;
import Entity.Item.Slime_gold;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import World.Tile.Tile;

public class Spaceship {

	public static final int numTilesHeight = Game.HEIGHT/32 + 2;
	public static final int numTilesWidth = Game.WIDTH/32 + 2;
	private final int tileSize = 32;
	public HashMap<Point, Integer> spaceship = new HashMap<Point, Integer>();
	
	public Spaceship() {	
		for (int x=0; x<500; x++)
			for (int y=0; y<500; y++) 
				spaceship.put(new Point(x,y), 1);		
	}
	
	public void draw(Graphics2D g) {

		int aktPosX = (int)(Game.playerX/tileSize);
		int aktPosY = (int)(Game.playerY/tileSize);
		
		if (Game.playerZ == 0)
		{
			for(int y = aktPosY - (numTilesHeight/2); y < aktPosY + numTilesHeight/2; y++) {
				for(int x = aktPosX - numTilesWidth/2; x < aktPosX + numTilesWidth/2; x++) {
					if (Tile.tiles[spaceship.get(new Point(x, y))].getAnimation() == null)
						g.drawImage(Tile.tiles[spaceship.get(new Point(x, y))].getImage(), (x+numTilesWidth/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
					else
						g.drawImage(Tile.tiles[spaceship.get(new Point(x, y))].getImage(0,Tile.tiles[spaceship.get(new Point(x, y))].animation.getFrame()), (x+numTilesWidth/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
					}
			}
		}
	}	
}