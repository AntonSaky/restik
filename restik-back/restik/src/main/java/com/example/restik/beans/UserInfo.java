package com.example.restik.beans;

public class UserInfo {
    private int id;
    private String name;
    private boolean isAuth;
    private String role;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
    public void setIsAuth(boolean isAuth){
        this.isAuth = isAuth;
    }
    public boolean getIsAuth(){
        return this.isAuth;
    }
}
