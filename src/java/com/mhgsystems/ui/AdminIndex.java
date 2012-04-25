///*
// * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
// *
// */
//package com.mhgsystems.ui;
//
//import com.mhgsystems.logic.AccountLogic;
//import com.mhgsystems.logic.LogicFactory;
//import com.mhgsystems.logic.WebFormLogic;
//import com.mhgsystems.model.Account;
//import com.mhgsystems.model.WebForm;
//import java.util.List;
//import java.util.Map;
//import javax.faces.context.FacesContext;
//
//
///**
// *
// * @author Pavlo Bazilinskyy
// */
//public class AdminIndex extends BaseView {
//
//    //TODO admin login
//
//    /**
//     *
//     */
//    @Override
//    public void prerender() {
//    }
//
//    protected int numberUnverifiedUsers;
//
//    /**
//     * Get the value of numberUnverifiedUsers
//     *
//     * @return the value of numberUnverifiedUsers
//     */
//    public int getNumberUnverifiedUsers() {
//        List list = null;
//        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
//        Account searchAccount = new Account();
//        searchAccount.setVerified(0);
//        list = accountLogic.list(searchAccount, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberUnverifiedUsers
//     *
//     * @param numberUnverifiedUsers new value of numberUnverifiedUsers
//     */
//    public void setNumberUnverifiedUsers(int numberUnverifiedUsers) {
//        this.numberUnverifiedUsers = numberUnverifiedUsers;
//    }
//
//    protected int numberVerifiedUsers;
//
//    /**
//     * Get the value of numberVerifiedUsers
//     *
//     * @return the value of numberVerifiedUsers
//     */
//    public int getNumberVerifiedUsers() {
//        List list = null;
//        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
//        Account searchAccount = new Account();
//        searchAccount.setVerified(1);
//        list = accountLogic.list(searchAccount, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberVerifiedUsers
//     *
//     * @param numberVerifiedUsers new value of numberVerifiedUsers
//     */
//    public void setNumberVerifiedUsers(int numberVerifiedUsers) {
//        this.numberVerifiedUsers = numberVerifiedUsers;
//    }
//
//    protected int numberAllUsers;
//
//    /**
//     * Get the value of numberAllUsers
//     *
//     * @return the value of numberAllUsers
//     */
//    public int getNumberAllUsers() {
//        List list = null;
//        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
//        Account searchAccount = new Account();
//        searchAccount.setVerified(-1);
//        list = accountLogic.list(searchAccount, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberAllUsers
//     *
//     * @param numberAllUsers new value of numberAllUsers
//     */
//    public void setNumberAllUsers(int numberAllUsers) {
//        this.numberAllUsers = numberAllUsers;
//    }
//
//    protected int numberUnconfirmedWebForms;
//
//    /**
//     * Get the value of numberUnconfirmedWebForms
//     *
//     * @return the value of numberUnconfirmedWebForms
//     */
//    public int getNumberUnconfirmedWebForms() {
//        List list = null;
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//        searchWebForm.setStatus(0);
//        searchWebForm.setConfirmationShown(-1);
//        searchWebForm.setWorkSortCd(-1);
//        list = webFormLogic.list(searchWebForm, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberUnconfirmedWebForms
//     *
//     * @param numberUnconfirmedWebForms new value of numberUnconfirmedWebForms
//     */
//    public void setNumberUnconfirmedWebForms(int numberUnconfirmedWebForms) {
//        this.numberUnconfirmedWebForms = numberUnconfirmedWebForms;
//    }
//    protected int numberConfirmedWebForms;
//
//    /**
//     * Get the value of numberConfirmedWebForms
//     *
//     * @return the value of numberConfirmedWebForms
//     */
//    public int getNumberConfirmedWebForms() {
//        List list = null;
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//        searchWebForm.setStatus(1);
//        searchWebForm.setConfirmationShown(-1);
//        searchWebForm.setWorkSortCd(-1);
//        list = webFormLogic.list(searchWebForm, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberConfirmedWebForms
//     *
//     * @param numberConfirmedWebForms new value of numberConfirmedWebForms
//     */
//    public void setNumberConfirmedWebForms(int numberConfirmedWebForms) {
//        this.numberConfirmedWebForms = numberConfirmedWebForms;
//    }
//    protected int numberCompletedWebForms;
//
//    /**
//     * Get the value of numberCompletedWebForms
//     *
//     * @return the value of numberCompletedWebForms
//     */
//    public int getNumberCompletedWebForms() {
//        List list = null;
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//        searchWebForm.setStatus(3);
//        searchWebForm.setConfirmationShown(-1);
//        searchWebForm.setWorkSortCd(-1);
//        list = webFormLogic.list(searchWebForm, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberCompletedWebForms
//     *
//     * @param numberCompletedWebForms new value of numberCompletedWebForms
//     */
//    public void setNumberCompletedWebForms(int numberCompletedWebForms) {
//        this.numberCompletedWebForms = numberCompletedWebForms;
//    }
//    protected int numberRejectedWebForms;
//
//    /**
//     * Get the value of numberRejectedWebForms
//     *
//     * @return the value of numberRejectedWebForms
//     */
//    public int getNumberRejectedWebForms() {
//        List list = null;
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//        searchWebForm.setStatus(4);
//        searchWebForm.setConfirmationShown(-1);
//        searchWebForm.setWorkSortCd(-1);
//        list = webFormLogic.list(searchWebForm, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberRejectedWebForms
//     *
//     * @param numberRejectedWebForms new value of numberRejectedWebForms
//     */
//    public void setNumberRejectedWebForms(int numberRejectedWebForms) {
//        this.numberRejectedWebForms = numberRejectedWebForms;
//    }
//    protected int numberRejectedMsgWebForms;
//
//    /**
//     * Get the value of numberRejectedMsgWebForms
//     *
//     * @return the value of numberRejectedMsgWebForms
//     */
//    public int getNumberRejectedMsgWebForms() {
//        List list = null;
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//        searchWebForm.setStatus(5);
//        searchWebForm.setConfirmationShown(-1);
//        searchWebForm.setWorkSortCd(-1);
//        list = webFormLogic.list(searchWebForm, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberRejectedMsgWebForms
//     *
//     * @param numberRejectedMsgWebForms new value of numberRejectedMsgWebForms
//     */
//    public void setNumberRejectedMsgWebForms(int numberRejectedMsgWebForms) {
//        this.numberRejectedMsgWebForms = numberRejectedMsgWebForms;
//    }
//    protected int numberAllWebForms;
//
//    /**
//     * Get the value of numberAllWebForms
//     *
//     * @return the value of numberAllWebForms
//     */
//    public int getNumberAllWebForms() {
//        List list = null;
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//        searchWebForm.setStatus(-1);
//        searchWebForm.setConfirmationShown(-1);
//        searchWebForm.setWorkSortCd(-1);
//        list = webFormLogic.list(searchWebForm, getSessionBean().getTenant());
//
//        return list.size();
//    }
//
//    /**
//     * Set the value of numberAllWebForms
//     *
//     * @param numberAllWebForms new value of numberAllWebForms
//     */
//    public void setNumberAllWebForms(int numberAllWebForms) {
//        this.numberAllWebForms = numberAllWebForms;
//    }
//
//    public String setManageUsers() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        Map requestMap = context.getExternalContext().getRequestParameterMap();
//        String typeStr = (String)requestMap.get("type");
//        int type = Integer.parseInt(typeStr);
//
//        if (getSessionBean().getSearchAccount() != null) {
//            getSessionBean().setSearchAccount(new Account());
//            getSessionBean().getSearchAccount().setVerified(type);
//            getSessionBean().setTypeBy(type);
//            return "manageUsers";
//        } else {
//            return null;
//        }
//    }
//
//    public String setManageWebForms() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        Map requestMap = context.getExternalContext().getRequestParameterMap();
//        String typeStr = (String)requestMap.get("type");
//        int type = Integer.parseInt(typeStr);
//
//        if (getSessionBean().getSearchWebFormAdmin() != null) {
//            getSessionBean().setSearchWebFormAdmin(new WebForm());
//            getSessionBean().getSearchWebFormAdmin().setStatus(type);
//            getSessionBean().setTypeBy(type);
//            return "manageWebForms";
//        } else {
//            return null;
//        }
//    }
//}
