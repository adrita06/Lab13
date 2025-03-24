import java.util.ArrayList;
import java.util.List;

public class CustomerFlightManagement {

    public List<Customer> customersList;
    Customer c;

    public CustomerFlightManagement(Customer c) {
        this.c = c;
    }

    void addNewFlightToCustomerList(Flight f) {
        c.flightsRegisteredByUser.add(f);
        // numOfFlights++;
    }

    void addExistingFlightToCustomerList(int index, int numOfTickets) {
        int newNumOfTickets = c.numOfTicketsBookedByUser.get(index) + numOfTickets;
        c.numOfTicketsBookedByUser.set(index, newNumOfTickets);
    }

}
