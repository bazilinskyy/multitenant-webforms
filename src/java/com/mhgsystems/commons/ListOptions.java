/*
 * MHG Systems Oy Ltd
 */
package com.mhgsystems.commons;

import java.io.Serializable;

/**
 *
 * @author Veli-Matti Plosila
 */
public class ListOptions implements Serializable{

    private ListOptions(ListOptions listOptions) {
        this.viewId = listOptions.viewId;
        this.columnName = listOptions.columnName;
        this.descending = listOptions.descending;
        this.setCurrentPage(listOptions.currentPage);
    }

    public ListOptions() {
    }
    public static final int LIST_SIZE = 30;
    public static final int LIST_SIZE_EMBEDDED = 10;

    public int getListSize() {
        return LIST_SIZE;
    }

    public int getListSizeEmbedded() {
        return LIST_SIZE_EMBEDDED;
    }
    private String viewId;

    /**
     * Get the value of viewId
     *
     * @return the value of viewId
     */
    public String getViewId() {
        return viewId;
    }

    /**
     * Set the value of viewId
     *
     * @param viewId new value of viewId
     */
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }
    private String columnName;

    /**
     * Get the value of columnName
     *
     * @return the value of columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Set the value of columnName
     *
     * @param columnName new value of columnName
     */
    public void setColumnName(String columnName) {

        this.columnName = columnName;
    }
    private String oldColumnName;
    private boolean descending = false;

    /**
     * Get the value of descending
     *
     * @return the value of descending
     */
    public boolean isDescending() {
        return descending;
    }

    /**
     * Set the value of descending
     *
     * @param descending new value of descending
     */
    public void setDescending(boolean descending) {
        this.descending = descending;
    }
    private int firstIndex = 0;

    /**
     * Get the value of firstIndex
     *
     * @return the value of firstIndex
     */
    public int getFirstIndex() {
        return firstIndex;
    }

    /**
     * Set the value of firstIndex
     *
     * @param firstIndex new value of firstIndex
     */
    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }
    private int currentPage = 1;

    /**
     * Get the value of currentPage
     *
     * @return the value of currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Set the value of currentPage
     *
     * @param currentPage new value of currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.setFirstIndex((currentPage - 1) * LIST_SIZE);
        this.currentPage = currentPage;
    }

    public void setSortOptions(String viewId, String columnName) {
        try {
            if (!viewId.equals(this.viewId)) {

                //New viewId, clear old options
                this.clear();
            }

            this.viewId = viewId;

            this.oldColumnName = this.columnName;
            this.columnName = columnName;

            if (columnName.equals(oldColumnName)) {
                if (this.descending == true) {
                    this.descending = false;
                } else {
                    this.descending = true;
                }
            } else {
                //New column asc
                this.descending = false;
            }
        } catch (Exception ex) {
            Logger.getInstance().log("ListOptions.setSortOptions()", ex);
        }
    }

    public ListOptions getNewInstance(String viewId) {
        if (viewId == null || !viewId.equals(this.viewId)) {
            this.clear();
        }
        return new ListOptions(this);
    }

    private void clear() {
        this.viewId = null;
        this.columnName = null;
        this.oldColumnName = null;
        this.descending = true;
    }
}
