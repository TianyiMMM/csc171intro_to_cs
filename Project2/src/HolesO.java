



public class HolesO{
	private int[] yardsO = {376, 453, 397, 480, 568, 412, 371, 175, 
			352, 386, 174, 348, 465, 618, 455, 423, 495, 357};
	private int[] ParO = {4, 4, 4, 4, 5, 4, 4, 3, 4, 4, 3, 4, 4, 5, 
			4, 4, 4, 4};
	private String[] Count = {"first", "second", "third", "fourth", 
			"fifth", "sixth", "seventh", "eighth", "nineth", 
			"tenth", "eleventh", "twelfth", "thirteenth", "fourteenth",
			"fifteenth", "sixteenth", "seventeenth", "eighteenth"};
	
	private int Pars = 0;
	
	public HolesO(int course){
		int[] yardsO = new int[18];
		int[] ParO = new int[18];
		for (int hole = 0; hole < 18; hole ++){
			yardsO[hole] = this.yardsO[hole]; 
			ParO[hole] = this.ParO[hole];
			System.out.println("You are at the " + Count[hole] + " tee. " + yardsO[hole] + " yards, par " + ParO[hole] + ".");//moved from Class Shots afterwards
			Shots a = new Shots(course, yardsO[hole], ParO[hole]);
			Pars += a.getPars();
		}
	}
	
	public int getPO(){
		return Pars;
	}
}
