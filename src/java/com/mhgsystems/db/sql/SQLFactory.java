/*
 * MHG Systems Oy Ltd
 * 
 */
package com.mhgsystems.db.sql;

import java.util.Map;
import com.mhgsystems.commons.ListOptions;
import com.mhgsystems.commons.Logger;
import com.mhgsystems.commons.util.TimeFormat;
import com.mhgsystems.commons.util.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Veli-Matti Plosila
 */
public class SQLFactory {

    private static final Character SQL_WILDCARD_PERCENT = '%';
    private static final Character SQL_WILDCARD_UNDERSCORE = '_';
    private static final Character SQL_ESCAPE_SLASH = '/';
    private static final Character BACKSLASH = '\\';
    private static final Character SINGLE_QUOTE = '\'';
    private static final Character DOUBLE_QUOTE = '"';
    private static final Character DOT = '.';

    public static String generateSQL(ListOptions listOptions, Map columns) {
        StringBuffer sb = new StringBuffer();

        if (listOptions == null || columns == null || listOptions.getColumnName() == null) {
            return null;
        }

        if (columns.get(listOptions.getColumnName()) != null) {
            sb.append("ORDER BY ");
            sb.append(columns.get(listOptions.getColumnName()));
            if (listOptions.isDescending()) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }

        return sb.toString();
    }

    public static String generateSQL(ArrayList<SearchOption> searchOptions) throws RuntimeException {

        return generateSQL(searchOptions, true);
    }

    public static String generateSQL(ArrayList<SearchOption> searchOptions, boolean startWithAnd) throws RuntimeException {
        StringBuffer sb = new StringBuffer();
        Iterator<SearchOption> iter = searchOptions.iterator();

        try {

            while (iter.hasNext()) {
                SearchOption option = iter.next();
                
                //Skip empty values except IS NULL operator
                if (Utils.isNullOrEmpty(option.getValue()) && option.getOperator() != SearchOption.IS_NULL) {
                    continue;
                }

                if ((option.getType() == SearchOption.NESTED_OR) || (option.getType() == SearchOption.NESTED_AND)) {
                    sb.append("AND ");
                    sb.append(generateNestedSQL(option));
                    sb.append(" ");
                } else {
                    sb.append("AND (");
                    sb.append(generateSQL(option));
                    sb.append(") ");
                }
            }

            if (!startWithAnd) {
                sb.delete(0, 4);// Remove "AND "
            }

            return sb.toString();

        } catch (Exception ex) {
            Logger.getInstance().log("SQLFactory.generateSQL(ArrayList<SearchOption> searchOptions)", ex);
            throw new RuntimeException("Couldn't generate SQL sentence");
        }
    }

    public static String generateSQL(SearchOption option) throws RuntimeException {

        StringBuffer sb = new StringBuffer();

        try {

            sb.append(option.getColumn());

            switch (option.getOperator()) {

                case SearchOption.NOT_LIKE:
                    sb.append(" NOT");
                case SearchOption.LIKE:
                    sb.append(" LIKE ");
                    switch (option.getType()) {
                        case SearchOption.STRING:
                            sb.append("'");
                            sb.append(formatSearchStringForSQL((String) option.getValue(), option.getOperator()));
                            sb.append("%'");
                            break;
                        case SearchOption.STRING_ARRAY:
                            String[] stringArray = (String[]) option.getValue();
                            for (String value : stringArray) {
                                sb.append("'");
                                sb.append(formatSearchStringForSQL((String) value, option.getOperator()));
                                sb.append("%'");
                                sb.append(" OR ");
                            }
                            if (sb.length() > 4) {
                                sb.delete(sb.length()-4, sb.length());
                            }
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                case SearchOption.EQUAL:
                case SearchOption.NOT_EQUAL:
                    if (option.getOperator() == SearchOption.EQUAL) {
                        sb.append(" = ");
                    } else if (option.getOperator() == SearchOption.NOT_EQUAL) {
                        sb.append(" != ");
                    }
                    switch (option.getType()) {
                        case SearchOption.INTEGER:
                            sb.append((Integer) option.getValue());
                            break;
                        case SearchOption.DATE:
                            sb.append("'");
                            sb.append(TimeFormat.getSqlDate((Date) option.getValue()));
                            sb.append("'");
                            break;
                        case SearchOption.BOOLEAN:
                            sb.append((Boolean) option.getValue());
                            break;
                        case SearchOption.STRING:
                            sb.append("'");
                            sb.append((String) option.getValue());
                            sb.append("'");
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                case SearchOption.NOT_BETWEEN:
                    sb.append(" NOT");
                case SearchOption.BETWEEN:
                    sb.append(" BETWEEN ");
                    switch (option.getType()) {
                        case SearchOption.DATE_ARRAY:
                            Date[] dateArray = (Date[]) option.getValue();
                            sb.append("'");
                            sb.append(TimeFormat.getSqlDate(dateArray[0]));
                            sb.append("' AND '");
                            sb.append(TimeFormat.getSqlDate(dateArray[1]));
                            sb.append("'");
                            break;
                        case SearchOption.TIMESTAMP_ARRAY:
                            Date[] timestampArray = (Date[]) option.getValue();
                            sb.append("'");
                            sb.append(TimeFormat.getSqlDate(timestampArray[0]) + " 00:00:00");
                            sb.append("' AND '");
                            sb.append(TimeFormat.getSqlDate(timestampArray[1]) + " 23:59:59");
                            sb.append("'");
                            break;
                        case SearchOption.INTEGER_ARRAY:
                            Integer[] integerArray = (Integer[]) option.getValue();
                            sb.append(integerArray[0]);
                            sb.append(" AND ");
                            sb.append(integerArray[1]);
                            break;
                        case SearchOption.DOUBLE_ARRAY:
                            Double[] doubleArray = (Double[]) option.getValue();
                            sb.append(doubleArray[0]);
                            sb.append(" AND ");
                            sb.append(doubleArray[1]);
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                case SearchOption.NOT_IN:
                    sb.append(" NOT");
                case SearchOption.IN:
                    sb.append(" IN (");
                    int length = 0;
                    int i = 0;
                    switch (option.getType()) {
                        case SearchOption.INTEGER_ARRAY:
                            Integer[] integerArray = (Integer[]) option.getValue();
                            length = integerArray.length;
                            while (i < length) {
                                sb.append(integerArray[i]);
                                i++;
                                if (i < length) {
                                    sb.append(", ");
                                }
                            }
                            break;
                        case SearchOption.DATE_ARRAY:
                            Date[] dateArray = (Date[]) option.getValue();
                            length = dateArray.length;
                            while (i < length) {
                                sb.append("'");
                                sb.append(TimeFormat.getSqlDate(dateArray[i]));
                                sb.append("'");
                                i++;
                                if (i < length) {
                                    sb.append(", ");
                                }
                            }
                            break;
                        case SearchOption.DOUBLE_ARRAY:
                            Double[] doubleArray = (Double[]) option.getValue();
                            length = doubleArray.length;
                            while (i < length) {
                                sb.append(doubleArray[i]);
                                i++;
                                if (i < length) {
                                    sb.append(", ");
                                }
                            }
                            break;
                        case SearchOption.STRING_ARRAY:
                            String[] stringArray = (String[]) option.getValue();
                            length = stringArray.length;
                            while (i < length) {
                                sb.append("'");
                                sb.append(stringArray[i]);
                                sb.append("'");
                                i++;
                                if (i < length) {
                                    sb.append(", ");
                                }
                            }
                            break;
                        case SearchOption.SUBQUERY:
                            SQLQuery subquery = (SQLQuery) option.getValue();
                            sb.append("SELECT ");
                            sb.append(subquery.getColumn());
                            sb.append(" FROM ");
                            sb.append(subquery.getTable());
                            sb.append(" WHERE ");
                            if ((subquery.getSearchOption().getType() == SearchOption.NESTED_OR) || (subquery.getSearchOption().getType() == SearchOption.NESTED_AND)) {
                                sb.append(generateNestedSQL(subquery.getSearchOption()));
                            } else {
                                sb.append(generateSQL(subquery.getSearchOption()));
                            }
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    sb.append(")");
                    break;

                case SearchOption.NOT_NULL:
                    sb.append(" IS NOT NULL ");
                    break;

                case SearchOption.IS_NULL:
                    sb.append(" IS NULL ");
                    break;

                case SearchOption.GREATER_THAN:
                    sb.append(" > ");
                    switch (option.getType()) {
                        case SearchOption.INTEGER:
                            sb.append((Integer) option.getValue());
                            break;
                        case SearchOption.FLOAT:
                            sb.append((Float) option.getValue());
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                case SearchOption.SMALLER_THAN:
                    sb.append(" < ");
                    switch (option.getType()) {
                        case SearchOption.INTEGER:
                            sb.append((Integer) option.getValue());
                            break;
                        case SearchOption.FLOAT:
                            sb.append((Float) option.getValue());
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                case SearchOption.GREATER_OR_EQUAL_THAN:
                    sb.append(" >= ");
                    switch (option.getType()) {
                        case SearchOption.INTEGER:
                            sb.append((Integer) option.getValue());
                            break;
                        case SearchOption.FLOAT:
                            sb.append((Float) option.getValue());
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                case SearchOption.SMALLER_OR_EQUAL_THAN:
                    sb.append(" <= ");
                    switch (option.getType()) {
                        case SearchOption.INTEGER:
                            sb.append((Integer) option.getValue());
                            break;
                        case SearchOption.FLOAT:
                            sb.append((Float) option.getValue());
                            break;
                        default:
                            throw new RuntimeException("Unsupported data type " + option.getType() + " with operator: " + option.getOperator());
                    }
                    break;

                default:
                    throw new RuntimeException("Unknown operator: " + option.getOperator());
            }

        } catch (Exception ex) {
            Logger.getInstance().log("SQLFactory.generateSQL(SearchOption option)", ex);
            throw new RuntimeException("Couldn't generate SQL sentence");
        }

        return sb.toString();
    }

    private static String generateNestedSQL(SearchOption option) throws Exception {

        StringBuffer sb = new StringBuffer();

        if ((option.getType() != SearchOption.NESTED_OR) && (option.getType() != SearchOption.NESTED_AND)) {
            throw new Exception("Nested SQL is not nested");
        }

        ArrayList<SearchOption> innerSearchOptions = (ArrayList<SearchOption>) option.getValue();

        sb.append("(");
        for (int i = 0; i < innerSearchOptions.size(); i++) {
            SearchOption innerOption = innerSearchOptions.get(i);

            if ((innerOption.getType() == SearchOption.NESTED_OR) || (innerOption.getType() == SearchOption.NESTED_AND)) {
                sb.append(generateNestedSQL(innerOption));
            } else {
                sb.append("(");
                sb.append(generateSQL(innerOption));
                sb.append(")");
            }

            if (i < (innerSearchOptions.size() - 1)) {
                if (option.getType() == SearchOption.NESTED_OR) {
                    sb.append(" OR ");
                } else if (option.getType() == SearchOption.NESTED_AND) {
                    sb.append(" AND ");
                } else {
                    throw new Exception("Illegal nested booleanoperator");
                }
            }
        }
        sb.append(")");

        return sb.toString();
    }

    public static String formatSearchStringForSQL(String fullString, int operator) throws Exception {

        String formedString = "";
        Character[] charArray = {SQL_WILDCARD_PERCENT, SQL_WILDCARD_UNDERSCORE, SQL_ESCAPE_SLASH, BACKSLASH, SINGLE_QUOTE, DOUBLE_QUOTE, DOT};
        try {
            for (int i = 0; i < fullString.length(); i++) {


                Character character = fullString.charAt(i);
                for (int c = 0; c < charArray.length; c++) {

                    //if character equals
                    if (character.compareTo(charArray[c]) == 0) {
                        //To search for "\", specify it as "\\\\"; this is because the backslashes are stripped once by the parser and again when the pattern match is made, leaving a single backslash to be matched against.
                        if ((BACKSLASH.equals(charArray[c])) && (SearchOption.LIKE == operator)) {
                            formedString = formedString + BACKSLASH + BACKSLASH + BACKSLASH + character;
                            break;
                        } else {
                            formedString = formedString + BACKSLASH + character;
                            break;
                        }
                    } else if (c + 1 == charArray.length) {

                        formedString = formedString + character;
                    } //else if charArray at is final value and formed string haven't got any values yet
                } //for charArray

            }
        } catch (Exception ex) {
            Logger.getInstance().log("SQLFactory.validateStringCharacters(String fullString, int operator)", ex);
            throw new RuntimeException("Couldn't generate SQL sentence");
        }
        return formedString;

    }
}
