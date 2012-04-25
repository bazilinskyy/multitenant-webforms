/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.logic;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.LogicResponse;
import com.mhgsystems.commons.MessageHandler;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.TenantDao;
import com.mhgsystems.model.Tenant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class TenantLogic implements GenericLogic<Tenant> {
    
    private TenantDao tenantDao = new TenantDao();

    /**
     *
     */
    public TenantLogic() {
    }

    /**
     *
     * @param id
     * @return
     */
    public Tenant get(int id) {
        try {

            return tenantDao.get(id, null);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public Tenant get(int id, User user) {
        try {
            return tenantDao.get(id, user);
        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    // For getting a list of all companies for drop down menu
    /**
     *
     * @return
     */
    public List<Tenant> list() {
        try {

            return tenantDao.list(null, null, null, null);
            
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Tenant>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public List<Tenant> list(Tenant object, User user) {
        try {

            return tenantDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Tenant>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param options
     * @return
     */
    public List<Tenant> list(Tenant object, User user, Map options) {
        try {

            return tenantDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Tenant>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param listOptions
     * @return
     */
    public List<Tenant> list(Tenant object, User user, ListOptions listOptions) {
        try {

            return tenantDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Tenant>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param options
     * @param listOptions
     * @return
     */
    public List<Tenant> list(Tenant object, User user, Map options, ListOptions listOptions) {
        try {

            return tenantDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Tenant>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(Tenant object, User user) {
        try {
            int id = tenantDao.insert(object, user);

            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Tenant.class, id);
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Tenant.class);
        }    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(Tenant object, User user) {
        try {
            int id = tenantDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Tenant.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Tenant.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(Tenant object, User user) {
        try {
            int id = tenantDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Tenant.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Tenant.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(Tenant object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(Tenant object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
