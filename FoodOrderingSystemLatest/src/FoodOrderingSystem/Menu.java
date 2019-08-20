package FoodOrderingSystem;

import java.util.ArrayList;

public class Menu  {

    private ArrayList<Food> foodList; 

    public Menu() {
    	this.foodList = new ArrayList<>();
         
    }

	/**
	 * @param food the food to add
	 */
	public void addFood(Food food) {
		for (Food f : foodList) {
			if (f.equals(food)) {
				System.out.println(f.getFoodName() + " already exist!");
				return;
			}
		}
		this.foodList.add(food);	
	}

	public void showMenu(){
        System.out.println("=======================================");
        System.out.println("                Menu                   ");
        System.out.println("=======================================");
        if(foodList.isEmpty()) System.out.println("Menu issa Empty! Please addsome food!");      
        else {
        	System.out.println("====================================");
        	System.out.println("            Food                    ");
        	System.out.println("====================================");
        	for (Food f : foodList) {
        		System.out.println(f.getFoodName() + " - RM" + f.getFoodPrice());
        	}
        }
    }
    
}
