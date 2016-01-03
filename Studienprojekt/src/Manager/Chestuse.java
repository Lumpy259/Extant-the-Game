package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Entity;
import Entity.Item.InventoryItem;
import Entity.Item.Item;
import GameState.ChestState;
import GameState.HouseState;
import GameState.PlayState;

public class Chestuse {

	public InventoryItem[][] chest;
	public InventoryItem[][] inventory;
	private int x;
	private int y;
	private Point selected;
	private boolean divide;
	InventoryItem selectedItem;
	public String name;
	public static boolean leavechest = false;
	
	public Chestuse() {
		chest = new InventoryItem[8][10];
		inventory = new InventoryItem[8][10];
		x = 0;
		y = 0;
	}
	
	public boolean addItem(Item item) {
		ArrayList<Point> freeSpace = new ArrayList<>();
		
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(chest[i][j] == null)
					freeSpace.add(new Point(i,j));
				else if(chest[i][j].getName().equals(item.getName()) && chest[i][j].getCount() < chest[i][j].getStackable()) {
					chest[i][j].setCount(1);
					return true;
				}
			
		if (freeSpace.size() > 0) {
			chest[freeSpace.get(0).x][freeSpace.get(0).y] = new InventoryItem(item, 1);
			return true;
		}	
		return false;				
	}
	
	public void removeItem(Point point) {
		chest[point.y][point.x] = null;
	}
		
	public InventoryItem searchforItem(String name){
		this.name=name;
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(chest[i][j] == null){}
				else if(chest[i][j].getName().equals(name)) return chest[i][j];
		return null;
	}
	
	public Point searchforPoint(String name){
		this.name=name;
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(chest[i][j] == null){}
				else if(chest[i][j].getName().equals(name)) return new Point(j,i);
		return null;
	}
	
	public void changeItem(Point selected, boolean divide) {
		
		if (selectedItem == null) {
			this.selected = selected;
			selectedItem = chest[this.selected.y][this.selected.x];
			
			if (divide) {
					if (selectedItem == null)
						return;
				selectedItem = selectedItem.newInventoryItem((int)Math.ceil(selectedItem.getCount() / 2.0));
				chest[this.selected.y][this.selected.x] = chest[this.selected.y][this.selected.x].newInventoryItem((int)Math.floor(chest[this.selected.y][this.selected.x].getCount() / 2.0));
				if (chest[this.selected.y][this.selected.x].getCount() == 0)
					removeItem(this.selected);
			}
			else 
				removeItem(this.selected);
			
			return;
		}
		
		InventoryItem selectedItem2 = chest[selected.y][selected.x]; 
			
		// Addieren
		if (selectedItem != null && selectedItem2 != null && selectedItem.getName().equals(selectedItem2.getName())) {
			if (divide) {
				if (selectedItem2.getStackable() > selectedItem2.getCount()) {
					selectedItem2.setCount(1);
					selectedItem.setCount(-1);
				}
			}
			else {
				if (selectedItem2.getStackable() - selectedItem2.getCount() > selectedItem.getCount()) {
					selectedItem2.setCount(selectedItem.getCount());
					selectedItem.setCount(-selectedItem.getCount());
				}
				else {
					selectedItem.setCount(-(selectedItem2.getStackable()-selectedItem2.getCount()));
					selectedItem2.setCount(selectedItem2.getStackable() - selectedItem2.getCount());
				}
			}
			if (selectedItem.getCount() == 0) {
				selectedItem = null;
				this.selected = null;
			}
		}
		else {
			// Auf leeres Feld / Austauschen
			if (divide && (selectedItem2 == null || selectedItem.getName().equals(selectedItem2.getName()))) {
				if (chest[selected.y][selected.x] == null)
					chest[selected.y][selected.x] = selectedItem.newInventoryItem(1);
				selectedItem.setCount(-1);
			}
			else {
				InventoryItem temp = chest[selected.y][selected.x];
				chest[selected.y][selected.x] = selectedItem;
				selectedItem = temp; 
				//selectedItem = null;
			}
			
			if (selectedItem != null && selectedItem.getCount() == 0) {
				selectedItem = null;
				this.selected = null;
			}	
		}
		
	}
	
	public void update(InputHandler input) {
		if (GameStateManager.getPrevState() == GameStateManager.PLAY) {
			if (PlayState.chest.containsKey(Entity.chest)){}
				else PlayState.chest.put(Entity.chest, new InventoryItem[8][10]);
		}
	
		if (GameStateManager.getPrevState() == GameStateManager.HOUSE) {
			if (HouseState.chest.containsKey(Entity.chest)){}
				else HouseState.chest.put(Entity.chest, new InventoryItem[8][10]);
		}
		
		if (ChestState.chestentered == true){
			if (GameStateManager.getPrevState() == GameStateManager.PLAY)
				chest = PlayState.chest.get(Entity.chest);
			
			if (GameStateManager.getPrevState() == GameStateManager.HOUSE)
				chest = HouseState.chest.get(Entity.chest);
			
			for(int i=0; i<4; i++){
				for (int j=0; j<10; j++){
					if(PlayState.inventory.inventory[i][j]!=null){
						chest[i][j] = new InventoryItem(PlayState.inventory.inventory[i][j].getItem(), PlayState.inventory.inventory[i][j].getCount());
					}
				}
			}
			
			ChestState.chestentered = false;
		}
		
		if (input.isPressed(input.SPACE))
			changeItem(new Point(x,y), input.keyState[input.STRG]);
		
		if (input.isPressed(input.USE) || input.isPressed(input.INGAMEMENUE)){
			for(int i=0; i<4; i++){
				for (int j=0; j<10; j++){
					if(chest[i][j]==null){
						PlayState.inventory.inventory[i][j] = null;
					}
					else{
						PlayState.inventory.inventory[i][j] = new InventoryItem(chest[i][j].getItem(), chest[i][j].getCount());
						chest[i][j]=null;
					}
				}
			}
			leavechest=true;
		}
		
		if (input.isPressed(input.DOWN))
			y--;
		else if (input.isPressed(input.UP))
			y++;
		else if (input.isPressed(input.LEFT))
			x--;
		else if (input.isPressed(input.RIGHT))
			x++;
		
		if (y < 0) y = 7;
		if (y > 7) y = 0;
		if (x < 0) x = 9;
		if (x > 9) x = 0;
	}
	
	public void draw(Graphics2D g) {
		if (ChestState.chestentered == true){}
		else{
		g.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		//if (selected != null)
		//	g.fillRect(98 + selected.x * 32, 306 - selected.y * 32 - (int)Math.ceil(selected.y*0.01) * 16- (selected.y > 3 ? 16 : 0), 28, 28);
		
		g.setColor(Color.WHITE);
		for (int i=0; i<8; i++)
			for (int j=0; j<10; j++)
				if(chest==null){}
				else if(chest[i][j] != null) {
					g.drawImage(chest[i][j].getImage(), 98 + j * 32, 306 - i * 32 - (int)Math.ceil(i*0.01) * 16 - (i > 3 ? 16 : 0), null);
					if (chest[i][j].getStackable() > 1)
						if (i < 4) g.drawString(""+chest[i][j].getCount(), 114 + j * 32 + (chest[i][j].getCount() < 10 ? 6 : 0), 334 - i * 32 - (int)Math.ceil(i*0.01) * 16);
						else g.drawString(""+chest[i][j].getCount(), 114 + j * 32 + (chest[i][j].getCount() < 10 ? 6 : 0), 318 - i * 32 - (int)Math.ceil(i*0.01) * 16);

				}
		
		//test
		if (selectedItem != null) {
			g.drawImage(selectedItem.getImage(), 95,  173, 20, 20, null);
			g.drawString(selectedItem.getName(), 130, 188);

			if (selectedItem.getStackable() > 1)
				g.drawString(""+selectedItem.getCount(), 115 + (selectedItem.getCount() < 10 ? 6 : 0), 192);
		}
		//ende
		
		g.setColor(Color.RED);
		g.drawRect(97 + x * 32, 305 - y * 32 - (int)Math.ceil(y*0.01) * 16 - (y > 3 ? 16 : 0), 29, 29);

		}
	}
	
	public InventoryItem getInventory(int y, int x) { return this.chest[y][x]; }
	public InventoryItem[] getShortCuts() { InventoryItem[] temp = new InventoryItem[10]; for(int i=0;i<10;i++) temp[i] = this.chest[0][i]; return temp; }
	public InventoryItem[] getArmor() { InventoryItem[] temp = new InventoryItem[4]; for(int i=0;i<4;i++) temp[i] = this.chest[i+4][0]; return temp; }
	public InventoryItem[] getCrafting() { InventoryItem[] temp = new InventoryItem[9]; for (int y=6;y>3;y--) for (int x=7; x>4;x--) temp[(6-y)*3 + x-5] = this.chest[y][x]; return temp; }

}
