package Manager;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Boss.*;
import Entity.Environment.*;
import Entity.Item.*;
import Entity.Monster.*;
import Entity.NPC.*;

public class Content {

	private String name;
	private String path;
	private int width;
	private int height;
	private BufferedImage[][] image;
	public Animation animation;
	
	//public static Content[] images = new Content[100];
	public static Content MONSTER_ORC = new Content(Orc.NAME, Orc.PATH, Orc.WIDTH, Orc.HEIGHT, new Point(3,4));
	public static Content MONSTER_BLOBGREEN = new Content(Blob_green.NAME, Blob_green.PATH, Blob_green.WIDTH, Blob_green.HEIGHT, new Point(3,4));
	public static Content MONSTER_BLOBRED = new Content(Blob_red.NAME, Blob_red.PATH, Blob_red.WIDTH, Blob_red.HEIGHT, new Point(3,4));
	public static Content MONSTER_BLOBPURPLE = new Content(Blob_purple.NAME, Blob_purple.PATH, Blob_purple.WIDTH, Blob_purple.HEIGHT, new Point(3,4));
	public static Content MONSTER_BLOBGOLD = new Content(Blob_gold.NAME, Blob_gold.PATH, Blob_gold.WIDTH, Blob_gold.HEIGHT, new Point(3,4));
	public static Content MONSTER_BLOBBLUE = new Content(Blob_blue.NAME, Blob_blue.PATH, Blob_blue.WIDTH, Blob_blue.HEIGHT, new Point(3,4));
	public static Content MONSTER_SPIDER = new Content(Spider.NAME, Spider.PATH, Spider.WIDTH, Spider.HEIGHT, new Point(3,4));
	public static Content MONSTER_TREEGUARD = new Content(Treeguard.NAME, Treeguard.PATH, Treeguard.WIDTH, Treeguard.HEIGHT, new Point(3,4));
	public static Content MONSTER_BABYDRAGON = new Content(Baby_dragon.NAME, Baby_dragon.PATH, Baby_dragon.WIDTH, Baby_dragon.HEIGHT, new Point(3,4));
	
	public static Content BOSS_MUFFIN = new Content(Muffin.NAME, Muffin.PATH, Muffin.WIDTH, Muffin.HEIGHT, new Point(3,4));
	public static Content BOSS_MUFFINRAGE = new Content(Muffin.NAME, Muffin.PATH2, Muffin.WIDTH, Muffin.HEIGHT, new Point(3,4));
	public static Content BOSS_BEAST = new Content(Beast.NAME, Beast.PATH, Beast.WIDTH, Beast.HEIGHT, new Point(3,1));
	public static Content BOSS_TROLLPRIEST = new Content(Troll_priest.NAME, Troll_priest.PATH, Troll_priest.WIDTH, Troll_priest.HEIGHT, new Point(3,1));
	public static Content BOSS_SHADOW = new Content(Shadow.NAME, Shadow.PATH, Shadow.WIDTH, Shadow.HEIGHT, new Point(3,4));
	
	public static Content ENV_TREEBIG = new Content(Tree_big.NAME, Tree_big.PATH, Tree_big.WIDTH, Tree_big.HEIGHT, new Point(1,1));
	public static Content ENV_FLOWER = new Content(Flower.NAME, Flower.PATH, Flower.WIDTH, Flower.HEIGHT, new Point(1,1));
	public static Content ENV_TREEGREEN = new Content(Tree_green.NAME, Tree_green.PATH, Tree_green.WIDTH, Tree_green.HEIGHT, new Point(1,1));
	public static Content ENV_TREERED = new Content(Tree_red.NAME, Tree_red.PATH, Tree_red.WIDTH, Tree_red.HEIGHT, new Point(1,1));
	public static Content ENV_TREEBLUE = new Content(Tree_blue.NAME, Tree_blue.PATH, Tree_blue.WIDTH, Tree_blue.HEIGHT, new Point(1,1));
	public static Content ENV_TREEPURPLE = new Content(Tree_purple.NAME, Tree_purple.PATH, Tree_purple.WIDTH, Tree_purple.HEIGHT, new Point(1,1));
	public static Content ENV_CRASH = new Content(Crash.NAME, Crash.PATH, Crash.WIDTH, Crash.HEIGHT, new Point(1,3));
	public static Content ENV_CACTUS = new Content(Rock_yellow.NAME, Rock_yellow.PATH, Rock_yellow.WIDTH, Rock_yellow.HEIGHT, new Point(1,1));
	public static Content ENV_ROCK = new Content(Rock.NAME, Rock.PATH, Rock.WIDTH, Rock.HEIGHT, new Point(1,1));
	public static Content ENV_PLANT = new Content(Plant.NAME, Plant.PATH, Plant.WIDTH, Plant.HEIGHT, new Point(1,1));
	public static Content ENV_SHROOM = new Content(Shroom.NAME, Shroom.PATH, Shroom.WIDTH, Shroom.HEIGHT,new Point(1,1));
	public static Content ENV_HOUSE = new Content(House.NAME, House.PATH, House.WIDTH, House.HEIGHT,new Point(1,1));
	public static Content ENV_DOOROUTSIDE = new Content(Door_outside.NAME, Door_outside.PATH, Door_outside.WIDTH, Door_outside.HEIGHT,new Point(1,1));
	public static Content ENV_OVEN = new Content(Oven.NAME, Oven.PATH, Oven.WIDTH, Oven.HEIGHT,new Point(1,1));
	public static Content ENV_ENTRANCE = new Content(Ladder.NAME, Ladder.PATH, Ladder.WIDTH, Ladder.HEIGHT,new Point(1,1));
	public static Content ENV_LADDEREXIT = new Content(Ladder_inside.NAME, Ladder_inside.PATH, Ladder_inside.WIDTH, Ladder_inside.HEIGHT,new Point(1,1));
	public static Content ENV_SHAMROCK = new Content(Shamrock.NAME, Shamrock.PATH, Shamrock.WIDTH, Shamrock.HEIGHT,new Point(1,1));
	public static Content ENV_CHEST = new Content(Chest.NAME, Chest.PATH, Chest.WIDTH, Chest.HEIGHT,new Point(1,1));
	public static Content ENV_BOSSDOOR = new Content(Bossdoor_outside.NAME, Bossdoor_outside.PATH, Bossdoor_outside.WIDTH, Bossdoor_outside.HEIGHT,new Point(1,1));
	public static Content ENV_HITBOX = new Content(Hitbox_environment.NAME, Hitbox_environment.PATH, Hitbox_environment.WIDTH, Hitbox_environment.HEIGHT,new Point(1,1));
	public static Content ENV_BOSSDOORINSIDE = new Content(Bossdoor_inside.NAME, Bossdoor_inside.PATH, Bossdoor_inside.WIDTH, Bossdoor_inside.HEIGHT, new Point(1,1));
	public static Content ENV_SAPLING = new Content(Sapling.NAME, Sapling.PATH, Sapling.WIDTH, Sapling.HEIGHT, new Point(1,1));
	public static Content ENV_POTATOE_SMAL = new Content(Potatoe_smal.NAME, Potatoe_smal.PATH, Potatoe_smal.WIDTH, Potatoe_smal.HEIGHT, new Point(1,1));
	public static Content ENV_POTATOE_GROWSTAGE2 = new Content(Potatoe_growstage2.NAME, Potatoe_growstage2.PATH, Potatoe_growstage2.WIDTH, Potatoe_growstage2.HEIGHT, new Point(1,1));
	public static Content ENV_POTATOE_GROWSTAGE3 = new Content(Potatoe_growstage3.NAME, Potatoe_growstage3.PATH, Potatoe_growstage3.WIDTH, Potatoe_growstage3.HEIGHT, new Point(1,1));
	public static Content ENV_POTATOE_GROWN = new Content(Potatoe_grown.NAME, Potatoe_grown.PATH, Potatoe_grown.WIDTH, Potatoe_grown.HEIGHT, new Point(1,1));
	public static Content ENV_TENT = new Content(Tent.NAME, Tent.PATH, Tent.WIDTH, Tent.HEIGHT, new Point(1,1));
	public static Content ENV_RIDDLEBOX = new Content(Riddlebox.NAME, Riddlebox.PATH, Riddlebox.WIDTH, Riddlebox.HEIGHT, new Point(1,1));
	public static Content ENV_DOORINSIDE = new Content(Door_inside.NAME, Door_inside.PATH, Door_inside.WIDTH, Door_inside.HEIGHT,new Point(1,1));
	public static Content ENV_BEDGREEN = new Content(Bed_green.NAME, Bed_green.PATH, Bed_green.WIDTH, Bed_green.HEIGHT,new Point(1,1));
	public static Content ENV_BEDRED = new Content(Bed_red.NAME, Bed_red.PATH, Bed_red.WIDTH, Bed_red.HEIGHT,new Point(1,1));
	public static Content ENV_BEDPURPLE = new Content(Bed_purple.NAME, Bed_purple.PATH, Bed_purple.WIDTH, Bed_purple.HEIGHT,new Point(1,1));
	public static Content ENV_BEDGOLD = new Content(Bed_gold.NAME, Bed_gold.PATH, Bed_gold.WIDTH, Bed_gold.HEIGHT,new Point(1,1));
	public static Content ENV_BEDBLUE = new Content(Bed_blue.NAME, Bed_blue.PATH, Bed_blue.WIDTH, Bed_blue.HEIGHT,new Point(1,1));
	public static Content ENV_TABLE = new Content(Table.NAME, Table.PATH, Table.WIDTH, Table.HEIGHT,new Point(1,1));
	public static Content ENV_BOOKSHELF = new Content(Bookshelf.NAME, Bookshelf.PATH, Bookshelf.WIDTH, Bookshelf.HEIGHT,new Point(1,1));
	public static Content ENV_CHAIR = new Content(Chair.NAME, Chair.PATH, Chair.WIDTH, Chair.HEIGHT,new Point(1,1));
	public static Content ENV_WHEAT_SMAL = new Content(Wheat_smal.NAME, Wheat_smal.PATH, Wheat_smal.WIDTH, Wheat_smal.HEIGHT, new Point(1,1));
	public static Content ENV_WHEAT_GROWSTAGE2 = new Content(Wheat_growstage2.NAME, Wheat_growstage2.PATH, Wheat_growstage2.WIDTH, Wheat_growstage2.HEIGHT, new Point(1,1));
	public static Content ENV_WHEAT_GROWSTAGE3 = new Content(Wheat_growstage3.NAME, Wheat_growstage3.PATH, Wheat_growstage3.WIDTH, Wheat_growstage3.HEIGHT, new Point(1,1));
	public static Content ENV_WHEAT_GROWN = new Content(Wheat_grown.NAME, Wheat_grown.PATH, Wheat_grown.WIDTH, Wheat_grown.HEIGHT, new Point(1,1));
	public static Content ENV_CARROT_SMAL = new Content(Carrot_smal.NAME, Carrot_smal.PATH, Carrot_smal.WIDTH, Carrot_smal.HEIGHT, new Point(1,1));
	public static Content ENV_CARROT_GROWSTAGE2 = new Content(Carrot_growstage2.NAME, Carrot_growstage2.PATH, Carrot_growstage2.WIDTH, Carrot_growstage2.HEIGHT, new Point(1,1));
	public static Content ENV_CARROT_GROWSTAGE3 = new Content(Carrot_growstage3.NAME, Carrot_growstage3.PATH, Carrot_growstage3.WIDTH, Carrot_growstage3.HEIGHT, new Point(1,1));
	public static Content ENV_CARROT_GROWN = new Content(Carrot_grown.NAME, Carrot_grown.PATH, Carrot_grown.WIDTH, Carrot_grown.HEIGHT, new Point(1,1));
	public static Content ENV_MELON_SMAL = new Content(Melon_smal.NAME, Melon_smal.PATH, Melon_smal.WIDTH, Melon_smal.HEIGHT, new Point(1,1));
	public static Content ENV_MELON_GROWSTAGE2 = new Content(Melon_growstage2.NAME, Melon_growstage2.PATH, Melon_growstage2.WIDTH, Melon_growstage2.HEIGHT, new Point(1,1));
	public static Content ENV_MELON_GROWSTAGE3 = new Content(Melon_growstage3.NAME, Melon_growstage3.PATH, Melon_growstage3.WIDTH, Melon_growstage3.HEIGHT, new Point(1,1));
	public static Content ENV_MELON_GROWN = new Content(Melon_grown.NAME, Melon_grown.PATH, Melon_grown.WIDTH, Melon_grown.HEIGHT, new Point(1,1));
	public static Content ENV_GATE = new Content(Gate.NAME, Gate.PATH, Gate.WIDTH, Gate.HEIGHT, new Point(1,1));
	public static Content ENV_GATE_OPEN = new Content(Gate_open.NAME, Gate_open.PATH, Gate_open.WIDTH, Gate_open.HEIGHT, new Point(1,1));
	public static Content ENV_FENCE_HORIZONTAL = new Content(Fence_horizontal.NAME, Fence_horizontal.PATH, Fence_horizontal.WIDTH, Fence_horizontal.HEIGHT, new Point(1,1));
	public static Content ENV_FENCE_VERTICAL = new Content(Fence_vertical.NAME, Fence_vertical.PATH, Fence_vertical.WIDTH, Fence_vertical.HEIGHT, new Point(1,1));
	public static Content ENV_PUMPKIN_LANTERN = new Content(Pumpkin_lantern.NAME, Pumpkin_lantern.PATH, Pumpkin_lantern.WIDTH, Pumpkin_lantern.HEIGHT, new Point(1,1));
	public static Content ENV_PUMPKIN_SMAL = new Content(Pumpkin_smal.NAME, Pumpkin_smal.PATH, Pumpkin_smal.WIDTH, Pumpkin_smal.HEIGHT, new Point(1,1));
	public static Content ENV_PUMPKIN_GROWSTAGE2 = new Content(Pumpkin_growstage2.NAME, Pumpkin_growstage2.PATH, Pumpkin_growstage2.WIDTH, Pumpkin_growstage2.HEIGHT, new Point(1,1));
	public static Content ENV_PUMPKIN_GROWSTAGE3 = new Content(Pumpkin_growstage3.NAME, Pumpkin_growstage3.PATH, Pumpkin_growstage3.WIDTH, Pumpkin_growstage3.HEIGHT, new Point(1,1));
	public static Content ENV_PUMPKIN_GROWN = new Content(Pumpkin_grown.NAME, Pumpkin_grown.PATH, Pumpkin_grown.WIDTH, Pumpkin_grown.HEIGHT, new Point(1,1));
	public static Content ENV_TENTENTRANCE = new Content(Tent_entrance.NAME, Tent_entrance.PATH, Tent_entrance.WIDTH, Tent_entrance.HEIGHT, new Point(1,1));
	public static Content ENV_TENTEXIT = new Content(Tent_exit.NAME, Tent_exit.PATH, Tent_exit.WIDTH, Tent_exit.HEIGHT, new Point(1,1));
	public static Content ENV_FUTURISTIC_OVEN = new Content(Futuristic_oven.NAME, Futuristic_oven.PATH, Futuristic_oven.WIDTH, Futuristic_oven.HEIGHT, new Point(1,1));
	public static Content ENV_WATERFALL = new Content(Waterfall.NAME, Waterfall.PATH, Waterfall.WIDTH, Waterfall.HEIGHT, new Point(1,1));
	public static Content ENV_BOOKSHELF_SPACE = new Content(Bookshelf_space.NAME, Bookshelf_space.PATH, Bookshelf_space.WIDTH, Bookshelf_space.HEIGHT, new Point(1,1));
	public static Content ENV_CHAIR_SPACE = new Content(Chair_space.NAME, Chair_space.PATH, Chair_space.WIDTH, Chair_space.HEIGHT, new Point(1,1));
	public static Content ENV_CHEST_SPACE = new Content(Chest_space.NAME, Chest_space.PATH, Chest_space.WIDTH, Chest_space.HEIGHT, new Point(1,1));
	public static Content ENV_TABLE_SPACE = new Content(Table_space.NAME, Table_space.PATH, Table_space.WIDTH, Table_space.HEIGHT, new Point(1,1));
	public static Content ENV_OBELISK = new Content(Obelisk.NAME, Obelisk.PATH, Obelisk.WIDTH, Obelisk.HEIGHT, new Point(1,1));
	public static Content ENV_BEDSPACE = new Content(Bed_space.NAME, Bed_space.PATH, Bed_space.WIDTH, Bed_space.HEIGHT, new Point(1,1));
	public static Content ENV_MOUNTAINCAVE = new Content(Mountaincave.NAME, Mountaincave.PATH, Mountaincave.WIDTH, Mountaincave.HEIGHT, new Point(1,1));
	
	
	public static Content ITEM_SWORD_WOOD = new Content(Sword_Wood.NAME, Sword_Wood.PATH, Sword_Wood.WIDTH, Sword_Wood.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_IRON = new Content(Sword_Iron.NAME, Sword_Iron.PATH, Sword_Iron.WIDTH, Sword_Iron.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_STONE = new Content(Sword_stone.NAME, Sword_stone.PATH, Sword_stone.WIDTH, Sword_stone.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_GOLD = new Content(Sword_gold.NAME, Sword_gold.PATH, Sword_gold.WIDTH, Sword_gold.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_DIAMOND = new Content(Sword_diamond.NAME, Sword_diamond.PATH, Sword_diamond.WIDTH, Sword_diamond.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_BRONZE = new Content(Sword_bronze.NAME, Sword_bronze.PATH, Sword_bronze.WIDTH, Sword_bronze.HEIGHT, new Point(1,1));
	public static Content ITEM_EXCALIBUR = new Content(Excalibur.NAME, Excalibur.PATH, Excalibur.WIDTH, Excalibur.HEIGHT, new Point(1,1));
	public static Content ITEM_BOOTSLEATHER = new Content(Boots_leather.NAME, Boots_leather.PATH, Boots_leather.WIDTH, Boots_leather.HEIGHT, new Point(1,1));
	public static Content ITEM_CHESTLEATHER = new Content(Chest_leather.NAME, Chest_leather.PATH, Chest_leather.WIDTH, Chest_leather.HEIGHT,new Point(1,1));
	public static Content ITEM_HELMETLEATHER = new Content(Helmet_leather.NAME, Helmet_leather.PATH, Helmet_leather.WIDTH, Helmet_leather.HEIGHT, new Point(1,1));
	public static Content ITEM_LEGGINSLEATHER = new Content(Leggins_leather.NAME, Leggins_leather.PATH, Leggins_leather.WIDTH, Leggins_leather.HEIGHT, new Point(1,1));
	public static Content ITEM_HEALTHP = new Content(HealthPot.NAME, HealthPot.PATH, HealthPot.WIDTH, HealthPot.HEIGHT, new Point(1,1));
	public static Content ITEM_PISTOL = new Content(Pistol.NAME, Pistol.PATH, Pistol.WIDTH, Pistol.HEIGHT, new Point(1,1));
	public static Content ITEM_TORCH = new Content(Torch.NAME, Torch.PATH, Torch.WIDTH, Torch.HEIGHT, new Point(1,1));
	public static Content ITEM_AMMONITION = new Content(Ammonition.NAME, Ammonition.PATH, Ammonition.WIDTH, Ammonition.HEIGHT, new Point(1,1));
	public static Content ITEM_ENERGY = new Content(Energy.NAME, Energy.PATH, Energy.WIDTH, Energy.HEIGHT, new Point(1,1));
	public static Content ITEM_SAND = new Content(Sand_item.NAME, Sand_item.PATH, Sand_item.WIDTH, Sand_item.HEIGHT, new Point(1,1));
	public static Content ITEM_MEAT = new Content(Meat.NAME, Meat.PATH, Meat.WIDTH, Meat.HEIGHT, new Point(1,1));
	public static Content ITEM_STONE = new Content(Stone_item.NAME, Stone_item.PATH, Stone_item.WIDTH, Stone_item.HEIGHT, new Point(1,1));
	public static Content ITEM_SLIMEGREEN = new Content(Slime_green.NAME, Slime_green.PATH, Slime_green.WIDTH, Slime_green.HEIGHT, new Point(1,1));
	public static Content ITEM_SLIMERED = new Content(Slime_red.NAME, Slime_red.PATH, Slime_red.WIDTH, Slime_red.HEIGHT, new Point(1,1));
	public static Content ITEM_SLIMEBLUE = new Content(Slime_blue.NAME, Slime_blue.PATH, Slime_blue.WIDTH, Slime_blue.HEIGHT, new Point(1,1));
	public static Content ITEM_SLIMEPURPLE = new Content(Slime_purple.NAME, Slime_purple.PATH, Slime_purple.WIDTH, Slime_purple.HEIGHT, new Point(1,1));
	public static Content ITEM_SLIMEGOLD = new Content(Slime_gold.NAME, Slime_gold.PATH, Slime_gold.WIDTH, Slime_gold.HEIGHT, new Point(1,1));
	public static Content ITEM_STICK= new Content(Stick.NAME, Stick.PATH, Stick.WIDTH, Stick.HEIGHT, new Point(1,1));
	public static Content ITEM_STRINGITEM= new Content(String_item.NAME, String_item.PATH, String_item.WIDTH, String_item.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOD = new Content(Wood.NAME, Wood.PATH, Wood.WIDTH, Wood.HEIGHT, new Point(1,1));
	public static Content ITEM_IRON = new Content(Iron.NAME, Iron.PATH, Iron.WIDTH, Iron.HEIGHT, new Point(1,1));
	public static Content ITEM_GOLD = new Content(Gold.NAME, Gold.PATH, Gold.WIDTH, Gold.HEIGHT, new Point(1,1));
	public static Content ITEM_DIAMOND= new Content(Diamond.NAME, Diamond.PATH, Diamond.WIDTH, Diamond.HEIGHT, new Point(1,1));
	public static Content ITEM_COAL = new Content(Coal.NAME, Coal.PATH, Coal.WIDTH, Coal.HEIGHT, new Point(1,1));
	public static Content ITEM_BRONZE = new Content(Bronze.NAME, Bronze.PATH, Bronze.WIDTH, Bronze.HEIGHT, new Point(1,1));
	public static Content ITEM_LADDER = new Content(Ladder_item.NAME, Ladder_item.PATH, Ladder_item.WIDTH, Ladder_item.HEIGHT, new Point(1,1));
	public static Content ITEM_SHROOM = new Content(Shroom_item.NAME, Shroom_item.PATH, Shroom_item.WIDTH, Shroom_item.HEIGHT, new Point(1,1));
	public static Content ITEM_FLOWER = new Content(Flower_item.NAME, Flower_item.PATH, Flower_item.WIDTH, Flower_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BRONZE_CHUNK = new Content(Bronze_chunk.NAME, Bronze_chunk.PATH, Bronze_chunk.WIDTH, Bronze_chunk.HEIGHT, new Point(1,1));
	public static Content ITEM_OVEN = new Content(Oven_item.NAME, Oven_item.PATH, Oven_item.WIDTH, Oven_item.HEIGHT, new Point(1,1));
	public static Content ITEM_IRON_ORE = new Content(Iron_ore.NAME, Iron_ore.PATH, Iron_ore.WIDTH, Iron_ore.HEIGHT, new Point(1,1));
	public static Content ITEM_GOLD_ORE = new Content(Gold_ore.NAME, Gold_ore.PATH, Gold_ore.WIDTH, Gold_ore.HEIGHT, new Point(1,1));
	public static Content ITEM_HOUSEITEM = new Content(House_item.NAME, House_item.PATH, House_item.WIDTH, House_item.HEIGHT, new Point(1,1));
	public static Content ITEM_ARROW = new Content(Arrow.NAME, Arrow.PATH, Arrow.WIDTH, Arrow.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_RUBY = new Content(Sword_ruby.NAME, Sword_ruby.PATH, Sword_ruby.WIDTH, Sword_ruby.HEIGHT, new Point(1,1));
	public static Content ITEM_SWORD_EMERALD = new Content(Sword_emerald.NAME, Sword_emerald.PATH, Sword_emerald.WIDTH, Sword_emerald.HEIGHT, new Point(1,1));
	public static Content ITEM_ROBERED = new Content(Robe_red.NAME, Robe_red.PATH, Robe_red.WIDTH, Robe_red.HEIGHT, new Point(1,1));
	public static Content ITEM_ROBEGREEN = new Content(Robe_green.NAME, Robe_green.PATH, Robe_green.WIDTH, Robe_green.HEIGHT, new Point(1,1));
	public static Content ITEM_ROBEBLUE = new Content(Robe_blue.NAME, Robe_blue.PATH, Robe_blue.WIDTH, Robe_blue.HEIGHT, new Point(1,1));
	public static Content ITEM_ORNATEDCHEST = new Content(Ornated_chest.NAME, Ornated_chest.PATH, Ornated_chest.WIDTH, Ornated_chest.HEIGHT, new Point(1,1));
	public static Content ITEM_RUBY = new Content(Ruby.NAME, Ruby.PATH, Ruby.WIDTH, Ruby.HEIGHT, new Point(1,1));
	public static Content ITEM_BOW = new Content(Bow.NAME, Bow.PATH, Bow.WIDTH, Bow.HEIGHT, new Point(1,1));
	public static Content ITEM_EMERALD = new Content(Emerald.NAME, Emerald.PATH, Emerald.WIDTH, Emerald.HEIGHT, new Point(1,1));
	public static Content ITEM_SHAMROCK = new Content(Shamrock_item.NAME, Shamrock_item.PATH, Shamrock_item.WIDTH, Shamrock_item.HEIGHT, new Point(1,1));
	public static Content ITEM_CHEST = new Content(Chest_item.NAME, Chest_item.PATH, Chest_item.WIDTH, Chest_item.HEIGHT, new Point(1,1));
	public static Content ITEM_PICKAXE_WOOD = new Content(Pickaxe_wood.NAME, Pickaxe_wood.PATH, Pickaxe_wood.WIDTH, Pickaxe_wood.HEIGHT, new Point(1,1));
	public static Content ITEM_PICKAXE_STONE = new Content(Pickaxe_stone.NAME, Pickaxe_stone.PATH, Pickaxe_stone.WIDTH, Pickaxe_stone.HEIGHT, new Point(1,1));
	public static Content ITEM_PICKAXE_DIAMOND = new Content(Pickaxe_diamond.NAME, Pickaxe_diamond.PATH, Pickaxe_diamond.WIDTH, Pickaxe_diamond.HEIGHT, new Point(1,1));
	public static Content ITEM_PICKAXE_BRONZE = new Content(Pickaxe_bronze.NAME, Pickaxe_bronze.PATH, Pickaxe_bronze.WIDTH, Pickaxe_bronze.HEIGHT, new Point(1,1));
	public static Content ITEM_PICKAXE_IRON = new Content(Pickaxe_iron.NAME, Pickaxe_iron.PATH, Pickaxe_iron.WIDTH, Pickaxe_iron.HEIGHT, new Point(1,1));
	public static Content ITEM_SHOVEL_WOOD = new Content(Shovel_wood.NAME, Shovel_wood.PATH, Shovel_wood.WIDTH, Shovel_wood.HEIGHT, new Point(1,1));
	public static Content ITEM_SHOVEL_STONE = new Content(Shovel_stone.NAME, Shovel_stone.PATH, Shovel_stone.WIDTH, Shovel_stone.HEIGHT, new Point(1,1));
	public static Content ITEM_SHOVEL_DIAMOND = new Content(Shovel_diamond.NAME, Shovel_diamond.PATH, Shovel_diamond.WIDTH, Shovel_diamond.HEIGHT, new Point(1,1));
	public static Content ITEM_SHOVEL_BRONZE = new Content(Shovel_bronze.NAME, Shovel_bronze.PATH, Shovel_bronze.WIDTH, Shovel_bronze.HEIGHT, new Point(1,1));
	public static Content ITEM_SHOVEL_IRON = new Content(Shovel_iron.NAME, Shovel_iron.PATH, Shovel_iron.WIDTH, Shovel_iron.HEIGHT, new Point(1,1));
	public static Content ITEM_AXE_WOOD = new Content(Axe_wood.NAME, Axe_wood.PATH, Axe_wood.WIDTH, Axe_wood.HEIGHT, new Point(1,1));
	public static Content ITEM_AXE_STONE = new Content(Axe_stone.NAME, Axe_stone.PATH, Axe_stone.WIDTH, Axe_stone.HEIGHT, new Point(1,1));
	public static Content ITEM_AXE_DIAMOND = new Content(Axe_diamond.NAME, Axe_diamond.PATH, Axe_diamond.WIDTH, Axe_diamond.HEIGHT, new Point(1,1));
	public static Content ITEM_AXE_BRONZE = new Content(Axe_bronze.NAME, Axe_bronze.PATH, Axe_bronze.WIDTH, Axe_bronze.HEIGHT, new Point(1,1));
	public static Content ITEM_AXE_IRON = new Content(Axe_iron.NAME, Axe_iron.PATH, Axe_iron.WIDTH, Axe_iron.HEIGHT, new Point(1,1));
	public static Content ITEM_BEDGREENITEM = new Content(Bedgreen_item.NAME, Bedgreen_item.PATH, Bedgreen_item.WIDTH, Bedgreen_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BEDREDITEM = new Content(Bedred_item.NAME, Bedred_item.PATH, Bedred_item.WIDTH, Bedred_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BEDGOLDITEM = new Content(Bedgold_item.NAME, Bedgold_item.PATH, Bedgold_item.WIDTH, Bedgold_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BEDBLUEITEM = new Content(Bedblue_item.NAME, Bedblue_item.PATH, Bedblue_item.WIDTH, Bedblue_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BEDPURPLEITEM = new Content(Bedpurple_item.NAME, Bedpurple_item.PATH, Bedpurple_item.WIDTH, Bedpurple_item.HEIGHT, new Point(1,1));
	public static Content ITEM_LEATHER = new Content(Leather.NAME, Leather.PATH, Leather.WIDTH, Leather.HEIGHT, new Point(1,1));
	public static Content ITEM_BOOKSHELFITEM = new Content(Bookshelf_item.NAME, Bookshelf_item.PATH, Bookshelf_item.WIDTH, Bookshelf_item.HEIGHT, new Point(1,1));
	public static Content ITEM_TABLEITEM = new Content(Table_item.NAME, Table_item.PATH, Table_item.WIDTH, Table_item.HEIGHT, new Point(1,1));
	public static Content ITEM_CHAIRITEM = new Content(Chair_item.NAME, Chair_item.PATH, Chair_item.WIDTH, Chair_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BOTTLE = new Content(Bottle.NAME, Bottle.PATH, Bottle.WIDTH, Bottle.HEIGHT, new Point(1,1));
	public static Content ITEM_BRICK = new Content(Brick.NAME, Brick.PATH, Brick.WIDTH, Brick.HEIGHT, new Point(1,1));
	public static Content ITEM_DIRT = new Content(Dirt_item.NAME, Dirt_item.PATH, Dirt_item.WIDTH, Dirt_item.HEIGHT, new Point(1,1));
	public static Content ITEM_SAPLINGITEM = new Content(Sapling_item.NAME, Sapling_item.PATH, Sapling_item.WIDTH, Sapling_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BUCKET = new Content(Bucket.NAME, Bucket.PATH, Bucket.WIDTH, Bucket.HEIGHT, new Point(1,1));
	public static Content ITEM_BUCKETWATER = new Content(Bucket_water.NAME, Bucket_water.PATH, Bucket_water.WIDTH, Bucket_water.HEIGHT, new Point(1,1));
	public static Content ITEM_BUCKETLAVA = new Content(Bucket_lava.NAME, Bucket_lava.PATH, Bucket_lava.WIDTH, Bucket_lava.HEIGHT, new Point(1,1));
	public static Content ITEM_BUCKETMILK = new Content(Bucket_milk.NAME, Bucket_milk.PATH, Bucket_milk.WIDTH, Bucket_milk.HEIGHT, new Point(1,1));
	public static Content ITEM_HOE_IRON = new Content(Hoe_iron.NAME, Hoe_iron.PATH, Hoe_iron.WIDTH, Hoe_iron.HEIGHT, new Point(1,1));
	public static Content ITEM_POTATOE = new Content(Potatoe.NAME, Potatoe.PATH, Potatoe.WIDTH, Potatoe.HEIGHT, new Point(1,1));
	public static Content ITEM_COMPASS = new Content(Compass.NAME, Compass.PATH, Compass.WIDTH, Compass.HEIGHT, new Point(1,1));
	public static Content ITEM_BOAT = new Content(Boat.NAME, Boat.PATH, Boat.WIDTH, Boat.HEIGHT, new Point(1,1));
	public static Content ITEM_FISHINGROD = new Content(Fishingrod.NAME, Fishingrod.PATH, Fishingrod.WIDTH, Fishingrod.HEIGHT, new Point(1,1));
	public static Content ITEM_STEM = new Content(Stem.NAME, Stem.PATH, Stem.WIDTH, Stem.HEIGHT, new Point(1,1));
	public static Content ITEM_PAPER = new Content(Paper.NAME, Paper.PATH, Paper.WIDTH, Paper.HEIGHT, new Point(1,1));
	public static Content ITEM_BOOK = new Content(Book.NAME, Book.PATH, Book.WIDTH, Book.HEIGHT, new Point(1,1));
	public static Content ITEM_CAKE = new Content(Cake.NAME, Cake.PATH, Cake.WIDTH, Cake.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOL= new Content(Wool.NAME, Wool.PATH, Wool.WIDTH, Wool.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOL_GREEN = new Content(Wool_green.NAME, Wool_green.PATH, Wool_green.WIDTH, Wool_green.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOL_BLUE = new Content(Wool_blue.NAME, Wool_blue.PATH, Wool_blue.WIDTH, Wool_blue.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOL_GOLD = new Content(Wool_gold.NAME, Wool_gold.PATH, Wool_gold.WIDTH, Wool_gold.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOL_RED = new Content(Wool_red.NAME, Wool_red.PATH, Wool_red.WIDTH, Wool_red.HEIGHT, new Point(1,1));
	public static Content ITEM_WOOL_PURPLE = new Content(Wool_purple.NAME, Wool_purple.PATH, Wool_purple.WIDTH, Wool_purple.HEIGHT, new Point(1,1));
	public static Content ITEM_WHEAT = new Content(Wheat.NAME, Wheat.PATH, Wheat.WIDTH, Wheat.HEIGHT, new Point(1,1));
	public static Content ITEM_EGG = new Content(Egg.NAME, Egg.PATH, Egg.WIDTH, Egg.HEIGHT, new Point(1,1));
	public static Content ITEM_CARROT = new Content(Carrot.NAME, Carrot.PATH, Carrot.WIDTH, Carrot.HEIGHT, new Point(1,1));
	public static Content ITEM_SEEDS = new Content(Seeds.NAME, Seeds.PATH, Seeds.WIDTH, Seeds.HEIGHT, new Point(1,1));
	public static Content ITEM_FEATHER = new Content(Feather.NAME, Feather.PATH, Feather.WIDTH, Feather.HEIGHT, new Point(1,1));
	public static Content ITEM_GLASS = new Content(Glass.NAME, Glass.PATH, Glass.WIDTH, Glass.HEIGHT, new Point(1,1));
	public static Content ITEM_MELON_SLICE = new Content(Melon_slice.NAME, Melon_slice.PATH, Melon_slice.WIDTH, Melon_slice.HEIGHT, new Point(1,1));
	public static Content ITEM_MELON = new Content(Melon.NAME, Melon.PATH, Melon.WIDTH, Melon.HEIGHT, new Point(1,1));
	public static Content ITEM_MELON_SEEDS = new Content(Melon_seeds.NAME, Melon_seeds.PATH, Melon_seeds.WIDTH, Melon_seeds.HEIGHT, new Point(1,1));
	public static Content ITEM_FISH = new Content(Fish.NAME, Fish.PATH, Fish.WIDTH, Fish.HEIGHT, new Point(1,1));
	public static Content ITEM_GATE = new Content(Gate_item.NAME, Gate_item.PATH, Gate_item.WIDTH, Gate_item.HEIGHT, new Point(1,1));
	public static Content ITEM_FENCE = new Content(Fence_item.NAME, Fence_item.PATH, Fence_item.WIDTH, Fence_item.HEIGHT, new Point(1,1));
	public static Content ITEM_SUGAR = new Content(Sugar.NAME, Sugar.PATH, Sugar.WIDTH, Sugar.HEIGHT, new Point(1,1));
	public static Content ITEM_PUMPKIN = new Content(Pumpkin.NAME, Pumpkin.PATH, Pumpkin.WIDTH, Pumpkin.HEIGHT, new Point(1,1));
	public static Content ITEM_PUMPKIN_LANTERN = new Content(Pumpkin_lantern_item.NAME, Pumpkin_lantern_item.PATH, Pumpkin_lantern_item.WIDTH, Pumpkin_lantern_item.HEIGHT, new Point(1,1));
	public static Content ITEM_FISH_BAKED = new Content(Fish_baked.NAME, Fish_baked.PATH, Fish_baked.WIDTH, Fish_baked.HEIGHT, new Point(1,1));
	public static Content ITEM_MEAT_GRILLED = new Content(Meat_grilled.NAME, Meat_grilled.PATH, Meat_grilled.WIDTH, Meat_grilled.HEIGHT, new Point(1,1));
	public static Content ITEM_POTATOE_BAKED = new Content(Potatoe_baked.NAME, Potatoe_baked.PATH, Potatoe_baked.WIDTH, Potatoe_baked.HEIGHT, new Point(1,1));
	public static Content ITEM_BREAD = new Content(Bread.NAME, Bread.PATH, Bread.WIDTH, Bread.HEIGHT, new Point(1,1));
	public static Content ITEM_APPLE = new Content(Apple.NAME, Apple.PATH, Apple.WIDTH, Apple.HEIGHT, new Point(1,1));
	public static Content ITEM_PUMPKIN_SEEDS = new Content(Pumpkin_seeds.NAME, Pumpkin_seeds.PATH, Pumpkin_seeds.WIDTH, Pumpkin_seeds.HEIGHT, new Point(1,1));
	public static Content ITEM_TENTITEM = new Content(Tent_item.NAME, Tent_item.PATH, Tent_item.WIDTH, Tent_item.HEIGHT, new Point(1,1));
	public static Content ITEM_PUMPKIN_PIE = new Content(Pumpkin_pie.NAME, Pumpkin_pie.PATH, Pumpkin_pie.WIDTH, Pumpkin_pie.HEIGHT, new Point(1,1));
	public static Content ITEM_FUTURISTIC_OVEN = new Content(Futuristic_oven_item.NAME, Futuristic_oven_item.PATH, Futuristic_oven_item.WIDTH, Futuristic_oven_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BOOKSHELF_SPACE = new Content(Bookshelf_space_item.NAME, Bookshelf_space_item.PATH, Bookshelf_space_item.WIDTH, Bookshelf_space_item.HEIGHT, new Point(1,1));
	public static Content ITEM_CHAIR_SPACE = new Content(Chair_space_item.NAME, Chair_space_item.PATH, Chair_space_item.WIDTH, Chair_space_item.HEIGHT, new Point(1,1));
	public static Content ITEM_CHEST_SPACE = new Content(Chest_space_item.NAME, Chest_space_item.PATH, Chest_space_item.WIDTH, Chest_space_item.HEIGHT, new Point(1,1));
	public static Content ITEM_TABLE_SPACE = new Content(Table_space_item.NAME, Table_space_item.PATH, Table_space_item.WIDTH, Table_space_item.HEIGHT, new Point(1,1));
	public static Content ITEM_BEDSPACE = new Content(Bedspace_item.NAME, Bedspace_item.PATH, Bedspace_item.WIDTH, Bedspace_item.HEIGHT, new Point(1,1));
	
	public static Content NPC_SHEEP = new Content(Sheep.NAME, Sheep.PATH, Sheep.WIDTH, Sheep.HEIGHT, new Point(3,4));
	public static Content NPC_COW = new Content(Cow.NAME, Cow.PATH, Cow.WIDTH, Cow.HEIGHT, new Point(3,4));
	public static Content NPC_MERCHANT = new Content(Merchant.NAME, Merchant.PATH, Merchant.WIDTH, Merchant.HEIGHT, new Point(3,4));
	public static Content NPC_CHICKEN = new Content(Chicken.NAME, Chicken.PATH, Chicken.WIDTH, Chicken.HEIGHT, new Point(3,4));
	
	
	
	public static Content ANIMATION_DAMAGE10 = new Content("Damage", "Resources/UndergroundTile/damage10.png", 32, 32, new Point(10,1));
	
	public Content(String name, String path, int width, int height, Point point) {
		this.name = name;
		this.path = path;
		this.width = width;
		this.height = height;
		this.load(point);
		this.animation = new Animation(10, point.x);
	}
	
	private void load(Point point) {
		BufferedImage completeImage;
		try {
			//completeImage = ImageIO.read(new File(path));
			//URL url = getClass().getResource(path);
			path = path.substring(9);
			completeImage = ImageIO.read(Content.class.getResourceAsStream(path));
			image = new BufferedImage[point.y][point.x];
			
			for (int x=0; x<point.x; x++)
				for (int y=0; y<point.y; y++)
					image[y][x] = completeImage.getSubimage(x * width, y * height, width, height);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void update() {
		animation.update();
	}
	
	public BufferedImage getImage(int y, int x) { return image[y][x]; }
	public BufferedImage getImage() { return image[0][0]; }
    
	public BufferedImage getrotatedImage(BufferedImage image, double degrees) {
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(degrees), image.getWidth() / 2, image.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
		return image;
	}
	public String getName() { return this.name; }
	
}
