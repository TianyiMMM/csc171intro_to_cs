package v2;

import java.util.Scanner;
public class Games{
	
	protected int course;
	protected String choice;
	
	public Games(){
	}
	
	// 'Starts' method that provide the prompt and ask the player whether she wants to quit or to play another round
	Scanner input = new Scanner(System.in);
	public void Starts(){
		// Prompt the player to choose the course she wants to play
		System.out.print("Welcome to TTY Golf! \n \n"
				+ "Please select a course: \n"
				+ "1. Genesee Valley Park North Course \n"
				+ "2. The Old Course at St. Andrews \n"
				+ "Your choice [1-2]: ");
		int course = input.nextInt();
		System.out.println();
		Course(course); // Call the Course method provided below
		System.out.println();
		System.out.print("Would you like to play another round? [Y/N]");
		choice = input.next();
		System.out.println();
		// If she answers "Y(yes)" then another round begins
		while (choice.equals("Y")){
			System.out.print("Please select a course: ");
			course = input.nextInt();
			System.out.println();
			Course(course);
			System.out.print("Would you like to play another round? [Y/N]");
			choice = input.next();
		}
		// If she answers "No(no)" then the game ends
		System.out.print("Thank you for playing the game.");
	}
	
	// 'Course' method that inform the player which course she has chosen and the final score of this round after the round is finished
	public void Course(int course){
		this.course = course;
		// If the player chooses the Genesee Valley Park North Course
		if (course == 1){
			System.out.println("You are playing Genesee Valley Park North Course. \n"
					+ "If you want to quit the game in the middle of a round, input 0 while choosing clubs and power. ");
			System.out.println();
			Holes(course);// Call the Holes method provided below
			if (hole != 19){
				if (Pars == 71){
					System.out.println("Your final score of the round actually make the par! Nice! ");
				} else if (Pars < 71){
					System.out.println("Your final score for the round is: " + (71-Pars) + " under. Good job!");
				} else {
					System.out.println("Your final score for the round is: " + (Pars-71) + " over. Nice try! ");
				}
			}
			// If the player chooses The Old Course at St. Andrews
			} else if (course == 2) {
				System.out.println("You are playing The Old Course at St. Andrews. \n"
						+ "If you want to quit the game in the middle of a round, input 0 while choosing clubs and power. ");
				System.out.println(); 
				Holes(course); // Call the Holes method provided below
				if (hole != 19){
					if (Pars == 72){
						System.out.println("Your final score of the round actually make the par! Nice! ");
					} else if (Pars < 72){
						System.out.println("Your final score for the round is: " + (72-Pars) + " under. Good job!");
					} else {
						System.out.println("Your final score for the round is: " + (Pars-72) + " over. Nice try! ");
					}
				}
			} 
	}
	
	// 'Holes' method takes turns and produces the prompts that keep track of the player's game situation.
	protected int[] yardsG = {530, 305, 331, 201, 500, 226, 409, 410, 
			229, 433, 363, 174, 545, 419, 512, 410, 320, 170};

	protected int[] ParG = {5, 4, 4, 3, 5, 3, 4, 4, 3, 4, 4, 3, 5, 4,
			5, 4, 4, 3};
	
	protected int[] yardsO = {376, 453, 397, 480, 568, 412, 371, 175, 
			352, 386, 174, 348, 465, 618, 455, 423, 495, 357};
	
	protected int[] ParO = {4, 4, 4, 4, 5, 4, 4, 3, 4, 4, 3, 4, 4, 5, 
			4, 4, 4, 4};

	private String[] Count = {"first", "second", "third", "fourth", 
		"fifth", "sixth", "seventh", "eighth", "nineth", 
		"tenth", "eleventh", "twelfth", "thirteenth", "fourteenth",
		"fifteenth", "sixteenth", "seventeenth", "eighteenth"};

	protected int Pars = 0;
	protected int hole;
	protected int remainder;

	public void Holes(int course){
		double[] yards = new double[18];
		int[] Par = new int[18];
		// Starts each turns
		for (int hole = 0; hole < 18; hole++){
			// Situation of which the player is playing on the first course
			if (course == 1){
				this.hole = hole;
				yards[hole] = this.yardsG[hole]; 
				Par[hole] = this.ParG[hole];
				System.out.println("You are at the " + Count[hole] + " tee. " + yardsG[hole] + " yards, par " + ParG[hole] + ".");//moved from Class Shots afterwards
				// Declare object 'Shots' that will shot the golf ball until the ball is in the hole
				Shots a = new Shots(course, yards[hole], ParG[hole]);
				if (a.getRemainder() == -1){
					remainder = a.getRemainder();
					hole = 19;
					this.hole = hole;
				} 
				this.Pars += a.getPars();
			// Situation of which the player is playing on the second course
			} else {
				this.hole = hole;
				yards[hole] = this.yardsO[hole]; 
				Par[hole] = this.ParO[hole];
				System.out.println("You are at the " + Count[hole] + " tee. " + yardsO[hole] + " yards, par " + ParO[hole] + ".");//moved from Class Shots afterwards
				// Declare object 'Shots' that will shot the golf ball until the ball is in the hole
				Shots a = new Shots(course, yardsO[hole], ParO[hole]);
				if (a.getRemainder() == -1){
					remainder = a.getRemainder();
					hole = 19;
					this.hole = hole;
				} 
				this.Pars += a.getPars();	
			}
			// Inform the player their total score so far after each turn
			if (remainder != -1){
				System.out.println("Your score after this hole is: " + Pars + " par(s). ");
				System.out.println();
			}
		}
	}
}
