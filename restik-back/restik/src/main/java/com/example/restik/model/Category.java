package com.example.restik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Column(name = "category_id")
    private int category_id;
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "dish_id")
    private int dish_id;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCategory_id(int categoryId){
        this.category_id = categoryId;
    }
    public int getCategory_id(){
        return this.category_id;
    }
    public void setDish_id(int dishId){
        this.dish_id = dishId;
    }
    public int getDish_id(){
        return this.dish_id;
    }
}
