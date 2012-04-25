/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.model;

import com.mhgsystems.commons.Logger;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
@Table(name = "preset_field")
public class PresetField implements Serializable {

    /**
     *
     */
    public PresetField() {
    }
    @Id
    @Column(name = "preset_field_id")
    private int id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "name")
    protected String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *
     */
    @Column(name = "type")
    protected String type;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of popupMessage
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     *
     */
    @Column(name = "popup_message")
    protected String popupMessage;

    /**
     * Get the value of popupMessage
     *
     * @return the value of popupMessage
     */
    public String getPopupMessage() {
        return popupMessage;
    }

    /**
     * Set the value of popupMessage
     *
     * @param popupMessage new value of popupMessage
     */
    public void setPopupMessage(String popupMessage) {
        this.popupMessage = popupMessage;
    }
    /**
     *
     */
    @Column(name = "default_value")
    protected String defaultValue;

    /**
     * Get the value of defaultValue
     *
     * @return the value of defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set the value of defaultValue
     *
     * @param defaultValue new value of defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    /**
     *
     */
    @Column(name = "colour")
    protected String colour;

    /**
     * Get the value of colour
     *
     * @return the value of colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Set the value of colour
     *
     * @param colour new value of colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
    @Column(name = "id")
    private int labelId;

    /**
     * Get the value of labelId
     *
     * @return the value of labelId
     */
    public int getLabelId() {
        return labelId;
    }

    /**
     * Set the value of labelId
     *
     * @param labelId new value of labelId
     */
    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
    /**
     *
     */
    @Column(name = "label_font")
    protected String labelFont;

    /**
     * Get the value of labelFont
     *
     * @return the value of labelFont
     */
    public String getLabelFont() {
        return labelFont;
    }

    /**
     * Set the value of labelFont
     *
     * @param labelFont new value of labelFont
     */
    public void setLabelFont(String labelFont) {
        this.labelFont = labelFont;
    }
    /**
     *
     */
    @Column(name = "label_font_size")
    protected int labelFontSize;

    /**
     * Get the value of labelFontSize
     *
     * @return the value of labelFontSize
     */
    public int getLabelFontSize() {
        return labelFontSize;
    }

    /**
     * Set the value of labelFontSize
     *
     * @param labelFontSize new value of labelFontSize
     */
    public void setLabelFontSize(int labelFontSize) {
        this.labelFontSize = labelFontSize;
    }
    /**
     *
     */
    @Column(name = "input_width")
    protected int inputWidth;

    /**
     * Get the value of inputWidth
     *
     * @return the value of inputWidth
     */
    public int getInputWidth() {
        return inputWidth;
    }

    /**
     * Set the value of inputWidth
     *
     * @param inputWidth new value of inputWidth
     */
    public void setInputWidth(int inputWidth) {
        this.inputWidth = inputWidth;
    }
    /**
     *
     */
    @Column(name = "input_height")
    protected int inputHeight;

    /**
     * Get the value of inputHeight
     *
     * @return the value of inputHeight
     */
    public int getInputHeight() {
        return inputHeight;
    }

    /**
     * Set the value of inputHeight
     *
     * @param inputHeight new value of inputHeight
     */
    public void setInputHeight(int inputHeight) {
        this.inputHeight = inputHeight;
    }
    /**
     *
     */
    @Column(name = "input_size")
    protected int inputSize;

    /**
     * Get the value of inputSize
     *
     * @return the value of inputSize
     */
    public int getInputSize() {
        return inputSize;
    }

    /**
     * Set the value of inputSize
     *
     * @param inputSize new value of inputSize
     */
    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }
    
    @Column(name = "textarea_row")
    protected int textAreaRow;

    /**
     * Get the value of textAreaRow
     *
     * @return the value of textAreaRow
     */
    public int getTextAreaRow() {
        return textAreaRow;
    }

    /**
     * Set the value of textAreaRow
     *
     * @param textAreaRow new value of textAreaRow
     */
    public void setTextAreaRow(int textAreaRow) {
        this.textAreaRow = textAreaRow;
    }

    @Column(name = "textarea_col")
    protected int textAreaCol;

    /**
     * Get the value of textAreaCol
     *
     * @return the value of textAreaCol
     */
    public int getTextAreaCol() {
        return textAreaCol;
    }

    /**
     * Set the value of textAreaCol
     *
     * @param textAreaCol new value of textAreaCol
     */
    public void setTextAreaCol(int textAreaCol) {
        this.textAreaCol = textAreaCol;
    }
}
