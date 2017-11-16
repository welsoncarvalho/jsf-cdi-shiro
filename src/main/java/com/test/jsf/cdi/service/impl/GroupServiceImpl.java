package com.test.jsf.cdi.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.test.jsf.cdi.dao.GroupDao;
import com.test.jsf.cdi.model.Group;
import com.test.jsf.cdi.service.GroupService;

@Stateless
public class GroupServiceImpl implements GroupService {

    @Inject
    private GroupDao groupDao;

    @Override
    public Long countByFilter(Group filter) {
        return groupDao.countByFilter(filter);
    }

    @Override
    public List<Group> findByFilter(Group filter, int first, int max) {
        return groupDao.findByFilter(filter, first, max);
    }
    
    @Override
    public void save(Group group) {
        groupDao.saveOrUpdate(group);
    }
    
    @Override
    public List<Group> list() {
        return groupDao.listAll();
    }
    
    @Override
    public Group findWithFunctionalities(Long id) {
        return groupDao.findWithFunctionalities(id);
    }
    
}
