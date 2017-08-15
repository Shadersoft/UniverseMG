package com.Shadersoft.UniverseMG.utils;

public class TimeUtils {

    public static long secondsToMillis(int seconds) {
        return seconds * 1000;
    }

    public static long minutesToMillis(int minutes) {
        return minutes * 60 * 1000;
    }

    public static long hoursToMillis(int hours) {
        return hours * 3600 * 1000;
    }

    public static long daysToMillis(int days) {
        return days * 24 * 3600 * 1000;
    }

    public static long yearsToMillis(int years) {
        return Math.round(years * 365.25 * 3600 * 1000);
    }
}
