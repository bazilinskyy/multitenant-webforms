/*
 * MHG Systems Oy Ltd
 */
package com.mhgsystems.db.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Veli-Matti Plosila
 */
public class SearchOption {

    // SQL search operators
    public static final int EQUAL = 1;
    public static final int LIKE = 2;
    public static final int IN = 3;
    public static final int BETWEEN = 4;
    public static final int GREATER_THAN = 5;
    public static final int SMALLER_THAN = 6;
    public static final int GREATER_OR_EQUAL_THAN = 7;
    public static final int SMALLER_OR_EQUAL_THAN = 8;
    public static final int NOT_EQUAL = 9;
    public static final int NOT_LIKE = 10;
    public static final int NOT_IN = 11;
    public static final int NOT_BETWEEN = 12;
    public static final int NOT_NULL = 13;
    public static final int IS_NULL = 14;

    // SQL boolean operators (between searchoptions)
    public static final long AND = 1;
    public static final long OR = 2;

    // Data types
    protected static final int INTEGER = 1;
    protected static final int INTEGER_ARRAY = 2;
    protected static final int STRING = 3;
    protected static final int STRING_ARRAY = 4;
    protected static final int DATE = 5;
    protected static final int DATE_ARRAY = 6;
    protected static final int TIMESTAMP_ARRAY = 7;
    protected static final int BOOLEAN = 8;
    protected static final int BOOLEAN_ARRAY = 9;
    protected static final int DOUBLE = 10;
    protected static final int DOUBLE_ARRAY = 11;
    protected static final int SUBQUERY = 12;
    protected static final int NESTED_AND = 13;
    protected static final int NESTED_OR = 14;
    protected static final int FLOAT = 15;
    protected static final int FLOAT_ARRAY = 16;

    private SearchOption(){
    }

    public SearchOption(String column, Integer value, int operator) {
        this.type = INTEGER;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Integer[] value, int operator) {
        this.type = INTEGER_ARRAY;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, String value, int operator) {
        this.type = STRING;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, String[] value, int operator) {
        this.type = STRING_ARRAY;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Date value, int operator) {
        this.type = DATE;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Date[] value, int operator) {
        this.type = DATE_ARRAY;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Date[] value, int operator, boolean timestamp) {
        this.type = TIMESTAMP_ARRAY;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Boolean value, int operator) {
        this.type = BOOLEAN;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Boolean[] value, int operator) {
        this.type = BOOLEAN_ARRAY;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Double value, int operator) {
        this.type = DOUBLE;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }

    public SearchOption(String column, Double[] value, int operator) {
        this.type = DOUBLE_ARRAY;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }
    public SearchOption(String column, SQLQuery subquery, int operator) {
        this.type = SUBQUERY;
        this.value = subquery;
        this.column = column;
        this.operator = operator;
    }
    public SearchOption(ArrayList<SearchOption> searchOptions, long booleanOperator) {
        // Sample code how to use is located in MHG JIRA: BIOERP-369
        if (booleanOperator == OR) {
            this.type = NESTED_OR;
        } else {
            this.type = NESTED_AND;
        }
        this.value = searchOptions;
    }
    public SearchOption(String column, Float value, int operator) {
        this.type = FLOAT;
        this.value = value;
        this.column = column;
        this.operator = operator;
    }
    public SearchOption(String column, int operator) {
        this.column = column;
        this.operator = operator;
    }
    
    private int type;

    public int getType() {
        return type;
    }

    private Object value;

    public Object getValue() {
        return value;
    }

    private String column;

    public String getColumn() {
        return column;
    }
    
    private int operator;

    public int getOperator() {
        return operator;
    }

    public static ArrayList<SearchOption> parseOptionsMapToSearchOptions(String tableIdField, String tableName, Map options) throws Exception {
        ArrayList<SearchOption> searchOptions = new ArrayList<SearchOption>();

        if (options.containsKey("idArray")) {
            String[] idArray = ((String) options.get("idArray")).split(",");
            Integer[] integerIdArray = new Integer[idArray.length];

            for (int i = 0; i < idArray.length; i++) {
                try {
                    integerIdArray[i] = Integer.parseInt(idArray[i]);
                } catch (NumberFormatException nex) {
                    // skip on error
                }
            }
            
            searchOptions.add(new SearchOption(tableName + "." + tableIdField, integerIdArray, SearchOption.IN));
        }

        /* bbox search requirements:
         * - table has fields named lat and lon (type double)
         * - table's id field is named as tablename_id ex. work_order_id
         */
        if (options.containsKey("bbox")) {
            String bbox = String.valueOf(options.get("bbox"));
            String[] bboxArray = bbox.split(",");

            try {
                searchOptions.add(new SearchOption(tableName + ".lat", new Double[] { Double.parseDouble(bboxArray[0]), Double.parseDouble(bboxArray[2]) }, SearchOption.BETWEEN));
                searchOptions.add(new SearchOption(tableName + ".lon", new Double[] { Double.parseDouble(bboxArray[1]), Double.parseDouble(bboxArray[3]) }, SearchOption.BETWEEN));
            } catch (NumberFormatException nex) {
                throw new Exception("Invalid bbox");
            }

            if (options.containsKey("bboxMask")) {
                String bboxMask = String.valueOf(options.get("bboxMask"));
                String[] bboxMaskArray = bboxMask.split(",");

                try {
                    ArrayList<SearchOption> subQuerySearchOptions = new ArrayList<SearchOption>();
                    subQuerySearchOptions.add(new SearchOption(tableName + ".lat", new Double[] { Double.parseDouble(bboxMaskArray[0]), Double.parseDouble(bboxMaskArray[2]) }, SearchOption.BETWEEN));
                    subQuerySearchOptions.add(new SearchOption(tableName + ".lon", new Double[] { Double.parseDouble(bboxMaskArray[1]), Double.parseDouble(bboxMaskArray[3]) }, SearchOption.BETWEEN));
                    SQLQuery subQuery = new SQLQuery(tableIdField, tableName, new SearchOption(subQuerySearchOptions, SearchOption.AND));
                    searchOptions.add(new SearchOption(tableName + "." + tableIdField, subQuery, SearchOption.NOT_IN));

                } catch (NumberFormatException nex) {
                    throw new Exception("Invalid bboxMask");
                }
            }
        }

        return searchOptions;
    }
}
