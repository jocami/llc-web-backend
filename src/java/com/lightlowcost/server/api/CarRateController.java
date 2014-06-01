/*
* Project: LightLowCost
* Package: com.lightlowcost.server.api
* File: CarRateController.java
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

package com.lightlowcost.server.api;

import com.lightlowcost.server.dao.CarRateDAO;
import com.lightlowcost.server.dao.ValuesDayDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/

@Controller
public class CarRateController {

    
    @Autowired
    CarRateDAO carRateDAO;
    
     @RequestMapping(value = {"/valuesCarDay/{fecha}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable("fecha")String fechaStr) {
        int dateInt = Integer.parseInt(fechaStr);
         
        try {
            if (carRateDAO.findByDateBool(dateInt)){
           
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");
                response.getWriter().println("Existe");
            
          }
        else{
            
                response.setStatus(HttpServletResponse.SC_ACCEPTED );
                response.setContentType("application/json; chaset=UTF-8");
                response.getWriter().println("No existe");
        }
            } catch (IOException ex) {
                Logger.getLogger(CarRateController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}