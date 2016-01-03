package Manager;

import java.util.ArrayList;

import Entity.Item.Bread;
import Entity.Item.Brick;
import Entity.Item.Bucket_lava;
import Entity.Item.Coal;
import Entity.Item.Dirt_item;
import Entity.Item.Fish;
import Entity.Item.Fish_baked;
import Entity.Item.Glass;
import Entity.Item.Gold;
import Entity.Item.Gold_ore;
import Entity.Item.InventoryItem;
import Entity.Item.Iron;
import Entity.Item.Iron_ore;
import Entity.Item.Meat;
import Entity.Item.Meat_grilled;
import Entity.Item.Potatoe;
import Entity.Item.Potatoe_baked;
import Entity.Item.Sand_item;
import Entity.Item.Wheat;
import Entity.Item.Wood;
import GameState.PlayState;


public class Burning {
	public static ArrayList<Burning> Recipes = new ArrayList<>();
	public static Burning Iron = new Burning(new InventoryItem(new Iron(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Iron_ore(0,0),1));
	public static Burning Iron2 = new Burning(new InventoryItem(new Iron(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Iron_ore(0,0),1));
	public static Burning Iron3 = new Burning(new InventoryItem(new Iron(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Iron_ore(0,0),32));
	public static Burning Gold = new Burning(new InventoryItem(new Gold(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Gold_ore(0,0),1));
	public static Burning Gold2 = new Burning(new InventoryItem(new Gold(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Gold_ore(0,0),1));
	public static Burning Gold3 = new Burning(new InventoryItem(new Gold(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Gold_ore(0,0),32));
	public static Burning Brick = new Burning(new InventoryItem(new Brick(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Dirt_item(0,0),1));
	public static Burning Brick2 = new Burning(new InventoryItem(new Brick(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Dirt_item(0,0),1));
	public static Burning Brick3 = new Burning(new InventoryItem(new Brick(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Dirt_item(0,0),32));
	public static Burning Glass = new Burning(new InventoryItem(new Glass(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Sand_item(0,0),1));
	public static Burning Glass2 = new Burning(new InventoryItem(new Glass(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Sand_item(0,0),1));
	public static Burning Glass3 = new Burning(new InventoryItem(new Glass(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Sand_item(0,0),32));
	public static Burning Fish = new Burning(new InventoryItem(new Fish_baked(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Fish(0,0),1));
	public static Burning Fish1 = new Burning(new InventoryItem(new Fish_baked(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Fish(0,0),1));
	public static Burning Fish2 = new Burning(new InventoryItem(new Fish_baked(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Fish(0,0),32));
	public static Burning Meat = new Burning(new InventoryItem(new Meat_grilled(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Meat(0,0),1));
	public static Burning Meat1 = new Burning(new InventoryItem(new Meat_grilled(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Meat(0,0),1));
	public static Burning Meat2 = new Burning(new InventoryItem(new Meat_grilled(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Meat(0,0),32));
	public static Burning Bread = new Burning(new InventoryItem(new Bread(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Wheat(0,0),1));
	public static Burning Bread1 = new Burning(new InventoryItem(new Bread(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Wheat(0,0),1));
	public static Burning Bread2 = new Burning(new InventoryItem(new Bread(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Wheat(0,0),32));
	public static Burning Potatoe = new Burning(new InventoryItem(new Potatoe_baked(0,0), 2), "1## ### 0##", new InventoryItem(new Coal(0,0),1), new InventoryItem(new Potatoe(0,0),1));
	public static Burning Potatoe1 = new Burning(new InventoryItem(new Potatoe_baked(0,0), 2), "1## ### 0##", new InventoryItem(new Wood(0,0),2), new InventoryItem(new Potatoe(0,0),1));
	public static Burning Potatoe2 = new Burning(new InventoryItem(new Potatoe_baked(0,0), 64), "1## ### 0##", new InventoryItem(new Bucket_lava(0,0),1), new InventoryItem(new Potatoe(0,0),32));
	
	public static int counter = 0;

	public InventoryItem result;
	public String recipe;
	public InventoryItem[] required;
	
	public Burning(InventoryItem result, String recipe, InventoryItem... required) {
		this.result = result;
		this.recipe = recipe;
		this.required = required;
		Burning.Recipes.add(this);
		Burning.counter++;		
	}
		
	public static void update() {
		boolean rightRecipe = false;
		InventoryItem[] craftingSpace = PlayState.ovenuse.getCrafting();
		if (PlayState.ovenuse.OvenandMerchant[5][9] != null)
			return;
		for (int j=0; j<Burning.Recipes.size(); j++) {
			char[] itemPosition = Burning.Recipes.get(j).recipe.replaceAll(" ", "").toCharArray();

			for (int i=0; i<9; i++) {				
				if (!(((int)itemPosition[i] - 48 == -13 && craftingSpace[i] == null) || (craftingSpace[i] != null && (int)itemPosition[i] - 48 != -13 && craftingSpace[i].getName().equals(Burning.Recipes.get(j).required[(int)itemPosition[i] - 48].getName()) && craftingSpace[i].getCount() >= Burning.Recipes.get(j).required[(int)itemPosition[i]-48].getCount())))
				{
					rightRecipe = false;
					break;
				}
				else
					rightRecipe = true;
			}
			if (rightRecipe == true) {		
				if (PlayState.ovenuse.OvenandMerchant[5][9] == null || PlayState.ovenuse.OvenandMerchant[5][9].getName().equals(Burning.Recipes.get(j).result.getName()))
				for (int i=0;i<9;i++)
					if ((int)itemPosition[i] - 48 != -13) {
						PlayState.ovenuse.OvenandMerchant[-((i-i%3)/3-6)][i%3+5].setCount(-Burning.Recipes.get(j).required[(int)itemPosition[i] - 48].getCount());
						
						if (PlayState.ovenuse.OvenandMerchant[-((i-i%3)/3-6)][i%3+5].getCount() == 0)
							PlayState.ovenuse.OvenandMerchant[-((i-i%3)/3-6)][i%3+5] = null;
					}
				if (PlayState.ovenuse.OvenandMerchant[5][9] == null)
					PlayState.ovenuse.OvenandMerchant[5][9] = Burning.Recipes.get(j).result.newInventoryItem(Burning.Recipes.get(j).result.getCount());
				else
					PlayState.ovenuse.OvenandMerchant[5][9].setCount(Burning.Recipes.get(j).result.getCount());
			}
		}
	}
	
	

}
