package FoodOrderingSystem;

public class Food {
//    private String foodId;
    private String foodName;
    private double foodPrice;

    public Food(String foodName, double foodPrice) { 
//        setFoodId(foodId);
        setFoodName(foodName);
        setFoodPrice(foodPrice);
    }

//    public String getFoodId() { 
//        return foodId;
//    }
//
//    public void setFoodId(String foodId) {
//        this.foodId = foodId;
//    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Food otherObj = (Food) obj;
		return this.foodName.equals(otherObj.foodName); // cannot have food with same name
	}
}


