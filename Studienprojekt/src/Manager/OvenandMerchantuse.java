package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Animation;
import Entity.Entity;
import Entity.Player;
import Entity.Item.InventoryItem;
import Entity.Item.Item;
import GameState.ChestState;
import GameState.OvenState;
import GameState.PlayState;
import Main.Game;

public class OvenandMerchantuse {

	public InventoryItem[][] OvenandMerchant;
	public InventoryItem[][] inventory;
	public static Content burningoven = new Content ("OVEN", "Resources/Animations/oven_animation.png", 32, 32, new Point(2,1));
	public static Content burningfutureoven = new Content ("FUTUREOVEN", "Resources/Animations/futuristic_oven_animation.png", 32, 32, new Point(2,1));
	private Animation OvenAnimation = new Animation(10, 2);
	public static int ovenanimation;
	public static int futureovenanimation;
	private int x;
	private int y;
	private Point selected;
	private boolean divide;
	InventoryItem selectedItem;
	public String name;
	public static boolean leaveoven = false;
	public static boolean futureoven = false;
	
	public OvenandMerchantuse() {
		OvenandMerchant = new InventoryItem[8][10];
		inventory = new InventoryItem[8][10];
		x = 0;
		y = 0;
	}
	
	public boolean addItem(Item item) {
		ArrayList<Point> freeSpace = new ArrayList<>();
		
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(OvenandMerchant[i][j] == null)
					freeSpace.add(new Point(i,j));
				else if(OvenandMerchant[i][j].getName().equals(item.getName()) && OvenandMerchant[i][j].getCount() < OvenandMerchant[i][j].getStackable()) {
					OvenandMerchant[i][j].setCount(1);
					return true;
				}
			
		if (freeSpace.size() > 0) {
			OvenandMerchant[freeSpace.get(0).x][freeSpace.get(0).y] = new InventoryItem(item, 1);
			return true;
		}	
		return false;				
	}
	
	public void removeItem(Point point) {
		OvenandMerchant[point.y][point.x] = null;
	}
		
	public InventoryItem searchforItem(String name){
		this.name=name;
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(OvenandMerchant[i][j] == null){}
				else if(OvenandMerchant[i][j].getName().equals(name)) return OvenandMerchant[i][j];
		return null;
	}
	
	public Point searchforPoint(String name){
		this.name=name;
		for(int i=0; i<4; i++)
			for (int j=0; j<10; j++)
				if(OvenandMerchant[i][j] == null){}
				else if(OvenandMerchant[i][j].getName().equals(name)) return new Point(j,i);
		return null;
	}
	
	public void changeItem(Point selected, boolean divide) {
		
		if (selectedItem == null) {
			this.selected = selected;
			selectedItem = OvenandMerchant[this.selected.y][this.selected.x];
			
			if (divide) {
					if (selectedItem == null)
						return;
				selectedItem = selectedItem.newInventoryItem((int)Math.ceil(selectedItem.getCount() / 2.0));
				OvenandMerchant[this.selected.y][this.selected.x] = OvenandMerchant[this.selected.y][this.selected.x].newInventoryItem((int)Math.floor(OvenandMerchant[this.selected.y][this.selected.x].getCount() / 2.0));
				if (OvenandMerchant[this.selected.y][this.selected.x].getCount() == 0)
					removeItem(this.selected);
			}
			else 
				removeItem(this.selected);
			
			return;
		}
		
		InventoryItem selectedItem2 = OvenandMerchant[selected.y][selected.x]; 
			
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
				if (OvenandMerchant[selected.y][selected.x] == null)
					OvenandMerchant[selected.y][selected.x] = selectedItem.newInventoryItem(1);
				selectedItem.setCount(-1);
			}
			else {
				InventoryItem temp = OvenandMerchant[selected.y][selected.x];
				OvenandMerchant[selected.y][selected.x] = selectedItem;
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
		if (OvenState.ovenentered == true){
			if (PlayState.isthisaoven == true) OvenandMerchant = PlayState.oven.get(Entity.oven);
			else OvenandMerchant = PlayState.oven.get(Entity.merchant);
			for(int i=0; i<4; i++){
				for (int j=0; j<10; j++){
					if(PlayState.inventory.inventory[i][j]!=null){
						OvenandMerchant[i][j] = new InventoryItem(PlayState.inventory.inventory[i][j].getItem(), PlayState.inventory.inventory[i][j].getCount());
					}
				}
			}
			OvenState.ovenentered = false;
		}
		
		if (input.isPressed(input.SPACE))
			changeItem(new Point(x,y), input.keyState[input.STRG]);
		if (input.isPressed(input.USE) || input.isPressed(input.INGAMEMENUE)){
			for(int i=0; i<4; i++){
				for (int j=0; j<10; j++){
					if(OvenandMerchant[i][j]==null){
						PlayState.inventory.inventory[i][j] = null;
					}
					else{
						PlayState.inventory.inventory[i][j] = new InventoryItem(OvenandMerchant[i][j].getItem(), OvenandMerchant[i][j].getCount());
						OvenandMerchant[i][j]=null;
					}
				}
			}
			leaveoven=true;
		}
		
		if (input.isPressed(input.DOWN)){
			y--;
			if (y==5 && x == 5) y=4;
			if (y==4 && x == 9) y=3;
		}
		else if (input.isPressed(input.UP)){
			y++;
			if (y==5 && x == 5) y=6;
			if (y==4 && x == 9) y=5;
		}
		else if (input.isPressed(input.LEFT)){
			x--;
			if (x<5 && (y==4 || y==6)){
				x=9; 
				y=5;
			}
			if (x<9 && y==5){
				x=5; 
				y=6;
			}
		}
		else if (input.isPressed(input.RIGHT)){
			x++;
			if (x>5 && (y==4 || y==6)){
				x=9; 
				y=5;
			}
			if (x>9 && y==5){
				x=5; 
				y=6;
			}
		}
		
		if (y<0 && x==9) y = 5;
		if (y<0 && x==5) y = 6;
		if (y == 5 && x == 5) y= 6;
		if (y > 6 && x == 5) y = 0;
		if (y > 5 && x == 9) y = 0;
		if (y > 3 && !(x==5 || x==9)) y = 0;
		if (y < 0) y = 3;
		if (x < 0) x = 9;
		if (x > 9) x = 0;
		
		ovenanimation -= (ovenanimation > 0 ? 1 : 0);
		futureovenanimation -= (futureovenanimation > 0 ? 1 : 0);
		
		if (PlayState.isthisaoven == true){
			if (OvenandMerchant[4][5] != null){
				if(futureoven==true){
					if (OvenandMerchant[4][5].getName().equals("Kohle") || OvenandMerchant[4][5].getName().equals("Holz") || OvenandMerchant[4][5].getName().equals("Eimer Lava")){
						if (futureovenanimation == 0)
							futureovenanimation = 20;
					}
				}
				else if (OvenandMerchant[4][5].getName().equals("Kohle") || OvenandMerchant[4][5].getName().equals("Holz") || OvenandMerchant[4][5].getName().equals("Eimer Lava")){
					if (ovenanimation == 0)
						ovenanimation = 20;
				}
				Burning.update();
			}
		}
		else Trading.update();
	}
	
	public void draw(Graphics2D g) {
		if (OvenState.ovenentered == true){}
		else{
			g.setFont(new Font("SansSerif", Font.PLAIN, 11));
			
			//if (selected != null)
			//	g.fillRect(98 + selected.x * 32, 306 - selected.y * 32 - (int)Math.ceil(selected.y*0.01) * 16- (selected.y > 3 ? 16 : 0), 28, 28);
			
			g.setColor(Color.WHITE);
			for (int i=0; i<8; i++)
				for (int j=0; j<10; j++)
					if(OvenandMerchant==null){}
					else if(OvenandMerchant[i][j] != null) {
						g.drawImage(OvenandMerchant[i][j].getImage(), 98 + j * 32, 306 - i * 32 - (int)Math.ceil(i*0.01) * 16 - (i > 3 ? 16 : 0), null);
						if (OvenandMerchant[i][j].getStackable() > 1)
							if (i < 4) g.drawString(""+OvenandMerchant[i][j].getCount(), 114 + j * 32 + (OvenandMerchant[i][j].getCount() < 10 ? 6 : 0), 334 - i * 32 - (int)Math.ceil(i*0.01) * 16);
							else g.drawString(""+OvenandMerchant[i][j].getCount(), 114 + j * 32 + (OvenandMerchant[i][j].getCount() < 10 ? 6 : 0), 318 - i * 32 - (int)Math.ceil(i*0.01) * 16);
					}
			
			//test
			if (selectedItem != null) {
				g.drawImage(selectedItem.getImage(), 95,  173, 20, 20, null);
				if (selectedItem.getStackable() > 1)
					g.drawString(""+selectedItem.getCount(), 115 + (selectedItem.getCount() < 10 ? 6 : 0), 192);
			}
			//ende
			if(OvenandMerchant[y][x] != null) {
				g.drawString(OvenandMerchant[y][x].getName(), 265, 68);
			}
			
			g.setColor(Color.RED);
			g.drawRect(97 + x * 32, 305 - y * 32 - (int)Math.ceil(y*0.01) * 16 - (y > 3 ? 16 : 0), 29, 29);
			if (ovenanimation > 0){
				OvenAnimation.update();
				g.drawImage(burningoven.getImage(0, OvenAnimation.getFrame()), Game.WIDTH/2, Game.HEIGHT/2-80, null);
			}
			else if (futureovenanimation > 0){
				OvenAnimation.update();
				g.drawImage(burningfutureoven.getImage(0, OvenAnimation.getFrame()), Game.WIDTH/2, Game.HEIGHT/2-80, null);
			}
		}
	}
	
	public InventoryItem getInventory(int y, int x) { return this.OvenandMerchant[y][x]; }
	public InventoryItem[] getShortCuts() { InventoryItem[] temp = new InventoryItem[10]; for(int i=0;i<10;i++) temp[i] = this.OvenandMerchant[0][i]; return temp; }
	public InventoryItem[] getArmor() { InventoryItem[] temp = new InventoryItem[4]; for(int i=0;i<4;i++) temp[i] = this.OvenandMerchant[i+4][0]; return temp; }
	public InventoryItem[] getCrafting() { InventoryItem[] temp = new InventoryItem[9]; for (int y=6;y>3;y--) for (int x=7; x>4;x--) temp[(6-y)*3 + x-5] = this.OvenandMerchant[y][x]; return temp; }

}
