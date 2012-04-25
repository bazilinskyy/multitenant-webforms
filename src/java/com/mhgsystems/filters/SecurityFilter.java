/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.filters;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.ui.SessionBean;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Veli-Matti Plosila
 */
public final class SecurityFilter implements Filter {

    private static final java.util.List<String> NO_FILTER = Arrays.<String>asList(
            "/login.jsf",
            "register.jsf",
            "/resources/",
            "/javax.faces.resource/",
            "/xmlhttp/");

    private static final String LOGIN = "login.jsf";

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String requestURL = null;

        try {

            HttpSession session = ((HttpServletRequest) request).getSession(true);


            try {
                requestURL = ((HttpServletRequest) request).getRequestURL().toString();
            } catch (Exception ex) {
                if (requestURL == null) {
                    Logger.getInstance().log("Request URL is null");
                    chain.doFilter(request, response);
                    return;
                }
            }

            for (String value : NO_FILTER) {
                if (requestURL.contains(value)) {
            
                    chain.doFilter(request, response);
                    return;
                }
            }
            
            if (session.isNew()) {
                throw new SecurityException("Session is new");
            }

            if(((SessionBean)session.getAttribute("sessionBean")).getUser() == null){
                throw new SecurityException("User not found");
            }

            //Pass request
            chain.doFilter(request, response);


        } catch (SecurityException ex) {

            request.getRequestDispatcher(LOGIN).forward(request, response);

        } catch (Exception ex) {

            Logger.getInstance().log(ex);
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }

    }
    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

    }

    @Override
    public void destroy() {
    }
}
