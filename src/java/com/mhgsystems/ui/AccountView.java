/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.logic.AccountLogic;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.model.Account;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.mhgsystems.commons.Logger;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class AccountView extends BaseView {

    private static final String MESSAGE_KEY = "javax.faces.validator.NOT_IN_RANGE";
    private static final String RESOURCES = "com/mhgsystems/ui/resources/JSFResources";
    private static final String ERROR_MESSAGE = ResourceBundle.getBundle(RESOURCES).getString(MESSAGE_KEY);

    /**
     *
     */
    @Override
    public void prerender() {
        getSessionBean().setSearchAccount(getSessionBean().getAccount()); // assigning default values
    }

    /**
     *
     * @return
     */
    public String addAction() {

        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
        LogicResponse logicResponse = null;

        // Determing if a new password was entered
        if (passwordOld.isEmpty() && password1.isEmpty() && password2.isEmpty()) {
            // Save old password
            logicResponse = accountLogic.updateNoMd5(getSessionBean().getSearchAccount(), null);
        } else {
            try {
                // Check if all passwords were entered to record a new value
                // Creating MD5 for passwordOLD
                MessageDigest m = MessageDigest.getInstance("MD5");
                String passwordOldMd5 = passwordOld;
                m.update(passwordOldMd5.getBytes(), 0, passwordOldMd5.length());
                passwordOldMd5 = new BigInteger(1, m.digest()).toString(16);

                if (passwordOldMd5.equals(getSessionBean().getAccount().getPassword()) && password1.equals(password2)
                        && !password1.isEmpty() && !password2.isEmpty()) {
                    //Everything is ok
                    getSessionBean().getSearchAccount().setPassword(password1);
                    logicResponse = accountLogic.update(getSessionBean().getSearchAccount(), null);
                } else {
                    //Creating messages for password fields
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidPassword")));
                    facesContext.addMessage("accountForm:password1", facesMessage);
                    facesContext.addMessage("accountForm:password2", facesMessage);
                    facesContext.addMessage("accountForm:passwordOld", facesMessage);
                    getSessionBean().getMessageHandler().createMessage("errorEditAccount");
                    return null;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getInstance().log(ex);
            }
        }

        if (logicResponse != null) {
            if (logicResponse.isSucceeded()) {
                //Creating MD5 for passwordOLD
                MessageDigest m = null;
                try {
                    m = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getInstance().log(ex);
                }
                if (!password1.isEmpty()) {
                    m.update(password1.getBytes(), 0, password1.length());
                    password1 = new BigInteger(1, m.digest()).toString(16);
                    getSessionBean().getSearchAccount().setPassword(password1);
                } else {
                    getSessionBean().getSearchAccount().setPassword(passwordOld);
                }

                if (getSessionBean().getSearchAccount().getPassword() == null || getSessionBean().getSearchAccount().getPassword().isEmpty())
                    getSessionBean().getSearchAccount().setPassword(getSessionBean().getAccount().getPassword());
                
                getSessionBean().setAccount(getSessionBean().getSearchAccount()); //Setting current account to a changed one
                getSessionBean().setConfirmation("editAccount");
                return "confirmation";
            } else {
                getSessionBean().getMessageHandler().createMessage("errorEditAccount");
                return null;
            }
        } else {
            return null;
        }
    }
    /**
     *
     */
    protected String passwordOld;

    /**
     * Get the value of passwordOld
     *
     * @return the value of passwordOld
     */
    public String getPasswordOld() {
        return passwordOld;
    }

    /**
     * Set the value of passwordOld
     *
     * @param passwordOld new value of passwordOld
     */
    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }
    /**
     *
     */
    protected String password1;

    /**
     * Get the value of password1
     *
     * @return the value of password1
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * Set the value of password1
     *
     * @param password1 new value of password1
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    /**
     *
     */
    protected String password2;

    /**
     * Get the value of password2
     *
     * @return the value of password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Set the value of password2
     *
     * @param password2 new value of password2
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    /**
     *
     */
    protected boolean deleteAvatar;

    /**
     * Get the value of deleteAvatar
     *
     * @return the value of deleteAvatar
     */
    public boolean isDeleteAvatar() {
        return deleteAvatar;
    }

    /**
     * Set the value of deleteAvatar
     *
     * @param deleteAvatar new value of deleteAvatar
     */
    public void setDeleteAvatar(boolean deleteAvatar) {
        this.deleteAvatar = deleteAvatar;
    }
}
