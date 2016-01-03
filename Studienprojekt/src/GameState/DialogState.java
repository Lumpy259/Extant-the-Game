package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entity.Entity;
import Entity.Player;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;

public class DialogState extends GameState {

	ArrayList<ArrayList<String>> dialog = new ArrayList<>();
	public static int dialogCounter = 0;
	public static int inDialogCounter = 0;
	public static int yuiDialogCounter = 5;
	private Content  cow,  sheep, hero, yui;
	public static boolean yuidialog = false;
	

	public DialogState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		ArrayList dialog1 = new ArrayList<>();
		dialog1.add("M‰‰‰‰‰hh");
		dialog1.add("Ich weiﬂ wirklich nicht was ich eigentlich erwartet habe ...");
	
		ArrayList dialog2 = new ArrayList<>();
		dialog2.add("Muuuuh");
		dialog2.add("Was hab ich mir dabei eigentlich gedacht? ...");
		
		ArrayList dialog3 = new ArrayList<>();
		dialog3.add("Bist du schon m¸de? "
				+ "\nDie Sonne ist noch garnicht unter gegangen.");
		
		ArrayList dialog4 = new ArrayList<>();
		dialog4.add("Diese Gegenst‰nde kannst du nur in einem Haus aufstellen.");
		
		ArrayList dialog5 = new ArrayList<>();
		dialog5.add("Du hast keine Munition mehr.");
		
		ArrayList dialog100 = new ArrayList<>();
		dialog100.add("Klopf, Klopf");
		dialog100.add("Sei still du Lauch!");
		
		ArrayList dialog101 = new ArrayList<>();
		dialog101.add("Klopf?");
		dialog101.add("Noch einmal und es klatscht, aber keinen Beifall!");
		
		
		dialog.add(dialog1);
		dialog.add(dialog2);
		dialog.add(dialog3);
		dialog.add(dialog4);
		dialog.add(dialog5);
		dialog.add(dialog100);
		dialog.add(dialog101);
		
		cow = new Content("Kuh", "Resources/Dialog/cow_face.png", 70, 70, new Point(1,1)); 
		sheep = new Content("Schaaf", "Resources/Dialog/sheep_face.png", 70, 70, new Point(1,1));
		hero = new Content("Hero", "Resources/Intro/Hero.png", 70, 70, new Point(1,1)); 
		yui = new Content("Hero", "Resources/Intro/Yui.png", 70, 70, new Point(1,1)); 
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.SPACE))
			inDialogCounter++;
		if (Player.usewhat == Entity.SHEEP) dialogCounter=0;
		if (Player.usewhat == Entity.COW) dialogCounter=1;
		if (Player.usewhat == Entity.BED) dialogCounter=2;
		if (Player.usewhat == Entity.NOTPLACEABLE) dialogCounter=3;
		if (Player.usewhat == Entity.NOAMMO) dialogCounter=4;
		Player.usewhat=Entity.ZERO;
		if (yuidialog==true) dialogCounter = yuiDialogCounter;
		if (inDialogCounter > dialog.get(dialogCounter).size()-1) {
			inDialogCounter = 0;
			if (yuidialog==true){
				if (yuiDialogCounter == 6){
					yuiDialogCounter = 5;
				}
				else yuiDialogCounter++;
				yuidialog=false;
			}
			else dialogCounter = 0;
			if (GameStateManager.getPrevState() == GameStateManager.PLAY)gsm.setState(gsm.PLAY);
			if (GameStateManager.getPrevState() == GameStateManager.HOUSE)gsm.setState(gsm.HOUSE);
			if (GameStateManager.getPrevState() == GameStateManager.BOSS)gsm.setState(gsm.BOSS);
			if (GameStateManager.getPrevState() == GameStateManager.TUTORIAL2)gsm.setState(gsm.TUTORIAL2);
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 304, Game.WIDTH, 80);
		g.setColor(Color.PINK);
		g.fillRect(5,309, 70, 70);
		g.setFont(new Font("Courier New", Font.PLAIN, 11));
		g.setColor(Color.WHITE);
		drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 80, 319);
		if(dialogCounter==0 && inDialogCounter%2 ==1) g.drawImage(hero.getImage(0,0), 5, 309, null);
		if(dialogCounter==0 && inDialogCounter%2 ==0) g.drawImage(sheep.getImage(0,0), 5, 309, null);
		if(dialogCounter==1 && inDialogCounter%2 ==1) g.drawImage(hero.getImage(0,0), 5, 309, null);
		if(dialogCounter==1 && inDialogCounter%2 ==0) g.drawImage(cow.getImage(0,0), 5, 309, null);
		if(dialogCounter>=2 && dialogCounter<5) g.drawImage(yui.getImage(0,0), 5, 309, null);
		if(dialogCounter>4 && inDialogCounter%2 ==0) g.drawImage(hero.getImage(0,0), 5, 309, null);
		if(dialogCounter>4 && inDialogCounter%2 ==1) g.drawImage(yui.getImage(0,0), 5, 309, null);
	}

    
	public void handleInput(InputHandler input) {
		
	}
	
	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

}
