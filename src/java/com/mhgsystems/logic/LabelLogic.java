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
import com.mhgsystems.db.dao.LabelDao;
import com.mhgsystems.model.Label;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pavlo Bazilinskyy
 */
public class LabelLogic implements GenericLogic<Label> {
    
    private LabelDao labelDao = new LabelDao();

    /**
     *
     */
    public LabelLogic() {
    }

    /**
     *
     * @param id
     * @return
     */
    public Label get(int id) {
        try {

            return labelDao.get(id, null);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }
    
    public int getMaxLabelId() {
        try {
            return labelDao.getMaxLabelId();
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
    public Label get(int id, User user) {
        try {
            return labelDao.get(id, user);
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
    public List<Label> list() {
        try {

            return labelDao.list(null, null, null, null);
            
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Label>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public List<Label> list(Label object, User user) {
        try {

            return labelDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Label>();
        }
    }
    
       public List<Label> listAll(User user) {
        try {

            return labelDao.listAll(user);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Label>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param options
     * @return
     */
    public List<Label> list(Label object, User user, Map options) {
        try {

            return labelDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Label>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @param listOptions
     * @return
     */
    public List<Label> list(Label object, User user, ListOptions listOptions) {
        try {

            return labelDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Label>();
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
    public List<Label> list(Label object, User user, Map options, ListOptions listOptions) {
        try {

            return labelDao.list(null, null, null, null);

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new ArrayList<Label>();
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(Label object, User user) {
        try {
            int id = labelDao.insert(object, user);

            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Label.class, id);
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Label.class);
        }    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(Label object, User user) {
        try {
            int id = labelDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Label.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Label.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(Label object, User user) {
        try {
            int id = labelDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Label.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Label.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(Label object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(Label object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
