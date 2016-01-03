package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import Entity.Player;
import Entity.Item.InventoryItem;
import GameState.BossState;
import GameState.PlayState;
import GameState.TutorialState2;
import Main.Game;

public class Hud {

	public static Content hud = new Content ("HUD", "Resources/HUD/HUD.png", 512, 80, new Point(1,1));
	public static Content clock = new Content ("CLOCK", "Resources/HUD/clock.png", 75, 75, new Point(1,1));
	
	public Hud() {
		
	}
	
	
	public static void draw(Graphics2D g) {
		//g.drawString((int)Game.playerX + "|" + (int)Game.playerY, 0, 10);
		
		g.setColor(Color.ORANGE);
		g.fillRect(97, 338, 318, 5);
		g.setColor(Color.RED);
		g.fillRect(97, 338, (int)(1.0 * (Player.xp-Player.calculateMinXP()) / Player.calculateXP() * 318), 5);
		
		g.setColor(Color.BLACK);
		g.setClip (new Rectangle (20, Game.HEIGHT-80, 74, 74));
		g.fillOval(20,Game.HEIGHT-80,73,73);
		g.setClip(null);
		
		g.setColor(Color.RED);
		g.setClip (new Rectangle (20, Game.HEIGHT-(int)(80.0 / Player.maxlife * Player.currentlife), 74, 74));
		g.fillOval(20,Game.HEIGHT-80,73,73);
		g.setClip(null);
		
		g.drawImage(hud.getImage(0,0), 0, Game.HEIGHT-80, null);
		
		g.drawImage(clock.getrotatedImage(clock.getImage(0,0), (int)(360.0/Game.DAY*(Game.TIME%Game.DAY))), 417, Game.HEIGHT-80, null);
		
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
		if(GameStateManager.currentState == GameStateManager.PLAY || GameStateManager.currentState == GameStateManager.BOSS)
			g.drawRect(97 + (PlayState.activeShortCut-1) * 32, 344, 29, 29);
		if(GameStateManager.currentState == GameStateManager.TUTORIAL2)
			g.drawRect(97 + (TutorialState2.activeShortCut-1) * 32, 344, 29, 29);
		
		g.setColor(Color.WHITE);
		for (int i=1;i<11;i++)
			g.drawString(""+i%10, 110 + (i-1) * 32, 384);
	}
}
