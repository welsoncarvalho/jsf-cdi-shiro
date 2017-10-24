package com.test.jsf.cdi.dao;

import com.test.jsf.cdi.model.User;

public interface UserDao extends GenericDao<User, Long> {

    User findByLogin(String login);
    
}
