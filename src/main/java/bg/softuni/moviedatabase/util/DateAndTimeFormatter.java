package bg.softuni.moviedatabase.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAndTimeFormatter {
    public static String formatDuration(Time duration) {
        SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
        return formatter.format(duration);
    }

    public static String formatReleaseDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return formatter.format(date);
    }
}
