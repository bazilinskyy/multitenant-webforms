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
import com.mhgsystems.db.dao.UserWebFieldDao;
import com.mhgsystems.db.dao.UserWebFieldDao;
import com.mhgsystems.model.*;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class UserWebFieldLogic implements GenericLogic<UserWebField> {

    private UserWebFieldDao userWebFieldDao = new UserWebFieldDao();

    /**
     *
     */
    public UserWebFieldLogic() {
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public UserWebField get(int id, User user) {
        try {

            return userWebFieldDao.get(id, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public UserWebField checkUserRightsField(UserWebField object, User user) {
        try {

            return userWebFieldDao.checkUserRightsField(object, user);

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
    public List<UserWebField> list(UserWebField object, User user) {
        try {

            return userWebFieldDao.list(object, user, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public List<UserWebField> listUserRights(UserWebField object, User user) {
        try {

            return userWebFieldDao.listUserRights(object, user, null, null);

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
    public List<UserWebField> list(UserWebField object, User user, Map options) {
        try {

            return userWebFieldDao.list(object, user, options, null);

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
    public List<UserWebField> list(UserWebField object, User user, ListOptions listOptions) {
        try {

            return userWebFieldDao.list(object, user, null, listOptions);

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
    public List<UserWebField> list(UserWebField object, Tenant tenant) {
        try {

            return userWebFieldDao.list(object, tenant, null, null);

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
    public List<UserWebField> list(UserWebField object, User user, Map options, ListOptions listOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(UserWebField object, User user) {
        try {
            int id = userWebFieldDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebField.class);
        }
    }

    public LogicResponse insert(UserWebField object, Account account) {
        try {
            int id = userWebFieldDao.insert(object, account);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(UserWebField object, User user) {
        try {
            int id = userWebFieldDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(UserWebField object, User user) {
        try {
            int id = userWebFieldDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebField.class);
        }
    }
    
    public LogicResponse deleteAllUsersForForm(UserWebField object, User user) {
        try {
            int id = userWebFieldDao.deleteAllUsersForForm(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, UserWebField.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, UserWebField.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(UserWebField object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(UserWebField object, User user) {
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
    public List<UserWebField> listWithLimit(int limitStart, int limitLength, UserWebField object, User user) {
        try {

            return userWebFieldDao.listWithLimit(limitStart, limitLength, object, user, null, null);

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
    public List<UserWebField> listWithLimit(int limitStart, int limitLength, UserWebField object, Tenant tenant) {
        try {

            return userWebFieldDao.listWithLimit(limitStart, limitLength, object, tenant, null, null);

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
    public List<UserWebField> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, UserWebField object, Tenant tenant) {
        try {
            return userWebFieldDao.listWithLimitSortBy(limitStart, limitLength, sortBy, object, tenant, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    public LogicResponse insertForAllTenantUsers(WebField webField, Tenant tenant) {
        try {
            AccountLogic userLogic = new AccountLogic();
            List<Account> listUsers = null;

            listUsers = userLogic.list(new Account(), tenant);

            int id = 0;
            UserWebField userWebField = new UserWebField();
            userWebField.setWebFieldId(webField.getId());

            //Assigning priviliges for the web form for all users of the tenant.
            for (Account account : listUsers) {
                userWebField.setUserId(account.getId());
                userWebFieldDao.insert(userWebField, account);
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
