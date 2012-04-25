/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.UserWebFormLogic;
import com.mhgsystems.logic.WebFormLogic;
import com.mhgsystems.model.UserWebForm;
import com.mhgsystems.model.WebForm;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class Home extends BaseView {

    /**
     *
     */
    @Override
    public void prerender() {
    }

    /**
     * 
     */
    public Home() {
        
        // Determing if to show Next button
        if (getSessionBean().getStartingIndexOfListToShow() <= getListWebformsSize() - 1 - getSessionBean().getTenant().getNumberWebFormsPerPage()) {
            showNextBtn = true;
        } else {
            showNextBtn = false;
        }

        //Determing if to show Prevous button
        if (getSessionBean().getStartingIndexOfListToShow() >= getSessionBean().getTenant().getNumberWebFormsPerPage()) {
            showPreviousBtn = true;
        } else {
            showPreviousBtn = false;
        }
        
    }
    /**
     *
     */
    protected List webFormList;

    /**
     * Get the list of work orders for a current user
     *
     * @return the value of webFormList
     */
    public List getWebFormList() {        
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        return webFormLogic.listWithLimit(getSessionBean().getStartingIndexOfListToShow(),
                    getSessionBean().getTenant().getNumberWebFormsPerPage(), null, getSessionBean().getUser());
        
    }

    /**
     * Set the value of webFormList
     *
     * @param webFormList new value of webFormList
     */
    public void setWebFormList(List webFormList) {
        this.webFormList = webFormList;
    }
    /**
     *
     */
    protected Boolean buttonShowEdit = true;

    /**
     * Get the value of buttonShowEdit
     *
     * @return the value of buttonShowEdit
     */
    public Boolean getButtonShowEdit() {
//        if (getSessionBean().getSearchWebFormAdmin() != null) {
//            if (getSessionBean().getSearchWebForm().getStatus() == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }

        return true;
        //TODO review
    }

    /**
     * Set the value of buttonShowEdit
     *
     * @param buttonShowEdit new value of buttonShowEdit
     */
    public void setButtonShowEdit(Boolean buttonShowEdit) {
        this.buttonShowEdit = buttonShowEdit;
    }
    /**
     *
     */
    protected Boolean buttonShowView = true;

    /**
     * Get the value of buttonShowView
     *
     * @return the value of buttonShowView
     */
    public Boolean getButtonShowView() {
//        if (getSessionBean().getSearchWebFormAdmin() != null) {
//            if (getSessionBean().getSearchWebForm().getStatus() == 0) {
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
        return true;
        //TODO review
    }

    /**
     * Set the value of buttonShowView
     *
     * @param buttonShowView new value of buttonShowView
     */
    public void setButtonShowView(Boolean buttonShowView) {
        this.buttonShowView = buttonShowView;
    }
    /**
     * 
     */
    protected List webFormsNew;

    /**
     * Get the value of webFormsRejectedUser
     *
     * @return the value of webFormsRejectedUser
     */
    public List getWebFormsNew() {
//        //Checking if any work order made by the user were confirmed or rejected
//        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
//        WebForm searchWebForm = new WebForm();
//
//        //Confirmed
//        searchWebForm.setStatus(4);
//        List<WebForm> list = webFormLogic.list(searchWebForm, getUser());
//
//        //Indicate that message shown
//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).setStatus(5);
//            LogicResponse logicResponse = webFormLogic.update(list.get(i), null);
//
//            if (!logicResponse.isSucceeded()) {
//                getSessionBean().getMessageHandler().createMessage("errorEditWebForm");
//                return null;
//            }
//        }
//
//        return list;
        
        throw new UnsupportedOperationException("Not supported yet.");
        
    }
    /**
     * Set the value of webFormsRejectedUser
     *
     * @param webFormsNew 
     */
    public void setWebFormsNew(List webFormsNew) {
        this.webFormsNew = webFormsNew;
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
     * @return
     */
    public String previousPage() {
        //Setting a starting index for next page
        getSessionBean().setStartingIndexOfListToShow(
                getSessionBean().getStartingIndexOfListToShow() - getSessionBean().getTenant().getNumberWebFormsPerPage());

        refresh();
        return null;
    }

    /**
     * 
     * @return
     */
    public String nextPage() {
        //Setting a starting index for next page
        getSessionBean().setStartingIndexOfListToShow(
                getSessionBean().getStartingIndexOfListToShow() + getSessionBean().getTenant().getNumberWebFormsPerPage());

        refresh();
        return null;
    }
    

    /**
     * 
     */
    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);

    }
    /**
     * 
     */
    protected int listWebFormsSize;

    /**
     * Get the value of listWebFormsSize
     *
     * @return the value of listWebFormsSize
     */
    public int getListWebformsSize() {
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        return webFormLogic.list(null, getSessionBean().getUser()).size();
    }

    /**
     * Set the value of listWebFormsSize
     *
     * @param listWebFormsSize new value of listWebFormsSize
     */
    public void setListWebformsSize(int listWebFormsSize) {
        this.listWebFormsSize = listWebFormsSize;
    }
    
    /**
     *  List of newly created web forms shown in the sidebar.
     */
    protected List newWebFormsSidebar;

    /**
     * Get the value of newWebFormsSidebar
     *
     * @return the value of newWebFormsSidebar
     */
    public List getNewWebFormsSidebar() {
 
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        List list =  webFormLogic.listWithLimit(0,
                    getSessionBean().getTenant().getNumberWebFormsSidebar(), null, getSessionBean().getUser());

        if (list.size() < 1) {
            getSessionBean().setListShowNewWebForms(false);
        }  else {
            getSessionBean().setListShowNewWebForms(true);
        }

        return list;
    }
    
    /**
     *  List of confirmed web forms in the sidebar.
     */
    protected List confirmedWebFormsSidebar;

    /**
     * Get the value of confirmedWebFormsSidebar
     *
     * @return the value of confirmedWebFormsSidebar
     */
    public List getConfirmedWebFormsSidebar() {
         
        WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);
        List list =  webFormLogic.listWithLimit(0,
                    getSessionBean().getTenant().getNumberWebFormsSidebar(), null, getSessionBean().getUser());

        if (list.size() < 1) {
            getSessionBean().setListShowConfirmedWebForms(false);
        }  else {
            getSessionBean().setListShowConfirmedWebForms(true);
        }

        return list;
    }
}
