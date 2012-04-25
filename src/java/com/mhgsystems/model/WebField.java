/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.model;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.UITextHandler;
import com.mhgsystems.logic.LabelLogic;
import com.mhgsystems.logic.ListValueLogic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bazilinskyy Pavlo
 */
@Table(name = "webfield")
public class WebField implements Serializable {

    /**
     *
     */
    public WebField() {
        
    }
    @Id
    @Column(name = "webfield_id")
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     *
     */
    @Column(name = "type")
    protected int type = 1;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public int getType() {
        return type;
    }

    /**
     * Set the value of popupMessage
     *
     * @param type new value of type
     */
    public void setType(int type) {
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
    @Column(name = "default_value1")
    protected String defaultValue1;

    /**
     * Get the value of defaultValue1
     *
     * @return the value of defaultValue1
     */
    public String getDefaultValue1() {
        return defaultValue1;
    }

    /**
     * Set the value of defaultValue1
     *
     * @param defaultValue1 new value of defaultValue1
     */
    public void setDefaultValue1(String defaultValue1) {
        this.defaultValue1 = defaultValue1;
    }
    
    @Column(name = "default_value2")
    protected String defaultValue2;

    /**
     * Get the value of defaultValue2
     *
     * @return the value of defaultValue2
     */
    public String getDefaultValue2() {
        return defaultValue2;
    }

    /**
     * Set the value of defaultValue2
     *
     * @param defaultValue2 new value of defaultValue2
     */
    public void setDefaultValue2(String defaultValue2) {
        this.defaultValue2 = defaultValue2;
    }
    
    @Column(name = "default_value3")
    protected String defaultValue3;

    /**
     * Get the value of defaultValue3
     *
     * @return the value of defaultValue3
     */
    public String getDefaultValue3() {
        return defaultValue3;
    }

    /**
     * Set the value of defaultValue3
     *
     * @param defaultValue3 new value of defaultValue3
     */
    public void setDefaultValue3(String defaultValue3) {
        this.defaultValue3 = defaultValue3;
    }
    
    @Column(name = "default_value4")
    protected String defaultValue4;

    /**
     * Get the value of defaultValue4
     *
     * @return the value of defaultValue4
     */
    public String getDefaultValue4() {
        return defaultValue4;
    }

    /**
     * Set the value of defaultValue4
     *
     * @param defaultValue4 new value of defaultValue4
     */
    public void setDefaultValue4(String defaultValue4) {
        this.defaultValue4 = defaultValue4;
    }
    
    @Column(name = "default_value5")
    protected String defaultValue5;

    /**
     * Get the value of defaultValue5
     *
     * @return the value of defaultValue5
     */
    public String getDefaultValue5() {
        return defaultValue5;
    }

    /**
     * Set the value of defaultValue5
     *
     * @param defaultValue5 new value of defaultValue5
     */
    public void setDefaultValue5(String defaultValue5) {
        this.defaultValue5 = defaultValue5;
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
    /**
     *
     */
    @Column(name = "label_font")
    protected String labelFont = "'Cambria,''Times New Roman'',''Nimbus Roman No9 L'',''Freeserif'',Times,serif'";

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
    protected int labelFontSize = 12;

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
    @Column(name = "position_in_webform")
    protected int positionInWebform;

    /**
     * Get the value of positionInWebform
     *
     * @return the value of positionInWebform
     */
    public int getPositionInWebform() {
        return positionInWebform;
    }

    /**
     * Set the value of positionInWebform
     *
     * @param positionInWebform new value of positionInWebform
     */
    public void setPositionInWebform(int positionInWebform) {
        this.positionInWebform = positionInWebform;
    }
    /**
     *
     */
    @Column(name = "preset_field_id")
    protected int presetFieldId;

    /**
     * Get the value of presetFieldId
     *
     * @return the value of presetFieldId
     */
    public int getPresetFieldId() {
        return presetFieldId;
    }

    /**
     * Set the value of presetFieldId
     *
     * @param presetFieldId new value of presetFieldId
     */
    public void setPresetFieldId(int presetFieldId) {
        this.presetFieldId = presetFieldId;
    }
    /**
     *
     */
    @Column(name = "tenant_id")
    protected int tenantId;

    /**
     * Get the value of tenantId
     *
     * @return the value of tenantId
     */
    public int getTenantId() {
        return tenantId;
    }

    /**
     * Set the value of tenantId
     *
     * @param tenantId new value of tenantId
     */
    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
    /**
     *
     */
    @Column(name = "webform_id")
    protected int webformId;

    /**
     * Get the value of webformId
     *
     * @return the value of webformId
     */
    public int getWebformId() {
        return webformId;
    }

    /**
     * Set the value of webformId
     *
     * @param webformId new value of webformId
     */
    public void setWebformId(int webformId) {
        this.webformId = webformId;
    }
    @Column(name = "required")
    protected int required;

    /**
     * Get the value of required
     *
     * @return the value of required
     */
    public int getRequired() {
        return required;
    }

    /**
     * Set the value of required
     *
     * @param required new value of required
     */
    public void setRequired(int required) {
        this.required = required;
    }
    
    @Column(name = "label_id")
    protected int labelId;

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
    
    @Column(name = "textarea_col")
    protected int textareaCol;

    /**
     * Get the value of textareaCol
     *
     * @return the value of textareaCol
     */
    public int getTextareaCol() {
        return textareaCol;
    }

    /**
     * Set the value of textareaCol
     *
     * @param textareaCol new value of textareaCol
     */
    public void setTextareaCol(int textareaCol) {
        this.textareaCol = textareaCol;
    }

    @Column(name = "textarea_row")
    protected int textareaRow;

    /**
     * Get the value of textareaRow
     *
     * @return the value of textareaRow
     */
    public int getTextareaRow() {
        return textareaRow;
    }

    /**
     * Set the value of textareaRow
     *
     * @param textareaRow new value of textareaRow
     */
    public void setTextareaRow(int textareaRow) {
        this.textareaRow = textareaRow;
    }
    
    protected Label label;

    /**
     * Get the value of label
     *
     * @return the value of label
     */
    public Label getLabel() {
        if (label == null) {
            LabelLogic labelLogic = new LabelLogic();
            label = labelLogic.get(labelId);
            return label;
        } else {
            return label;
        }
    }

    /**
     * Set the value of label
     *
     * @param label new value of label
     */
    public void setLabel(Label label) {
        this.label = label;
    }
    
    /**
     * Set the value of label
     *
     * @param label new value of label
     */
    public void setLabel(String labelStr) {
        LabelLogic labelLogic = new LabelLogic();
        Label labelObj = new Label();
        labelObj.setId(labelId);
        labelObj.setEn(labelStr);
        labelObj.setFi(labelStr);
        labelObj.setRu(labelStr);
        labelObj.setUk(labelStr);
        
        this.label = labelObj;
    }
    
    protected String styleId;

    /**
     * Get the value of styleId
     *
     * @return the value of styleId
     */
    public String getStyleId() {
        return "field" + id;
    }

    /**
     * Set the value of styleId
     *
     * @param styleId new value of styleId
     */
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }
    
    protected String enterCharacters;

    /**
     * Get the value of enterCharacters
     *
     * @return the value of enterCharacters
     */
    public String getEnterCharacters() {
        UITextHandler uiTextHandler = new UITextHandler();
        return uiTextHandler.getText("youMayEnterUpTo") + " " + inputSize + " " + uiTextHandler.getText("characters");
    }

    /**
     * Set the value of enterCharacters
     *
     * @param enterCharacters new value of enterCharacters
     */
    public void setEnterCharacters(String enterCharacters) {
        this.enterCharacters = enterCharacters;
    }
    
    /**
     *
     */
    protected List listOptions;

    /**
     * Get  values for a list (type = 6)
     *
     * @return the value of listOptions
     */
    public List getListOptions() {
        if (listOptions == null) {
            listOptions = new ArrayList();
            UITextHandler uiTextHandler = new UITextHandler();
            
            ListValueLogic listValueLogic = new ListValueLogic();
            ListValue listValue = new ListValue();
            listValue.setWebfieldId(id);
            List<ListValue> list = listValueLogic.list(listValue, null);
                        
            for (ListValue valueList : list) {
//                listOptions.add(new SelectItem(valueList.value, uiTextHandler.getText(valueList.text)));
                listOptions.add(new SelectItem(valueList.value, valueList.text));
            }
        }
        return listOptions;
    }
    
    protected String defaultListValue;

    /**
     * Get the value of defaultListValue
     *
     * @return the value of defaultListValue
     */
    public String getDefaultListValue() {
        ListValueLogic listValueLogic = new ListValueLogic();
        ListValue listValue = new ListValue();
        listValue.setWebfieldId(id);
        listValue = listValueLogic.getDefault(listValue, null);
                       
        if (listValue == null)
            return " ";
        else
            return listValue.getValue();        
    }

    /**
     * Set the value of defaultListValue
     *
     * @param defaultListValue new value of defaultListValue
     */
    public void setDefaultListValue(String defaultListValue) {
        this.defaultListValue = defaultListValue;
    }
    
    protected boolean motherField;

    /**
     * Get the value of motherField
     *
     * @return the value of motherField
     */
    public boolean isMotherField() {
        //TODO return if mother field
        return motherField;
    }

    /**
     * Set the value of motherField
     *
     * @param motherField new value of motherField
     */
    public void setMotherField(boolean motherField) {
        this.motherField = motherField;
    }
    
    
    /**
     * If the field is taken from another field in a mother form.
     *
     */
    protected boolean child = false;

    /**
     * Get the value of child
     *
     * @return the value of child
     */
    public boolean isChild() {
        return child;
    }

    /**
     * Set the value of child
     *
     * @param child new value of child
     */
    public void setChild(boolean child) {
        this.child = child;
    }

    protected int idInMotherForm;

    /**
     * Get the value of idInMotherForm
     *
     * @return the value of idInMotherForm
     */
    public int getIdInMotherForm() {
        return idInMotherForm;
    }

    /**
     * Set the value of idInMotherForm
     *
     * @param idInMotherForm new value of idInMotherForm
     */
    public void setIdInMotherForm(int idInMotherForm) {
        this.idInMotherForm = idInMotherForm;
    }
    
    protected boolean mother;

    /**
     * Get the value of mother
     *
     * @return the value of mother
     */
    public boolean isMother() {
        return mother;
    }

    /**
     * Set the value of mother
     *
     * @param mother new value of mother
     */
    public void setMother(boolean mother) {
        this.mother = mother;
    }
    
    protected boolean toBeDeleted;

    /**
     * Get the value of toBeDeleted
     *
     * @return the value of toBeDeleted
     */
    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    /**
     * Set the value of toBeDeleted
     *
     * @param toBeDeleted new value of toBeDeleted
     */
    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }

}
