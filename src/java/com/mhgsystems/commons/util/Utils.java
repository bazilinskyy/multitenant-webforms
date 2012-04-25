/*
 * Utils.java
 *
 * Created on 6.11.2008, 14:57 Refactored from NumberValidator
 * Copyright 2009 MHG Systems Oy Ltd. All rights reserved.
 */
package com.mhgsystems.commons.util;

import com.mhgsystems.commons.Logger;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Veli-Matti Plosila
 */
public class Utils {

    /** Creates a new instance of Utils */
    public Utils() {
    }

    /** If str value is not numeric returns 0 in str */
    public static String validateStrValue(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception ex) {
            str = "0";
        } finally {
            return str;
        }
    }

    public static boolean exists(ArrayList list, String id) {

        int size = list.size();
        for (int i = 0; i < size; i++) {

            String existID = (String) list.get(i);
            if (id.equals(existID)) {
                return true;
            }

        }

        return false;
    }

    public static boolean exists(String[] list, String id) {
        boolean found = false;
        for (int i = 0; i < list.length; i++) {
            if (id.equals(list[i])) {
                found = true;
            }
        }

        return found;
    }

    public static String editCharacters(String message) {
        try {
            message = message.replaceAll("\\+", " ");
            message = message.replaceAll("%E4", "");
            message = message.replaceAll("%C4", "");
            message = message.replaceAll("%F6", "");
            message = message.replaceAll("%D6", "");

            return message;
        } catch (Exception ex) {

            message = message + ex.toString();
            return message;
        }
    }

    /** Validates string value char by char that they are integers */
    public static boolean validateIntValue(String value) {

        try {
            int length = value.length();

            for (int i = 0; i < length; i++) {
                String current = value.substring(i, i + 1);

                try {
                    Integer.valueOf(current);
                } catch (Exception ex) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean validateNumericDecimalDotComma(Object value) {

        String[] acceptedChars = new String[2];
        acceptedChars[0] = ".";
        acceptedChars[1] = ",";

        try {

            if (validate(acceptedChars, String.valueOf(value), false, false) == true) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            Logger.getInstance().log("Utils.validateNumericDecimalDotComma(Object value): " + ex);
            return false;
        }

    }

    public static boolean isNullOrEmpty(String value) {
        return isNullOrEmpty((Object) value);
    }

    public static boolean isNullOrEmpty(Object value) {
        if (value == null || "".equals(value)) {
            return true;
        } else {
            return false;
        }
    }

    public static String escapeCharacters(String attr) {

        attr = attr.replaceAll("&", "&amp;");
        attr = attr.replaceAll("<", "&lt;");
        attr = attr.replaceAll(">", "&gt;");
        attr = attr.replaceAll("\"", "&quot;");
        attr = attr.replaceAll("'", "&apos;");

        return attr;
    }

    public static String convertLinefeeds(String attr) {
        attr = attr.replaceAll("\n", "<br />");
        return attr;
    }

    public static String createTableRow(String[] keyParts, String value) {
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < keyParts.length; i++) {
            key.append(keyParts[i]);
        }

        return Utils.createTableRow(key.toString(), value);
    }

    public static String createTableRow(String[] keyParts, Float value) {
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < keyParts.length; i++) {
            key.append(keyParts[i]);
        }

        return Utils.createTableRow(key.toString(), String.valueOf(value));
    }

    public static String createTableRow(String key, Integer value) {
        return createTableRow(key, String.valueOf(value), "dataKey", "dataValue");
    }

    public static String createTableRow(String key, Double value) {
        return createTableRow(key, String.valueOf(value), "dataKey", "dataValue");
    }

    public static String createTableRow(String key, String value) {
        return createTableRow(key, value, "dataKey", "dataValue");
    }

    public static String createTableRow(String key, String value, String leftClass, String rightClass) {
        if ((key == null) || (value == null)) {
            return "";
        }

        StringBuffer sb = new StringBuffer();

        sb.append("<tr>");
        sb.append("<td class='" + leftClass + "'>");
        sb.append(Utils.convertLinefeeds(Utils.escapeCharacters(key)));
        sb.append("</td>");
        sb.append("<td class='" + rightClass + "'>");
        if (value.startsWith("<html>")) {
            sb.append(value);
        } else {
            sb.append(Utils.convertLinefeeds(Utils.escapeCharacters(value)));
        }
        sb.append("</td>");
        sb.append("</tr>");

        return sb.toString();
    }

    public static double roundToDecimals(double d, int decimals) {
        StringBuffer sb = new StringBuffer();
        sb.append("#");

        if (decimals > 0) {
            sb.append(".");

            for (int i = 0; i < decimals; i++) {
                sb.append("#");
            }
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat dForm = new DecimalFormat(sb.toString(), symbols);

        return Double.valueOf(dForm.format(d));
    }

    public static String getName(Class c) {
        Table t = (Table) c.getAnnotation(Table.class);
        if (t != null) {
            return t.name();
        } else {
            return null;
        }
    }

    public static String getIdField(Class c) {
        Field[] fields = c.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Id id = (Id) fields[i].getAnnotation(Id.class);

            if (id != null) {
                Column col = (Column) fields[i].getAnnotation(Column.class);
                return col.name();
            }
        }

        return null;
    }

    public static Integer getInteger(Object value) {
        try {
            try {
                Integer intValue = (Integer) value;
                return intValue;

            } catch (ClassCastException nex) {
                try {
                    Long longValue = (Long) value;
                    return Integer.valueOf(longValue.intValue());

                } catch (ClassCastException nex2) {
                    Logger.getInstance().log("Utils.getInteger(Object value) - 2", nex2);
                    return null;
                }
            }
        } catch (Exception ex) {
            Logger.getInstance().log("Utils.getInteger(Object value) - 1", ex);
            return null;
        }
    }

    public static boolean validate(String[] acceptedChars, String content, boolean multipass, boolean acceptLetter) {

        boolean pass = true;
        boolean oneValue = true;

        if ((content != null) || (content.length() > 0)) {

            for (int i = 0; i < content.length(); i++) {

                if (!Character.isDigit(content.charAt(i))) {
                    boolean validCharacter = false;

                    for (int y = 0; y < acceptedChars.length; y++) {
                        if (acceptedChars[y].equals(Character.toString(content.charAt(i)))) {

                            if (oneValue) {
                                validCharacter = true;
                                if (!multipass) {
                                    oneValue = false;
                                } // multipass == true ; Multiple validChars are allowed
                            } else {
                                pass = false;
                            } // else if oneValue == false ; Multiple validChars aren't allowed

                        } // if one of acceptedChars matches then true
                        if ((acceptLetter == true) && (Character.isLetter(content.charAt(i)))) {
                            validCharacter = true;
                        }
                    } //for y
                    if (validCharacter == false) {
                        pass = false;
                    } // if character isn't valid
                } // if non-numberic character
            } // for i
        } // if content != null
        return pass;
    }
//
//    public static Integer getInteger(Integer value) {
//        return value;
//    }
//
//    public static Integer getInteger(Long value) {
//
//        if (value == null) {
//            return null;
//        } else {
//            return Integer.valueOf(value.intValue());
//        }
//
//    }

    public static String toHexString(String value) {
        StringBuilder rep = new StringBuilder();
        try {

            for (int j = 0; j < value.length(); j++) {
                char c = value.charAt(j);
                int hexNumber = c & 0xFFFF;

                String temp = Integer.toHexString(hexNumber);
                // ex. 'a' -> temp = 61 have to be 0061
                if (temp.length() == 3) {
                    temp = "0" + temp;
                } else if (temp.length() == 2) {
                    temp = "00" + temp;
                } else if (temp.length() == 1) {
                    temp = "000" + temp;
                }
                rep.append(temp);

            } // for j

            value = rep.toString().toUpperCase();
        } catch (Exception ex) {
            Logger.getInstance().log("Exception: toHexString", ex);
        }
        return value;
    }

    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(string.getBytes("UTF-8"));
            byte[] digest = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                String hex = Integer.toHexString(0xFF & digest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception ex) {
            return null;
        }
    }
}
