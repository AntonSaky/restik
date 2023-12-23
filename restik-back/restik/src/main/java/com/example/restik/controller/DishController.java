package com.example.restik.controller;

import com.example.restik.beans.DishInfo;
import com.example.restik.beans.UserInfo;
import com.example.restik.exception.ServiceException;
import com.example.restik.model.Dish;
import com.example.restik.service.DishService;
import com.example.restik.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DishController {
    private DishService dishService;
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }

    @Autowired
    public void setDishService(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping("/basket/{user_id}")
    public ResponseEntity<List<DishInfo>> showBasket(@PathVariable("user_id") Integer userId){
        List<DishInfo> dishesInfo = null;
        try {
                dishesInfo = dishService.getUserDishesInBasket(userId);

        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(dishesInfo);
    }

    @PostMapping("/basket/{user_id}/increment/{dish_id}")
    public ResponseEntity<Object> increment(@PathVariable("dish_id") Integer dish_id, @PathVariable("user_id") Integer user_id){
        try {
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession();
//            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            dishService.incrementDishAmount(user_id, dish_id);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/basket/{user_id}/decrement/{dish_id}")
    public ResponseEntity<Object> decrement(@PathVariable("dish_id") Integer dish_id, @PathVariable("user_id") Integer user_id){
        try {
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession();
//            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            dishService.decrementDishAmount(user_id, dish_id);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/single-category/{category_id}")
    public ResponseEntity<List<Dish>> showCategory(@PathVariable("category_id") Integer category_id){
        List<Dish> dishes = new ArrayList<>();
        try{
            dishes =  dishService.getDishesByCategoryId(category_id);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(dishes);
    }

    @PostMapping("/single-category/{dish_id}/{category_id}/add-to-basket")
    public ResponseEntity<Object> addToBasket(@PathVariable("dish_id") Integer dishId, @PathVariable("category_id") Integer category_id,
                                           @RequestBody UserInfo userInfo){
        try {
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession();
//            UserInfo userinfo = (UserInfo) session.getAttribute("user");
            int userId = userInfo.getId();
            dishService.addDishToBasket(userId, dishId);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/basket/order/{userId}")
    public void makeOrder(@PathVariable("userId") Integer userId){
        try {
            orderService.makeOrder(userId);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }

    }
}
