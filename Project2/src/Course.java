



public class Course{
	
	private int course;
	
	public Course(int course){
		this.course = course;
		if (course == 1){
			System.out.println("You are playing Genesee Valley Park North Course. ");
			System.out.println();
			Holes a = new Holes(course);
			if (a.getPG() == 71){
				System.out.println("Your final score of the round actually make the par! Nice! ");
			} else if (a.getPG() < 71){
				System.out.println("Your final score for the round is: " + (71-a.getPG()) + " under. Good job!");
			} else {
				System.out.println("Your final score for the round is: " + (a.getPG()-71) + " over. Nice try! ");
			}
			} else if (course == 2) {
				System.out.println("You are playing The Old Course at St. Andrews. ");
				System.out.println(); 
				HolesO b = new HolesO(course);
				if (b.getPO() == 72){
					System.out.println("Your final score of the round actually make the par! Nice! ");
				} else if (b.getPO() < 72){
					System.out.println("Your final score for the round is: " + (72-b.getPO()) + " under. Good job!");
				} else {
					System.out.println("Your final score for the round is: " + (b.getPO()-72) + " over. Nice try! ");
				}
			} 
	}
	
	

}
