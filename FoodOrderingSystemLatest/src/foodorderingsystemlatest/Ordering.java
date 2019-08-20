package foodorderingsystemlatest;

import foodorderingsystemlatest.Food;
import foodorderingsystemlatest.Drink;
import java.util.ArrayList;
import java.util.Scanner;


public interface Ordering {
    public boolean getOrderItems();
    public ArrayList<Food> orderFood (Scanner userInput);
    public ArrayList<Drink> orderDrink (Scanner userInput);
    public Food get_food_item(int food_id);
    public Drink get_drink_item(int drinkId);
    public double calculateTotalPrice(ArrayList<Food> foodList, ArrayList<Drink> drinkList);
    public void display_item(int foodId, int drinkId);
    
}
