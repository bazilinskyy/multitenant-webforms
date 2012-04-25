/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.logic;

import com.mhgsystems.commons.LanguageHandler;
import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.commons.UITextHandler;
import com.mhgsystems.commons.user.User;

/**
 *
 * @author Esa Hiiva
 */
public abstract class BaseLogic {

    /**
     * 
     */
    public BaseLogic() {
        this.languageHandler = new LanguageHandler();
        this.uiTextHandler = new UITextHandler();
    }

    /**
     * Abstract method for validate.
     * <br>Must use protected type, so can't be used outside Logic class. Abstract can't be private.
     * <br>GenericObject can't be used as type, because BaseLogic can be used also other than GenericLogics.
     *
     * @param object
     * @param user
     * @param method
     *
     * @return LogicResponse
     */
    protected abstract LogicResponse validate(Object object, User user, String method);


    /**
     * 
     */
    protected UITextHandler uiTextHandler;
    /**
     * 
     */
    protected LanguageHandler languageHandler;

    /**
     * Get the value of languageHandler
     *
     * @return the value of languageHandler
     */
    public LanguageHandler getLanguageHandler() {
        return languageHandler;
    }

    /**
     * Set the value of languageHandler
     *
     * @param languageHandler new value of languageHandler
     */
    public void setLanguageHandler(LanguageHandler languageHandler) {
        this.languageHandler = languageHandler;
    }

    /**
     * Get the value of uiTextHandler
     *
     * @return the value of uiTextHandler
     */
    public UITextHandler getUiTextHandler() {
        return uiTextHandler;
    }

    /**
     * Set the value of uiTextHandler
     *
     * @param uiTextHandler new value of uiTextHandler
     */
    public void setUiTextHandler(UITextHandler uiTextHandler) {
        this.uiTextHandler = uiTextHandler;
    }
}
