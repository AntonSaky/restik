package com.example.restik.service.impl;

import com.example.restik.dao.DishDao;
import com.example.restik.exception.DAOException;
import com.example.restik.exception.ServiceException;
import com.example.restik.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {
    DishDao dishDao;

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
    @Override
    @Transactional
    public boolean makeOrder(int userId) throws ServiceException {
        try{
            dishDao.makeOrder(userId);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
        return true;
    }
}
