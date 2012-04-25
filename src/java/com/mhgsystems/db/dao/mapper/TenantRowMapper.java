/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhgsystems.db.dao.mapper;

import com.mhgsystems.model.Tenant;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class TenantRowMapper implements RowMapper<Tenant>  {

    /**
     *
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    public Tenant mapRow(ResultSet rs, int i) throws SQLException {
        Tenant tenant = new Tenant();

        //Main parameters
        tenant.setId(rs.getInt("tenant_id"));
        tenant.setName(rs.getString("name"));

        //Settings
        tenant.setNumberWebFormsPerPage(rs.getInt("number_webforms_per_page"));
        tenant.setNumberWebFormsSidebar(rs.getInt("number_webforms_sidebar"));
        tenant.setNumberWebFormsVerified(rs.getInt("number_webforms_verified"));
        tenant.setNumberWebFormsUnverified(rs.getInt("number_webforms_unverified"));
        tenant.setInstanceUrl(rs.getString("instance_url"));
        tenant.setApiKey(rs.getString("api_key"));
        tenant.setSecurityKey(rs.getString("security_key"));

        return tenant;
    }
}
