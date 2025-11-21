package javakit.datetimes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private final ZonedDateTime createdDateTime;
    public DateTime() { this.createdDateTime = ZonedDateTime.now(); }

    @Override
    public String toString() { return "DateTime{createdDateTime=%s}".formatted(createdDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)); }
}
