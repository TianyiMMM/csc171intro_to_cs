



public class Holes{

	private int[] yardsG = {530, 305, 331, 201, 500, 226, 409, 410, 
				229, 433, 363, 174, 545, 419, 512, 410, 32, 170};
	
	private int[] ParG = {5, 4, 4, 3, 5, 3, 4, 4, 3, 4, 4, 3, 5, 4,
				5, 4, 4, 3};
	
	private String[] Count = {"first", "second", "third", "fourth", 
			"fifth", "sixth", "seventh", "eighth", "nineth", 
			"tenth", "eleventh", "twelfth", "thirteenth", "fourteenth",
			"fifteenth", "sixteenth", "seventeenth", "eighteenth"};
	
	private int Pars = 0;
	
	public Holes(int course){
		int[] yardsG = new int[18];
		int[] ParG = new int[18];
		for (int hole = 0; hole < 18; hole++){
			yardsG[hole] = this.yardsG[hole]; 
			ParG[hole] = this.ParG[hole];
			System.out.println("You are at the " + Count[hole] + " tee. " + yardsG[hole] + " yards, par " + ParG[hole] + ".");//moved from Class Shots afterwards
			Shots a = new Shots(course, yardsG[hole], ParG[hole]);
			Pars += a.getPars();
		}
	}
	public int getPG(){
		return Pars;
	}
}
