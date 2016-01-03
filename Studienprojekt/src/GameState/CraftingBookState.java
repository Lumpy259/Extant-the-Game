package GameState;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;






import Entity.Item.Bucket_milk;
import Entity.Item.Cake;
import Entity.Item.Egg;
import Entity.Item.InventoryItem;
import Entity.Item.Sugar;
import Entity.Item.Wheat;
import Main.Game;
import Manager.Content;
import Manager.Crafting;
import Manager.GameStateManager;
import Manager.InputHandler;

public class CraftingBookState extends GameState {

	private Content hud, questionmark;
	private int page;
	
	public CraftingBookState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {	
		hud = new Content("Craftingbook", "Resources/HUD/Craftingbook.png", 352, 320, new Point(1,1));
		questionmark = new Content("Fragezeichen", "Resources/HUD/questionmark.png", 28, 28, new Point(1,1));
		Game.game.g.setColor(new Color(0,0,0,200));
		Game.game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		page = 0;
	}

	public void update(InputHandler input) {
		handleInput(input);
	}

	public void draw(Graphics2D g) {	
		g.drawImage(hud.getImage(0,0), 80, 32, null);
		
		for (int c=0; c<6; c++) {
			if(6*page+c >= Crafting.Recipes.size())
				break;

			char[] itemPosition = Crafting.Recipes.get(6*page+c).recipe.replaceAll(" ", "").toCharArray();
			
			for (int i=0; i<9; i++) {
				if (Crafting.Recipes.get(6*page+c).craftingBook == false)
					g.drawImage(questionmark.getImage(), c%2*176+90+(i%3)*32, (int)(c/2)*104+42+(int)(i/3)*32, null);					
				else if ((int)itemPosition[i]-48 != -13)
					g.drawImage(Crafting.Recipes.get(6*page+c).required[(int)itemPosition[i]-48].getImage(), c%2*176+90+(i%3)*32, (int)(c/2)*104+42+(int)(i/3)*32, null);
			}
			g.drawImage(Crafting.Recipes.get(6*page+c).result.getImage(), c%2*176+218, (int)(c/2)*104+74, null);
		}
	}

	public void handleInput(InputHandler input) {	
		if (input.isPressed(input.USE))
			page += 1;
		if (input.isPressed(input.BOSS))
			page -= 1;
		if (page > (int)Crafting.Recipes.size()/6) page = 0;
		if (page < 0) page = (int)Crafting.Recipes.size()/6;
		
		
		if ((input.isPressed(input.TUTORIAL) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL ) {
			gsm.setState(gsm.TUTORIAL);
		}
		if ((input.isPressed(input.CRAFTING) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.PLAY) {
			gsm.setState(gsm.PLAY);
		}
		if ((input.isPressed(input.CRAFTING) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			gsm.setState(gsm.HOUSE);
		}
		if ((input.isPressed(input.CRAFTING) || input.isPressed(input.INGAMEMENUE)) && GameStateManager.getPrevState() == GameStateManager.TUTORIAL2) {
			gsm.setState(gsm.TUTORIAL2);
		}
	}

}
