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
@Table(name="list_value")
public class ListValue  implements Serializable{
    
    /**
     *
     */
    public ListValue() {

    }
    
    @Id
    @Column(name="list_value_id")
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
    @Column(name="value")
    protected String value;

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of text
     *
     * @param value new value of value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     */
    @Column(name="text")
    protected String text;

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     */
    @Column(name="position_in_list")
    protected int positionInList;

    /**
     * Get the value of positionInList
     *
     * @return the value of positionInList
     */
    public int getPositionInList() {
        return positionInList;
    }

    /**
     * Set the value of positionInList
     *
     * @param positionInList new value of positionInList
     */
    public void setPositionInList(int positionInList) {
        this.positionInList = positionInList;
    }

    /**
     * 
     */
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
    
    /**
     * 
     */
    @Column(name="preset_field_id")
    protected int presetFieldId;

    /**
     * Get the value of presetFieldId
     *
     * @return the value of presetFieldId
     */
    public int getPresetFieldId() {
        return presetFieldId;
    }

    /**
     * Set the value of presetFieldId
     *
     * @param presetFieldId new value of presetFieldId
     */
    public void setPresetFieldId(int presetFieldId) {
        this.presetFieldId = presetFieldId;
    }
    
    @Column(name="default_value")
    private int defaultValue;

    /**
     * Get the value of defaultValue
     *
     * @return the value of defaultValue
     */
    public int getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set the value of defaultValue
     *
     * @param defaultValue new value of defaultValue
     */
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    protected boolean defaultOption;

    /**
     * Get the value of defaultOption
     *
     * @return the value of defaultOption
     */
    public boolean isDefaultOption() {
        return defaultOption;
    }

    /**
     * Set the value of defaultOption
     *
     * @param defaultOption new value of defaultOption
     */
    public void setDefaultOption(boolean defaultOption) {
        this.defaultOption = defaultOption;
    }

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

