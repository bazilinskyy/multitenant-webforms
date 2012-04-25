/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class Confirmation  extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {
        getSessionBean().setShowCaptcha(true);
    }

}
