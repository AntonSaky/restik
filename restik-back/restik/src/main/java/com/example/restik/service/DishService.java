package com.example.restik.service;

import com.example.restik.beans.DishInfo;
import com.example.restik.exception.ServiceException;
import com.example.restik.model.Dish;

import java.util.List;

public interface DishService {
    public List<DishInfo> getUserDishesInBasket(int userId) throws ServiceException;

    public List<Dish> getDishesByCategoryId(int id) throws ServiceException;

    public void decrementDishAmount(int userId, int dishId) throws ServiceException;

    public void incrementDishAmount(int userId, int dishId) throws ServiceException;

    public void addDishToBasket(int userId, int dishId) throws ServiceException;

}
