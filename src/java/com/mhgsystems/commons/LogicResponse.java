package com.mhgsystems.commons;

/**
 * Buisiness logic response wrapper
 *
 * @author Esa Hiiva
 */
public class LogicResponse {

    public static final boolean OK = true;
    public static final boolean FAILED = false;

    /** 
     * Do not use
     */
    private LogicResponse() {
    }

    /**
     * Response with succeeded information and Class from related Object
     *
     * @param succeeded
     * @param messageCode
     * @param objectClass
     */
    public LogicResponse(boolean succeeded, String messageCode, Class objectClass) {
        this.succeeded = succeeded;
        this.messageCode = messageCode;
        this.objectClass = objectClass;
    }

    /**
     * Response with succeeded information and information about related Object
     *
     * @param succeeded
     * @param messageCode
     * @param objectClass
     * @param objectId
     */
    public LogicResponse(boolean succeeded, String messageCode, Class objectClass, Integer objectId) {
        this.succeeded = succeeded;
        this.messageCode = messageCode;
        this.objectClass = objectClass;
        this.objectId = objectId;

    }

    private boolean succeeded;

    /**
     * Get the value of succeeded
     * <ul>
     * <li><b>true</b> if Logic operation succeeded</li>
     * <li><b>false</b> if Logic operation failed</li>
     * </ul>
     *
     * @return the value of succeeded
     */
    public boolean isSucceeded() {
        return succeeded;
    }

    private String messageCode;

    /**
     * Get the messageCode of Logic response
     * <br><br>
     * Use this code to get correct translation from MessageHandler.
     * <br>
     * Do NOT compare using this code! Use <code>isSucceeded()</code> method
     *
     * @return the value of messageCode
     */
    public String getMessageCode() {
        return messageCode;
    }

    private Class objectClass;

    /**
     * Get the value of objectClass
     *
     * @return the value of objectClass
     */
    public Class getObjectClass() {
        return objectClass;
    }

    private Integer objectId;

    /**
     * Get the value of objectId
     *
     * @return the value of objectId
     */
    public Integer getObjectId() {
        return objectId;
    }
}
