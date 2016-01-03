package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;




import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.OvenandMerchantuse;

public class MerchantState extends GameState {

	private Content hud;
	public static boolean ovenentered = false;
	
	public MerchantState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("Inventory Merchant", "Resources/Inventory/trade.png", 352, 320, new Point(1,1));
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void update(InputHandler input) {
		if (OvenandMerchantuse.leaveoven == true && GameStateManager.getPrevState() == 1) {
			OvenandMerchantuse.leaveoven = false;
			PlayState.isthisaoven = false;
			gsm.setState(gsm.PLAY);
		}
		if (OvenandMerchantuse.leaveoven == true && GameStateManager.getPrevState() == 12) {
			OvenandMerchantuse.leaveoven = false;
			PlayState.isthisaoven = false;
			gsm.setState(gsm.HOUSE);
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
