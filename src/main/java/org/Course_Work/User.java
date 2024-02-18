package org.Course_Work;

import java.util.HashMap;

public class User {
    private String user_name;
    private String password;

    HashMap<String,String> user_data = new HashMap<String,String>();

    User(){
        user_data.put("user1","password1");
        user_data.put("user2","password2");
        user_data.put("user3","password3");
    }



    //Constructor for User
    public User(String user_name, String password){
        this.user_name = user_name;
        this.password = password;
    }

    //Implement getters
    public String getUser_name(){
        return user_name;
    }

    public String getPassword(){
        return password;
    }

    //Implements setters
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
