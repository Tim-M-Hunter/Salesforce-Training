package business;

import java.util.Date;
import java.util.UUID;

import bus.Bus;
import bus.Seat;

/**
 * Makes reservations of bus seats for customers.
 */
public class Reservation {

	/** Unique ID */
	private String id;
	/** The customer making the reservation */
	private Customer customer;
	/** The date of the reservation */
	private Date date;
	/** The bus */
	private Bus bus;
	/** The seat on the bus */
	private Seat seat;
	/** How many bags the customer checks */
	private int numOfBags;
	
	/**
	 * Constructor.
	 * @param customer The customer.
	 * @param date The date of the reservation.
	 * @param bus The bus.
	 * @param seat The seat on the bus.
	 */
	public Reservation(Customer customer, Date date, int numOfBags, Bus bus, Seat seat) {
		this.id = UUID.randomUUID().toString();
		this.customer = customer;
		this.setDate(date);
		this.setNumOfBags(numOfBags);
		this.setBus(bus);
		this.setSeat(seat);
	}

	/**
	 * Get the ID.
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the customer.
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Get the date of the reservation.
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the date of the reservation.
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Get the number of bags being checked.
	 * @return the numOfBags
	 */
	public int getNumOfBags() {
		return numOfBags;
	}

	/**
	 * Set the number of bags being checked.
	 * @param numOfBags the numOfBags to set
	 */
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}

	/**
	 * Get the bus.
	 * @return the bus
	 */
	public Bus getBus() {
		return bus;
	}

	/**
	 * Set the bus.
	 * @param bus the bus to set
	 */
	public void setBus(Bus bus) {
		this.bus = bus;
	}

	/**
	 * Get the seat on the bus.
	 * @return the seat
	 */
	public Seat getSeat() {
		return seat;
	}

	/**
	 * Set the seat on the bus.
	 * @param seat the seat to set
	 */
	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Reservation[customer=" + customer.getName() + ", date=" + date + ", busId=" + bus.getId() 
			+ ", seat=" + seat.getId() + ", bags=" + numOfBags +"]";
	}
	
}
