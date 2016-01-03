package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;




import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;

public class InventoryState extends GameState {

	private Content hud;
	
	public InventoryState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("Inventory", "Resources/Inventory/Inventar.png", 352, 320, new Point(1,1));
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void update(InputHandler input) {
		if ((input.isPressed(input.TUTORIAL) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL || input.isPressed(input.TUTORIAL) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL) {
			TutorialState.dialogCounter=PlayState.tutorialnumber;
			gsm.setState(gsm.TUTORIAL);
			PlayState.tutorialnumber++;
		}
		if ((input.isPressed(input.TUTORIAL) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.PLAY || input.isPressed(input.INVENTORY) && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			gsm.setState(gsm.PLAY);
		}
		if ((input.isPressed(input.TUTORIAL) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			gsm.setState(gsm.HOUSE);
		}
		if ((input.isPressed(input.TUTORIAL) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			TutorialState2.wasInInventory=true;
			gsm.setState(gsm.TUTORIAL2);
		}
		PlayState.inventory.update(input);
	}

	public void draw(Graphics2D g) {	
		g.drawImage(hud.getImage(0,0), 80, 32, null);
		PlayState.inventory.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
