package diet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant class with given opening times and a set of menus.
 */
public class Restaurant {
	
	/**
	 * retrieves the name of the restaurant.
	 *
	 * @return name of the restaurant
	 */
	private String name;

	
	public Restaurant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * Define opening times.
	 * Accepts an array of strings (even number of elements) in the format {@code "HH:MM"},
	 * so that the closing hours follow the opening hours
	 * (e.g., for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00,
	 * arguments would be {@code "08:15", "14:00", "19:00", "00:00"}).
	 *
	 * @param hm sequence of opening and closing times
	 */

	 // String ... name is something like an array but it can take more than one
	 // value at a time
	 TreeMap<Integer, Integer> hours = new TreeMap<>(); 
	public void setHours(String ... hm) {
		for(int i = 0 ; i < hm.length ; i += 2){ 
			Integer start = parsTime(hm[i]); 
			Integer end = parsTime(hm[i+1]); 
			hours.put(start, end); 
		}

	}

	public String timeHandler(String time){ 
		int timeInt = parsTime(time);
		if(isOpenAt(time)){ 
			return convertTimeFormat(time); 
		}  Integer nextHour = hours.ceilingKey(timeInt);

		if(nextHour == null){
			nextHour = hours.firstKey(); 
		 }
		 return convertTimeFormat(nextHour); 




	}

	public int parsTime(String time){ 
		String[] parts = time.split(":");
		int hour = Integer.parseInt(parts[0]); 
		int minute = Integer.parseInt(parts[1]);
		return hour * 60 + minute; 
	}

	public String convertTimeFormat(int time){ 
		int hour = time / 60;  
		int minute = time % 60;
		return String.format("%02d:%02d", hour, minute); 
	}

	public String convertTimeFormat(String time){ 
		String[] parts = time.split(":");
		int hour = Integer.parseInt(parts[0]); 
		int minute = Integer.parseInt(parts[1]);
		return String.format("%02d:%02d", hour, minute); 
	}

	/**
	 * Checks whether the restaurant is open at the given time.
	 *
	 * @param time time to check
	 * @return {@code true} is the restaurant is open at that time
	 */
	public boolean isOpenAt(String time){
		int intTime = parsTime(time); 
		for(var hour : hours.entrySet()){
			if(intTime >= hour.getKey() && intTime <= hour.getValue()){ 
				return true; 
			}	 
		}
		return false;
	}

	/**
	 * Adds a menu to the list of menus offered by the restaurant
	 *
	 * @param menu	the menu
	 */
	HashMap<String, Menu> menus = new HashMap<>(); 
	public void addMenu(Menu menu) {
	}

	/**
	 * Gets the restaurant menu with the given name
	 *
	 * @param name	name of the required menu
	 * @return menu with the given name
	 */
	public Menu getMenu(String name) {
		return menus.get(name);
	}

	/**
	 * Retrieve all order with a given status with all the relative details in text format.
	 *
	 * @param status the status to be matched
	 * @return textual representation of orders
	 */
	List<Order> orders = new ArrayList<>(); 
	
	public void addOrder(Order order){ 
		orders.add(order); 
	}

	public String ordersWithStatus(OrderStatus status) {
		String result = ""; 
		var x = orders.stream().filter(o -> o.getOs() == status)
		.sorted(Comparator.comparing(Order::getSortFields)).toList();
		for (Order order : x) {
			result += order.toString() + "\n"; 
		}
		return result.trim(); 
	}
}
