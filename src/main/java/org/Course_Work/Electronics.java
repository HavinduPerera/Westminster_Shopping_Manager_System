package org.Course_Work;

public class Electronics extends Product{
    private String brand;
    private int warrenty_period;

    //Constructor for Electronics
    public Electronics(String product_Id, String product_name, int number_of_available_items, double price, String product_type, String brand, int warrenty_period){
        super(product_Id, product_name, number_of_available_items,price, product_type);
        this.brand = brand;
        this.warrenty_period = warrenty_period;
    }

    //Implement getters
    public String getBrand(){
        return brand;
    }

    public int getWarrenty_period(){
        return warrenty_period;
    }

    //Implement the displayProducts method
    public String  displayProducts(){
        return(getProduct_Id() + "|" + getProduct_name() + "|" + getNumber_of_available_items() + "|" + getPrice() + "|" + getBrand() + "|" + getWarrenty_period() + "|" + getProduct_type());
    }
}
