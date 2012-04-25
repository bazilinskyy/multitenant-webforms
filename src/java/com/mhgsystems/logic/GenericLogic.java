/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.logic;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.commons.user.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <T> 
 * @author Esa Hiiva
 */
public interface GenericLogic<T> {

    /*
     * Get
     */
    /**
     * 
     * @param id
     * @param user
     * @return
     */
    public T get(int id, User user);

    /*
     * List
     */
    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public List<T> list(T object, User user);

    /**
     * 
     * @param object
     * @param user
     * @param options
     * @return
     */
    public List<T> list(T object, User user, Map options);

    /**
     * 
     * @param object
     * @param user
     * @param listOptions
     * @return
     */
    public List<T> list(T object, User user, ListOptions listOptions);

    /**
     * 
     * @param object
     * @param user
     * @param options
     * @param listOptions
     * @return
     */
    public List<T> list(T object, User user, Map options, ListOptions listOptions);

    /*
     * Insert
     */
    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(T object, User user);

    /*
     * Update
     */
    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(T object, User user);

    /*
     * Delete
     */
    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(T object, User user);

    /*
     * Activate
     */
    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(T object, User user);

    /*
     * Deactivate
     */
    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(T object, User user);
}
