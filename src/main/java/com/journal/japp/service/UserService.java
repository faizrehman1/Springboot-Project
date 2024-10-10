package com.journal.japp.service;


import com.journal.japp.entity.User;
import com.journal.japp.repository.UsersRepository;
import com.journal.japp.request.UserRequest;
import com.journal.japp.response.GetUsersResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean saveNewUser(UserRequest user){
        boolean isAdded;
        try{
            User userNew = User.builder().userName(user.getUserName()).password(password.encode(user.getPassword())).build();
            userNew.setRoles(Arrays.asList("USER"));
            usersRepository.save(userNew);
            isAdded = true;
        }catch (Exception e){
            isAdded = false;
        }
     return isAdded;
    }

    public List<GetUsersResponse> getAll(){
        List<User> userList = usersRepository.findAll();
        List<GetUsersResponse> getUsersResponses = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            getUsersResponses.add(GetUsersResponse.builder().userName(userList.get(i).getUserName()).roles(userList.get(i).getRoles()).build());
        }
        return getUsersResponses;
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
