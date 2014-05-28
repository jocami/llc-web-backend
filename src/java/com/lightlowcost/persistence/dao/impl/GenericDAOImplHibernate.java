/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lightlowcost.persistence.dao.impl;

import com.lightlowcost.persistence.dao.GenericDAO;
import com.lightlowcost.persistence.hibernate.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author miguel.martinez
 */
public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected SessionFactory sessionFactory;

    public GenericDAOImplHibernate() {

        this.sessionFactory = HibernateUtil.getSessionFactory();

    }

    @Override
    public T read(ID id) {

        Session session = sessionFactory.getCurrentSession();

        T t = (T) session.get(getEntityClass(), id);

        return t;
    }

    @Override
    public void insert(T t) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(t);

        session.getTransaction().commit();

    }

    @Override
    public void update(T t) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.update(t);

        session.getTransaction().commit();

    }

    @Override
    public void delete(ID id) {

        Session session = sessionFactory.getCurrentSession();

        T t = (T) session.get(getEntityClass(), id);

        session.beginTransaction();

        session.delete(t);

        session.getTransaction().commit();
    }

    @Override
    public List<T> findAll() {

        Session session = sessionFactory.getCurrentSession(); //Abrimos la sesion   

        Query query = session.createQuery("SELECT t FROM " + getEntityClass().getName() + " t");

        List<T> tList = query.list();

        return tList;
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}