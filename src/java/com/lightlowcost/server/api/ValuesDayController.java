/*
* Project: LightLowCost
* Package: com.lightlowcost.server.api
* File: ValuesDayController.java
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightlowcost.server.dao.ValuesDayDAO;
import com.lightlowcost.server.domain.ValuesDay;
import java.io.IOException;
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
public class ValuesDayController {
    
    @Autowired
    ValuesDayDAO valuesDayDAO;
    
     @RequestMapping(value = {"/valuesDay/{fecha}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable("fecha")String fechaStr) {

        try {
            
            int dateInt = Integer.parseInt(fechaStr);
            if (valuesDayDAO.findByDateBool(dateInt)) {
                ValuesDay valuesDayRead = valuesDayDAO.findByDate(dateInt);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");
                
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonOutput = objectMapper.writeValueAsString(valuesDayRead);
                response.getWriter().println(jsonOutput);
                
            }
            else {
                
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");
                response.getWriter().println("Date not found");
                
                //BussinesMessage mensaje = new BussinesMessage();
                //mensaje.setMensaje("No se ha encontrado ningun empleado para actualizar.");
            }

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }
    }
}

