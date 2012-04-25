/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.WebFormRowMapper;
import com.mhgsystems.db.sql.SQLFactory;
import com.mhgsystems.db.sql.SearchOption;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.Tenant;
import com.mhgsystems.model.WebForm;
import java.sql.SQLException;
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
public class WebFormDao extends BaseDao implements GenericDao<WebForm> {

    /**
     * Get work order with id = id from database.
     *
     * @param id
     * @param user
     * @return
     * @throws DataAccessException
     */
    public WebForm get(int id, User user) throws DataAccessException {
        String sql = "SELECT * FROM webform WHERE webform_id=:webform_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webform_id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new WebFormRowMapper());
    }

    /**
     * Get list of work orders.
     *
     * @param searchWebForm 
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<WebForm> list(WebForm searchWebForm, User user, Map options, ListOptions listOptions) throws DataAccessException {
//
//        this.searchOptions = new ArrayList<SearchOption>();
//        if(searchWebForm.getStatus() != -1)
//            searchOptions.add(new SearchOption("status", new Integer(searchWebForm.getStatus()), SearchOption.EQUAL));
//        if(searchWebForm.getConfirmationShown() != -1)
//            searchOptions.add(new SearchOption("confirmation_shown", new Integer(searchWebForm.getConfirmationShown()), SearchOption.EQUAL));
//
//        String sql = "SELECT * FROM work_order WHERE account_id=:account_id "
//                + SQLFactory.generateSQL(searchOptions, true)
//                + " ORDER BY deadline ASC";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("account_id", user.getAccountId());
//
//        return jdbcTemplate.query(sql, parameters, new WebFormRowMapper());
        
        throw new UnsupportedOperationException("Not supported yet.");

    }
    
        public List<WebForm> listAllMotherWebForms(WebForm searchWebForm, User user) throws DataAccessException {

        String sql = "SELECT * FROM webform WHERE can_be_mother=:can_be_mother ";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("can_be_mother", searchWebForm.getCanBeMother());

        return jdbcTemplate.query(sql, parameters, new WebFormRowMapper());
        

    }

    /**
     * Get list of work orders.
     * When user is not known.
     *
     * @param searchWebForm 
     * @param tenant 
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<WebForm> list(WebForm searchWebForm, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
//        this.searchOptions = new ArrayList<SearchOption>();
//        if(searchWebForm.getStatus() != -1)
//            searchOptions.add(new SearchOption("status", new Integer(searchWebForm.getStatus()), SearchOption.EQUAL));
//        if(searchWebForm.getWorkSortCd() != -1)
//            searchOptions.add(new SearchOption("work_sort_cd", new Integer(searchWebForm.getWorkSortCd()), SearchOption.EQUAL));
//
//        String sql = "SELECT * FROM work_order WHERE company_id=:company_id "
//                + SQLFactory.generateSQL(searchOptions, true)
//                + " ORDER BY deadline ASC";
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("company_id", company.getId());
//        return jdbcTemplate.query(sql, parameters, new WebFormRowMapper());
        
        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * Insert new WebForm to database
     *
     * @param webForm 
     * @param user
     * @return Generated insert id
     * @throws DataAccessException
     */
    public int insert(WebForm webForm, User user) throws DataAccessException {
        String sql = "INSERT INTO webform ("
                + "name, "
                + "can_be_mother, "
                + "captcha, "
                + "added) "
                + "VALUES ("
                + ":name, "
                + ":can_be_mother, "
                + ":captcha, "
                + "NOW())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new SqlParameterSourceImpl(webForm), keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * Update work order from searchWebForm
     *
     * @param searchWebForm
     * @param user
     * @returnstatus
     * @throws DataAccessException
     */
    public int update(WebForm searchWebForm, User user) throws DataAccessException {
        Logger.getInstance().log("captcha " + searchWebForm.getCaptcha());
        Logger.getInstance().log("can be mother  " + searchWebForm.getCanBeMother());
        
        String sql = "UPDATE webform SET "
                + "name = :name, "
                + "can_be_mother = :can_be_mother, "
                + "captcha = :captcha, "
                + "updated = NOW() "
                + "WHERE webform_id = :webform_id";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchWebForm));
    }

    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int activate(WebForm object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int deactivate(WebForm object, User user) throws DataAccessException {
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
     * Delete work order from database.
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int delete(WebForm object, User user) throws DataAccessException {
        String sql = "DELETE FROM webform WHERE webform_id = :webform_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("webform_id", object.getId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(object));
    }

    /**
     * 
     * @param limitStart
     * @param limitLength
     * @param searchWebForm
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<WebForm> listWithLimit(int limitStart, int limitLength, WebForm searchWebForm, User user, Map options, ListOptions listOptions) throws DataAccessException {
//
//        this.searchOptions = new ArrayList<SearchOption>();
//        searchOptions.add(new SearchOption("status", new Integer(searchWebForm.getStatus()), SearchOption.EQUAL));
//        if (searchWebForm.getConfirmationShown() != -1)
//            searchOptions.add(new SearchOption("confirmation_shown", new Integer(searchWebForm.getConfirmationShown()), SearchOption.EQUAL));
//
//        String sql = "SELECT * FROM work_order WHERE account_id=:account_id "
//                + SQLFactory.generateSQL(searchOptions, true)
//                + " ORDER BY deadline ASC LIMIT "
//                + limitStart
//                + ", "
//                + limitLength;
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("account_id", user.getAccountId());
//        return jdbcTemplate.query(sql, parameters, new WebFormRowMapper());
        
        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * 
     * @param limitStart
     * @param limitLength
     * @param searchWebForm
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<WebForm> listWithLimit(int limitStart, int limitLength, WebForm searchWebForm, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
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
     * @param searchWebForm
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<WebForm> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, WebForm searchWebForm, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
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

    /**
     * 
     * @return
     * @throws DataAccessException
     * @throws SQLException
     */
    public int getMaxWebFormId() throws DataAccessException, SQLException {
        String sql = "SELECT MAX(webform_id) FROM webform";
        WebForm object = new WebForm();
        return jdbcTemplate.queryForInt(sql, new SqlParameterSourceImpl(object));
        
    }
}
