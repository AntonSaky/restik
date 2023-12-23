package com.example.restik.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int dish_id;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public int price;

    @Column(name = "description")
    public String description;

    @Column(name = "img")
    public String img;

//    public int amount;

    public Dish(){
//        this.amount = 0;
    }
    public Dish(int id, String dishName, int price, String descr, String image) {
        this.dish_id = id;
        this.name = dishName;
        this.price = price;
        this.description = descr;
        this.img = image;
//        this.amount = 0;
    }

    public Dish Clone() {
        return new Dish(dish_id, name, price, description, img);
    }

    public String getName() {
        return name;
    }

    public void setName(String dishName) {
        this.name = dishName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

//    public int getAmount() {
//        return amount;
//    }
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }

    public int getId() {
        return dish_id;
    }

    public void setId(int id) {
        this.dish_id = id;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String image) {
        this.img = image;
    }
}
