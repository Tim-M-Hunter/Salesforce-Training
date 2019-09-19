package _main;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import bus.Bus;
import bus.DeluxeBus;
import bus.RocketBus;
import bus.Seat;
import bus.SeatType;
import business.BusManager;
import business.Customer;
import business.Reservation;
import business.ReservationManager;

/**
 * Starts the system and menu.
 */
public class Main {

	/** Scanner for user input */
	public static Scanner scanner;
	/** Whether the program should keep running */
	public static boolean run = true;
	/** The customer for this transaction */
	public static Customer customer;
	
	/** Handles organization of Bus objects */
	public static BusManager busManager = BusManager.getInstance();
	/** Handles organization of Reservation objects */
	public static ReservationManager reservationManager = ReservationManager.getInstance();
	
	/** Instance of RocketBus for global object commands */
	public static RocketBus rocketBus = new RocketBus();
	/** Instance of DeluxeBus for global object commands */
	public static DeluxeBus deluxeBus = new DeluxeBus();
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		customer = new Customer("Your Name", "Your Address", 123456789);
		
		busManager.createDeluxeBus();
		busManager.createDeluxeBus();
		busManager.createRocketBus();
		busManager.createRocketBus();
		
		while(run) {
			mainMenu();
		}
	}
	
	/**
	 * The main menu of the system.
	 */
	private static void mainMenu() {
		log("** Main Menu **");
		log("What would you like to do? (Enter Number)");
		log("(1) Make Reservation");
		log("(2) View/Change Reservations");
		log("(3) View Rocket Bus Seat Map");
		log("(4) View Deluxe Bus Seat Map");
		log("(5) Exit");
		
		String input = scanner.nextLine();
		log();
		
		switch(input) {
			case "1":
				makeReservationMenu();
				break;
			case "2":
				viewChangeReservationsMenu();
				break;
			case "3":
				rocketBus.displaySeatMap();
				break;
			case "4":
				deluxeBus.displaySeatMap();
				break;
			case "5":
				run = false;
				break;
			default:
				log("Not a valid input.");
		}
	}
	
	/**
	 * Menu system to make a reservation.
	 */
	private static void makeReservationMenu() {
		String input;
		DecimalFormat df = new DecimalFormat("#.00");
		
		Date date;
		int numOfBags;
		Bus bus;
		Seat seat;
		
		Reservation reservation;
		
		log("** Make Reservation **");
		
		dateLoop: while(true) {
			log("What date would you like to make the reservation for? (dd/MM/yyyy)");
			input = scanner.nextLine();
			log();
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
				break dateLoop;
			} catch (ParseException e) {
				log("Not the correct Date format. Please try again.");
				log();
			}
		}
		
		busLoop: while(true) {
			ArrayList<Bus> busList;
			SeatType seatType;
			
			log("What type of bus do you want to take? (Fares: Premium/Business/Coach)");
			log("(1) Rocket Bus ($50/$35/$20)");
			log("(2) Deluxe Bus ($100/$70/$50)");
			input = scanner.nextLine();
			log();
			
			switch(input) {
				case "1":
					busList = busManager.getRocketBusList();
					break;
				case "2":
					busList = busManager.getDeluxeBusList();
					break;
				default:
					log("Not a valid selection.");
					continue busLoop;
			}
			
			log("What type of seat do you want?");
			log("(1) Premium");
			log("(2) Business");
			log("(3) Coach");
			input = scanner.nextLine();
			log();
			
			switch(input) {
				case "1":
					seatType = SeatType.PREMIUM;
					break;
				case "2":
					seatType = SeatType.BUSINESS;
					break;
				case "3":
					seatType = SeatType.COACH;
					break;
				default:
					log("Not a valid selection.");
					continue busLoop;
			}
			
			for(Bus b : busList) {
				if(b.isSeatingAvailable(seatType)) {
					bus = b;
					seat = b.getFirstAvailableSeat(seatType);
					break busLoop;
				}
			}
			
			log("Selected bus type does not have any avaiable seats of selected seat type. Please try a different selection.");
		}
		
		bagLoop: while(true) {
			log("How many bags would you like to check? ($" + df.format(bus.getBaggageFee()) + " per bag)");
			input = scanner.nextLine();
			try {
				numOfBags = Integer.parseInt(input);
				break bagLoop;
			} catch (NumberFormatException e) {
				log("Not a valid number.");
			}
		}
		
		reservation = new Reservation(customer, date, numOfBags, bus, seat);
		
		confirmLoop: while(true) {
			log("Total Cost of Reservation: $" + df.format(reservationManager.calculateTotalCost(reservation)));
			log("Do you want to confirm this reservation?");
			log("(1) Yes, confirm the reservation.");
			log("(2) No, cancel the reservation.");
			input = scanner.nextLine();
			log();
			
			switch(input) {
				case "1":
					reservationManager.addReservation(reservation);
					log("Reservation added.");
					break confirmLoop;
				case "2":
					log("Reservation cancelled.");
					break confirmLoop;
				default:
					log("Not a valid selection.");
					continue confirmLoop;
			}
		}
		
		log();
	}
	
	/**
	 * Menu to view and edit reservations.
	 */
	private static void viewChangeReservationsMenu() {
		String input;
		Reservation selection;
		DecimalFormat df = new DecimalFormat("#.00");
		
		log("** View/Change Reservations **");
		
		taskLoop: while(true) {
			ArrayList<Reservation> resList = reservationManager.findReservationsByCustomerId(customer.getId());
			if(resList.isEmpty()) {
				log("No reservations at this time.");
				log();
				return;
			} else {
				for(int i = 0; i < resList.size(); i++) {
					log("(" +  (i + 1) + ") " + resList.get(i));
				}
			}
			log();
			
			log("Which reservation would you like to change?");
			log("(Enter a number next to a reservation to change it. Enter 'EXIT' or 0 to return to Main Menu.)");
			input = scanner.nextLine();
			log();
			
			if(input.toUpperCase().contentEquals("EXIT") || input.equals("0")) {
				return;
			}
			try {
				int index = Integer.parseInt(input);
				index--;
				if(index < 0 || index >= resList.size()) {
					log("Not a valid selection.");
					continue taskLoop;
				}
				selection = resList.get(index);
				break taskLoop;
			} catch(Exception e) {
				log("Not a valid input.");
				continue taskLoop;
			}
		}
		
		editLoop: while(true) {
			log("How do want to change this reservation?");
			log("(1) Edit the reservation details.");
			log("(2) Cancel the reservation.");
			log("(3) Return to Main Menu.");
			input = scanner.nextLine();
			log();
			
			switch(input) {
				case "1":
					break;
				case "2":
					reservationManager.removeReservation(selection);
					log("Reservation canceled.");
					log();
					return;
				case "3":
					return;
				default:
					log("Not a valid selection.");
					continue editLoop;
			}
			
			dataLoop: while(true) {
				log("Which detail would you like to change?");
				log("(1) Date");
				log("(2) Bus");
				log("(3) Seat");
				log("(4) Finished Editing");
				input = scanner.nextLine();
				log();
				
				switch(input) {
					case "1":
						editDate(selection);
						break;
					case "2":
						editBus(selection);
						break;
					case "3":
						editSeat(selection);
						break;
					case "4":
						log("Total Cost of Reservation: $" + df.format(reservationManager.calculateTotalCost(selection)));
						log();
						break dataLoop;
					default:
						log("Not a valid selection.");
				}
			}
		}
	}
	
	/**
	 * Menu to edit date for given reservation.
	 * @param selection The reservation to edit.
	 */
	private static void editDate(Reservation selection) {
		String input;
		log("Please enter the new date: (dd/MM/yyyy)");
		input = scanner.nextLine();
		log();
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
			selection.setDate(date);
			log("Date successfully changed.");
			log();
		} catch (ParseException e) {
			log("Not the correct Date format. Please try again.");
			log();
		}
	}
	
	/**
	 * Menu to edit bus for given reservation.
	 * @param selection The reservation to edit.
	 */
	private static void editBus(Reservation selection) {
		String input;
		ArrayList<Bus> busList;
		
		log("What type of bus do you want to take? (Fares: Premium/Business/Coach)");
		log("(1) Rocket Bus ($50/$35/$20)");
		log("(2) Deluxe Bus ($100/$70/$50)");
		input = scanner.nextLine();
		log();
		
		switch(input) {
			case "1":
				busList = busManager.getRocketBusList();
				break;
			case "2":
				busList = busManager.getDeluxeBusList();
				break;
			default:
				log("Not a valid selection.");
				return;
		}
		
		log("Which bus do you want?");
		log("(Enter number next to bus listing to make selection.)");
		for(int i = 0; i < busList.size(); i++) {
			log("(" + (i + 1) + ") " + busList.get(i).getId());
		}
		input = scanner.nextLine();
		log();
		
		try {
			int index = Integer.parseInt(input);
			index--;
			if(index < 0 || index >= busList.size()) {
				log("Not a valid selection.");
				log();
				return;
			}
			Bus bus = busList.get(index);
			selection.setBus(bus);
			editSeat(selection);
			log("Bus successfully changed.");
			log();
			return;
		} catch (Exception e) {
			log("Not a valid input.");
			log();
		}
		
	}
	
	/**
	 * Menu to edit seat for given reservation.
	 * @param selection The reservation to edit.
	 */
	private static void editSeat(Reservation selection) {
		String input;
		SeatType seatType;
		log("Do want to change the seat type?");
		log("(1) Yes, change the seat type.");
		log("(2) No, don't change the seat type.");
		input = scanner.nextLine();
		log();
		
		switch(input) {
			case "1":
				seatType = editSeatType(selection);
				break;
			default:
				seatType = selection.getSeat().getType();
		}
		
		ArrayList<Seat> seatList;
		switch(seatType) {
		case PREMIUM:
			seatList = selection.getBus().getAvailablePremiumSeats();
			break;
		case BUSINESS:
			seatList = selection.getBus().getAvailableBusinessSeats();
			break;
		default:
			seatList = selection.getBus().getAvailableCoachSeats();
		}
		
		if(seatList.isEmpty()) {
			log("No available seats for selection.");
			log();
			return;
		}
		
		seatLoop: while(true) {
			log("Which seat do you want?");
			log("(Enter number next to seat listing to make selection.)");
			for(int i = 0; i < seatList.size(); i++) {
				log("(" + (i + 1) + ") " + seatList.get(i).getId());
			}
			input = scanner.nextLine();
			log();
			
			try {
				int index = Integer.parseInt(input);
				index--;
				if(index < 0 || index >= seatList.size()) {
					log("Not a valid selection.");
					log();
					continue seatLoop;
				}
				Seat seat = seatList.get(index);
				selection.getSeat().setReserved(false);
				seat.setReserved(true);
				selection.setSeat(seat);
				log("Seat successfully changed.");
				log();
				return;
			} catch (Exception e) {
				log("Not a valid input.");
				log();
			}
		}
	}
	
	/**
	 * Menu to edit seat type for given reservation.
	 * @param selection The reservation to edit.
	 */
	private static SeatType editSeatType(Reservation selection) {
		String input;
		log("Which type of seat would you like?");
		log("(1) Premium");
		log("(2) Business");
		log("(3) Coach");
		input = scanner.nextLine();
		log();
		
		switch(input) {
			case "1":
				return SeatType.PREMIUM;
			case "2":
				return SeatType.BUSINESS;
			case "3":
				return SeatType.COACH;
			default:
				log("Invalid selection, seat type did not change.");
				return selection.getSeat().getType();
		}
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
