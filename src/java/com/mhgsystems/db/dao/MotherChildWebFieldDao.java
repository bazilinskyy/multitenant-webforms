/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.MotherChildWebFieldRowMapper;
import com.mhgsystems.db.dao.mapper.UserWebFieldRowMapper;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import com.mhgsystems.model.MotherChildWebField;
import com.mhgsystems.model.UserWebField;
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
public class MotherChildWebFieldDao extends BaseDao implements GenericDao<MotherChildWebField> {

    public MotherChildWebField get(int id, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public MotherChildWebField checkIfFieldIsChild(MotherChildWebField object, User user) throws DataAccessException {
        String sql = "SELECT * FROM mother_child_webfield WHERE child_id=:child_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("child_id", object.getChildId());

        return jdbcTemplate.queryForObject(sql, parameters, new MotherChildWebFieldRowMapper());
    }

    public MotherChildWebField checkIfFieldIsMother(MotherChildWebField object, User user) throws DataAccessException {
        String sql = "SELECT * FROM mother_child_webfield WHERE mother_id=:mother_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("mother_id", object.getMotherId());

        return jdbcTemplate.queryForObject(sql, parameters, new MotherChildWebFieldRowMapper());
    }

    public List<MotherChildWebField> listChildredToMother(MotherChildWebField object, User user) throws DataAccessException {
        String sql = "SELECT * FROM mother_child_webfield WHERE mother_id=:mother_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("mother_id", object.getMotherId());

        return jdbcTemplate.query(sql, parameters, new MotherChildWebFieldRowMapper());

    }

    /**
     *
     * @param object
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<MotherChildWebField> list(MotherChildWebField object, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int insert(MotherChildWebField object, User user) throws DataAccessException {
        String sql = "INSERT INTO mother_child_webfield ("
                + "mother_id, "
                + "child_id) "
                + "VALUES ("
                + ":mother_id, "
                + ":child_id)";

        Logger.getInstance().log("MOTHER ID " + object.getMotherId());
        Logger.getInstance().log("CHILD ID " + object.getChildId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new SqlParameterSourceImpl(object), keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int insert(MotherChildWebField object, Account account) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int update(MotherChildWebField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int activate(MotherChildWebField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(MotherChildWebField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(MotherChildWebField object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int deleteAllForChild(MotherChildWebField object, User user) throws DataAccessException {
        String sql = "DELETE FROM mother_child_webfield WHERE child_id = :child_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("child_id", object.getChildId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }
    
    public int deleteAllForMother(MotherChildWebField object, User user) throws DataAccessException {
        String sql = "DELETE FROM mother_child_webfield WHERE mother_id = :mother_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("mother_id", object.getChildId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param searchMotherChildWebField
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<MotherChildWebField> listWithLimit(int limitStart, int limitLength, MotherChildWebField searchMotherChildWebField, User user, Map options, ListOptions listOptions) throws DataAccessException {

//        this.searchOptions = new ArrayList<SearchOption>();
//
//        String sql = "SELECT * FROM user_webform WHERE user_id=:user_id "
//                + SQLFactory.generateSQL(searchOptions, true)
//                + " ORDER BY webform_id ASC LIMIT "
//                + limitStart
//                + ", "
//                + limitLength;
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("user_id", user.getAccountId());
//        return jdbcTemplate.query(sql, parameters, new MotherChildWebFieldRowMapper());
//        

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<MotherChildWebField> list(MotherChildWebField object, User user, Map options, ListOptions listOptions) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
