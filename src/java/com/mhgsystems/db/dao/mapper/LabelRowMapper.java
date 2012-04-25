/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class LabelRowMapper implements RowMapper<Label> {

    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    public Label mapRow(ResultSet rs, int i) throws SQLException {
        Label label = new Label();
        label.setId(rs.getInt("label_id"));
        label.setEn(rs.getString("en"));
        label.setFi(rs.getString("fi"));
        label.setRu(rs.getString("ru"));
        label.setUk(rs.getString("uk"));

        return label;
    }

}
