package business;

import java.util.UUID;

/**
 * A Customer in the system.
 */
public class Customer {

	/** Unique id */
	private String id;
	/** Name of customer */
	private String name;
	/** Home address */
	private String address;
	/** Credit card number */
	private int creditCard;
	
	/**
	 * Constructor.
	 * @param name Name of the customer.
	 * @param address Home address.
	 * @param creditCard Credit card number.
	 */
	public Customer(String name, String address, int creditCard) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.setAddress(address);
		this.setCreditCard(creditCard);
	}


	/**
	 * Get the ID.
	 * @return The ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name.
	 * @param name The name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the address.
	 * @return The address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address.
	 * @param address The address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the credit card number.
	 * @return The credit card number.
	 */
	public int getCreditCard() {
		return creditCard;
	}

	/**
	 * Set the credit card number.
	 * @param creditCard The credit card number.
	 */
	public void setCreditCard(int creditCard) {
		this.creditCard = creditCard;
	}

}
