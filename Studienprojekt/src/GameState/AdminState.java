package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;



import java.util.HashMap;

import Main.Game;
import Manager.Admin;
import Manager.Chestuse;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;

public class AdminState extends GameState {

	private Content hud;
	public static boolean chestentered = false;
	public Admin admin;	
	
	public AdminState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("Inventory", "Resources/Inventory/chest.png", 352, 320, new Point(1,1));
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		admin = new Admin();
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.DEBUG)  && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			//Chestuse.leavechest = false;
			gsm.setState(gsm.PLAY);
		}
		if (input.isPressed(input.DEBUG) && GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			//Chestuse.leavechest = false;
			gsm.setState(gsm.HOUSE);
		}
		if (input.isPressed(input.DEBUG) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			//Chestuse.leavechest = false;
			gsm.setState(gsm.TUTORIAL2);
		}
		admin.update(input);
	}

	public void draw(Graphics2D g) {	
		g.drawImage(hud.getImage(0,0), 80, 32, null);
		admin.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
