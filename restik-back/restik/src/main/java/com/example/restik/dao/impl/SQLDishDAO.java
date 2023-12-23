package com.example.restik.dao.impl;

import com.example.restik.beans.DishInfo;
import com.example.restik.dao.DishDao;
import com.example.restik.exception.DAOException;
import com.example.restik.model.Basket;
import com.example.restik.model.Category;
import com.example.restik.model.Dish;
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
public class SQLDishDAO implements DishDao {

    @Autowired
    EntityManager entityManager;
//    private SessionFactory sessionFactory;
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    public Basket getDishInBasket(int userId, int dishId, Session session){
        Basket basketRecord = null;
        try {
            String hql = "from Basket b where b.user_id = :u_id and b.dish_id = :d_id";
            Query query = session.createQuery(hql, Basket.class);
            query.setParameter("u_id", userId);
            query.setParameter("d_id", dishId);
            basketRecord = (Basket) query.uniqueResult();
        }catch(HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return basketRecord;
    }

    @Override
    public boolean decrementDishAmountInBasket(int userId, int dishId) throws DAOException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try{
            Basket basketRecord = getDishInBasket(userId, dishId, session);
            int amount = basketRecord.getDish_amount();
            amount--;
            if(amount != 0){
                basketRecord.setDish_amount(amount);
                session.merge(basketRecord);
            }else{
                session.remove(basketRecord);
            }
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return true;
    }

    @Override
    public boolean incrementDishAmountInBasket(int userId, int dishId) throws DAOException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try{
            Basket basketRecord = getDishInBasket(userId, dishId, session);
            int amount = basketRecord.getDish_amount();
            amount++;
            basketRecord.setDish_amount(amount);
            session.merge(basketRecord);
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<DishInfo> getDishesByUserId(int user_id, Session session) throws DAOException {
        List<DishInfo> dishes = new ArrayList<DishInfo>();
        List<Basket> baskeArrt = null;
        try{
            String hql = "from Basket b where b.user_id = :id";
            Query query = session.createQuery(hql, Basket.class);
            query.setParameter("id", user_id);
            baskeArrt = query.list();

            for (int i = 0; i < baskeArrt.size(); i++) {
                hql = "from Dish d where d.dish_id = :id";
                query = session.createQuery(hql, Dish.class);
                query.setParameter("id", baskeArrt.get(i).getDish_id());
                Dish dish = (Dish)query.uniqueResult();
                DishInfo dishInfo = new DishInfo();
                dishInfo.setDish(dish);
                dishInfo.setAmount(baskeArrt.get(i).getDish_amount());
                dishes.add(dishInfo);
            }
        }catch(HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return dishes;
    }
    @Override
    public List<DishInfo> getDishesByUserId(int user_id) throws DAOException {
        List<DishInfo> dishes = new ArrayList<DishInfo>();
        List<Basket> baskeArrt = null;
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try{
            String hql = "from Basket b where b.user_id = :id";
            Query query = session.createQuery(hql, Basket.class);
            query.setParameter("id", user_id);
            baskeArrt = query.list();

            for (int i = 0; i < baskeArrt.size(); i++) {
                hql = "from Dish d where d.dish_id = :id";
                query = session.createQuery(hql, Dish.class);
                query.setParameter("id", baskeArrt.get(i).getDish_id());
                Dish dish = (Dish)query.uniqueResult();
                DishInfo dishInfo = new DishInfo();
                dishInfo.setDish(dish);
                dishInfo.setAmount(baskeArrt.get(i).getDish_amount());
                dishes.add(dishInfo);
            }
        }catch(HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return dishes;
    }

    @Override
    public Dish getDishById(int id) throws DAOException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        Dish dish = null;
        try{
            dish = session.get(Dish.class, id);
        }catch (HibernateException e){
            throw new HibernateException("Error during db process. Dish doesn't exist");
        }
        return dish;
    }

    @Override
    public boolean addDishToBasket(int userId, int dishId) throws DAOException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try{
            Basket basketRecord = getDishInBasket(userId, dishId, session);
            if(basketRecord == null){
                basketRecord = new Basket();
                basketRecord.setDish_id(dishId);
                basketRecord.setUser_id(userId);
                basketRecord.setDish_amount(1);
            }else{
                int amount = basketRecord.getDish_amount();
                amount++;
                basketRecord.setDish_amount(amount);
            }
            session.persist(basketRecord);
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> getDishesListByCategoryId(int categoryId) throws DAOException {
        List<Dish> dishes = new ArrayList<>();
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try {
            String hql = "from Category c where c.category_id = :id";
            Query query = session.createQuery(hql, Dish.class);
            query.setParameter("id", categoryId);
            List<Category> categories= query.list();
            for (int i = 0; i < categories.size(); i++) {
                hql = "from Dish d where d.dish_id = :dish_id";
                query = session.createQuery(hql, Dish.class);
                query.setParameter("dish_id", categories.get(i).getDish_id());
                Dish dish = (Dish)query.uniqueResult();
                dishes.add(dish);
            }
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return dishes;
    }

    @Override
    public boolean makeOrder(int userId){
//        Session session = sessionFactory.getCurrentSession();
        Session session = entityManager.unwrap(Session.class);
        try{
                String hql = "delete from Basket b where b.user_id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", userId);
                int rows = (Integer) query.executeUpdate();
        }catch (HibernateException e){
            throw new HibernateException("Error during db process.");
        }
        return true;
    }
}