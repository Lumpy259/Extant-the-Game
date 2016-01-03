package Entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import Entity.Boss.Boss;
import Entity.Environment.Bed_blue;
import Entity.Environment.Bed_gold;
import Entity.Environment.Bed_green;
import Entity.Environment.Bed_purple;
import Entity.Environment.Bed_red;
import Entity.Environment.Bossdoor_inside;
import Entity.Environment.Bossdoor_outside;
import Entity.Environment.Carrot_grown;
import Entity.Environment.Carrot_growstage2;
import Entity.Environment.Carrot_growstage3;
import Entity.Environment.Carrot_smal;
import Entity.Environment.Chest;
import Entity.Environment.Crash;
import Entity.Environment.Door_inside;
import Entity.Environment.Door_outside;
import Entity.Environment.Environment;
import Entity.Environment.Fence_horizontal;
import Entity.Environment.Fence_vertical;
import Entity.Environment.Flower;
import Entity.Environment.Futuristic_oven;
import Entity.Environment.Gate;
import Entity.Environment.Gate_open;
import Entity.Environment.Ladder;
import Entity.Environment.Ladder_inside;
import Entity.Environment.Melon_grown;
import Entity.Environment.Melon_growstage2;
import Entity.Environment.Melon_growstage3;
import Entity.Environment.Melon_smal;
import Entity.Environment.Mountaincave;
import Entity.Environment.Oven;
import Entity.Environment.Plant;
import Entity.Environment.Potatoe_grown;
import Entity.Environment.Potatoe_growstage2;
import Entity.Environment.Potatoe_growstage3;
import Entity.Environment.Potatoe_smal;
import Entity.Environment.Pumpkin_grown;
import Entity.Environment.Pumpkin_growstage2;
import Entity.Environment.Pumpkin_growstage3;
import Entity.Environment.Pumpkin_lantern;
import Entity.Environment.Pumpkin_smal;
import Entity.Environment.Riddlebox;
import Entity.Environment.Rock;
import Entity.Environment.Rock_yellow;
import Entity.Environment.Sapling;
import Entity.Environment.Shamrock;
import Entity.Environment.Shroom;
import Entity.Environment.Tent_entrance;
import Entity.Environment.Tent_exit;
import Entity.Environment.Tree_blue;
import Entity.Environment.Tree_green;
import Entity.Environment.Tree_purple;
import Entity.Environment.Tree_red;
import Entity.Environment.Wheat_grown;
import Entity.Environment.Wheat_growstage2;
import Entity.Environment.Wheat_growstage3;
import Entity.Environment.Wheat_smal;
import Entity.Item.Boat;
import Entity.Item.Item;
import Entity.Monster.Monster;
import Entity.NPC.Cow;
import Entity.NPC.Merchant;
import Entity.NPC.NPC;
import Entity.NPC.Sheep;
import Entity.Projectile.Projectile;
import GameState.BossState;
import GameState.HouseState;
import GameState.PlayState;
import GameState.PuzzleState;
import Main.Game;
import Manager.Content;
import Manager.GameStateManager;
import Manager.InputHandler;
import Manager.Music;
import Manager.Rand;
import World.Tile.Tile;

public abstract class Entity implements Comparable<Entity>{

	public Animation animation;
	public Content image;	
	public double x;
	public double y;
	protected double moveSpeed;
	public int height;
	public int width;
	protected HitBox hitBox;
	private int attackrange;
	private double actualdamage;
	public static Point ladder;
	public static Point bed;
	public static Point chest;
	public static Point oven;
	public static Point animal;
	public static Point merchant;
	public static Point house;
	public static String bucket = "";
	public static boolean booleanhit = false;
	public static TreeMap<Long, Point> plant = new TreeMap<Long, Point>();
	public static ArrayList<Long> plantarray = new ArrayList<Long>();
	public static long temptime = 0;
	public static int ZERO = 0;
	public static int DOOR_OUTSIDE = 1;
	public static int DOOR_INSIDE = 2;
	public static int LADDER = 3;
	public static int LADDER_INSIDE =4;
	public static int OVEN = 5;
	public static int CHEST = 6;
	public static int BOSSDOOR_INSIDE = 7;
	public static int BOSSDOOR_OUTSIDE = 8;
	public static int TENT_EXIT = 10;
	public static int TENT_ENTRANCE = 11;
	public static int BED = 12;
	public static int SHEEP = 13;
	public static int COW = 14;
	public static int MERCHANT = 15;
	public static int RIDDLEBOX = 16;
	public static int NOTPLACEABLE = 21;
	public static int NOAMMO = 22;
	public static int FUTURISTIC_OVEN = 23;
	public static int MOUNTAINCAVE = 24;
	public static boolean boatused = false;
	public static boolean fishing = false;
	public static boolean gateopened = false;
	public static int boss;
	
	public Entity() {
		animation = new Animation(20, 2);
		
	}
	
	public void update(InputHandler input, ArrayList<Entity> entities) {
		//getNextPosition();
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		
		
		}
	
	
	public void getNextPosition() {
		
	}
	public void validateDo(ArrayList<Entity> entities, double x, double y, double xBefore, double yBefore) {
		int xOff = (xBefore == x ? 0 : (xBefore - x > 0 ? -1 : 1)) -1;
		int yOff = (yBefore == y ? 0 : (yBefore - y > 0 ? -1 : 1)) -1;
		if(BossState.activeItem==null || HouseState.activeItem ==null){
			if(Player.activeItem.getType() == Item.BUCKET){
				if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.WATER.id) {
					bucket = "Wasser";
				}
				if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.LAVA.id) {
					bucket = "Lava";
				}	
				for (Entity e : entities) {
					if( (x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x && (xBefore + hitBox.width+hitBox.x) <= e.x + e.hitBox.x) // BB2 liegt rechts von m_BB
						continue;
					     
					if( (y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y && (yBefore + hitBox.height-hitBox.y) <= e.y - e.hitBox.y) // BB2 liegt unter den m_BB
						continue;
					 
					if( (e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x && (e.x + e.hitBox.width + e.hitBox.x) <= xBefore+hitBox.x) // BB2 liegt links von m_BB
						continue;
					 
					if( (e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y && (e.y + e.hitBox.height - e.hitBox.y) <= yBefore-hitBox.y) // BB2 liegt über den m_BB
					    continue;
					
					if (e instanceof Cow) {
						bucket = "Milch";
					}
				}
			}
			else if(Player.activeItem.getType() == Item.HOE){
				if (x > Player.spawnpointX-100-32 && x < Player.spawnpointX-100+256+32 && y > Player.spawnpointY-220+32 && y < Player.spawnpointY-220+256-32){}
				else if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.GRASS.id) {
					PlayState.world.world.get(Game.playerZ).put(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff), Tile.DIRT_HOED.id);
				}
			}
			else if(Player.activeItem.getType() == Item.BOAT){
				if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.WATER.id) {
					boatused = true;
				}
			}
			else if(Player.activeItem.getType() == Item.FISHINGROD){
				if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.WATER.id) {
					fishing = true;
				}
			}
		}
	}
	
	public void growing(ArrayList<Entity> entities, Point point, int i){
		for (Entity e : entities) {	 
			if((int)e.x == point.x && (int)e.y == point.y){
				if (e instanceof Sapling || e instanceof Potatoe_smal || e instanceof Potatoe_growstage2 || e instanceof Potatoe_growstage3 || 
						e instanceof Pumpkin_smal || e instanceof Pumpkin_growstage2 || e instanceof Pumpkin_growstage3 || e instanceof Wheat_smal || 
						e instanceof Wheat_growstage2 || e instanceof Wheat_growstage3 || e instanceof Melon_smal || e instanceof Melon_growstage2 ||
						e instanceof Melon_growstage3 || e instanceof Carrot_smal || e instanceof Carrot_growstage2 || e instanceof Carrot_growstage3){
					Environment environment = (Environment)e;
					environment.currentLife = 0;
					plant.remove(plantarray.get(i));
					plantarray.remove(i);
					environment.remove = true;
				}
				else{
					plant.remove(plantarray.get(i));
					plantarray.remove(i);
				}
			}
		}
	}
		
	public void validateAttack(ArrayList<Entity> entities, double x, double y, double xBefore, double yBefore) {
		int xOff = (xBefore == x ? 0 : (xBefore - x > 0 ? -1 : 1)) -1;
		int yOff = (yBefore == y ? 0 : (yBefore - y > 0 ? -1 : 1)) -1;
		if (Game.playerZ > 0 && Tile.coolDown <= 0 && (Player.activeItem.getType() == Item.SHOVEL || Player.activeItem.getType() == Item.PICKAXE)) {
			if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) != Tile.STONE.id && Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff))].maxLife != -1) {
				Point hitTile = new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff);
				
				if (Tile.hitTile != null && (Tile.hitTile.x != hitTile.x || Tile.hitTile.y != hitTile.y))
					Tile.tiles[PlayState.world.world.get(Game.playerZ).get(Tile.hitTile)].currentLife = Tile.tiles[PlayState.world.world.get(Game.playerZ).get(Tile.hitTile)].maxLife;
				if (PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.U_SAND.id || PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff)) == Tile.U_DIRT.id){
					if (Player.activeItem.getType() == Item.SHOVEL){
						actualdamage= Player.damage * Player.activeItem.getDamage();
					}
					else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.2;		
				}
				else{
					if (Player.activeItem.getType() == Item.PICKAXE){
						actualdamage= Player.damage * Player.activeItem.getDamage();
					}
					else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.2;	
				}
				
				Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff))].currentLife -= actualdamage;//Player.damage * Player.activeItem.getDamage();
				Tile.coolDown = 350;
				Tile.hitTile = new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff);
				if (Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff))].currentLife <= 0) {
				
					try {
						Tile tile = (Tile)Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff))];
						for(Class key : tile.loot.keySet())
							if (Rand.weightedRandom(new int[]{100-tile.loot.get(key),tile.loot.get(key)}) == 1)
								PlayState.items.add((Item)key.getConstructors()[0].newInstance(xBefore+xOff*32+16, yBefore+yOff*32+16));	
				    } catch (Exception ex) { ex.printStackTrace(); }	
					
					Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff))].currentLife = Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff))].maxLife;
					PlayState.world.world.get(Game.playerZ).put(new Point((int)(xBefore/32)+xOff, (int)(yBefore/32)+yOff), Tile.STONE.id);
					Tile.hitTile = null;					
				}
					
				//Schadensabfrage
				//Drops 
			}
		}
		
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if (e instanceof Item) continue;
			
			//if (GameStateManager.currentState == GameStateManager.BOSS)
				
			
			//if (e instanceof Environment) continue;

			if( (x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x && (xBefore + hitBox.width+hitBox.x) <= e.x + e.hitBox.x) // BB2 liegt rechts von m_BB
				continue;
			     
			if( (y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y && (yBefore + hitBox.height-hitBox.y) <= e.y - e.hitBox.y) // BB2 liegt unter den m_BB
				continue;
			 
			if( (e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x && (e.x + e.hitBox.width + e.hitBox.x) <= xBefore+hitBox.x) // BB2 liegt links von m_BB
				continue;
			 
			if( (e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y && (e.y + e.hitBox.height - e.hitBox.y) <= yBefore-hitBox.y) // BB2 liegt über den m_BB
			    continue;
	
			if ((e instanceof Sapling || e instanceof Pumpkin_smal || e instanceof Potatoe_smal || e instanceof Wheat_smal || e instanceof Melon_smal || e instanceof Carrot_smal)&& Player.activeItem.getName().equals("Eimer Wasser")){
				if(Player.direction == 1 || Player.direction == 2)
					PlayState.world.world.get(Game.playerZ).put(new Point((int)(xBefore/32)-1, (int)(yBefore/32)-1), Tile.DIRT_WATERED.id);
				else if(Player.direction == 0)
					PlayState.world.world.get(Game.playerZ).put(new Point((int)(xBefore/32)-1, (int)(yBefore/32)), Tile.DIRT_WATERED.id);
				else if(Player.direction == 3)
					PlayState.world.world.get(Game.playerZ).put(new Point((int)(xBefore/32)-2, (int)(yBefore/32)-1), Tile.DIRT_WATERED.id);
				
				Random rand = new Random();
				int plantRand;
				plantRand = rand.nextInt((int)Game.DAY/6);
				temptime = Game.TIME + plantRand;
				plantarray.add(temptime);
				plant.put(temptime, new Point((int)e.x,(int)e.y));
			}
			else if (e instanceof Environment) {
				booleanhit=true;
				Environment environment = (Environment)e;
				if (environment.cooldown <= 0) {
					if (e instanceof Rock || e instanceof Rock_yellow || e instanceof Oven){
						if (Player.activeItem.getType() == Item.PICKAXE){
							actualdamage= Player.damage * Player.activeItem.getDamage();
						}
						else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.2;
					}
					else if (e instanceof Gate || e instanceof Fence_horizontal || e instanceof Fence_vertical || e instanceof Plant || 
							e instanceof Tree_blue || e instanceof Tree_green || e instanceof Tree_purple || e instanceof Tree_red){
						if (Player.activeItem.getType() == Item.AXE){
							actualdamage= Player.damage * Player.activeItem.getDamage();
						}
						else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.2;
					}
					else if ( e instanceof Potatoe_growstage2 || e instanceof Potatoe_growstage3 || e instanceof Pumpkin_growstage2 || e instanceof Pumpkin_growstage3 || 
							e instanceof Wheat_growstage2 || e instanceof Wheat_growstage3 || e instanceof Melon_growstage2 ||
							e instanceof Melon_growstage3 || e instanceof Carrot_growstage2 || e instanceof Carrot_growstage3 ||
							e instanceof Pumpkin_lantern || e instanceof Pumpkin_smal || e instanceof Pumpkin_grown || e instanceof Shamrock || 
							e instanceof Flower || e instanceof Shroom || e instanceof Sapling || e instanceof Potatoe_smal || e instanceof Potatoe_grown || 
							e instanceof Melon_smal || e instanceof Melon_grown || e instanceof Wheat_smal || e instanceof Wheat_grown || 
							e instanceof Carrot_smal || e instanceof Carrot_grown || e instanceof Pumpkin_smal || e instanceof Pumpkin_grown){
						actualdamage= Player.damage * Player.activeItem.getDamage();
					}
					else if (e instanceof Ladder){
						if (Player.activeItem.getType() == Item.SHOVEL){
							actualdamage= Player.damage * Player.activeItem.getDamage();
						}
						else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.2;
					}
					else actualdamage= 0;
					environment.currentLife -= actualdamage;
					environment.cooldown = 350;
				}
			}
			else if (e instanceof Monster) {
				booleanhit=true;
				Monster monster = (Monster)e;
				if (monster.cooldown <= 0) {
					if (Player.activeItem.getType() == Item.WEAPON) actualdamage= Player.damage * Player.activeItem.getDamage();
					else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.2;
					if (Player.direction == 0)
						monster.mknockBackY = 50;
					else if (Player.direction == 1)
						monster.mknockBackY = -50;
					else if (Player.direction == 2)
						monster.mknockBackX = 50;
					else if (Player.direction == 3)
						monster.mknockBackX = -50;   
					monster.currentLife -= actualdamage;
					monster.cooldown = 350;
				}
			}
			else if (e instanceof Boss) {
				booleanhit=true;
				Boss boss = (Boss)e;    
				System.out.println("cooldown: " + boss.bosscooldown + ", hitbox: " + hitBox.x + ", " + booleanhit);
				if (boss.bosscooldown <= 0) {
					if (BossState.activeItem.getType() == Item.WEAPON) actualdamage= Player.damage * BossState.activeItem.getDamage()*0.5;
					else actualdamage= Player.damage * BossState.activeItem.getDamage() * 0.1;
					boss.currentLife -= actualdamage;
					boss.bosscooldown = 350;
				}
			}
			else if (e instanceof NPC) {
				booleanhit=true;
				NPC npc = (NPC)e;
				if (e instanceof Merchant){}
				else if (npc.cooldown <= 0) {
						if (Player.activeItem.getType() == Item.WEAPON) actualdamage= Player.damage * Player.activeItem.getDamage();
						else actualdamage= Player.damage * Player.activeItem.getDamage() * 0.7;
						if (Player.direction == 0)
							npc.mknockBackY = 50;
						else if (Player.direction == 1)
							npc.mknockBackY = -50;
						else if (Player.direction == 2)
							npc.mknockBackX = 50;
						else if (Player.direction == 3)
							npc.mknockBackX = -50; 
						npc.runcountdown =100;
						npc.currentLife -= actualdamage;
						npc.cooldown = 350;
					}
			}
		}
	}
	
	public void validateRangedAttack (ArrayList<Entity> entities, double x, double y, double xBefore, double yBefore) {
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if (e instanceof Item) continue;
			if (e instanceof Environment) continue;

			if( (x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x && (xBefore + hitBox.width+hitBox.x) <= e.x + e.hitBox.x) // BB2 liegt rechts von m_BB
				continue;
			     
			if( (y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y && (yBefore + hitBox.height-hitBox.y) <= e.y - e.hitBox.y) // BB2 liegt unter den m_BB
				continue;
			 
			if( (e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x && (e.x + e.hitBox.width + e.hitBox.x) <= xBefore+hitBox.x) // BB2 liegt links von m_BB
				continue;
			 
			if( (e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y && (e.y + e.hitBox.height - e.hitBox.y) <= yBefore-hitBox.y) // BB2 liegt über den m_BB
			    continue;
	
			else if (e instanceof Monster) {
				Monster monster = (Monster)e;
				if (monster.cooldown <= 0) {
					actualdamage= Player.damage * Player.activeItem.getDamage();
					monster.currentLife -= actualdamage;
					monster.cooldown = 350;
				}
			}
			else if (e instanceof Boss) {
				Boss boss = (Boss)e;   
				if (boss.bosscooldown <= 0) {
					actualdamage= Player.damage * BossState.activeItem.getDamage() *0.5;
					boss.currentLife -= actualdamage;
					boss.bosscooldown = 350;
				}
			}
			else if (e instanceof NPC) {
				NPC npc = (NPC)e; 
				if (e instanceof Merchant){}
				else if (npc.cooldown <= 0) {
						actualdamage= Player.damage * Player.activeItem.getDamage();
						npc.currentLife -= actualdamage;
						npc.runcountdown =100;
						npc.cooldown = 350;
					}
			}
			
		}
	}

	public void validatebowAttack(ArrayList<Entity> entities, double x, double y) {
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if (e instanceof Item) continue;
			if (e instanceof Environment) continue;
			
	
			if ((e.y <= y+20 && e.y >= y-20 && e.x <= x+20 && e.x >= x-20) && e instanceof Monster) {
				Monster monster = (Monster)e;
				if (monster.cooldown <= 0) {
					if(Player.activeItem != null)
						actualdamage= Player.damage * Player.activeItem.getDamage();
					else actualdamage = Player.damage * 1; //Egg damage
					monster.currentLife -= actualdamage;
					monster.cooldown = 350;
					Player.BowhitAnimation = 0;
					Player.EgghitAnimation = 0;
				}
			}
			else if ((e.y <= y+40 && e.y >= y-40 && e.x <= x+40 && e.x >= x-40) && e instanceof Boss) {
				Boss boss = (Boss)e;   
				if (boss.bosscooldown <= 0) {
					actualdamage= Player.damage * BossState.activeItem.getDamage() *0.5;
					boss.currentLife -= actualdamage;
					boss.bosscooldown = 350;
					BossState.BowhitAnimation = 0;
				}
			}
			else if ((e.y <= y+20 && e.y >= y-20 && e.x <= x+20 && e.x >= x-20) && e instanceof NPC) {
				NPC npc = (NPC)e;
				if (e instanceof Merchant){}
				else if (npc.cooldown <= 0) {
						actualdamage= Player.damage * Player.activeItem.getDamage();
						npc.currentLife -= actualdamage;
						npc.cooldown = 350;
						npc.runcountdown =100;
						Player.BowhitAnimation = 0;
						Player.EgghitAnimation = 0;
					}
			}	
		}
	}
	
	public boolean validateNextPosition(ArrayList<Entity> entities, double x, double y) {
		if (Player.currentlife <= 0)
			return false;
		if (Game.playerZ == 0 && GameStateManager.currentState == GameStateManager.TUTORIAL2) {

		}
		else if (Game.Debug)
			return false;
		// Kollisionsabfrage mit z.B. Wasser NICHT LÖSCHEN ;)
		else if (GameStateManager.currentState == GameStateManager.PUZZLE) {
			if (Tile.tiles[PuzzleState.map[(int)((y-this.hitBox.y)/32)][(int)(x/32)]].accessable == false)
				return true;
		}
		else if (GameStateManager.currentState == GameStateManager.HOUSE || GameStateManager.currentState == GameStateManager.BOSS) {}
		else if (Monster.checkmonster == true || NPC.checknpc == true){
			if (Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(x/32)-1, (int)((y-this.hitBox.y)/32)-1))].id == Tile.WATER.id)
				return true;
		}
		else if (Player.swimming){
			if (Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(x/32)-1, (int)((y-this.hitBox.y)/32)-1))].id != Tile.WATER.id){
				if (Player.direction == 0)
					Game.playerY+=6;
				else if (Player.direction == 1)
					Game.playerY-=6;
				else if (Player.direction == 2)
					Game.playerX+=6;
				else if (Player.direction == 3)
					Game.playerX-=6;
				Player.swimming=false;
				PlayState.inventory.addItem(new Boat(1, 1));
			}
			else return false;
		}
		else if (Game.playerZ == 0) {
			if (Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(x/32)-1, (int)((y-this.hitBox.y)/32)-1))].accessable == false)
				return true;
		}
		else {
			if (Tile.tiles[PlayState.world.world.get(Game.playerZ).get(new Point((int)(x/32)-1, (int)((y-this.hitBox.y)/32)-1))].accessable == false)
				return true;
		}
		
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if(this instanceof Player && e instanceof Projectile) continue;
			
			if ((x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x)		//rechts
				continue;
			     
			if ((y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y)		//unten
				continue;

			if ((e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x)		//links
				continue;
			
			if ((e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y)		//oben
				continue;
	
			if (e instanceof Item && this.getClass() == Player.class) {	//Spieler kollidiert mit Item
				Music.getPickupSound();
				PlayState.removeEntity.add(e);
				continue;
			}  
			
			if (e instanceof Player && this instanceof Projectile) {
				Projectile projectile = (Projectile)this;
				if (projectile.cooldown <= 0) {
					Player.currentlife -= projectile.damage;
					projectile.cooldown = 400;
					continue;
				}
				
			}
			
			//if (e instanceof Item || this instanceof Item)				//Kollidierung mit Item
				//continue;
			    
			return true;
		}

		return false;
	}
	
	public int validateUse(ArrayList<Entity> entities, double x, double y, double xBefore, double yBefore) {
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if (e instanceof Item) continue;
			//if (e instanceof Environment) continue;

			if( (x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x && (xBefore + hitBox.width+hitBox.x) <= e.x + e.hitBox.x) // BB2 liegt rechts von m_BB
				continue;
			     
			if( (y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y && (yBefore + hitBox.height-hitBox.y) <= e.y - e.hitBox.y) // BB2 liegt unter den m_BB
				continue;
			 
			if( (e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x && (e.x + e.hitBox.width + e.hitBox.x) <= xBefore+hitBox.x) // BB2 liegt links von m_BB
				continue;
			 
			if( (e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y && (e.y + e.hitBox.height - e.hitBox.y) <= yBefore-hitBox.y) // BB2 liegt über den m_BB
			    continue;
	
			if (e instanceof Door_outside){
				HouseState.bighouseentered = true;
				house = new Point((int)e.x, (int)e.y);
				return DOOR_OUTSIDE;
			}
			if (e instanceof Tent_entrance){
				HouseState.tententered = true;
				house = new Point((int)e.x, (int)e.y);
				return TENT_ENTRANCE;
			}
			if (e instanceof Door_inside) return DOOR_INSIDE;
			if (e instanceof Tent_exit) return TENT_EXIT;
			if (e instanceof Ladder) {
				ladder = new Point((int)e.x, (int)e.y);
				//((Ladder) e).use(); mal schaun obs so ned besser geht ...
				return LADDER;
			}
			if (e instanceof Ladder_inside) return LADDER_INSIDE;
			if (e instanceof Oven) {
				oven = new Point((int)e.x, (int)e.y);
				return OVEN;
			}
			if (e instanceof Futuristic_oven) {
				oven = new Point((int)e.x, (int)e.y);
				return FUTURISTIC_OVEN;
			}
			if (e instanceof Chest) {
				chest = new Point((int)e.x, (int)e.y);
				return CHEST;
			}
			if (e instanceof Gate){
				Gate gate = (Gate)e;   
				gate.currentLife = 0;
				gateopened = true;
			}
			if (e instanceof Bossdoor_inside) return BOSSDOOR_INSIDE;
			if (e instanceof Bossdoor_outside) return BOSSDOOR_OUTSIDE;
			if (e instanceof Mountaincave) {
				Mountaincave tmp = (Mountaincave) e;
				boss = tmp.boss;
				return MOUNTAINCAVE;
				}
			if (e instanceof Bed_green || e instanceof Bed_purple || e instanceof Bed_red || e instanceof Bed_gold || e instanceof Bed_blue){
				bed = new Point((int)e.x, (int)e.y);
				return BED;
			}
			if (e instanceof Sheep){ 
				return SHEEP;
			}
			if (e instanceof Cow){
				return COW;
			}
			if (e instanceof Merchant){
				merchant = new Point((int)e.x, (int)e.y);
				return MERCHANT;
			}
			if (e instanceof Riddlebox)	return RIDDLEBOX;
		}
		return 0;
	}
	
	public int validateAnimal(ArrayList<Entity> entities, double x, double y, double xBefore, double yBefore) {
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if (e instanceof Item) continue;

			if( (x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x && (xBefore + hitBox.width+hitBox.x) <= e.x + e.hitBox.x) // BB2 liegt rechts von m_BB
				continue;
			     
			if( (y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y && (yBefore + hitBox.height-hitBox.y) <= e.y - e.hitBox.y) // BB2 liegt unter den m_BB
				continue;
			 
			if( (e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x && (e.x + e.hitBox.width + e.hitBox.x) <= xBefore+hitBox.x) // BB2 liegt links von m_BB
				continue;
			 
			if( (e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y && (e.y + e.hitBox.height - e.hitBox.y) <= yBefore-hitBox.y) // BB2 liegt über den m_BB
			    continue;
	
			if (e instanceof Sheep){ 
				NPC npc = (NPC)e;
				animal = new Point((int)e.x, (int)e.y);
				npc.animalfollow=true;
				return SHEEP;
			}
			if (e instanceof Cow){
				NPC npc = (NPC)e;
				animal = new Point((int)e.x, (int)e.y);
				npc.animalfollow=true;
				return COW;
			}
		}
		return 0;
	}
	
	public int validateBreeding(ArrayList<Entity> entities, double x, double y, double xBefore, double yBefore) {
		for (Entity e : entities) {
			if(this.equals(e)) continue;
			if (e instanceof Item) continue;

			if( (x + hitBox.width+hitBox.x) <= e.x + e.hitBox.x && (xBefore + hitBox.width+hitBox.x) <= e.x + e.hitBox.x) // BB2 liegt rechts von m_BB
				continue;
			     
			if( (y + hitBox.height-hitBox.y) <= e.y - e.hitBox.y && (yBefore + hitBox.height-hitBox.y) <= e.y - e.hitBox.y) // BB2 liegt unter den m_BB
				continue;
			 
			if( (e.x + e.hitBox.width + e.hitBox.x) <= x+hitBox.x && (e.x + e.hitBox.width + e.hitBox.x) <= xBefore+hitBox.x) // BB2 liegt links von m_BB
				continue;
			 
			if( (e.y + e.hitBox.height - e.hitBox.y) <= y-hitBox.y && (e.y + e.hitBox.height - e.hitBox.y) <= yBefore-hitBox.y) // BB2 liegt über den m_BB
			    continue;
	
			if (e instanceof Sheep){ 
				for (Entity f : entities) {
					if (f instanceof Sheep){ 
						if (f.x > e.x-96 && f.x < e.x+96 && f.y < e.y +96 && f.y > e.y-96){
							NPC npc = (NPC)e;
							NPC npc2 = (NPC)f;
							if(npc.breedcountdown==0 && npc2.breedcountdown == 0){
								animal = new Point((int)e.x, (int)e.y);
								npc.breedcountdown = (int)Game.DAY;
								npc2.breedcountdown = (int)Game.DAY;
								return SHEEP;
							}
						}
					}
				}
				return 0;
			}
			if (e instanceof Cow){
				for (Entity f : entities) {
					if (f instanceof Cow){ 
						if (f.x > e.x-96 && f.x < e.x+96 && f.y < e.y +96 && f.y > e.y-96 ){
							NPC npc = (NPC)e;
							NPC npc2 = (NPC)f;
							if(npc.breedcountdown==0 && npc2.breedcountdown == 0){
								animal = new Point((int)e.x, (int)e.y);
								npc.breedcountdown = (int)Game.DAY;
								npc2.breedcountdown = (int)Game.DAY;
								return COW;
							}
						}
					}
				}
				return 0;
			}
		}
		return 0;
	}
	
	public static void deletefence(ArrayList<Entity> entities, double x, double y) {
		for (Entity e : entities) {
			if(e instanceof Gate_open)
				if (e.x == x && e.y == y) ((Gate_open) e).currentLife = 0;
		}
	}
			
	public double getX() { return x; }
	public double getY() { return y; }
	
	public int compareTo(Entity e){
		//return (int)((y + height) - (e.y + e.height));
		if (e instanceof Crash)
			return 1;
		if (this instanceof Crash)
			return -1;
		return (int)((y-hitBox.y +hitBox.height) - (e.y-e.hitBox.y + e.hitBox.height));
	}
}


