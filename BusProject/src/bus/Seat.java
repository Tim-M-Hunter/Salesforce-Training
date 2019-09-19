package bus;

/**
 * A seat on a bus. Contains information about the seat type and whether it's reserved.
 */
public class Seat {

	/** Unique ID */
	private String id;
	/** The type of Seat */
	private SeatType type;
	/** Whether it is reserved */
	private boolean reserved;
	
	/**
	 * Constructor.
	 * @param id A unique ID.
	 * @param type The type of seat.
	 */
	public Seat(String id, SeatType type) {
		this.id = id;
		this.type = type;
	}

	/**
	 * Get the ID.
	 * @return The ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the type of seat.
	 * @return The type of seat.
	 */
	public SeatType getType() {
		return type;
	}

	/**
	 * Whether it is reserved.
	 * @return True if reserved, false otherwise.
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * Set the reserved status.
	 * @param reserved True if reserved, false otherwise.
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	
	@Override
	public String toString() {
		return "Seat[id=" + id + ", type=" + type + ", reserved=" + reserved + "]";
	}

	
}
