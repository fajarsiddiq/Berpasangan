package com.fajarsiddiq.berpasangan.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Muhammad Fajar on 16/06/2016.
 */
public class TimeHelper {
    /**
     * Copied from http://stackoverflow.com/a/23776786/6443410
     * @param time
     * @return
     */
    public static String getDate(final long time) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm");
        java.util.Date date = new java.util.Date(time);
        return sdf.format(date);
    }
}
