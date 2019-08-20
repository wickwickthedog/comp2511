package FoodOrderingSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Payment {
    
    public static final String[] paymentMethod = {
        "Cash", "Card"
    };

    public void makePayment(double amountDue){
        Scanner scanner = new Scanner(System.in);
        DecimalFormat two = new DecimalFormat("0.00");
        System.out.println("Amount due: RM " + two.format(amountDue));
        
        System.out.println("Pay by the following options: ");
        for (int i = 0; i < paymentMethod.length; i++) {
            String temp_id = Integer.toString(i + 1);
            System.out.println(temp_id + ") " + paymentMethod[i]);
        }
        
        int selected_payment_method = scanner.nextInt() - 1;
        System.out.println(paymentMethod[selected_payment_method] + " selected");
        System.out.println("Payment Succesful");
    }
    

    public void paymentDetails(double amountDue) {
        System.out.println("The payment amount is " + amountDue);
    }
}
