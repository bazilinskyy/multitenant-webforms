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
@Table(name="tenant")
public class Tenant implements Serializable{

    /**
     *
     */
    public Tenant() {
    }

    @Id
    @Column(name="tenant_id")
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

    @Column(name="name")
    private String name;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of id
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    @Column(name="number_webforms_per_page")
    protected int numberWebFormsPerPage;

    /**
     * Get the value of numberWebFormsPerPage
     *
     * @return the value of numberWebFormsPerPage
     */
    public int getNumberWebFormsPerPage() {
        return numberWebFormsPerPage;
    }

    /**
     * Set the value of numberWebFormsPerPage
     *
     * @param numberWebFormsPerPage new value of numberWebFormsPerPage
     */
    public void setNumberWebFormsPerPage(int numberWebFormsPerPage) {
        this.numberWebFormsPerPage = numberWebFormsPerPage;
    }
    
    /**
     * 
     */
    @Column(name="number_webforms_sidebar")
    protected int numberWebFormsSidebar;

    /**
     * Get the value of numberWebFormsSidebar
     *
     * @return the value of numberWebFormsSidebar
     */
    public int getNumberWebFormsSidebar() {
        return numberWebFormsSidebar;
    }

    /**
     * Set the value of numberWebFormsSidebar
     *
     * @param numberWebFormsSidebar new value of numberWebFormsSidebar
     */
    public void setNumberWebFormsSidebar(int numberWebFormsSidebar) {
        this.numberWebFormsSidebar = numberWebFormsSidebar;
    }

    /**
     *
     */
    @Column(name="number_webforms_verified")
    protected int numberWebFormsVerified;

    /**
     * Get the value of numberWebFormsVerified
     *
     * @return the value of numberWebFormsVerified
     */
    public int getNumberWebFormsVerified() {
        return numberWebFormsVerified;
    }

    /**
     * Set the value of numberWebFormsVerified
     *
     * @param numberWebFormsVerified new value of numberWebFormsVerified
     */
    public void setNumberWebFormsVerified(int numberWebFormsVerified) {
        this.numberWebFormsVerified = numberWebFormsVerified;
    }

    /**
     *
     */
    @Column(name="number_webforms_unverified")
    protected int numberWebFormsUnverified;

    /**
     * Get the value of numberWebFormsUnverified
     *
     * @return the value of numberWebFormsUnverified
     */
    public int getNumberWebFormsUnverified() {
        return numberWebFormsUnverified;
    }

    /**
     * Set the value of numberWebFormsUnverified
     *
     * @param numberWebFormsUnverified new value of numberWebFormsUnverified
     */
    public void setNumberWebFormsUnverified(int numberWebFormsUnverified) {
        this.numberWebFormsUnverified = numberWebFormsUnverified;
    }
    
    /**
     *
     */
    @Column(name="instance_url")
    protected String instanceUrl;

    /**
     * Get the value of instance_url
     *
     * @return the value of instance_url
     */
    public String getInstanceUrl() {
        return instanceUrl;
    }

    /**
     * Set the value of instance_url
     *
     * @param instanceUrl
     */
    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }
    
    /**
     *
     */
    @Column(name="api_key")
    protected String apiKey;

    /**
     * Get the value of apiKey
     *
     * @return the value of apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Set the value of apiKey
     *
     * @param apiKey new value of apiKey
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     *
     */
    @Column(name="security_key")
    protected String securityKey;

    /**
     * Get the value of securityKey
     *
     * @return the value of securityKey
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * Set the value of securityKey
     *
     * @param securityKey new value of securityKey
     */
    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

}
