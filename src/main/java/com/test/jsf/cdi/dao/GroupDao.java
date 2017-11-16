package com.test.jsf.cdi.dao;

import java.util.List;

import com.test.jsf.cdi.model.Group;

public interface GroupDao extends GenericDao<Group, Long> {

    Long countByFilter(Group filter);
    
    List<Group> findByFilter(Group filter, int first, int max);
    
    Group findWithFunctionalities(Long id);
    
}
