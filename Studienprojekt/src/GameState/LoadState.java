package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Entity.Player;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Gameover;
import Manager.InputHandler;
import Manager.Status;

public class LoadState extends GameState {
	
	//private Content load;
	private static int count = 0;
	private static int ucount = 0;
	
	private static Content[] images = new Content[8];
	private static Content[] uimages = new Content[5];
	
	public LoadState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		images[0] = new Content("", "Resources/Load/image0.png", 512, 384, new Point(1,1));
		images[1] = new Content("Erzeuge Planet", "Resources/Load/image1.png", 512, 384, new Point(1,1));
		images[2] = new Content("Pflanze Gras an...", "Resources/Load/image2.png", 512, 384, new Point(1,1));
		images[3] = new Content("Regen...", "Resources/Load/image3.png", 512, 384, new Point(1,1));
		images[4] = new Content("Schalte Licht an...", "Resources/Load/image4.png", 512, 384, new Point(1,1));
		images[5] = new Content("Pflanze Bäume...", "Resources/Load/image5.png", 512, 384, new Point(1,1));
		images[6] = new Content("Erschaffe Felsen...", "Resources/Load/image6.png", 512, 384, new Point(1,1));
		images[7] = new Content("Monster werden geboren...", "Resources/Load/image7.png", 512, 384, new Point(1,1));
		
		uimages[0] = new Content("", "Resources/Load/underground1.png", 512, 384, new Point(1,1));
		uimages[1] = new Content("", "Resources/Load/underground2.png", 512, 384, new Point(1,1));
		uimages[2] = new Content("", "Resources/Load/underground3.png", 512, 384, new Point(1,1));
		uimages[3] = new Content("", "Resources/Load/underground4.png", 512, 384, new Point(1,1));
		uimages[4] = new Content("", "Resources/Load/underground4.png", 512, 384, new Point(1,1));
		//load = new Content("Load", "Resources/Menu/LoadScreen.jpeg", 512, 384, true);
	}
	
	public void update(InputHandler input) {
		gsm.setState(gsm.PLAY);
	}

	public void draw(Graphics2D g) {		
		g.drawImage(images[count].getImage(0, 0), 0, 0, null);
		g.setColor(Color.WHITE);
		Game.game.g.drawString(images[count].getName(), 200, 120);
	}

	public static void generateLoadingScreen() {
		count++;
		
		if (count >= 8)
			count = 0;
		
		Game.game.g.drawImage(images[count].getImage(0, 0), 0, 0, null);
		Game.game.g.drawString(images[count].getName(), 200, 120);
		Game.game.drawToScreen();
	}
	
	public static void generateUndergroundLoadingScreen() {
		
		Game.game.g.drawImage(uimages[ucount].getImage(0, 0), 0, 0, null);
		//Game.game.g.drawString(uimages[ucount].getName(), 200, 120);
		Game.game.drawToScreen();
		ucount++;
		if (ucount >= 5)
			ucount = 0;
	}
	
	public void handleInput(InputHandler input) {	
		
	}

}
