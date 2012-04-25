/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.logic.AccountLogic;
import com.mhgsystems.logic.TenantLogic;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
public class Login extends BaseView {
    private boolean cookiesFound;

    /**
     *
     */
    @Override
    public void prerender() {
        checkCookie(); //Check if cookied with remembersd user exict
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

    /**
     *
     */
    protected boolean rememberMe;

    /**
     * Get the value of rememberMe
     *
     * @return the value of rememberMe
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * Set the value of rememberMe
     *
     * @param rememberMe new value of rememberMe
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     *
     */
    protected String rememberMe1 = "hi";

    /**
     * Get the value of rememberMe1
     *
     * @return the value of rememberMe1
     */
    public String getRememberMe1() {
        return rememberMe1;
    }

    /**
     * Set the value of rememberMe1
     *
     * @param rememberMe1 new value of rememberMe1
     */
    public void setRememberMe1(String rememberMe1) {
        this.rememberMe1 = rememberMe1;
    }

    /**
     * 
     */
    protected String languageFromPreviousSession;

    /**
     * Get the value of languageFromPreviousSession
     *
     * @return the value of languageFromPreviousSession
     */
    public String getLanguageFromPreviousSession() {
        return languageFromPreviousSession;
    }

    /**
     * Set the value of languageFromPreviousSession
     *
     * @param languageFromPreviousSession new value of languageFromPreviousSession
     */
    public void setLanguageFromPreviousSession(String languageFromPreviousSession) {
        this.languageFromPreviousSession = languageFromPreviousSession;
    }

    /**
     * 
     */
    protected String countryFromPreviousSession;

    /**
     * Get the value of countryFromPreviousSession
     *
     * @return the value of countryFromPreviousSession
     */
    public String getCountryFromPreviousSession() {
        return countryFromPreviousSession;
    }

    /**
     * Set the value of countryFromPreviousSession
     *
     * @param countryFromPreviousSession new value of countryFromPreviousSession
     */
    public void setCountryFromPreviousSession(String countryFromPreviousSession) {
        this.countryFromPreviousSession = countryFromPreviousSession;
    }


    /**
     * Call AccountLogic to login to the system
     *
     * @return String view to navigate
     */
    public String loginAction() {

        AccountLogic accountLogic = (AccountLogic)LogicFactory.getNewGenericLogic(Account.class);
        Account account = accountLogic.get(username, password);
        
        
                
        if (account != null) {
            //Account found
            getSessionBean().setAccount(account);
            getSessionBean().setUser(accountLogic.createUser(account));
            TenantLogic tenantLogic = (TenantLogic)LogicFactory.getNewGenericLogic(Tenant.class);
            
            Tenant tenant = tenantLogic.get(account.getTenantId());
            getSessionBean().setTenant(tenant);            
            getSessionBean().setLoggedIn(true);

            // Save the userid and password in a cookie
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Cookie btuser = new Cookie("btuser", username);
            Cookie btpasswd = new Cookie("btpasswd", password);
            if (rememberMe == false) {
                rememberMe1 = "false";
            } else {
                rememberMe1 = "true";
            }
            Cookie btremember = new Cookie("btremember", rememberMe1);
            btuser.setMaxAge(3600);
            btpasswd.setMaxAge(3600);

            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btuser);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btpasswd);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btremember);

            // Redirect if coockies were found
            if (cookiesFound) {
                try {
                    //Setting language from saved cookie
                    if (languageFromPreviousSession != null && countryFromPreviousSession != null) {
                        FacesContext fcontext = FacesContext.getCurrentInstance();
                        fcontext.getViewRoot().setLocale(new Locale(languageFromPreviousSession, countryFromPreviousSession));
                        getSessionBean().setLanguage(new Locale(languageFromPreviousSession, countryFromPreviousSession).getLanguage());
                    }

                    facesContext.getExternalContext().redirect("/Multitenant_webforms/home.jsf");
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return "home";
        } else {
            getSessionBean().getMessageHandler().createMessage("wrongUsername");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String registerAction() {

        getSessionBean().setAccount(new Account());
        return "register";
    }

    /**
     * Checks if cookies are found from previous session.
     * Called from prerender()
     *
     */
    public void checkCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCookies();
        if (cookie != null && cookie.length > 0) {
            for (int i = 0; i < cookie.length; i++) {
                cookieName = cookie[i].getName();
                if (cookieName.equals("btuser")) {
                    username = cookie[i].getValue();
                } else if (cookieName.equals("btpasswd")) {
                    password = cookie[i].getValue();
                } else if (cookieName.equals("btlang")) {
                    languageFromPreviousSession = cookie[i].getValue();
                } else if (cookieName.equals("btcountry")) {
                    countryFromPreviousSession = cookie[i].getValue();
                } else if (cookieName.equals("btremember")) {
                    rememberMe1 = cookie[i].getValue();
                    if (rememberMe1.equals("false")) {
                        rememberMe = false;
                    } else if (rememberMe1.equals("true")) {
                        rememberMe = true;
                        cookiesFound = true;
                        loginAction();
                    }
                }
            }
        } //ELSE: Cookies not found, go to login message prompt
    }
}
