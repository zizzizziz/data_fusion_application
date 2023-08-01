package ldn.cs.decision.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ComputeFutureTime {
    // 获取本年度剩余季度
    public static Map<Integer, List<Long>> getRemainingQuartersTimestamps(long eventTime) {
        Map<Integer, List<Long>> quartersTimestamps = new TreeMap<>();
        LocalDate now = Instant.ofEpochMilli(eventTime).atZone(ZoneId.systemDefault()).toLocalDate();

        int quarter = (now.getMonthValue() - 1) / 3 + 1;
        int year = now.getYear();

        for (int i = quarter; i <= 4; i++) {
            LocalDate start = LocalDate.of(year, (i - 1) * 3 + 1, 1);
            LocalDate end = start.plusMonths(3).minusDays(1);

            List<Long> startAndEndTimestamps = Arrays.asList(
                    start.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                    end.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            quartersTimestamps.put(i, startAndEndTimestamps);
        }

        return quartersTimestamps;
    }

    // 获取本年度剩余月数
    public static Map<Integer, List<Long>> getRemainingMonthsTimestamps(long millis) {
        Map<Integer, List<Long>> monthsTimestamps = new TreeMap<>();
        LocalDate now = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();

        int month = now.getMonthValue();
        int year = now.getYear();

        for (int i = month; i <= 12; i++) {
            LocalDate start = LocalDate.of(year, i, 1);
            LocalDate end = start.plusMonths(1).minusDays(1);

            List<Long> startAndEndTimestamps = Arrays.asList(
                    start.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                    end.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            monthsTimestamps.put(i, startAndEndTimestamps);
        }

        return monthsTimestamps;
    }

    // 获取未来四年（包括本年）的时间
    public static Map<Integer, List<Long>> getNextFourYearsTimestamps(long millis) {
        Map<Integer, List<Long>> yearsTimestamps = new TreeMap<>();
        LocalDate now = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();

        int year = now.getYear();

        for (int i = 0; i < 4; i++) {
            LocalDate start = LocalDate.of(year + i, 1, 1);
            LocalDate end = start.plusYears(1).minusDays(1);

            List<Long> startAndEndTimestamps = Arrays.asList(
                    start.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                    end.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            yearsTimestamps.put(year + i, startAndEndTimestamps);
        }

        return yearsTimestamps;
    }
}
