/*
* Project: LightLowCost
* Package: com.lightlowcost.server.dao.impl.hibernate
* File: CarRateImplHibernate.java
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
import com.lightlowcost.server.dao.CarRateDAO;
import com.lightlowcost.server.domain.CarRate;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/
public class CarRateDAOImplHibernate extends GenericDAOImplHibernate<CarRate, Integer> implements CarRateDAO{
    public List<CarRate> findByDate(int date) {
        
        if (date == 0) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT carrate FROM CarRate carrate");

            List<CarRate> carRate = query.list();

            return carRate;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT carrate FROM CarRate carrate  WHERE id_day = ?");
            
            query.setInteger(0,date);
            
            List<CarRate> carRate = query.list();
            
            return carRate;
            
        }
    }

    @Override
    public boolean findByDateBool(int date) {
        boolean value = true;
        if (date == 0) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT carrate FROM CarRate carrate");

            List<CarRate> carRate = query.list();

            if (carRate.isEmpty() || carRate==null){
            
                value = false;
            }
            
            return value;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT carrate FROM CarRate carrate  WHERE id_day = ?");
            
            query.setInteger(0,date);
            
            List<CarRate> carRate = query.list();

            if (carRate.isEmpty() || carRate==null){
            
                value = false;
            }
            
            return value;
            
        }
    }

    
}