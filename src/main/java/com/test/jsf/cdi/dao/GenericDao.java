package com.test.jsf.cdi.dao;

import java.util.List;

import com.test.jsf.cdi.model.AbstractModel;

public interface GenericDao<T extends AbstractModel, I> {

    T saveOrUpdate(T t);
    
    T findOne(I id);
    
    List<T> listAll();
    
}
