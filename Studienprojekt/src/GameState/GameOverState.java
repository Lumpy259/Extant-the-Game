package GameState;

import java.awt.Graphics2D;
import java.awt.Point;

import Entity.Player;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Gameover;
import Manager.InputHandler;
import Manager.Status;

public class GameOverState extends GameState {
	
	private Content over;
	private Gameover gameover;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		gameover = new Gameover();
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.ENTER)) {
			Player.knockBackX = 0;
			Player.knockBackY = 0;
			Player.xp = 0;
			Player.bittime = 0;
			Player.fishingtime = 0;
			Player.bucketingtime = 0;
			Player.wateringtime = 0;
			Player.eatingtime = 0;
			Player.drinkingtime = 0;
			Player.swimming = false;
			Player.hitAnimation = 0;	
			Player.hoeAnimation = 0;	
			Player.axeAnimation = 0;	
			Player.pickaxeAnimation = 0;
			Player.shovelAnimation = 0;	
			Player.rhitAnimation = 0;
			Player.BowhitAnimation = 0;
			Player.EgghitAnimation = 0;
			Player.monsterhitAnimation = 0;
			Player.currentlife = Player.maxlife;
			Game.playerX = Player.spawnpointX;
			Game.playerY = Player.spawnpointY;
			gsm.setState(gsm.PLAY);
		}
		gameover.update(input);
	}

	public void draw(Graphics2D g) {		
		gameover.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
