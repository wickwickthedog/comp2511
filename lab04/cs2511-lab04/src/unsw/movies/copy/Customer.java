package unsw.movies.copy;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals;

    public Customer(String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {

        double totalAmount = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental r : rentals) {
            // show figures for this rental
            result += "\t" + r.getMovie().getTitle() + "\t" + r.getCharge() + "\n";
            totalAmount += r.getCharge();

        }

        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        return result;
    }

    public static void main(String[] args) {
        Customer c1 = new Customer("Mary Jane Watson");
        Movie m = new Movie("Gone with the wind", new Regular());
        Rental r = new Rental(m, 3);
        c1.addRental(r);

        m = new Movie("Finding Nemo", new Childrens());
        r = new Rental(m, 7);
        c1.addRental(r);

        System.out.println(c1.statement());

    }
}
