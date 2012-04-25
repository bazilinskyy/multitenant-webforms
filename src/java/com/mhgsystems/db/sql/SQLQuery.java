/*
 * MHG Systems Oy Ltd
 */

package com.mhgsystems.db.sql;

import com.mhgsystems.db.*;

/**
 *
 * @author Veli-Matti Plosila
 */
public class SQLQuery {

     public SQLQuery(String column, String table, SearchOption searchOption) {
            this.searchOption = searchOption;
            this.column = column;
            this.table = table;
        }


        private SearchOption searchOption;

        /**
         * Get the value of searchOption
         *
         * @return the value of searchOption
         */
        public SearchOption getSearchOption() {
            return searchOption;
        }

        private String column;

        /**
         * Get the value of column
         *
         * @return the value of column
         */
        public String getColumn() {
            return column;
        }

        private String table;

        /**
         * Get the value of table
         *
         * @return the value of table
         */
        public String getTable() {
            return table;
        }



}
