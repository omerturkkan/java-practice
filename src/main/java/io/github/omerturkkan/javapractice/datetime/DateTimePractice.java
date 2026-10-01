package io.github.omerturkkan.javapractice.datetime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateTimePractice {
    public static void main(String[] args) {
        LocalDate birthday = LocalDate.of(1995, Month.MARCH, 14);
        LocalDate release = LocalDate.of(2026, 10, 1);

        System.out.println("birthday     : " + birthday);
        System.out.printf("day of week  : %s, day of year: %d%n", birthday.getDayOfWeek(), birthday.getDayOfYear());
        System.out.printf("leap year    : %b, length of month: %d%n", birthday.isLeapYear(), birthday.lengthOfMonth());

        // Date objects are immutable: every operation returns a new instance
        LocalDate later = birthday.plusYears(30).plusMonths(2).minusDays(5);
        System.out.printf("original     : %s (unchanged)%n", birthday);
        System.out.printf("shifted      : %s%n", later);

        // Period measures date-based gaps, ChronoUnit gives a single unit
        Period age = Period.between(birthday, release);
        System.out.printf("%nperiod       : %d years %d months %d days%n",
                age.getYears(), age.getMonths(), age.getDays());
        System.out.printf("total days   : %d%n", ChronoUnit.DAYS.between(birthday, release));
        System.out.printf("total months : %d%n", ChronoUnit.MONTHS.between(birthday, release));

        // Comparisons read like plain English
        System.out.printf("%nbefore       : %b, after: %b, equal: %b%n",
                birthday.isBefore(release), birthday.isAfter(release), birthday.isEqual(birthday));

        // TemporalAdjusters handle the awkward calendar questions
        LocalDate firstOfMonth = release.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastOfMonth = release.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate nextFriday = release.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        LocalDate lastMonday = release.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));

        System.out.printf("%nfirst of month : %s%n", firstOfMonth);
        System.out.printf("last of month  : %s%n", lastOfMonth);
        System.out.printf("next friday    : %s%n", nextFriday);
        System.out.printf("last monday    : %s%n", lastMonday);

        // LocalTime and Duration cover time-based gaps
        LocalTime start = LocalTime.of(9, 30);
        LocalTime end = LocalTime.of(17, 45);
        Duration shift = Duration.between(start, end);
        System.out.printf("%nshift        : %s - %s = %d h %d min (%d minutes total)%n",
                start, end, shift.toHours(), shift.toMinutesPart(), shift.toMinutes());

        LocalDateTime meeting = LocalDateTime.of(release, LocalTime.of(14, 0));
        System.out.printf("meeting      : %s%n", meeting);
        System.out.printf("plus 90 min  : %s%n", meeting.plusMinutes(90));

        // Formatting: built-in patterns, custom patterns, and locales
        DateTimeFormatter turkish = DateTimeFormatter.ofPattern("dd MMMM yyyy EEEE", new Locale("tr", "TR"));
        DateTimeFormatter english = DateTimeFormatter.ofPattern("EEE, MMM d yyyy", Locale.ENGLISH);
        DateTimeFormatter stamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.printf("%niso          : %s%n", release.format(DateTimeFormatter.ISO_DATE));
        System.out.printf("turkish      : %s%n", release.format(turkish));
        System.out.printf("english      : %s%n", release.format(english));
        System.out.printf("timestamp    : %s%n", meeting.format(stamp));

        // Parsing is the reverse, and it throws on bad input
        LocalDate parsed = LocalDate.parse("2026-12-31");
        LocalDate parsedCustom = LocalDate.parse("31/12/2026", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.printf("%nparsed       : %s%n", parsed);
        System.out.printf("parsed custom: %s%n", parsedCustom);

        try {
            LocalDate.parse("31-12-2026");
        } catch (DateTimeParseException e) {
            System.out.println("bad input    : " + e.getMessage());
        }

        // Time zones: the same instant seen from three places
        ZonedDateTime istanbul = meeting.atZone(ZoneId.of("Europe/Istanbul"));
        System.out.printf("%nistanbul     : %s%n", istanbul);
        System.out.printf("london       : %s%n", istanbul.withZoneSameInstant(ZoneId.of("Europe/London")));
        System.out.printf("tokyo        : %s%n", istanbul.withZoneSameInstant(ZoneId.of("Asia/Tokyo")));

        // A small countdown, the kind of thing a real app needs
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = birthday.withYear(today.getYear());
        if (!nextBirthday.isAfter(today)) nextBirthday = nextBirthday.plusYears(1);
        System.out.printf("%ntoday        : %s%n", today);
        System.out.printf("next birthday: %s (%d days away)%n",
                nextBirthday, ChronoUnit.DAYS.between(today, nextBirthday));
    }
}
