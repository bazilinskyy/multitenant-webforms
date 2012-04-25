/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.ListValue;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ListValueRowMapper implements RowMapper<ListValue> {

    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    public ListValue mapRow(ResultSet rs, int i) throws SQLException {
        ListValue listValue = new ListValue();
        listValue.setId(rs.getInt("list_value_id"));
        listValue.setValue(rs.getString("value"));
        listValue.setText(rs.getString("text"));
        listValue.setPositionInList(rs.getInt("position_in_list"));
        listValue.setWebfieldId(rs.getInt("webfield_id"));
        listValue.setPresetFieldId(rs.getInt("preset_field_id"));
        listValue.setDefaultValue(rs.getInt("default_value"));
        listValue.setEn(rs.getString("en"));
        listValue.setRu(rs.getString("ru"));
        listValue.setFi(rs.getString("fi"));
        listValue.setUk(rs.getString("uk"));

        return listValue;
    }

}
