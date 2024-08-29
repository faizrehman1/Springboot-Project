package com.journal.japp.service;


import com.journal.japp.entity.User;
import com.journal.japp.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    private static final PasswordEncoder password = new BCryptPasswordEncoder();

    public void saveUser(User user){
        usersRepository.save(user);
    }

    public boolean saveNewUser(User user){
        boolean isAdded;
        try{
            user.setPassword(password.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            usersRepository.save(user);
            isAdded = true;
        }catch (Exception e){
            isAdded = false;
        }
     return isAdded;
    }

    public List<User> getAll(){
        return usersRepository.findAll();
    }

    public User findbyUserName(String userName){
      return  usersRepository.findByUserName(userName);
    }

    public String logout(HttpServletRequest request) {
        tokenBlacklistService.addToBlacklist(request);
        // Clear any session-related data if necessary
        return "Logged out successfully";
    }


}
