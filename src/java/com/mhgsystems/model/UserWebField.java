/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.model;

import com.mhgsystems.commons.UITextHandler;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bazilinskyy Pavlo
 */
@Table(name = "user_webfield")
public class UserWebField implements Serializable {

    /**
     * 
     */
    protected UITextHandler uiTextHandler;

    /**
     *
     */
    public UserWebField() {
        this.uiTextHandler = new UITextHandler();
    }
    @Id
    @Column(name = "user_id")
    private int userId;

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
    
    @Column(name = "webfield_id")
    private int webFieldId;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getWebFieldId() {
        return webFieldId;
    }

    /**
     * Set the value of id
     *
     * @param webFieldId 
     */
    public void setWebFieldId(int webFieldId) {
        this.webFieldId = webFieldId;
    }
}
