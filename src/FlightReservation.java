/*
 * FlightReservation class allows the user to book, cancel and check the status of the registered flights.
 *
 *
 * */


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FlightReservation {

    //        ************************************************************ Fields ************************************************************
    private FlightReservationDisplay fd;
    private Flight flight = new Flight();

    public FlightReservation(FlightReservationDisplay fd) {
        this.fd = fd;
    }
    int flightIndexInFlightList;

    //        ************************************************************ Behaviours/Methods ************************************************************


    /**
     * Book the numOfTickets for said flight for the specified user. Update the available seats in main system by
     * Subtracting the numOfTickets from the main system. If a new customer registers for the flight, then it adds
     * the customer to that flight, else if the user is already added to that flight, then it just updates the
     * numOfSeats of that flight.
     *
     * @param flightNo     FlightID of the flight to be booked
     * @param numOfTickets number of tickets to be booked
     * @param userID       userID of the user which is booking the flight
     */

    void bookFlight(String flightNo, int numOfTickets, String userID) {
        Flight targetFlight = null;
        Customer targetCustomer = null;
        targetFlight = findFlight(flightNo,targetFlight);
        targetCustomer = findCustomer(userID,targetCustomer);
        CustomerFlightManagement cfm=new CustomerFlightManagement(targetCustomer);

        if (targetFlight==null) {
            System.out.printf("\n %50s You've booked %d tickets for Flight \"%5s\"...", "", numOfTickets, flightNo.toUpperCase());
            return;
        }
        if (targetCustomer==null) {
            System.out.println("Invalid Flight Number...! No flight with the  ID \"" + flightNo + "\" was found...");
            return;
        }
        targetFlight.setNoOfSeatsInTheFlight(targetFlight.getNoOfSeats() - numOfTickets);
        if (!targetFlight.isCustomerAlreadyAdded(targetFlight.getListOfRegisteredCustomersInAFlight(), targetCustomer)) {
            targetFlight.addNewCustomerToFlight(targetCustomer);
        }
        if (isFlightAlreadyAddedToCustomerList(targetCustomer.flightsRegisteredByUser, targetFlight)) {
            addNumberOfTicketsToAlreadyBookedFlight(targetCustomer, numOfTickets);
            int i = flightIndex(flight.getFlightList(), flight);
            if (i != -1) {
                cfm.addExistingFlightToCustomerList(i, numOfTickets);
            }
        } else {
            cfm.addNewFlightToCustomerList(targetFlight);
            targetCustomer.numOfTicketsBookedByUser.add(numOfTickets);
        }
        System.out.printf("\n %50s You've booked %d tickets for Flight \"%5s\"...", "", numOfTickets, flightNo.toUpperCase());
    }
    private Flight findFlight(String flightNo,Flight targetFlight){
        for (Flight f1 : flight.getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                targetFlight = f1;
                return targetFlight;
            }
        }
        return null;
    }

    private Customer findCustomer(String userID,Customer targetCustomer){
        for (Customer customer : CustomerManagement.customerCollection) {
            if (userID.equals(customer.getUserID())) {
                targetCustomer = customer;
                return targetCustomer;
            }
        }
        return null;
    }


    /**
     * Cancels the flight for a particular user and return/add the numOfTickets back to
     * the main flight scheduler.
     *
     * @param userID ID of the user for whom the flight is to be cancelled
     */
    void cancelFlight(String userID) {
        String flightNum = "";
        Scanner read = new Scanner(System.in);
        int index = 0, ticketsToBeReturned;
        int size = 0;
        Customer targetCustomer = null;
        Flight targetFlight = null;
        boolean isFound = false;
        for (Customer customer : CustomerManagement.customerCollection) {
            if (userID.equals(customer.getUserID())) {
                size = customer.getFlightsRegisteredByUser().size();
                targetCustomer = customer;
            }
        }
        if (size == 0) {
            System.out.println("No Flight Has been Registered by you with ID \"\"" + flightNum.toUpperCase() + "\"\".....");
            return;
        }

        System.out.printf("%50s %s Here is the list of all the Flights registered by you %s", " ", "++++++++++++++", "++++++++++++++");
        fd.displayFlightsRegisteredByOneUser(userID);
        System.out.print("Enter the Flight Number of the Flight you want to cancel : ");
        flightNum = read.nextLine();
        System.out.print("Enter the number of tickets to cancel : ");
        int numOfTickets = read.nextInt();
        Iterator<Flight> flightIterator = targetCustomer.getFlightsRegisteredByUser().iterator();
        while (flightIterator.hasNext()) {
            Flight flight = flightIterator.next();
            if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
                isFound = true;
                targetFlight = flight;
            }
        }
        if (!isFound) {
            System.out.println("ERROR!!! Couldn't find Flight with ID \"" + flightNum.toUpperCase() + "\".....");
        }
        int numOfTicketsForFlight = targetCustomer.getNumOfTicketsBookedByUser().get(index);
        while (numOfTickets > numOfTicketsForFlight) {
            System.out.print("ERROR!!! Number of tickets cannot be greater than " + numOfTicketsForFlight + " for this flight. Please enter the number of tickets again : ");
            numOfTickets = read.nextInt();
        }
        if (numOfTicketsForFlight == numOfTickets) {
            ticketsToBeReturned = flight.getNoOfSeats() + numOfTicketsForFlight;
            targetCustomer.numOfTicketsBookedByUser.remove(index);
            flightIterator.remove();
        } else {
            ticketsToBeReturned = numOfTickets + flight.getNoOfSeats();
            targetCustomer.numOfTicketsBookedByUser.set(index, (numOfTicketsForFlight - numOfTickets));
        }
        flight.setNoOfSeatsInTheFlight(ticketsToBeReturned);
        index++;
    }

    void addNumberOfTicketsToAlreadyBookedFlight(Customer customer, int numOfTickets) {
        int newNumOfTickets = customer.numOfTicketsBookedByUser.get(flightIndexInFlightList) + numOfTickets;
        customer.numOfTicketsBookedByUser.set(flightIndexInFlightList, newNumOfTickets);
    }
    boolean isFlightAlreadyAddedToCustomerList(List<Flight> flightList, Flight flight) {
        boolean addedOrNot = false;
        for (Flight flight1 : flightList) {
            boolean flight1Equals = flight1.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber());
            if (flight1Equals) {
                this.flightIndexInFlightList = flightList.indexOf(flight1);
                addedOrNot = true;
                break;
            }
        }
        return addedOrNot;
    }
    int flightIndex(List<Flight> flightList, Flight flight) {
        int i = -1;
        for (Flight flight1 : flightList) {
            if (flight1.equals(flight)) {
                i = flightList.indexOf(flight1);
            }
        }
        return i;
    }


}
