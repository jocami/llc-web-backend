/*
 * Project: LightLowCost
 * Package: com.lightlowcost.server.domain
 * File: Day.java
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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 * @author JOCAMI
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "valuesday")
public class ValuesDay implements Serializable{
    /*
     * PROPERTIES
     */
    @Id
    @Column(name="id_valuesday")
    private Integer id_valuesday;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_day")
    @IndexColumn(name = "idx")    
    private List<NightRate> nightRate = new ArrayList();    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_day")
    @IndexColumn(name = "idx")
    private List<NormalRate> normalRate = new ArrayList();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_day")
    @IndexColumn(name = "idx")
    private List<CarRate> carRate = new ArrayList();

    /*
     * CONSTRUCTOR EMPTY
     */
    public ValuesDay() {
    }

    /**
     * GETTERS AND SETTERS
     */
    public Integer getId_valuesday() {
        return id_valuesday;
    }

    public void setId_valuesday(Integer id_valuesday) {
        this.id_valuesday = id_valuesday;
    }

    public List<NormalRate> getNormalRate() {
        return normalRate;
    }

    public void setNormalRate(List<NormalRate> normalRate) {
        this.normalRate = normalRate;
    }

    public List<NightRate> getNightRate() {
        return nightRate;
    }

    public void setNightRate(List<NightRate> nightRate) {
        this.nightRate = nightRate;
    }

    public List<CarRate> getCarRate() {
        return carRate;
    }

    public void setCarRate(List<CarRate> carRate) {
        this.carRate = carRate;
    }
    
    

}
