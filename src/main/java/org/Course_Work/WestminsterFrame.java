package org.Course_Work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestminsterFrame {
    public static ShoppingCart user_Shopping_Cart = new ShoppingCart();
    public JFrame westminsterFrame = new JFrame("Westminster Shopping Center"); //Define the Frame

    //Define the Panels
    public static JPanel top_Panel, bottom_Panel,
            top_Console_Panel, top_Console_Panel1, top_Console_Panel2, top_Console_Panel3,
            bottom_Panel1, bottom_Panel2;

    public static JTable table;
    public static JScrollPane scroll_Pane;
    public static String drop_Down_Option;
    public static Product selected_Product;
    public static JLabel id_Label, category_Label, name_Label, available_Items_Label, extra_Label_1, extra_Label_2;
    public WestminsterFrame(){
        westminsterFrame.setSize(800,700); //Set the Frame size
        westminsterFrame.setLayout(new GridLayout(2,1)); //Westminster Frame Layout (top_Panel, bottom_Panel)

        //Define main Panels in Frame
        top_Panel = new JPanel(new GridLayout(2,1)); //top_Panel Layout
        bottom_Panel = new JPanel(new GridLayout(1,2)); //bottom_Panel Layout

        //Define Panel in top_Panel
        top_Console_Panel = new JPanel(new GridLayout(1,3)); //top_Console_Panel Layout(top_Console_Panel1, 2 & 3)

        //Define Panels in top_Console_Panel
        top_Console_Panel1 = new JPanel(new GridBagLayout());
        top_Console_Panel2 = new JPanel(new GridBagLayout());
        top_Console_Panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Define Panels in bottom_Panel
        bottom_Panel1 = new JPanel();
        bottom_Panel2 = new JPanel();


        bottom_Panel1.setLayout(new BoxLayout(bottom_Panel1, BoxLayout.Y_AXIS));
        bottom_Panel2.setLayout(new BoxLayout(bottom_Panel2, BoxLayout.Y_AXIS));



        int marginSize =7;
        EmptyBorder emptyBorder = new EmptyBorder(marginSize, marginSize*4, marginSize, 0);
        bottom_Panel2.setBorder(new EmptyBorder(0, 0, marginSize*6,0));

        //Define the Text, Dropdown Menu & Button in top_Console_Panel
        JLabel top_Text = new JLabel("Select Product Category"); //Define the Label in the top_Console_Panel1

        JLabel bottom_Text = new JLabel("Selected Product - Details");

        id_Label = new JLabel("Product ID: ");
        category_Label = new JLabel("Category: ");
        name_Label = new JLabel("Name: ");
        available_Items_Label = new JLabel("Items Available: ");
        extra_Label_1 = new JLabel("Size: ");
        extra_Label_2 = new JLabel("Color: ");

        bottom_Panel1.setBorder(emptyBorder);

        id_Label.setBorder(emptyBorder);
        category_Label.setBorder(emptyBorder);
        name_Label.setBorder(emptyBorder);
        available_Items_Label.setBorder(emptyBorder);
        extra_Label_1.setBorder(emptyBorder);
        extra_Label_2.setBorder(emptyBorder);

        //Creating the Dropdown Menu & add the Dropdown list
        String[] drop_Down_List = {"All", "Electronics", "Clothing"};
        JComboBox<String> drop_Down_Menu = new JComboBox<>(drop_Down_List);
        drop_Down_Menu.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxx");

        drop_Down_Option = (String) drop_Down_Menu.getSelectedItem();

        JButton shopping_Cart_Button = new JButton("Shopping Cart"); //Creating the Shopping Cart button



        top_Console_Panel1.add(top_Text);
        top_Console_Panel2.add(drop_Down_Menu);
        top_Console_Panel3.add(shopping_Cart_Button);

        //Calling the table
        table = create_Table(WestminsterShoppingManager.product_List.getProductlist());
        scroll_Pane = new JScrollPane(table);

        drop_Down_Menu.addActionListener(e -> {
            drop_Down_Option = (String) drop_Down_Menu.getSelectedItem();
            System.out.println(drop_Down_Option);
            update_Table();
            top_Panel.repaint();
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selected_Row = table.getSelectedRow();
                    System.out.println("item selected" + selected_Row);

                    if (selected_Row != -1) {
                        int model_Row = table.convertRowIndexToModel(selected_Row);
                        selected_Product = get_Product_List(WestminsterShoppingManager.product_List.getProductlist(), drop_Down_Option).get(model_Row);
//                        System.out.println(selected_Product.displayProducts());
                        update_Display_Panel(selected_Product);
                    }
                }
            }
        });

        shopping_Cart_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCartFrame().update_Information();    //Open the ShoppingCart Frame by clicking the shopping_Cart_Button
            }
        });

        JButton add_Shopping_Cart_Button = new JButton("Add to Shopping Cart"); //Creating the Add to Shopping Cart button


        add_Shopping_Cart_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected_Product != null){
                    user_Shopping_Cart.add_product(selected_Product);
                    try{
                        ShoppingCartFrame.update_Information();
                        ShoppingCartFrame.update_Table_Model();
                    }catch (NullPointerException ignored){}
                }
            }
        });



        bottom_Panel1.add(bottom_Text);
        bottom_Panel1.add(id_Label);
        bottom_Panel1.add(category_Label);
        bottom_Panel1.add(name_Label);
        bottom_Panel1.add(extra_Label_1);
        bottom_Panel1.add(extra_Label_2);
        bottom_Panel1.add(available_Items_Label);
        bottom_Panel2.add(add_Shopping_Cart_Button);

        //Add Panels and elements
        //Add the main Panels for Frame
        westminsterFrame.add(top_Panel);
        westminsterFrame.add(bottom_Panel);

        //top_Panel adds
        top_Panel.add(top_Console_Panel); //Add the top_Console_Panel for top_Panel
        top_Panel.add(scroll_Pane);

        //Add the Panels for top_Console_Panel
        top_Console_Panel.add(top_Console_Panel1);
        top_Console_Panel.add(top_Console_Panel2);
        top_Console_Panel.add(top_Console_Panel3);

        //Add the Panels for bottom_Panel
        bottom_Panel.add(bottom_Panel1);
        bottom_Panel.add(bottom_Panel2);

        westminsterFrame.setVisible(true);

    }

    public static JTable create_Table(ArrayList<Product> product_List){                 //Create the table
        String[] column_Names = {"Product ID", "Name", "Category", "Price(£)", "Info"}; //Define the column names
        DefaultTableModel model = new DefaultTableModel(column_Names,0);        //Define the table model

        for (Product product: product_List){
            System.out.println(product.displayProducts());
            switch (product.getProduct_type()){
                case "Electronics":
                    Object[] row_Data_Electronics = {product.getProduct_Id(), product.getProduct_name(), product.getProduct_type(), product.getPrice(), product.getBrand()+", "+ product.getWarrenty_period()+" week Warranty"};
                    model.addRow(row_Data_Electronics); //Add data for the row for Electronics
                    break;

                case "Clothing":
                    Object[] row_Data_Clothing = {product.getProduct_Id(), product.getProduct_name(), product.getProduct_type(), product.getPrice(), product.getSize()+", "+ product.getColour()};
                    model.addRow(row_Data_Clothing);    //Add data for the row for Clothing
                    break;
            }
        }


        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);

        //Center the data in the table
        DefaultTableCellRenderer center_Render = new DefaultTableCellRenderer();
        center_Render.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center_Render);
        }

        return table;
    }

    public void update_Table(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        ArrayList<Product> product_List = get_Product_List(WestminsterShoppingManager.product_List.getProductlist(), drop_Down_Option);

        for (Product product: product_List) {

            switch (product.getProduct_type()) {
                case "Electronics":
                    Object[] row_Data_Electronics = {product.getProduct_Id(), product.getProduct_name(), product.getProduct_type(), product.getPrice(), product.getBrand() + ", " + product.getWarrenty_period() + " week Warranty"};
                    model.addRow(row_Data_Electronics); //Add data for the row for Electronics
                    break;

                case "Clothing":
                    Object[] row_Data_Clothing = {product.getProduct_Id(), product.getProduct_name(), product.getProduct_type(), product.getPrice(), product.getSize() + ", " + product.getColour()};
                    model.addRow(row_Data_Clothing);    //Add data for the row for Clothing
                    break;
            }
        }
    }

   //Create a method to sort the data in the table for Electronics and Clothing
    public static ArrayList<Product> get_Product_List(ArrayList<Product> product_List, String drop_Down_Option){
        ArrayList<Product> selected_Product = new ArrayList<>();
        switch (drop_Down_Option){
            case "All":
                selected_Product = product_List;
                break;

            case "Electronics":
                for (Product product: product_List){
                    if (product.getProduct_type().equals("Electronics")){
                        selected_Product.add(product);
                    }
                }
                break;

            case "Clothing":
                for (Product product: product_List){
                    if (product.getProduct_type().equals("Clothing")){
                        selected_Product.add(product);
                    }
                }
                break;
        }
        return selected_Product;
    }

    private static void update_Display_Panel(Product selectedProduct) {
        System.out.println(selectedProduct.displayProducts());

        id_Label.setText("Product Id: " + selected_Product.getProduct_Id());
        category_Label.setText("Category: " + selected_Product.getProduct_type());
        name_Label.setText("Name: " + selected_Product.getProduct_name());
        available_Items_Label.setText("Items Available: " + selected_Product.getNumber_of_available_items());

        switch (selected_Product.getProduct_type()) {
            case "Electronics":
                extra_Label_1.setText("Brand: " + selected_Product.getBrand());
                extra_Label_2.setText("Warranty Period: " + selected_Product.getWarrenty_period());
                break;
            case "Clothing":
                extra_Label_1.setText("Size: " + selected_Product.getSize());
                extra_Label_2.setText("Colour: " + selected_Product.getColour());
                break;
        }
    }


}
