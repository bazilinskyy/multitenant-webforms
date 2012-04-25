/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.user.User;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 *
 * @param <T>
 * @author Veli-Matti Plosila
 *
 * GenericDao interface should be used allways (when propriate) with generic objects which needs
 * basic database operations like get, list, update, insert, activate, deactivate.
 *
 * Implementing class structure should be like:
 *
 * - Static final NAMED_QUERY ints (if needed)
 * - Static final Map SORT_COLUMNS (if needed)
 * - Get methods (if implemented)
 * - List methods (if implemented)
 * - Insert method (if implemented)
 * - Update method (if implemented)
 * - Activate method (if implemented)
 * - Deactivate method (if implemented)
 * - FindByNamedQuery method (if implemented)
 * - Custom queries/updates/inserts/deletes (if implemented)
 * - Unsupportted/not implemented operations
 * 
 */
public interface GenericDao<T> {


    /*
     * Get
     */
    /**
     *
     * @param id
     * @param user
     * @return
     * @throws DataAccessException
     */
    public T get(int id, User user) throws DataAccessException;

    /*
     * List
     */
    /**
     *
     * @param object
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<T> list(T object, User user, Map options, ListOptions listOptions) throws DataAccessException;

    /*
     * Insert
     */
    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int insert(T object, User user) throws DataAccessException;

    /*
     * Update
     */
    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int update(T object, User user) throws DataAccessException;


    /*
     * Activate
     */
    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int activate(T object, User user) throws DataAccessException;

    /*
     * Deactivate
     */
    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int deactivate(T object, User user) throws DataAccessException;

    /*
     * Find by named query
     */
    /**
     *
     * @param object
     * @param namedQuery
     * @return
     * @throws DataAccessException
     */
    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException;

    /*
     * Delete
     */
    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int delete(T object, User user) throws DataAccessException;
}
