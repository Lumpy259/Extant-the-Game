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

public class TutorialState extends GameState {

	ArrayList<ArrayList<String>> dialog = new ArrayList<>();
	public static int dialogCounter = 0;
	public static int inDialogCounter = 0;
	private Content yui;
	private int dialogCounterprev = 0;

	public TutorialState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {


	
		ArrayList dialog0 = new ArrayList<>();
		dialog0.add("Markiere ein Item und Drücke Leertaste"
				+"\num es zu fixieren. Jetzt kannst du das Item" +
				"\nin dein Inventar verschieben und mit mit Leertaste bestätigen");
		ArrayList dialog1 = new ArrayList<>();
		dialog1.add("Gehe zur Kiste und Drücke E um den Inhalt" +
				"\nder Kiste zu sehen.");
		
				
		ArrayList dialog2 = new ArrayList<>();
		dialog2.add("Im Inventar kannst du nicht nur deine Equipment sehen"
				+ "\nsondern kannst auch links oben deine Ruestung auswaehlen."
				+ "\ndesweiteren kannst du rechts oben Craften.");
		dialog2.add("Mit der Leerzeichen Taste kannst du Objekte auswaehlen."
				+ "\nAusgewaehlte Items werden dir Links unter der Ruestung"
				+ "\nangezeigt. Jetzt kannst du die Items verschieben z.B in  "
				+ "\ndein HUD durch weiteres druecken der Leertaste ");
		dialog2.add("wird dein Item an der aktuellen Stelle plaziert."
				+ "\nAchtung: Dein Item wird an dieser Stelle plaziert,"
				+ "\nsollte ein anderes Item an der Stelle sein, so"
				+ "\nkommt dieses Item in die aktuelle Auswahl!");
		dialog2.add("Durch erneutes Druecken der Taste I schliesst du das Inventar");

		
		ArrayList dialog3 = new ArrayList<>();
		dialog3.add("Hier siehst du dein Aktuelles Level und deine erspielten"
				+ "\nPunkte. ");
		dialog3.add("Deine Erspielten Punkte kannst du zur individuellen"
				+ "\nAufwertung deines Charakters verwenden.");
		dialog3.add("Um die Punkte zu verteilen Navigiere zum Feld das du gerne"
				+ "\naufwerten moechtest. Mit den Pfeiltasten(Links und Rechts)"
				+ "\nkannst du jetzt die Punkte verteilen und mit der Leertaste"
				+ "\nbest�tigen.");
		ArrayList dialog4 = new ArrayList<>();
		dialog4.add("Du solltest zuerst das Inventar unter die Lupe nehmen!");
		
		ArrayList dialog5 = new ArrayList<>();
		dialog5.add("Du solltest zuerst das Statusmenue unter die Lupe nehmen!");
		
		
		ArrayList dialog6 = new ArrayList<>();
		dialog6.add("Das wars zur Theorie, jetzt kann gespielt werden!"
				+ "\nRueste dich zunaechst mit einer Axt aus und faelle einen Baum.");
		dialog6.add("Ueber das HUD kannst du mittels Schnelltasten von 1 - 9 dein"
				+ "\nbenoetigtes Item auswaehlen."
				+ "\nBegib dich jetzt zu einem Baum und faelle ihn indem du mehrmals"
				+ "\nauf die Leertaste drueckst.");
		dialog6.add("Je besser deine Items sind desto schneller geht der Abbau."
				+ "\nNach dem faellen des Baums kannst du den Drop einsammeln und"
				+ "\ner steht dir in deinem Inventar Verfuegung.");

		
		ArrayList dialog7 = new ArrayList<>();
		dialog7.add("Behalte die Uhrzeit im Auge! Die Nacht birgt einige Gefahren."
				+ "\nDie Uhr wird rechts unten im Bildschirm angezeigt. Der blaue"
				+ "\nBereich auf der Uhr zeigt dir wann Nacht ist.");
		
		ArrayList dialog8 = new ArrayList<>();
		dialog8.add("Links unten im Bildschirm findest du deine Lebensanzeige."
				+ "\nDu kannst mittels Heiltrank dein Leben auff�llen.");
		
		ArrayList dialog9 = new ArrayList<>();
		dialog9.add("Markiere ein Item und Drücke Leertaste"
				+"\num es zu fixieren. Jetzt kannst du das Item" +
				"\nin dein Inventar verschieben und mit mit Leertaste bestätigen");


		dialog.add(dialog0);
		dialog.add(dialog1);
		dialog.add(dialog2);
		dialog.add(dialog3);
		dialog.add(dialog4);
		dialog.add(dialog5);
		dialog.add(dialog6);
		dialog.add(dialog7);
		dialog.add(dialog8);
		dialog.add(dialog9);


		
		yui = new Content("Hero", "Resources/Intro/Yui.png", 70, 70, new Point(1,1)); 

		
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.SPACE))
			inDialogCounter++;
		if(GameStateManager.getPrevState() == GameStateManager.INVENTORY){
			dialogCounterprev = dialogCounter;
			dialogCounter = 2;
			PlayState.tutorialnumber=2;
			PlayState.wasInInventory = true;
		}
		if(GameStateManager.getPrevState() == GameStateManager.STATUS){
			dialogCounterprev = dialogCounter;
			dialogCounter = 3;
			PlayState.tutorialnumber=3;
			PlayState.wasInStatus = true;
		}
		if(GameStateManager.getPrevState() == GameStateManager.PLAY && dialogCounter > 5){
			if(PlayState.wasInInventory == false){
				dialogCounter = 4;
				PlayState.tutorialnumber=1;
			}
			if(PlayState.wasInStatus == false){
				dialogCounter = 5;
				PlayState.tutorialnumber=1;
			}
		}
		
		if(GameStateManager.getPrevState() == GameStateManager.TUTORIAL2){
			dialogCounterprev = dialogCounter;
			dialogCounter = 1;
			//inDialogCounter++;
			//PlayState.tutorialnumber=2;
		}

		if (inDialogCounter > dialog.get(dialogCounter).size()-1) {
			inDialogCounter = 0;			
			 dialogCounter = 0;
			if (GameStateManager.getPrevState() == GameStateManager.PLAY)gsm.setState(gsm.PLAY);
			if (GameStateManager.getPrevState() == GameStateManager.HOUSE)gsm.setState(gsm.HOUSE);
			if (GameStateManager.getPrevState() == GameStateManager.BOSS)gsm.setState(gsm.BOSS);
			if (GameStateManager.getPrevState() == GameStateManager.INVENTORY)gsm.setState(gsm.INVENTORY);
			if (GameStateManager.getPrevState() == GameStateManager.STATUS)gsm.setState(gsm.STATUS);
			if (GameStateManager.getPrevState() == GameStateManager.CHEST)gsm.setState(gsm.CHEST);
			if (GameStateManager.getPrevState() == GameStateManager.TUTORIAL2)gsm.setState(gsm.TUTORIAL2);

			
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 304, Game.WIDTH, 80);
		g.setColor(Color.PINK);
		g.fillRect(5,309, 70, 70);
		g.setFont(new Font("Arial", Font.PLAIN, 11));
		g.setColor(Color.WHITE);
		
		/*if (GameStateManager.getPrevState() == GameStateManager.INVENTORY){		
			drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 80, 319); 
			dialogCounter = dialogCounterprev;
			PlayState.tutorialnumber=dialogCounter;
			
		}
		else if (GameStateManager.getPrevState() == GameStateManager.STATUS){
			drawString(g, dialog.get(3).get(inDialogCounter), 80, 319); 
			dialogCounter = dialogCounterprev;
			PlayState.tutorialnumber=dialogCounter;
		}
		else if (dialogCounter == 2 && GameStateManager.getPrevState() != GameStateManager.INVENTORY || dialogCounter == 3 && GameStateManager.getPrevState() != GameStateManager.STATUS){
			dialogCounter = 6;
			PlayState.tutorialnumber=dialogCounter;
		}
		else*/
		
		if(GameStateManager.getPrevState() == GameStateManager.TUTORIAL2){
			drawString(g, dialog.get(1).get(inDialogCounter), 80, 319); 

			
		}else
			
		drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 80, 319);
		g.drawImage(yui.getImage(0,0), 5, 309, null);		
		}

    
	public void handleInput(InputHandler input) {
		
	}
	
	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

}
