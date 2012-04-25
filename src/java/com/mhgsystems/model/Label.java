/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */

package com.mhgsystems.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bazilinskyy Pavlo
 */
@Table(name="label")
public class Label  implements Serializable{
    
    /**
     *
     */
    public Label() {

    }
    
    @Id
    @Column(name="label_id")
    private int id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     *
     */
    @Column(name="en")
    protected String en;

    /**
     * Get the value of en
     *
     * @return the value of en
     */
    public String getEn() {
        return en;
    }

    /**
     * Set the value of en
     *
     * @param en new value of en
     */
    public void setEn(String en) {
        this.en = en;
    }

    @Column(name="fi")
    protected String fi;

    /**
     * Get the value of fi
     *
     * @return the value of fi
     */
    public String getFi() {
        return fi;
    }

    /**
     * Set the value of fi
     *
     * @param fi new value of fi
     */
    public void setFi(String fi) {
        this.fi = fi;
    }

    @Column(name="ru")
    protected String ru;

    /**
     * Get the value of ru
     *
     * @return the value of ru
     */
    public String getRu() {
        return ru;
    }

    /**
     * Set the value of ru
     *
     * @param ru new value of ru
     */
    public void setRu(String ru) {
        this.ru = ru;
    }

    @Column(name="uk")
    protected String uk;

    /**
     * Get the value of uk
     *
     * @return the value of uk
     */
    public String getUk() {
        return uk;
    }

    /**
     * Set the value of uk
     *
     * @param uk new value of uk
     */
    public void setUk(String uk) {
        this.uk = uk;
    }


}
