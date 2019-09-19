package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import bus.Bus;
import bus.Seat;

/**
 * Manages created Reservations.
 */
public class ReservationManager {

	/** Singleton instance */
	private static ReservationManager INSTANCE = null;
	/** Holds all reservations */
	private HashMap<String, Reservation> reservations;
	
	/**
	 * Private Constructor.
	 */
	private ReservationManager() {
		reservations = new HashMap<>();
	}
	
	/**
	 * Returns the instance of the ReservationManager.
	 * @return The ReservationManager.
	 */
	public static ReservationManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ReservationManager();
		}
		
		return INSTANCE;
	}
	
	/**
	 * Add a Reservation to the collection.
	 * @param reservation The reservation.
	 */
	public void addReservation(Reservation reservation) {
		reservation.getSeat().setReserved(true);
		reservations.put(reservation.getId(), reservation);
	}
	
	/**
	 * Create a new reservation and add it to the collection.
	 * @param customer The customer.
	 * @param date The date.
	 * @param numberOfBags The number of bags.
	 * @param bus The bus.
	 * @param seat The seat on the bus.
	 * @return The created reservation.
	 */
	public Reservation createReservation(Customer customer, Date date, int numberOfBags, Bus bus, Seat seat) {
		Reservation reservation = new Reservation(customer, date, numberOfBags, bus, seat);
		seat.setReserved(true);
		reservations.put(reservation.getId(), reservation);
		return reservation;
	}
	
	/**
	 * Removes the given reservation.
	 * @param reservation The reservation to remove.
	 */
	public void removeReservation(Reservation reservation) {
		reservation.getSeat().setReserved(false);
		reservations.remove(reservation.getId());
	}
	
	/**
	 * Returns a reservation with given id.
	 * @param reservationId The reservation id.
	 * @return The found reservation.
	 */
	public Reservation findReservationById(String reservationId) {
		return reservations.get(reservationId);
	}
	
	/**
	 * Returns a list of reservations associated to the given customer name.
	 * @param customerName The customer name.
	 * @return The collection of reservations.
	 */
	public ArrayList<Reservation> findReservationsByCustomerName(String customerName) {
		ArrayList<Reservation> found = new ArrayList<>();
		reservations.forEach((a, b) -> {if(customerName.equals(b.getCustomer().getName())) found.add(b);});
		return found;
	}
	
	/**
	 * Returns a list of reservations associated to the given customer ID.
	 * @param customerId The customer ID.
	 * @return The collection of reservations.
	 */
	public ArrayList<Reservation> findReservationsByCustomerId(String customerId) {
		ArrayList<Reservation> found = new ArrayList<>();
		reservations.forEach((a, b) -> {if(customerId.equals(b.getCustomer().getId())) found.add(b);});
		return found;
	}
	
	/**
	 * Calculates the total cost of the reservation based on the baggage fee * number of bags + the cost of fare.
	 * @param reservation The reservation to calculate.
	 * @return The total cost of the reservation.
	 */
	public double calculateTotalCost(Reservation reservation) {
		double totalCost = 0.0;
		totalCost += reservation.getBus().getBaggageFee() * reservation.getNumOfBags();
		switch(reservation.getSeat().getType()) {
			case PREMIUM:
				totalCost += reservation.getBus().getPremiumFare();
				break;
			case BUSINESS:
				totalCost += reservation.getBus().getBusinessFare();
				break;
			case COACH:
				totalCost += reservation.getBus().getCoachFare();
		}
		return totalCost;
	}
}
