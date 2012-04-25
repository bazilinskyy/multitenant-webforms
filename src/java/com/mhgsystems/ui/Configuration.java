/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.logic.TenantLogic;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.WebFormLogic;
import com.mhgsystems.logic.WebFieldLogic;
import com.mhgsystems.model.Tenant;
import com.mhgsystems.model.WebForm;
import com.mhgsystems.model.WebField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;


/**
 *
 * @author Pavlo Bazilinskyy
 */
public class Configuration extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {
        getSessionBean().setSearchTenant(getSessionBean().getTenant());//Assigning default values
    }


    /**
     *
     * @return
     */
    public String addAction() {

        TenantLogic tenantLogic = (TenantLogic)LogicFactory.getNewGenericLogic(Tenant.class);
        LogicResponse logicResponse = tenantLogic.update(getSessionBean().getSearchTenant(), null);

        if (logicResponse.isSucceeded()) {
            getSessionBean().setTenant(getSessionBean().getSearchTenant());
            getSessionBean().setConfirmation("configuration");
            return "confirmation";
        } else {
            getSessionBean().getMessageHandler().createMessage("errorEditConfiguration");
            return null;
        }
    }
}
