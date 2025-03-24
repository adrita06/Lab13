import java.util.Iterator;

public class DisplayCustomer {
    static CustomerManagement customerManagement=new CustomerManagement();
    private static String toString(int i, Customer c) {
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", "", i,
                randomIDDisplay(c.getUserID()), c.getName(), c.getAge(), c.getEmail(),c.getAddress(), c.getPhone());
    }

    public static void displayCustomersData(boolean showHeader) {
        displayHeader();
        Iterator<Customer> iterator = customerManagement.customerCollection.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            Customer c = iterator.next();
            System.out.println(toString(i,c));
            System.out.printf(
                    "%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n",
                    "");
        }
    }

    static void displayHeader() {
        System.out.println();
        System.out.printf(
                "%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n",
                "");
        System.out.printf(
                "%10s| SerialNum  |   UserID   | Passenger Names                  | Age     | EmailID\t\t       | Home Address\t\t\t     | Phone Number\t       |%n",
                "");
        System.out.printf(
                "%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n",
                "");
        System.out.println();

    }

    static String randomIDDisplay(String randomID) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i <= randomID.length(); i++) {
            if (i == 3) {
                newString.append(" ").append(randomID.charAt(i));
            } else if (i < randomID.length()) {
                newString.append(randomID.charAt(i));
            }
        }
        return newString.toString();
    }
}
