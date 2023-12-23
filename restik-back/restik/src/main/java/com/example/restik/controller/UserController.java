package com.example.restik.controller;


import com.example.restik.beans.*;
import jakarta.servlet.http.HttpSession;
import com.example.restik.exception.ServiceException;
import com.example.restik.model.User;
import com.example.restik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(@RequestBody SignInUser signInUser) {
        UserInfo userInfo = null;
        try {
            User user = userService.signIn(signInUser.getUsername(), signInUser.getPassword());
            if (user != null) {
                if (user.getRole().equals(UserRoles.ADMIN)) {
                    userInfo = new UserInfo();
                    userInfo.setRole(UserRoles.ADMIN);
                } else {
                    userInfo = new UserInfo();
                    userInfo.setId(user.getId());
                    userInfo.setName(user.getLogin());
                    userInfo.setIsAuth(true);
                    userInfo.setRole(UserRoles.USER);
                }
            } else {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный логин или пароль");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/signOut")
    public ResponseEntity<String> exit(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.removeAttribute("user");
        return ResponseEntity.ok("Успешный выход");
    }

    @PostMapping("/registration")
    public ResponseEntity<UserInfo> signUp(@RequestBody RegisterUser registerUser) {
        UserInfo userInfo = null;
        try {
            User user = new User();
            user.setPassword(registerUser.getPassword());
            user.setLogin(registerUser.getUsername());
            user.setEmail(registerUser.getEmail());
            user.setRole(UserRoles.USER);
            userInfo = userService.registration(user);
            userInfo.setRole(UserRoles.USER);
            if (userInfo != null) {
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession();
                session.setAttribute("user", userInfo);
            } else {
                System.out.println("Server error");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<AdminUserInfo>> showAdminPage(RedirectAttributes redirectAttributes) {
        List<AdminUserInfo> users = new ArrayList<>();
        try {
            users = userService.getAllUsersInfo();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(users);
    }
}
