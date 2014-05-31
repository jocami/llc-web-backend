/*
 * Project: LightLowCost
 * Package: com.lightlowcost.server.domain
 * File: CarRate.java
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author JOCAMI
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "carrate")
public class CarRate implements Serializable{
    /*
     * PROPERTIES
     */
    @JsonIgnore
    @Id
    @Column(name = "id_carRate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_carRate;
    
    @Column(name = "hour")
    private String hour;
    
    @Column(name = "value")
    private Double value;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_day")
    private ValuesDay day;
    /*
     * CONSTRUCTOR EMPTY
     */

    public CarRate() {
    }

    /**
     * ********************
     * GETTERS AND SETTERS ********************
     */
    public Integer getId_carRate() {
        return id_carRate;
    }

    public void setId_carRate(Integer id_carRate) {
        this.id_carRate = id_carRate;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ValuesDay getDay() {
        return day;
    }

    public void setDay(ValuesDay day) {
        this.day = day;
    }

}
