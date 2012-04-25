/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.UserWebFormRowMapper;
import com.mhgsystems.db.sql.SQLFactory;
import com.mhgsystems.db.sql.SearchOption;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import com.mhgsystems.model.UserWebForm;
import com.mhgsystems.model.WebForm;
import java.util.ArrayList;
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
public class UserWebFormDao extends BaseDao implements GenericDao<UserWebForm> {

    public UserWebForm get(int id, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<UserWebForm> list(UserWebForm object, User user, Map options, ListOptions listOptions) throws DataAccessException {
        String sql = "SELECT * FROM user_webform WHERE user_id=:user_id ORDER BY webform_id ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", user.getAccountId());

        return jdbcTemplate.query(sql, parameters, new UserWebFormRowMapper());
    }
    
    public List<UserWebForm> listUserRights(UserWebForm object, User user, Map options, ListOptions listOptions) throws DataAccessException {
        String sql = "SELECT * FROM user_webform WHERE webform_id=:webform_id ORDER BY user_id ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webform_id", object.getWebFormId());

        return jdbcTemplate.query(sql, parameters, new UserWebFormRowMapper());
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
    public List<UserWebForm> list(UserWebForm object, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO need it
    }

    public int insert(UserWebForm userWebForm, Account account) throws DataAccessException {
        String sql = "INSERT INTO user_webform ("
                + "user_id, "
                + "webform_id) "
                + "VALUES ("
                + ":user_id, "
                + ":webform_id)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(userWebForm), keyHolder);
    }

    public int update(UserWebForm object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int activate(UserWebForm object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deactivate(UserWebForm object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(UserWebForm object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int deleteAllUsersForForm(UserWebForm object, User user) throws DataAccessException {
        String sql = "DELETE FROM user_webform WHERE webform_id = :webform_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webform_id", object.getWebFormId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param searchUserWebForm
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<UserWebForm> listWithLimit(int limitStart, int limitLength, UserWebForm searchUserWebForm, User user, Map options, ListOptions listOptions) throws DataAccessException {

        this.searchOptions = new ArrayList<SearchOption>();

        String sql = "SELECT * FROM user_webform WHERE user_id=:user_id "
                + SQLFactory.generateSQL(searchOptions, true)
                + " ORDER BY webform_id ASC LIMIT "
                + limitStart
                + ", "
                + limitLength;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", user.getAccountId());
        return jdbcTemplate.query(sql, parameters, new UserWebFormRowMapper());


    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param searchUserWebForm
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<UserWebForm> listWithLimit(int limitStart, int limitLength, UserWebForm searchUserWebForm, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
//        this.searchOptions = new ArrayList<SearchOption>();
//        searchOptions.add(new SearchOption("status", new Integer(searchWebForm.getStatus()), SearchOption.EQUAL));
//
//        String sql = "SELECT * FROM work_order WHERE company_id=:company_id "
//                + SQLFactory.generateSQL(searchOptions, true)
//                + " ORDER BY deadline LIMIT "
//                + limitStart
//                + ", "
//                + limitLength;
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("company_id", company.getId());
//        return jdbcTemplate.query(sql, parameters, new WebFormRowMapper());

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param sortBy
     * @param searchUserWebForm
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<UserWebForm> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, UserWebForm searchUserWebForm, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
//        this.searchOptions = new ArrayList<SearchOption>();
//        searchOptions.add(new SearchOption("status", new Integer(searchWebForm.getStatus()), SearchOption.EQUAL));
//
//        //Determine what to sort by
//        String sortByStr = "deadline";
//        switch (sortBy) {
//            case 1:  sortByStr = "deadline ASC";       break;
//            case 2:  sortByStr = "id ASC";      break;
//            case 3:  sortByStr = "work_sort_cd ASC";         break;
//            case 4:  sortByStr = "lat ASC";         break;
//            case 5:  sortByStr = "lon ASC";           break;
//            case 6:  sortByStr = "deadline DESC";       break;
//            case 7:  sortByStr = "id DESC";      break;
//            case 8:  sortByStr = "work_sort_cd DESC";         break;
//            case 9:  sortByStr = "lat DESC";         break;
//            case 10:  sortByStr = "lon DESC";           break;
//            default: sortByStr = "deadline ASC"; break;
//        }
//
//
//        String sql = "SELECT * FROM work_order WHERE company_id=:company_id "
//                + SQLFactory.generateSQL(searchOptions, true)
//                + "ORDER BY "
//                + sortByStr
//                + " LIMIT "
//                + limitStart
//                + ", "
//                + limitLength;
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("company_id", company.getId());
//        
//        return jdbcTemplate.query(sql, parameters, new WebFormRowMapper());

        throw new UnsupportedOperationException("Not supported yet.");

    }

    public int insert(UserWebForm object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
