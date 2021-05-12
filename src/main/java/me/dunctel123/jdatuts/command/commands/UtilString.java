package me.dunctel123.jdatuts.command.commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class UtilString {

    /**
     * Change variable names to readable String.
     * Use VariableToString("_", Enum.type) to format Enum.
     *
     * @param regex
     * @param input
     * @return
     */
    public static String VariableToString(String regex, String input) {
        String[] splitting = new String[]{input};
        if (regex != null) splitting = input.split(regex);
        String splitted = "";
        for (String s : splitting) {
            splitted += s.substring(0, 1).toUpperCase(Locale.ENGLISH) + s.substring(1).toLowerCase(Locale.ENGLISH) + " ";
        }
        return splitted.substring(0, splitted.length() - 1);
    }

    /**
     * Return a string of formatted duration in Hour/Min/Sec format
     *
     * @param duration
     * @return
     */
    public static String formatDurationToString(Long duration) {
        TimeUnit u = TimeUnit.MILLISECONDS;
        long hours = u.toHours(duration) % 24;
        long minutes = u.toMinutes(duration) % 60;
        long seconds = u.toSeconds(duration) % 60;
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    /**
     * Return a string of durations that is appropriate for parsing.
     * Turn any types of duration(i.e. MM:SS) to HH:MM:SS format.
     * Use Duration.between(LocalTime.MIN, LocalTime.parse(formatDurationString(format)).toMillis()
     * to format it into a duration.
     *
     * @param format
     * @return
     */
    public static String formatDurationString(String format) {
        String[] durations = format.split(":");
        String duration = "";

        for (int i = 0; i < durations.length; i++) {
            if (durations[i].length() == 1)
                durations[i] = "0" + durations[i];
            duration += durations[i] + ":";
        }
        duration = duration.substring(0, duration.length() - 1);

        if (durations.length == 2) //Hour
            duration = "00:" + duration;
        else if (durations.length == 1) //Minutes
            duration = "00:00:" + duration;
        return duration;
    }

    /**
     * Return a string of formatted time in "00 Hour, 00 Minutes, 00 Seconds" format
     *
     * @param time
     * @return
     */
    public static String formatTime(Long time) {
        TimeUnit u = TimeUnit.MILLISECONDS;
        long days = u.toDays(time) % 7;
        long hours = u.toHours(time) % 24;
        long minutes = u.toMinutes(time) % 60;
        long seconds = u.toSeconds(time) % 60;
        String day = "";
        String hour = "";
        String minute = "";
        String second = "";
        if (days > 0) {
            day = String.format("%2d day(s), ", days);
        }
        if (hours > 0) {
            hour = String.format("%2d hour(s), ", hours);
        }
        if (minutes > 0) {
            minute = String.format("%2d minute(s), ", minutes);
        }
        if (seconds > 0) {
            second = String.format("%2d second(s)", seconds);
        }
        return day + hour + minute + second + " ";
    }

    /**
     * Change OffsetDateTIme to human readable form.
     *
     * @param time the OffsetDateTIme to be formatted to
     * @return a string of the date in "Month/Day/Year Hour:Minute:Second AM/PM" format
     */
    public static String formatOffsetDateTime(OffsetDateTime time) {
        return DateTimeFormatter.ofPattern("M/d/u h:m:s a").format(time);
    }

    /**
     * Convert bytes value to human readable form.
     * See: http://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
     *
     * @param bytes for the bytes value to be converted
     * @param si    Choose between SI or Binary
     * @return
     */
    public static String convertBytes(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
