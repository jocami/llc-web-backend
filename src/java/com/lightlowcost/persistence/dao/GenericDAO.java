/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lightlowcost.persistence.dao;

import java.util.List;

/**
 *
 * @author miguel.martinez
 */
public interface GenericDAO<T, ID> {

    T read(ID id);
    
    void insert(T t);

    void update(T t);

    void delete(ID id);

    List<T> findAll();
}