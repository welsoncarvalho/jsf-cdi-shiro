package com.test.jsf.cdi.service;

import java.util.List;

import com.test.jsf.cdi.model.User;

public interface UserService {

    List<User> list();
    
    User findByLogin(String login);
}
