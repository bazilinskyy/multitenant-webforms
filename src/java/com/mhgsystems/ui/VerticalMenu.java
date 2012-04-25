/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;


/**
 *
 * @author Pavlo Bazilinskyy
 */
public class VerticalMenu extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {
    }

    /**
     *
     */
    protected String companyName;

    /**
     * Get the value of companyName
     *
     * @return the value of companyName
     */
    public String getCompanyName() {
        return Integer.toString(getSessionBean().getAccount().getTenantId()); // temp for outputting in vertical sidebar
    }

    /**
     * Set the value of companyName
     *
     * @param companyName new value of companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
