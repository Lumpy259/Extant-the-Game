package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Item.InventoryItem;

public class Gameover{
	private Gameover gameover;
	private Content hud1, hud2;
	private int state;
	
	public Gameover() {
		hud1 = new Content("GameOverMenue", "Resources/Menu/GameOver1.jpeg", 512, 384, new Point(1,1));
		hud2 = new Content("GameOverMenue", "Resources/Menu/GameOver2.jpeg", 512, 384, new Point(1,1));
		state=2;
	}
	
	
	public void update(InputHandler input) {
		if (input.isPressed(input.LEFT)){
			state--;
			if(state == 0) state = 2;
			
		}
		else if (input.isPressed(input.RIGHT)){
			state++;
			if(state == 3) state = 1;
		}
		if (input.isPressed(input.ENTER) && state == 2){
			System.exit(0);
		}
	}
		
	
	public void draw(Graphics2D g)  {
		if(state == 2)		g.drawImage(hud2.getImage(0,0), 0, 0, null);
		if(state == 1)		g.drawImage(hud1.getImage(0,0), 0, 0, null);
	}
}
