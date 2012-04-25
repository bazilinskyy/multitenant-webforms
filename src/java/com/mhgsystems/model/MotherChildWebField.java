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
@Table(name = "mother_child_webfield")
public class MotherChildWebField implements Serializable {

    /**
     * 
     */
    protected UITextHandler uiTextHandler;

    /**
     *
     */
    public MotherChildWebField() {
        this.uiTextHandler = new UITextHandler();
    }
    @Column(name = "mother_id")
    private int motherId;

    /**
     * Get the value of motherId
     *
     * @return the value of motherId
     */
    public int getMotherId() {
        return motherId;
    }

    /**
     * Set the value of motherId
     *
     * @param motherId new value of motherId
     */
    public void setMotherId(int motherId) {
        this.motherId = motherId;
    }
    
    @Column(name = "child_id")
    private int childId;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getChildId() {
        return childId;
    }

    /**
     * Set the value of id
     *
     * @param childId 
     */
    public void setChildId(int childId) {
        this.childId = childId;
    }
}
