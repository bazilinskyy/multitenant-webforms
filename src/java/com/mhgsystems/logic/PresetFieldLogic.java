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
import com.mhgsystems.db.dao.PresetFieldDao;
import com.mhgsystems.model.PresetField;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class PresetFieldLogic implements GenericLogic<PresetField> {

    private PresetFieldDao presetFieldDao = new PresetFieldDao();

    /**
     *
     * @param id
     * @param tenantId
     * @param user
     * @return
     */
    public PresetField get(int id, int tenantId, User user) {
        try {

            return presetFieldDao.get(id, user);

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
    public List<PresetField> list(PresetField object, User user) {
        try {

            return presetFieldDao.list(object, user, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public List<PresetField> listAll(User user) {
        try {

            return presetFieldDao.listAll(user);

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
    public List<PresetField> list(PresetField object, User user, Map options) {
        try {

            return presetFieldDao.list(object, user, options, null);

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
    public List<PresetField> list(PresetField object, User user, ListOptions listOptions) {
        try {

            return presetFieldDao.list(object, user, null, listOptions);

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
    public List<PresetField> list(PresetField object, User user, Map options, ListOptions listOptions) {
        try {

            return presetFieldDao.list(object, user, options, listOptions);

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
    public LogicResponse insert(PresetField object, User user) {
        try {
            int id = presetFieldDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, PresetField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, PresetField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(PresetField object, User user) {
        try {
            int id = presetFieldDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, PresetField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, PresetField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(PresetField object, User user) {
        try {
            int id = presetFieldDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, PresetField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, PresetField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(PresetField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(PresetField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used to get max value of work_order_cd used since it is not auto incremented
     *
     * @return
     */
    public int getMaxPresetFieldId() {
        try {
            return presetFieldDao.getMaxPresetFieldId();
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
    public PresetField get(int id, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
