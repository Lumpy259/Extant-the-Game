package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Player;
import Entity.Item.InventoryItem;

public class Status {
	private int x;
	private int y;
	private int[] stats = new int[7];

	
	public static int LEVEL = 0;
	public static int POINTS = 1;
	public static int STRENGTH = 2;
	public static int VITALITAET = 3;
	public static int DEFENSE = 4;
	public static int MOVESPEED = 5;
	public static int LUCK = 6;
	
	public Status() {
		x = 0;
		y = 0;
	}
	
	
	public void update(InputHandler input) {
		if (input.isPressed(input.DOWN))
			y++;
		else if (input.isPressed(input.UP))
			y--;
		else if (input.isPressed(input.LEFT))
			x--;
		else if (input.isPressed(input.RIGHT))
			x++;	
		else if (input.isPressed(input.SPACE))
		{
			if (y == 0) {
				Player.strength += stats[y+2];
				Player.damage = 5 * Player.strength; 
				
			}
			if (y == 1) {
				//Player.getVitalitaet() += stats[y+2];
				Player.maxlife = 10 * Player.getVitalitaet();
				Player.currentlife += stats[y+2] * 10;
				
			}
			if (y == 2) {
				Player.defense += stats[y+2];
				//Player.armor += 10 * Player.defense;
				
			}
			if (y == 3) {
				Player.moveSpeed += 0.1 * stats[y+2];
				
				
			}
			if (y == 4) Player.luck += stats[y+2];
			
			
			
			Player.points -= stats[y+2];
			stats[POINTS] += stats[y+2];
			stats[y+2] = 0;
			
		}
		
		if (y < 0) y = 4;
		if (y > 4) y = 0;
		
		if (Player.points + stats[POINTS] > 0 && x > 0) {
			stats[y+2]++;
			stats[POINTS]--;
		}
		if (stats[y+2] > 0 && Player.points > stats[POINTS] && x < 0) {
			stats[y+2]--;
			stats[POINTS]++;
		}
		
		x = 0;
	}
	
	public void draw(Graphics2D g)  {
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 22));
		g.drawString(""+Player.level, 190, 88);	
		g.drawString(""+Player.points, 350, 120); 
		g.drawString(""+Player.strength, 275, 152); 
		g.drawString(""+Player.getVitalitaet(), 275, 184);
		g.drawString(""+Player.defense, 275, 216);
		g.drawString(""+Player.moveSpeed, 275, 248);
		g.drawString(""+Player.luck, 275, 280);
		g.setColor(Color.RED);
		g.drawString(""+stats[POINTS], 385, 120);
		g.drawString(""+stats[STRENGTH], 350, 152);
		g.drawString(""+stats[VITALITAET], 350, 184);
		g.drawString(""+stats[DEFENSE], 350, 216);
		g.drawString(""+stats[MOVESPEED], 350, 248);
		g.drawString(""+stats[LUCK], 350, 280);
		g.drawRect(255, 128+y*32, 75, 32);
		g.setColor(Color.RED);
		g.fillRect(117,107, ((int)(122.0 / (Player.level*70)*Player.xp) <= 122 ? (int)(122.0 / (Player.level*70)*Player.xp) : 122), 10);
				
	}
}
