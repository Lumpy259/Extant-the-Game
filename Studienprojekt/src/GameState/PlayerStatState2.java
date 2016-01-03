package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import Entity.Player;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Status;

public class PlayerStatState2 extends GameState {

	private Content hud, numbers10, numbers20, skillbar;
	private Content[] skills;
	private int[] points;
	private int[] pointsPlayer;
	private int y;
	
	public PlayerStatState2(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("PlayerStats", "Resources/Inventory/Playerstat.png", 352, 320, new Point(1,1));
		numbers10 = new Content("Numbers10", "Resources/Inventory/numbers10.png", 10, 10, new Point(11,1));
		numbers20 = new Content("Numbers20", "Resources/Inventory/numbers20.png", 20, 26, new Point(11,1));
		skillbar = new Content("Skillbar", "Resources/Inventory/skillbar.png", 130, 20, new Point(1,3));
		skills = new Content[] {
				new Content("Stärke", "Resources/Inventory/strength.png", 28, 28, new Point(1,1)),
				new Content("Vitalität", "Resources/Inventory/vitality.png", 28, 28, new Point(1,1)),
				new Content("Verteidigung", "Resources/Inventory/defense.png", 28, 28, new Point(1,1)),
				new Content("Geschwindigkeit", "Resources/Inventory/speed.png", 28, 28, new Point(1,1)),
				new Content("Glück", "Resources/Inventory/luck.png", 28, 28, new Point(1,1))
		};
		points = new int[] {0, 0, 0, 0, 0};
		pointsPlayer = new int[] {Player.strength, Player.getVitalitaet(), Player.defense, (int)Player.moveSpeed, Player.luck};
		y = 0;
		
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void update(InputHandler input) {
		handleInput(input);
		pointsPlayer = new int[] {Player.strength, Player.getVitalitaet(), Player.defense, (int)Player.moveSpeed, Player.luck};
	}

	public void draw(Graphics2D g) {		
		char[] charsLevel = String.valueOf(Player.level).toCharArray();
		char[] charsPoints = String.valueOf(Player.points).toCharArray();
		char[] charsCurrentLife = String.valueOf(Player.currentlife).toCharArray();
		char[] charsMaxLife = String.valueOf(Player.maxlife).toCharArray();
		char[] charsCurrentXP = String.valueOf(Player.xp-Player.calculateMinXP()).toCharArray();
		char[] charsMaxXP = String.valueOf(Player.calculateXP()).toCharArray();
		
		g.drawImage(hud.getImage(0,0), 80, 32, null);
		
		g.setColor(Color.WHITE); // Playername
		g.setFont(new Font("SansSerif", Font.PLAIN, 25));
		g.drawString(""+Player.name, 180, 60);	

		
		for (int i=0; i<charsLevel.length; i++)	// Player Level
			g.drawImage(numbers20.getImage(0, Character.getNumericValue(charsLevel[i])), 198+i*20, 64, null);
		
		
		for (int i=0; i<charsPoints.length; i++) // Player Skillpoints
			g.drawImage(numbers20.getImage(0, Character.getNumericValue(charsPoints[i])), 188+i*20, 134, null);

		
		g.setColor(Color.RED);	// Lebenspunkte
		g.fillRect(289,	76, (int)(1.0 * Player.currentlife / Player.maxlife * 126), 8);
		
		for (int i=0; i<charsCurrentLife.length; i++)  // current Lebenspunkte
			g.drawImage(numbers10.getImage(0, Character.getNumericValue(charsCurrentLife[i])), 289+(int)((126 -(charsCurrentLife.length + charsMaxLife.length + 0.4) * 10) / 2)+i*10, 75, null);
		
		g.drawImage(numbers10.getImage(0, 10), 289 + (int)((126 - (charsCurrentLife.length + charsMaxLife.length + 0.4) * 10) / 2) + charsCurrentLife.length * 10, 75, null);
		
		for (int i=0; i<charsMaxLife.length; i++)	// max Lebenspunkte
			g.drawImage(numbers10.getImage(0, Character.getNumericValue(charsMaxLife[i])), 289 + (int)((126 - (charsCurrentLife.length + charsMaxLife.length + 0.4) * 10) / 2) + charsCurrentLife.length * 10 + i * 10 + 7, 75, null);
		
		
		
		g.setColor(Color.ORANGE);	// Erfahrungspunkte
		g.fillRect(289,	97, (int)(1.0 * (Player.xp-Player.calculateMinXP()) / Player.calculateXP() * 126), 8);
		
		for (int i=0; i<charsCurrentXP.length; i++)  // current Erfahrungspunkte
			g.drawImage(numbers10.getImage(0, Character.getNumericValue(charsCurrentXP[i])), 289+(int)((126 -(charsCurrentXP.length + charsMaxXP.length + 0.4) * 10) / 2)+i*10, 96, null);
		
		g.drawImage(numbers10.getImage(0, 10), 289 + (int)((126 - (charsCurrentXP.length + charsMaxXP.length + 0.4) * 10) / 2) + charsCurrentXP.length * 10, 96, null);
		
		for (int i=0; i<charsMaxXP.length; i++)	// max Erfahrungspunkte
			g.drawImage(numbers10.getImage(0, Character.getNumericValue(charsMaxXP[i])), 289 + (int)((126 - (charsCurrentXP.length + charsMaxXP.length + 0.4) * 10) / 2) + charsCurrentXP.length * 10 + i * 10 + 7, 96, null);
		

		
		g.drawImage(skills[y].getImage(), 82, 162 + y*32, null); // Cursor
		
		
		for (int i=0; i<points.length; i++) {	//skillbar
			if (pointsPlayer[i] > 0 && (pointsPlayer[i]-1)%10 + points[i] < 10)	//blau
				g.drawImage(skillbar.getImage(0, 0).getSubimage(0, 0, 30 + 11 * ((pointsPlayer[i]-1)%10), 20), 112, 166 + i*32, null);
			
			if ((pointsPlayer[i]-1)%10 + points[i] >= 10 || (pointsPlayer[i] == 0 && points[i] > 0))	//grün
				g.drawImage(skillbar.getImage(1, 0).getSubimage(0, 0, 30 + 11 * ((pointsPlayer[i] + points[i]-1)%10), 20), 112, 166 + i*32, null);
			else if(points[i] % 10 != 0)
				g.drawImage(skillbar.getImage(1, 0).getSubimage(30 + 11 * (pointsPlayer[i]%10-1), 0, 11 * (points[i]%10), 20), 112 + 30 + 11 * (pointsPlayer[i]%10-1), 166 + i*32, null);
			
			g.drawImage(numbers10.getImage(0, (int)((pointsPlayer[i]+points[i]-1)/10.0)), 118, 171 + i*32, null); // Stufe
		}
		
	}

	public void handleInput(InputHandler input) {	
		if (input.isPressed(input.STATUS) || input.isPressed(input.INGAMEMENUE)) {
			resetPoints();
			switch (GameStateManager.getPrevState()) {
				case GameStateManager.PLAY: gsm.setState(gsm.PLAY); break;
				case GameStateManager.HOUSE: gsm.setState(gsm.HOUSE); break;
				case GameStateManager.TUTORIAL2: TutorialState2.wasInStatus=true; gsm.setState(gsm.TUTORIAL2); break;
			}
		}
		
		if (input.isPressed(input.UP)) y = (y-1 < 0 ? 4 : --y);
		else if (input.isPressed(input.DOWN)) y = (y+1 > 4 ? 0 : ++y);
		else if (input.isPressed(input.RIGHT) && Player.points > 0) { points[y]++; Player.points--; }
		else if (input.isPressed(input.LEFT) && points[y] > 0) { points[y]--; Player.points++; }
		else if (input.isPressed(input.SPACE)) savePoints();
		
	}
	
	public void resetPoints() {
		for (int i=0; i<points.length; i++)
			Player.points += points[i];
	}

	public void savePoints() {
		Player.strength += points[0];
		Player.setVitalitaet(points[1]);
		Player.defense += points[2];
		Player.moveSpeed += points[3];
		Player.luck += points[4];
		points = new int[] {0, 0, 0, 0, 0};
	}
}
