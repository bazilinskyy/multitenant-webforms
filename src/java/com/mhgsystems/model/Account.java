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
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */

@Table(name="account")
public class Account implements Serializable {

    /**
     *
     */
    public Account() {
    }

    @Id
    @Column(name="user_id")
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

    @Column(name="tenant_id")
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
     * @param tenantId 
     */
    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Column(name="username")
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

    @Column(name="password")
    private String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="email")
    private String email;

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="firstname")
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

    @Column(name="surname")
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

    
    @Column(name="verified")
    private int verified;

    /**
     * Get the value of verified
     *
     * @return the value of verified
     */
    public int getVerified() {
        return verified;
    }

    /**
     * Set the value of verified
     *
     * @param verified new value of verified
     */
    public void setVerified(int verified) {
        this.verified = verified;
    }
    
    @Column(name="avatar")
    private String avatar;

    /**
     * Get the value of avatar
     *
     * @return the value of avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Set the value of avatar
     *
     * @param avatar new value of avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    @Column(name="admin")
    private int admin;

    /**
     * Get the value of admin
     *
     * @return the value of admin
     */
    public int getAdmin() {
        return admin;
    }

    /**
     * Set the value of admin
     *
     * @param admin new value of admin
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }

    /**
     *
     */
    protected String avatarUrl;

    /**
     * Get the value of avatarUrl
     *
     * @return the value of avatarUrl
     */
    public String getAvatarUrl() {
        if (avatar != null)
            avatarUrl = "/resources/img/useravatars/" + avatar + ".jpg";
        else
            avatarUrl = "/resources/img/useravatars/default_avatar.jpg"; //If account does not have avatar uplaoded, set it to defualt image
        return avatarUrl;
    }

    /**
     * Set the value of avatarUrl
     *
     * @param avatarUrl new value of avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
