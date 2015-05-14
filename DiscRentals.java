import java.util.ArrayList;


public class DiscRentals {
	
	public ArrayList<RentalInstance> RentalsLogbook = new ArrayList<RentalInstance>();
	
	public RentalInstance get(int i){
		return RentalsLogbook.get(i);
	}
	
	public int size(){
		return RentalsLogbook.size();
	}
}
