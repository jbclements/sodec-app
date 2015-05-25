package edu.calpoly.sodec.sodecapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Methods for dealing with timestamps
 */
public class TimestampUtils {

    /**
     * Return an ISO 8601 combined date and time string for current date/time
     *
     * @return String with format "yyyy-MM-dd'T'HH:mm:ss"
     */
    public static String getIsoForNow() {
        return Long.toString(System.currentTimeMillis()/1000);
    }


    public static String getStartIsoForMonth() {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date lastMonth = cal.getTime();
        return Long.toString(lastMonth.getTime()/1000);
    }

    public static String getStartIsoForWeek() {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date lastWeek = cal.getTime();
        return Long.toString(lastWeek.getTime()/1000);

    }

    public static String getStartIsoForDay(){
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDay = cal.getTime();
        return Long.toString(lastDay.getTime()/1000);
    }
    public static String getStartIsoForYear(){
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -365);
        Date lastDay = cal.getTime();
        return Long.toString(lastDay.getTime()/1000);
    }

    /**
     * Return an ISO 8601 combined date and time string for specified date/time
     *
     * @param date
     *            Date
     * @return String with format "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    private static String getISO(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        return dateFormat.format(date);
    }

    /**
     * Private constructor: class cannot be instantiated
     */
    private TimestampUtils() {
    }
}