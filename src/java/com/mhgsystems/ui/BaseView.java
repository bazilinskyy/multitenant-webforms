/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.UITextHandler;
import com.mhgsystems.commons.user.User;
import javax.el.ELResolver;

import javax.faces.context.FacesContext;
/**
 *
 * @author Veli-Matti Plosila
 */
public abstract class BaseView {

    public BaseView() {
        this.uiTextHandler = new UITextHandler();
    }

    protected UITextHandler uiTextHandler;

    public abstract void prerender();

    public Object getBean(String name) {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        ELResolver resolver = fcontext.getApplication().getELResolver();

        return resolver.getValue(fcontext.getELContext(), null, name);
    }

    public SessionBean getSessionBean() {
        return (SessionBean) getBean("sessionBean");
    }

    public User getUser() {

        return getSessionBean().getUser();
    }
}
