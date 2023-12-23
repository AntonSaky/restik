package com.example.restik.beans;

import java.util.List;

public class AdminUserInfo {

    private String name;
    private String email;
    private String password;
    private List<DishInfo> dishesInBasket;

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setDishesInBasket(List<DishInfo> dishes){
        this.dishesInBasket = dishes;
    }
    public List<DishInfo> getDishesInBasket(){
        return this.dishesInBasket;
    }
}
