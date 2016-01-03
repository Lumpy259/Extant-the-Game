package Manager;

import java.util.ArrayList;

import Entity.Item.Dirt_item;
import Entity.Item.Emerald;
import Entity.Item.InventoryItem;
import Entity.Item.Sand_item;
import Entity.Item.Stone_item;
import GameState.PlayState;


public class Trading {
	public static ArrayList<Trading> Recipes = new ArrayList<>();
	public static Trading Emerald = new Trading(new InventoryItem(new Emerald(0,0), 2), "1## ### 0##", new InventoryItem(new Sand_item(0,0),32), new InventoryItem(new Stone_item(0,0),32));
	public static Trading Emerald2 = new Trading(new InventoryItem(new Emerald(0,0), 2), "1## ### 0##", new InventoryItem(new Dirt_item(0,0),32), new InventoryItem(new Stone_item(0,0),32));
	
	public static int counter = 0;

	public InventoryItem result;
	public String recipe;
	public InventoryItem[] required;
	
	public Trading(InventoryItem result, String recipe, InventoryItem... required) {
		this.result = result;
		this.recipe = recipe;
		this.required = required;
		Trading.Recipes.add(this);
		Trading.counter++;		
	}
		
	public static void update() {
		boolean rightRecipe = false;
		InventoryItem[] craftingSpace = PlayState.ovenuse.getCrafting();
		if (PlayState.ovenuse.OvenandMerchant[5][9] != null)
			return;
		for (int j=0; j<Trading.Recipes.size(); j++) {
			char[] itemPosition = Trading.Recipes.get(j).recipe.replaceAll(" ", "").toCharArray();

			for (int i=0; i<9; i++) {				
				if (!(((int)itemPosition[i] - 48 == -13 && craftingSpace[i] == null) || (craftingSpace[i] != null && (int)itemPosition[i] - 48 != -13 && craftingSpace[i].getName().equals(Trading.Recipes.get(j).required[(int)itemPosition[i] - 48].getName()) && craftingSpace[i].getCount() >= Trading.Recipes.get(j).required[(int)itemPosition[i]-48].getCount())))
				{
					rightRecipe = false;
					break;
				}
				else
					rightRecipe = true;
			}
			if (rightRecipe == true) {		
				if (PlayState.ovenuse.OvenandMerchant[5][9] == null || PlayState.ovenuse.OvenandMerchant[5][9].getName().equals(Trading.Recipes.get(j).result.getName()))
				for (int i=0;i<9;i++)
					if ((int)itemPosition[i] - 48 != -13) {
						PlayState.ovenuse.OvenandMerchant[-((i-i%3)/3-6)][i%3+5].setCount(-Trading.Recipes.get(j).required[(int)itemPosition[i] - 48].getCount());
						
						if (PlayState.ovenuse.OvenandMerchant[-((i-i%3)/3-6)][i%3+5].getCount() == 0)
							PlayState.ovenuse.OvenandMerchant[-((i-i%3)/3-6)][i%3+5] = null;
					}
				if (PlayState.ovenuse.OvenandMerchant[5][9] == null)
					PlayState.ovenuse.OvenandMerchant[5][9] = Trading.Recipes.get(j).result.newInventoryItem(Trading.Recipes.get(j).result.getCount());
				else
					PlayState.ovenuse.OvenandMerchant[5][9].setCount(Trading.Recipes.get(j).result.getCount());
			}
		}
	}
	
	

}
