package com.journal.japp.repository;

import com.journal.japp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

}
