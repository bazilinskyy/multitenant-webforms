/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.WebFieldRowMapper;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.WebField;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class WebFieldDao extends BaseDao implements GenericDao<WebField> {

    public WebField get(int id, User user) throws DataAccessException {
        String sql = "SELECT * FROM webfield WHERE webfield_id=:webfield_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webfield_id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new WebFieldRowMapper());
    }

    public List<WebField> list(WebField searchWebField, User user, Map options, ListOptions listOptions) throws DataAccessException {
        String sql = "SELECT * FROM webfield WHERE webform_id=:webform_id ORDER BY position_in_webform ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webform_id", searchWebField.getWebformId());

        return jdbcTemplate.query(sql, parameters, new WebFieldRowMapper());
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
    public List<WebField> list(int webFormId, User user, Map options, ListOptions listOptions) throws DataAccessException {
        String sql = "SELECT * FROM webfield WHERE webform_id=:webform_id ORDER BY position_in_webform ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webform_id", webFormId);

        return jdbcTemplate.query(sql, parameters, new WebFieldRowMapper());
    }

    public int insert(WebField searchWebField, User user) throws DataAccessException {
        String sql = "INSERT INTO webfield ("
                + "type, "
                + "popup_message, "
                + "default_value, "
                + "default_value1, "
                + "default_value2, "
                + "default_value3, "
                + "default_value4, "
                + "default_value5, "
                + "required, "
                + "colour, "
                + "label_id, "
                + "label_font, "
                + "label_font_size, "
                + "input_width, "
                + "input_height, "
                + "input_size, "
                + "textarea_col, "
                + "textarea_row, "
                + "position_in_webform, "
                + "webform_id, "
                + "added, "
                + "tenant_id) "
                + "VALUES ("
                + ":type, "
                + ":popup_message, "
                + ":default_value, "
                + ":default_value1, "
                + ":default_value2, "
                + ":default_value3, "
                + ":default_value4, "
                + ":default_value5, "
                + ":required, "
                + ":colour, "
                + ":label_id, "
                + ":label_font, "
                + ":label_font_size, "
                + ":input_width, "
                + ":input_height, "
                + ":input_size, "
                + ":textarea_col, "
                + ":textarea_row, "
                + ":position_in_webform, "
                + ":webform_id, "
                + "NOW(), "
                + ":tenant_id)";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchWebField));

    }

    public int insertPreset(WebField searchWebField, User user) throws DataAccessException {
        String sql = "INSERT INTO webfield ("
                + "type, "
                + "popup_message, "
                + "default_value, "
                + "default_value1, "
                + "default_value2, "
                + "default_value3, "
                + "default_value4, "
                + "default_value5, "
                + "required, "
                + "colour, "
                + "label_id, "
                + "label_font, "
                + "label_font_size, "
                + "input_width, "
                + "input_height, "
                + "input_size, "
                + "textarea_col, "
                + "textarea_row, "
                + "preset_field_id, "
                + "position_in_webform, "
                + "webform_id, "
                + "added, "
                + "tenant_id) "
                + "VALUES ("
                + ":type, "
                + ":popup_message, "
                + ":default_value, "
                + ":default_value1, "
                + ":default_value2, "
                + ":default_value3, "
                + ":default_value4, "
                + ":default_value5, "
                + ":required, "
                + ":colour, "
                + ":label_id, "
                + ":label_font, "
                + ":label_font_size, "
                + ":input_width, "
                + ":input_height, "
                + ":input_size, "
                + ":textarea_col, "
                + ":textarea_row, "
                + ":preset_field_id, "
                + ":position_in_webform, "
                + ":webform_id, "
                + "NOW(), "
                + ":tenant_id)";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchWebField));

    }

    public int update(WebField object, User user) throws DataAccessException {
        String sql = "UPDATE webfield SET "
                + "type = :type, "
                + "popup_message = :popup_message, "
                + "default_value = :default_value, "
                + "default_value1 = :default_value1, "
                + "default_value2 = :default_value2, "
                + "default_value3 = :default_value3, "
                + "default_value4 = :default_value4, "
                + "default_value5 = :default_value5, "
                + "required = :required, "
                + "colour = :colour, "
                + "label_id = :label_id, "
                + "label_font = :label_font, "
                + "label_font_size = :label_font_size, "
                + "input_height = :input_height, "
                + "input_width = :input_width, "
                + "input_size = :input_size, "
                + "textarea_col = :textarea_col, "
                + "textarea_row = :textarea_row, "
                + "position_in_webform = :position_in_webform, "
                + "tenant_id = :tenant_id, "
                + "webform_id = :webform_id, "
                + "updated = NOW() "
                + "WHERE webfield_id = :webfield_id";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    public int updatePreset(WebField object, User user) throws DataAccessException {
        Logger.getInstance().log("preset: " + object.getPresetFieldId());

        String sql = "UPDATE webfield SET "
                + "type = :type, "
                + "popup_message = :popup_message, "
                + "default_value = :default_value, "
                + "default_value1 = :default_value1, "
                + "default_value2 = :default_value2, "
                + "default_value3 = :default_value3, "
                + "default_value4 = :default_value4, "
                + "default_value5 = :default_value5, "
                + "required = :required, "
                + "colour = :colour, "
                + "label_id = :label_id, "
                + "label_font = :label_font, "
                + "label_font_size = :label_font_size, "
                + "input_height = :input_height, "
                + "input_width = :input_width, "
                + "input_size = :input_size, "
                + "textarea_col = :textarea_col, "
                + "textarea_row = :textarea_row, "
                + "preset_field_id = :preset_field_id, "
                + "position_in_webform = :position_in_webform, "
                + "tenant_id = :tenant_id, "
                + "webform_id = :webform_id, "
                + "updated = NOW() "
                + "WHERE webfield_id = :webfield_id";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    public int activate(WebField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(WebField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(WebField object, User user) throws DataAccessException {
        String sql = "DELETE FROM webfield WHERE webfield_id=:webfield_id AND tenant_id=:tenant_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webfield_id", object.getId());
        parameters.addValue("tenant_id", object.getTenantId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    /**
     * Used to get max value of webfield used since it is not auto incremented
     *
     * @return
     * @throws DataAccessException
     * @throws SQLException
     */
    public int getMaxWebfieldId() throws DataAccessException, SQLException {
        String sql = "SELECT MAX(webfield_id) FROM webfield";
        WebField object = new WebField();
        return jdbcTemplate.queryForInt(sql, new SqlParameterSourceImpl(object));

    }
}
