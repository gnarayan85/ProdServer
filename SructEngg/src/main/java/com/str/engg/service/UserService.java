package com.str.engg.service;

import java.util.List;

import com.str.engg.model.User;

public interface UserService {

    User save(User user);
    List<User> findAll();
    User findOne(String username);

    User activateUSer(long code);
}
