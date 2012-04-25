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
import com.mhgsystems.db.dao.WebFieldDao;
import com.mhgsystems.model.WebField;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class WebFieldLogic implements GenericLogic<WebField> {

    private WebFieldDao webFieldDao = new WebFieldDao();

    /**
     *
     * @param id
     * @param tenantId 
     * @param user
     * @return
     */
    public WebField get(int id, int tenantId, User user) {
        try {

            return webFieldDao.get(id, user);

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
    public List<WebField> list(WebField object, User user) {
        try {
            
            return webFieldDao.list(object, user, null, null);

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
    public List<WebField> list(WebField object, User user, Map options) {
        try {

            return webFieldDao.list(object, user, options, null);

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
    public List<WebField> list(WebField object, User user, ListOptions listOptions) {
        try {

            return webFieldDao.list(object, user, null, listOptions);

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
    public List<WebField> list(WebField object, User user, Map options, ListOptions listOptions) {
        try {

            return webFieldDao.list(object, user, options, listOptions);

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
    public LogicResponse insert(WebField object, User user) {
        try {
            int id = webFieldDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebField.class);
        }
    }
    
     /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insertPreset(WebField object, User user) {
        try {
            int id = webFieldDao.insertPreset(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(WebField object, User user) {
        try {
            int id = webFieldDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(WebField object, User user) {
        try {
            int id = webFieldDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(WebField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(WebField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of work_order_cd used since it is not auto incremented
     *
     * @return
     */
    public int getMaxWebFieldId() {
        try {
            return webFieldDao.getMaxWebfieldId();
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
    public WebField get(int id, User user) {
        try {

            return webFieldDao.get(id, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }
}
