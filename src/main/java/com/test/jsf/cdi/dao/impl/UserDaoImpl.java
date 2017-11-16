package com.test.jsf.cdi.dao.impl;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.test.jsf.cdi.dao.UserDao;
import com.test.jsf.cdi.model.User;
import java.util.HashMap;
import java.util.List;
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
    
    @Override
    public List<User> findByFilter(User filter) {
        Map<String, Object> params = new HashMap<>();
        
        StringBuilder hql = new StringBuilder();
        hql.append("From User u where 1 = 1 ");
        
        if(StringUtils.isNotBlank(filter.getName())) {
            hql.append("and u.name like :name ");
            params.put("name", "%" + filter.getName() + "%");
        }
        
        if(StringUtils.isNotBlank(filter.getEmail())) {
            hql.append("and u.email like :email ");
            params.put("email", "%" + filter.getEmail() + "%");
        }
        
        return findByParameters(hql.toString(), params);
    }
    
}
