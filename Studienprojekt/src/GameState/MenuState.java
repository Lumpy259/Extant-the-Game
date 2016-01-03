package GameState;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.IOException;






import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.MainMenue;

public class MenuState extends GameState {
	
	private MainMenue mainMenue;
	private GameStateManager gsm;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;/*
		for ( String fonts : GraphicsEnvironment.getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames() )
System.out.println( fonts );*/
	}

	public void init() {		
		mainMenue = new MainMenue(gsm);
		
		
	}
	public void update(InputHandler input) {
		mainMenue.update(input);
		
	}

	public void draw(Graphics2D g) {
		
		mainMenue.draw(g);
	}

	public void handleInput(InputHandler input) {	
		
	}

}