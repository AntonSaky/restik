package com.example.restik.service;

import com.example.restik.beans.AdminUserInfo;
import com.example.restik.beans.UserInfo;
import com.example.restik.exception.ServiceException;
import com.example.restik.model.User;

import java.util.List;

public interface UserService {

    User signIn(String login, String password) throws ServiceException;

    UserInfo registration(User user) throws ServiceException;

    List<AdminUserInfo> getAllUsersInfo() throws  ServiceException;

}
