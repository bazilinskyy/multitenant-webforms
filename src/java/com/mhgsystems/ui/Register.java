/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.ui;

import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.commons.jsf.ValidationBean;
import com.mhgsystems.logic.AccountLogic;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.logic.TenantLogic;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
public class Register extends BaseView {

    private static final String MESSAGE_KEY = "javax.faces.validator.NOT_IN_RANGE";
    private static final String RESOURCES = "com/mhgsystems/ui/resources/JSFResources";
    private static final String ERROR_MESSAGE = ResourceBundle.getBundle(RESOURCES).getString(MESSAGE_KEY);

    /**
     *
     */
    @Override
    public void prerender() {
        if (getSessionBean().isLoggedIn()) {
            getSessionBean().getMessageHandler().createMessage("pleaseLogout");
        } else if (!getSessionBean().getCurrentView().equals("register")) {
            getSessionBean().setAccount(new Account());
            getSessionBean().setCurrentView("register");
        }
    }
    private List tenantOptions;
    private String passwordConfirm;
    private String emailConfirm;

    /**
     *
     * @return
     */
    public String getEmailConfirm() {
        return emailConfirm;
    }

    /**
     *
     * @param emailConfirm
     */
    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    /**
     *
     * @return
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     *
     * @param passwordConfirm
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    /**
     *
     */
    protected String password;

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of companyOptions
     *
     * @return the value of companyOptions
     */
    public List<Tenant> getTenantOptions() {
        if (tenantOptions == null) {

            //Getting a list of companies from the SQL
            TenantLogic tenantLogic = (TenantLogic) LogicFactory.getNewGenericLogic(Tenant.class);
            List<Tenant> tenants = tenantLogic.list();

            //Populating a list with companies
            tenantOptions = new ArrayList();

            for (Tenant tenant : tenants) {
                tenantOptions.add(new SelectItem(tenant.getId(), tenant.getName()));
                
            }

        }
        return tenantOptions;
    }
    /**
     *
     */
    protected List languageOptionsSelectMenu;

    /**
     * Get the value of languageOptions
     *
     * NB: To be changed if new languages are added
     *
     * @return the value of languageOptions
     */
    public List getLanguageOptionsSelectMenu() {
        if (languageOptionsSelectMenu == null) {

            languageOptionsSelectMenu = new ArrayList();

            //Creating an array of select items
            for (int i = 0; i < getLanguageOptions().size(); i++) {
                languageOptionsSelectMenu.add(new SelectItem(getLanguageOptions().get(i),
                        this.uiTextHandler.getText((String) getLanguageOptions().get(i))));
            }

        }
        return languageOptionsSelectMenu;
    }
    /**
     *
     */
    protected List languageOptions;

    /**
     * Get the value of languageOptions
     *
     * @return the value of languageOptions
     */
    public List getLanguageOptions() {
        if (languageOptions == null) {
            languageOptions = new ArrayList();
            languageOptions.add("en");
            languageOptions.add("fi");
            languageOptions.add("ru");
            languageOptions.add("uk");
        }
        return languageOptions;
    }

    /**
     * Set the value of languageOptions
     *
     * @param languageOptions new value of languageOptions
     */
    public void setLanguageOptions(List languageOptions) {
        this.languageOptions = languageOptions;
    }
    /**
     *
     */
    protected List countryOptions;

    /**
     * Get the value of countryOptions
     *
     * NB: To be changed if new countries are added
     *
     * @return the value of countryOptions
     */
    public List getCountryOptions() {
        if (countryOptions == null) {
            countryOptions = new ArrayList();
            countryOptions.add(new SelectItem("FI", this.uiTextHandler.getText("finland")));
            countryOptions.add(new SelectItem("RU", this.uiTextHandler.getText("russia")));
            countryOptions.add(new SelectItem("UA", this.uiTextHandler.getText("ukraine")));
            countryOptions.add(new SelectItem("UK", this.uiTextHandler.getText("britain")));
        }
        return countryOptions;
    }

    /**
     * Call AccountLogic to insert new account
     *
     * @return String view to navigate
     */
    public String addAction() {
        //TODO upload new file for avatar image

        boolean errorsPresent = false; //true if errors are present

        if (!password.equals(passwordConfirm)) {
            //Checking if passwords enetered match

            //Creating messages for password fields
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidPassword")));
            facesContext.addMessage("registerForm:password1", facesMessage);
            facesContext.addMessage("registerForm:password2", facesMessage);

            errorsPresent = true;
        }

        if (errorsPresent) {
            getSessionBean().getMessageHandler().createMessage("errorRegistration");
            return null;
        }

        //If they are the same, set password for account object
        getSessionBean().getAccount().setPassword(password);

        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
        LogicResponse logicResponse = accountLogic.insert(getSessionBean().getAccount(), getUser());

        if (logicResponse.isSucceeded()) {
            getSessionBean().getMessageHandler().createMessage("successfulRegistrationMessage");
            return "login";
        } else {
            getSessionBean().getMessageHandler().createMessage("errorRegistration");
            return null;
        }
    }

    /**
     * Takes String username, checks it in the database and returns either free (true) or not free (false)
     *
     * @return
     */
    public String checkAvailiability() {
        ValidationBean validationBean = new ValidationBean();

        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String,String> request = ctx.getExternalContext().getRequestParameterMap();
        String username = request.get("registerForm:username"); //the id for the form : id of the text 

        if (username.isEmpty()) {
            //Username was not entered
            FacesMessage facesMessage = new FacesMessage(this.uiTextHandler.getText("usernameIsNotEntered"));
            ctx.addMessage("registerForm:availabilityButton", facesMessage);
            return null;
        } else {
            //Make checks to see if it's valid (taken from ValidationBean)
            try {

            //Set the pattern string
            //Also used in ValidationBean. Should be the same
            Pattern p = Pattern.compile("^([a-zA-Z])[a-zA-Z_-]*[\\w_-]*[\\S]$|^([a-zA-Z])[0-9_-]*[\\S]$|^[a-zA-Z]*[\\S]$");

            //Match the given string with the pattern
            Matcher m = p.matcher(username);

            //Check whether match is not found
            if (!m.matches()) {
                //RegEx validation failed
                throw new Exception("regex");
            } else if (username.length() < 5 || username.length() > 15) {
                //Check for length
                throw new Exception("length");
            }

            } catch (Exception ex) {
                if (ex.getMessage().equals("regex")) {
                    //All other exceptions
                    FacesMessage facesMessage = new FacesMessage(this.uiTextHandler.getText("notValidUserName"));
                    ctx.addMessage("registerForm:availabilityButton", facesMessage);
                    return null;
                } else if (ex.getMessage().equals("length")) {
                    //Length is not right
                    FacesMessage facesMessage = new FacesMessage(this.uiTextHandler.getText("notValidUserName"));
                    ctx.addMessage("registerForm:availabilityButton", facesMessage);
                    return null;
                }
            }
        }

        if (validationBean.checkFreeUsername(username)) {
            //Available
            FacesMessage facesMessage = new FacesMessage(this.uiTextHandler.getText("usernameIsNotTaken"));
            ctx.addMessage("registerForm:availabilityButton", facesMessage);
            return null;

        } else {
            //Not available
            FacesMessage facesMessage = new FacesMessage(this.uiTextHandler.getText("usernameIsTaken"));
            ctx.addMessage("registerForm:availabilityButton", facesMessage);
            return null;
        }
    }

    /**
     *
     */
    protected UIInput enteredUsername;

    /**
     * Get the value of enteredUsername
     *
     * @return the value of enteredUsername
     */
    public UIInput getEnteredUsername() {
        return enteredUsername;
    }

    /**
     * Set the value of enteredUsername
     *
     * @param enteredUsername new value of enteredUsername
     */
    public void setEnteredUsername(UIInput enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

}
