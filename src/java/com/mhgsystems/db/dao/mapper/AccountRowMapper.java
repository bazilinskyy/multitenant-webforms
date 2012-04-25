    /*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.commons.Logger;
import com.mhgsystems.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
public class AccountRowMapper implements RowMapper<Account> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        Account account = new Account();

        //Main parameters
        account.setId(rs.getInt("user_id"));
        account.setTenantId(rs.getInt("tenant_id"));
        account.setUsername(rs.getString("username"));

        //Additional parameters
        account.setPassword(rs.getString("password"));
        account.setEmail(rs.getString("email"));
        account.setFirstname(rs.getString("firstname"));
        account.setSurname(rs.getString("surname"));
        account.setVerified(rs.getInt("verified"));
        account.setAvatar(rs.getString("avatar"));
        account.setAdmin(rs.getInt("admin"));

        return account;

    }

}
