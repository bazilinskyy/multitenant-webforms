/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.commons.user;

import java.io.Serializable;

/**
 *
 * @author Veli-Matti Plosila
 */
public class User implements Serializable{

    public User() {
        this.admin = false;
    }

    private int accountId;

    /**
     * Get the value of accountId
     *
     * @return the value of accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Set the value of accountId
     *
     * @param accountId new value of accountId
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    private int tenantId;

    /**
     * Get the value of companyId
     *
     * @return the value of companyId
     */
    public int getTenantId() {
        return tenantId;
    }

    /**
     * Set the value of companyId
     *
     * @param tenantId new value of companyId
     */
    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    private String username;

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    private String firstname;

    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the value of firstname
     *
     * @param firstname new value of firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String surname;

    /**
     * Get the value of surname
     *
     * @return the value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the value of surname
     *
     * @param surname new value of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    private boolean admin;

    /**
     * Get the value of admin
     *
     * @return the value of admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Set the value of admin
     *
     * @param admin new value of admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
