/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.UserWebField;
import com.mhgsystems.model.WebForm;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class UserWebFieldRowMapper implements RowMapper<UserWebField> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public UserWebField mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserWebField UserWebField = new UserWebField();
        UserWebField.setUserId(rs.getInt("user_id"));
        UserWebField.setWebFieldId(rs.getInt("webfield_id"));

        return UserWebField;
    }

}
