package FoodOrderingSystem;

import java.util.Scanner;
import java.util.ArrayList;

public class Order extends Menu implements Ordering {
//    public static double subtotal;
//    public static double total;
//    private static double price;
//    
//    static boolean ordering = true;
//    static double j = 0.0;
//    
//    static Scanner input = new Scanner(System.in);
//    
//    static Menu menu = new Menu();

    public Order() { //default constructor

    }

    @Override
    public boolean getOrderItems() {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Food> selectedFood = new ArrayList<>();
        ArrayList<Drink> selectedDrink = new ArrayList<>();
        System.out.println("Do you wish to continue? (Y or N)");
        char isContinue = userInput.next().charAt(0);

        if (isContinue == 'Y') {
            System.out.println("Do you wish to purchase food? (Y or N)");
            char buyFood = userInput.next().charAt(0);
            if (buyFood == 'Y') {
                selectedFood = orderFood(userInput);
            }

            System.out.println("Do you wish to purhcase drinks? (Y or N)");
            char buyDrink = userInput.next().charAt(0);
            if (buyDrink == 'Y') {
                selectedDrink = orderDrink(userInput);
            }

            double totalPrice = calculateTotalPrice(selectedFood, selectedDrink);
            Payment payment = new Payment();
            payment.makePayment(totalPrice);
            System.out.println(Double.toString(totalPrice));
            System.out.println(selectedDrink.get(0).getDrinkName());
            
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Food> orderFood(Scanner userInput) {
        ArrayList<Food> listFood = new ArrayList<>();

        System.out.println("Select Food Item: ");
        int foodInput = userInput.nextInt();

        System.out.println("Enter the amount you wish to purchase: ");
        int foodQuantity = userInput.nextInt();
        System.out.println(foodQuantity);
        for (int i = 0; i < foodQuantity; i++) {
            Food selectedFood = get_food_item(foodInput);
            listFood.add(selectedFood);
        }
        return listFood;

    }

    public ArrayList<Drink> orderDrink(Scanner userInput) {
        ArrayList<Drink> listDrink = new ArrayList<>();

        System.out.println("Select drink item: ");
        int drinkInput = userInput.nextInt();

        System.out.println("Enter the amount you wish to purchase: ");
        int drinkQuantity = userInput.nextInt();
        System.out.println(drinkQuantity);
        for (int i = 0; i < drinkQuantity; i++) {
            Drink selectedDrink = get_drink_item(drinkInput);
            listDrink.add(selectedDrink);
        }

        return listDrink;
    }

    public Food get_food_item(int food_id) {
        String id = "F" + Integer.toString(food_id);
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getFoodId().equals(id)) {
                return foodList.get(i);
            }
        }
        return null;
    }

    public Drink get_drink_item(int drinkId) {
        String id = "D" + Integer.toString(drinkId);
        for (int i = 0; i < drinkList.size(); i++) {
            if (drinkList.get(i).getDrinkId().equals(id)) {
                return drinkList.get(i);
            }
        }
        return null;
    }

    public double calculateTotalPrice(ArrayList<Food> foodList, ArrayList<Drink> drinkList) {
        double totalPrice = 0.0;
        System.out.println(foodList.size());
        System.out.println(drinkList.size());
        if (!foodList.isEmpty()) {
            for (int i = 0; i < foodList.size(); i++) {
                totalPrice += foodList.get(i).getFoodPrice();
            }
        }

        if (!drinkList.isEmpty()) {
            for (int i = 0; i < drinkList.size(); i++) {
                totalPrice += drinkList.get(i).getDrinkPrice();
            }
        }
        return totalPrice;
    }

    public void display_item(int foodId, int drinkId) {
        System.out.println();
    }

//    public static double getItemPrice(int foodId){ 
//        if(foodId == 1){
//            System.out.println("You've ordered burger.");
//            price = 10.00;
//        }
//        if(foodId == 2){
//            System.out.println("You've ordered fries. ");
//            price = 5.00;
//        }
//        if(foodId == 3){
//            System.out.println("You've ordered soft drink.");
//            price = 3.50;
//        }  
//        getQuantity();
//        return j;
//    }
//    
//    public static double getQuantity(){
//        System.out.println("Enter quantity: ");
//        double quantity = input.nextDouble();
//        getSubtotal(quantity, price);
//        return quantity;
//    }
//    
//    public static double getSubtotal(double quantity, double itemPrice){
//        double subtotal = quantity * itemPrice;
//        System.out.println("Subtotal: RM" + subtotal);
//        j = subtotal;
//        return subtotal;
//    }
//    
// 
//      public static void showDone(double total){
//        ordering = false;
//        System.out.println(total);
//        System.out.println("Enjoy your meal!");
//    
//    }
//    
//    public static void setSubtotal(double subtotal) {
//        Order.subtotal = subtotal;
//    }
//
//
//    public static void setItemPrice(double price) {
//        Order.price = price;
//    }
//    
//    public static void provideMenu(){
//        menu.showFoodMenu();
//        System.out.println("4) End \n");
//    }
//    
//    public static void getFoodItem(){
//          int foodOption;
//          int foodId = 0;
//          provideMenu();
//          Scanner input = new Scanner(System.in);
//          double total = 0.0;
//          while(ordering) {
//              System.out.println("Enter food id here: ");
//              foodOption = input.nextInt();
//              switch(foodOption){
//                  case 1: 
//                      foodId = 1;
//                      total += getItemPrice(foodId);
//                      break;
//                  case 2:
//                      foodId = 2;
//                      total += getItemPrice(foodId);
//                      break;
//                  case 3:
//                      foodId = 3;
//                      total += getItemPrice(foodId);
//                      break;
//                  case 4:
//                      showDone(total);
//                      break;
//                  default:
//                      System.out.println("Invalid option.");
//              }
//              
//          }  
//        System.out.println("Total: RM " + total);
//          
//    }
}
