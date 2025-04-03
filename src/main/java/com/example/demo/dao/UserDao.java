package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao {
    void save(User user);
    User findByEmailAndPassword(String email, String password);
}
