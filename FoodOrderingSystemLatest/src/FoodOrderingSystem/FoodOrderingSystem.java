package FoodOrderingSystem;

import java.util.Scanner;

import FoodOrderingSystem.Food;
import FoodOrderingSystem.Menu;
import FoodOrderingSystem.Order;

public class FoodOrderingSystem {

	Menu menu;
     
    public FoodOrderingSystem(){
    	this.menu = new Menu();
    }
    
    public void processCommand(String[] choice) {
    	int commandLength = choice.length;
    	switch(choice[0]) {
    	case "1":  // Show Food Menu
    		menu.showMenu();
    		break;
    		
    	case "2": // Add Food to Menu
            if (commandLength != 3) {
            	System.out.println("No enough args");
            	break;
            }
            Food food = new Food(choice[1], Double.parseDouble(choice[2]));
            menu.addFood(food);
    		break;
    	
    	case "3": // Order
    		Menu menu = new Menu();
            menu.showMenu();
            
            Order order = new Order();
            order.getOrderItems();
    		break;
    	
    	case "4":
    		
    		break;
    		
    	default:
    		System.out.println("Invalid request");
    		break;
    	}
    }
    
    public void choiceMenu(){
    	System.out.println("=======================================");
        System.out.println("                Choice                   ");
        System.out.println("=======================================");
        System.out.println("Enter [1] to Show Food Menu.");
        System.out.println("Enter [2 (use - instead of space)Food Price] to Add Food to Menu. ");
        System.out.println("Enter 3 to Order. ");
        System.out.println("Enter 4 to Make Payment.");
        System.out.println("Enter 5 to Exit System.");
        System.out.println("Enter choice here: ");
    }
    
    public static void main(String[] args) {
    	FoodOrderingSystem system = new FoodOrderingSystem();
    	
        Scanner sc = new Scanner(System.in);

        system.choiceMenu();
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
            	String[] commands = line.trim().split("\\s+");
                system.processCommand(commands);
                System.out.println("\n"); //Just an an extra line more clear
                
                system.choiceMenu();              
            }
        }
        sc.close();
    }

}