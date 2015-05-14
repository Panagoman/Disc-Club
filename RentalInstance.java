import java.text.SimpleDateFormat;
import java.util.*;

public class RentalInstance {
	
	int rentedIndex;
	String RenterName;
	String RenterPhone;
	boolean isMovie;
	Date rentedDate;
	SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
	boolean ongoing=true;
	boolean isWeekly;
	
	public RentalInstance(){
	}
	
	public RentalInstance(boolean isItMovie, int index, String name, String phone, Date date, boolean weekly){
		rentedIndex = index;
		RenterName = name;
		RenterPhone = phone;
		isMovie = isItMovie;
		rentedDate = date;
		isWeekly = weekly;
	}
	
	public String returnDetails(){
		return RenterName +", "+ RenterPhone + ", " + ft.format(rentedDate);
	}
	
	public int returnDiscIndex(){
		return rentedIndex;
	}
	
	public boolean isMovie(){
		return isMovie;
	}
	
	public String getStatus(){
		if (ongoing) return "ONGOING";
		else return "RETURNED";
	}
	
	public long CalculateTimeDifference(){
		Date dateNow = new Date();
		return dateNow.getTime() - rentedDate.getTime();
	}
	
	public double CalculateCost(){
		long numbers = CalculateTimeDifference();
		long days = numbers / 1000 / 60 / 60 / 24;
		if (isWeekly) { days = days / 7;}
		return days +1;
	}
}
