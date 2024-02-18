package org.Course_Work;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame {

        public JFrame userframe = new JFrame("User Profile");  //Define the Frame
        public JPanel main_Panel;
        public static JLabel user_name_Label, password_Label;
        public static JTextField user_name, password;
        public UserFrame(){
            userframe.setSize(200,250);
            userframe.setLayout(new FlowLayout());



            user_name_Label = new JLabel("User Name: ");
            password_Label = new JLabel("Password: ");

            user_name = new JTextField(15);
            password = new JTextField(15);

            JButton sign_in_button = new JButton("Sign in");

            sign_in_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = user_name.getText();
//                    WestminsterShoppingManager.user_data.getUser_name().equals(username);

                    new WestminsterFrame();
                }
            });




            userframe.add(user_name_Label);
            userframe.add(user_name);
            userframe.add(password_Label);
            userframe.add(password);
            userframe.add(sign_in_button);

            userframe.setVisible(true);

        }

}
