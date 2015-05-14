
public class MovieDisc extends Disc{
	//initialize variables
	String director;
	String writer;
	String actors;
	double duration;

	public MovieDisc(){
		super();
	}
	
	public MovieDisc(String medium, String title, int year, String company, String categories, String director, String writer, String actors, double duration,int availableCopies, boolean isWeekly){
		super(medium, title, year, company, categories, availableCopies, isWeekly);
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.duration = duration;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public String getActors() {
		return actors;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public String getWeekly(boolean isWeekly){
		if (isWeekly) return "Weekly";
		else return "Daily";
	}
	
	public String returnDetails(){
		return getTitle() +", "+getCategories()+", "+getYear()+", "+getCompany()+", "+getDirector()+", "+getWriter()+", "+getActors()+", "+getDuration() + ", "+getWeekly(isWeekly);
	}
	
}
