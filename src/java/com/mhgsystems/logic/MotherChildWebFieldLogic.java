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
import com.mhgsystems.db.dao.MotherChildWebFieldDao;
import com.mhgsystems.model.*;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class MotherChildWebFieldLogic implements GenericLogic<MotherChildWebField> {

    private MotherChildWebFieldDao motherChildWebFieldDao = new MotherChildWebFieldDao();

    /**
     *
     */
    public MotherChildWebFieldLogic() {
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public MotherChildWebField get(int id, User user) {
        try {

            return motherChildWebFieldDao.get(id, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public MotherChildWebField checkIfFieldIsChild(MotherChildWebField object, User user) {
        try {

            return motherChildWebFieldDao.checkIfFieldIsChild(object, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public MotherChildWebField checkIfFieldIsMother(MotherChildWebField object, User user) {
        try {

            return motherChildWebFieldDao.checkIfFieldIsMother(object, user);

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
    public List<MotherChildWebField> list(MotherChildWebField object, User user) {
        try {

            return motherChildWebFieldDao.list(object, user, null, null);

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
    public List<MotherChildWebField> list(MotherChildWebField object, User user, Map options) {
        try {

            return motherChildWebFieldDao.list(object, user, options, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public List<MotherChildWebField> listChildredToMother(MotherChildWebField object, User user) {
        try {

            return motherChildWebFieldDao.listChildredToMother(object, user);

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
    public List<MotherChildWebField> list(MotherChildWebField object, User user, ListOptions listOptions) {
        try {

            return motherChildWebFieldDao.list(object, user, null, listOptions);

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
     * @param tenant
     * @return
     */
    public List<MotherChildWebField> list(MotherChildWebField object, Tenant tenant) {
        try {

            return motherChildWebFieldDao.list(object, tenant, null, null);

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
    public List<MotherChildWebField> list(MotherChildWebField object, User user, Map options, ListOptions listOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(MotherChildWebField object, User user) {
        try {
            int id = motherChildWebFieldDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, MotherChildWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, MotherChildWebField.class);
        }
    }

    public LogicResponse insert(MotherChildWebField object, Account account) {
        try {
            int id = motherChildWebFieldDao.insert(object, account);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, MotherChildWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, MotherChildWebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(MotherChildWebField object, User user) {
        try {
            int id = motherChildWebFieldDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, MotherChildWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, MotherChildWebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(MotherChildWebField object, User user) {
        try {
            int id = motherChildWebFieldDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, MotherChildWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, MotherChildWebField.class);
        }
    }
    
    public LogicResponse deleteAllForChild(MotherChildWebField object, User user) {
        try {
            int id = motherChildWebFieldDao.deleteAllForChild(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, MotherChildWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, MotherChildWebField.class);
        }
    }
    
    public LogicResponse deleteAllForMother(MotherChildWebField object, User user) {
        try {
            int id = motherChildWebFieldDao.deleteAllForMother(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, MotherChildWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, MotherChildWebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(MotherChildWebField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(MotherChildWebField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param object
     * @param user
     * @return
     */
    public List<MotherChildWebField> listWithLimit(int limitStart, int limitLength, MotherChildWebField object, User user) {
        try {

            return motherChildWebFieldDao.listWithLimit(limitStart, limitLength, object, user, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }
}