package v2;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
public class Shots extends Games{
	protected int yards;
	protected int Pars;
	protected int clubs;
	protected int power;
	protected int remainder;
	protected int Pars3;// Used to store the scores made during the bunker
	
	// Constructor of object 'Shots' that keep shooting the golf according to the player's choice of club and power until the golf is in the hole
	public Shots(int course, double yards, int Pars){
		this.yards = (int)yards;
		Scanner a = new Scanner(System.in);
		double d = 0;
		int remainder = this.yards; 
		double bunkerRemainder = 0;
		int pars;
		int pars3;
		
		// Shots before entering the green
		for (pars = 1; remainder > 20; pars++){
			this.Pars = pars;
			// Prompt the player to choose the club and power
			System.out.print("Choose your club [1-10]: ");
			clubs = a.nextInt();
			System.out.print("Power [1-10]: ");
			power = a.nextInt();
			
			// Decide whether the player wants to quit the game
			if (clubs == 0 && power == 0){
				remainder = -1;
			// If the player wants to continue then:
			} else {
				Clubs(course, yards, Pars, clubs, power, d); // Call 'Clubs' method that calculate the distance the golf travel
				System.out.println("You hit the ball " + distance + " yards.");
				int remainder2 = remainder;
				remainder = Math.abs(remainder - distance); 
				System.out.println();
				
				// Decide whether the ball lands in the water hazard, or the bunker, or normal ground
				// When the ball lands in the water hazard
				if (remainder < (yards - (int)4.0/9.0*yards) && remainder > (yards - (int)5.0/9.0*yards)){
					System.out.println("Your ball lands in the water hazard. \n"
							+ "You loses a stroke and hits again from the same place.");
					remainder = remainder2;
					System.out.println();
					System.out.println(this.Pars + " Par(s). " + remainder2 + " yards from the hole. ");
				// When the ball lands in the bunker
				} else if (remainder < (yards - (int)11.0/18.0*yards) && remainder > (yards - (int)7.0/9.0*yards)){
					bunkerRemainder = (int)(remainder-(int)2.0/9.0*yards);
					System.out.println(this.Pars + " Par(s). " + "Your ball lands in the bunker. \n"
							+ "You can only use club number 10 now and the ball will only travel a short distance. \n");
					// Switch to use "Bunker" methods to calculate the distance traveled by the ball each shot instead of keep using the 'Club' method
					for (pars3 = 1; bunkerRemainder > 1; pars3++){
						System.out.print("Choose your power [1-10]: ");
						power = a.nextInt();
						System.out.println();
						// Decide whether the player wants to quit the game
						if (power == 0){
							bunkerRemainder = -1;
							remainder = -1;
						} else {
							Bunker(course, yards, Pars, power, d);
							System.out.println("You hit the ball " + distance + " yards.");
							bunkerRemainder = bunkerRemainder - distance;
							remainder = Math.abs(remainder - distance); 
							System.out.println();
							if (bunkerRemainder <= 1){
								System.out.println((pars + pars3) + " Par(s). " + remainder + " yards from the hole. \n"
										+ "Your ball is out of the bunker. ");
							} else {
								System.out.println((pars + pars3) + " Par(s). " + (int)bunkerRemainder + " yards from the border of the bunker. \n"
										+ "Your ball is still in the bunker. ");
							}
						}
						this.Pars3 = pars3;
					}
					pars += this.Pars3;
				// When the ball lands in normal grounds
				} else { 
					System.out.println(this.Pars + " Par(s). " + remainder + " yards from the hole. ");
				}
			}
			this.remainder = remainder;
		}
		int pars2;
		remainder *= 3;
		
		// Putting
		for (pars2 = 0; remainder <= 60 && remainder > 1; pars2++){
			this.Pars = pars + pars2;
			// Inform the player she is on the green and prompt her to choose the power
			System.out.print("You are on the green. \n" 
							+ "Power [1-10]: ");
			power = a.nextInt();
			if (power == 0){
				remainder = -1;
			} else {
				Putting(course, yards, Pars, power, d);// Call 'Putting' method that calculate the distance the golf travel
				System.out.println("You hit the ball " + distance + " feets.");
				remainder = Math.abs(remainder - distance);// Calculate and inform the remaining distance from the golf to the hole
				System.out.println();
				System.out.println(this.Pars + " Par(s). " + remainder + " feets from the hole. ");
			}
			this.remainder = remainder;
		}
		
		// Inform the player the result of the turn
		if (this.remainder != -1){
			if (this.Pars == Pars){
				System.out.println("You made par on this hole!");
			} else if (this.Pars < Pars){
				if ((Pars-this.Pars)==1){
					System.out.println("You've got a birdie! "); // Adding the term 'birdie' to make the game more realistic(extra credit 1)
				} else if ((Pars-this.Pars)==2){
					System.out.println("You've got an eagle! Nice job! "); // Adding the term 'eagle' to make the game more realistic(extra credit 1)
				} else if ((Pars-this.Pars)==3){
					System.out.println("You've got an albatross! Unbelievable! "); // Adding the term 'albatross' to make the game more realistic(extra credit 1)
				} else {
					System.out.println("You are under par!");
				}
			} else {
				if ((this.Pars-Pars)==1){
					System.out.println("You've got a bogey! "); // Adding the term 'bogey' to make the game more realistic(extra credit 1)
				} else {
					System.out.println("You are over par. ");
				}
			}
			this.Pars -= Pars;
			System.out.println();
		}
	}
	
	// Getters
	public int getPars(){
		return Pars;
	}
	public int getRemainder(){
		return remainder;
	}
	
	// 'Clubs' method that calculate the distance traveled by the ball per each shot in the normal ground
	protected int[] mean = {230, 215, 180, 170, 155, 145, 135, 125, 110, 50};
	protected int[] stddev = {30, 20, 20, 17, 15, 15, 15, 15, 10, 10};
	
	protected int distance = 0;
	
	//Calculation
	public void Clubs(int course, double yards, int Pars, int clubs, int power, double d){
		Random random = new Random();
		double m = mean[clubs-1] * power / 10.0; 
		double s = stddev[clubs-1] * power / 10.0; 
		d = Math.abs(random.nextGaussian() * s + m); 
		this.distance = (int)d;
	}
	
	// 'Putting' method that calculate the distance traveled by the ball per each shot in the green
	protected int[] meanP = {1, 2, 4, 8, 12, 16, 20, 25, 30, 40};
	protected int[] stddevP = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};

	// Calculation
	public void Putting(int course, double yards, int Pars, int power, double d){
		Random random = new Random();
		double m = meanP[power-1];
		double s = stddevP[power-1];
		d = Math.abs(random.nextGaussian() * s + m);
		this.distance = (int)d;
	}	
	
	// 'Bunker' method that calculate the distance traveled by the ball per each shot in the bunker
	public void Bunker(int course, double yards, int Pars, int power, double d){
		Random random = new Random();
		double m = mean[9] * power / 10.0; 
		double s = stddev[9] * power / 10.0; 
		d = Math.abs(random.nextGaussian() * s + m); 
		this.distance = (int)(d*2.0/3.0);
	}	
}
