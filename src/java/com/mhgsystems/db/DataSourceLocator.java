/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db;

import com.mhgsystems.commons.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Veli-Matti Plosila
 */
public class DataSourceLocator {

    public DataSourceLocator() {
        Logger.getInstance().log("DataSourceLocator init");
        cache = Collections.synchronizedMap(new HashMap<String, DataSource>());
    }
    private static final String DEFAULT_DATASOURCE = "multitenantWebforms";
    private static final String JNDI_PREFIX = "java:comp/env/jdbc/";
    private static final DataSourceLocator INSTANCE = new DataSourceLocator();
    private Map<String, DataSource> cache;

    /**
     *
     * @return instance of DataSourceLocator
     */
    public static final DataSourceLocator getInstance() {
        return INSTANCE;
    }

    /**
     * Get DataSource object with default data source name
     * @return DataSource object
     */
    public DataSource getDataSource() {
        return this.getDataSource(DEFAULT_DATASOURCE);
    }

    /**
     * Get DataSource object with given name
     * @param name of the data source
     * @return DataSource object
     */
    public DataSource getDataSource(String name) {
        DataSource dataSource = null;
        try {
            //Try to find DataSource from cache
            dataSource = cache.get(name);

            //DataSource not found from cache. Let's try to add it to the cache
            if (dataSource == null) {
                InitialContext initialContext = new InitialContext();
                dataSource = (DataSource) initialContext.lookup(JNDI_PREFIX + name);

                if (dataSource == null) {
                    throw new Exception("DataSource not found with name " + name);
                }

                if (!cache.containsKey(name)) {
                    Logger.getInstance().log("DataSource " + name + " added to the cache");
                    cache.put(name, dataSource);
                }
            }

            return dataSource;
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return null;
        }
    }
}
