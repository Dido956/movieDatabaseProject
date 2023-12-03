package bg.softuni.moviedatabase.util;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateAndTimeFormatterTest {

    @Test
    public void testFormatDuration() {
        Time duration = Time.valueOf("1:23:45");

        String formattedDuration = DateAndTimeFormatter.formatDuration(duration);

        assertEquals("1:23", formattedDuration);
    }

    @Test
    public void testFormatReleaseDate() {
        LocalDate releaseDate = LocalDate.of(2023, 12, 03);

        String formattedReleaseDate = DateAndTimeFormatter.formatReleaseDate(releaseDate);

        assertEquals("December 2023", formattedReleaseDate);
    }

}