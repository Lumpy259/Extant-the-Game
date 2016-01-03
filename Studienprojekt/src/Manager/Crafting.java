package Manager;

import java.util.ArrayList;

import Entity.Item.*;
import GameState.PlayState;

public class Crafting {
	public static ArrayList<Crafting> Recipes = new ArrayList<>();
	public static Crafting Sword_wood = new Crafting(new InventoryItem(new Sword_Wood(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Sword_wood1 = new Crafting(new InventoryItem(new Sword_Wood(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Sword_wood2 = new Crafting(new InventoryItem(new Sword_Wood(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Sword_iron = new Crafting(new InventoryItem(new Sword_Iron(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Sword_iron1 = new Crafting(new InventoryItem(new Sword_Iron(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Sword_iron2 = new Crafting(new InventoryItem(new Sword_Iron(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Sword_ruby = new Crafting(new InventoryItem(new Sword_ruby(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Ruby(0,0),1));
	public static Crafting Sword_ruby1 = new Crafting(new InventoryItem(new Sword_ruby(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Ruby(0,0),1));
	public static Crafting Sword_ruby2 = new Crafting(new InventoryItem(new Sword_ruby(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Ruby(0,0),1));
	public static Crafting Sword_gold = new Crafting(new InventoryItem(new Sword_gold(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Gold(0,0),1));
	public static Crafting Sword_gold1 = new Crafting(new InventoryItem(new Sword_gold(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Gold(0,0),1));
	public static Crafting Sword_gold2 = new Crafting(new InventoryItem(new Sword_gold(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Gold(0,0),1));
	public static Crafting Sword_emerald = new Crafting(new InventoryItem(new Sword_emerald(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Emerald(0,0),1));
	public static Crafting Sword_emerald1 = new Crafting(new InventoryItem(new Sword_emerald(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Emerald(0,0),1));
	public static Crafting Sword_emerald2 = new Crafting(new InventoryItem(new Sword_emerald(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Emerald(0,0),1));
	public static Crafting Sword_bronze = new Crafting(new InventoryItem(new Sword_bronze(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Sword_bronze1 = new Crafting(new InventoryItem(new Sword_bronze(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Sword_bronze2 = new Crafting(new InventoryItem(new Sword_bronze(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Sword_stone = new Crafting(new InventoryItem(new Sword_stone(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Sword_stone1 = new Crafting(new InventoryItem(new Sword_stone(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Sword_stone2 = new Crafting(new InventoryItem(new Sword_stone(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Sword_diamond = new Crafting(new InventoryItem(new Sword_diamond(0,0), 1), "#1# #1# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Sword_diamond1 = new Crafting(new InventoryItem(new Sword_diamond(0,0), 1), "##1 ##1 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Sword_diamond2 = new Crafting(new InventoryItem(new Sword_diamond(0,0), 1), "1## 1## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Helmet_leather = new Crafting(new InventoryItem(new Helmet_leather(0,0), 1), "00# 00# ###", new InventoryItem(new Leather(0,0),1));
	public static Crafting Helmet_leather1 = new Crafting(new InventoryItem(new Helmet_leather(0,0), 1), "#00 #00 ###", new InventoryItem(new Leather(0,0),1));
	public static Crafting Helmet_leather2 = new Crafting(new InventoryItem(new Helmet_leather(0,0), 1), "### 00# 00#", new InventoryItem(new Leather(0,0),1));
	public static Crafting Helmet_leather3 = new Crafting(new InventoryItem(new Helmet_leather(0,0), 1), "### #00 #00", new InventoryItem(new Leather(0,0),1));
	public static Crafting Chest_leather = new Crafting(new InventoryItem(new Chest_leather(0,0), 1), "0#0 000 000", new InventoryItem(new Leather(0,0),1));
	public static Crafting Leggins_leather = new Crafting(new InventoryItem(new Leggins_leather(0,0), 1), "000 0#0 0#0", new InventoryItem(new Leather(0,0),1));
	public static Crafting Boots_leather = new Crafting(new InventoryItem(new Boots_leather(0,0), 1), "### 0#0 0#0", new InventoryItem(new Leather(0,0),1));
	public static Crafting Boots_leather1 = new Crafting(new InventoryItem(new Boots_leather(0,0), 1), "0#0 0#0 ###", new InventoryItem(new Leather(0,0),1));
	public static Crafting Ornated_chest = new Crafting(new InventoryItem(new Ornated_chest(0,0), 1), "0#0 010 020", new InventoryItem(new Gold(0,0),1), new InventoryItem(new Ruby(0,0),1), new InventoryItem(new Emerald(0,0),1));
	public static Crafting Robe_red = new Crafting(new InventoryItem(new Robe_red(0,0), 1), "0#0 000 000", new InventoryItem(new Wool_red(0,0),1));
	public static Crafting Robe_blue = new Crafting(new InventoryItem(new Robe_blue(0,0), 1), "0#0 000 000", new InventoryItem(new Wool_blue(0,0),1));
	public static Crafting Robe_green = new Crafting(new InventoryItem(new Robe_green(0,0), 1), "0#0 000 000", new InventoryItem(new Wool_green(0,0),1));
	public static Crafting Wool_green = new Crafting(new InventoryItem(new Wool_green(0,0), 1), "### ### 101", new InventoryItem(new Wool(0,0),1),new InventoryItem(new Slime_green(0,0),1));
	public static Crafting Wool_green1 = new Crafting(new InventoryItem(new Wool_green(0,0), 1), "### 101 ###", new InventoryItem(new Wool(0,0),1),new InventoryItem(new Slime_green(0,0),1));
	public static Crafting Wool_green2 = new Crafting(new InventoryItem(new Wool_green(0,0), 1), "101 ### ###", new InventoryItem(new Wool(0,0),1),new InventoryItem(new Slime_green(0,0),1));
	public static Crafting Wool_blue = new Crafting(new InventoryItem(new Wool_blue(0,0), 1), "### ### 101", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_blue(0,0),1));
	public static Crafting Wool_blue1 = new Crafting(new InventoryItem(new Wool_blue(0,0), 1), "### 101 ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_blue(0,0),1));
	public static Crafting Wool_blue2 = new Crafting(new InventoryItem(new Wool_blue(0,0), 1), "101 ### ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_blue(0,0),1));
	public static Crafting Wool_gold = new Crafting(new InventoryItem(new Wool_gold(0,0), 1), "### ### 101", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_gold(0,0),1));
	public static Crafting Wool_gold1 = new Crafting(new InventoryItem(new Wool_gold(0,0), 1), "### 101 ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_gold(0,0),1));
	public static Crafting Wool_gold2 = new Crafting(new InventoryItem(new Wool_gold(0,0), 1), "101 ### ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_gold(0,0),1));
	public static Crafting Wool_purple = new Crafting(new InventoryItem(new Wool_purple(0,0), 1), "### ### 101", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_purple(0,0),1));
	public static Crafting Wool_purple1 = new Crafting(new InventoryItem(new Wool_purple(0,0), 1), "### 101 ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_purple(0,0),1));
	public static Crafting Wool_purple2 = new Crafting(new InventoryItem(new Wool_purple(0,0), 1), "101 ### ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_purple(0,0),1));
	public static Crafting Wool_red = new Crafting(new InventoryItem(new Wool_red(0,0), 2), "### ### 010", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_red(0,0),1));
	public static Crafting Wool_red1 = new Crafting(new InventoryItem(new Wool_red(0,0), 2), "### 010 ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_red(0,0),1));
	public static Crafting Wool_red2 = new Crafting(new InventoryItem(new Wool_red(0,0), 2), "010 ### ###", new InventoryItem(new Wool(0,0),1), new InventoryItem(new Slime_red(0,0),1));
	public static Crafting Chair = new Crafting(new InventoryItem(new Chair_item(0,0), 1), "### #00 #11", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Chair1 = new Crafting(new InventoryItem(new Chair_item(0,0), 1), "### 00# 11#", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Chair2 = new Crafting(new InventoryItem(new Chair_item(0,0), 1), "00# 11# ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Chair3 = new Crafting(new InventoryItem(new Chair_item(0,0), 1), "#00 #11 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Chest_item = new Crafting(new InventoryItem(new Chest_item(0,0), 1), "### 000 000", new InventoryItem(new Wood(0,0),1));
	public static Crafting Chest_item1 = new Crafting(new InventoryItem(new Chest_item(0,0), 1), "000 000 ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Table = new Crafting(new InventoryItem(new Table_item(0,0), 1), "000 1#1 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Table1 = new Crafting(new InventoryItem(new Table_item(0,0), 1), "### 000 1#1", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Ladder_item = new Crafting(new InventoryItem(new Ladder_item(0,0), 1), "0#0 000 0#0", new InventoryItem(new Stick(0,0),1));
	public static Crafting Oven = new Crafting(new InventoryItem(new Oven_item(0,0), 1), "000 0#0 000", new InventoryItem(new Stone_item(0,0),1));
	public static Crafting House = new Crafting(new InventoryItem(new House_item(0,0), 1), "222 111 000", new InventoryItem(new Stone_item(0,0),64), new InventoryItem(new Wood(0,0),64), new InventoryItem(new Brick(0,0),64));
	public static Crafting Bedgreen_item = new Crafting(new InventoryItem(new Bedgreen_item(0,0), 1), "### 111 000", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_green(0,0),1));
	public static Crafting Bedgreen_item1 = new Crafting(new InventoryItem(new Bedgreen_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_green(0,0),1));
	public static Crafting Bedred_item = new Crafting(new InventoryItem(new Bedred_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_red(0,0),1));
	public static Crafting Bedred_item1 = new Crafting(new InventoryItem(new Bedred_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_red(0,0),1));
	public static Crafting Bedblue_item = new Crafting(new InventoryItem(new Bedblue_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_blue(0,0),1));
	public static Crafting Bedblue_item1 = new Crafting(new InventoryItem(new Bedblue_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_blue(0,0),1));
	public static Crafting Bedgold_item = new Crafting(new InventoryItem(new Bedgold_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_gold(0,0),1));
	public static Crafting Bedgold_item1 = new Crafting(new InventoryItem(new Bedgold_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_gold(0,0),1));
	public static Crafting Bedpurple_item = new Crafting(new InventoryItem(new Bedpurple_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_purple(0,0),1));
	public static Crafting Bedpurple_item1 = new Crafting(new InventoryItem(new Bedpurple_item(0,0), 1), "111 000 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Wool_purple(0,0),1));
	public static Crafting Bookshelf_item = new Crafting(new InventoryItem(new Bookshelf_item(0,0), 1), "000 111 000", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Book(0,0),1));
	public static Crafting Book = new Crafting(new InventoryItem(new Book(0,0), 1), "### 11# 01#", new InventoryItem(new Leather(0,0),1), new InventoryItem(new Paper(0,0),1));
	public static Crafting Book1 = new Crafting(new InventoryItem(new Book(0,0), 1), "#11 #01 ###", new InventoryItem(new Leather(0,0),1), new InventoryItem(new Paper(0,0),1));
	public static Crafting Book2 = new Crafting(new InventoryItem(new Book(0,0), 1), "11# 01# ###", new InventoryItem(new Leather(0,0),1), new InventoryItem(new Paper(0,0),1));
	public static Crafting Book3 = new Crafting(new InventoryItem(new Book(0,0), 1), "### #11 #01", new InventoryItem(new Leather(0,0),1), new InventoryItem(new Paper(0,0),1));
	public static Crafting Fishingrod = new Crafting(new InventoryItem(new Fishingrod(0,0), 1), "##1 #10 1#0", new InventoryItem(new String_item(0,0),1), new InventoryItem(new Stick(0,0),1));		
	public static Crafting Axe_wood = new Crafting(new InventoryItem(new Axe_wood(0,0), 1), "11# 10# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Axe_wood1 = new Crafting(new InventoryItem(new Axe_wood(0,0), 1), "#11 #10 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Axe_stone = new Crafting(new InventoryItem(new Axe_stone(0,0), 1), "11# 10# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Axe_stone1 = new Crafting(new InventoryItem(new Axe_wood(0,0), 1), "#11 #10 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Axe_bronze = new Crafting(new InventoryItem(new Axe_bronze(0,0), 1), "11# 10# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Axe_bronze1 = new Crafting(new InventoryItem(new Axe_wood(0,0), 1), "#11 #10 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Axe_diamond = new Crafting(new InventoryItem(new Axe_diamond(0,0), 1), "11# 10# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Axe_diamond1 = new Crafting(new InventoryItem(new Axe_wood(0,0), 1), "#11 #10 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Axe_iron = new Crafting(new InventoryItem(new Axe_iron(0,0), 1), "11# 10# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Axe_iron1 = new Crafting(new InventoryItem(new Axe_wood(0,0), 1), "#11 #10 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Shovel_wood = new Crafting(new InventoryItem(new Shovel_wood(0,0), 1), "#1# #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Shovel_wood1 = new Crafting(new InventoryItem(new Shovel_wood(0,0), 1), "##1 ##0 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Shovel_wood2 = new Crafting(new InventoryItem(new Shovel_wood(0,0), 1), "1## 0## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Shovel_stone = new Crafting(new InventoryItem(new Shovel_stone(0,0), 1), "#1# #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Shovel_stone1 = new Crafting(new InventoryItem(new Shovel_stone(0,0), 1), "##1 ##0 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Shovel_stone2 = new Crafting(new InventoryItem(new Shovel_stone(0,0), 1), "1## 0## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Shovel_bronze = new Crafting(new InventoryItem(new Shovel_bronze(0,0), 1), "#1# #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Shovel_bronze1 = new Crafting(new InventoryItem(new Shovel_bronze(0,0), 1), "##1 ##0 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Shovel_bronze2 = new Crafting(new InventoryItem(new Shovel_bronze(0,0), 1), "1## 0## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Shovel_diamond = new Crafting(new InventoryItem(new Shovel_diamond(0,0), 1), "#1# #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Shovel_diamond1 = new Crafting(new InventoryItem(new Shovel_diamond(0,0), 1), "##1 ##0 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Shovel_diamond2 = new Crafting(new InventoryItem(new Shovel_diamond(0,0), 1), "1## 0## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Shovel_iron = new Crafting(new InventoryItem(new Shovel_iron(0,0), 1), "#1# #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Shovel_iron1 = new Crafting(new InventoryItem(new Shovel_iron(0,0), 1), "##1 ##0 ##0", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Shovel_iron2 = new Crafting(new InventoryItem(new Shovel_iron(0,0), 1), "1## 0## 0##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Pickaxe_wood = new Crafting(new InventoryItem(new Pickaxe_wood(0,0), 1), "111 #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Pickaxe_stone = new Crafting(new InventoryItem(new Pickaxe_stone(0,0), 1), "111 #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1));
	public static Crafting Pickaxe_bronze = new Crafting(new InventoryItem(new Pickaxe_bronze(0,0), 1), "111 #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Bronze(0,0),1));
	public static Crafting Pickaxe_diamond = new Crafting(new InventoryItem(new Pickaxe_diamond(0,0), 1), "111 #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Diamond(0,0),1));
	public static Crafting Pickaxe_iron = new Crafting(new InventoryItem(new Pickaxe_iron(0,0), 1), "111 #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Hoe_iron = new Crafting(new InventoryItem(new Hoe_iron(0,0), 1), "11# #0# #0#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Iron(0,0),1));
	public static Crafting Pistol = new Crafting(new InventoryItem(new Pistol(0,0), 1), "000 211 ##0", new InventoryItem(new Iron(0,0),1), new InventoryItem(new Diamond(0,0),1), new InventoryItem(new Ruby(0,0),1));
	public static Crafting Bow = new Crafting(new InventoryItem(new Bow(0,0), 1), "##1 #10 100", new InventoryItem(new Stick(0,0),1), new InventoryItem(new String_item(0,0),1));
	public static Crafting Bow2 = new Crafting(new InventoryItem(new Bow(0,0), 1), "001 01# 1##", new InventoryItem(new Stick(0,0),1), new InventoryItem(new String_item(0,0),1));
	public static Crafting Arrow = new Crafting(new InventoryItem(new Arrow(0,0), 1), "#1# #0# #2#", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Stone_item(0,0),1), new InventoryItem(new Feather(0,0),1));
	public static Crafting Ammonition = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "0## ### ###", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition1 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "#0# ### ###", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition2 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "##0 ### ###", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition3 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "### 0## ###", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition4 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "### #0# ###", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition5 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "### ##0 ###", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition6 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "### ### 0##", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition7 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "### ### #0#", new InventoryItem(new Iron(0,0),1));
	public static Crafting Ammonition8 = new Crafting(new InventoryItem(new Ammonition(0,0), 10), "### ### ##0", new InventoryItem(new Iron(0,0),1));
	public static Crafting Compass = new Crafting(new InventoryItem(new Compass(0,0), 1), "#0# 010 0#0", new InventoryItem(new Iron(0,0),1), new InventoryItem(new Ruby(0,0),1));
	public static Crafting Boat = new Crafting(new InventoryItem(new Boat(0,0), 1), "### 0#0 000", new InventoryItem(new Wood(0,0),1));
	public static Crafting Boat2 = new Crafting(new InventoryItem(new Boat(0,0), 1), "0#0 000 ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Bronze = new Crafting(new InventoryItem(new Bronze(0,0), 1), "00# 00# ###", new InventoryItem(new Bronze_chunk(0,0),1));
	public static Crafting Bronze1 = new Crafting(new InventoryItem(new Bronze(0,0), 1), "### 00# 00#", new InventoryItem(new Bronze_chunk(0,0),1));
	public static Crafting Bronze2 = new Crafting(new InventoryItem(new Bronze(0,0), 1), "### #00 #00", new InventoryItem(new Bronze_chunk(0,0),1));
	public static Crafting Bronze3 = new Crafting(new InventoryItem(new Bronze(0,0), 1), "#00 #00 ###", new InventoryItem(new Bronze_chunk(0,0),1));
	public static Crafting Stick = new Crafting(new InventoryItem(new Stick(0,0), 4), "0## ### ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick1 = new Crafting(new InventoryItem(new Stick(0,0), 4), "#0# ### ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick2 = new Crafting(new InventoryItem(new Stick(0,0), 4), "##0 ### ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick3 = new Crafting(new InventoryItem(new Stick(0,0), 4), "### 0## ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick4 = new Crafting(new InventoryItem(new Stick(0,0), 4), "### #0# ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick5 = new Crafting(new InventoryItem(new Stick(0,0), 4), "### ##0 ###", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick6 = new Crafting(new InventoryItem(new Stick(0,0), 4), "### ### 0##", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick7 = new Crafting(new InventoryItem(new Stick(0,0), 4), "### ### #0#", new InventoryItem(new Wood(0,0),1));
	public static Crafting Stick8 = new Crafting(new InventoryItem(new Stick(0,0), 4), "### ### ##0", new InventoryItem(new Wood(0,0),1));
	public static Crafting Paper = new Crafting(new InventoryItem(new Paper(0,0), 1), "### ### 000", new InventoryItem(new Stem(0,0),1));
	public static Crafting Paper1 = new Crafting(new InventoryItem(new Paper(0,0), 1), "### 000 ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Paper2 = new Crafting(new InventoryItem(new Paper(0,0), 1), "000 ### ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Bottle = new Crafting(new InventoryItem(new Bottle(0,0), 4), "0#0 #0# ###", new InventoryItem(new Glass(0,0),1));
	public static Crafting Bottle1 = new Crafting(new InventoryItem(new Bottle(0,0), 4), "### 0#0 #0#", new InventoryItem(new Glass(0,0),1));
	public static Crafting Bottle3 = new Crafting(new InventoryItem(new Bottle(0,0), 4), "### 0## ###", new InventoryItem(new Glass(0,0),1));
	public static Crafting Healthpot = new Crafting(new InventoryItem(new HealthPot(0,0), 4), "### 012 #3#", new InventoryItem(new Flower_item(0,0),1), new InventoryItem(new Shamrock_item(0,0),1), new InventoryItem(new Shroom_item(0,0),1), new InventoryItem(new Bottle(0,0),1));
	public static Crafting Healthpot2 = new Crafting(new InventoryItem(new HealthPot(0,0), 4), "012 #3# ###", new InventoryItem(new Flower_item(0,0),1), new InventoryItem(new Shamrock_item(0,0),1), new InventoryItem(new Shroom_item(0,0),1), new InventoryItem(new Bottle(0,0),1));
	public static Crafting Fence = new Crafting(new InventoryItem(new Fence_item(0,0), 2), "### 101 101", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Fence1 = new Crafting(new InventoryItem(new Fence_item(0,0), 2), "101 101 ###", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Gate = new Crafting(new InventoryItem(new Gate_item(0,0), 2), "### 101 101", new InventoryItem(new Stick(0,0),1), new InventoryItem(new Wood(0,0),1));
	public static Crafting Gate1 = new Crafting(new InventoryItem(new Gate_item(0,0), 2), "101 101 ###", new InventoryItem(new Wood(0,0),1), new InventoryItem(new Stick(0,0),1));
	public static Crafting Melonslice = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "### ### ##0", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice1 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "### ### #0#", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice2 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "### ### 0##", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice3 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "### ##0 ###", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice4 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "### #0# ###", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice5 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "### 0## ###", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice6 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "##0 ### ###", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice7 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "#0# ### ###", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonslice8 = new Crafting(new InventoryItem(new Melon_slice(0,0), 9), "0## ### ###", new InventoryItem(new Melon(0,0),1));
	public static Crafting Melonseeds = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "### ### ##0", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds1 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "### ### #0#", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds2 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "### ### 0##", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds3 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "### ##0 ###", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds4 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "### #0# ###", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds5 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "### 0## ###", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds6 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "##0 ### ###", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds7 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "#0# ### ###", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Melonseeds8 = new Crafting(new InventoryItem(new Melon_seeds(0,0), 1), "0## ### ###", new InventoryItem(new Melon_slice(0,0),1));
	public static Crafting Sugar = new Crafting(new InventoryItem(new Sugar(0,0), 3), "### ### ##0", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar1 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "### ### #0#", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar2 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "### ### 0##", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar3 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "### ##0 ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar4 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "### #0# ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar5 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "### 0## ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar6 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "##0 ### ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar7 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "#0# ### ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Sugar8 = new Crafting(new InventoryItem(new Sugar(0,0), 3), "0## ### ###", new InventoryItem(new Stem(0,0),1));
	public static Crafting Cake = new Crafting(new InventoryItem(new Cake(0,0), 6), "#0# 121 333", new InventoryItem(new Bucket_milk(0,0),1), new InventoryItem(new Sugar(0,0),1), new InventoryItem(new Egg(0,0),1), new InventoryItem(new Wheat(0,0),1));
	public static Crafting Pumpkinpie = new Crafting(new InventoryItem(new Pumpkin_pie(0,0), 6), "#0# 121 343", new InventoryItem(new Bucket_milk(0,0),1), new InventoryItem(new Sugar(0,0),1), new InventoryItem(new Egg(0,0),1), new InventoryItem(new Wheat(0,0),1) , new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "### ### ##0", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern1 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "### ### #0#", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern2 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "### ### 0##", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern3 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "### ##0 ###", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern4 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "### #0# ###", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern5 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "### 0## ###", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern6 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "##0 ### ###", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern7 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "#0# ### ###", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Pumpkinlantern8 = new Crafting(new InventoryItem(new Pumpkin_lantern_item(0,0), 1), "0## ### ###", new InventoryItem(new Pumpkin(0,0),1));
	public static Crafting Tent = new Crafting(new InventoryItem(new Tent_item(0,0), 1), "000 000 000", new InventoryItem(new Wool(0,0),2));
	
	public static int counter = 0;

	public InventoryItem result;
	public String recipe;
	public InventoryItem[] required;
	public boolean craftingBook;
	
	public Crafting(InventoryItem result, String recipe, InventoryItem... required) {
		this.result = result;
		this.recipe = recipe;
		this.required = required;
		this.craftingBook = false;
		Crafting.Recipes.add(this);
		Crafting.counter++;		
	}
		
	public static void shadowUpdate() {
		boolean rightRecipe = false;
		InventoryItem[] craftingSpace = PlayState.inventory.getCrafting();
		
		for (int j=0; j<Crafting.Recipes.size(); j++) {
			char[] itemPosition = Crafting.Recipes.get(j).recipe.replaceAll(" ", "").toCharArray();

			for (int i=0; i<9; i++) {				
				if (!(((int)itemPosition[i] - 48 == -13 && craftingSpace[i] == null) || (craftingSpace[i] != null && (int)itemPosition[i] - 48 != -13 && craftingSpace[i].getName().equals(Crafting.Recipes.get(j).required[(int)itemPosition[i] - 48].getName()) && craftingSpace[i].getCount() >= Crafting.Recipes.get(j).required[(int)itemPosition[i]-48].getCount())))
				{
					rightRecipe = false;
					break;
				}
				else
					rightRecipe = true;
			}
			if (rightRecipe == true) {		
				Crafting.Recipes.get(j).craftingBook = true;
				PlayState.inventory.inventory[5][9] = Crafting.Recipes.get(j).result.newInventoryItem(Crafting.Recipes.get(j).result.getCount());
				break;
			}
			if (rightRecipe == true)
				System.out.println(rightRecipe);
		}		
		if (rightRecipe == false)
			PlayState.inventory.inventory[5][9] = null;
	}
	
	public static void update() {
		boolean rightRecipe = false;
		InventoryItem[] craftingSpace = PlayState.inventory.getCrafting();
		if (PlayState.inventory.inventory[5][9] != null)
			return;
		for (int j=0; j<Crafting.Recipes.size(); j++) {
			char[] itemPosition = Crafting.Recipes.get(j).recipe.replaceAll(" ", "").toCharArray();

			for (int i=0; i<9; i++) {				
				if (!(((int)itemPosition[i] - 48 == -13 && craftingSpace[i] == null) || (craftingSpace[i] != null && (int)itemPosition[i] - 48 != -13 && craftingSpace[i].getName().equals(Crafting.Recipes.get(j).required[(int)itemPosition[i] - 48].getName()) && craftingSpace[i].getCount() >= Crafting.Recipes.get(j).required[(int)itemPosition[i]-48].getCount())))
				{
					rightRecipe = false;
					break;
				}
				else
					rightRecipe = true;
			}
			if (rightRecipe == true) {		
				if (PlayState.inventory.inventory[5][9] == null || PlayState.inventory.inventory[5][9].getName().equals(Crafting.Recipes.get(j).result.getName()))
				for (int i=0;i<9;i++)
					if ((int)itemPosition[i] - 48 != -13) {
						PlayState.inventory.inventory[-((i-i%3)/3-6)][i%3+5].setCount(-Crafting.Recipes.get(j).required[(int)itemPosition[i] - 48].getCount());
						
						if (PlayState.inventory.inventory[-((i-i%3)/3-6)][i%3+5].getCount() == 0)
							PlayState.inventory.inventory[-((i-i%3)/3-6)][i%3+5] = null;
					}
				if (PlayState.inventory.inventory[5][9] == null)
					PlayState.inventory.inventory[5][9] = Crafting.Recipes.get(j).result.newInventoryItem(Crafting.Recipes.get(j).result.getCount());
				else
					PlayState.inventory.inventory[5][9].setCount(Crafting.Recipes.get(j).result.getCount());
			}
		}
	}
	
	

}
