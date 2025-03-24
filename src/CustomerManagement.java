import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CustomerManagement {
    public static List<Customer> customerCollection = null;
    CustomerManagement(){
          customerCollection = new ArrayList<>();
    }

    public void addNewCustomer() {
        System.out.printf("\n\n\n%60s ++++++++++++++ Welcome to the Customer Registration Portal ++++++++++++++", "");
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter your name :\t");
        String name = read.nextLine();
        System.out.print("Enter your email address :\t");
        String email = read.nextLine();
        while (isUniqueData(email)){
            System.out.println(
                    "ERROR!!! User with the same email already exists... Use new email or login using the previous credentials....");
            System.out.print("Enter your email address :\t");
            email = read.nextLine();
        }
        System.out.print("Enter your Password :\t");
        String password = read.nextLine();
        System.out.print("Enter your Phone number :\t");
        String phone = read.nextLine();
        System.out.print("Enter your address :\t");
        String address = read.nextLine();
        System.out.print("Enter your age :\t");
        int age = read.nextInt();
        customerCollection.add(new Customer(name, email, password, phone, address,age));
    }
    public boolean isUniqueData(String emailID) {
        boolean isUnique = false;
        for (Customer c : customerCollection) {
            if (emailID.equals(c.getEmail())) {
                isUnique = true;
                break;
            }
        }
        return isUnique;
    }
    public Customer searchUser(String ID) {
        boolean isFound = false;
        Customer customerWithTheID = customerCollection.get(0);
        for (Customer c : customerCollection) {
            if (ID.equals(c.getUserID())) {
                System.out.printf("%-50sCustomer Found...!!!Here is the Full Record...!!!\n\n\n", " ");
                DisplayCustomer.displayHeader();
                isFound = true;
                customerWithTheID = c;
                break;
            }
        }
        if (isFound) {
            System.out.printf(
                    "%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n",
                    "");
            return customerWithTheID;
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
            return null;
        }
    }

    public static void editUserInfo(String ID) {
        Scanner read = new Scanner(System.in);

        // Find the index of the customer in the collection
        for (int i = 0; i < customerCollection.size(); i++) {
            Customer c = customerCollection.get(i);
            if (ID.equals(c.getUserID())) {
                System.out.print("\nEnter the new name of the Passenger:\t");
                String name = read.nextLine();
                System.out.print("Enter the new email address:\t");
                String email = read.nextLine();
                System.out.print("Enter the new Phone number:\t");
                String phone = read.nextLine();
                System.out.print("Enter the new address:\t");
                String address = read.nextLine();
                System.out.print("Enter the new age:\t");
                int age = read.nextInt();

                // Create a new updated Customer object
                Customer updatedCustomer = c.updateInfo(name, email, phone, address, age);

                // Remove the old customer and add the new one
                customerCollection.set(i, updatedCustomer);

                System.out.println("Customer details updated successfully.");
                return;
            }
        }

        System.out.printf("No Customer with the ID %s Found...!!!\n", ID);
    }



    public void deleteUser(String ID) {
        boolean isFound = false;
        Iterator<Customer> iterator = customerCollection.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (ID.equals(customer.getUserID())) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            iterator.remove();
            System.out.printf("\n%-50sPrinting all  Customer's Data after deleting Customer with the ID %s.....!!!!\n",
                    "", ID);
            DisplayCustomer.displayCustomersData(false);
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
    }

}
