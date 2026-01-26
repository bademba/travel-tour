package com.kendirita.travel_tour.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimestampUtil {

    private static final String PATTERN = "dd-MM-yyyy HH:mm:ss";

    private TimestampUtil() {
        // prevent instantiation
    }

    public static String now() {
        return new SimpleDateFormat(PATTERN).format(new Date());
    }
}