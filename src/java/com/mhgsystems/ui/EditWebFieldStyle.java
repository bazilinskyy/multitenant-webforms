/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.MotherChildWebFieldLogic;
import com.mhgsystems.logic.WebFieldLogic;
import com.mhgsystems.model.MotherChildWebField;
import com.mhgsystems.model.WebField;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class EditWebFieldStyle extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {

        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        getSessionBean().setWebField(webFieldLogic.get(Integer.parseInt(getSelectedRecordId()), null));
        
        // Check if field is a child from another field.
        MotherChildWebFieldLogic motherChildWebFieldLogic = new MotherChildWebFieldLogic();
        MotherChildWebField motherChildWebField = new MotherChildWebField();
        motherChildWebField.setChildId(Integer.parseInt(getSelectedRecordId()));
        MotherChildWebField tempMotherChildWebField = new MotherChildWebField();
        tempMotherChildWebField = motherChildWebFieldLogic.checkIfFieldIsChild(motherChildWebField, getUser());
        if (tempMotherChildWebField != null) { // Web field is a child, set boolean property child to TRUE.
            getSessionBean().getWebField().setChild(true);
            childField = 1;
        }
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
    
    protected int childField = 0;

    /**
     * Get the value of childField
     *
     * @return the value of childField
     */
    public int getChildField() {
        return childField;
    }

    /**
     * Set the value of childField
     *
     * @param childField new value of childField
     */
    public void setChildField(int childField) {
        this.childField = childField;
    }
}
