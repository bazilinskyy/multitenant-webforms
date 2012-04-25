/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.UserWebForm;
import com.mhgsystems.model.WebForm;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class UserWebFormRowMapper implements RowMapper<UserWebForm> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public UserWebForm mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserWebForm userWebForm = new UserWebForm();
        userWebForm.setUserId(rs.getInt("user_id"));
        userWebForm.setWebFormId(rs.getInt("webform_id"));

        return userWebForm;
    }

}
