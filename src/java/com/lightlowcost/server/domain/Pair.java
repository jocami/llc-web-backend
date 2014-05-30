/*
* Project: LightLowCost
* Package: com.lightlowcost.server.domain
* File: Pair.java
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

/**
* @author JOCAMI
* @version 1.0
* @since 1.0
*/
public class Pair {
   /*
    * PROPERTIES
    */
    private String hour;
    private String value;
    /*
     * CONSTRUCTOR EMPTY
     */
    public Pair() {
    }
    /*
     * CONSTRUCTOR OVERLOADED
     */
    public Pair(String hour, String value) {
        this.hour = hour;
        this.value = value;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}