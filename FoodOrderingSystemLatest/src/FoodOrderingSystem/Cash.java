package FoodOrderingSystem;


public class Cash extends Payment {
    
   
    public Cash(double amount){
        super(amount);
    }
    
    public void paymentDetails(){
        System.out.println("The payment of cash: RM " + amount);
        
}
}
