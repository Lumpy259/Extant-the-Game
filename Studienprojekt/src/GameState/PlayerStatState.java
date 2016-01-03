package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Status;

public class PlayerStatState extends GameState {

	private Content playerStats;
	private Status status;
	
	public PlayerStatState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		playerStats = new Content("PlayerStats", "Resources/PlayerStats/PlayerStatus.png", 352, 320, new Point(1,1));
		status = new Status();
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.TUTORIAL) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL || input.isPressed(input.TUTORIAL) && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			TutorialState.dialogCounter=PlayState.tutorialnumber;
			gsm.setState(gsm.TUTORIAL);
			PlayState.tutorialnumber++;
		}
		if (input.isPressed(input.STATUS) && GameStateManager.getPrevState() == GameStateManager.PLAY || input.isPressed(input.STATUS) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL) {
			gsm.setState(gsm.PLAY);
		}
		if (input.isPressed(input.STATUS) && GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			gsm.setState(gsm.HOUSE);
		}
		if (input.isPressed(input.STATUS) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			TutorialState2.wasInStatus=true;
			gsm.setState(gsm.TUTORIAL2);
		}
		status.update(input);
		
	}

	public void draw(Graphics2D g) {		
		g.drawImage(playerStats.getImage(0,0), 80, 32, null);
		status.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
