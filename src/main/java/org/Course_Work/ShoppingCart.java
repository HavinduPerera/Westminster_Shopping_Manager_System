package org.Course_Work;

import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCart {
    public ArrayList<Product> product_list;

    //Constructor for ShoppingCart
    public ShoppingCart(){
        this.product_list = new ArrayList<>();
    }

    public void add_product(Product product){   //Implement the add_product method
        product_list.add(product);
    }

    public void remove_product(Product product){    //Implement the remove_product method
        product_list.remove(product);
    }

    public double total_cost(){     //Implement the total_cost method
        double totalCost = 0;
        for (int i = 0; i < product_list.size(); i++) {
            totalCost = totalCost + product_list.get(i).getPrice()*product_list.get(i).getNumber_of_available_items();
        }
        return totalCost;
    }

    public double category_Discount(){  //Implement the category_Discount method
        int electronic_count = 0;
        int clothing_count = 0;
        double promotion = 0;
        double discounted_cost = 0;
        for (int i = 0; i < product_list.size(); i++) {
            if (product_list.get(i).getProduct_type().equals("Electronics")){
                electronic_count++;
            }else if (product_list.get(i).getProduct_type().equals("Clothing")){
                clothing_count++;
            }
        }
        if (electronic_count >= 3 || clothing_count >= 3){
            promotion = 0.2;

        }

        if (promotion != 0) {
            discounted_cost = total_cost() * promotion;
            return discounted_cost;
        }else {
            return discounted_cost;
        }
    }

    public double first_Discount(Boolean new_Account){ //Implement the first_Discount method
        double first_Discount_cost = 0;
        if (new_Account){
            first_Discount_cost = total_cost()*0.1;
        }
        return first_Discount_cost;
    }

    public double final_Total(){
        double final_Total_Cost = 0;
        final_Total_Cost = total_cost()-(category_Discount()+first_Discount(false));
        return final_Total_Cost;
    }

    public ArrayList<Product> getProduct_list() {
        return product_list;
    }

    public ArrayList<Product> getProductlist(){
        Collections.sort(product_list);     //sorting the ArrayList product_list
        return product_list;
    }



}
