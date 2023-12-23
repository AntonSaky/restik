package com.example.restik.dao;

import com.example.restik.beans.AdminUserInfo;
import com.example.restik.beans.UserInfo;
import com.example.restik.exception.DAOException;
import com.example.restik.model.User;

import java.util.List;

public interface UserDao {

    List<AdminUserInfo> GetAllUsersInfo() throws DAOException;

    User signIn(String login, String password) throws DAOException;

    UserInfo registration(User user) throws DAOException;

}
