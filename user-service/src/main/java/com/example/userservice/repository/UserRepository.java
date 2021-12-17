package com.example.userservice.repository;

import com.example.userservice.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{}", fields = "{'name' : 1}")
    List<User> findAllUser();

    @Query(value = "{ name: '?0' }", fields = "{'password':0}")
    public User findUsersByName(String name);

    @Query(value = "{ name : '?0' }", fields = "{'item':0}")
    public User findUsersByNameAuth(String name);

}

