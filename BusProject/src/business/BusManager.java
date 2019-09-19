package business;

import java.util.ArrayList;

import bus.Bus;
import bus.DeluxeBus;
import bus.RocketBus;

/**
 * Manages created Bus objects.
 */
public class BusManager {

	/** Singleton instance */
	private static BusManager INSTANCE = null;
	/** The collection of bus objects */
	private ArrayList<Bus> buses;
	
	/**
	 * Private constructor.
	 */
	private BusManager() {
		buses = new ArrayList<>();
	}
	
	/**
	 * Returns the singleton instance of the class.
	 * @return The BusManager object.
	 */
	public static BusManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new BusManager();
		}
		
		return INSTANCE;
	}
	
	/**
	 * Creates a new RocketBus and adds it to the collection.
	 * @return The created bus.
	 */
	public RocketBus createRocketBus() {
		RocketBus bus = new RocketBus();
		buses.add(bus);
		return bus;
	}
	
	/**
	 * Creates a new DeluxeBus and adds it to the collection.
	 * @return The created bus.
	 */
	public DeluxeBus createDeluxeBus() {
		DeluxeBus bus = new DeluxeBus();
		buses.add(bus);
		return bus;
	}
	
	/**
	 * Gets an ArrayList of the RocketBus objects.
	 * @return The collection of RocketBus.
	 */
	public ArrayList<Bus> getRocketBusList() {
		ArrayList<Bus> list = new ArrayList<>();
		for(Bus bus : buses) {
			if(bus.getClass().equals(RocketBus.class)) {
				list.add(bus);
			}
		}
		return list;
	}
	
	/**
	 * Gets an ArrayList of the DeluxeBus objects.
	 * @return The collection of DeluxeBus.
	 */
	public ArrayList<Bus> getDeluxeBusList() {
		ArrayList<Bus> list = new ArrayList<>();
		for(Bus bus : buses) {
			if(bus.getClass().equals(DeluxeBus.class)) {
				list.add(bus);
			}
		}
		return list;
	}
	
}
