package org.Course_Work;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    public static WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
    public static ShoppingCart product_List = new ShoppingCart();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        load_from_file();

        while (true) {
            try {
                System.out.println("Enter the User Type");
                System.out.println("1. Manager");
                System.out.println("2. Customer");
                int type = input.nextInt();


                if (type == 1) {


                    System.out.println("1. Add Product");
                    System.out.println("2. Remove Product");
                    System.out.println("3. Display all Products");
                    System.out.println("4. Save");
                    System.out.println("0. Exit");
                    int number = input.nextInt();

                    switch (number) {
                        case 0:
                            System.exit(0);

                        case 1:
                            westminsterShoppingManager.add_product();
                            break;

                        case 2:
                            westminsterShoppingManager.delete_product();
                            break;

                        case 3:
                            westminsterShoppingManager.displayProduct();
                            break;

                        case 4:
                            westminsterShoppingManager.save_file();
                            break;

                        default:
                            System.out.println("Wrong Input");
                    }
                } else if (type == 2) {
                    new UserFrame();
                } else {
                    System.out.println("Wrong Input");
                }

            }catch (Exception e){
                System.out.println("Invalid Input");
                input.nextLine();
            }
        }
        }




        @Override
        public void add_product() {
            if (product_List.getProduct_list().size() <= 50) {
                System.out.println("Choose the Product Type :");
                System.out.println("1. Clothing");
                System.out.println("2. Electronics");
                int productType = input.nextInt();

                System.out.println("Enter the Product ID :");
                String productId = input.next();
                System.out.println("Enter the Product Name :");
                String productName = input.next();
                System.out.println("Enter the No of Items :");
                int numberofAvailableItems = input.nextInt();
                System.out.println("Enter the Price :");
                double price = input.nextDouble();

                if (productType == 1) {
                    System.out.println("Enter the Size :");
                    String size = input.next();
                    System.out.println("Enter the Color :");
                    String color = input.next();

                    Clothing clothing = new Clothing(productId, productName, numberofAvailableItems, price, "Clothing", size, color);
                    product_List.add_product(clothing);
                } else if (productType == 2) {
                    System.out.println("Enter the Brand :");
                    String brand = input.next();
                    System.out.println("Enter the Warranty Period :");
                    int warrentyPeriod = input.nextInt();

                    Electronics electronics = new Electronics(productId, productName, numberofAvailableItems, price, "Electronics", brand, warrentyPeriod);
                    product_List.add_product(electronics);
                } else {
                    System.out.println("Invalid Product Type");
                }
            }else {
                System.out.println("Product Limit Exceeds");
            }
        }

    @Override
    public void delete_product(){
        displayProduct();

        System.out.println("Choose the product type :");
        System.out.println("1. Clothing");
        System.out.println("2. Electronics");
        int productType = input.nextInt();

        System.out.println("Enter Product ID :");
        String productId = input.next();

        boolean deleteItem = false;

        switch (productType){
            case 1:
                for (int i = 0; i < product_List.getProductlist().size(); i++) {
                    if(productId.equals(product_List.getProductlist().get(i).getProduct_Id()) && product_List.getProductlist().get(i).getProduct_type().equals("Clothing")){
                        System.out.println(product_List.getProductlist().get(i).displayProducts());
                        product_List.remove_product(product_List.getProductlist().get(i));
                        deleteItem = true;
                    }

                }
                break;

            case 2:
                for (int i = 0; i < product_List.getProductlist().size(); i++) {
                    if (productId.equals(product_List.getProductlist().get(i).getProduct_Id()) && product_List.getProductlist().get(i).getProduct_type().equals("Electronics")){
                        System.out.println(product_List.getProductlist().get(i).displayProducts());
                        product_List.remove_product(product_List.getProductlist().get(i));
                        deleteItem = true;

                    }

                }
                break;

            default:
                System.out.println("Invalid Product Type");
                break;
        }
        if (deleteItem){
            System.out.println("Product Deleted Successfully");
            System.out.println("Items Left in the Cart : " + product_List.getProductlist().size());
        }else {
            System.out.println("Product not Deleted");
        }
    }

    @Override
    public void displayProduct(){
        if (product_List.getProductlist().isEmpty()){
            System.out.println("Product cart is Empty");
        }else {
            for (int i = 0; i < product_List.getProductlist().size(); i++) {
                System.out.println(product_List.getProductlist().get(i).displayProducts());
            }
        }
        System.out.println(product_List.category_Discount());

    }

    @Override
    public void save_file(){
        try {
            FileWriter product_file = new FileWriter("savedProducts.txt");
            for (int i = 0; i < product_List.getProductlist().size(); i++) {
                product_file.write(product_List.getProductlist().get(i).displayProducts() + "\n");
            }
            product_file.close();
            System.out.println("Products Saved Successfully");
        } catch (IOException e){
            System.out.println("An Error Occurred!");
        }
    }

    public static void load_from_file(){
        try {
            File savedfile = new File("savedProducts.txt");
            Scanner fileReader = new Scanner(savedfile);
            while (fileReader.hasNextLine()){
                String dataLine = fileReader.nextLine();
                String[] dataArray = dataLine.split("\\|");

                if (dataArray.length == 7){
                    if (dataArray[6].equals("Clothing")){
                        Clothing clothing = new Clothing(dataArray[0], dataArray[1], Integer.parseInt(dataArray[2]), Double.parseDouble(dataArray[3]), "Clothing", dataArray[4], dataArray[5]);
                        product_List.add_product(clothing);
                    } else if (dataArray[6].equals("Electronics")) {
                        Electronics electronics = new Electronics(dataArray[0], dataArray[1], Integer.parseInt(dataArray[2]), Double.parseDouble(dataArray[3]), "Electronics", dataArray[4], Integer.parseInt(dataArray[5]));
                        product_List.add_product(electronics);
                    }
                }else {
                    System.out.println("Invalid File Format");
                }
            }
            System.out.println("Loaded Previously Saved Products");
            fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println("No Previously Saved Products Found");
        }
    }
}
