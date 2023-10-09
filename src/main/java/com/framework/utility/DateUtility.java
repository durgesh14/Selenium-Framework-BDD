package com.framework.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
    /**
     * Formats the current date into a specified string format.
     *
     * @param dateFormat The desired format for the date string,
     *                   as accepted by {@link SimpleDateFormat}.
     * @return A string representation of the current date in the specified format.
     */
    public static String getStringDate(String dateFormat) {
        SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);
        Date localDate = new Date();
        return dtf.format(localDate).toString();
    }
}
