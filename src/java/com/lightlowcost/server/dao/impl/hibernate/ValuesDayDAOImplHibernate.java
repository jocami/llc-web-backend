/*
* Project: LightLowCost
* Package: com.lightlowcost.server.dao.impl.hibernate
* File: ValuesDayImplHibernate.java
* Date: 30-may-2014
* Encoding: UTF-8
* License: default
*
* Copyright(c) Jocami 2014
* www.jocami.com
* admin@jocami.com
*
* This file is part of Jocami.
* Jocami is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* any later version.
*
* Jocami is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Jocami. If not, see <http://www.gnu.org/licenses/>.
*/

package com.lightlowcost.server.dao.impl.hibernate;

import com.lightlowcost.persistence.dao.impl.GenericDAOImplHibernate;
import com.lightlowcost.server.dao.ValuesDayDAO;
import com.lightlowcost.server.domain.ValuesDay;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/
public class ValuesDayDAOImplHibernate extends GenericDAOImplHibernate<ValuesDay, Integer> implements ValuesDayDAO{
    @Override
    public ValuesDay findByDate(int date) {
        
        if (date == 0) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT valuesday FROM ValuesDay valuesday");

            ValuesDay valuesDay = (ValuesDay) query.uniqueResult();

            return valuesDay;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT valuesday FROM ValuesDay valuesday  WHERE id_valuesday = ?");
            
            query.setInteger(0,date);
            
            ValuesDay valuesDay = (ValuesDay) query.uniqueResult();
            
            return valuesDay;
            
        }
    }
    
    @Override
    public boolean findByDateBool(int date){
    boolean value = true;
        if (date == 0) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT valuesDay FROM ValuesDay valuesday");

            List<ValuesDay> valuesDay = query.list();

            if (valuesDay.isEmpty() || valuesDay==null){
            
                value = false;
            }
            
            return value;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT valuesday FROM ValuesDay valuesday  WHERE id_valuesday = ?");
            
            query.setInteger(0,date);
            
            List<ValuesDay> valuesDay = query.list();

            if (valuesDay.isEmpty() || valuesDay==null){
            
                value = false;
            }
            
            return value;
            
          }
     }

}