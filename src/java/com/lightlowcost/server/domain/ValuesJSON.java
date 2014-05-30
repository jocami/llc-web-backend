/*
* Project: LightLowCost
* Package: com.lightlowcost.server.domain
* File: newJava JOCAMI.java
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

package com.lightlowcost.server.domain;

import java.util.ArrayList;
import java.util.List;

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/
public class ValuesJSON {
   /*
    * PROPERTIES
    */
    private String day;
    private List<Pair> carRate = new ArrayList();
    private List<Pair> normalRate = new ArrayList();
    private List<Pair> nightRate = new ArrayList();  
    /*
     * CONSTRUCTOR EMPTY
     */
    public ValuesJSON() {
    
    }

    /*
     * CONSTRUCTOR OVERLOADED
     */
    public ValuesJSON(String day) {
        this.day = day;
    }
    /**********************
     * GETTERS AND SETTERS
     **********************/
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Pair> getCarRate() {
        return carRate;
    }

    public void setCarRate(List<Pair> carRate) {
        this.carRate = carRate;
    }

    public List<Pair> getNormalRate() {
        return normalRate;
    }

    public void setNormalRate(List<Pair> normalRate) {
        this.normalRate = normalRate;
    }

    public List<Pair> getNightRate() {
        return nightRate;
    }

    public void setNightRate(List<Pair> nightRate) {
        this.nightRate = nightRate;
    }

    
    


}