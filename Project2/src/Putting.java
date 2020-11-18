


import java.util.Random;

public class Putting{
	
	private int[] mean = {1, 2, 4, 8, 12, 16, 20, 25, 30, 40};
	// modified. previously the index is in the reverse way(the stronger the power, the shorter the distance)
	private int[] stddev = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
	
	private int distance;

	public Putting(int course, int yards, int Pars, int power, double d){
		Random random = new Random();
		double m = mean[power-1];
		double s = stddev[power-1];
		d = Math.abs(random.nextGaussian() * s + m);
		this.distance = (int)d;
	}
	
	public int getD(){   //added afterward(modification)
		return distance;
	}
}
