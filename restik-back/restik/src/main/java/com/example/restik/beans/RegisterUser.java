package com.example.restik.beans;

public class RegisterUser {
    private String username;
    private String password;
    private String email;

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

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
}
