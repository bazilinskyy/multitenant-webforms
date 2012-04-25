/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.commons.jsf;

import com.mhgsystems.commons.UITextHandler;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

public class EmailValidator implements Validator {

    private static final String MESSAGE_KEY = "javax.faces.validator.NOT_IN_RANGE";
    private static final String RESOURCES = "com/mhgsystems/ui/resources/JSFResources";
    private static final String ERROR_MESSAGE = ResourceBundle.getBundle(RESOURCES).getString(MESSAGE_KEY);

    /**
     *
     */
    protected UITextHandler uiTextHandler;

    /**
     *
     */
    public EmailValidator() {
        this.uiTextHandler = new UITextHandler();
    }

    @Override
    public void validate(FacesContext context,
            UIComponent toValidate,
            Object value) {

        try {

            String enteredEmail = (String) value;
            //Set the email pattern string
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

            //Match the given string with the pattern
            Matcher m = p.matcher(enteredEmail);

            //Check whether match is not found
            ValidationBean validationBean = new ValidationBean();

            if (!m.matches()) {
                throw new Exception("regex");
            } else if (!validationBean.checkFreeEmail(enteredEmail)) {
                //Check if email is availiable
                throw new Exception("taken");
            }

            ((UIInput) toValidate).setValid(true);
        } catch (Exception ex) {
            if (ex.getMessage().equals("taken")) {
                //Email is taken
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("emailIsTaken")));
                context.addMessage(toValidate.getClientId(context), message);
            } else if (ex.getMessage().equals("regex")) {
                //Regex validation failed
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("notValidEmail")));
                context.addMessage(toValidate.getClientId(context), message);
            }
        }
    }
}
