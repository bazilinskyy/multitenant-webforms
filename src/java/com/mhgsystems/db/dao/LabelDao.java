/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.LabelRowMapper;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.Label;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class LabelDao extends BaseDao implements GenericDao<Label>{

    public Label get(int id, User user) throws DataAccessException {
        String sql = "SELECT * FROM label WHERE label_id=:label_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("label_id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new LabelRowMapper());        
    }
    

    public List<Label> listAll(User user) throws DataAccessException {
        String sql = "SELECT * FROM label";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        return jdbcTemplate.query(sql, parameters, new LabelRowMapper());
    }
    
    /**
     * 
     * @param webFormId
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Label> list(int webFormId, User user, Map options, ListOptions listOptions) throws DataAccessException {
//        String sql = "SELECT * FROM Label WHERE webform_id=:webform_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("webform_id", webFormId);
//
//        return jdbcTemplate.query(sql, parameters, new LabelRowMapper());
    throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insert(Label searchLabel, User user) throws DataAccessException {
        String sql = "INSERT INTO label ("
                + "en, "
                + "ru, "
                + "fi, "
                + "uk) "
                + "VALUES ("
                + ":en, "
                + ":ru, "
                + ":fi, "
                + ":uk)";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchLabel));
        
    }

    public int update(Label object, User user) throws DataAccessException {
        String sql = "UPDATE label SET "
                + "en = :en, "
                + "ru = :ru, "
                + "fi = :fi, "
                + "uk = :uk "
                + "WHERE label_id = :label_id";
        
        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    public int activate(Label object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(Label object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(Label object, User user) throws DataAccessException {
//        String sql = "DELETE FROM Label WHERE Label_id=:Label_id AND tenant_id=:tenant_id";
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("Label_id", object.getId());
//        parameters.addValue("tenant_id", object.getTenantId());
//
//        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of Label used since it is not auto incremented
     *
     * @return
     * @throws DataAccessException
     * @throws SQLException
     */
    public int getMaxLabelId() throws DataAccessException, SQLException {
        String sql = "SELECT MAX(label_id) FROM Label";
        Label object = new Label();
        return jdbcTemplate.queryForInt(sql, new SqlParameterSourceImpl(object));
    }

    public List<Label> list(Label object, User user, Map options, ListOptions listOptions) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
