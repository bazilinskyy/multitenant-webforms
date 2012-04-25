/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.WebField;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class WebFieldRowMapper implements RowMapper<WebField> {

    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    public WebField mapRow(ResultSet rs, int i) throws SQLException {
        WebField webField = new WebField();
        webField.setId(rs.getInt("webfield_id"));
        webField.setType(rs.getInt("type"));
        webField.setPopupMessage(rs.getString("popup_message"));
        webField.setDefaultValue(rs.getString("default_value"));
        webField.setDefaultValue1(rs.getString("default_value1"));
        webField.setDefaultValue2(rs.getString("default_value2"));
        webField.setDefaultValue3(rs.getString("default_value3"));
        webField.setDefaultValue4(rs.getString("default_value4"));
        webField.setDefaultValue5(rs.getString("default_value5"));
        webField.setColour(rs.getString("colour"));
        webField.setLabelId(rs.getInt("label_id"));
        webField.setLabelFont(rs.getString("label_font"));
        webField.setLabelFontSize(rs.getInt("label_font_size"));
        webField.setInputWidth(rs.getInt("input_width"));
        webField.setInputHeight(rs.getInt("input_height"));
        webField.setInputSize(rs.getInt("input_size"));
        webField.setPresetFieldId(rs.getInt("preset_field_id"));
        webField.setTenantId(rs.getInt("tenant_id"));
        webField.setWebformId(rs.getInt("webform_id"));
        webField.setPositionInWebform(rs.getInt("position_in_webform"));
        webField.setRequired(rs.getInt("required"));
        webField.setTextareaCol(rs.getInt("textarea_col"));
        webField.setTextareaRow(rs.getInt("textarea_row"));

        return webField;
    }

}
