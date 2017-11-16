package com.test.jsf.cdi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.test.jsf.cdi.dao.GroupDao;
import com.test.jsf.cdi.model.Group;

@Stateless
public class GroupDaoImpl extends GenericDaoImpl<Group, Long> implements GroupDao {

    public GroupDaoImpl() {
        super(Group.class);
    }

    @Override
    public Long countByFilter(Group filter) {
        
        Map<String, Object> params = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        
        hql.append("select count(g) from Group g ");
        queryByFilter(filter, hql, params);
        
        return countByParameters(hql.toString(), params);
    }

    @Override
    public List<Group> findByFilter(Group filter, int first, int max) {
        
        Map<String, Object> params = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        
        hql.append("From Group g ");
        queryByFilter(filter, hql, params);
        
        return findByParametersPaginator(hql.toString(), params, first, max);
    }
    
    private void queryByFilter(Group filter, StringBuilder hql, Map<String, Object> params) {
        hql.append("where 1 = 1 ");
        
        if(StringUtils.isNotBlank(filter.getName())) {
            hql.append("and g.name like :name ");
            params.put("name", "%" + filter.getName() + "%");
        }
    }
    
    @Override
    public Group findWithFunctionalities(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        
        String hql = "From Group g join fetch g.functionalities f where g.id = :id";
        
        return findSingleByParameters(hql, params);
    }
    
}
