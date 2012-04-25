/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.MessageHandler;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.MotherChildWebFieldLogic;
import com.mhgsystems.logic.UserWebFieldLogic;
import com.mhgsystems.logic.WebFieldLogic;
import com.mhgsystems.model.*;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
public class SessionBean implements Serializable {

    private HashMap<String, Locale> locales = null;

    /**
     *
     * @return
     */
    public HashMap<String, Locale> getLocales() {
        return locales;
    }

    /**
     *
     * @param locales
     */
    public void setLocales(HashMap<String, Locale> locales) {
        this.locales = locales;
    }

    /**
     * Setting global vars for the session
     *
     */
    public SessionBean() {
        this.messageHandler = new MessageHandler();
        
        locales = new HashMap<String, Locale>(2);
        locales.put("english", new Locale("en", "UK"));
        locales.put("finnish", new Locale("fi", "FI"));
        locales.put("russian", new Locale("ru", "RU"));
        locales.put("ukrainian", new Locale("uk", "UA"));

        setLanguage("en");

        setSearchWebForm(new WebForm());

//
//        setSearchWebFormAdmin(new WebForm());
//        getSearchWebFormAdmin().setStatus(0);
//        getSearchWebFormAdmin().setConfirmationShown(-1);
        //TODO remove if not needed

        setSearchAccount(new Account());
        getSearchAccount().setVerified(0);

    }
    
    private String language;

    /**
     * Get the value of language
     *
     * @return the value of language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the value of language
     *
     * @param language new value of language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    private User user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     *
     */
    protected Account account;

    /**
     * Get the value of account
     *
     * @return the value of account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Set the value of account
     *
     * @param account new value of account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     *
     */
    protected Tenant tenant;

    /**
     * Get the value of tenant
     *
     * @return the value of tenant
     */
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * Set the value of tenant
     *
     * @param tenant new value of tenant
     */
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    /**
     *
     */
    protected WebForm searchWebForm;

    /**
     * Get the value of searchWebForm
     *
     * @return the value of searchWebForm
     */
    public WebForm getSearchWebForm() {
        return searchWebForm;
    }

    /**
     * Set the value of searchWebForm
     *
     * @param searchWebForm new value of searchWebForm
     */
    public void setSearchWebForm(WebForm searchWebForm) {
        this.searchWebForm = searchWebForm;
    }


    private MessageHandler messageHandler;

    /**
     * Get the value of messageHandler
     *
     * @return the value of messageHandler
     */
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * Set the value of messageHandler
     *
     * @param messageHandler new value of messageHandler
     */
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    /**
     *
     * @param event
     */
    public void onChooseLocale(ActionEvent event) {
        String current = event.getComponent().getId();
        FacesContext fcontext = FacesContext.getCurrentInstance();
        fcontext.getViewRoot().setLocale((Locale) getLocales().get(current));
        setLanguage(getLocales().get(current).getLanguage());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie btlang = new Cookie("btlang", getLocales().get(current).getLanguage());
        btlang.setMaxAge(3600);
        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btlang);

        Cookie btcountry = new Cookie("btcountry", getLocales().get(current).getCountry());
        btcountry.setMaxAge(3600);
        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btcountry);
    }

    /**
     * Temp Web Form
     */
    protected WebForm webForm;

    /**
     * Get the value of webForm
     *
     * @return the value of webForm
     */
    public WebForm getWebForm() {
        return webForm;
    }

    /**
     * Set the value of webForm
     *
     * @param webForm new value of webForm
     */
    public void setWebForm(WebForm webForm) {
        this.webForm = webForm;
    }

    /**
     *
     */
    protected WebForm searchWebFormAdmin;

    /**
     * Get the value of searchWebFormAdmin
     *
     * @return the value of searchWebFormAdmin
     */
    public WebForm getSearchWebFormAdmin() {
        return searchWebFormAdmin;
    }

    /**
     * Set the value of searchWebFormAdmin
     *
     * @param searchWebFormAdmin 
     */
    public void setSearchWebFormAdmin(WebForm searchWebFormAdmin) {
        this.searchWebFormAdmin = searchWebFormAdmin;
    }

    /**
     *
     */
    protected Account searchAccount;

    /**
     * Get the value of searchAccount
     *
     * @return the value of searchAccount
     */
    public Account getSearchAccount() {
        return searchAccount;
    }

    /**
     * Set the value of searchAccount
     *
     * @param searchAccount new value of searchAccount
     */
    public void setSearchAccount(Account searchAccount) {
        this.searchAccount = searchAccount;
    }

    /**
     *
     */
    protected Tenant searchTenant;

    /**
     * Get the value of searchTenant
     *
     * @return the value of searchTenant
     */
    public Tenant getSearchTenant() {
        return searchTenant;
    }

    /**
     * Set the value of searchTenant
     *
     * @param searchTenant new value of searchTenant
     */
    public void setSearchTenant(Tenant searchTenant) {
        this.searchTenant = searchTenant;
    }

    /**
     *
     */
    protected boolean confirmationEditAccount;

    /**
     * Get the value of confirmationEditAccount
     *
     * @return the value of confirmationEditAccount
     */
    public boolean isConfirmationEditAccount() {
        return confirmationEditAccount;
    }

    /**
     * Set the value of confirmationEditAccount
     *
     * @param confirmationEditAccount new value of confirmationEditAccount
     */
    public void setConfirmationEditAccount(boolean confirmationEditAccount) {
        this.confirmationEditAccount = confirmationEditAccount;
    }

    /**
     *
     */
    protected boolean confirmationConfiguration;

    /**
     * Get the value of confirmationConfiguration
     *
     * @return the value of confirmationConfiguration
     */
    public boolean isConfirmationConfiguration() {
        return confirmationConfiguration;
    }

    /**
     * Set the value of confirmationConfiguration
     *
     * @param confirmationConfiguration new value of confirmationConfiguration
     */
    public void setConfirmationConfiguration(boolean confirmationConfiguration) {
        this.confirmationConfiguration = confirmationConfiguration;
    }

    /**
     *
     */
    protected boolean confirmationNewWebForm;

    /**
     * Get the value of confirmationNewWebForm
     *
     * @return the value of confirmationNewWebForm
     */
    public boolean isConfirmationNewWebForm() {
        return confirmationNewWebForm;
    }

    /**
     * Set the value of confirmationNewWebForm
     *
     * @param confirmationNewWebForm new value of confirmationNewWebForm
     */
    public void setConfirmationNewWebForm(boolean confirmationNewWebForm) {
        this.confirmationNewWebForm = confirmationNewWebForm;
    }

    /**
     *
     */
    protected boolean confirmationEditWebForm;

    /**
     * Get the value of confirmationEditWebForm
     *
     * @return the value of confirmationEditWebForm
     */
    public boolean isConfirmationEditWebForm() {
        return confirmationEditWebForm;
    }

    /**
     * Set the value of confirmationEditWebForm
     *
     * @param confirmationEditWebForm new value of confirmationEditWebForm
     */
    public void setConfirmationEditWebForm(boolean confirmationEditWebForm) {
        this.confirmationEditWebForm = confirmationEditWebForm;
    }

    /**
     *
     */
    protected boolean confirmationEditUser;

    /**
     * Get the value of confirmationEditUser
     *
     * @return the value of confirmationEditUser
     */
    public boolean isConfirmationEditUser() {
        return confirmationEditUser;
    }

    /**
     * Set the value of confirmationEditUser
     *
     * @param confirmationEditUser new value of confirmationEditUser
     */
    public void setConfirmationEditUser(boolean confirmationEditUser) {
        this.confirmationEditUser = confirmationEditUser;
    }

    /**
     *
     */
    protected boolean showConfirmationImage;

    /**
     * Get the value of showConfirmationImage
     *
     * @return the value of showConfirmationImage
     */
    public boolean isShowConfirmationImage() {
        return showConfirmationImage;
    }

    /**
     * Set the value of showConfirmationImage
     *
     * @param showConirmationImage
     */
    public void setShowConfirmationImage(boolean showConirmationImage) {
        this.showConfirmationImage = showConirmationImage;
    }
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /**
     * Add VetoableChangeListener.
     *
     * @param listener
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Remove VetoableChangeListener.
     *
     * @param listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }
    
    protected boolean confirmationFormDeleted;

    /**
     * Get the value of confirmationFormDeleted
     *
     * @return the value of confirmationFormDeleted
     */
    public boolean isConfirmationFormDeleted() {
        return confirmationFormDeleted;
    }

    /**
     * Set the value of confirmationFormDeleted
     *
     * @param confirmationFormDeleted new value of confirmationFormDeleted
     */
    public void setConfirmationFormDeleted(boolean confirmationFormDeleted) {
        this.confirmationFormDeleted = confirmationFormDeleted;
    }
    
    protected boolean confirmationFormEdited;

    /**
     * Get the value of confirmationFormEdited
     *
     * @return the value of confirmationFormEdited
     */
    public boolean isConfirmationFormEdited() {
        return confirmationFormEdited;
    }

    /**
     * Set the value of confirmationFormEdited
     *
     * @param confirmationFormEdited new value of confirmationFormEdited
     */
    public void setConfirmationFormEdited(boolean confirmationFormEdited) {
        this.confirmationFormEdited = confirmationFormEdited;
    }


    //Used to determine which confirmation message to show
    void setConfirmation(String type) {

        //Set to false by default
        confirmationConfiguration = false;
        confirmationEditAccount = false;
        confirmationEditUser = false;
        confirmationEditWebForm = false;
        confirmationNewWebForm = false;
        confirmationFormDeleted = false;
        confirmationFormEdited = false;

        if (type.equals("editAccount"))
            confirmationEditAccount = true;
        else if (type.equals("configuration"))
            confirmationConfiguration = true;
        else if (type.equals("editUser"))
            confirmationEditUser = true;
        else if (type.equals("editWebForm"))
            confirmationEditWebForm = true;
        else if (type.equals("newWebForm"))
            confirmationNewWebForm = true;
        else if (type.equals("formDeleted"))
            confirmationFormDeleted = true;
         else if (type.equals("formEdited"))
            confirmationFormEdited = true;

        showConfirmationImage = true;
    }

    /**
     * Used in UI for checks of login status
     *
     */
    protected boolean loggedIn = false;

    /**
     * Get the value of loggedIn
     *
     * @return the value of loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Set the value of loggedIn
     *
     * @param loggedIn new value of loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *
     */
    protected int numberWebFormsSubmited;

    /**
     * Get the value of numberWebFormsSubmited
     *
     * @return the value of numberWebFormsSubmited
     */
    public int getNumberWebFormsSubmited() {
        return numberWebFormsSubmited;
    }

    /**
     * Set the value of numberWebFormsSubmited
     *
     * @param numberWebFormsSubmited new value of numberWebFormsSubmited
     */
    public void setNumberWebFormsSubmited(int numberWebFormsSubmited) {
        this.numberWebFormsSubmited = numberWebFormsSubmited;
    }

    /**
     *
     */
    protected boolean showCaptcha = true;

    /**
     * Get the value of showCaptcha
     *
     * @return the value of showCaptcha
     */
    public boolean isShowCaptcha() {
        return showCaptcha;
    }

    /**
     * Set the value of showCaptcha
     *
     * @param showCaptcha new value of showCaptcha
     */
    public void setShowCaptcha(boolean showCaptcha) {
        this.showCaptcha = showCaptcha;
    }

    /**
     * 
     */
    protected boolean showNextBtn;

    /**
     * Get the value of showNextBtn
     *
     * @return the value of showNextBtn
     */
    public boolean isShowNextBtn() {
        return showNextBtn;
    }

    /**
     * Set the value of showNextBtn
     *
     * @param showNextBtn new value of showNextBtn
     */
    public void setShowNextBtn(boolean showNextBtn) {
        this.showNextBtn = showNextBtn;
    }

    /**
     * 
     */
    protected boolean showPreviousBtn;

    /**
     * Get the value of showPreviousBtn
     *
     * @return the value of showPreviousBtn
     */
    public boolean isShowPreviousBtn() {
        return showPreviousBtn;
    }

    /**
     * Set the value of showPreviousBtn
     *
     * @param showPreviousBtn new value of showPreviousBtn
     */
    public void setShowPreviousBtn(boolean showPreviousBtn) {
        this.showPreviousBtn = showPreviousBtn;
    }

    /**
     * 
     */
    protected int startingIndexOfListToShow;

    /**
     * Get the value of startingIndexOfListToShow
     *
     * @return the value of startingIndexOfListToShow
     */
    public int getStartingIndexOfListToShow() {
        return startingIndexOfListToShow;
    }

    /**
     * Set the value of startingIndexOfListToShow
     *
     * @param startingIndexOfListToShow new value of startingIndexOfListToShow
     */
    public void setStartingIndexOfListToShow(int startingIndexOfListToShow) {
        this.startingIndexOfListToShow = startingIndexOfListToShow;
    }
    /**
     *
     */
    protected Boolean listShowNewWebForms = true;

    /**
     * Get the value of listShowNewWebForms
     *
     * @return the value of listShowNewWebForms
     */
    public Boolean getListShowNewWebForms() {
        return listShowNewWebForms;
    }

    /**
     * Set the value of listShowNewWebForms
     *
     * @param listShowNewWebForms new value of listShowNewWebForms
     */
    public void setListShowNewWebForms(Boolean listShowNewWebForms) {
        this.listShowNewWebForms = listShowNewWebForms;
    }
    
    protected Boolean listShowConfirmedWebForms = true;

    /**
     * Get the value of listShowConfirmedWebForms
     *
     * @return the value of listShowConfirmedWebForms
     */
    public Boolean getListShowConfirmedWebForms() {
        return listShowConfirmedWebForms;
    }

    /**
     * Set the value of listShowConfirmedWebForms
     *
     * @param listShowConfirmedWebForms new value of listShowConfirmedWebForms
     */
    public void setListShowConfirmedWebForms(Boolean listShowConfirmedWebForms) {
        this.listShowConfirmedWebForms = listShowConfirmedWebForms;
    }


    /**
     * 
     */
    protected int sortBy = 1;

    /**
     * Get the value of sortBy
     *
     * @return the value of sortBy
     */
    public int getSortBy() {
        return sortBy;
    }

    /**
     * Set the value of sortBy
     *
     * @param sortBy new value of sortBy
     */
    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * 
     */
    protected int currentSortBy = 1;

    /**
     * Get the value of currentSortBy
     * Used for checks if setting page to 1 is needed.
     *
     * @return the value of currentSortBy
     */
    public int getCurrentSortBy() {
        return currentSortBy;
    }

    /**
     * Set the value of currentSortBy
     *
     * @param currentSortBy new value of currentSortBy
     */
    public void setCurrentSortBy(int currentSortBy) {
        this.currentSortBy = currentSortBy;
    }

    /**
     * 
     */
    protected String currentView = "login";

    /**
     * Get the value of currentView
     *
     * @return the value of currentView
     */
    public String getCurrentView() {
        return currentView;
    }

    /**
     * Set the value of currentView
     *
     * @param currentView new value of currentView
     */
    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    /**
     * 
     */
    protected int typeBy = 1;

    /**
     * Get the value of typeBy
     *
     * @return the value of typeBy
     */
    public int getTypeBy() {
        return typeBy;
    }

    /**
     * Set the value of typeBy
     *
     * @param typeBy new value of typeBy
     */
    public void setTypeBy(int typeBy) {
        this.typeBy = typeBy;
    }
    
    protected WebField webField;

    /**
     * Get the value of webField
     *
     * @return the value of webField
     */
    public WebField getWebField() {
        return webField;
    }

    /**
     * Set the value of webField
     *
     * @param webField new value of webField
     */
    public void setWebField(WebField webField) {
        this.webField = webField;
    }
    
     /**
     *  List of confirmed web forms in the sidebar.
     */
    protected List<WebField> webFieldsInForm;

    /**
     * Get the value of webFieldsInForm
     *
     * @return the value of webFieldsInForm
     */
    public List getWebFieldsInForm() {
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        WebField webField = new WebField();
        webField.setWebformId(getWebForm().getId());
        List list =  webFieldLogic.list(webField, getUser());
        webFieldsInForm = list;
        
        return list;
    }
    
    /**
     *  List of web fields excluding those that a user has no privileges for working with.
     */
    protected List<WebField> webFieldsInFormForUser;

    /**
     * Get the value of webFieldsInFormForUser
     *
     * @return the value of webFieldsInForm
     */
    public List getWebFieldsInFormForUser() {
        WebFieldLogic webFieldLogic = (WebFieldLogic) LogicFactory.getNewGenericLogic(WebField.class);
        WebField webField = new WebField();
        webField.setWebformId(getWebForm().getId());
        List<WebField> list =  webFieldLogic.list(webField, getUser());
        
        // Filter web fields without privileges.
        UserWebFieldLogic userWebFieldLogic = (UserWebFieldLogic) LogicFactory.getNewGenericLogic(UserWebField.class);
        UserWebField tempUserWebField = new UserWebField();
        ArrayList<WebField> finalList =  new ArrayList();
        
        // Check if a user has rights for the field.
        for (WebField tempField : list) {            
            tempUserWebField = new UserWebField();
            tempUserWebField.setWebFieldId(tempField.getId());
            tempUserWebField = userWebFieldLogic.checkUserRightsField(tempUserWebField, getUser());
            if (tempUserWebField != null) {
                // Check if field is a child from another field.
                MotherChildWebFieldLogic motherChildWebFieldLogic = new MotherChildWebFieldLogic();
                MotherChildWebField motherChildWebField = new MotherChildWebField();
                motherChildWebField.setChildId(tempField.getId());
                MotherChildWebField tempMotherChildWebField = new MotherChildWebField();
                tempMotherChildWebField = motherChildWebFieldLogic.checkIfFieldIsChild(motherChildWebField, getUser());
                if (tempMotherChildWebField != null) { // Web field is a child, set boolean property child to TRUE.
                    tempField.setChild(true);
                }
                
                // Check if field is a mother from another field.
                motherChildWebField.setMotherId(tempField.getId());
                tempMotherChildWebField = null; // Set to null after check fo child above.
                tempMotherChildWebField = motherChildWebFieldLogic.checkIfFieldIsMother(motherChildWebField, getUser());
                if (tempMotherChildWebField != null) { // Web field is a mother, set boolean property child to TRUE.
                    tempField.setMother(true);
                }
                
                // Possible exception is when one field is both child and mother. TODO: improve code for this situation.
                
                // Add field to the list that is to be returned.
                finalList.add(tempField);
            }
        }
        
        webFieldsInFormForUser = finalList;
        
        return finalList;
    }
    
    
    protected List<WebField> newWebFieldsInForm;

    /**
     * Get the value of newWebFieldsInForm
     *
     * @return the value of newWebFieldsInForm
     */
    public List<WebField> getNewWebFieldsInForm() {
        WebField webField = new WebField();
        ArrayList<WebField> list = new ArrayList();
        
        for (int i = 0; i < 40; i++) {
            list.add(new WebField());
            list.get(i).setLabel(new Label());
            list.get(i).setPositionInWebform(1);
            list.get(i).setId(i+1);
            list.get(i).setLabelId(-1);
        }
        
        newWebFieldsInForm = list;

        return list;
    }

    /**
     * Set the value of newWebFieldsInForm
     *
     * @param newWebFieldsInForm new value of newWebFieldsInForm
     */
    public void setNewWebFieldsInForm(List<WebField> newWebFieldsInForm) {
        this.newWebFieldsInForm = newWebFieldsInForm;
    }
    
    protected List<ListValue> newListValues;

    /**
     * Get the value of newListValues
     *
     * @return the value of newListValues
     */
    public List<ListValue> getNewListValues() {
        ArrayList<ListValue> list = new ArrayList();
        
        for (int i = 0; i < 40; i++) {
            list.add(new ListValue());
            list.get(i).setPositionInList(i+1);
        }
        
        newListValues = list;

        return list;
    }

    /**
     * Set the value of newListValues
     *
     * @param newListValues new value of newListValues
     */
    public void setNewListValues(List<ListValue> newListValues) {
        this.newListValues = newListValues;
    }
    
    protected List<WebField> webFieldsInFormMotherForm;

    /**
     * Get the value of webFieldsInFormMotherForm
     *
     * @return the value of webFieldsInFormMotherForm
     */
    public List<WebField> getWebFieldsInFormMotherForm() {
        return webFieldsInFormMotherForm;
    }

    /**
     * Set the value of webFieldsInFormMotherForm
     *
     * @param webFieldsInFormMotherForm new value of webFieldsInFormMotherForm
     */
    public void setWebFieldsInFormMotherForm(List<WebField> webFieldsInFormMotherForm) {
        this.webFieldsInFormMotherForm = webFieldsInFormMotherForm;
    }
    
    protected boolean deleteForm = false;

    /**
     * Get the value of deleteForm
     *
     * @return the value of deleteForm
     */
    public boolean isDeleteForm() {
        return deleteForm;
    }

    /**
     * Set the value of deleteForm
     *
     * @param deleteForm new value of deleteForm
     */
    public void setDeleteForm(boolean deleteForm) {
        this.deleteForm = deleteForm;
    }

}
