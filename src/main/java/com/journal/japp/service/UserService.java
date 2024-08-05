package com.journal.japp.service;


import com.journal.japp.entity.User;
import com.journal.japp.repository.UsersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final PasswordEncoder password = new BCryptPasswordEncoder();

    public void saveUser(User user){
        usersRepository.save(user);
    }

    public void saveNewUser(User user){
        user.setPassword(password.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        usersRepository.save(user);
    }

    public List<User> getAll(){
        return usersRepository.findAll();
    }

    public User findbyUserName(String userName){
      return  usersRepository.findByUserName(userName);
    }



}
