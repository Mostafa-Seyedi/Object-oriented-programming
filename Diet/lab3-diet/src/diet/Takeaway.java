package diet;

import java.util.*;


/**
 * Represents a takeaway restaurant chain.
 * It allows managing restaurants, customers, and orders.
 */
public class Takeaway {

	/**
	 * Constructor
	 * @param food the reference {@link Food} object with materials and products info.
	 */
	Food food;

	HashMap<String, Restaurant> returants = new HashMap<>();
	public Takeaway(Food food){
		this.food = food; 
	}

	/**
	 * Creates a new restaurant with a given name
	 *
	 * @param restaurantName name of the restaurant
	 * @return the new restaurant
	 */
	Map<String , Restaurant> retaurants = new TreeMap<>(); 

	public Restaurant addRestaurant(String restaurantName) {
		Restaurant restaurant = new Restaurant(restaurantName); 
		returants.put(restaurantName, restaurant); 
		return restaurant;
	}

	/**
	 * Retrieves the names of all restaurants
	 *
	 * @return collection of restaurant names
	 */
	public Collection<String> restaurants() {
		return returants.keySet();
	}

	/**
	 * Creates a new customer for the takeaway
	 * @param firstName first name of the customer
	 * @param lastName	last name of the customer
	 * @param email		email of the customer
	 * @param phoneNumber mobile phone number
	 *
	 * @return the object representing the newly created customer
	 */
	HashMap<String, Customer> customers = new HashMap<>(); 
	public Customer registerCustomer(String firstName, String lastName, String email, String phoneNumber) {
		Customer customer = new Customer(firstName, lastName, email, phoneNumber); 
		customers.put(customer.toString(), customer); 
		return customer;
	}

	/**
	 * Retrieves all registered customers
	 *
	 * @return sorted collection of customers
	 */

	public Collection<Customer> customers(){
		return customers.values().stream().sorted(Comparator.comparing(Customer::sortField)).toList();
	}


	/**
	 * Creates a new order for the chain.
	 *
	 * @param customer		 customer issuing the order
	 * @param restaurantName name of the restaurant that will take the order
	 * @param time	time of desired delivery
	 * @return order object
	 */
	Set<Order> orders = new HashSet<>(); 
	public Order createOrder(Customer customer, String restaurantName, String time) {
		Order order = new Order(customer, returants.get(restaurantName), time); 
		orders.add(order); 	
		returants.get(restaurantName).addOrder(order);
		return order;
	}

	/**
	 * Find all restaurants that are open at a given time.
	 *
	 * @param time the time with format {@code "HH:MM"}
	 * @return the sorted collection of restaurants
	 */
	public Collection<Restaurant> openRestaurants(String time){
		return returants.values().stream().filter(r -> r.isOpenAt(time)).
		sorted(Comparator.comparing(Restaurant::getName)).toList();
	}
}
