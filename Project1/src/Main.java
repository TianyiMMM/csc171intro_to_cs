/* tma8
 * project1
 * MW 200-315
 * I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;
import java.lang.Math;
import java.util.Random;
public class Main {

	public static void main(String[] args) {
		// Variables
		boolean outer = true; // Will explain later
		boolean inner = true; // Will explain later
		int score = 0; // Initialized the score of the user to 0
		int x; // = distance
		int y; // = height of the wall
		double h; // h is the height that the projectile will reach when it has moved x meters
		double v; // Speed of the projectile
		double a; // Angle
		String choice; // Whether to quit the game
		
		Scanner input = new Scanner(System.in);
		Random generator = new Random();
		
		while (outer){ 
			// When outer = true, another new game is started with new random x and y
			// When outer = false, the user quit the game
			
			x = generator.nextInt(100)+1; // The program draws two random integers as the distance and height
			y = generator.nextInt(100)+1;
			while (inner){
				// inner = true means that the user hasn't got the right answer yet and need to try again
				// inner = false means that the user has got the right answer and can proceed
				
				System.out.print("Please input the speed of your projectile: ");
				v = input.nextInt();
				System.out.print("Please input the angle you want to shoot your projectile: ");
				a = input.nextInt();
				
				a = Math.toRadians(a);
				h = x * Math.tan(a) - 9.8 * Math.pow(x, 2) / 2 * Math.pow(v * Math.cos(a), 2);
				score -= 1; // The game charges 1 point for every try
				
				if (h - y > 4){
					String [] arr = {"Plenty of room! ", "Way too high! ", "Totally over the wall! ", "Much higher than the wall! "};
					int select = generator.nextInt(arr.length);
					System.out.println(arr[select]); // The program randomly chooses a string based on the number of each string
					score += 3;
					inner = false;
				} else if (h - y <= 4 && h - y > 0){
					String [] arr = {"You made it! ", "Just over the wall! ", "Awesome, the height is just right! ", "Precisely the height :)"};
					int select = generator.nextInt(arr.length);
					System.out.println(arr[select]);
					score += 5;
					inner = false;
				} else if (h - y >= 4 && h - y <= 0){
					String [] arr = {"Not quite over. ", "You nearly made it, try one more time.", "Didn't made it but close! ", "Almost there! "};
					int select = generator.nextInt(arr.length);
					System.out.println(arr[select]);
					inner = true;
				} else {
					String [] arr = {"Not even close! ", "Noooooo", "Far too low. ", "Far below the top of the wall! "};
					int select = generator.nextInt(arr.length);
					System.out.println(arr[select]);
					score -= 2;
					inner = true;
				}
			}
			System.out.println("Do you want to quit? Type 'yes' or 'no'. "); // Asking the user whether they want to quit or not
			choice = input.next();
			if (choice.equals("yes")){
				outer = false; // When outer = false, the user quit the game
			} else {
				outer = true; // When outer = true, another new game is started with new random x and y
				inner = true; // inner = true means that the user can have a new attempt to launch the projectile
			}		
			}
		System.out.println("Your score is: " + score);
	}
}
