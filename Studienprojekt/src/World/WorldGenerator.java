package World;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import Entity.Environment.Bossdoor_outside;
import Entity.Environment.Door_outside;
import Entity.Environment.Mountaincave;
import Entity.Environment.Rock_yellow;
import GameState.LoadState;
import GameState.PlayState;
import Main.Game;
import Manager.Rand;
import World.Tile.Tile;

public class WorldGenerator {
	
	public static HashMap<Point, Integer> generateMap(int width, int height, int points) {
		 HashMap<Point, Integer> world = new HashMap<>();
		 HashMap<Point, Integer> checkPoints = new HashMap<>();
		 
		checkPoints.put(new Point(235,235), 1);
		checkPoints.put(new Point(265,235), 1);
		checkPoints.put(new Point(235,265), 1);
		checkPoints.put(new Point(265,265), 1);
		
		for (int i=0; i<points-4; i++) {
			int x = Rand.Random(width);
			int y = Rand.Random(height);
			int t = Rand.Random(5);
			if (x > 235 && y > 235 && x < 265 && y < 265 && t != 1)
				t = 1;
			checkPoints.put(new Point(x,y), t);

			//checkPoints.put(new Point(Rand.Random(width), Rand.Random(height)), Rand.Random(5));
		}
		for(int x=0; x<width; x++) {
			if (x%100 == 0)
				LoadState.generateLoadingScreen();
			for (int y=0;y<height; y++) {
				int minDist = Integer.MAX_VALUE;
				Point nearestPoint = null;
				for (Point p : checkPoints.keySet()) {
					int xDiff = p.x - x;
		            int yDiff = p.y - y;
		            int cDist = xDiff*xDiff + yDiff*yDiff;
		            
		            if (cDist < minDist) {
		            	nearestPoint = p;
		            	minDist = cDist;
	                }  
				}
				world.put(new Point(x,y), checkPoints.get(nearestPoint));
			}
		}
		
		// Rahmen oben
		for (int x=0; x<width; x++) {
			for (int y=0; y<10; y++) 
				world.put(new Point(x,y), Tile.GRASS2.id);
			
			world.put(new Point(x,10), Tile.MOUNTAIN32.id);
			world.put(new Point(x,11), Tile.MOUNTAIN42.id);
			
			if (world.get(new Point(x,12)) == Tile.GRASS.id || world.get(new Point(x,12)) == Tile.GRASS2.id)
				world.put(new Point(x,12), Tile.MOUNTAIN52_GRASS.id);
			else if (world.get(new Point(x,12)) == Tile.SAND.id)
				world.put(new Point(x,12), Tile.MOUNTAIN52_SAND.id);
			else if (world.get(new Point(x,12)) == Tile.STONE.id)
				world.put(new Point(x,12), Tile.MOUNTAIN52_STONE.id);
			else if (world.get(new Point(x,12)) == Tile.DIRT.id)
				world.put(new Point(x,12), Tile.MOUNTAIN52_DIRT.id);
			else if (world.get(new Point(x,12)) == Tile.WATER.id)
				world.put(new Point(x,12), Tile.MOUNTAIN52_WATER.id);
			
			
		}
		
		int counterX = 15;
		int counterY = 0;
		do {	//äußerste schleife
			counterX += Rand.Random(3,8);
			
			do {	//groß
				if (counterX >= 498) break;
				for (int i=0; i<=counterY; i++)
					world.put(new Point(counterX, 10+i), Tile.GRASS2.id);
				
				world.put(new Point(counterX, 11+counterY), Tile.MOUNTAIN31.id);
				world.put(new Point(counterX, 12+counterY), Tile.MOUNTAIN41.id);
				if (world.get(new Point(counterX,13+counterY)) == Tile.GRASS.id || world.get(new Point(counterX,13+counterY)) == Tile.GRASS2.id)
					world.put(new Point(counterX,13+counterY), Tile.MOUNTAIN51_GRASS.id);
				else if (world.get(new Point(counterX,13+counterY)) == Tile.SAND.id)
					world.put(new Point(counterX,13+counterY), Tile.MOUNTAIN51_SAND.id);
				else if (world.get(new Point(counterX,13+counterY)) == Tile.STONE.id)
					world.put(new Point(counterX,13+counterY), Tile.MOUNTAIN51_STONE.id);
				else if (world.get(new Point(counterX,13+counterY)) == Tile.DIRT.id)
					world.put(new Point(counterX,13+counterY), Tile.MOUNTAIN51_DIRT.id);
				else if (world.get(new Point(counterX,13+counterY)) == Tile.WATER.id)
					world.put(new Point(counterX,13+counterY), Tile.MOUNTAIN51_WATER.id);
				
				counterX++;
				counterY++;
				
				while ((counterX >= 20 && counterX <= 40) || Rand.Random(100) < 50) {	//gerade
					if (counterX >= 498) break;
					if (counterX >= 20 && counterX <= 40) PlayState.waterfallY = 11+counterY;
					if (counterX == 40) {
						for (int i=0; i<=PlayState.waterfallY-1; i++) {
							world.put(new Point(29, i), Tile.WATER.id);
							world.put(new Point(30, i), Tile.WATER.id);
							world.put(new Point(31, i), Tile.WATER.id);
							if (i+1<PlayState.waterfallY-1)
								for (int j=-4; j<=4; j++)
									world.put(new Point(30+j, i), Tile.WATER.id);
							if (i+3<PlayState.waterfallY-1)
								for (int j=-6; j<=6; j++)
									world.put(new Point(30+j, i), Tile.WATER.id);
						}
						for (int i=PlayState.waterfallY+2; i<=1+PlayState.waterfallY+2; i++) {
							if (i == PlayState.waterfallY+2) {
								world.put(new Point(29, i), Tile.WATER.id);
								world.put(new Point(30, i), Tile.WATER.id);
								world.put(new Point(31, i), Tile.WATER.id);
							}
							else
								world.put(new Point(30, i), Tile.WATER.id);
						}
					}
					
					for (int i=0; i<=counterY-1; i++)
						world.put(new Point(counterX, 10+i), Tile.GRASS2.id);
				
					world.put(new Point(counterX, 11+counterY-1), Tile.MOUNTAIN32.id);
					world.put(new Point(counterX, 12+counterY-1), Tile.MOUNTAIN42.id);
					if (world.get(new Point(counterX,13+counterY-1)) == Tile.GRASS.id || world.get(new Point(counterX,13+counterY-1)) == Tile.GRASS2.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_GRASS.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.SAND.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_SAND.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.STONE.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_STONE.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.DIRT.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_DIRT.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.WATER.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_WATER.id);

					counterX++;
				}
				
			} while(Rand.Random(100) < 75);
			
			while(counterY > 0) { // klein
				if (counterX >= 500) break;
				for (int i=0; i<=counterY; i++)
					world.put(new Point(counterX, 10+i), Tile.GRASS2.id);
				
				world.put(new Point(counterX, 11+counterY-1), Tile.MOUNTAIN33.id);
				world.put(new Point(counterX, 12+counterY-1), Tile.MOUNTAIN43.id);
				if (world.get(new Point(counterX,13+counterY-1)) == Tile.GRASS.id || world.get(new Point(counterX,13+counterY-1)) == Tile.GRASS2.id)
					world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN53_GRASS.id);
				else if (world.get(new Point(counterX,13+counterY-1)) == Tile.SAND.id)
					world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN53_SAND.id);
				else if (world.get(new Point(counterX,13+counterY-1)) == Tile.STONE.id)
					world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN53_STONE.id);
				else if (world.get(new Point(counterX,13+counterY-1)) == Tile.DIRT.id)
					world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN53_DIRT.id);
				else if (world.get(new Point(counterX,13+counterY-1)) == Tile.WATER.id)
					world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN53_WATER.id);	

				counterX++;
				counterY--;
				
				while ((counterX >= 20 && counterX <= 40) || Rand.Random(100) < 50) {	//gerade
					if (counterX >= 500) break;
					if (counterX >= 20 && counterX <= 40) PlayState.waterfallY = 11+counterY;
					if (counterX == 40) {
						for (int i=0; i<=PlayState.waterfallY-1; i++) {
							world.put(new Point(29, i), Tile.WATER.id);
							world.put(new Point(30, i), Tile.WATER.id);
							world.put(new Point(31, i), Tile.WATER.id);
							if (i+1<PlayState.waterfallY-1)
								for (int j=-4; j<=4; j++)
									world.put(new Point(30+j, i), Tile.WATER.id);
							if (i+3<PlayState.waterfallY-1)
								for (int j=-6; j<=6; j++)
									world.put(new Point(30+j, i), Tile.WATER.id);
						}
						for (int i=PlayState.waterfallY+2; i<=1+PlayState.waterfallY+2; i++) {
							if (i == PlayState.waterfallY+2) {
								world.put(new Point(29, i), Tile.WATER.id);
								world.put(new Point(30, i), Tile.WATER.id);
								world.put(new Point(31, i), Tile.WATER.id);
							}
							else
								world.put(new Point(30, i), Tile.WATER.id);
						}
					}
					
					for (int i=0; i<=counterY-1; i++)
						world.put(new Point(counterX, 10+i), Tile.GRASS2.id);
				
					world.put(new Point(counterX, 11+counterY-1), Tile.MOUNTAIN32.id);
					world.put(new Point(counterX, 12+counterY-1), Tile.MOUNTAIN42.id);
					if (world.get(new Point(counterX,13+counterY-1)) == Tile.GRASS.id || world.get(new Point(counterX,13+counterY-1)) == Tile.GRASS2.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_GRASS.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.SAND.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_SAND.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.STONE.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_STONE.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.DIRT.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_DIRT.id);
					else if (world.get(new Point(counterX,13+counterY-1)) == Tile.WATER.id)
						world.put(new Point(counterX,13+counterY-1), Tile.MOUNTAIN52_WATER.id);
					counterX++;
				}
				if (counterX >= 500) break;
			}
		} while (counterX < 480);
		
		//	linker Rand
		for (int y=0; y<height; y++) {
			for (int x=0; x<10; x++)
				world.put(new Point(x,y), Tile.GRASS2.id);
			world.put(new Point(10,y), Tile.MOUNTAIN23.id);
		}
		
		counterX = 0;
		counterY = 15;
		do {	//äußerste schleife
			counterY += Rand.Random(3,8);
			
			do {	//groß
				if (counterY >= 500) break;
				for (int i=0; i<=counterX; i++)
					world.put(new Point(10+i, counterY), Tile.GRASS2.id);
				
				if (world.get(new Point(11+counterX, counterY)) == Tile.GRASS.id || world.get(new Point(11+counterX, counterY)) == Tile.GRASS2.id)
					world.put(new Point(11+counterX, counterY), Tile.MOUNTAIN13_GRASS.id);
				else if (world.get(new Point(11+counterX, counterY)) == Tile.SAND.id)
					world.put(new Point(11+counterX, counterY), Tile.MOUNTAIN13_SAND.id);
				else if (world.get(new Point(11+counterX, counterY)) == Tile.STONE.id)
					world.put(new Point(11+counterX, counterY), Tile.MOUNTAIN13_STONE.id);
				else if (world.get(new Point(11+counterX, counterY)) == Tile.DIRT.id)
					world.put(new Point(11+counterX, counterY), Tile.MOUNTAIN13_DIRT.id);
				else if (world.get(new Point(11+counterX, counterY)) == Tile.WATER.id)
					world.put(new Point(11+counterX, counterY), Tile.MOUNTAIN13_WATER.id);
				counterX++;
				counterY++;
				
				while (Rand.Random(100) < 50) {	//gerade
					if (counterY >= 500) break;
					for (int i=0; i<=counterX-1; i++)
						world.put(new Point(10+i, counterY), Tile.GRASS2.id);
				
					world.put(new Point(11+counterX-1, counterY), Tile.MOUNTAIN23.id);
					
					counterY++;
				}
				
			} while(Rand.Random(100) < 75);
			
			while(counterX > 0) { // klein
				if (counterY >= 498) break;
				for (int i=0; i<=counterX; i++)
					world.put(new Point(10+i, counterY), Tile.GRASS2.id);
				
				
				world.put(new Point(11+counterX-1, counterY), Tile.MOUNTAIN33.id);
				world.put(new Point(11+counterX-1, counterY+1), Tile.MOUNTAIN43.id);

				if (world.get(new Point(11+counterX-1,counterY+2)) == Tile.GRASS.id || world.get(new Point(11+counterX-1,counterY+2)) == Tile.GRASS2.id)
					world.put(new Point(11+counterX-1,counterY+2), Tile.MOUNTAIN53_GRASS.id);
				else if (world.get(new Point(11+counterX-1,counterY+2)) == Tile.SAND.id)
					world.put(new Point(11+counterX-1,counterY+2), Tile.MOUNTAIN53_SAND.id);
				else if (world.get(new Point(11+counterX-1,counterY+2)) == Tile.STONE.id)
					world.put(new Point(11+counterX-1,counterY+2), Tile.MOUNTAIN53_STONE.id);
				else if (world.get(new Point(11+counterX-1,counterY+2)) == Tile.DIRT.id)
					world.put(new Point(11+counterX-1,counterY+2), Tile.MOUNTAIN53_DIRT.id);
				else if (world.get(new Point(11+counterX-1,counterY+2)) == Tile.WATER.id)
					world.put(new Point(11+counterX-1,counterY+2), Tile.MOUNTAIN53_WATER.id);
				
				
				
				counterX--;
				counterY++;
				
				while (Rand.Random(100) < 50) {	//gerade
					if (counterY >= 500) break;
					for (int i=0; i<=counterX-1; i++)
						world.put(new Point(10+i, counterY), Tile.GRASS2.id);
				
					world.put(new Point(11+counterX-1, counterY), Tile.MOUNTAIN23.id);
					counterY++;
				}
				if (counterY >= 500) break;
			}
		} while (counterY < 480);
		

		// Rahmen unten
		for (int x=0; x<width; x++) {
			for (int y=height; y>height-10; y--)
				world.put(new Point(x,y), Tile.GRASS2.id);
			
			if (world.get(new Point(x,height-10)) == Tile.GRASS.id || world.get(new Point(x,height-10)) == Tile.GRASS2.id)
				world.put(new Point(x,height-10), Tile.MOUNTAIN12_GRASS.id);
			else if (world.get(new Point(x,height-10)) == Tile.SAND.id)
				world.put(new Point(x,height-10), Tile.MOUNTAIN12_SAND.id);
			else if (world.get(new Point(x,height-10)) == Tile.STONE.id)
				world.put(new Point(x,height-10), Tile.MOUNTAIN12_STONE.id);
			else if (world.get(new Point(x,height-10)) == Tile.DIRT.id)
				world.put(new Point(x,height-10), Tile.MOUNTAIN12_DIRT.id);
			else if (world.get(new Point(x,height-10)) == Tile.WATER.id)
				world.put(new Point(x,height-10), Tile.MOUNTAIN12_WATER.id);
		}		
		
		counterX = 15;
		counterY = 0;
		do {	//äußerste schleife
			counterX += Rand.Random(3,8);
			
			do {	//groß
				if (counterX >= 498) break;
				for (int i=0; i<=counterY; i++)
					world.put(new Point(counterX, 500-(10+i)), Tile.GRASS2.id);
				
				if (world.get(new Point(counterX, 500-(11+counterY))) == Tile.GRASS.id || world.get(new Point(counterX, 500-(11+counterY))) == Tile.GRASS2.id)
					world.put(new Point(counterX, 500-(11+counterY)), Tile.MOUNTAIN11_GRASS.id);
				else if (world.get(new Point(counterX, 500-(11+counterY))) == Tile.SAND.id)
					world.put(new Point(counterX, 500-(11+counterY)), Tile.MOUNTAIN11_SAND.id);
				else if (world.get(new Point(counterX, 500-(11+counterY))) == Tile.STONE.id)
					world.put(new Point(counterX, 500-(11+counterY)), Tile.MOUNTAIN11_STONE.id);
				else if (world.get(new Point(counterX, 500-(11+counterY))) == Tile.DIRT.id)
					world.put(new Point(counterX, 500-(11+counterY)), Tile.MOUNTAIN11_DIRT.id);
				else if (world.get(new Point(counterX, 500-(11+counterY))) == Tile.WATER.id)
					world.put(new Point(counterX, 500-(11+counterY)), Tile.MOUNTAIN11_WATER.id);
				
				counterX++;
				counterY++;
				
				while (Rand.Random(100) < 50) {	//gerade
					if (counterX >= 498) break;
					for (int i=0; i<=counterY-1; i++)
						world.put(new Point(counterX, 500-(10+i)), Tile.GRASS2.id);
				
					if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.GRASS.id || world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.GRASS2.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_GRASS.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.SAND.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_SAND.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.STONE.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_STONE.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.DIRT.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_DIRT.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.WATER.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_WATER.id);
					counterX++;
				}
				
			} while(Rand.Random(100) < 75);
			
			while(counterY > 0) { // klein
				if (counterX >= 498) break;
				for (int i=0; i<=counterY; i++)
					world.put(new Point(counterX, 500-(9+i)), Tile.GRASS2.id);

				if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.GRASS.id || world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.GRASS2.id)
					world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN13_GRASS.id);
				else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.SAND.id)
					world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN13_SAND.id);
				else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.STONE.id)
					world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN13_STONE.id);
				else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.DIRT.id)
					world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN13_DIRT.id);
				else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.WATER.id)
					world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN13_WATER.id);
				
				counterX++;
				counterY--;
				
				while (Rand.Random(100) < 50) {	//gerade
					if (counterX >= 498) break;
					for (int i=0; i<=counterY-1; i++)
						world.put(new Point(counterX, 500-(10+i)), Tile.GRASS2.id);
				
					if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.GRASS.id || world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.GRASS2.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_GRASS.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.SAND.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_SAND.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.STONE.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_STONE.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.DIRT.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_DIRT.id);
					else if (world.get(new Point(counterX, 500-(11+counterY-1))) == Tile.WATER.id)
						world.put(new Point(counterX, 500-(11+counterY-1)), Tile.MOUNTAIN12_WATER.id);
					counterX++;
				}
				if (counterX >= 500) break;
			}
		} while (counterX < 480);
		
		
		
		// Rahmen rechts
		for (int y=0; y<height; y++) {
			for (int x=width; x>width-10; x--)
				world.put(new Point(x,y), Tile.GRASS2.id);
			world.put(new Point(width-10,y), Tile.MOUNTAIN21.id);
		}
				
		
		
		counterX = 0;
		counterY = 15;
		do {	//äußerste schleife
			counterY += Rand.Random(3,8);
			
			do {	//groß
				if (counterY >= 498) break;
				for (int i=0; i<=counterX; i++)
					world.put(new Point(500-(10+i), counterY), Tile.GRASS2.id);
				
				if (world.get(new Point(500-(11+counterX), counterY)) == Tile.GRASS.id || world.get(new Point(500-(11+counterX), counterY)) == Tile.GRASS2.id)
					world.put(new Point(500-(11+counterX), counterY), Tile.MOUNTAIN11_GRASS.id);
				else if (world.get(new Point(500-(11+counterX), counterY)) == Tile.SAND.id)
					world.put(new Point(500-(11+counterX), counterY), Tile.MOUNTAIN11_SAND.id);
				else if (world.get(new Point(500-(11+counterX), counterY)) == Tile.STONE.id)
					world.put(new Point(500-(11+counterX), counterY), Tile.MOUNTAIN11_STONE.id);
				else if (world.get(new Point(500-(11+counterX), counterY)) == Tile.DIRT.id)
					world.put(new Point(500-(11+counterX), counterY), Tile.MOUNTAIN11_DIRT.id);
				else if (world.get(new Point(500-(11+counterX), counterY)) == Tile.WATER.id)
					world.put(new Point(500-(11+counterX), counterY), Tile.MOUNTAIN11_WATER.id);
					
				counterX++;
				counterY++;
				
				while (Rand.Random(100) < 50) {	//gerade
					for (int i=0; i<=counterX-1; i++)
						world.put(new Point(500-(10+i), counterY), Tile.GRASS2.id);
				
					world.put(new Point(500-(11+counterX-1), counterY), Tile.MOUNTAIN21.id);
					
					counterY++;
				}
				
			} while(Rand.Random(100) < 75);
			
			while(counterX > 0) { // klein
				if (counterY >= 498) break;
				for (int i=0; i<=counterX; i++)
					world.put(new Point(500-(10+i), counterY), Tile.GRASS2.id);
				
				
				world.put(new Point(500-(11+counterX-1), counterY), Tile.MOUNTAIN31.id);
				world.put(new Point(500-(11+counterX-1), counterY+1), Tile.MOUNTAIN41.id);
				
				if (world.get(new Point(500-(11+counterX-1),counterY+2)) == Tile.GRASS.id || world.get(new Point(500-(11+counterX-1),counterY+2)) == Tile.GRASS2.id)
					world.put(new Point(500-(11+counterX-1),counterY+2), Tile.MOUNTAIN51_GRASS.id);
				else if (world.get(new Point(500-(11+counterX-1),counterY+2)) == Tile.SAND.id)
					world.put(new Point(500-(11+counterX-1),counterY+2), Tile.MOUNTAIN51_SAND.id);
				else if (world.get(new Point(500-(11+counterX-1),counterY+2)) == Tile.STONE.id)
					world.put(new Point(500-(11+counterX-1),counterY+2), Tile.MOUNTAIN51_STONE.id);
				else if (world.get(new Point(500-(11+counterX-1),counterY+2)) == Tile.DIRT.id)
					world.put(new Point(500-(11+counterX-1),counterY+2), Tile.MOUNTAIN51_DIRT.id);
				else if (world.get(new Point(500-(11+counterX-1),counterY+2)) == Tile.WATER.id)
					world.put(new Point(500-(11+counterX-1),counterY+2), Tile.MOUNTAIN51_WATER.id);
				
				
				
				counterX--;
				counterY++;
				
				while (Rand.Random(100) < 50) {	//gerade
					if (counterY >= 500) break;
					for (int i=0; i<=counterX-1; i++)
						world.put(new Point(500-(10+i), counterY), Tile.GRASS2.id);
				
					world.put(new Point(500-(11+counterX-1), counterY), Tile.MOUNTAIN21.id);
					counterY++;
				}
				if (counterY >= 500) break;
			}
		} while (counterY < 480);
		
		
		// Bosseingang der Traum
		
		int x = Rand.Random(20, 480);
		int y = Rand.Random(20, 480);

		
		while (world.get(new Point(x, y)) == Tile.WATER.id) {
			x = Rand.Random(20, 480);
			y = Rand.Random(20, 480);
		}

		PlayState.entities.get(Game.playerZ).add(new Mountaincave(x*32+64, y*32+64, 0));
		
		if (world.get(new Point(x, y-2)) == Tile.GRASS.id || world.get(new Point(x, y-2)) == Tile.GRASS2.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN11_GRASS.id);
		else if (world.get(new Point(x, y-2)) == Tile.SAND.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN11_SAND.id);
		else if (world.get(new Point(x, y-2)) == Tile.STONE.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN11_STONE.id);
		else if (world.get(new Point(x, y-2)) == Tile.DIRT.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN11_DIRT.id);
		else if (world.get(new Point(x, y-2)) == Tile.WATER.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN11_WATER.id);
		
		world.put(new Point(x,y-1), Tile.MOUNTAIN21.id);
		world.put(new Point(x,y), Tile.MOUNTAIN31.id);
		world.put(new Point(x,y+1), Tile.MOUNTAIN41.id);
		
		if (world.get(new Point(x, y+2)) == Tile.GRASS.id || world.get(new Point(x, y+2)) == Tile.GRASS2.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN51_GRASS.id);
		else if (world.get(new Point(x, y+2)) == Tile.SAND.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN51_SAND.id);
		else if (world.get(new Point(x, y+2)) == Tile.STONE.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN51_STONE.id);
		else if (world.get(new Point(x, y+2)) == Tile.DIRT.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN51_DIRT.id);
		else if (world.get(new Point(x, y+2)) == Tile.WATER.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN51_WATER.id);
		
		
		x++;
		if (world.get(new Point(x, y-2)) == Tile.GRASS.id || world.get(new Point(x, y-2)) == Tile.GRASS2.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN12_GRASS.id);
		else if (world.get(new Point(x, y-2)) == Tile.SAND.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN12_SAND.id);
		else if (world.get(new Point(x, y-2)) == Tile.STONE.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN12_STONE.id);
		else if (world.get(new Point(x, y-2)) == Tile.DIRT.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN12_DIRT.id);
		else if (world.get(new Point(x, y-2)) == Tile.WATER.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN12_WATER.id);
		
		world.put(new Point(x,y-1), Tile.GRASS2.id);
		world.put(new Point(x,y), Tile.MOUNTAIN32.id);
		world.put(new Point(x,y+1), Tile.MOUNTAIN42.id);
		
		if (world.get(new Point(x, y+2)) == Tile.GRASS.id || world.get(new Point(x, y+2)) == Tile.GRASS2.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN52_GRASS.id);
		else if (world.get(new Point(x, y+2)) == Tile.SAND.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN52_SAND.id);
		else if (world.get(new Point(x, y+2)) == Tile.STONE.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN52_STONE.id);
		else if (world.get(new Point(x, y+2)) == Tile.DIRT.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN52_DIRT.id);
		else if (world.get(new Point(x, y+2)) == Tile.WATER.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN52_WATER.id);
		
		
		
		x++;
		if (world.get(new Point(x, y-2)) == Tile.GRASS.id || world.get(new Point(x, y-2)) == Tile.GRASS2.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN13_GRASS.id);
		else if (world.get(new Point(x, y-2)) == Tile.SAND.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN13_SAND.id);
		else if (world.get(new Point(x, y-2)) == Tile.STONE.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN13_STONE.id);
		else if (world.get(new Point(x, y-2)) == Tile.DIRT.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN13_DIRT.id);
		else if (world.get(new Point(x, y-2)) == Tile.WATER.id)
			world.put(new Point(x, y-2), Tile.MOUNTAIN13_WATER.id);
		
		world.put(new Point(x,y-1), Tile.MOUNTAIN23.id);
		world.put(new Point(x,y), Tile.MOUNTAIN33.id);
		world.put(new Point(x,y+1), Tile.MOUNTAIN43.id);
		
		if (world.get(new Point(x, y+2)) == Tile.GRASS.id || world.get(new Point(x, y+2)) == Tile.GRASS2.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN53_GRASS.id);
		else if (world.get(new Point(x, y+2)) == Tile.SAND.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN53_SAND.id);
		else if (world.get(new Point(x, y+2)) == Tile.STONE.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN53_STONE.id);
		else if (world.get(new Point(x, y+2)) == Tile.DIRT.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN53_DIRT.id);
		else if (world.get(new Point(x, y+2)) == Tile.WATER.id)
			world.put(new Point(x, y+2), Tile.MOUNTAIN53_WATER.id);
		
		
		return world;
	}
	
	public static HashMap<Point, Integer> generateDungeon(int width, int height, int points, int z) {
		 HashMap<Point, Integer> world = new HashMap<>();
		 HashMap<Point, Integer> checkPoints = new HashMap<>();
		 
		for (int i=0; i<points; i++) 
			checkPoints.put(new Point(Rand.Random(width), Rand.Random(height)), Rand.weightedRandom(new int[] {(int) (2*Math.pow(2, z-1)), (int) (16*Math.pow(2, z-1)), (int) (16/Math.pow(2, z-1)), (int) (16/Math.pow(2, z-1))}, new int[] {Tile.STONE.id, Tile.U_STONE.id, Tile.U_DIRT.id, Tile.U_SAND.id}));

		for(int x=0; x<width; x++) {
			if (x%100 == 0)
				LoadState.generateUndergroundLoadingScreen();
			for (int y=0;y<height; y++) {
				int minDist = Integer.MAX_VALUE;
				Point nearestPoint = null;
				for (Point p : checkPoints.keySet()) {
					int xDiff = p.x - x;
		            int yDiff = p.y - y;
		            int cDist = xDiff*xDiff + yDiff*yDiff;
		            
		            if (cDist < minDist) {
		            	nearestPoint = p;
		            	minDist = cDist;
	                }  
				}
				world.put(new Point(x,y), checkPoints.get(nearestPoint));
			}
		}
		
		// Rahmen oben
		for (int x=0; x<width; x++)
			for (int y=0; y<9; y++)
				world.put(new Point(x,y), Tile.LAVA.id);
		
		// Rahmen unten
		for (int x=0; x<width; x++)
			for (int y=height-9; y<500; y++)
				world.put(new Point(x,y), Tile.LAVA.id);
		
		// Rahmen links
		for (int x=0; x<9; x++)
			for (int y=0; y<height; y++)
				world.put(new Point(x,y), Tile.LAVA.id);
		
		// Rahmen rechts
		for (int x=width-9; x<width; x++)
			for (int y=0; y<height; y++)
				world.put(new Point(x,y), Tile.LAVA.id);
		
		for (Point p : checkPoints.keySet()) {
			if (checkPoints.get(p) == Tile.U_STONE.id) {
				int c = Rand.Random(1, 10);
				int ore = Rand.weightedRandom(new int[] {16, 8, 4, 2, 1}, new int[] {Tile.U_COAL.id, Tile.U_IRON.id, Tile.U_RUBY.id, Tile.U_GOLD.id, Tile.U_DIAMOND.id});
				
				for (int x=p.x-1; x<=p.x+1; x++)
					for (int y=p.y-1; y<=p.y+1;y++) {
						if (c <= 0)
							continue;
						
						c--;
						
						if(x >= 0 && x < width && y >= 0 && y<height)
							world.put(new Point(x, y), ore);
					}
			}
		}
		
		return world;
	}	
	
	
	
	
	
	
	
	
	
	
}
