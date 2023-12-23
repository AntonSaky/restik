package com.example.restik.service;

import com.example.restik.exception.ServiceException;

public interface OrderService {

//    void createOrder(String address, List<CartItem> cart) throws ServiceException;
    public boolean makeOrder(int userId) throws ServiceException;
}
