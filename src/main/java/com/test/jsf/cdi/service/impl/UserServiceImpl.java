package com.test.jsf.cdi.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.test.jsf.cdi.dao.UserDao;
import com.test.jsf.cdi.model.User;
import com.test.jsf.cdi.service.UserService;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;
    
    @Override
    public List<User> list() {
        return userDao.listAll();
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
    
    @Override
    public List<User> findByFilter(User filter) {
        return userDao.findByFilter(filter);
    }
    
    @Override
    public void save(User user) {
        userDao.saveOrUpdate(user);
    }

}
