/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.WebForm;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class WebFormRowMapper implements RowMapper<WebForm> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public WebForm mapRow(ResultSet rs, int rowNum) throws SQLException {

        WebForm webForm = new WebForm();
        webForm.setId(rs.getInt("webform_id"));
        webForm.setName(rs.getString("name"));
        webForm.setCaptcha(rs.getInt("captcha"));
        webForm.setCanBeMother(rs.getInt("can_be_mother"));

        return webForm;
    }

}
