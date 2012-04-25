/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.model;

import com.mhgsystems.commons.Logger;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */

@Table(name="received_value")
public class ReceivedValue implements Serializable {

    /**
     *
     */
    public ReceivedValue() {
    }

    @Id
    @Column(name="received_value_id")
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
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="value")
    private String value;

    /**
     * Get the value of companyId
     *
     * @return the value of companyId
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     *
     * @param value 
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Column(name="model")
    private String model;

    /**
     * Get the value of model
     *
     * @return the value of model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the value of model
     *
     * @param model new value of model
     */
    public void setModel(String model) {
        this.model = model;
    }

    @Column(name="webfield_id")
    protected int webfieldId;

    /**
     * Get the value of webfieldId
     *
     * @return the value of webfieldId
     */
    public int getWebfieldId() {
        return webfieldId;
    }

    /**
     * Set the value of webfieldId
     *
     * @param webfieldId new value of webfieldId
     */
    public void setWebfieldId(int webfieldId) {
        this.webfieldId = webfieldId;
    }

    @Column(name="user_id")
    protected int userId;

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
