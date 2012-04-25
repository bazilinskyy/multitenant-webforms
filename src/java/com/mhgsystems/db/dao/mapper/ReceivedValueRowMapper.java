/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.ReceivedValue;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ReceivedValueRowMapper implements RowMapper<ReceivedValue> {

    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    public ReceivedValue mapRow(ResultSet rs, int i) throws SQLException {
        ReceivedValue receivedValue = new ReceivedValue();
        receivedValue.setId(rs.getInt("received_value_id"));
        receivedValue.setValue(rs.getString("value"));
        receivedValue.setModel(rs.getString("model"));
        receivedValue.setWebfieldId(rs.getInt("webfield_id"));
        receivedValue.setUserId(rs.getInt("user_id"));

        return receivedValue;
    }

}
