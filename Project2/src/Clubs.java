


import java.util.Random;

public class Clubs{
	
	private int[] mean = {230, 215, 180, 170, 155, 145, 135, 125, 110, 50};
	private int[] stddev = {30, 20, 20, 17, 15, 15, 15, 15, 10, 10};
	
	private int distance;
	
	public Clubs(int course, int yards, int Pars, int clubs, int power, double d){
		Random random = new Random();
		double m = mean[clubs-1] * power / 10.0; 
		double s = stddev[clubs-1] * power / 10.0; 
		d = Math.abs(random.nextGaussian() * s + m); 
		this.distance = (int)d;
	}
	
	public int getD(){   //added afterward(modification)
		return distance;
	}

}
