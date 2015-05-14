/* 30/4/2015
 * @Author: Panagos Ioannis
 * 3140308
 */

import java.util.*;

public class DiscShelves {
	//ArrayList with items
	public ArrayList<Disc> DiscShelves = new ArrayList<Disc>();
	
	public Disc get(int i){
		return DiscShelves.get(i);
	}
	
	public int size(){
		return DiscShelves.size();
	}
}
