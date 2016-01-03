package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.OvenandMerchantuse;

public class OvenState extends GameState {

	private Content hud;
	public static boolean ovenentered = false;
	
	public OvenState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("Inventory Oven", "Resources/Inventory/oven.png", 352, 320, new Point(1,1));
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void update(InputHandler input) {
		if (OvenandMerchantuse.leaveoven == true && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			OvenandMerchantuse.leaveoven = false;
			PlayState.isthisaoven = false;
			OvenandMerchantuse.ovenanimation = 0;
			OvenandMerchantuse.futureoven=false;
			gsm.setState(gsm.PLAY);
		}
		if (OvenandMerchantuse.leaveoven == true && GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			OvenandMerchantuse.leaveoven = false;
			PlayState.isthisaoven = false;
			OvenandMerchantuse.ovenanimation = 0;
			OvenandMerchantuse.futureoven=false;
			gsm.setState(gsm.HOUSE);
		}
		if (OvenandMerchantuse.leaveoven == true && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			OvenandMerchantuse.leaveoven = false;
			TutorialState2.isthisaoven = false;
			OvenandMerchantuse.ovenanimation = 0;
			OvenandMerchantuse.futureoven=false;
			gsm.setState(gsm.TUTORIAL2);
		}
		PlayState.ovenuse.update(input);
	}

	public void draw(Graphics2D g) {	
		g.drawImage(hud.getImage(0,0), 80, 32, null);
		PlayState.ovenuse.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}
