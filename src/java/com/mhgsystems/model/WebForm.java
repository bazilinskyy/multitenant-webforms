/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.model;

import com.mhgsystems.commons.UITextHandler;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.WebFieldLogic;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bazilinskyy Pavlo
 */
@Table(name = "webform")
public class WebForm implements Serializable {

    /**
     * 
     */
    protected UITextHandler uiTextHandler;

    /**
     *
     */
    public WebForm() {
        this.uiTextHandler = new UITextHandler();
    }
    @Id
    @Column(name = "webform_id")
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
    
    
    @Column(name = "name")
    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "captcha")
    protected int captcha;

    /**
     * Get the value of captcha
     *
     * @return the value of captcha
     */
    public int getCaptcha() {
        return captcha;
    }

    /**
     * Set the value of captcha
     *
     * @param captcha new value of captcha
     */
    public void setCaptcha(int captcha) {
        this.captcha = captcha;
    }
    
    protected int numberWebfields;

    /**
     * Get the value of numberWebfields
     *
     * @return the value of numberWebfields
     */
    public int getNumberWebfields() {
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        WebField webField = new WebField();
        webField.setWebformId(id);
        List list =  webFieldLogic.list(webField, null);
        return list.size();
    }

    /**
     * Set the value of numberWebfields
     *
     * @param numberWebfields new value of numberWebfields
     */
    public void setNumberWebfields(int numberWebfields) {
        this.numberWebfields = numberWebfields;
    }
    
    protected boolean hasWebfields;

    /**
     * Get the value of hasWebfields
     *
     * @return the value of hasWebfields
     */
    public boolean isHasWebfields() {
        if (getNumberWebfields() > 0)
            return true;
        else
            return false;
    }

    /**
     * Set the value of hasWebfields
     *
     * @param hasWebfields new value of hasWebfields
     */
    public void setHasWebfields(boolean hasWebfields) {
        this.hasWebfields = hasWebfields;
    }
    
    @Column(name = "can_be_mother")
    protected int canBeMother;

    /**
     * Get the value of canBeMother
     *
     * @return the value of canBeMother
     */
    public int getCanBeMother() {
        return canBeMother;
    }

    /**
     * Set the value of canBeMother
     *
     * @param canBeMother new value of canBeMother
     */
    public void setCanBeMother(int canBeMother) {
        this.canBeMother = canBeMother;
    }

    protected boolean captchaRequired;

    /**
     * Get the value of captchaRequired
     *
     * @return the value of captchaRequired
     */
    public boolean isCaptchaRequired() {
        if (captcha > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the value of captchaRequired
     *
     * @param captchaRequired new value of captchaRequired
     */
    public void setCaptchaRequired(boolean captchaRequired) {
        if (captchaRequired) {
            captcha = 1;
        } else {
            captcha = 0;
        }
        this.captchaRequired = captchaRequired;
    }
    
    protected boolean canBeMotherBool;

    /**
     * Get the value of canBeMotherBool
     *
     * @return the value of canBeMotherBool
     */
    public boolean isCanBeMotherBool() {
        if (canBeMother > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the value of canBeMotherBool
     *
     * @param canBeMotherBool new value of canBeMotherBool
     */
    public void setCanBeMotherBool(boolean canBeMotherBool) {
        if (canBeMotherBool) {
            canBeMother = 1;
        } else {
            canBeMother = 0;
        }
        this.canBeMotherBool = canBeMotherBool;
    }

}
