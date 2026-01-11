package com.oops.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class TimeZone {

    private TimeZone() {}

    public static final ZoneId KOREA = ZoneId.of("Asia/Seoul");

    /**
     * LocalDateTime → Asia/Seoul ZonedDateTime
     */
    public static ZonedDateTime toKorea(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atZone(KOREA);
    }
}
