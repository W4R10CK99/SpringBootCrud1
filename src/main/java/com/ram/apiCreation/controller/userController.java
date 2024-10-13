package com.ram.apiCreation.controller;

import com.ram.apiCreation.Service.UserService;
import com.ram.apiCreation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public boolean adduser(@RequestBody User user){
        userService.saveUser(user);
        return true;
    }

    @GetMapping
    public ResponseEntity<?> showUsers(){
        List<User> allUsers = userService.getAllUsers();
        if(allUsers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
