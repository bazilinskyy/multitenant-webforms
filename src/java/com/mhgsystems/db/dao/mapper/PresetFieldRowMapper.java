/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.PresetField;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class PresetFieldRowMapper implements RowMapper<PresetField> {

    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    public PresetField mapRow(ResultSet rs, int i) throws SQLException {
        PresetField presetField = new PresetField();
        presetField.setId(rs.getInt("preset_field_id"));
        presetField.setName(rs.getString("name"));
        presetField.setType(rs.getString("type"));
        presetField.setPopupMessage(rs.getString("popup_message"));
        presetField.setDefaultValue(rs.getString("default_value"));
        presetField.setColour(rs.getString("colour"));
        presetField.setLabelId(rs.getInt("label_id"));
        presetField.setLabelFont(rs.getString("label_font"));
        presetField.setLabelFontSize(rs.getInt("label_font_size"));
        presetField.setInputWidth(rs.getInt("input_width"));
        presetField.setInputHeight(rs.getInt("input_height"));
        presetField.setInputSize(rs.getInt("input_size"));
        presetField.setTextAreaCol(rs.getInt("textarea_col"));
        presetField.setTextAreaRow(rs.getInt("textarea_row"));

        return presetField;
    }

}
