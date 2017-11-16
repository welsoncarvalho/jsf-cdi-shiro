package com.test.jsf.cdi.dao;

import java.util.List;

import com.test.jsf.cdi.model.User;

public interface UserDao extends GenericDao<User, Long> {

    User findByLogin(String login);
    
    List<User> findByFilter(User filter);
    
}
