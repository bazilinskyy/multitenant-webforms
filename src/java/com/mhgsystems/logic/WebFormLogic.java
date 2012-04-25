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
import com.mhgsystems.db.dao.WebFormDao;
import com.mhgsystems.model.Tenant;
import com.mhgsystems.model.UserWebForm;
import com.mhgsystems.model.WebForm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class WebFormLogic implements GenericLogic<WebForm> {

    private WebFormDao webFormDao = new WebFormDao();

    /**
     *
     */
    public WebFormLogic() {
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public WebForm get(int id, User user) {
        try {

            return webFormDao.get(id, user);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * Getting a list of web forms from user_webform table
     *
     * @param object
     * @param user
     * @return
     */
    public List<WebForm> list(WebForm object, User user) {
        try {
            UserWebFormLogic userWebFormLogic = (UserWebFormLogic) LogicFactory.getNewGenericLogic(UserWebForm.class);
            List<UserWebForm> list = userWebFormLogic.list(null, user);

            ArrayList<WebForm> listWebForms = new ArrayList();
            WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);

            // Web forms visible to the user.
            for (UserWebForm userWebForm : list) {
                listWebForms.add(webFormLogic.get(userWebForm.getWebFormId(), user));
            }

            return listWebForms;

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * Getting a list of web forms from user_webform table
     *
     * @param object
     * @param user
     * @return
     */
    public List<WebForm> listAllMotherWebForms(WebForm object, User user) {
        try {

            return webFormDao.listAllMotherWebForms(object, user);

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
    public List<WebForm> list(WebForm object, User user, Map options) {
        try {

            return webFormDao.list(object, user, options, null);

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
    public List<WebForm> list(WebForm object, User user, ListOptions listOptions) {
        try {

            return webFormDao.list(object, user, null, listOptions);

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
    public List<WebForm> list(WebForm object, Tenant tenant) {
        try {

            return webFormDao.list(object, tenant, null, null);

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
    public List<WebForm> list(WebForm object, User user, Map options, ListOptions listOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(WebForm object, User user) {
        try {
            int id = webFormDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(WebForm object, User user) {
        try {
            int id = webFormDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(WebForm object, User user) {
        try {
            int id = webFormDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, WebForm.class, id);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, WebForm.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(WebForm object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(WebForm object, User user) {
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
    public List<WebForm> listWithLimit(int limitStart, int limitLength, WebForm object, User user) {
        try {

            UserWebFormLogic userWebFormLogic = (UserWebFormLogic) LogicFactory.getNewGenericLogic(UserWebForm.class);
            List<UserWebForm> list = userWebFormLogic.listWithLimit(limitStart, limitLength, null, user);

            ArrayList<WebForm> listWebForms = new ArrayList();
            WebFormLogic webFormLogic = (WebFormLogic) LogicFactory.getNewGenericLogic(WebForm.class);

            for (UserWebForm userWebForm : list) {
                listWebForms.add(webFormLogic.get(userWebForm.getWebFormId(), user));
            }

            return listWebForms;

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
    public List<WebForm> listWithLimit(int limitStart, int limitLength, WebForm object, Tenant tenant) {
        try {

            return webFormDao.listWithLimit(limitStart, limitLength, object, tenant, null, null);

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
    public List<WebForm> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, WebForm object, Tenant tenant) {
        try {
            return webFormDao.listWithLimitSortBy(limitStart, limitLength, sortBy, object, tenant, null, null);

        } catch (DataAccessException daex) {
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public int getMaxWebFormId() {
        try {
            return webFormDao.getMaxWebFormId();
        } catch (Exception ex) {
            return -1;
        }
    }
}
