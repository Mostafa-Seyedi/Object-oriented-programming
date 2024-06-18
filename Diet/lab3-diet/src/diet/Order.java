package diet;

import java.util.HashMap;
import java.util.logging.Handler;

/**
 * Represents and order issued by an {@link Customer} for a {@link Restaurant}.
 *
 * When an order is printed to a string is should look like:
 * <pre>
 *  RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
 *  	MENU_NAME_1->MENU_QUANTITY_1
 *  	...
 *  	MENU_NAME_k->MENU_QUANTITY_k
 * </pre>
 */

public class Order {
	Customer customer; 
	Restaurant restaurant; 
	private String time;

	private OrderStatus os; 
	private PaymentMethod pm;  



	public Order(Customer customer, Restaurant restaurant, String time) {
		this.customer = customer;
		this.restaurant = restaurant;
		this.time = this.restaurant.timeHandler(time); 

		this.os = OrderStatus.ORDERED; 
		this.pm = PaymentMethod.CASH;

	}
	

		
	public OrderStatus getOs() {
		return os;
	}

	public void setOs(OrderStatus os) {
		this.os = os;
	}

	public PaymentMethod getPm() {
		return pm;
	}

	public void setPm(PaymentMethod pm) {
		this.pm = pm;
	}
	


	/**
	 * Possible order statuses
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED
	}

	/**
	 * Accepted payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD
	}

	/**
	 * Set payment method
	 * @param pm the payment method
	 */
	public void setPaymentMethod(PaymentMethod pm) {
		this.pm = pm;
	}

	/**
	 * Retrieves current payment method
	 * @return the current method
	 */
	public PaymentMethod getPaymentMethod() {
		return pm;
	}

	/**
	 * Set the new status for the order
	 * @param os new status
	 */
	public void setStatus(OrderStatus os) {
		this.os = os;
	}

	/**
	 * Retrieves the current status of the order
	 *
	 * @return current status
	 */
	public OrderStatus getStatus() {
		return os;
	}

	/**
	 * Add a new menu to the order with a given quantity
	 *
	 * @param menu	menu to be added
	 * @param quantity quantity
	 * @return the order itself (allows method chaining)
	 */
	HashMap<String, Integer> orderMenu = new HashMap<>();
	public Order addMenus(String menu, int quantity) {
		orderMenu.put(menu, quantity); 
		return this;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = restaurant.getName() + ", "+ customer.toString()+" : (" + this.time + "):\n";
		for(var order : orderMenu.entrySet()){ 
			result += "\t" + order.getKey() + "->" + order.getValue() + "\n"; 
		}

		// trim for eliminating the last redundant \n
		return result.trim();
	}

	public String getSortFields(){ 
		return restaurant.getName() + customer.toString() + this.time; 
	}

	
	
	
}
