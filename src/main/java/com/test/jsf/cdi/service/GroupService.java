package com.test.jsf.cdi.service;

import java.util.List;

import com.test.jsf.cdi.model.Group;

public interface GroupService {
    
    Long countByFilter(Group filter);
    
    List<Group> findByFilter(Group filter, int first, int max);
    
    void save(Group group);
    
}
