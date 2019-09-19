package bus;

import java.util.UUID;

/**
 * A Deluxe model bus.
 */
public class DeluxeBus extends Bus {

	/**
	 * Constructor.
	 */
	public DeluxeBus() {
		super("D-" + UUID.randomUUID());
		setBaggageFee(15.0);
		createSeats(9, 11, 15);
		setSeatFares(100.0, 70.0, 50.0);
	}
	
	@Override
	public void displaySeatMap() {
		log("** Deluxe Bus Seat Map **");
		log("----------------------------------");
		log("|  P  P  P  B  B  B  B C C C C C |");
		log("|  P  P  P  B  B  B  B C C C C C |");
		log("|                                |");
		log("|  P  P  P     B  B  B C C C C C |");
		log("-----------==---------------------");
		log();
		log("P = Premium Seat");
		log("B = Business Seat");
		log("C = Coach Seat");
		log();
	}
	
	/**
	 * Shorthand empty println function.
	 */
	private static void log() {
		log(null);
	}
	
	/**
	 * Shorthand println function using given content.
	 * @param a The objects to println.
	 */
	private static void log(Object a) {
		if(a == null) {
			System.out.println();
		} else {
			System.out.println(a);
		}
	}

}
