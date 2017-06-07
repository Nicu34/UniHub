package com.websystique.springmvc.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Gets generic object by primary key.
     * @param key
     * @return generic object with above criteria
     */
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    /**
     * Saves the object into database.
     * @param entity
     */
    public void persist(T entity) {
        getSession().persist(entity);
    }

    /**
     * Updates the existing object with given one
     * @param entity
     */
    public void update(T entity) {
        getSession().update(entity);
    }

    /**
     * Deletes the object by given entity
     * @param entity
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

}
