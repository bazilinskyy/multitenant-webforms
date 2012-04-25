/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.ReceivedValueRowMapper;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.ReceivedValue;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ReceivedValueDao extends BaseDao implements GenericDao<ReceivedValue>{

    public ReceivedValue get(int id, User user) throws DataAccessException {
//        String sql = "SELECT * FROM ReceivedValue WHERE ReceivedValue_id=:ReceivedValue_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("ReceivedValue_id", id);
//
//        return jdbcTemplate.queryForObject(sql, parameters, new ReceivedValueRowMapper());
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ReceivedValue> list(ReceivedValue searchReceivedValue, User user, Map options, ListOptions listOptions) throws DataAccessException {
//        String sql = "SELECT * FROM ReceivedValue WHERE tenant_id=:tenant_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("tenant_id", user.getTenantId());
//
//        return jdbcTemplate.query(sql, parameters, new ReceivedValueRowMapper());
        
        throw new UnsupportedOperationException("Not supported yet.");
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
    public List<ReceivedValue> list(int webFormId, User user, Map options, ListOptions listOptions) throws DataAccessException {
//        String sql = "SELECT * FROM ReceivedValue WHERE webform_id=:webform_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("webform_id", webFormId);
//
//        return jdbcTemplate.query(sql, parameters, new ReceivedValueRowMapper());
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insert(ReceivedValue searchReceivedValue, User user) throws DataAccessException {
//        String sql = "INSERT INTO ReceivedValue ("
//                + "ReceivedValue_id, "
//                + "type, "
//                + "popup_message, "
//                + "default_value, "
//                + "colour, "
//                + "label_text, "
//                + "label_font, "
//                + "label_font_size, "
//                + "input_width, "
//                + "input_size, "
//                + "preset_field_id, "
//                + "added, "
//                + "tenant_id) "
//                + "VALUES ("
//                + ":ReceivedValue_id, "
//                + ":type, "
//                + ":popup_message, "
//                + ":default_value, "
//                + ":colour, "
//                + ":label_text, "
//                + ":label_font, "
//                + ":label_font_size, "
//                + ":input_width, "
//                + ":input_size, "
//                + ":preset_field_id, "
//                + "NOW(), "
//                + ":tenant_id)";
//
//        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchReceivedValue));
        
        throw new UnsupportedOperationException("Not supported yet.");

    }

    public int update(ReceivedValue object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int activate(ReceivedValue object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(ReceivedValue object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(ReceivedValue object, User user) throws DataAccessException {
//        String sql = "DELETE FROM ReceivedValue WHERE ReceivedValue_id=:ReceivedValue_id AND tenant_id=:tenant_id";
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("ReceivedValue_id", object.getId());
//        parameters.addValue("tenant_id", object.getTenantId());
//
//        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of ReceivedValue used since it is not auto incremented
     *
     * @return
     * @throws DataAccessException
     * @throws SQLException
     */
    public int getMaxReceivedValueId() throws DataAccessException, SQLException {
//        String sql = "SELECT MAX(ReceivedValue_id) FROM ReceivedValue";
//        ReceivedValue object = new ReceivedValue();
//        return jdbcTemplate.queryForInt(sql, new SqlParameterSourceImpl(object));
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
