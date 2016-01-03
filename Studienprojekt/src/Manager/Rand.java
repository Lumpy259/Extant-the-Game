package Manager;
import java.util.Random;

public class Rand {

	private static Random r = new Random(System.nanoTime());
	
	public static void setSeed(long seed) {
		r.setSeed(seed);
	}
	
	public static int Random(int min, int max) {
		return r.nextInt(max-min) + min;
	}

	public static int Random(int max) {
		return r.nextInt(max);
	}
	
	public static int weightedRandom(int[] values, int[] returnvalues) {
		return returnvalues[weightedRandom(values)];
		/*
		int sum = 0;
		int rand = r.nextInt(sum(values));
		int c = values.length;
		
		for (int i=0; i<c; i++) 
		{
			sum += values[i];
			if (rand < sum)
				return returnvalues[i];
		}
		return -1;*/
	}
	
	public static int weightedRandom(int[] values) {
		int sum = 0;
		int rand = r.nextInt(sum(values));
		int c = values.length;
		
		for (int i=0; i<c; i++) 
		{
			sum += values[i];
			if (rand < sum)
				return i;
		}
		return -1;
	}
	
	public static double avg(int[] values) {
		int c = values.length;
		int sum = 0;
		for (int i=0; i<c; i++)
			sum += values[i];
		return (1.0 * sum) / c;
	}
	
	public static int sum(int[] values) {
		int c = values.length;
		int sum = 0;
		for (int i=0; i<c; i++)
			sum += values[i];
		return sum;
	}
	public static int mult(int[] values) {
		int c = values.length;
		int mult = 1;
		for (int i=0; i<c; i++)
			mult *= values[i];
		return mult;
	}
}
