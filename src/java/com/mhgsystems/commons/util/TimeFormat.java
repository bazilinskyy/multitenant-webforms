/*
 * TimeFormat.java
 *
 * Created on 9.6.2006, 10:11
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.mhgsystems.commons.util;

import com.mhgsystems.commons.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Veli-Matti Plosila
 */
public class TimeFormat implements java.io.Serializable {

    /** Creates a new instance of TimeFormat */
    public TimeFormat() {
    }
    public static final java.sql.Date MIN_SQL_DATE = new java.sql.Date(new java.util.GregorianCalendar(0000, 0, 1).getTime().getTime());
    public static final java.sql.Date MAX_SQL_DATE = new java.sql.Date(new java.util.GregorianCalendar(9999, 0, 1).getTime().getTime());
    public static final java.util.Date MIN_UTIL_DATE = new GregorianCalendar(0000, 0, 1).getTime();
    public static final java.util.Date MAX_UTIL_DATE = new GregorianCalendar(9999, 0, 1).getTime();

    public String timestampToStr(Timestamp timestamp) {
        try {
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("d.M.yyyy HH:mm");

            return sdf.format(timestamp);
        } catch (Exception ex) {
            return "";
        }
    }

    public String dateToStr(Date date) {
        try {
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("d.M.yyyy");
            return sdf.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getSqlDate(Date date) {
        try {
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    public static Date strToDate(String strDate) {

        SimpleDateFormat format = new SimpleDateFormat();

        String[] pattern = {"dd.MM.yyyy",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd-MM-yyyy",
            "dd/MM/yyyy"};

        int i = 0;

        while (i < pattern.length) {

            try {
                format.applyPattern(pattern[i]);
                return format.parse(strDate);

            } catch (Exception e) {
            }

            i++;
        }

        return null;
    }

    public Date deviceTimestampToDate(String deviceTimestamp) {

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.clear();

            int year = Integer.parseInt(deviceTimestamp.substring(0, 4));

            if ((year > 2008) && (year < 2038)) {
                // kk alkaa nollasta, siksi -1 (tammikuu = 0)
                int month = Integer.parseInt(deviceTimestamp.substring(4, 6)) - 1;
                int day = Integer.parseInt(deviceTimestamp.substring(6, 8));
                int hour = Integer.parseInt(deviceTimestamp.substring(8, 10));
                int minute = Integer.parseInt(deviceTimestamp.substring(10, 12));
                int second = Integer.parseInt(deviceTimestamp.substring(12));

                calendar.set(year, month, day, hour, minute, second);

                return calendar.getTime();

            } else {
                // deviceTimestamp is unix timestamp
                return new Date(Long.parseLong(deviceTimestamp));
            }

        } catch (Exception ex) {
            return null;
        } finally {
            calendar = null;
        }

    }

    public Date convertYYYYMMDDToDate(String YYYYMMDD) {

        Calendar calendar = Calendar.getInstance();

        try {

            calendar.clear();

            int YEAR = Integer.parseInt(YYYYMMDD.substring(0, 4));
            // january = 0
            int MONTH = Integer.parseInt(YYYYMMDD.substring(4, 6)) - 1;
            int DAY = Integer.parseInt(YYYYMMDD.substring(6, 8));

            calendar.set(YEAR, MONTH, DAY);

            return calendar.getTime();
        } catch (Exception ex) {
            return null;
        } finally {
            calendar = null;
        }


    }

    public Date getDiffenrencedDate(Date date, int dif) {
        try {
            Calendar cal = Calendar.getInstance();
            // changes the day
            cal.setTime(date);
            cal.add(cal.DAY_OF_YEAR, dif);
            Date newDate = new Date();
            // format  date
            newDate = (Date) cal.getTime();
            return newDate;

        } catch (Exception ex) {
            return null;
        }

    }
    /**
     * Holds value of property minDate.
     */
    private java.util.GregorianCalendar minDate;

    /**
     * Getter for property minDate.
     * @return Value of property minDate.
     */
    public java.util.GregorianCalendar getMinDate() {
        minDate = new java.util.GregorianCalendar(1975, 0, 1);
        return this.minDate;
    }

    /**
     * Setter for property minDate.
     * @param minDate New value of property minDate.
     */
    public void setMinDate(java.util.GregorianCalendar minDate) {

        this.minDate = minDate;
    }
    /**
     * Holds value of property maxDate.
     */
    private java.util.GregorianCalendar maxDate;

    /**
     * Getter for property maxDate.
     * @return Value of property maxDate.
     */
    public java.util.GregorianCalendar getMaxDate() {
        maxDate = new java.util.GregorianCalendar(2020, 11, 31);
        return this.maxDate;
    }

    /**
     * Setter for property maxDate.
     * @param maxDate New value of property maxDate.
     */
    public void setMaxDate(java.util.GregorianCalendar maxDate) {

        this.maxDate = maxDate;
    }

    public static Date getGreaterDate(Date date1, Date date2) {

        if (date1 != null && date2 != null) {

            if (date1.after(date2)) {
                return date1;
            } else {
                return date2;
            }

        } else {
            return null;
        }
    }

    public static java.util.Date getLocalDate(TimeZone localTimeZone) {
        Calendar utcCalendar = new GregorianCalendar(TimeZone.getTimeZone("Etc/UTC"));
        Calendar localCalendar = TimeFormat.convertUtcToLocal(utcCalendar, localTimeZone);

        return localCalendar.getTime();
    }

    public static Calendar getLocalCalendar(TimeZone localTimeZone) {
        Calendar utcCalendar = new GregorianCalendar(TimeZone.getTimeZone("Etc/UTC"));
        Calendar localCalendar = TimeFormat.convertUtcToLocal(utcCalendar, localTimeZone);

        return localCalendar;
    }

    public static java.util.Date convertTimeUtcToLocal(java.util.Date utcDate, TimeZone localTimeZone) {
        if (utcDate == null) {
            return null;
        }

        if (utcDate.getTime() > (24 * 60 * 60 * 1000)) {
            return null;
        }

        int offset = localTimeZone.getOffset(new GregorianCalendar(localTimeZone).getTime().getTime());

        long timeMs = utcDate.getTime() + offset;
        long dayInMs = 24 * 60 * 60 * 1000;
        if (timeMs < 0) {
            timeMs += dayInMs;
        } else if (timeMs >= dayInMs) {
            timeMs -= dayInMs;
        }

        return new Date(timeMs);
    }

    public static Calendar convertUtcToLocal(Calendar utcCalendar, TimeZone localTimeZone) {
        Calendar localCalendar = new GregorianCalendar(localTimeZone);
        localCalendar.setTimeInMillis(utcCalendar.getTimeInMillis());

        return localCalendar;
    }

    public static java.util.Date convertTimeLocalToUtc(java.util.Date localDate, TimeZone localTimeZone) {
        if (localDate == null) {
            return null;
        }

        if (localDate.getTime() > (24 * 60 * 60 * 1000)) {
            return null;
        }

        int offset = localTimeZone.getOffset(new GregorianCalendar(localTimeZone).getTime().getTime());

        long timeMs = localDate.getTime() - offset;
        long dayInMs = 24 * 60 * 60 * 1000;
        if (timeMs < 0) {
            timeMs += dayInMs;
        } else if (timeMs >= dayInMs) {
            timeMs -= dayInMs;
        }

        return new Date(timeMs);
    }

    public static Calendar convertLocalToUtc(Calendar localCalendar, TimeZone localTimeZone) {
        Calendar utcCalendar = new GregorianCalendar(TimeZone.getTimeZone("Etc/UTC"));
        utcCalendar.setTimeInMillis(localCalendar.getTimeInMillis());

        return utcCalendar;
    }

    private static long convertTimeValuesToMs(int hour, int minute, int second, int millisecond) {
        long time = 0;

        time += hour * 60 * 60 * 1000;
        time += minute * 60 * 1000;
        time += second * 1000;
        time += millisecond;

        return time;
    }

    private static Map convertMsToTimeValues(long millisecond) {
        Map<String, Integer> timeValues = new HashMap<String, Integer>();

        int hour = (int) (millisecond / (60 * 60 * 1000));
        timeValues.put("hour", hour);
        millisecond -= hour * 60 * 60 * 1000;

        int minute = (int) (millisecond / (60 * 1000));
        timeValues.put("minute", minute);
        millisecond -= minute * 60 * 1000;

        int second = (int) (millisecond / 1000);
        timeValues.put("second", second);
        millisecond -= second * 1000;

        timeValues.put("millisecond", new Integer((int) millisecond));

        return timeValues;
    }
    
//    public static boolean compareDates(Date date, String strDate) {
//        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
//
//        try {
//        Date date2 = df.parse(strDate);
//
//        String strDate1 = df.format(date);
//        String strDate2 = df.format(date2);
//
//        if (strDate1.equals(strDate2)) {
//            return true;
//        } else {
//            return false;
//        }
//        } catch (ParseException e) {
//            Logger.getInstance().log("TimeFormat.compareDates(Date date, String strDate)" + e);
//            return false;
//        }
//    }

    public static String getLocalizedTime(String language, String timezoneString, Date date) {
        return getLocalizedTime(language, timezoneString, date, DateFormat.LONG);
    }

    public static String getLocalizedTime(String language, String timezoneString, Date date, int style) {
        TimeZone localTimezone = TimeZone.getTimeZone(timezoneString);

        Locale locale = new Locale(language, "", "");
        DateFormat timeFormat = DateFormat.getTimeInstance(style, locale);

        SimpleDateFormat stf = (SimpleDateFormat) timeFormat;
        stf.setTimeZone(localTimezone);
        String timeString = stf.format(date);

        return timeString;
    }

    public static String getLocalizedDate(String language, String timeZoneString, Date date) {
        TimeZone localTimezone = TimeZone.getTimeZone(timeZoneString);

        Locale locale = new Locale(language, "", "");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

        SimpleDateFormat sdf = (SimpleDateFormat) dateFormat;
        sdf.setTimeZone(localTimezone);
        String dateString = sdf.format(date);

        return dateString;
    }

}

