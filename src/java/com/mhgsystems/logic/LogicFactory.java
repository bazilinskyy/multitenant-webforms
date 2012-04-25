/*
 * Copyright 2011 MHG Systems Oy Ltd. All rights reserved.
 *
 */
package com.mhgsystems.logic;

import com.mhgsystems.commons.LanguageHandler;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.model.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map; 
import java.util.Map.Entry;
import javax.persistence.Table;

/**
 *
 * @author Esa Hiiva
 */
public class LogicFactory {

    /**
     * 
     */
    public static final Map<Class, Class> GENERICOBJECTS =
            Collections.unmodifiableMap(new HashMap<Class, Class>() {
        {
            put(Account.class, AccountLogic.class);
            put(WebForm.class, WebFormLogic.class);
            put(UserWebForm.class, UserWebFormLogic.class);
            put(Tenant.class, TenantLogic.class);
            put(WebField.class, WebFieldLogic.class);
            put(UserWebField.class, UserWebFieldLogic.class);
            put(PresetField.class, PresetFieldLogic.class);
            put(Label.class, LabelLogic.class);
            put(ListValue.class, ListValueLogic.class);
            put(ReceivedValue.class, ReceivedValueLogic.class);
            put(MotherChildWebField.class, MotherChildWebFieldLogic.class);
        }
    });

    /**
     * 
     * @param tableName
     * @return
     */
    public static GenericLogic getNewGenericLogic(String tableName) {
        Class genericClass = getGenericClassByTableName(tableName);
        return createGenericLogic(genericClass, null);
    }

    /**
     * 
     * @param genericObjectClass
     * @return
     */
    public static GenericLogic getNewGenericLogic(Class genericObjectClass) {
        return createGenericLogic(genericObjectClass, null);
    }

    /**
     * 
     * @param tableName
     * @param language
     * @return
     */
    public static GenericLogic getNewGenericLogic(String tableName, String language) {
        Class genericClass = getGenericClassByTableName(tableName);
        return createGenericLogic(genericClass, language);
    }

    /**
     * 
     * @param genericObjectClass
     * @param language
     * @return
     */
    public static GenericLogic getNewGenericLogic(Class genericObjectClass, String language) {
        return createGenericLogic(genericObjectClass, language);
    }

    private static GenericLogic createGenericLogic(Class genericObjectClass, String language) {
        try {
            if (genericObjectClass == null) {
                throw new Exception("Class is null for creating GenericLogic");
            }
            
            if (GENERICOBJECTS.containsKey(genericObjectClass)) {
                Class logicClass = GENERICOBJECTS.get(genericObjectClass);
                GenericLogic genericLogic = (GenericLogic) logicClass.newInstance();
                
                if (language != null) {
                    // Set language for Logic using casting to BaseLogic
                    ((BaseLogic) genericLogic).setLanguageHandler(new LanguageHandler(language));
                }

                return genericLogic;

            } else {
                throw new Exception("Invalid class for creating GenericLogic: " + genericObjectClass.getName());
            }

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * 
     * @param tableName
     * @return
     */
    public static Class getGenericClassByTableName(String tableName) {
        if ((tableName == null) || tableName.equals("")) {
            return null;
        }

        for (Entry<Class, Class> entry : GENERICOBJECTS.entrySet()) {
            Class genericClass = entry.getKey();

            Table t = (Table) genericClass.getAnnotation(Table.class);
            if ((t != null) && (t.name().equals(tableName))) {
                return genericClass;
            }
        }
        return null;
    }

    /**
     * 
     * @param tableName
     * @return
     */
    public static boolean hasGenericLogic(String tableName) {
        return hasGenericLogic(getGenericClassByTableName(tableName));
    }

    /**
     * 
     * @param c
     * @return
     */
    public static boolean hasGenericLogic(Class c) {
        if (c  == null) {
            return false;
        }

        if (GENERICOBJECTS.containsKey(c)) {
            return true;
        } else {
            return false;
        }
    }
}
