package com.example.restik.model;

import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "dish_id")
    private int dish_id;

    @Column(name = "dish_amount")
    private int dish_amount;

    public int getRow_id(){
        return this.row_id;
    }
    public void setRow_id(int id){
        this.row_id = id;
    }
    public int getDish_id(){
        return this.dish_id;
    }
    public void setDish_id(int id){
        this.dish_id = id;
    }
    public int getUser_id(){
        return this.user_id;
    }
    public void setUser_id(int id){
        this.user_id = id;
    }
    public int getDish_amount(){
        return this.dish_amount;
    }
    public void setDish_amount(int amount){
        this.dish_amount = amount;
    }
}
