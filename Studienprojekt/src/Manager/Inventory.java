package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Item.InventoryItem;
import Entity.Item.Item;
import Main.Game;

public class Inventory {

	public InventoryItem[][] inventory;
	private int x;
	private int y;
	private Point selected;
	private boolean divide;
	InventoryItem selectedItem;
	public String name;
	
	public Inventory() {
		inventory = new InventoryItem[8][10];
		x = 0;
		y = 0;
	}
	
	public boolean addItem(Item item) {
		ArrayList<Point> freeSpace = new ArrayList<>();
		
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(inventory[i][j] == null)
					freeSpace.add(new Point(i,j));
				else if(inventory[i][j].getName().equals(item.getName()) && inventory[i][j].getCount() < inventory[i][j].getStackable()) {
					inventory[i][j].setCount(1);
					return true;
				}
			
		if (freeSpace.size() > 0) {
			inventory[freeSpace.get(0).x][freeSpace.get(0).y] = new InventoryItem(item, 1);
			return true;
		}	
		return false;				
	}
	
	public void removeItem(Point point) {
		inventory[point.y][point.x] = null;
	}
		
	public InventoryItem searchforItem(String name){
		this.name=name;
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(inventory[i][j] == null){}
				else if(inventory[i][j].getName().equals(name)) return inventory[i][j];
		return null;
	}
	
	public Point searchforPoint(String name){
		this.name=name;
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(inventory[i][j] == null){}
				else if(inventory[i][j].getName().equals(name)) return new Point(j,i);
		return null;
	}
	
	public void changeItem(Point selected, boolean divide) {
		
		if (selectedItem == null) {
			this.selected = selected;
			selectedItem = inventory[this.selected.y][this.selected.x];
			
			if (divide) {
					if (selectedItem == null)
						return;
				selectedItem = selectedItem.newInventoryItem((int)Math.ceil(selectedItem.getCount() / 2.0));
				inventory[this.selected.y][this.selected.x] = inventory[this.selected.y][this.selected.x].newInventoryItem((int)Math.floor(inventory[this.selected.y][this.selected.x].getCount() / 2.0));
				if (inventory[this.selected.y][this.selected.x].getCount() == 0)
					removeItem(this.selected);
			}
			else 
				removeItem(this.selected);
			
			return;
		}
		
		InventoryItem selectedItem2 = inventory[selected.y][selected.x]; 
		
		
		if (this.selected.y == 7 && this.selected.x == 0 || selected.y == 7 && selected.x == 0) 
			if (!((selectedItem == null || selectedItem.getType() == Item.HELMET) && (selectedItem2 == null || selectedItem2.getType() == Item.HELMET))) 
				return;			
		
		if (this.selected.y == 6 && this.selected.x == 0 || selected.y == 6 && selected.x == 0) 
			if (!((selectedItem == null || selectedItem.getType() == Item.CHEST) && (selectedItem2 == null || selectedItem2.getType() == Item.CHEST))) 
				return;			
		
		if (this.selected.y == 5 && this.selected.x == 0 || selected.y == 5 && selected.x == 0) 
			if (!((selectedItem == null || selectedItem.getType() == Item.TROUSERS) && (selectedItem2 == null || selectedItem2.getType() == Item.TROUSERS))) 
				return;			
		
		if (this.selected.y == 4 && this.selected.x == 0 || selected.y == 4 && selected.x == 0) 
			if (!((selectedItem == null || selectedItem.getType() == Item.BOOTS) && (selectedItem2 == null || selectedItem2.getType() == Item.BOOTS))) 
				return;			
			
		
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
				if (inventory[selected.y][selected.x] == null)
					inventory[selected.y][selected.x] = selectedItem.newInventoryItem(1);
				selectedItem.setCount(-1);
			}
			else {
				InventoryItem temp = inventory[selected.y][selected.x];
				inventory[selected.y][selected.x] = selectedItem;
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
		if (input.isPressed(input.SPACE))
			changeItem(new Point(x,y), input.keyState[input.STRG]);

		if (input.isPressed(input.DOWN))
			y--;
		else if (input.isPressed(input.UP))
			y++;
		else if (input.isPressed(input.LEFT))
			x--;
		else if (input.isPressed(input.RIGHT))
			x++;
		
		if (y < 0) 
			if (x < 4) y = 7;
			else y = 6;
		
		if (y > 7) y = 0;
		if (x < 0) x = 9;
		if (x > 9) x = 0;
		
		if (y > 3 && x < 4) x = 0;
		if (y > 3 && x == 4) x = 5;
		if (y > 6 && x > 4) y = 0;
		if (y > 3 && x > 7)
			if (input.isPressed(input.RIGHT)) x = 9;
			else if (input.isPressed(input.LEFT)) x = 7;
			else if (input.isPressed(input.UP) || input.isPressed(input.DOWN)) y = 0;
		if (y > 3 && x==9) y = 5;
		
		if (input.isPressed(input.SPACE) && x == 9 && y == 5)
			Crafting.update();
		else
			Crafting.shadowUpdate();
	}
	
	public void draw(Graphics2D g) {
		g.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		for (int i=4; i<8; i++){
			if(inventory[i][0] != null) {
				g.setColor(Color.BLACK);
				g.fillRect(98, 306 - i * 32 - (int)Math.ceil(i*0.01) * 16 - (i > 3 ? 16 : 0), 28, 28);
			}
		}
		//if (selected != null)
		//	g.fillRect(98 + selected.x * 32, 306 - selected.y * 32 - (int)Math.ceil(selected.y*0.01) * 16- (selected.y > 3 ? 16 : 0), 28, 28);
		
		g.setColor(Color.WHITE);
		for (int i=0; i<8; i++)
			for (int j=0; j<10; j++)
				if(inventory[i][j] != null) {
					g.drawImage(inventory[i][j].getImage(), 98 + j * 32, 306 - i * 32 - (int)Math.ceil(i*0.01) * 16 - (i > 3 ? 16 : 0), null);
					if (inventory[i][j].getStackable() > 1)
						if (i < 4) g.drawString(""+inventory[i][j].getCount(), 114 + j * 32 + (inventory[i][j].getCount() < 10 ? 6 : 0), 334 - i * 32 - (int)Math.ceil(i*0.01) * 16);
						else g.drawString(""+inventory[i][j].getCount(), 114 + j * 32 + (inventory[i][j].getCount() < 10 ? 6 : 0), 318 - i * 32 - (int)Math.ceil(i*0.01) * 16);
				}
		
		//test
		if (selectedItem != null) {
			g.drawImage(selectedItem.getImage(), 95,  173, 20, 20, null);
			g.drawString(selectedItem.getName(), 130, 188);
			if (selectedItem.getStackable() > 1)
				g.drawString(""+selectedItem.getCount(), 115 + (selectedItem.getCount() < 10 ? 6 : 0), 192);
		}
		//ende
		if(inventory[y][x] != null) {
			g.drawString(inventory[y][x].getName(), 265, 68);
		}
		
		for (int i=4; i<8; i++){
			if(inventory[i][0] != null) {
				g.setColor(Color.WHITE);
				g.drawString(""+inventory[i][0].getArmor(), 114 + 0 * 32 + (inventory[i][0].getArmor() < 10 ? 6 : 0), 318 - i * 32 - (int)Math.ceil(i*0.01) * 16);
			}
		}
		
		g.setColor(Color.RED);
		g.drawRect(97 + x * 32, 305 - y * 32 - (int)Math.ceil(y*0.01) * 16 - (y > 3 ? 16 : 0), 29, 29);
		
		g.setColor(Color.WHITE);
		g.drawString("Tag: " + (int)Math.ceil(Statistics.playTime / Game.DAY), 130, 58);
		g.drawString("Spielzeit: " + (int)(Statistics.playTime/1000), 130, 78);
		g.drawString("Tode: " + Statistics.deaths, 130, 98);
		g.drawString("Getötete Monster: " + Statistics.killedMonsters, 130, 118);
		g.drawString("Laufdistanz: " + (int)(Statistics.playerDistance/32) + "m", 130, 138);
	}
	
	public InventoryItem getInventory(int y, int x) { return this.inventory[y][x]; }
	public InventoryItem[] getShortCuts() { InventoryItem[] temp = new InventoryItem[10]; for(int i=0;i<10;i++) temp[i] = this.inventory[0][i]; return temp; }
	public InventoryItem[] getArmor() { InventoryItem[] temp = new InventoryItem[4]; for(int i=0;i<4;i++) temp[i] = this.inventory[i+4][0]; return temp; }
	public InventoryItem[] getCrafting() { InventoryItem[] temp = new InventoryItem[9]; for (int y=6;y>3;y--) for (int x=7; x>4;x--) temp[(6-y)*3 + x-5] = this.inventory[y][x]; return temp; }

}
