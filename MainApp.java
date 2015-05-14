/* Authors:
 * Panagos Ioannis, 3140308
 * Sartzis Charalampos, 3140179
 * Nikitas Golfopoulos, 3140032
 */

//imports
import java.text.SimpleDateFormat;
import java.util.*;

public class MainApp {
	public static void main(String[] args) throws Exception {
		
		//Creating new 'shelves' containing Disc, Game and RentalInstance objects
		DiscShelves myMovieShelves = new DiscShelves();
		DiscShelves myGameShelves = new DiscShelves();
		DiscRentals myDiscRentals = new DiscRentals();
		
		//Initializing variables
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String answer1;
		String answer2;
		String answer3;
		boolean done = false;
		
		//Populate movies, rentals
		PopulateMovieShelves(myMovieShelves);
		PopulateGameShelves(myGameShelves);
		PopulateDiscRentals(myDiscRentals,myMovieShelves,myGameShelves);
		
		System.out.println("Welcome to the Disc Club!");
		//Main loop
		while(!done){
			
			PrintMenu();
			answer1 = in.nextLine();
			
			//View available movies
			if (Integer.parseInt(answer1) == 1){
				String medium = DvdOrBlu();
				DisplayDiscShortDetails(myMovieShelves,medium);
				PrintMovieDetailsPrompt();
				answer2 = in.nextLine();
				
				//View Movie Details
				if (Integer.parseInt(answer2) >= 1 && Integer.parseInt(answer2) < myMovieShelves.size()){
					
					System.out.println(myMovieShelves.get(Integer.parseInt(answer2)).returnDetails());
					PrintMovieRentConfirmation();
					answer3 = in.nextLine();
					
					//New Rental Instance
					if (Integer.parseInt(answer3) == 1){
						if (medium.equals("blu")) CreateNewRental(true,Integer.parseInt(answer2),myDiscRentals, false );
						else CreateNewRental(true,Integer.parseInt(answer2),myDiscRentals, true );
						myMovieShelves.get(Integer.parseInt(answer2)).OneLessAvailable();
					}
					else if (Integer.parseInt(answer3) == 0){	
					}
					else PrintCommandNotFound();
				}
				else System.out.println("I can't find that movie");
			}
			//View available games
			else if (Integer.parseInt(answer1) == 2){
				String medium = DecideConsole();
				DisplayDiscShortDetails(myGameShelves, medium);
				PrintGameDetailsPrompt();
				answer2 = in.nextLine();
				
				//View Game Details
				if (Integer.parseInt(answer2) >= 1 && Integer.parseInt(answer2) < myGameShelves.size()){
					
					System.out.println(myGameShelves.get(Integer.parseInt(answer2)).returnDetails());
					PrintGameRentConfirmation();
					answer3 = in.nextLine();
					
					//New Rental Instance
					if (Integer.parseInt(answer3) == 1){
						CreateNewRental(false,Integer.parseInt(answer2),myDiscRentals,true);
						myGameShelves.get(Integer.parseInt(answer2)).OneLessAvailable();
					}
					
					else if (Integer.parseInt(answer3) == 0){
						
					}
					else PrintCommandNotFound();
				}
				else System.out.println("I can't find that game");
			}
			
			//View rentals history
			else if (Integer.parseInt(answer1) == 3){
				DisplayDiscRentals(myDiscRentals,myMovieShelves,myGameShelves);
				
			}
			
			//Add new item
			else if (Integer.parseInt(answer1) == 4){
				CreateNewDisc(myMovieShelves,myGameShelves);
			}
			
			//Exit
			else if (Integer.parseInt(answer1) == 0){
				System.out.println("\nThank you for using our Video Club application!\n");
				done = true;
			}
			else PrintCommandNotFound();
		}
	}
	
	//Populate shelves and rental logbooks
	public static void PopulateMovieShelves(DiscShelves myDiscShelves){
		//dvd
		myDiscShelves.DiscShelves.add(new MovieDisc("dvd","You're not supposed to see this", 2015, "Something's wrong", "Drama, Comedy", "Check your code", "seriously", "now", 124.12,666, false));
		myDiscShelves.DiscShelves.add(new MovieDisc("dvd","12 years a slave", 2013, "Panagos inc.", "Biography, Drama, History", "Steve McQueen", "John Ridley, Solomon Northup", " Chiwetel Ejiofor, Michael Kenneth Williams, Michael Fassbender", 124.12, 1, false));
		myDiscShelves.DiscShelves.add(new MovieDisc("dvd","Lord of the Rings; The return of the King", 2003, "New Line Cinema","Fantasy, Adventure", "Peter Jackson", "J.R.R. Tolkien", " Elijah Wood, Viggo Mortensen, Ian McKellen, Orlando Bloom", 124.12, 2, true));
		myDiscShelves.DiscShelves.add(new MovieDisc("dvd","The Dark Knight", 2008, "Warner Bros.", " Action, Drama, Crime", "Christopher Nolan", "Jonathan & Christopher Nolan", " Christian Bale, Heath Ledger, Aaron Eckhart", 124.12, 1, true));
		myDiscShelves.DiscShelves.add(new MovieDisc("dvd","Dumb and Dumber", 1994, "New Line Cinema", "Comedy", "Peter & Bobby Farrelly","Peter Farrelly, Bennett Yellin", " Jim Carrey, Jeff Daniels, Lauren Holly", 124.12, 2, true));
		//blu ray
		myDiscShelves.DiscShelves.add(new MovieDisc("blu","12 years a slave", 2013, "Panagos inc.", "Biography, Drama, History", "Steve McQueen", "John Ridley, Solomon Northup", " Chiwetel Ejiofor, Michael Kenneth Williams, Michael Fassbender", 124.12, 2, false));
		myDiscShelves.DiscShelves.add(new MovieDisc("blu","Lord of the Rings; The return of the King", 2003, "New Line Cinema","Fantasy, Adventure", "Peter Jackson", "J.R.R. Tolkien", " Elijah Wood, Viggo Mortensen, Ian McKellen, Orlando Bloom", 124.12, 1, false));
		myDiscShelves.DiscShelves.add(new MovieDisc("blu","The Dark Knight", 2008, "Warner Bros.", " Action, Drama, Crime", "Christopher Nolan", "Jonathan & Christopher Nolan", " Christian Bale, Heath Ledger, Aaron Eckhart", 124.12, 2 , false));
		myDiscShelves.DiscShelves.add(new MovieDisc("blu","Dumb and Dumber", 1994, "New Line Cinema", "Comedy", "Peter & Bobby Farrelly","Peter Farrelly, Bennett Yellin", " Jim Carrey, Jeff Daniels, Lauren Holly", 124.12, 2, false));
	}
	public static void PopulateGameShelves(DiscShelves myDiscShelves){
		myDiscShelves.DiscShelves.add(new GameDisc("pc","String", 2015, "String", "String", 777, true));
		//xbox
		myDiscShelves.DiscShelves.add(new GameDisc("xbox","Halo:Reach", 2010, "Bungie", "Action, Adventure, Mystery", 2, true));
		myDiscShelves.DiscShelves.add(new GameDisc("xbox","Halo 4", 2012, "Bungie", "Action, Adventure, Mystery", 1, true));
		//playstation
		myDiscShelves.DiscShelves.add(new GameDisc("playstation","FIFA 15", 2014, "EA Sports", "Sports, football", 1, true));
		myDiscShelves.DiscShelves.add(new GameDisc("playstation","NBA 2k15", 2014, "2k Sports", "Sports, basketball", 2, true));
		//nintendo
		myDiscShelves.DiscShelves.add(new GameDisc("nintendo", "Super Mario Bros.", 1985, "Nintendo", "Platformer", 2, true));
		myDiscShelves.DiscShelves.add(new GameDisc("nintendo", "Super Metroid", 1994, "Nintendo", "Action, Adventure, Horror", 2, true));
	}
	public static void PopulateDiscRentals(DiscRentals myDiscRentals, DiscShelves myMovieShelves, DiscShelves myGameShelves) throws Exception{
		myDiscRentals.RentalsLogbook.add(new RentalInstance(true, 1, "This movie shouldn't be visible. You shouldn't be here. LEAVE", "666 666 667", ConvertStringToDate("06/06/0666"), false));
		//Movies
		myDiscRentals.RentalsLogbook.add(new RentalInstance(true, 3, "Mary Sue", "2112128989", ConvertStringToDate("09/04/2015"), true));
		myDiscRentals.RentalsLogbook.add(new RentalInstance(true, 6, "John Stan", "211213459", ConvertStringToDate("15/04/2015"), false));
		myDiscRentals.RentalsLogbook.add(new RentalInstance(true, 1, "Gary Stew", "2321046442", ConvertStringToDate("20/04/2015"), false));
		//Games
		myDiscRentals.RentalsLogbook.add(new RentalInstance(false, 3, "John Harrison", "2118956989", ConvertStringToDate("24/04/2015"), true));
		myDiscRentals.RentalsLogbook.add(new RentalInstance(false, 2, "Xatzimtroglou Ioannis", "21056462314", ConvertStringToDate("02/05/2015"), true));
	}
	
	public static void PrintMenu(){
		System.out.println("\n1. View available movies");
		System.out.println("2. View available games");
		System.out.println("3. View rentals history");
		System.out.println("4. Add new item");
		System.out.println("0. Exit");
	}
	
	public static void DisplayDiscRentals(DiscRentals myDiscRentals, DiscShelves myMovieShelves, DiscShelves myGameShelves){
		System.out.println();
		System.out.println("Which history would you like to view?");
		System.out.println("1. Movies");
		System.out.println("2. Games");
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String historyAnswer = in.nextLine();
		
		if (Integer.parseInt(historyAnswer) == 1){
			DisplayRentalRecords(true, myDiscRentals, myMovieShelves);
			int discToReturn=ReadInt("Which item do you want to return: ");
			if (myDiscRentals.get(discToReturn).ongoing){
				if(PrintReturnDiscConfirmation()==1){
				ReturnDisc(myMovieShelves,myDiscRentals.get(discToReturn).rentedIndex);
				myDiscRentals.get(discToReturn).ongoing=false;
				PrintReturnCost(myDiscRentals, discToReturn);
				}
			}
			else System.out.println("This item is already return!");
			
		}
		
		else if (Integer.parseInt(historyAnswer) == 2){
			DisplayRentalRecords(false, myDiscRentals, myGameShelves);
			int discToReturn=ReadInt("Which item do you want to return: ");
			if (myDiscRentals.get(discToReturn).ongoing){
				if(PrintReturnDiscConfirmation()==1){
				ReturnDisc(myGameShelves,myDiscRentals.get(discToReturn).rentedIndex);
				myDiscRentals.get(discToReturn).ongoing=false;
				PrintReturnCost(myDiscRentals, discToReturn);
				}
			}
			else System.out.println("This item has already been returned!");
			
		}
		
	}
	public static void DisplayRentalRecords(boolean isMovie, DiscRentals myDiscRentals, DiscShelves myDiscShelves){
		System.out.println();
		System.out.println("Here is the rental history:");
		for(int i=1; i<myDiscRentals.size(); i++){
			if (isMovie == myDiscRentals.get(i).isMovie()){
				System.out.println("ID:"+i+" "+"["+myDiscRentals.get(i).getStatus()+"] cost:"+myDiscRentals.get(i).CalculateCost()+", "+myDiscShelves.get(myDiscRentals.get(i).rentedIndex).getTitle()+", "+myDiscRentals.get(i).returnDetails());
			}
		}
	}
	
	public static void DisplayDiscDetails(DiscShelves shelf){
		System.out.println("");
		for(int i=1; i<shelf.size(); i++){
			System.out.println(i+". "+shelf.get(i).returnDetails());
		}
	}
	public static void DisplayDiscShortDetails(DiscShelves shelf, String medium){
		System.out.println("");
		System.out.println("Here are all the available items:");
		for(int i=1; i<shelf.size(); i++){
			if (shelf.get(i).medium == medium){
				if (shelf.get(i).availableCopies>0) System.out.println(i+". "+shelf.get(i).returnShortDetails());
			}
		}
	}

	public static void CreateNewRental(boolean isMovie, int movieIndex, DiscRentals myDiscRentals, boolean weekly) throws Exception{
		String rName = ReadString("Enter renter full name: ");
		String rPhone = ReadString("Enter renter phone number: ");
		Date rDate = askDate();
		myDiscRentals.RentalsLogbook.add(new RentalInstance(isMovie, movieIndex, rName, rPhone, rDate, weekly));
	}
	public static void CreateNewDisc(DiscShelves myDiscShelves, DiscShelves myGameShelves){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println();
		System.out.println("Add new movie or game?");
		System.out.println("1. Movie");
		System.out.println("2. Game");
		String answer = in.nextLine();
		if (Integer.parseInt(answer) == 1){
			String medium = DvdOrBlu();
			String title = ReadString("Enter title: ");
			int year = ReadInt("Enter production year: ");
			String company = ReadString("Enter production company: ");
			String category = ReadString("Enter categories: ");
			String director = ReadString("Enter director: ");
			String writer = ReadString("Enter writer: ");
			String actors = ReadString("Enter actors: ");
			double duration = ReadDouble("Enter duration in minutes: ");
			int copies = ReadInt("Enter amount of copies: ");
			boolean isWeekly = false;
			if (medium.equals("dvd")) isWeekly = DecideWeekly();
			myDiscShelves.DiscShelves.add(new MovieDisc(medium, title, year, company, category, director, writer, actors, duration,copies, isWeekly));
		}
		else if (Integer.parseInt(answer) == 2) {
			String medium = DecideConsole();
			String title = ReadString("Enter title: ");
			int year = ReadInt("Enter production year: ");
			String company = ReadString("Enter production company: ");
			String category = ReadString("Enter categories: ");
			int copies = ReadInt("Enter amound of copies: ");
			myGameShelves.DiscShelves.add(new GameDisc(medium, title, year, company, category, copies, true));
		}
	}
	public static void ReturnDisc(DiscShelves myDiscShelf, int discIndex){
		myDiscShelf.get(discIndex).OneMoreAvailable();
	}
	
	
	public static void PrintReturnCost(DiscRentals myDiscRentals, int discToReturn){
		System.out.println();
		System.out.println("Thank you for returning this item!");
		double cost = myDiscRentals.get(discToReturn).CalculateCost();
		System.out.println("The cost is: "+ cost);
	}
	public static void PrintCommandNotFound(){
		System.out.println();
		System.out.println("I don't know that command");
	}
	public static void PrintMovieDetailsPrompt(){
		System.out.println();
		System.out.print("Display details for which movie?: ");
	}
	public static void PrintGameDetailsPrompt(){
		System.out.println();
		System.out.print("Display details for which game?: ");
	}
	public static void PrintMovieRentConfirmation(){
		System.out.println();
		System.out.println("Do you want to rent this movie?");
		System.out.println("1. Yes");
		System.out.println("0. No");
	}
	public static void PrintGameRentConfirmation(){
		System.out.println();
		System.out.println("Do you want to rent this game?");
		System.out.println("1. Yes");
		System.out.println("0. No");
	}
	public static int PrintReturnDiscConfirmation(){
		System.out.println();
		System.out.println("Are you sure you want to return this item?");
		System.out.println("1. Yes");
		System.out.println("0. No");
		return ReadInt("");
	}
	
	public static String ReadString(String DisplayText){
		Scanner in = new Scanner(System.in);
		System.out.print(DisplayText);
		return in.nextLine();
	}
	public static int ReadInt(String DisplayText){
		Scanner in = new Scanner(System.in);
		System.out.print(DisplayText);
		return in.nextInt();
	}
	public static double ReadDouble(String DisplayText){
		Scanner in = new Scanner(System.in);
		System.out.print(DisplayText);
		return in.nextDouble();
	}
	
	public static Date ReadDate(String DisplayText) throws Exception{
		Scanner in = new Scanner(System.in);
		System.out.print(DisplayText);
		return ConvertStringToDate(in.nextLine());
	}
	public static Date ConvertStringToDate(String dateString) throws Exception{
		String dateFormat = "dd/MM/yyyy";
		return new SimpleDateFormat(dateFormat).parse(dateString);
	}
	public static Date askDate() throws Exception{
		System.out.println("Was this rented today?");
		System.out.println("1. Yes");
		System.out.println("0. No");
		if(ReadInt("")==1) return new Date();
		else return ReadDate("Enter date (dd/MM/yyyy): ");
	}
	
	public static String DvdOrBlu(){
		System.out.println();
		System.out.println("DVD or BluRay?");
		System.out.println("1. DVD");
		System.out.println("2. BluRay");
		int oneOrTwo = ReadInt("");
		String medium = "dvd";
		if (oneOrTwo==2) medium = "blu";
		return medium;
	}
	public static String DecideConsole(){
		System.out.println();
		System.out.println("Which console?");
		System.out.println("1. Xbox");
		System.out.println("2. Playstation");
		System.out.println("3. Nintendo");
		int number = ReadInt("");
		String medium = "";
		if (number==1) medium = "xbox";
		else if (number == 2) medium = "playstation";
		else if (number == 3) medium = "nintendo";
		return medium;
	}
	public static boolean DecideWeekly(){
		System.out.println();
		System.out.println("Is this charged weekly or daily?");
		System.out.println("1. Weekly");
		System.out.println("2. Daily");
		String answer = ReadString("");
		if (answer.equals("1")) return true;
		else return false;
	}

}