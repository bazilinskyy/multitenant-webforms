/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */

package com.mhgsystems.db.dao;

import com.mhgsystems.db.DataSourceLocator;
import com.mhgsystems.db.sql.SearchOption;
import java.util.ArrayList;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author Veli-Matti Plosila
 */
public abstract class BaseDao {

    /**
     *
     */
    public BaseDao() {
        jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceLocator.getInstance().getDataSource());
    }

    /**
     * Counstructor that init jdbcTemplate with given data source name
     *
     * @param dataSource name of the new datasource. Must be given without jdbc/ prefix
     */
    public BaseDao(String dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceLocator.getInstance().getDataSource(dataSource));
    }

    /**
     * Change data source from dao method
     *
     * @param dataSource name of the new data source. Must be given without jdbc/ prefix
     */
    protected void changeDatasource(String dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceLocator.getInstance().getDataSource(dataSource));
    }

    /**
     *
     */
    protected NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Get the value of jdbcTemplate
     *
     * @return the value of jdbcTemplate
     */
    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Set the value of jdbcTemplate
     *
     * @param jdbcTemplate new value of jdbcTemplate
     */
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

  
    /**
     *
     */
    protected String listOptionsSQL;

    /**
     *
     */
    protected ArrayList<SearchOption> searchOptions;

}
