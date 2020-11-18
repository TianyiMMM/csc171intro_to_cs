


import java.util.Scanner;
public class Games{
	
	private int course;
	private String choice;
	
	public Games(){
	}
	// the definition of OOP
	public void Starts(){
		Scanner input = new Scanner(System.in);
		int course;
		System.out.print("Welcome to TTY Golf! \n \n"
				+ "Please select a course: \n"
				+ "1. Genesee Valley Park North Course \n"
				+ "2. The Old Course at St. Andrews \n"
				+ "Your choice [1-2]: ");
		course = input.nextInt();
		System.out.println();
		Course a = new Course(course);
		
		System.out.print("Would you like to play another round? [Y/N]");
		choice = input.next();
		System.out.println();
		while (choice.equals("Y")){
			System.out.print("Please select a course: ");
			course = input.nextInt();
			System.out.println();
			Course b = new Course(course);
			System.out.print("Would you like to play another round? [Y/N]");
			choice = input.next();
		}
		System.out.print("Thank you for playing the game.");
	}

}
