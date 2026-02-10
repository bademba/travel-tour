package com.kendirita.travel_tour.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class HybridIdGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final DateTimeFormatter YM_FORMAT =
            DateTimeFormatter.ofPattern("yyMM");

    private HybridIdGenerator() {}

    public static String generate() {
        String yearMonth = LocalDate.now().format(YM_FORMAT);

        StringBuilder sb = new StringBuilder(yearMonth);

        // 6 random digits
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
