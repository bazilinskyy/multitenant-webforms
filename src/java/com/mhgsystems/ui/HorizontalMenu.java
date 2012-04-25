/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class HorizontalMenu extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {
    }

    /**
     * Logout action. Sets vars from SessionBean to null.
     *
     * @return
     * @throws DataAccessException
     */
    public String logout() throws DataAccessException {
        getSessionBean().setAccount(null);
        getSessionBean().setUser(null);
        getSessionBean().setTenant(null);
        getSessionBean().setLoggedIn(false);
        getSessionBean().setShowCaptcha(true);

        //Unset cookies
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCookies();
        if (cookie != null && cookie.length > 0) {
            for (int i = 0; i < cookie.length; i++) {
                cookieName = cookie[i].getName();
                if (cookieName.equals("btuser") || cookieName.equals("btpasswd")
                        || cookieName.equals("btremember") || cookieName.equals("btlang")
                        || cookieName.equals("btcountry")) {
                    cookie[i].setValue(null);
                    cookie[i].setMaxAge(0);
                    ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[i]);
                }
            }
        }
        
        return "login";
    }

    /**
     *
     * @return
     */
    public String goAdminHome() {
        return "home";
    }
}
