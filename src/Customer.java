import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String userID;
    private String email;
    private String name;
    private String phone;
    private final String password;
    private String address;
    private int age;
    public List<Flight> flightsRegisteredByUser;
    public List<Integer> numOfTicketsBookedByUser;




    Customer(String name, String email, String password, String phone, String address, int age) {
        RandomGenerator random = new RandomGenerator();
        random.randomIDGen();
        this.name = name;
        this.userID = random.getRandomNumber();
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.flightsRegisteredByUser = new ArrayList<>();
        this.numOfTicketsBookedByUser = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public Customer updateInfo(String name, String email, String phone, String address, int age) {
        return new Customer(this.userID, name, email, phone, address, age);
    }

    public List<Flight> getFlightsRegisteredByUser() {
        return flightsRegisteredByUser;
    }

    public List<Integer> getNumOfTicketsBookedByUser() {
        return numOfTicketsBookedByUser;
    }
}
