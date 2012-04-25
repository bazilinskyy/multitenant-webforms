/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.PresetFieldRowMapper;
import com.mhgsystems.model.PresetField;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class PresetFieldDao extends BaseDao implements GenericDao<PresetField> {

    public PresetField get(int id, User user) throws DataAccessException {
//        String sql = "SELECT * FROM PresetField WHERE PresetField_id=:PresetField_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("PresetField_id", id);
//
//        return jdbcTemplate.queryForObject(sql, parameters, new PresetFieldRowMapper());

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PresetField> list(PresetField searchPresetField, User user, Map options, ListOptions listOptions) throws DataAccessException {
//        String sql = "SELECT * FROM PresetField WHERE tenant_id=:tenant_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("tenant_id", user.getTenantId());
//
//        return jdbcTemplate.query(sql, parameters, new PresetFieldRowMapper());

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
    public List<PresetField> list(int webFormId, User user, Map options, ListOptions listOptions) throws DataAccessException {
//        String sql = "SELECT * FROM PresetField WHERE webform_id=:webform_id";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("webform_id", webFormId);
//
//        return jdbcTemplate.query(sql, parameters, new PresetFieldRowMapper());

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PresetField> listAll(User user) throws DataAccessException {
        String sql = "SELECT * FROM preset_field";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, parameters, new PresetFieldRowMapper());
    }

    public int insert(PresetField searchPresetField, User user) throws DataAccessException {
//        String sql = "INSERT INTO PresetField ("
//                + "PresetField_id, "
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
//                + ":PresetField_id, "
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
//        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchPresetField));

        throw new UnsupportedOperationException("Not supported yet.");

    }

    public int update(PresetField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int activate(PresetField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(PresetField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(PresetField object, User user) throws DataAccessException {
//        String sql = "DELETE FROM PresetField WHERE PresetField_id=:PresetField_id AND tenant_id=:tenant_id";
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("PresetField_id", object.getId());
//        parameters.addValue("tenant_id", object.getTenantId());
//
//        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of PresetField used since it is not auto incremented
     *
     * @return
     * @throws DataAccessException
     * @throws SQLException
     */
    public int getMaxPresetFieldId() throws DataAccessException, SQLException {
//        String sql = "SELECT MAX(PresetField_id) FROM PresetField";
//        PresetField object = new PresetField();
//        return jdbcTemplate.queryForInt(sql, new SqlParameterSourceImpl(object));

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
