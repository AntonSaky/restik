package com.example.restik.dao.impl;

import com.example.restik.beans.AdminUserInfo;
import com.example.restik.beans.DishInfo;
import com.example.restik.beans.UserInfo;
import com.example.restik.dao.UserDao;
import com.example.restik.exception.DAOException;
import com.example.restik.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SQLUserDAO implements UserDao {
    @Autowired
    EntityManager entityManager;
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AdminUserInfo> GetAllUsersInfo() throws DAOException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        List<AdminUserInfo> adminUsers = new ArrayList<>();
        try{
            String hql = "from User";
            Query query = session.createQuery(hql, User.class);
            List<User> users = query.list();
            SQLDishDAO dishDao = new SQLDishDAO();
            for (int i = 0; i < users.size(); i++) {
                List<DishInfo> dishes = dishDao.getDishesByUserId(users.get(i).getId(), session);
                AdminUserInfo adminUserInfo = new AdminUserInfo();
                adminUserInfo.setName(users.get(i).getLogin());
                adminUserInfo.setEmail(users.get(i).getEmail());
                adminUserInfo.setPassword(users.get(i).getPassword());
                adminUserInfo.setDishesInBasket(dishes);
                adminUsers.add(adminUserInfo);
            }
        }catch(HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return adminUsers;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User signIn(String login, String password) throws DAOException {
//        Session session = this.sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try {
            String hql = "from User u where u.name = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", login);
            User user = query.uniqueResult();
            if(user != null){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return null;
    }

    @Override
    public UserInfo registration(User user) throws DAOException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        UserInfo userInfo = null;
        try{
            session.persist(user);
            userInfo = new UserInfo();
            userInfo.setIsAuth(true);
            userInfo.setName(user.getLogin());
            userInfo.setId(userInfo.getId());
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return userInfo;
    }
}
