package FoodOrderingSystem;

import java.util.Scanner;
import java.util.ArrayList;


public class Card extends Payment {

    public static String cardNo, name, expDate;
    Scanner scan = new Scanner(System.in);

   
    public Card(double amount, String name, String cardNo, String date) { //overloaded method 
        super(amount);
        this.cardNo = cardNo;
        this.expDate = date;
        this.name = name;
    }

//    public Card(String cardNo, String name, String date, String ccv) {
//        this.cardNo = cardNo;
//        this.name = name;
//        this.expDate = date;
//        this.ccv = ccv;
//    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        System.out.println("Enter card number: ");
        String cardNum = scan.next();
        
        if(cardNum.length() >= 13 && cardNum.length() <= 16){
            System.out.println("Valid card number.");
            this.cardNo = cardNo;
    }   else {
            System.out.println("Error: Invalid Card Number!");
        }
        
    }

    public String getExpDate() {
        return expDate;
    }

    public void setDate(String date) {
        this.expDate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void paymentDetails(){
        System.out.println("The payment is RM " + this.amount + " through the card " + this.cardNo + " and expire date " + this.expDate + " and the owner name " + this.name +"." );
    }

//    public static ArrayList<Payment> cards = new ArrayList<Payment>();
//
//    public static void creditCardDetails() {
//
//        Card card = new Card();
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Please enter card no here: ");
//        String cardNo = scan.next();
//        card.setCardNo(cardNo);
//
//        ArrayList<String> no = new ArrayList<>();
//        no.add(cardNo);
//
//        for (int i = 0; i < no.size(); i++) {
//
//        }
//
//        System.out.println("Please enter");
//        cards.add(card1);
//        cards.add(card2);
//
//        for (int i = 0; i < cards.size(); i++) {
//            System.out.println("Credit card details:" + cards.get(i).getCardNo());
//            System.out.println("Name on card: " + ca.name);
//            System.out.println("Expiration date: " + expDate);
//            System.out.println("Credit card number: " + cardNo);
//        }
//
//    }
//
//    public static void addCard() {
//        Scanner scan = new Scanner(System.in);
//
//        for (int i = 0; i < 1; i++) {
//            //get the input of card no
//            System.out.println("\n \n Add credit card to the system: ");
//
//            System.out.println("\n \n Please enter card no: ");
//            String cardNo = scan.next();
//
//            System.out.println("\n \n Please enter name on the card: ");
//            String name = scan.next();
//
//            System.out.println("\n \n Please enter expiration date: ");
//            String expDate = scan.next();
//
//            System.out.println("\n \n Please enter ccv behind the card: ");
//            String ccv = scan.next();
//
//            cards.add(new CardPayment(cardNo, name, expDate, ccv));
//
//            ArrayList<String> card = new ArrayList<String>();
//
//            System.out.println("You have successfully added a new card to the system!");
//        }
//    }
//
//    public void paymentDetails() {
//        System.out.println("Name on card: " + this.getName());
//        System.out.println("Expiration date: " + this.getExpDate());
//        System.out.println("Credit card number: " + this.getCardNo());
//        System.out.println("The payment by credit card amount is " + getPayment());
//    }

}


