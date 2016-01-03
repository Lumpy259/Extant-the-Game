package GameState;


import java.awt.Graphics2D;
import java.awt.Point;

import Entity.Item.Item;
import Entity.Item.Sword_Iron;
import Entity.Item.Sword_Wood;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InGameMenue;
import Manager.InputHandler;
import Manager.Inventory;
import Manager.OptionsMenu;

public class OptionState extends GameState {
	
	

	private Content hud;
	private OptionsMenu options;

	
	public OptionState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("InGameMenue", "Resources/Menu/Menu_Controlling.jpeg", 512, 384, new Point(1,1));
		options = new OptionsMenu(gsm);

				
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.INGAMEMENUE) && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			gsm.setState(gsm.PLAY);
			InGameMenue.entered = false;
		}
		if (input.isPressed(input.INGAMEMENUE) && InGameMenue.enteredstate == GameStateManager.HOUSE) {
			gsm.setState(gsm.HOUSE);
			InGameMenue.entered = false;
		}
		if (input.isPressed(input.INGAMEMENUE) && GameStateManager.getPrevState() == GameStateManager.MENU) {
			gsm.setState(gsm.MENU);
		}
		if (input.isPressed(input.INGAMEMENUE) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			gsm.setState(gsm.TUTORIAL2);
			InGameMenue.entered = false;
		}
		options.update(input);
	}

	public void draw(Graphics2D g) {		
		options.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
