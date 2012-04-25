/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhgsystems.commons;

/**
 *
 * @author Veli-Matti Plosila
 */
public class Config {

    public Config() {
        Logger.getInstance().log("Config init");
    }
    private static final Config INSTANCE = new Config();

    public static Config getInstance() {
        return INSTANCE;
    }

    private String filePath;

    /**
     * Get the value of filePath
     *
     * @return the value of filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the value of filePath
     *
     * @param filePath new value of filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
