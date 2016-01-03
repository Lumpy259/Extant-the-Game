package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Entity.Item.Item;
import Entity.Item.Sword_Iron;
import Entity.Item.Sword_Wood;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InGameMenue;
import Manager.InputHandler;
import Manager.Inventory;

public class InGameMenueState extends GameState {
	
	

	private Content hud;
	private InGameMenue menue;

	
	public InGameMenueState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("InGameMenue", "Resources/Menu/InGameMenue1.jpeg", 282, 256, new Point(1,1));
		menue = new InGameMenue(gsm);
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		//inventory = new Inventory();
		//sword = new Sword_Wood();
		//inventory.addItem(sword);

				
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.INGAMEMENUE) && (GameStateManager.getPrevState() == GameStateManager.PLAY || InGameMenue.enteredstate == GameStateManager.PLAY)) {
			gsm.setState(gsm.PLAY);
		}
		if (input.isPressed(input.INGAMEMENUE) && (GameStateManager.getPrevState() == GameStateManager.HOUSE || InGameMenue.enteredstate == GameStateManager.HOUSE)) {
			gsm.setState(gsm.HOUSE);
		}
		if (input.isPressed(input.INGAMEMENUE) && (GameStateManager.getPrevState() == GameStateManager.TUTORIAL2 || InGameMenue.enteredstate == GameStateManager.TUTORIAL2)) {
			gsm.setState(gsm.TUTORIAL2);
		}
		menue.update(input);
	}

	public void draw(Graphics2D g) {		
		menue.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
