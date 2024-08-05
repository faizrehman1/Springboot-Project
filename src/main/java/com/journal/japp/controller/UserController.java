package com.journal.japp.controller;


import com.journal.japp.entity.User;
import com.journal.japp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAll(){
       return userService.getAll();
    }


    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


    @PutMapping
    public ResponseEntity<Object> udpateUserByid(@RequestBody User user){

      User user1 =  userService.findbyUserName(user.getUserName());

      if(user1!=null){
          user1.setUserName(user.getUserName());
          user1.setPassword(user.getPassword());
          userService.saveUser(user1);
      }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


}
