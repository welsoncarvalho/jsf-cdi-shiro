package com.test.jsf.cdi.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.test.jsf.cdi.dao.GenericDao;
import com.test.jsf.cdi.model.AbstractModel;

public class GenericDaoImpl<T extends AbstractModel, I> implements GenericDao<T, I> {

    @PersistenceContext(unitName = "appPU")
    private EntityManager em;
    
    private Class<T> modelClass;
    
    protected GenericDaoImpl(Class<T> modelClass) {
        this.modelClass = modelClass;
    }
    
    @Override
    public T saveOrUpdate(T t) {
        if(t.getIdObject() != null && 
                this.em.find(t.getClass(), t.getIdObject()) != null) {
            
            t = this.em.merge(t);
        } else {
            this.em.persist(t);
        }
        return t;
    }

    @Override
    public T findOne(I id) {
        return this.em.find(this.modelClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> listAll() {
        String query = "From " + modelClass.getName();
        return em.createQuery(query).getResultList();
    }
    
    protected Long countByParameters(String hql, Map<String, Object> params) {
        
        Query query = this.em.createQuery(hql);
        setParametersOnQuery(query, params);
        return (Long) query.getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
    protected List<T> findByParameters(String hql, Map<String, Object> params) {
        
        Query query = this.em.createQuery(hql);
        setParametersOnQuery(query, params);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByParametersPaginator(String hql, Map<String, Object> params, int first, int max) {
        
        Query query = this.em.createQuery(hql);
        setParametersOnQuery(query, params);
        query.setFirstResult(first);
        query.setMaxResults(max);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    protected T findSingleByParameters(String hql, Map<String, Object> params) {
        
        Query query = this.em.createQuery(hql);
        setParametersOnQuery(query, params);
        
        return (T) query.getSingleResult();
    }
    
    protected void setParametersOnQuery(Query query, Map<String, Object> params) {
        
        if(params != null) {
            params.entrySet().forEach(stringObjectEntry -> {
                query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
            });
        }
    }


}
