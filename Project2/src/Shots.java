


import java.util.Scanner;
import java.lang.Math;
public class Shots{
	private int yards;
	private int Pars;
	private int clubs;
	private int power;
	
	public Shots(int course, int yards, int Pars){
		this.yards = yards;
		Scanner a = new Scanner(System.in);
		double d = 0;
		int remainder = this.yards; 
		int pars;
		for (pars = 1/*modified afterwards; previously "pars=0"*/; remainder > 20; pars++){
			this.Pars = pars;
			System.out.print("Choose your club [1-10]: ");
			clubs = a.nextInt();
			System.out.print("Power [1-10]: ");
			power = a.nextInt();
			Clubs b = new Clubs(course, yards, Pars, clubs, power, d);
			System.out.println("You hit the ball " + b.getD()/* added afterward*/ + " yards.");
			remainder = Math.abs(remainder - b.getD());
			System.out.println();
			System.out.println(this.Pars/*modified afterwards; previously "Pars" only*/ + " Par(s). " + remainder + " yards from the hole. ");
		}
		int pars2;
		remainder *= 3;
		for (pars2 = 0/*modified afterwards; previously "pars2=1"*/; remainder <= 60 && remainder > 1; pars2++){
			this.Pars = pars + pars2;
			System.out.print("You are on the green. \n" 
							+ "Power [1-10]: ");
			power = a.nextInt();
			Putting c = new Putting(course, yards, Pars, power, d);
			System.out.println("You hit the ball " + c.getD() + " feets.");
			remainder = Math.abs(remainder - c.getD());
			System.out.println();
			System.out.println(this.Pars/*modified afterwards; previously "Pars" only*/ + " Par(s). " + remainder + " feets from the hole. ");
		}
		if (this.Pars == Pars){
			System.out.println("You made par on this hole!");
			System.out.println();
		} else if (this.Pars < Pars){
			System.out.println("You are under par!");
			System.out.println();
		} else {
			System.out.println("You are over par. ");
			System.out.println();
		}
	}
	
	public int getPars(){
		return Pars;
	}
}
