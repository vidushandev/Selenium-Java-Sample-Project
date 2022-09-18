package com.shopapotheke.qa.taf.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Most Common date time utility methods can be handled thru here.
 */
public class DateTimeUtil {
    private DateTimeUtil() {
        // making constructor private to restrict unwanted instantiation
    }

    /**
     * Get the Current time stamp as a String in yyyyMMddHHmmss format
     *
     * @return Current TimeStamp as a String (Eg. 20201020062541)
     */
    public static String getCurrentTimeStampAsString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return simpleDateFormat.format(timestamp);
    }
}
