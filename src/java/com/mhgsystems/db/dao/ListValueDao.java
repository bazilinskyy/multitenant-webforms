/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.ListValueRowMapper;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.ListValue;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ListValueDao extends BaseDao implements GenericDao<ListValue>{

    public ListValue get(int id, User user) throws DataAccessException {
        
        String sql = "SELECT * FROM list_value WHERE list_value_id=:list_value_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("list_value_id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new ListValueRowMapper());
        
    }
    
    public ListValue getDefault(ListValue object, User user) throws DataAccessException {
        
        String sql = "SELECT * FROM list_value WHERE webfield_id=:webfield_id AND default_value=1";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webfield_id", object.getWebfieldId());

        return jdbcTemplate.queryForObject(sql, parameters, new ListValueRowMapper());
        
    }

    public List<ListValue> list(ListValue searchListValue, User user, Map options, ListOptions listOptions) throws DataAccessException {
        String sql = "SELECT * FROM list_value WHERE webfield_id=:webfield_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webfield_id", searchListValue.getWebfieldId());

        return jdbcTemplate.query(sql, parameters, new ListValueRowMapper());
        
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
    public List<ListValue> list(int webFormId, User user, Map options, ListOptions listOptions) throws DataAccessException {
//        String sql = "SELECT * FROM ListValue WHERE webform_id=:webform_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("webform_id", webFormId);
//
//        return jdbcTemplate.query(sql, parameters, new ListValueRowMapper());
    throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insert(ListValue searchListValue, User user) throws DataAccessException {
        
        String sql = "INSERT INTO list_value ("
                + "value, "
                + "text, "
                + "position_in_list, "
                + "default_value, "
                + "en, "
                + "fi, "
                + "ru, "
                + "uk, "
                + "webfield_id, "
                + "added) "
                + "VALUES ("
                + ":value, "
                + ":text, "
                + ":position_in_list, "
                + ":default_value, "
                + ":en, "
                + ":fi, "
                + ":ru, "
                + ":uk, "
                + ":webfield_id, "
                + "NOW())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchListValue), keyHolder);

        return keyHolder.getKey().intValue();
        
    }
    
    public int insertPreset(ListValue searchListValue, User user) throws DataAccessException {
        
        String sql = "INSERT INTO list_value ("
                + "value, "
                + "text, "
                + "position_in_list, "
                + "default_value, "
                + "en, "
                + "fi, "
                + "ru, "
                + "uk, "
                + "webfield_id, "
                + "preset_field_id, "
                + "added) "
                + "VALUES ("
                + ":value, "
                + ":text, "
                + ":position_in_list, "
                + ":default_value, "
                + ":en, "
                + ":fi, "
                + ":ru, "
                + ":uk, "
                + ":webfield_id, "
                + ":preset_field_id, "
                + "NOW())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchListValue), keyHolder);

        return keyHolder.getKey().intValue();
        
    }

    public int update(ListValue object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int activate(ListValue object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(ListValue object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(ListValue object, User user) throws DataAccessException {
//        String sql = "DELETE FROM ListValue WHERE ListValue_id=:ListValue_id AND tenant_id=:tenant_id";
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("ListValue_id", object.getId());
//        parameters.addValue("tenant_id", object.getTenantId());
//
//        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of ListValue used since it is not auto incremented
     *
     * @return
     * @throws DataAccessException
     * @throws SQLException
     */
    public int getMaxListValueId() throws DataAccessException, SQLException {
//        String sql = "SELECT MAX(ListValue_id) FROM ListValue";
//        ListValue object = new ListValue();
//        return jdbcTemplate.queryForInt(sql, new SqlParameterSourceImpl(object));
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
