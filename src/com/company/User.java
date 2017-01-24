package com.company;

import java.util.ArrayList;

/**
 * Created by ryankielty on 12/23/16.
 */
public class User {
    String userName;
    String password;

    ArrayList<Albums> albumsList = new ArrayList<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
