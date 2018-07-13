package com.str.engg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.str.engg.model.User;

@Repository(value="userDao")
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
