/* 30/4/2015
 * @Author: Panagos Ioannis
 * 3140308
 */

public class Disc {
	
	public String title;
	public int year;
	public String company;
	public String categories;
	public String medium;
	public int availableCopies;
	public boolean isWeekly;
	
	public Disc() {
	}
	
	public Disc(String medium, String title, int year, String company, String categories, int availableCopies,boolean isWeekly){
		this.title = title;
		this.year = year;
		this.company = company;
		this.categories = categories;
		this.medium = medium;
		this.availableCopies = availableCopies;
		this.isWeekly = isWeekly;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getCategories() {
		return categories;
	}
	
	public void print() {
		System.out.println(getTitle() +", "+getCategories()+", "+getYear()+", "+getCompany());
	}
	
	public String returnDetails(){
		return "tite: "+getTitle() +", "+getCategories()+", "+getYear()+", "+getCompany();
	}
	
	public String returnShortDetails(){
		return getTitle() +", "+getYear()+", available: " + availableCopies;
	}
	
	public void OneLessAvailable(){
		if (availableCopies>0) availableCopies-=1;
	}
	
	public void OneMoreAvailable(){
		availableCopies+=1;
	}
}
