/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhgsystems.commons.jsf;

//import com.icesoft.faces.context.effects.JavascriptContext;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.UITextHandler;
import com.mhgsystems.logic.AccountLogic;
import com.mhgsystems.logic.LogicFactory;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import com.mhgsystems.ui.SessionBean;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ValidationBean {

    private static final String MESSAGE_KEY = "javax.faces.validator.NOT_IN_RANGE";
    private static final String REQUIRED_KEY = "javax.faces.component.UIInput.REQUIRED"; //Required value not entered
    private static final String RESOURCES = "com/mhgsystems/ui/resources/JSFResources";
    private static final String ERROR_MESSAGE = ResourceBundle.getBundle(RESOURCES).getString(MESSAGE_KEY);
    private static final String REQUIRED_MESSAGE = ResourceBundle.getBundle(RESOURCES).getString(REQUIRED_KEY); //Required value not entered
    /**
     *
     */
    protected UITextHandler uiTextHandler;

    /**
     *
     */
    public ValidationBean() {
        this.uiTextHandler = new UITextHandler();
        showReloadImage = true;
    }

    /**
     * When company is known
     *
     * @param username 
     * @param company
     * @return true if username is available, false if not available
     */
    public boolean checkFreeUsername(String username, Tenant tenant) {

        Account searchAccount = new Account();
        searchAccount.setUsername(username);

        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
        List listUsers = accountLogic.list(searchAccount, tenant);

        if (listUsers.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * When company is not known
     *
     * @param username
     * @return true if username is available, false if not available
     */
    public boolean checkFreeUsername(String username) {

        Account searchAccount = new Account();
        searchAccount.setUsername(username);

        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
        List listUsers = accountLogic.list(searchAccount);

        if (listUsers.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param email 
     * @param company
     * @return true if username is available, false if not available
     */
    public boolean checkFreeEmail(String email, Tenant tenant) {

        Account searchAccount = new Account();
        searchAccount.setEmail(email);

        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
        List listUsers = accountLogic.list(searchAccount, tenant);

        if (listUsers.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * All companies
     *
     * @param email
     * @return true if username is available, false if not available
     */
    public boolean checkFreeEmail(String email) {

        Account searchAccount = new Account();
        searchAccount.setEmail(email);

        AccountLogic accountLogic = (AccountLogic) LogicFactory.getNewGenericLogic(Account.class);
        List listUsers = accountLogic.list(searchAccount);

        if (listUsers.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Will verify if the text is is in a valid user name format like: is alphanumeric, starts with an alphabet and contains
     * no special characters other than underscore or dash.
     *
     * @param context
     * @param toValidate
     * @param value
     */
    public void usernameValidator(FacesContext context, UIComponent toValidate, Object value) {
        try {

            String enteredValue = (String) value;
            //Set the pattern string
            //Also used in Register bean. Should be the same
            Pattern p = Pattern.compile("^([a-zA-Z])[a-zA-Z_-]*[\\w_-]*[\\S]$|^([a-zA-Z])[0-9_-]*[\\S]$|^[a-zA-Z]*[\\S]$");

            //Match the given string with the pattern
            Matcher m = p.matcher(enteredValue);

            //Check whether match is not found

            if (enteredValue.isEmpty() || enteredValue == null) {
                //Required value not entered
                throw new Exception("empty");
            } else if (!m.matches()) {
                //RegEx validation failed
                throw new Exception("regex");
            } else if (enteredValue.length() < 5 || enteredValue.length() > 15) {
                //Check for length
                throw new Exception("length");
            } else if (!checkFreeUsername(enteredValue)) {
                //Check if username is availiable
                throw new Exception("taken");
            }

            ((UIInput) toValidate).setValid(true);
        } catch (Exception ex) {
            if (ex.getMessage().equals("empty")) {
                //Not entered
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage();
                context.addMessage(toValidate.getClientId(context), message);
            } else if (ex.getMessage().equals("taken")) {
                //Username is taken
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("usernameIsTaken")));
                context.addMessage(toValidate.getClientId(context), message);
            } else if (ex.getMessage().equals("regex")) {
                //All other exceptions
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidUserName")));
                context.addMessage(toValidate.getClientId(context), message);
            } else if (ex.getMessage().equals("length")) {
                //All other exceptions
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidUserName")));
                context.addMessage(toValidate.getClientId(context), message);
            }
            updateCaptcha();
        }
    }

    /**
     *
     * @param context
     * @param toValidate
     * @param value
     */
    public void passwordValidator(FacesContext context, UIComponent toValidate, Object value) {

        try {

            String enteredValue = (String) value;

            if (enteredValue.length() < 5 || enteredValue.length() > 15) {
                //Check for length
                throw new Exception();
            }

            ((UIInput) toValidate).setValid(true);
        } catch (Exception ex) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidPassword")));
            context.addMessage(toValidate.getClientId(context), message);
            updateCaptcha();
        }
    }

    /**
     *
     * @param context
     * @param toValidate
     * @param value
     */
    public void firstNameValidator(FacesContext context, UIComponent toValidate, Object value) {

        try {

            String enteredValue = (String) value;
            //Set the pattern string
            Pattern p = Pattern.compile("^[a-zA-Z]+(([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)*$");

            //Match the given string with the pattern
            Matcher m = p.matcher(enteredValue);

            //Check whether match is not found
            if (!m.matches()) {
                throw new Exception();
            }

            ((UIInput) toValidate).setValid(true);
        } catch (Exception ex) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidFirstName")));
            context.addMessage(toValidate.getClientId(context), message);
            updateCaptcha();
        }
    }

    /**
     * Uses same reg exp as first name check
     *
     * @param context
     * @param toValidate
     * @param value
     */
    public void surnameValidator(FacesContext context, UIComponent toValidate, Object value) {

        try {

            String enteredValue = (String) value;
            //Set the pattern string
            Pattern p = Pattern.compile("^[a-zA-Z]+(([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)*$");

            //Match the given string with the pattern
            Matcher m = p.matcher(enteredValue);

            //Check whether match is not found
            if (!m.matches()) {
                throw new Exception();
            }

            ((UIInput) toValidate).setValid(true);
        } catch (Exception ex) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidSurname")));
            context.addMessage(toValidate.getClientId(context), message);
            updateCaptcha();
        }
    }

    /**
     * Checks captcha
     *
     * @param context
     * @param toValidate
     * @param value
     */
    public void captchaValidator(FacesContext context, UIComponent toValidate, Object value) {
        /* Getting current SessionBean instance. Used for getting a global value
         * of boolean showCaptcha
         */
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
        showCaptcha = sessionBean.isShowCaptcha();

        try {

            String captchaEntered = (String) value;
            String captchaGenerated = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("registerForm:captchaHash");

            if (captchaEntered.isEmpty() || captchaEntered == null) {
                throw new Exception("empty");
            }

            if (captchaGenerated == null) //If not register, than get value from work order. Not used
            {
                captchaGenerated = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newWebFormForm:captchaHash");
            }

            if (!rpHash(captchaEntered).equals(captchaGenerated) && showCaptcha) {
                throw new Exception("range");
            }

            sessionBean.setShowCaptcha(false); //Hide captcha if entered correctly;
            ((UIInput) toValidate).setValid(true);
        } catch (Exception ex) {
            if (ex.getMessage().equals("range")) {
                //Not in range
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("wrongCaptcha")));
                context.addMessage(toValidate.getClientId(context), message);
                updateCaptcha();
            } else if (ex.getMessage().equals("empty")) {
                Logger.getInstance().log("Empty");
                //Not entered
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(REQUIRED_MESSAGE.replaceAll("", null));
                context.addMessage(toValidate.getClientId(context), message);
                updateCaptcha();
            }
        }
    }
    /**
     *
     */
    protected boolean showReloadImage = true;

    /**
     * Get the value of showReloadImage
     *
     * @return the value of showReloadImage
     */
    public boolean isShowReloadImage() {
        return showReloadImage;
    }

    /**
     * Set the value of showReloadImage
     *
     * @param showReloadImage new value of showReloadImage
     */
    public void setShowReloadImage(boolean showReloadImage) {
        this.showReloadImage = showReloadImage;
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
     * Compute the hash value to check for "real person" submission.
     *
     * @param  value  the entered value
     * @return  its hash value
     */
    private String rpHash(String value) {
        int hash = 5381;
        value = value.toUpperCase();
        for (int i = 0; i < value.length(); i++) {
            hash = ((hash << 5) + hash) + value.charAt(i);
        }
        return String.valueOf(hash);
    }

    private void updateCaptcha() {
//        JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "updateCaptchaFromBean();");
//        showReloadImage = false;
    }
}