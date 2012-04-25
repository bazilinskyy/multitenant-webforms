/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.dao;

import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.user.User;
import com.mhgsystems.db.dao.mapper.AccountRowMapper;
import com.mhgsystems.db.sql.SQLFactory;
import com.mhgsystems.db.sql.SearchOption;
import com.mhgsystems.db.sql.SqlParameterSourceImpl;
import com.mhgsystems.model.Account;
import com.mhgsystems.model.Tenant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Veli-Matti Plosila, Pavlo Bazilinskyy
 */
public class AccountDao extends BaseDao implements GenericDao<Account> {

    /**
     * Get Account from database by username and password
     *
     * @param username
     * @param password
     * @return Account if found. Throws DataAccessException if not found.
     */
    public Account get(String username, String password) {
        String sql = "SELECT * FROM account WHERE username=:username AND password=:password";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("username", username);
        parameters.addValue("password", password);
        Logger.getInstance().log(username);

        return jdbcTemplate.queryForObject(sql, parameters, new AccountRowMapper());
    }

    /**
     * Get Account from database by username and password for administration
     * Needed only without XML integration!
     *
     * @param username
     * @param password
     * @return Account if found. Throws DataAccessException if not found.
     */
    public Account getAdmin(String username, String password) {
        String sql = "SELECT * FROM account WHERE username=:username AND password=:password AND admin=1";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("username", username);
        parameters.addValue("password", password);

        return jdbcTemplate.queryForObject(sql, parameters, new AccountRowMapper());
    }

    /**
     * Get account from database by id.
     *
     * @param id
     * @param user
     * @return
     * @throws DataAccessException
     */
    public Account get(int id, User user) throws DataAccessException {
        String sql = "SELECT * FROM account WHERE user_id=:user_id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new AccountRowMapper());
    }

    /**
     * Get a list of accounts from database according to searchAccount.
     * When user is not known.
     *
     * @param searchAccount
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Account> list(Account searchAccount, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
        //Getting list of users
        this.searchOptions = new ArrayList<SearchOption>();
        if (searchAccount.getVerified() != -1)
            searchOptions.add(new SearchOption("verified", new Integer(searchAccount.getVerified()), SearchOption.EQUAL));

        String sql = "SELECT * FROM account WHERE tenant_id=:tenant_id "
                + SQLFactory.generateSQL(searchOptions, true)
                + " ORDER BY user_id ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tenant_id", tenant.getId());

        return jdbcTemplate.query(sql, parameters, new AccountRowMapper());
    }

    /**
     * Get list of accounts from database according to searchAccount.
     *
     * @param searchAccount
     * @param user
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Account> list(Account searchAccount, User user, Map options, ListOptions listOptions) throws DataAccessException {
        //Getting list of users
        this.searchOptions = new ArrayList<SearchOption>();
        if (searchAccount.getVerified() != -1)
            searchOptions.add(new SearchOption("verified", new Integer(searchAccount.getVerified()), SearchOption.EQUAL));

        String sql = "SELECT * FROM account WHERE tenant_id=:tenant_id "
                + SQLFactory.generateSQL(searchOptions, true)
                + " ORDER BY user_id ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tenant_id", user.getTenantId());

        return jdbcTemplate.query(sql, parameters, new AccountRowMapper());
    }

    /**
     * Get list of accounts from database according to searchAccount.
     *
     * All companies
     *
     * @param searchAccount
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Account> list(Account searchAccount, Map options, ListOptions listOptions) throws DataAccessException {
        //Getting list of users
        this.searchOptions = new ArrayList<SearchOption>();
        if (searchAccount.getUsername() != null)
            searchOptions.add(new SearchOption("username", searchAccount.getUsername(), SearchOption.EQUAL));
        if (searchAccount.getEmail() != null)
            searchOptions.add(new SearchOption("email", searchAccount.getEmail(), SearchOption.EQUAL));

        String sql = "SELECT * FROM account WHERE "
                + SQLFactory.generateSQL(searchOptions, false)
                + " ORDER BY user_id ASC";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        return jdbcTemplate.query(sql, parameters, new AccountRowMapper());
    }

    /**
     * Insert new Account to database
     *
     * @param account
     * @param user
     * @return Generated insert id
     * @throws DataAccessException
     */
    public int insert(Account account, User user) throws DataAccessException {
        String sql = "INSERT INTO account ("
                + "tenant_id, "
                + "added, "
                + "username, "
                + "password, "
                + "email, "
                + "firstname, "
                + "surname, "
                + "verified, "
                + "avatar, "
                + "admin) "
                + "VALUES (:tenant_id, "
                + "NOW(), "
                + ":username, "
                + "MD5(:password), "
                + ":email, "
                + ":firstname, "
                + ":surname, "
                + ":verified, "
                + ":avatar, "
                + ":admin)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new SqlParameterSourceImpl(account), keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * Update account from searchAccount.
     *
     * @param searchAccount
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int update(Account searchAccount, User user) throws DataAccessException {
        String sql = "UPDATE account SET "
                + "tenant_id = :tenant_id, "
                + "updated = NOW(), "
                + "username = :username, "
                + "password = MD5(:password), "
                + "email = :email, "
                + "firstname = :firstname, "
                + "surname = :surname, "
                + "avatar = :avatar, "
                + "verified = :verified, "
                + "admin = :admin "
                + "WHERE user_id = :user_id";

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchAccount));
    }

    /**
     * Update account from searchAccount.
     * It does not encrypt password to MD5.
     * Used by changing account settings when no new password is entered.
     *
     * @param searchAccount
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int updateNoMd5(Account searchAccount, User user) throws DataAccessException {
        String sql = null;
        if (searchAccount.getPassword() == null || searchAccount.getPassword().isEmpty()){
            sql = "UPDATE account SET "
                + "tenant_id = :tenant_id, "
                + "updated = NOW(), "
                + "username = :username, "
                + "email = :email, "
                + "firstname = :firstname, "
                + "surname = :surname, "
                + "avatar = :avatar, "
                + "verified = :verified, "
                + "admin = :admin "
                + "WHERE user_id = :user_id";
        } else {
            sql = "UPDATE account SET "
                + "tenant_id = :tenant_id, "
                + "updated = NOW(), "
                + "username = :username, "
                + "password = :password, "
                + "email = :email, "
                + "firstname = :firstname, "
                + "surname = :surname, "
                + "avatar = :avatar, "
                + "verified = :verified, "
                + "admin = :admin "
                + "WHERE user_id = :user_id";
        }
        
        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(searchAccount));
    }

    /**
     *
     * @param object
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int activate(Account object, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param account 
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int deactivate(Account account, User user) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param object
     * @param namedQuery
     * @return
     * @throws DataAccessException
     */
    public List findByNamedQuery(Object object, int namedQuery) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Delete account from database.
     *
     * @param account
     * @param user
     * @return
     * @throws DataAccessException
     */
    public int delete(Account account, User user) throws DataAccessException {
        String sql = "DELETE FROM account WHERE user_id = :user_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", account.getId());

        return jdbcTemplate.update(sql, new SqlParameterSourceImpl(account));
    }

    /**
     * 
     * @param limitStart
     * @param limitLength
     * @param searchAccount
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Account> listWithLimit(int limitStart, int limitLength, Account searchAccount, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
        //Getting list of users
        this.searchOptions = new ArrayList<SearchOption>();
        if (searchAccount.getVerified() != -1)
            searchOptions.add(new SearchOption("verified", new Integer(searchAccount.getVerified()), SearchOption.EQUAL));

        String sql = "SELECT * FROM account WHERE tenant_id=:tenant_id "
                + SQLFactory.generateSQL(searchOptions, true)
                + " ORDER BY user_id ASC LIMIT "
                + limitStart
                + ", "
                + limitLength;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tenant_id", tenant.getId());

        return jdbcTemplate.query(sql, parameters, new AccountRowMapper());
    }

    /**
     * 
     * @param limitStart
     * @param limitLength
     * @param sortBy
     * @param searchAccount
     * @param tenant
     * @param options
     * @param listOptions
     * @return
     * @throws DataAccessException
     */
    public List<Account> listWithLimitSortBy(int limitStart, int limitLength, int sortBy, Account searchAccount, Tenant tenant, Map options, ListOptions listOptions) throws DataAccessException {
        //Getting list of users
        this.searchOptions = new ArrayList<SearchOption>();
        if (searchAccount.getVerified() != -1)
            searchOptions.add(new SearchOption("verified", new Integer(searchAccount.getVerified()), SearchOption.EQUAL));

        //Determine what to sort by
        String sortByStr = "user_id";
        switch (sortBy) {
            case 1:  sortByStr = "user_id ASC";       break;
            case 2:  sortByStr = "username ASC";      break;
            case 3:  sortByStr = "firstname ASC";         break;
            case 4:  sortByStr = "surname ASC";         break;
            case 5:  sortByStr = "user_id DESC";       break;
            case 6:  sortByStr = "username DESC";      break;
            case 7:  sortByStr = "firstname DESC";         break;
            case 8:  sortByStr = "surname DESC";         break;
            default: sortByStr = "user_id ASC"; break;
        }

        String sql = "SELECT * FROM account WHERE tenant_id=:tenant_id "
                + SQLFactory.generateSQL(searchOptions, true)
                + "ORDER BY "
                + sortByStr
                + " LIMIT "
                + limitStart
                + ", "
                + limitLength;



        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tenant_id", tenant.getId());

        return jdbcTemplate.query(sql, parameters, new AccountRowMapper());
    }
}
