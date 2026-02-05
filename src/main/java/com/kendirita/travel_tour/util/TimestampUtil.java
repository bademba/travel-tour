package com.kendirita.travel_tour.util;

import org.springframework.expression.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimestampUtil {

    private static final String PATTERN = "dd-MM-yyyy HH:mm:ss";
    private static final String DB_PATTERN = "yyyy-MM-dd HH:mm:ss";


    private TimestampUtil() {
        // prevent instantiation
    }

    public static String now() {
        return new SimpleDateFormat(PATTERN).format(new Date());
    }



    public static Date parseDate(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return (Date) value;
        }

        if (value instanceof String) {
            try {
                return new SimpleDateFormat(DB_PATTERN).parse((String) value);
            } catch (java.text.ParseException e) {
                throw new IllegalArgumentException(
                        "Invalid date format. Expected yyyy-MM-dd HH:mm:ss, got: " + value
                );
            }
        }

        throw new IllegalArgumentException("Unsupported date value: " + value);
    }

}