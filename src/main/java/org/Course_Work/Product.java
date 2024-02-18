package org.Course_Work;

public abstract class Product implements Comparable<Product> {
    private String product_Id;
    private String product_name;
    private int number_of_available_items;
    private double price;
    private String product_type;

    //Constructor for products
    public Product(String product_Id, String product_name, int number_of_available_items, double price, String product_type){
        this.product_Id = product_Id;
        this.product_name = product_name;
        this.number_of_available_items = number_of_available_items;
        this.price = price;
        this.product_type = product_type;
    }

    //Implement getters
    public String getProduct_Id(){
        return product_Id;
    }

    public String getProduct_name(){
        return product_name;
    }

    public int getNumber_of_available_items(){
        return number_of_available_items;
    }

    public double getPrice(){
        return price;
    }

    public void setNumber_of_available_items(int number_of_available_items) {
        this.number_of_available_items = number_of_available_items;
    }

    public String getProduct_type(){return product_type;}
    public String getBrand(){return getBrand();}
    public int getWarrenty_period(){return getWarrenty_period();}
    public String getColour(){return getColour();}
    public String getSize(){return getSize();}

    abstract String displayProducts();  //Calling displayProducts metod

    @Override
    public int compareTo(Product product) {
        return product_Id.compareTo(product.product_Id);
    }
}
