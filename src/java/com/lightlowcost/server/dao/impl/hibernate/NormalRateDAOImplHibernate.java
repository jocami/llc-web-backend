/*
* Project: LightLowCost
* Package: com.lightlowcost.server.dao.impl.hibernate
* File: NormalRateImplHibernate.java
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
import com.lightlowcost.server.dao.NormalRateDAO;
import com.lightlowcost.server.domain.NightRate;
import com.lightlowcost.server.domain.NormalRate;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/
public class NormalRateDAOImplHibernate extends GenericDAOImplHibernate<NormalRate, Integer> implements NormalRateDAO{

    @Override
    public List<NormalRate> findByDate(int date) {
        if (date == 0) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT normalrate FROM Normalrate normalrate");

            List<NormalRate> normalRate = query.list();

            return normalRate;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT normalrate FROM Normalrate normalrate WHERE id_day = ?");
            
            query.setInteger(0,date);
            
            List<NormalRate> normalRate = query.list();
            
            return normalRate;
            
        }
         }

   

}