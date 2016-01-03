package Entity.Item;


import java.awt.image.BufferedImage;


public class InventoryItem {

	private Item item;
	public int count;
	
	public InventoryItem(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	

	public String getName() { return this.item.getName(); }
	public int getStackable() { return this.item.getStackable(); }
	public int getCount() { return this.count; }
	public void setCount(int count) { this.count += count; }
	public BufferedImage getImage() { return this.item.getImage().getImage(0, 0); }
	public int getType() { return this.item.getType(); }
	public Item getItem() { return this.item; } 
	public InventoryItem newInventoryItem(int count) { return new InventoryItem(this.item, count); }
	public int getArmor() { return this.item.getArmor(); }
	//public void draw(Graphics2D g) { this.item.draw(g); };
}
