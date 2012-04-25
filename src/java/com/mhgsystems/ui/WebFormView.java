/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.logic.*;
import com.mhgsystems.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class WebFormView extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {
    }
    /**
     *
     */
    protected List worksortOptions;

    /**
     * Get the value of worksortOptions
     *
     * @return the value of worksortOptions
     */
    public List getWorksortOptions() {
//        if (worksortOptions == null) {
//            worksortOptions = new ArrayList();
//
//            WebFieldLogic workSortLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
//            List<WebField> list = workSortLogic.list(null, getUser());
//
//            for (int i = 0; i < list.size(); i++) {
//                worksortOptions.add(new SelectItem(list.get(i).getWorkSortCd(), list.get(i).getPopupMessage()));
//            }
//        }
//        return worksortOptions;

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Set the value of worksortOptions
     *
     * @param worksortOptions new value of worksortOptions
     */
    public void setWorksortOptions(List worksortOptions) {
        this.worksortOptions = worksortOptions;
    }
    /**
     *
     */
    protected com.mhgsystems.model.WebForm WebForm;

    /**
     * Get the value of WebForm
     *
     * @return the value of WebForm
     */
    public com.mhgsystems.model.WebForm getWebForm() {
        return WebForm;
    }

    /**
     * Set the value of WebForm
     *
     * @param WebForm new value of WebForm
     */
    public void setWebForm(com.mhgsystems.model.WebForm WebForm) {
        this.WebForm = WebForm;
    }
    /**
     *
     */
    protected String currentWorksort;

    /**
     * Get the value of currentWorksort
     *
     * @return the value of currentWorksort
     */
    public String getCurrentWorksort() {
        return currentWorksort;
    }

    /**
     * Set the value of currentWorksort
     *
     * @param currentWorksort new value of currentWorksort
     */
    public void setCurrentWorksort(String currentWorksort) {
        this.currentWorksort = currentWorksort;
    }

    /**
     * Create new Web Form.
     *
     * @return
     * @throws DataAccessException
     */
    public String newWebForm() throws DataAccessException {
        // Logic class declarations.
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        LabelLogic labelLogic = (LabelLogic) LogicFactory.getNewGenericLogic(Label.class);
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        UserWebFormLogic userWebFormLogic = (UserWebFormLogic) LogicFactory.getNewGenericLogic(UserWebForm.class);
        UserWebFieldLogic userWebFieldLogic = (UserWebFieldLogic) LogicFactory.getNewGenericLogic(UserWebField.class);
        boolean success = true;

        // Insert new web form.
        LogicResponse logicResponse = webFormLogic.insert(getSessionBean().getWebForm(), null);
        if (!logicResponse.isSucceeded()) {
            success = false;
        }
        int newWebFormId = webFormLogic.getMaxWebFormId();
        getSessionBean().getWebForm().setId(newWebFormId);

        // Allow users of the tenant use the web form.
        logicResponse = userWebFormLogic.insertForAllTenantUsers(getSessionBean().getWebForm(), getSessionBean().getTenant());
        if (!logicResponse.isSucceeded()) {
            success = false;
        }

        // Add new web fields.
        Logger.getInstance().log("label ID 0" + getSessionBean().newWebFieldsInForm.get(0).getLabelId());
        for (WebField newField : getSessionBean().newWebFieldsInForm) {
            Logger.getInstance().log("label ID " + newField.getLabelId());
            if (!newField.getLabel().getEn().isEmpty()) {
                logicResponse = labelLogic.insert(newField.getLabel(), null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newLabelId = labelLogic.getMaxLabelId();
                newField.setLabelId(newLabelId);

                newField.setWebformId(newWebFormId);
                newField.setTenantId(getSessionBean().getUser().getTenantId());
                logicResponse = webFieldLogic.insert(newField, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newWebFieldId = webFieldLogic.getMaxWebFieldId();
                newField.setId(newWebFieldId);

                // Allow users of the tenant use the web field.
                logicResponse = userWebFieldLogic.insertForAllTenantUsers(newField, getSessionBean().getTenant());
                if (!logicResponse.isSucceeded()) {
                    success = false;
                }

            } else if (newField.getLabelId() != -1) {
                newField.setWebformId(newWebFormId);
                newField.setTenantId(getSessionBean().getUser().getTenantId());
                logicResponse = webFieldLogic.insert(newField, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newWebFieldId = webFieldLogic.getMaxWebFieldId();
                newField.setId(newWebFieldId);

                // Allow users of the tenant use the web field.
                logicResponse = userWebFieldLogic.insertForAllTenantUsers(newField, getSessionBean().getTenant());
                if (!logicResponse.isSucceeded()) {
                    success = false;
                }
            }
        }

        // If there were no errors, go back to edit web form view.
        if (success) {
            getSessionBean().getMessageHandler().createMessage("editWebFormSuccessful");
            return "editWebForm";
        } else {
            getSessionBean().getMessageHandler().createMessage("errorEditWebForm");
            return null;
        }
    }

    /**
     * Create new Web Form based on another web form.
     *
     * @return
     * @throws DataAccessException
     */
    public String newChildWebForm() throws DataAccessException {
        // Logic class declarations.
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        LabelLogic labelLogic = (LabelLogic) LogicFactory.getNewGenericLogic(Label.class);
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        UserWebFormLogic userWebFormLogic = (UserWebFormLogic) LogicFactory.getNewGenericLogic(UserWebForm.class);
        UserWebFieldLogic userWebFieldLogic = (UserWebFieldLogic) LogicFactory.getNewGenericLogic(UserWebField.class);
        MotherChildWebFieldLogic motherChildWebFieldLogic = (MotherChildWebFieldLogic) LogicFactory.getNewGenericLogic(MotherChildWebField.class);
        boolean success = true; // Trigger of errors.

        // Insert new web form.
        LogicResponse logicResponse = webFormLogic.insert(getSessionBean().getWebForm(), null);
        if (!logicResponse.isSucceeded()) {
            success = false;
        }
        int newWebFormId = webFormLogic.getMaxWebFormId();
        getSessionBean().getWebForm().setId(newWebFormId);

        // Allow users of the tenant use the web form.
        logicResponse = userWebFormLogic.insertForAllTenantUsers(getSessionBean().getWebForm(), getSessionBean().getTenant());
        if (!logicResponse.isSucceeded()) {
            success = false;
        }

        // Add web fields from the mother web form.
        for (WebField newField : getSessionBean().webFieldsInFormMotherForm) {
            if (!newField.getLabel().getEn().isEmpty()) {
                logicResponse = labelLogic.insert(newField.getLabel(), null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newLabelId = labelLogic.getMaxLabelId();
                newField.setLabelId(newLabelId);

                newField.setWebformId(newWebFormId);
                newField.setTenantId(getSessionBean().getUser().getTenantId());
                logicResponse = webFieldLogic.insert(newField, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newWebFieldId = webFieldLogic.getMaxWebFieldId();
                newField.setIdInMotherForm(newField.getId());
                newField.setId(newWebFieldId);

                // Allow users of the tenant use the web field.
                logicResponse = userWebFieldLogic.insertForAllTenantUsers(newField, getSessionBean().getTenant());
                if (!logicResponse.isSucceeded()) {
                    success = false;
                }

                // Determing if a web filed is derivered from another field (child set to TRUE).
                if (newField.isChild()) {
                    MotherChildWebField motherChildWebField = new MotherChildWebField();
                    motherChildWebField.setMotherId(newField.getIdInMotherForm());
                    motherChildWebField.setChildId(newField.getId());

                    // Insert mother-child pair. No logic response is retreaved because breaks in logic it generates at this place. TODO fix it.
                    motherChildWebFieldLogic.insert(motherChildWebField, getSessionBean().getUser());
                }
            }
        }

        // Add new web fields.
        for (WebField newField : getSessionBean().newWebFieldsInForm) {
            if (!newField.getLabel().getEn().isEmpty()) {
                logicResponse = labelLogic.insert(newField.getLabel(), null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newLabelId = labelLogic.getMaxLabelId();
                newField.setLabelId(newLabelId);

                newField.setWebformId(newWebFormId);
                newField.setTenantId(getSessionBean().getUser().getTenantId());
                logicResponse = webFieldLogic.insert(newField, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
                int newWebFieldId = webFieldLogic.getMaxWebFieldId();
                newField.setId(newWebFieldId);

                // Allow users of the tenant use the web field.
                logicResponse = userWebFieldLogic.insertForAllTenantUsers(newField, getSessionBean().getTenant());
                if (!logicResponse.isSucceeded()) {
                    success = false;
                }
            }
        }


        // If there were no errors, go back to edit web form view.
        if (success) {
            getSessionBean().getMessageHandler().createMessage("editWebFormSuccessful");
            return "editWebForm";
        } else {
            getSessionBean().getMessageHandler().createMessage("errorEditWebForm");
            return null;
        }
    }

    /**
     * Function called when web form needs to be edited.
     *
     * @return
     * @throws DataAccessException
     */
    public String editWebForm() throws DataAccessException {
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        LabelLogic labelLogic = (LabelLogic) LogicFactory.getNewGenericLogic(Label.class);
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        UserWebFieldLogic userWebFieldLogic = (UserWebFieldLogic) LogicFactory.getNewGenericLogic(UserWebField.class);
        UserWebFormLogic userWebFormLogic = (UserWebFormLogic) LogicFactory.getNewGenericLogic(UserWebForm.class);
        MotherChildWebFieldLogic motherChildWebFieldLogic = new MotherChildWebFieldLogic();
        LogicResponse logicResponse = null;
        boolean success = true;


        if (getSessionBean().isDeleteForm()) {
            // Delete web fields of the form that is to be deleted.
            WebField webField = new WebField();
            webField.setWebformId(getSessionBean().getWebForm().getId());
            List<WebField> fieldsDelete = webFieldLogic.list(webField, getUser());

            for (WebField field : fieldsDelete) {
                // Delete user rights for web field.
                UserWebField userWebField = new UserWebField();
                userWebField.setWebFieldId(field.getId());
                logicResponse = userWebFieldLogic.deleteAllUsersForForm(userWebField, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }

                // Delete mother child assignments for web field.
                MotherChildWebField motherChild = new MotherChildWebField();
                motherChild.setChildId(field.getId());
                logicResponse = motherChildWebFieldLogic.deleteAllForChild(motherChild, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }

                motherChild.setMotherId(field.getId());
                logicResponse = motherChildWebFieldLogic.deleteAllForMother(motherChild, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }

                // Delete web field
                logicResponse = webFieldLogic.delete(field, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
            }

            // Delete user rights for web form.
            UserWebForm userWebForm = new UserWebForm();
            userWebForm.setWebFormId(getSessionBean().getWebForm().getId());
            logicResponse = userWebFormLogic.deleteAllUsersForForm(userWebForm, null);
            if (!logicResponse.isSucceeded()) {
                success = false;
            }

            // Delete form if delete was checked.
            logicResponse = webFormLogic.delete(getSessionBean().getWebForm(), null);
            if (!logicResponse.isSucceeded()) {
                success = false;
            }

            // If no errors return to the view. Otherwise, show error message.
            if (success) {
                getSessionBean().setConfirmation("formDeleted");
                return "confirmation";
            } else {
                getSessionBean().getMessageHandler().createMessage("errorEditWebForm");
                return null;
            }
        } else {
            // Update form.

            // Toggle captcha
            if (getSessionBean().getWebForm().isCaptchaRequired()) {
                getSessionBean().getWebForm().setCaptcha(1);
            } else {
                getSessionBean().getWebForm().setCaptcha(0);
            }

            // Toggle can be mother
            if (getSessionBean().getWebForm().isCanBeMotherBool()) {
                getSessionBean().getWebForm().setCanBeMother(1);
            } else {
                getSessionBean().getWebForm().setCanBeMother(0);
            }

            logicResponse = webFormLogic.update(getSessionBean().getWebForm(), null);
            if (!logicResponse.isSucceeded()) {
                success = false;
            }

            // Add new web fields.
            for (WebField newField : getSessionBean().newWebFieldsInForm) {
                if (!newField.getLabel().getEn().isEmpty()) {
                    logicResponse = labelLogic.insert(newField.getLabel(), null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }
                    int newLabelId = labelLogic.getMaxLabelId();
                    newField.setLabelId(newLabelId);

                    newField.setWebformId(getSessionBean().getWebForm().getId());
                    newField.setTenantId(getSessionBean().getUser().getTenantId());
                    logicResponse = webFieldLogic.insert(newField, null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }
                    int newWebFieldId = webFieldLogic.getMaxWebFieldId();
                    newField.setId(newWebFieldId);

                    // Allow users of the tenant use the web field.
                    logicResponse = userWebFieldLogic.insertForAllTenantUsers(newField, getSessionBean().getTenant());
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                    }
                }
            }

            // Update old fields.
            for (WebField field : getSessionBean().webFieldsInFormForUser) {
                if (field.isToBeDeleted()) {
                    // Delete field if required.

                    // Delete user rights for web field.
                    UserWebField userWebField = new UserWebField();
                    userWebField.setWebFieldId(field.getId());
                    logicResponse = userWebFieldLogic.deleteAllUsersForForm(userWebField, null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }

                    // Delete mother child assignments for web field.
                    MotherChildWebField motherChild = new MotherChildWebField();
                    motherChild.setChildId(field.getId());
                    logicResponse = motherChildWebFieldLogic.deleteAllForChild(motherChild, null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }

                    motherChild.setMotherId(field.getId());
                    logicResponse = motherChildWebFieldLogic.deleteAllForMother(motherChild, null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }

                    // Delete web field
                    logicResponse = webFieldLogic.delete(field, null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }
                } else {
                    // Update field.
                    logicResponse = webFieldLogic.update(field, null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }

                    logicResponse = labelLogic.update(field.getLabel(), null);
                    if (!logicResponse.isSucceeded()) {
                        success = false;
                        break;
                    }

                    // Update child field. Possibly recursion can be used here.
                    // Check if field is a mother from another field.
                    MotherChildWebField tempMotherChildWebField = new MotherChildWebField();
                    MotherChildWebField motherChildWebField = new MotherChildWebField();
                    motherChildWebField.setMotherId(field.getId());
                    tempMotherChildWebField = null; // Set to null after check fo child above.
                    tempMotherChildWebField = motherChildWebFieldLogic.checkIfFieldIsMother(motherChildWebField, getUser());
                    if (tempMotherChildWebField != null) { // Web field is a mother
                        // Get list of all fields that are children to the field.
                        List<MotherChildWebField> listChildren =
                                motherChildWebFieldLogic.listChildredToMother(motherChildWebField, getSessionBean().getUser());

                        // Loop through list of fields and update values.
                        for (MotherChildWebField child : listChildren) {
                            WebField childField = webFieldLogic.get(child.getChildId(), getSessionBean().getUser());

                            // Set new values
                            childField.setInputWidth(field.getInputWidth());
                            childField.setInputHeight(field.getInputHeight());
                            childField.setInputSize(field.getInputSize());
                            childField.setTextareaCol(field.getTextareaCol());
                            childField.setTextareaRow(field.getTextareaRow());
                            childField.setType(field.getType());

                            // Update child field
                            logicResponse = webFieldLogic.update(childField, null);
                            if (!logicResponse.isSucceeded()) {
                                success = false;
                                break;
                            }

                        }
                    }
                }
            }

            // Update user rights for the form
            UserWebForm userWebForm = new UserWebForm();
            userWebForm.setWebFormId(getSessionBean().getWebForm().getId());
            // Delete all user rights for the form.
            logicResponse = userWebFormLogic.deleteAllUsersForForm(userWebForm, getSessionBean().getUser());
            if (!logicResponse.isSucceeded()) {
                success = false;
            }

            // Add updated rights.
            userWebForm.setWebFormId(getSessionBean().getWebForm().getId());
            for (String userRight : userRightsSelected) {
                userWebForm.setUserId(Integer.parseInt(userRight));
                logicResponse = userWebFormLogic.insert(userWebForm, getSessionBean().getAccount());
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
            }

            // If no errors return to the view. Otherwise, show error message.
            if (success) {
                getSessionBean().setConfirmation("formEdited");
                return "confirmation";
                
            } else {
                getSessionBean().getMessageHandler().createMessage("errorEditWebForm");
                return null;
            }
        }
    }

    /**
     * Work order used is parsed by public String parseWebForm()
     *
     * @return
     * @throws DataAccessException
     */
    public String editWebFormAdmin() throws DataAccessException {
//        //TODO delete-uplaod multiple files for work order
//
//        WebFormLogic WebFormLogic = (WebFormLogic)LogicFactory.getNewGenericLogic(WebForm.class);
//        LogicResponse logicResponse = WebFormLogic.update(getSessionBean().getWebForm(), null);
//
//        if (logicResponse.isSucceeded()) {
//            getSessionBean().setConfirmation("editWebForm");
//            return "confirmationAdmin";
//        } else {
//            getSessionBean().getMessageHandler().createMessage("wrongUsername");
//            return null;
//        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Parse web form via f:param. e.g. <f:param name="idWork"...
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @return
     */
    public String parseFillWebForm() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String idWork = (String) requestMap.get("idWork");
        int id = Integer.parseInt(idWork);

        WebFormLogic WebFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        getSessionBean().setWebForm(WebFormLogic.get(id, getUser()));

        if (getSessionBean().getWebForm().getNumberWebfields() < 1) {
            getSessionBean().getMessageHandler().createMessage("noWebFields");
            return "fillWebForm";
        } else {
            return "fillWebForm";
        }

    }

    /**
     * 
     * @return
     */
    public String parseNewWebForm() {
        getSessionBean().setWebForm(new WebForm());
        return "newWebForm";

    }

    /**
     * 
     * @return
     */
    public String parseNewChildWebForm() {
        getSessionBean().setWebForm(new WebForm());

        return "newChildWebForm";

    }

    /**
     * 
     * @return
     */
    public String updateMotherForm() {
        // TODO finish
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        WebField webField = new WebField();
        webField.setWebformId(getSessionBean().getWebForm().getId());
        List list = webFieldLogic.list(webField, getUser());
        getSessionBean().setWebFieldsInFormMotherForm(list);

        return "newChildWebForm";

    }

    /**
     * Parse web form via f:param. e.g. <f:param name="idWork"...
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *

     *
     * @return
     */
    public String parseViewWebForm() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String idWork = (String) requestMap.get("idWork");
        int id = Integer.parseInt(idWork);

        WebFormLogic WebFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        getSessionBean().setWebForm(WebFormLogic.get(id, getUser()));

        if (getSessionBean().getWebForm().getNumberWebfields() < 1) {
            getSessionBean().getMessageHandler().createMessage("noWebFields");
            return "viewWebForm";
        } else {
            return "viewWebForm";
        }

    }

    /**
     * Parse web form via f:param. e.g. <f:param name="idWork"...
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *

     *
     * @return
     */
    public String parseEditWebForm() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String idWork = (String) requestMap.get("idWork");
        int id = Integer.parseInt(idWork);

        WebFormLogic WebFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        getSessionBean().setWebForm(WebFormLogic.get(id, getUser()));

        if (getSessionBean().getWebForm().getNumberWebfields() < 1) {
            getSessionBean().getMessageHandler().createMessage("noWebFields");
            return "editWebForm";
        } else {
            return "editWebForm";
        }

    }

    /**
     * Parse work order via f:param. e.g. <f:param name="idWork" value="#{WebForm.id}" />
     *
     * @return
     */
    public String parseWebFormAdmin() {

//        FacesContext context = FacesContext.getCurrentInstance();
//        Map requestMap = context.getExternalContext().getRequestParameterMap();
//        String idWork = (String)requestMap.get("idWork");
//        int id = Integer.parseInt(idWork);
//
//        WebFormLogic WebFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        getSessionBean().setWebForm(WebFormLogic.get(id, getUser()));
//
//        return "editWebFormAdmin";

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public String completeWebForm() {

//        FacesContext context = FacesContext.getCurrentInstance();
//        Map requestMap = context.getExternalContext().getRequestParameterMap();
//        String idWork = (String)requestMap.get("idWork");
//        int id = Integer.parseInt(idWork);
//
//        WebFormLogic WebFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm WebForm = WebFormLogic.get(id, getSessionBean().getUser());
//
//        WebForm.setStatus(3); //Setting status = confirmed for a temp WebForm object
//        WebFormLogic.update(WebForm, null);
//
//
//        return "home";

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public String viewWebForm() {
        return null;
    }

    /**
     * 
     * @return
     */
    public String fillIn() {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * 
     * @return
     */
    public String fillInWebField() {

        throw new UnsupportedOperationException("Not supported yet.");

    }
    /**
     * 
     */
    protected List typeOptions;

    /**
     * Get the value of typeOptions
     *
     * @return the value of typeOptions
     */
    public List getTypeOptions() {
        if (typeOptions == null) {
            typeOptions = new ArrayList();
            typeOptions.add(new SelectItem(1, uiTextHandler.getText("inputField")));
            typeOptions.add(new SelectItem(2, uiTextHandler.getText("emailField")));
            typeOptions.add(new SelectItem(3, uiTextHandler.getText("textArea")));
            typeOptions.add(new SelectItem(4, uiTextHandler.getText("birthdayField")));
            typeOptions.add(new SelectItem(5, uiTextHandler.getText("deadlineField")));
            typeOptions.add(new SelectItem(6, uiTextHandler.getText("selectOneList")));
            typeOptions.add(new SelectItem(7, uiTextHandler.getText("selectOneRadio")));
            typeOptions.add(new SelectItem(8, uiTextHandler.getText("selectOneCheckbox")));
            typeOptions.add(new SelectItem(9, uiTextHandler.getText("checkbox")));
            typeOptions.add(new SelectItem(10, uiTextHandler.getText("googleMaps")));
        }
        return typeOptions;
    }
    /**
     * 
     */
    protected List labelOptions;

    /**
     * Get the value of labelOptions
     *
     * @return the value of labelOptions
     */
    public List getLabelOptions() {
        if (labelOptions == null) {
            labelOptions = new ArrayList();
            labelOptions.add(new SelectItem(-1, "--- " + uiTextHandler.getText("chooseLabel") + " ---")); // Add default -1 value
            LabelLogic labelLogic = (LabelLogic) LogicFactory.getNewGenericLogic(Label.class);
            // Get list of all users for a given form.
            List<Label> listLabels = labelLogic.listAll(getSessionBean().getUser());
            Logger.getInstance().log("size labels" + listLabels.size());

            for (Label label : listLabels) {
                labelOptions.add(new SelectItem(label.getId(), label.getEn())); // TODO: add support for localisation.
            }
        }
        return labelOptions;
    }

    /**
     * Set the value of labelOptions
     *
     * @param labelOptions new value of labelOptions
     */
    public void setLabelOptions(List labelOptions) {
        this.labelOptions = labelOptions;
    }

    /**
     * Set the value of typeOptions
     *
     * @param typeOptions new value of typeOptions
     */
    public void setTypeOptions(List typeOptions) {
        this.typeOptions = typeOptions;
    }
    /**
     * 
     */
    protected List motherFormOptions;

    /**
     * Get the value of motherFormOptions
     *
     * @return the value of motherFormOptions
     */
    public List getMotherFormOptions() {
        if (motherFormOptions == null) {
            motherFormOptions = new ArrayList();
            WebFormLogic webFormLogic = new WebFormLogic();
            WebForm tempForm = new WebForm();
            tempForm.setCanBeMother(1);
            List<WebForm> listForms = webFormLogic.listAllMotherWebForms(tempForm, getSessionBean().getUser());

            for (WebForm form : listForms) {
                motherFormOptions.add(new SelectItem(form.getId(), form.getName())); // TODO: add support for localisation.
            }
        }
        return motherFormOptions;
    }

    /**
     * Set the value of motherFormOptions
     *
     * @param motherFormOptions new value of motherFormOptions
     */
    public void setMotherFormOptions(List motherFormOptions) {
        this.motherFormOptions = motherFormOptions;
    }
    /**
     * 
     */
    protected List labelFontOptions;

    /**
     * Get the value of labelFontOptions
     *
     * @return the value of labelFontOptions
     */
    public List getLabelFontOptions() {
        if (labelFontOptions == null) {
            labelFontOptions = new ArrayList();
            labelFontOptions.add(new SelectItem("Cambria,'Times New Roman','Nimbus Roman No9 L','Freeserif',Times,serif", uiTextHandler.getText("timesNewRoman")));
            labelFontOptions.add(new SelectItem("Verdana,Geneva,'DejaVu Sans',sans-serif", uiTextHandler.getText("verdana")));
            labelFontOptions.add(new SelectItem("Arial,'DejaVu Sans','Liberation Sans',Freesans,sans-serif", uiTextHandler.getText("arial")));
            labelFontOptions.add(new SelectItem("'Comic Sans MS',cursive", uiTextHandler.getText("comicSans")));
            labelFontOptions.add(new SelectItem("Helvetica,Arial,'DejaVu Sans','Liberation Sans',Freesans,sans-serif", uiTextHandler.getText("helvetica")));

        }
        return labelFontOptions;
    }

    /**
     * Set the value of labelFontOptions
     *
     * @param labelFontOptions new value of labelFontOptions
     */
    public void setLabelFontOptions(List labelFontOptions) {
        this.labelFontOptions = labelFontOptions;
    }
    /**
     * 
     */
    protected List presetFieldOptions;

    /**
     * Get the value of presetFieldOptions
     *
     * @return the value of presetFieldOptions
     */
    public List getPresetFieldOptions() {
        if (presetFieldOptions == null) {
            presetFieldOptions = new ArrayList();
            PresetFieldLogic presetFieldLogic = new PresetFieldLogic();
            List<PresetField> listPreset = presetFieldLogic.listAll(getSessionBean().getUser());

            Logger.getInstance().log("number preset " + listPreset.size());

            for (PresetField field : listPreset) {
                presetFieldOptions.add(new SelectItem(field.getId(), field.getName())); // TODO: add support for localisation.
            }
        }
        return presetFieldOptions;
    }

    /**
     * Set the value of presetFieldOptions
     *
     * @param presetFieldOptions new value of presetFieldOptions
     */
    public void setPresetFieldOptions(List presetFieldOptions) {
        this.presetFieldOptions = presetFieldOptions;
    }
    /**
     * 
     */
    protected List positionOptions;

    /**
     * Get the value of positionOptions
     *
     * @return the value of positionOptions
     */
    public List getPositionOptions() {
        if (positionOptions == null) {
            positionOptions = new ArrayList();
            int numberWebFields = getSessionBean().getWebForm().getNumberWebfields();
            for (int i = 0; i < numberWebFields; i++) {
                positionOptions.add(new SelectItem(i + 1, Integer.toString(i + 1)));
            }
        }
        return positionOptions;
    }

    /**
     * Set the value of positionOptions
     *
     * @param positionOptions new value of positionOptions
     */
    public void setPositionOptions(List positionOptions) {
        this.positionOptions = positionOptions;
    }
    /**
     * 
     */
    protected List requiredOptions;

    /**
     * Get the value of requiredOptions
     *
     * @return the value of requiredOptions
     */
    public List getRequiredOptions() {
        if (requiredOptions == null) {
            requiredOptions = new ArrayList();
            requiredOptions.add(new SelectItem(1, uiTextHandler.getText("required")));
            requiredOptions.add(new SelectItem(0, uiTextHandler.getText("notRequired")));
        }
        return requiredOptions;
    }

    /**
     * Set the value of requiredOptions
     *
     * @param requiredOptions new value of requiredOptions
     */
    public void setRequiredOptions(List requiredOptions) {
        this.requiredOptions = requiredOptions;
    }
    /**
     * 
     */
    protected List userRightsOptions;

    /**
     * Get the value of userRightsOptions
     *
     * @return the value of userRightsOptions
     */
    public List getUserRightsOptions() {
        if (userRightsOptions == null) {
            userRightsOptions = new ArrayList();
            AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
            Account tempUser = new Account();
            tempUser.setTenantId(getSessionBean().getTenant().getId());
            tempUser.setVerified(-1);
            // Get list of all users for a given form.
            List<Account> listUsers = accountLogic.list(tempUser, getSessionBean().getTenant());
            Logger.getInstance().log("tenant " + tempUser.getTenantId());
            Logger.getInstance().log("size " + listUsers.size());

            for (Account user : listUsers) {
                // Filter users from other tenants.
                if (user.getTenantId() != getSessionBean().getTenant().getId()) {
                    continue;
                }

                // Build name of the user: ID-FIRST-SECOND.
                String nameUser = Integer.toString(user.getId()) + ": "
                        + user.getFirstname() + " " + user.getSurname(); // Add id first second.
                userRightsOptions.add(new SelectItem(user.getId(), nameUser)); // TODO: add support for localisation.
            }
        }
        return userRightsOptions;
    }

    /**
     * Set the value of userRightsOptions
     *
     * @param userRightsOptions new value of userRightsOptions
     */
    public void setUserRightsOptions(List userRightsOptions) {
        this.userRightsOptions = userRightsOptions;
    }
    /**
     * 
     */
    protected List userRightsWebFieldOptions;

    /**
     * Get the value of userRightsOptions
     *
     * @return the value of userRightsOptions
     */
    public List getUserRightsWebFieldOptions() {
        if (userRightsWebFieldOptions == null) {
            userRightsWebFieldOptions = new ArrayList();
            AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
            Account tempUser = new Account();
            tempUser.setTenantId(getSessionBean().getTenant().getId());
            tempUser.setVerified(-1);
            // Get list of all users for a given form.
            List<Account> listUsers = accountLogic.list(tempUser, getSessionBean().getTenant());

            for (Account user : listUsers) {
                // Filter users from other tenants.
                if (user.getTenantId() != getSessionBean().getTenant().getId()) {
                    continue;
                }

                // Build name of the user: ID-FIRST-SECOND.
                String nameUser = Integer.toString(user.getId()) + ": "
                        + user.getFirstname() + " " + user.getSurname(); // Add id first second.
                userRightsWebFieldOptions.add(new SelectItem(user.getId(), nameUser)); // TODO: add support for localisation.
            }
        }
        return userRightsWebFieldOptions;
    }

    /**
     * Set the value of userRightsOptions
     *
     * @param userRightsWebFieldOptions 
     */
    public void setUserRightsWebFieldOptions(List userRightsWebFieldOptions) {
        this.userRightsWebFieldOptions = userRightsWebFieldOptions;
    }

    /**
     * 
     * @return
     */
    public String parseWebField() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String idField = (String) requestMap.get("idField");
        int id = Integer.parseInt(idField);

        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        getSessionBean().setWebField(webFieldLogic.get(id, null));

        return "editWebFieldStyle";
    }

    /**
     *
     * @return
     */
    public String editWebField() {
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        LabelLogic labelLogic = (LabelLogic) LogicFactory.getNewGenericLogic(Label.class);

        boolean success = true;

        LogicResponse logicResponse = webFieldLogic.update(getSessionBean().getWebField(), null);
        if (!logicResponse.isSucceeded()) {
            success = false;
        }

        if (success) {
            logicResponse = labelLogic.update(getSessionBean().getWebField().getLabel(), null);
        }

        if (!logicResponse.isSucceeded()) {
            success = false;
        }

        if (success) {
            return "closePopup";
        } else {
            getSessionBean().getMessageHandler().createMessage("errorEditWebField");
            return null;
        }
    }

/**
    * 
    * @return
    */
public String editWebFieldRights() {
    UserWebFieldLogic userWebFieldLogic = new UserWebFieldLogic();

    // Update user rights for the form
    UserWebField userWebField = new UserWebField();
    userWebField.setWebFieldId(getSessionBean().getWebField().getId());
    // Delete all user rights for the form.
    boolean success = true;
    LogicResponse logicResponse = userWebFieldLogic.deleteAllUsersForForm(userWebField, getSessionBean().getUser());
    if (!logicResponse.isSucceeded()) {
        success = false;
    }

    // Add updated rights.
    userWebField.setWebFieldId(getSessionBean().getWebField().getId());
    for (String userRight : userRightsWebFieldSelected) {
        userWebField.setUserId(Integer.parseInt(userRight));
        logicResponse = userWebFieldLogic.insert(userWebField, getSessionBean().getAccount());
        if (!logicResponse.isSucceeded()) {
            success = false;
            break;
        }
    }

    if (success) {
        return "closePopup";
    } else {
        getSessionBean().getMessageHandler().createMessage("errorEditWebField");
        return null;
    }
}

    /**
     * 
     * @return
     */
    public String editListValues() {
        ListValueLogic listValueLogic = new ListValueLogic();
        boolean success = true;

        for (ListValue value : getSessionBean().newListValues) {
            value.setWebfieldId(getSessionBean().getWebField().getId());
//            value.setEn(" ");
//            value.setRu(" ");
//            value.setFi(" ");
//            value.setUk(" ");
            if (value.isDefaultOption()) {
                value.setDefaultValue(1);
            } else {
                value.setDefaultValue(0);
            }

            if (!value.getText().isEmpty() && !value.getValue().isEmpty()) {
                LogicResponse logicResponse = listValueLogic.insert(value, null);
                if (!logicResponse.isSucceeded()) {
                    success = false;
                    break;
                }
            }
        }

        if (success) {
            return "closePopup";
        } else {
            getSessionBean().getMessageHandler().createMessage("errorEditWebField");
            return null;
        }
    }

    /**
     * 
     * @return
     */
    public String chooseLabel() {
        getSessionBean().newWebFieldsInForm.get(getSessionBean().getWebField().getId() - 1).
                setLabelId(getSessionBean().getWebField().getLabelId());
        return "closePopup";
    }
    /**
     * 
     */
    protected List<String> userRightsSelected;

    /**
     * Get the value of userRightsSelected
     *
     * @return the value of userRightsSelected
     */
    public List<String> getUserRightsSelected() {
        // Preset selected items.  
        if (userRightsSelected == null) {
            userRightsSelected = new ArrayList();
            UserWebFormLogic userWebFormLogic = (UserWebFormLogic) LogicFactory.getNewGenericLogic(UserWebForm.class);
            AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
            UserWebForm tempUserForm = new UserWebForm();
            tempUserForm.setWebFormId(getSessionBean().getWebForm().getId());
            // Get list of all users for a given form.
            List<UserWebForm> listUserForms = userWebFormLogic.listUserRights(tempUserForm, getSessionBean().getUser());

            for (UserWebForm userForm : listUserForms) {
                Account account = accountLogic.get(userForm.getUserId());

                // Filter users from other tenants.
                if (account.getTenantId() != getSessionBean().getTenant().getId()) {
                    continue;
                }
                Logger.getInstance().log("user id: " + account.getId());
                userRightsSelected.add(Integer.toString(account.getId())); // TODO: add support for localisation.
            }
        }


        return userRightsSelected;
    }

    /**
     * Set the value of userRightsSelected
     *
     * @param userRightsSelected new value of userRightsSelected
     */
    public void setUserRightsSelected(List<String> userRightsSelected) {
        this.userRightsSelected = userRightsSelected;
    }
    /**
     * 
     */
    protected List<String> userRightsWebFieldSelected;

    /**
     * Get the value of userRightsWebFieldSelected
     *
     * @return the value of userRightsWebFieldSelected
     */
    public List<String> getUserRightsWebFieldSelected() {
        if (userRightsWebFieldSelected == null) {
            userRightsWebFieldSelected = new ArrayList();
            UserWebFieldLogic userWebFieldLogic = (UserWebFieldLogic) LogicFactory.getNewGenericLogic(UserWebField.class);
            AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
            UserWebField tempUserField = new UserWebField();
            tempUserField.setWebFieldId(getSessionBean().getWebField().getId());
            // Get list of all users for a given Field.
            List<UserWebField> listUserFields = userWebFieldLogic.listUserRights(tempUserField, getSessionBean().getUser());

            for (UserWebField userField : listUserFields) {
                // Build name of the user: ID-FIRST-SECOND
                Account account = accountLogic.get(userField.getUserId());

                // Filter users from other tenants.
                if (account.getTenantId() != getSessionBean().getTenant().getId()) {
                    continue;
                }
                userRightsWebFieldSelected.add(Integer.toString(account.getId())); // TODO: add support for localisation.
            }
        }
        return userRightsWebFieldSelected;
    }

    /**
     * Set the value of userRightsWebFieldSelected
     *
     * @param userRightsWebFieldSelected new value of userRightsWebFieldSelected
     */
    public void setUserRightsWebFieldSelected(List<String> userRightsWebFieldSelected) {
        this.userRightsWebFieldSelected = userRightsWebFieldSelected;
    }
}
