package bus;

import java.util.UUID;

/**
 * A Rocket model bus.
 */
public class RocketBus extends Bus {

	/**
	 * Constructor.
	 */
	public RocketBus() {
		super("R-" + UUID.randomUUID());
		setBaggageFee(7.0);
		createSeats(3, 5, 24);
		setSeatFares(50.0, 35.0, 20.0);
	}

	@Override
	public void displaySeatMap() {
		log("** Rocket Bus Seat Map **");
		log("----------------------------");
		log("|  P  B  B C C C C C C C C |");
		log("|  P  B  B C C C C C C C C |");
		log("|                          |");
		log("|  P     B C C C C C C C C |");
		log("-----==---------------------");
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
