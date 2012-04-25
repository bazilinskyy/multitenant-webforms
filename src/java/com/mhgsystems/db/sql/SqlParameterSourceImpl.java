/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.db.sql;

import com.mhgsystems.commons.user.User;
import com.mhgsystems.commons.util.Utils;
import java.lang.reflect.Field;
import javax.persistence.Column;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 *
 * @author Veli-Matti Plosila
 */
public class SqlParameterSourceImpl extends MapSqlParameterSource implements SqlParameterSource {

    /**
     * Map given object to the MapSqlParameterSource.
     * Read keys from object's @Column-annotations
     *
     * @param object to be mapped
     */
    public SqlParameterSourceImpl(Object object) {
        this.createMapSqlParameterSource(object);
    }

    /**
     * Map given object to the MapSqlParameterSource.
     * Read keys from object's @Column-annotations.
     * Add parameters upd_username and network_id from user.
     * 
     * @param object to be mapped
     * @param user current user
     */
    public SqlParameterSourceImpl(Object object, User user) {
        this.createMapSqlParameterSource(object);

        this.addValue("tenant_id", user.getTenantId());
    }

    /**
     * Map id field and value and user network_id 
     *
     * @param field id field
     * @param id primary key
     * @param user current user
     */
    public SqlParameterSourceImpl(String field, int id, User user) {
        this.addValue(field, id);

        this.addValue("tenant_id", user.getTenantId());
    }

    /**
     * Map values to id field, upd_username and network_id
     *
     * @param field id field
     * @param id primary key
     * @param user current user
     */
    public SqlParameterSourceImpl(Class c, int id, User user) {
        this.addValue(Utils.getIdField(c), id);

        this.addValue("tenant_id", user.getTenantId());
    }


    /**
     * Map given object to the MapSqlParameterSource.
     * Read keys from object's @Column-annotations
     * Should be used only from constructor
     *
     * @param object
     */
    private void createMapSqlParameterSource(Object object) {
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
        Field[] fieldArray = object.getClass().getDeclaredFields();

        for (Field field : fieldArray) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {

                Object propertyValue = wrapper.getPropertyValue(field.getName());

                if (propertyValue instanceof String[]) {
                    StringBuffer sb = new StringBuffer();
                    String[] stringArray = (String[]) propertyValue;
                    for (String value : stringArray) {
                        sb.append(value);
                        sb.append(",");
                    }
                    if (sb.length() > 0) {
                        sb = sb.deleteCharAt(sb.length() - 1);
                    }

                    this.addValue(column.name(), sb.toString());
                } else {
                    this.addValue(column.name(), propertyValue);
                }
            }
        }
    }
}
