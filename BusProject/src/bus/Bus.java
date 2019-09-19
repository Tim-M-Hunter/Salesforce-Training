package bus;

import java.util.ArrayList;

/**
 * A bus within the system. Contains information regarding seat costs and baggage handling.
 */
public abstract class Bus {

	/** Unique ID */
	private String id;
	/** Collection of Bus Seats */
	private ArrayList<Seat> seats;
	/** The cost of baggage handling */
	private double baggageFee;
	/** Price of premium seats */
	private double premiumFare;
	/** Price of business seats */
	private double businessFare;
	/** Price of coach seats */
	private double coachFare;
	
	/**
	 * Constructor.
	 * @param id The unique ID.
	 */
	public Bus(String id) {
		this.id = id;
	}
	
	/**
	 * Displays a seat map of the bus.
	 */
	public abstract void displaySeatMap();
	
	/**
	 * Creates and sets the array list of Seat objects.
	 * @param premium Number of premium seats.
	 * @param business Number of business seats.
	 * @param coach Number of coach seats.
	 * @return The created array list.
	 */
	protected void createSeats(int premium, int business, int coach) {
		ArrayList<Seat> seatList = new ArrayList<Seat>();
		for(int i = 0; i < premium; i++) {
			seatList.add(new Seat("P-" + i, SeatType.PREMIUM));
		}
		for(int i = 0; i < business; i++) {
			seatList.add(new Seat("B-" + i, SeatType.BUSINESS));
		}
		for(int i = 0; i < coach; i++) {
			seatList.add(new Seat("C-" + i, SeatType.COACH));
		}
		seats = seatList;
	}
	
	/**
	 * Set the fares associated to the different seat types.
	 * @param premiumFare The cost of Premium class seats.
	 * @param businessFare The cost of Business class seats.
	 * @param coachFare The cost of Coach class seats.
	 */
	public void setSeatFares(double premiumFare, double businessFare, double coachFare) {
		this.premiumFare = premiumFare;
		this.businessFare = businessFare;
		this.coachFare = coachFare;
	}
	
	/**
	 * Checks if there are any available seats.
	 * @return True if seats are available, false otherwise.
	 */
	public boolean isSeatingAvailable() {
		return !getAllAvailableSeats().isEmpty();
	}
	
	/**
	 * Checks if a seat is available of the given type.
	 * @param type The type of seat.
	 * @return True if seat type is available, false otherwise.
	 */
	public boolean isSeatingAvailable(SeatType type) {
		switch(type) {
		case PREMIUM:
			return !getAvailablePremiumSeats().isEmpty();
		case BUSINESS:
			return !getAvailableBusinessSeats().isEmpty();
		default:
			return !getAvailableCoachSeats().isEmpty();
		}
	}
	
	/**
	 * Gets the first available seat of the given seat type.
	 * @param type The type of seat to get.
	 * @return The first available seat.
	 */
	public Seat getFirstAvailableSeat(SeatType type) {
		
		switch(type) {
			case PREMIUM:
				return getAvailablePremiumSeats().get(0);
			case BUSINESS:
				return getAvailableBusinessSeats().get(0);
			default:
				return getAvailableCoachSeats().get(0);
		}
	}
	
	/**
	 * A list of all unreserved seats.
	 * @return Collection of available seats.
	 */
	public ArrayList<Seat> getAllAvailableSeats() {
		ArrayList<Seat> freeSeats = new ArrayList<>();
		seats.forEach(a -> {if(!a.isReserved()) freeSeats.add(a);});
		return freeSeats;
	}
	
	/**
	 * A list of all unreserved premium class seats;
	 * @return Collection of available premium seats.
	 */
	public ArrayList<Seat> getAvailablePremiumSeats() {
		ArrayList<Seat> freeSeats = new ArrayList<>();
		seats.forEach(a -> {if(a.getType().equals(SeatType.PREMIUM) && !a.isReserved()) freeSeats.add(a);});
		return freeSeats;
	}
	
	/**
	 * A list of all unreserved business class seats.
	 * @return Collection of available business seats.
	 */
	public ArrayList<Seat> getAvailableBusinessSeats() {
		ArrayList<Seat> freeSeats = new ArrayList<>();
		seats.forEach(a -> {if(a.getType().equals(SeatType.BUSINESS) && !a.isReserved()) freeSeats.add(a);});
		return freeSeats;
	}
	
	/**
	 * A list of all unreserved coach class seats.
	 * @return Collection of available coach seats.
	 */
	public ArrayList<Seat> getAvailableCoachSeats() {
		ArrayList<Seat> freeSeats = new ArrayList<>();
		seats.forEach(a -> {if(a.getType().equals(SeatType.COACH) && !a.isReserved()) freeSeats.add(a);});
		return freeSeats;
	}
	
	/**
	 * Get the Bus ID.
	 * @return The Bus ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the ArrayList of Seats associated to the Bus.
	 * @return The ArrayList of Seats;
	 */
	public ArrayList<Seat> getSeats() {
		return seats;
	}

	/**
	 * Get the baggage fee.
	 * @return The baggage fee.
	 */
	public double getBaggageFee() {
		return baggageFee;
	}

	/**
	 * Set the baggage fee.
	 * @param baggageFee The baggage fee.
	 */
	public void setBaggageFee(double baggageFee) {
		this.baggageFee = baggageFee;
	}

	/**
	 * Get the cost of premium seats.
	 * @return the premiumFare
	 */
	public double getPremiumFare() {
		return premiumFare;
	}

	/**
	 * Get the cost of business seats;
	 * @return the businessFare
	 */
	public double getBusinessFare() {
		return businessFare;
	}

	/**
	 * Get the cost of coach seats;
	 * @return the coachFare
	 */
	public double getCoachFare() {
		return coachFare;
	}
}
