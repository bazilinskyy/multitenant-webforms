/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.WebFieldLogic;
import com.mhgsystems.model.WebField;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class PresetFieldView extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {

        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        getSessionBean().setWebField(webFieldLogic.get(Integer.parseInt(getSelectedRecordId()), null));
    }
    private String selectedWebFieldId;

    public String getSelectedRecordId() {
        selectedWebFieldId = (String) getParamValue("#{param.fieldId}");
        return selectedWebFieldId;
    }

    public void setSelectedRecordId(String selectedRecordId) {
        this.selectedWebFieldId = selectedRecordId;
    }

    public Object getParamValue(String s) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Object value = facesContext.getApplication().createValueBinding(s).getValue(facesContext);
        return value;
    }
}
