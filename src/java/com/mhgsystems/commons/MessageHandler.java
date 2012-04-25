/*
 * MessageHandler.java
 *
 * Created on 27. helmikuuta 2006, 14:06
 * Copyright 2009 MHG Systems Oy Ltd. All rights reserved.
 */
package com.mhgsystems.commons;

import java.io.Serializable;

/**
 *
 * @author Veli-Matti Plosila
 */
public class MessageHandler implements Serializable {

    public static final String EDIT_NOT_SUCCESS = "edit_not_success";
    public static final String EDIT_SUCCESS = "edit_success";
    public static final String ADD_NOT_SUCCESS = "add_not_success";
    public static final String ADD_SUCCESS = "add_success";
    public static final String DEACTIVATE_NOT_SUCCESS = "remove_not_success";
    public static final String DEACTIVATE_SUCCESS = "remove_success";
    public static final String REMOVE_NOT_SUCCESS = "remove_not_success";
    public static final String REMOVE_SUCCESS = "remove_success";
    public static final String ACTIVATE_NOT_SUCCESS = "activate_not_success";
    public static final String ACTIVATE_SUCCESS = "activate_success";
    public static final String MARK_NOT_SUCCESS = "mark_not_success";
    public static final String MARK_SUCCESS = "mark_success";
    //Duplicate login
    public static final String DUPLICATE_LOGIN = "duplicate_login";
    public static final String DUPLICATE_LOGIN_TEXT = "Duplicate login";
    private LanguageHandler languageHandler = new LanguageHandler();
    private UITextHandler uiTextHandler = new UITextHandler();

    public MessageHandler() {
    }
    private Message message;

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Generates new instance of Message inner class
     * @return new instance of Message
     */
    public Message newMessage() {
        return new Message();
    }

    /**
     * Creates and populates message instance from database with message code and language handler language
     * If message is not found with user language the method try get message with LanguageHanlder.DEFAULT
     * If message is not found with LanguageHanlder.DEFAULT method creates system error message.
     * Created message is in MessageHandler.message field
     * @param messageCode
     */
    public void createMessage(String messageCode) {
        this.createMessage(messageCode, "info", languageHandler.getLanguage());
    }

    /**
     * Creates and populates message instance from database with message code and paremeter language
     * If message is not found with user language the method try get message with LanguageHanlder.DEFAULT
     * If message is not found with LanguageHanlder.DEFAULT method creates system error message.
     * Created message is in MessageHandler.message field
     * @param messageCode
     * @param language
     */
    public void createMessage(String messageCode, String messageType, String language) {

        visible = true;

        this.message = new Message();
        message.setMessageCode(messageCode);
        message.setMessageText(uiTextHandler.getText(messageCode));
        message.setMessageType(messageType);

    }
    private boolean visible = false;

    /**
     * Get the value of visible
     *
     * @return the value of visible
     */
    public boolean getVisibility() {
        return visible;
    }

    public void toggleMessageVisibility() {
        isVisible();
    }

    public boolean isVisible() {
        if (visible) {
            visible = false;
            return true;
        }

        return visible;
    }

    /**
     * Set the value of visible
     *
     * @param visible new value of visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public class Message implements Serializable {

        private Message() {
        }
        /**
         * Holds value of property messageText.
         */
        private String messageText;

        /**
         * Getter for property messageText.
         * @return Value of property messageText.
         */
        public String getMessageText() {
            return this.messageText;
        }

        /**
         * Setter for property messageText.
         * @param messageText New value of property messageText.
         */
        public void setMessageText(String message_text) {

            this.messageText = message_text;
        }
        /**
         * Holds value of property messageCode.
         */
        private String messageCode;

        /**
         * Getter for property messageCode.
         * @return Value of property messageCode.
         */
        public String getMessageCode() {

            return this.messageCode;
        }

        /**
         * Setter for property messageCode.
         * @param messageCode New value of property messageCode.
         */
        public void setMessageCode(String messageCode) {

            this.messageCode = messageCode;
        }
        /**
         * Holds value of property messageType.
         */
        private String messageType;

        /**
         * Getter for property messageType.
         * @return Value of property messageType.
         */
        public String getMessageType() {

            return this.messageType;
        }

        /**
         * Setter for property messageType.
         * @param messageType New value of property messageType.
         */
        public void setMessageType(String message_type) {

            this.messageType = message_type;
        }
        /**
         * Holds value of property language.
         */
        private String language;

        /**
         * Getter for property language.
         * @return Value of property language.
         */
        public String getLanguage() {

            return this.language;
        }

        /**
         * Setter for property language.
         * @param language New value of property language.
         */
        public void setLanguage(String message_type) {

            this.language = message_type;
        }
    }
}
