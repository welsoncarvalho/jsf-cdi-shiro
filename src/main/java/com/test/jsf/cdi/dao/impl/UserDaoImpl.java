package com.test.jsf.cdi.dao.impl;

import javax.ejb.Stateless;

import com.test.jsf.cdi.dao.UserDao;
import com.test.jsf.cdi.model.User;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByLogin(String login) {
        StringBuilder hql = new StringBuilder();
        hql.append("From User u where u.login = :login ");
        
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        
        return findSingleByParameters(hql.toString(), params);
    }
    
}
