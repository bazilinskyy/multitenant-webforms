/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.TenantRowMapper;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.mhgsystems.model.Tenant;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class TenantDao extends BaseDao implements GenericDao<Tenant> {

    /**
     * Get company from database.
     *
     * @param id
     * @param user
     * @return
     * @throws DataAccessException
     */
    public Tenant get(int id, User user) throws DataAccessException {

        String sql = "SELECT * FROM tenant WHERE tenant_id=:tenant_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tenant_id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new TenantRowMapper());
    }

    /**
     * Get list of companies from database. Used to get a list of all companies for drop down menu.
     *
     * @param tenant
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Tenant> list(Tenant tenant, User user, Map options, ListOptions listOptions) throws DataAccessException {
        String sql = "SELECT * FROM tenant";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        return jdbcTemplate.query(sql, parameters, new TenantRowMapper());
    }

    /**
     * Add new company to database. For future use.
     *
     * @param tenant
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int insert(Tenant tenant, User user) throws DataAccessException {
        String sql = "INSERT INTO tenant ("
                + "added, "
                + "name, "
                + "number_webforms_per_page, "
                + "number_webforms_verified, "
                + "number_webforms_unverified, "
                + "number_webforms_sidebar, "
                + "instance_url, "
                + "api_key, "
                + "security_key) "
                + "VALUES ("
                + "NOW(), "
                + ":name, "
                + ":number_webforms_per_page, "
                + ":number_webforms_verified, "
                + ":number_webforms_unverified, "
                + ":number_webforms_sidebar, "
                + ":instance_url, "
                + ":api_key, "
                + ":security_key)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new SqlParameterSourceImpl(tenant), keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * Update database from searchCompany.
     *
     * @param searchCompany
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int update(Tenant searchCompany, User user) throws DataAccessException {
        String sql = "UPDATE tenant SET "
                + "name = :name, "
                + "number_webforms_per_page = :number_webforms_per_page, "
                + "number_webforms_sidebar = :number_webforms_sidebar, "
                + "number_webforms_verified = :number_webforms_verified, "
                + "number_webforms_unverified = :number_webforms_unverified, "
                + "instance_url = :instance_url, "
                + "api_key = :api_key, "
                + "security_key = :security_key, "
                + "updated = NOW() "
                + "WHERE tenant_id = :tenant_id";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchCompany));
    }

    /**
     * Activation of tenants. May be used later
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int activate(Tenant object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int deactivate(Tenant object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param namedQuery
     * @return
     * @throws DataAccessException
     */
    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Delete company from database.
     *
     * @param tenant
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int delete(Tenant tenant, User user) throws DataAccessException {
        String sql = "DELETE FROM tenant WHERE tenant_id = :tenant_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tenant_id", tenant.getId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(tenant));
    }
}
