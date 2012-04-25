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
import com.mhgsystems.db.dao.ReceivedValueDao;
import com.mhgsystems.model.ReceivedValue;
import com.mhgsystems.model.ReceivedValue;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class ReceivedValueLogic implements GenericLogic<ReceivedValue> {

    private ReceivedValueDao receivedValueDao = new ReceivedValueDao();

    /**
     *
     * @param id
     * @param tenantId 
     * @param user
     * @return
     */
    public ReceivedValue get(int id, int tenantId, User user) {
        try {

            return receivedValueDao.get(id, user);

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
    public List<ReceivedValue> list(ReceivedValue object, User user) {
        try {
            
            return receivedValueDao.list(object, user, null, null);

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
    public List<ReceivedValue> list(ReceivedValue object, User user, Map options) {
        try {

            return receivedValueDao.list(object, user, options, null);

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
    public List<ReceivedValue> list(ReceivedValue object, User user, ListOptions listOptions) {
        try {

            return receivedValueDao.list(object, user, null, listOptions);

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
    public List<ReceivedValue> list(ReceivedValue object, User user, Map options, ListOptions listOptions) {
        try {

            return receivedValueDao.list(object, user, options, listOptions);

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
    public LogicResponse insert(ReceivedValue object, User user) {
        try {
            int id = receivedValueDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, ReceivedValue.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, ReceivedValue.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(ReceivedValue object, User user) {
        try {
            int id = receivedValueDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, ReceivedValue.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, ReceivedValue.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(ReceivedValue object, User user) {
        try {
            int id = receivedValueDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, ReceivedValue.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, ReceivedValue.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(ReceivedValue object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(ReceivedValue object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of work_order_cd used since it is not auto incremented
     *
     * @return
     */
    public int getMaxReceivedValueId() {
        try {
            return receivedValueDao.getMaxReceivedValueId();
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
    public ReceivedValue get(int id, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
