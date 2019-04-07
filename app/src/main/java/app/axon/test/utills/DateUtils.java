package app.axon.test.utills;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtils {
    private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static String getDateString(String time) {
        return new DateTime(time).toString(fmt);
    }
}
