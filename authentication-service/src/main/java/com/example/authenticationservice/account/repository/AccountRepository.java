package com.example.authenticationservice.account.repository;

import com.example.authenticationservice.account.pojo.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    @Query(value = "{}", fields = "{'name' : 1}")
    List<Account> findAllAccount();


    @Query(value = "{ name: '?0'}", fields = "{ 'items':0 }")
    public Account findAccountsByNameAuth(String name);
}
