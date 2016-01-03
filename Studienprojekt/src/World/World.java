package World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Entity;
import Entity.Player;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import World.Tile.Tile;

public class World {

	public static final int numTilesHeight = Game.HEIGHT/32 + 2;
	public static final int numTilesWidth = Game.WIDTH/32 + 2;
	private final int tileSize = 32;
	public ArrayList<HashMap<Point, Integer>> world = new ArrayList<>();
	
	public World() {		
		world.add(WorldGenerator.generateMap(500, 500, 500));
		//world.add(WorldGenerator.generateDungeon(500, 500, 500, 1));
		
	}
	
	public void generateLayer(int z) {
		world.add(WorldGenerator.generateDungeon(500, 500, 500, z));
	}
	
	public void setLadder(int z) {
		Point ladder = new Point((int)Entity.ladder.x / 32, (int)Entity.ladder.y / 32);
		for (int x = ladder.x-2; x<=ladder.x+1; x++)
			for (int y = ladder.y-2; y<=ladder.y+1; y++)
				world.get(z).put(new Point(x,y), Tile.STONE.id);
	}
	
	public void draw(Graphics2D g) {
		HashMap<Point, Integer> layer = world.get(Game.playerZ);
		
		
		
		int aktPosX = (int)(Game.playerX/tileSize);
		int aktPosY = (int)(Game.playerY/tileSize);
		
		if (Game.playerZ == 0)
		{
			for(int y = aktPosY - (numTilesHeight/2); y < aktPosY + numTilesHeight/2; y++) {
				for(int x = aktPosX - numTilesWidth/2; x < aktPosX + numTilesWidth/2; x++) {
					if (Tile.tiles[layer.get(new Point(x, y))].getAnimation() == null)
						g.drawImage(Tile.tiles[layer.get(new Point(x, y))].getImage(), (x+numTilesWidth/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
					else
						g.drawImage(Tile.tiles[layer.get(new Point(x, y))].getImage(0,Tile.tiles[layer.get(new Point(x, y))].animation.getFrame()), (x+numTilesWidth/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
					}
			}
		}
		else 
		{
			int numTilesHeight2 = numTilesHeight + 2-2;
			int numTilesWidth2 = numTilesWidth + 2-2;
			
			for(int y = aktPosY - (numTilesHeight2/2); y < aktPosY + numTilesHeight2/2; y++) 
				for(int x = aktPosX - numTilesWidth2/2; x < aktPosX + numTilesWidth2/2; x++) 
					g.drawImage(Tile.STONE.getImage(),  (x+numTilesWidth2/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight2/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
			
			//jeweils -1 gemacht
			for(int y = aktPosY - (numTilesHeight2/2)-1; y < aktPosY + numTilesHeight2/2; y++) 
				for(int x = aktPosX - numTilesWidth2/2-1; x < aktPosX + numTilesWidth2/2; x++) 
					if (Tile.tiles[layer.get(new Point(x, y))].id != Tile.STONE.id) {
						g.setColor(Color.black);
						g.drawImage(Tile.tiles[layer.get(new Point(x, y))].getImage(), (x+numTilesWidth2/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight2/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
						
						//Schwarz füllen
						//g.fillRect((x+numTilesWidth2/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight2/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, 32, 32);
						
						//Hitanimation
						if (Tile.hitTile != null && Tile.hitTile.x == x && Tile.hitTile.y == y)
							g.drawImage(Content.ANIMATION_DAMAGE10.getImage(0, Math.abs((int)(1.0*Tile.tiles[PlayState.world.world.get(Game.playerZ).get(Tile.hitTile)].currentLife / Tile.tiles[PlayState.world.world.get(Game.playerZ).get(Tile.hitTile)].maxLife * 10)-9)), (x+numTilesWidth2/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight2/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
			}
		}
	}
	
	public void draw2(Graphics2D g) {
		HashMap<Point, Integer> layer = world.get(0);
		
		int aktPosX = (int)(Game.playerX/tileSize);
		int aktPosY = (int)(Game.playerY/tileSize);
		
		for(int y = aktPosY - (numTilesHeight/2); y < aktPosY + numTilesHeight/2; y++) 
			for(int x = aktPosX - numTilesWidth/2; x < aktPosX + numTilesWidth/2; x++) 
				g.drawImage(Tile.STONE.getImage(),  (x+numTilesWidth/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+numTilesHeight/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
		
		int abc = numTilesHeight + 2;
		for(int y = aktPosY - (abc/2); y < aktPosY + abc/2; y++) 
			for(int x = aktPosX - numTilesWidth/2; x < aktPosX + numTilesWidth/2; x++) 
				if (Tile.tiles[layer.get(new Point(x, y))].id != Tile.STONE.id)
					g.drawImage(Tile.tiles[layer.get(new Point(x, y))].getImage(), (x+numTilesWidth/2-aktPosX) * tileSize - (int)Game.playerX%tileSize, (y+abc/2-aktPosY) * tileSize - (int)Game.playerY%tileSize, null);
		
	}
	//37/42
	
}
