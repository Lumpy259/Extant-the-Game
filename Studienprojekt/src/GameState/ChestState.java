package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;



import java.util.HashMap;

import Main.Game;
import Manager.Chestuse;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;

public class ChestState extends GameState {

	private Content hud;
	public static boolean chestentered = false;
	//private boolean wasinChest = false;
		
	public ChestState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("Inventory", "Resources/Inventory/chest.png", 352, 320, new Point(1,1));
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void update(InputHandler input) {
		if (GameStateManager.getPrevState() == GameStateManager.TUTORIAL2 && TutorialState2.wasinchest == false){
			TutorialState.dialogCounter=9;
			TutorialState2.wasinchest = true;
			gsm.setState(gsm.TUTORIAL);		
		}

		if (Chestuse.leavechest == true && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			Chestuse.leavechest = false;
			gsm.setState(gsm.PLAY);
		}
		if (Chestuse.leavechest == true && GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			Chestuse.leavechest = false;
			gsm.setState(gsm.HOUSE);
		}
		if (Chestuse.leavechest == true && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			Chestuse.leavechest = false;
			TutorialState2.isback = true;
			gsm.setState(gsm.TUTORIAL2);
		}
		if (Chestuse.leavechest == true && GameStateManager.getPrevState() == GameStateManager.TUTORIAL) {
			Chestuse.leavechest = false;
			TutorialState2.isback = true;
			gsm.setState(gsm.TUTORIAL2);
		}
		PlayState.chestuse.update(input);
	}

	public void draw(Graphics2D g) {	
		g.drawImage(hud.getImage(0,0), 80, 32, null);
		PlayState.chestuse.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
