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
import com.mhgsystems.db.dao.ListValueDao;
import com.mhgsystems.model.ListValue;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ListValueLogic implements GenericLogic<ListValue> {

    private ListValueDao listValueDao = new ListValueDao();

    /**
     *
     * @param id
     * @param tenantId 
     * @param user
     * @return
     */
    public ListValue get(int id, int tenantId, User user) {
        try {

            return listValueDao.get(id, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }
    
    public ListValue getDefault(ListValue object, User user) {
        try {

            return listValueDao.getDefault(object, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * 
     * @param object
     * @param user
     * @return
     */
    public List<ListValue> list(ListValue object, User user) {
        try {
            
            return listValueDao.list(object, user, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param options
     * @return
     */
    public List<ListValue> list(ListValue object, User user, Map options) {
        try {

            return listValueDao.list(object, user, options, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param listOptions
     * @return
     */
    public List<ListValue> list(ListValue object, User user, ListOptions listOptions) {
        try {

            return listValueDao.list(object, user, null, listOptions);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
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
    public List<ListValue> list(ListValue object, User user, Map options, ListOptions listOptions) {
        try {

            return listValueDao.list(object, user, options, listOptions);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(ListValue object, User user) {
        try {
            int id = listValueDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, ListValue.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, ListValue.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(ListValue object, User user) {
        try {
            int id = listValueDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, ListValue.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, ListValue.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(ListValue object, User user) {
        try {
            int id = listValueDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, ListValue.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, ListValue.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(ListValue object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(ListValue object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of work_order_cd used since it is not auto incremented
     *
     * @return
     */
    public int getMaxListValueId() {
        try {
            return listValueDao.getMaxListValueId();
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 
     * @param id
     * @param user
     * @return
     */
    public ListValue get(int id, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
