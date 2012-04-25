/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.MotherChildWebField;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class MotherChildWebFieldRowMapper implements RowMapper<MotherChildWebField> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public MotherChildWebField mapRow(ResultSet rs, int rowNum) throws SQLException {

        MotherChildWebField MotherChildWebField = new MotherChildWebField();
        MotherChildWebField.setMotherId(rs.getInt("mother_id"));
        MotherChildWebField.setChildId(rs.getInt("child_id"));

        return MotherChildWebField;
    }

}
