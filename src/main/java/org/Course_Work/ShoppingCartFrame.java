package org.Course_Work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingCartFrame  {
    public JFrame shoppingCartFrame = new JFrame("Shopping Cart");  //Define the Frame
    public static JPanel top_console_cart, bottom_console_cart,bottom_part_1,bottom_part_2;

    public static JTable table_cart;
    public static JScrollPane scroll_Pane_cart;

    public static JLabel total_Label, first_Discount_Label, category_Discount_Label,
            final_total_Label, total_No_Label, first_Discount_No_Label, category_Discount_No_Label, final_total_No_Label;
    public ShoppingCartFrame(){
        shoppingCartFrame.setSize(600,500);
        shoppingCartFrame.setLayout(new GridLayout(2,1));

        int marginSize =7;
        EmptyBorder emptyBorder = new EmptyBorder(marginSize, marginSize*4, marginSize, 0);

        //Define main panels in the frame
        top_console_cart = new JPanel();
        bottom_console_cart = new JPanel(new GridLayout(1,2));

        bottom_part_1 = new JPanel();
        bottom_part_2 = new JPanel();

        bottom_part_1.setLayout(new BoxLayout(bottom_part_1, BoxLayout.Y_AXIS));
        bottom_part_2.setLayout(new BoxLayout(bottom_part_2, BoxLayout.Y_AXIS));

        table_cart = cart_table_create(WestminsterFrame.user_Shopping_Cart.getProduct_list());
        JScrollPane scroll_Pane_cart = new JScrollPane(table_cart);

        table_cart.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 1) { // Check if the edited column is the Quantity column
                DefaultTableModel model1 = (DefaultTableModel) table_cart.getModel();
                Object quantity =  model1.getValueAt(row, column);
                System.out.println(quantity.toString());
                // Get the relative Product object and update the quantity using the setter method
                Product product = WestminsterFrame.user_Shopping_Cart.getProduct_list().get(row);
                product.setNumber_of_available_items(Integer.parseInt(quantity.toString()));
                update_Table_Model();
                update_Information();
            }
        });

        total_Label = new JLabel("Total: ");
        first_Discount_Label = new JLabel("First Purchase Discount(10%): ");
        category_Discount_Label = new JLabel("Three Items in same Category Discount(20%): ");
        final_total_Label = new JLabel("Final Total: ");
        total_No_Label = new JLabel();
        first_Discount_No_Label = new JLabel();
        category_Discount_No_Label = new JLabel();
        final_total_No_Label = new JLabel();



        total_Label.setBorder(emptyBorder);
        first_Discount_Label.setBorder(emptyBorder);
        category_Discount_Label.setBorder(emptyBorder);
        final_total_Label.setBorder(emptyBorder);

        total_No_Label.setBorder(emptyBorder);
        first_Discount_No_Label.setBorder(emptyBorder);
        category_Discount_No_Label.setBorder(emptyBorder);
        final_total_No_Label.setBorder(emptyBorder);


        //Add Panels and elements
        //Add the main Panels for Frame
        shoppingCartFrame.add(top_console_cart);
        shoppingCartFrame.add(bottom_console_cart);

        top_console_cart.add(scroll_Pane_cart);

        bottom_console_cart.add(bottom_part_1);
        bottom_console_cart.add(bottom_part_2);

        bottom_part_1.add(total_Label);
        bottom_part_1.add(first_Discount_Label);
        bottom_part_1.add(category_Discount_Label);
        bottom_part_1.add(final_total_Label);

        bottom_part_2.add(total_No_Label);
        bottom_part_2.add(first_Discount_No_Label);
        bottom_part_2.add(category_Discount_No_Label);
        bottom_part_2.add(final_total_No_Label);

        shoppingCartFrame.setVisible(true);

    }

    public static JTable cart_table_create(ArrayList<Product> product_List){
        String[] column_names = {"Product", "Quantity", "Price"};
        DefaultTableModel model_cart = new DefaultTableModel(column_names,0);        //Define the table model




        for (Product product: product_List ) {
            switch (product.getProduct_type()) {
                case "Electronics":
                    Object[] row_Data_Electronics = {product.getProduct_Id()+" "+
                            product.getProduct_name()+" "+
                            product.getBrand()+", "+ product.getWarrenty_period()+" week Warranty",
                            product.getNumber_of_available_items(),
                            product.getPrice()};
                    model_cart.addRow(row_Data_Electronics); //Add data for the row for Electronics
                    break;

                case "Clothing":
                    Object[] row_Data_Clothing = {product.getProduct_Id()+" "+
                            product.getProduct_name()+" "+
                            product.getProduct_type()+","+ product.getSize() + ", " + product.getColour(),
                            product.getNumber_of_available_items(),
                            product.getPrice()};
                    model_cart.addRow(row_Data_Clothing);    //Add data for the row for Clothing
                    break;
            }
        }

        JTable cart_tabel = new JTable(model_cart);


        //Center the data in the table
        DefaultTableCellRenderer center_Render = new DefaultTableCellRenderer();
        center_Render.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < cart_tabel.getColumnCount(); i++) {
            cart_tabel.getColumnModel().getColumn(i).setCellRenderer(center_Render);
        }
        return cart_tabel;
    }

    public static void update_Table_Model() {
        DefaultTableModel model = (DefaultTableModel) table_cart.getModel();
        model.setRowCount(0);
        ArrayList<Product> productList = WestminsterFrame.user_Shopping_Cart.getProduct_list();

        for (Product product : productList) {
            switch (product.getProduct_type()) {
                case "Electronics":
                    Object[] rowDataElectronic = {
                            product.getProduct_Id() + " " +
                                    product.getProduct_name() + " " +
                                    product.getBrand() + ", " + product.getWarrenty_period(),
                            product.getNumber_of_available_items(),
                            product.getPrice(),};
                    model.addRow(rowDataElectronic);
                    break;
                case "Clothing":
                    Object[] rowDataClothing = {
                            product.getProduct_Id() + " " +
                                    product.getProduct_name() + " " +
                                    product.getSize() + ", " + product.getColour(),
                            product.getNumber_of_available_items(),
                            product.getPrice()*product.getNumber_of_available_items(),
                    };
                    model.addRow(rowDataClothing);
                    break;
            }
        }
    }

    public static void update_Information(){
        total_No_Label.setText(Double.toString(WestminsterFrame.user_Shopping_Cart.total_cost()));
        first_Discount_No_Label.setText(Double.toString(WestminsterFrame.user_Shopping_Cart.first_Discount(false)));
        category_Discount_No_Label.setText(Double.toString(WestminsterFrame.user_Shopping_Cart.category_Discount()));
        final_total_No_Label.setText(Double.toString(WestminsterFrame.user_Shopping_Cart.total_cost()));
    }
}
