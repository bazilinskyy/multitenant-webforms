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
import com.mhgsystems.commons.util.Utils;
import com.mhgsystems.db.dao.AccountDao;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
public class AccountLogic extends BaseLogic implements GenericLogic<Account> {

    private AccountDao accountDao = new AccountDao();

    /**
     *
     */
    public AccountLogic() {
    }

    /**
     * Get Account from username and password
     *
     * @param username
     * @param password
     * @return Account if found. Returns null if not found.
     */
    public Account get(String username, String password) {

        try {
            return accountDao.get(username, Utils.getMD5(password));
            
        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * Get Account from username and password for administration
     * Needed only without XML integration!
     *
     * @param username
     * @param password
     * @return Account if found. Returns null if not found.
     */
    public Account getAdmin(String username, String password) {

        try {

            return accountDao.getAdmin(username, Utils.getMD5(password));

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     * Get Account from id
     * @param id
     * @return
     */
    public Account get(int id) {
        try {

            return accountDao.get(id, null);

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
    public Account get(int id, User user) {
        try {

            return accountDao.get(id, null);

        } catch (DataAccessException daex){
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
    public List<Account> list(Account object, User user) {
        try {

            return accountDao.list(object, user,null,null);

        } catch (DataAccessException daex){
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
    public List<Account> list(Account object, Tenant tenant) {
        try {

            return accountDao.list(object, tenant,null,null);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }

    /**
     *
     * @param object
     * @return
     */
    public List<Account> list(Account object) {
        try {

            return accountDao.list(object,null,null);

        } catch (DataAccessException daex){
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
    public List<Account> list(Account object, User user, Map options) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @param listOptions
     * @return
     */
    public List<Account> list(Account object, User user, ListOptions listOptions) {
        try {

            return accountDao.list(object, user,null,listOptions);

        } catch (DataAccessException daex){
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
    public List<Account> list(Account object, User user, Map options, ListOptions listOptions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse insert(Account object, User user) {
        try {
            int id = accountDao.insert(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Account.class, id);
            
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Account.class);
        }

    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse update(Account object, User user) {
        try {
            int id = accountDao.update(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Account.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Account.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse updateNoMd5(Account object, User user) {
        try {
            int id = accountDao.updateNoMd5(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Account.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Account.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse delete(Account object, User user) {
        try {
            int id = accountDao.delete(object, user);
            return new LogicResponse(LogicResponse.OK, MessageHandler.ADD_SUCCESS, Account.class, id);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new LogicResponse(LogicResponse.FAILED, MessageHandler.ADD_NOT_SUCCESS, Account.class);
        }
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse activate(Account object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param user
     * @return
     */
    public LogicResponse deactivate(Account object, User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected LogicResponse validate(Object object, User user, String method) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param account
     * @return
     */
    public User createUser(Account account) {

        User user = new User();
        user.setAccountId(account.getId());
        user.setTenantId(account.getTenantId());
        user.setUsername(account.getUsername());

        return user;
    }

    /**
     * 
     * @param limitStart
     * @param limitLength
     * @param object
     * @param tenant
     * @return
     */
    public List<Account> listWithLimit(int limitStart, int limitLength, Account object, Tenant tenant) {
        try {

            return accountDao.listWithLimit(limitStart, limitLength, object, tenant,null,null);

        } catch (DataAccessException daex){
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
    public List<Account> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, Account object, Tenant tenant) {
        try {

            return accountDao.listWithLimitSortBy(limitStart, limitLength, sortBy, object, tenant,null,null);

        } catch (DataAccessException daex){
            return null;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }
}
