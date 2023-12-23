package com.example.restik.beans;

public class SignInUser {
    private String username;
    private String password;

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setUsername(String name){
        this.username = name;
    }
    public String getUsername(){
        return this.username;
    }
}
