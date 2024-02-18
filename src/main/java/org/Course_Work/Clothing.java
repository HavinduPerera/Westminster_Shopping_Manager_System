package org.Course_Work;

public class Clothing extends Product{
    private String size;
    private String colour;

    //Constructor for Clothing
    public Clothing(String product_Id, String product_name, int number_of_available_items, double price, String product_type, String size, String colour){
        super(product_Id, product_name, number_of_available_items, price, product_type);
        this.size = size;
        this.colour = colour;
    }

    //Implement getters
    public String getSize(){
        return size;
    }

    public String getColour(){
        return colour;
    }

    //Implement the displayProducts method
    public String displayProducts(){
        return(getProduct_Id() + "|" + getProduct_name() + "|" + getNumber_of_available_items() + "|" + getPrice() + "|" + getSize() + "|" + getColour() + "|" + getProduct_type());
    }
}
