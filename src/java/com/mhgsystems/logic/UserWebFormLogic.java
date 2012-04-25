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
import com.mhgsystems.db.dao.UserWebFormDao;
import com.mhgsystems.model.*;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class UserWebFormLogic implements GenericLogic<UserWebForm> {

    private UserWebFormDao userWebFormDao = new UserWebFormDao();

    /**
     *
     */
    public UserWebFormLogic() {
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public UserWebForm get(int id, User user) {
        try {

            return userWebFormDao.get(id, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * List of all forms for a given user
     *
     *
     * @param object
     * @param user
     * @return
     */
    public List<UserWebForm> list(UserWebForm object, User user) {
        try {

            return userWebFormDao.list(object, user, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * List of all users for a given form
     *
     *
     * @param object
     * @param user
     * @return
     */
    public List<UserWebForm> listUserRights(UserWebForm object, User user) {
        try {

            return userWebFormDao.listUserRights(object, user, null, null);

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
    public List<UserWebForm> list(UserWebForm object, User user, Map options) {
        try {

            return userWebFormDao.list(object, user, options, null);

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
    public List<UserWebForm> list(UserWebForm object, User user, ListOptions listOptions) {
        try {

            return userWebFormDao.list(object, user, null, listOptions);

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
    public List<UserWebForm> list(UserWebForm object, Tenant tenant) {
        try {

            return userWebFormDao.list(object, tenant, null, null);

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
    public List<UserWebForm> list(UserWebForm object, User user, Map options, ListOptions listOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(UserWebForm object, User user) {
        try {
            int id = userWebFormDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(UserWebForm object, Account account) {
        try {
            int id = userWebFormDao.insert(object, account);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(UserWebForm object, User user) {
        try {
            int id = userWebFormDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(UserWebForm object, User user) {
        try {
            int id = userWebFormDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebForm.class);
        }
    }

    public LogicResponse deleteAllUsersForForm(UserWebForm object, User user) {
        try {
            int id = userWebFormDao.deleteAllUsersForForm(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(UserWebForm object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(UserWebForm object, User user) {
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
    public List<UserWebForm> listWithLimit(int limitStart, int limitLength, UserWebForm object, User user) {
        try {

            return userWebFormDao.listWithLimit(limitStart, limitLength, object, user, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param object
     * @param tenant
     * @return
     */
    public List<UserWebForm> listWithLimit(int limitStart, int limitLength, UserWebForm object, Tenant tenant) {
        try {

            return userWebFormDao.listWithLimit(limitStart, limitLength, object, tenant, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param limitStart
     * @param limitLength
     * @param sortBy
     * @param object
     * @param tenant
     * @return
     */
    public List<UserWebForm> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, UserWebForm object, Tenant tenant) {
        try {
            return userWebFormDao.listWithLimitSortBy(limitStart, limitLength, sortBy, object, tenant, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public LogicResponse insertForAllTenantUsers(WebForm webForm, Tenant tenant) {
        try {
            AccountLogic userLogic = new AccountLogic();
            List<Account> listUsers = null;

            listUsers = userLogic.list(new Account(), tenant);

            int id = 0;
            UserWebForm userWebForm = new UserWebForm();
            userWebForm.setWebFormId(webForm.getId());

            //Assigning priviliges for the web form for all users of the tenant.
            for (Account account : listUsers) {
                userWebForm.setUserId(account.getId());
                userWebFormDao.insert(userWebForm, account);
            }
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebField.class);
        }
    }
}
