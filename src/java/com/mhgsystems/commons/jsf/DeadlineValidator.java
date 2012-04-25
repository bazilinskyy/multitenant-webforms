/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.commons.jsf;

import com.mhgsystems.commons.UITextHandler;
import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class DeadlineValidator implements Validator {

    private static final String MESSAGE_KEY = "javax.faces.validator.NOT_IN_RANGE";
    private static final String RESOURCES = "com/mhgsystems/ui/resources/JSFResources";
    private static final String ERROR_MESSAGE = ResourceBundle.getBundle(RESOURCES).getString(MESSAGE_KEY);

    /**
     *
     */
    public DeadlineValidator() {
        this.uiTextHandler = new UITextHandler();
    }
    
    /**
     *
     */
    protected UITextHandler uiTextHandler;

    /**
     * TODO Possible tweak: check if the deadline is not too far in the future
     *
     * @param context
     * @param toValidate
     * @param value
     * @throws ValidatorException
     */
    public void validate(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        Date myDate = (Date) value;
        Date today = new Date();
        if (myDate.before(today)) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage(ERROR_MESSAGE.replaceAll("\\{0\\}", this.uiTextHandler.getText("dateIsPast")));
            context.addMessage(toValidate.getClientId(context), message);
        } else {
            ((UIInput) toValidate).setValid(true);
        }
    }
}
