package com.example.restik.dao;

import com.example.restik.beans.DishInfo;
import com.example.restik.exception.DAOException;
import com.example.restik.model.Dish;

import java.util.List;


public interface DishDao {

    List<Dish> getDishesListByCategoryId(int categoryId) throws DAOException;

    boolean decrementDishAmountInBasket(int userId, int dishId) throws DAOException;
    boolean incrementDishAmountInBasket(int userId, int dishId) throws DAOException;

    boolean addDishToBasket(int userId, int dishId) throws DAOException;

    Dish getDishById(int id) throws DAOException;

    List<DishInfo> getDishesByUserId(int id) throws DAOException;

    boolean makeOrder(int userId) throws DAOException;
}
