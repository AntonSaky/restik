package com.example.restik.service.impl;

import com.example.restik.beans.DishInfo;
import com.example.restik.dao.DishDao;
import com.example.restik.exception.DAOException;
import com.example.restik.exception.ServiceException;
import com.example.restik.model.Dish;
import com.example.restik.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private DishDao dishDao;

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    @Transactional
    public List<DishInfo> getUserDishesInBasket(int userId) throws ServiceException {
        List<DishInfo> list = null;
        try {
            list = dishDao.getDishesByUserId(userId);
        }catch (DAOException ex){
            throw new ServiceException(ex);
        }
        return list;

    }

    @Override
    @Transactional
    public List<Dish> getDishesByCategoryId(int categoryId) throws ServiceException {
        List<Dish> list = null;
        try {
            list = dishDao.getDishesListByCategoryId(categoryId);
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
        return list;
    }

    @Override
    @Transactional
    public void incrementDishAmount(int userId, int dishId) throws ServiceException{
        try {
            dishDao.incrementDishAmountInBasket(userId, dishId);
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
    }

    @Override
    @Transactional
    public void decrementDishAmount(int userId, int dishId)throws ServiceException {
        try {
            dishDao.decrementDishAmountInBasket(userId, dishId);
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
    }

    @Override
    @Transactional
    public void addDishToBasket(int userId, int dishId) throws ServiceException {
        try {
            dishDao.addDishToBasket(userId, dishId);
        }catch (DAOException ex){
            throw  new ServiceException(ex);
        }
    }
}
