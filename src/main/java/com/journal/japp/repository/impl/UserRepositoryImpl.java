package com.journal.japp.repository.impl;

import com.journal.japp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserFromDB(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("Sami"));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

}
