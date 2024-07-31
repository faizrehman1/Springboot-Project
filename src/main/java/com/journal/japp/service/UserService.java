package com.journal.japp.service;


import com.journal.japp.entity.User;
import com.journal.japp.repository.UsersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UsersRepository usersRepository;


    public void saveUser(User user){
        usersRepository.save(user);
    }

    public List<User> getAll(){
        return usersRepository.findAll();
    }

    public User findbyUserName(String userName){
      return  usersRepository.findByUserName(userName);
    }



}
