package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Player;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Music;

public class IntroState extends GameState {

	public ArrayList<Integer> name = new ArrayList<>();
	private Content  intro1,  intro2, intro3, intro4, intro5, intro6, intro7, intro8, intro9, yui, hero;
	private int state;
	ArrayList<ArrayList<String>> dialog = new ArrayList<>();
	int dialogCounter = 0;
	int inDialogCounter = 0;
	String playerName = "";
	
	public IntroState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		intro1 = new Content("Intro1", "Resources/Intro/Intro1.png", 512, 304, new Point(1,1));
		intro2 = new Content("Intro2", "Resources/Intro/Intro2.png", 512, 304, new Point(1,1));
		intro3 = new Content("Intro3", "Resources/Intro/Intro3.png", 512, 304, new Point(1,1));
		intro4 = new Content("Intro4", "Resources/Intro/Intro4.png", 512, 304, new Point(1,1));
		intro5 = new Content("Intro5", "Resources/Intro/Intro5.png", 512, 304, new Point(1,1));
		intro6 = new Content("Intro6", "Resources/Intro/Intro6.png", 512, 304, new Point(1,1));
		intro7 = new Content("Intro7", "Resources/Intro/Intro7.png", 512, 304, new Point(1,1));
		intro8 = new Content("Intro7", "Resources/Intro/Intro8.png", 512, 304, new Point(1,1));
		intro9 = new Content("Intro7", "Resources/Intro/Intro9.png", 512, 304, new Point(1,1));
		yui = new Content("Yui", "Resources/Intro/Yui.png", 70, 70, new Point(1,1));
		hero = new Content("Hero", "Resources/Intro/Hero.png", 70, 70, new Point(1,1)); 
		state = 1;
		ArrayList dialog1 = new ArrayList<>();
		dialog1.add("Wir befinden uns im Jahr 2834 auf einem Forschungschiff, das vom "
				+ "\nPlaneten Erde auf eine Mission gesandt wurde um neues Leben zu"
				+ "\nentdecken.");
		dialog1.add("Es wurden bereits viele neue Lebensformen katalogisiert, es gibt jedoch"
				+ "\nHinweiße auf Leben auf dem Planeten TI102 im Sonnensystem Aldebaran."
				+ "\nDie gut ausgebildete Mannschaft soll den Planeten erforschen und"
				+ "\nKontakt zu möglichen Lebensformen herstellen.");
		dialog1.add("Doch etwas stimmt mit dem Raumschiff nicht, die Geschwindkeit verringert"
				+ "\nsich stetig. Aber das Ziel ist schon in Sicht und die Crew"
				+ "\nentscheidet sich dafür den Landeanflug zu beginnen.");
		dialog1.add("Doch ein Fehler an der Vektorschubsteuerung führt zu einer "
				+ "\nKettenreaktion und zerstört das ganze Raumschiff.");
		dialog1.add("Doch ein Wissenschaftler der Fh Landshut, der gerade die "
				+ "\nRettungskapseln inspizierte konnte entkommen und bewegt sich nun, "
				+ "\nin der kleinen Kapsel, mit hoher Geschwindkigkeit auf den "
				+ "\nunbekannten Planeten zu. ");
		dialog1.add("Die Gravitationskraft auf dem Planeten ist so stark, das der Aufprall"
				+ "\ngroßen Schaden am Raumschiff und der Umgebung verursacht hat.");
		dialog1.add("...");
		dialog1.add("Wie fühlst du dich? Bist du okay? Ich konnte dich gerade"
				+ "\nnoch aus dem Ruamschiff retten, befor die hohe Temperatur"
				+ "\ndie Zellen deines Körpers zerstört hätte.");
		dialog1.add("Wo bin ich? "
				+ "\n... "
				+ "\nDanke für deine Hilfe.");
		dialog1.add("Das hier ist der Planet Alana. Ich bin eine Fee, hab aber"
				+ "\nkeine Angst, ich werde dir nichts tun. Kannst du stehen?");
		dialog1.add("Freut mich das es dir einigermaßen gut geht."
				+ "\nWie heißt du? Mein Name ist übrigens Yui.");
		dialog1.add("\nDas ist ein schöner Name. "
				+ "\n... ");
		dialog1.add("Wir sind hier nicht sicher, auf dem Planeten wimmelt es von "
				+ "\nMonstern, die alles angreifen, was sie für eine lohnenswerte"
				+ "\nBeute halten.");
		dialog1.add("Nimm dieses Schwert und diese Heiltränke um dich zu "
				+ "\nverteidigen, ich begleite dich auf deiner Reise"
				+ "\nund helfe dir, aber wenn die Zeit reif ist brauche"
				+ "\nich deine Hilfe.");
		dialog.add(dialog1);
	}

	public void update(InputHandler input) {
		if (input.isPressed(input.SPACE)&& state == 15){
			//Spiel starten	
			//Music.stopMenuMusic();
			Music.getGameMusic();
			gsm.setState(gsm.LOADGAME);
		}
		else if (input.isPressed(input.SPACE)&& state != 12){
			state++;
			inDialogCounter++;
		}
		else if (state == 12){
		//Namenseingabe
			if (input.pressedKey >= 65 && input.pressedKey <= 90 && name.size() < 15) {
				name.add(input.pressedKey);
				input.pressedKey = 0;
			}
	
			if (input.isPressed(input.BACKSPACE)) {
				if (name.size() > 0) 
					name.remove(name.size()-1);
			}
			
			if (input.isPressed(input.ENTER) ) {
				for (int i=0; i<name.size();i++) {
					char temp = (char)(int)name.get(i);	
					playerName += (i==0 ? temp : Character.toLowerCase(temp));
				}
				Player.name = playerName;
				state++;
			}
		}
		else {
			if (inDialogCounter > dialog.get(dialogCounter).size()-1) {
				inDialogCounter = 0;
				dialogCounter++;
			}
			if (dialogCounter > dialog.size()) 
				dialogCounter--;
		}
	}

	
	public void draw(Graphics2D g) {
		if(state == 13)		g.drawImage(intro9.getImage(0,0), 0, 0, null);
		if(state == 11)		g.drawImage(intro9.getImage(0,0), 0, 0, null);
		if(state == 8)		g.drawImage(intro8.getImage(0,0), 0, 0, null);
		if(state == 7)		g.drawImage(intro7.getImage(0,0), 0, 0, null);
		if(state == 6)		g.drawImage(intro6.getImage(0,0), 0, 0, null);
		if(state == 5)		g.drawImage(intro5.getImage(0,0), 0, 0, null);
		if(state == 4)		g.drawImage(intro4.getImage(0,0), 0, 0, null);
		if(state == 3)		g.drawImage(intro3.getImage(0,0), 0, 0, null);
		if(state == 2)		g.drawImage(intro2.getImage(0,0), 0, 0, null);
		if(state == 1)		g.drawImage(intro1.getImage(0,0), 0, 0, null);
				
		//Namenseingabe
		if(state == 12){
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));
			g.setColor(Color.WHITE);
			drawString(g, "Gib nun deinen Namen ein:", 10, 319);
			char[] output = new char[name.size()];
			for (int i=0; i<name.size();i++)
				output[i] = (char)(int)name.get(i);
			
			g.setColor(Color.WHITE);
			g.drawChars(output, 0, output.length, 200, 333);
		}
		else if (state == 13){
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setColor(Color.PINK);
			g.fillRect(5,309, 70, 70);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));
			g.setColor(Color.WHITE);
			drawString(g, playerName, 80, 319);
			drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 80, 319);
			g.drawImage(yui.getImage(0,0), 5, 309, null);
		}
		else if (state > 7) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setColor(Color.PINK);
			g.fillRect(5,309, 70, 70);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));
			g.setColor(Color.WHITE);
			drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 80, 319);
			if (state == 8 || state == 10 || state == 11|| state == 14|| state == 15){
				g.drawImage(yui.getImage(0,0), 5, 309, null);
			}
			else g.drawImage(hero.getImage(0,0), 5, 309, null);
		}
		else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 304, Game.WIDTH, 80);
			g.setFont(new Font("Courier New", Font.PLAIN, 11));
			g.setColor(Color.WHITE);
			drawString(g, dialog.get(dialogCounter).get(inDialogCounter), 10, 319);
		}
	}

	public void handleInput(InputHandler input) {	
		
	}

	private void drawString(Graphics g, String text, int x, int y) {
             for (String line : text.split("\n"))
                 g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}
