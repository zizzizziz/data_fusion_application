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
    public static Map<Integer, List<Long>> getRemainingQuartersTimestamps(long millils) {
        Map<Integer, List<Long>> quartersTimestamps = new TreeMap<>();
        LocalDate now = Instant.ofEpochSecond(millils).atZone(ZoneId.systemDefault()).toLocalDate();

        int quarter = (now.getMonthValue() - 1) / 3 + 1 + 1;
        int year = now.getYear();

        for (int i = quarter; i <= 4; i++) {
            LocalDate start = LocalDate.of(year, (i - 1) * 3 + 1, 1);
            LocalDate end = start.plusMonths(3).minusDays(1);

            List<Long> startAndEndTimestamps = Arrays.asList(
                    start.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond(),
                    end.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());

            quartersTimestamps.put(i, startAndEndTimestamps);
        }

        return quartersTimestamps;
    }

    // 获取本年度剩余月数
    public static Map<Integer, List<Long>> getRemainingMonthsTimestamps(long millis) {
        Map<Integer, List<Long>> monthsTimestamps = new TreeMap<>();
        LocalDate now = Instant.ofEpochSecond(millis).atZone(ZoneId.systemDefault()).toLocalDate();

        int month = now.getMonthValue() + 1;
        int year = now.getYear();

        for (int i = month; i <= 12; i++) {
            LocalDate start = LocalDate.of(year, i, 1);
            LocalDate end = start.plusMonths(1).minusDays(1);

            List<Long> startAndEndTimestamps = Arrays.asList(
                    start.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond(),
                    end.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());

            monthsTimestamps.put(i, startAndEndTimestamps);
        }

        return monthsTimestamps;
    }

    // 获取未来四年的时间
    public static Map<Integer, List<Long>> getNextFourYearsTimestamps(long millis) {
        Map<Integer, List<Long>> yearsTimestamps = new TreeMap<>();
        LocalDate now = Instant.ofEpochSecond(millis).atZone(ZoneId.systemDefault()).toLocalDate();

        int year = now.getYear() + 1;

        for (int i = 0; i < 4; i++) {
            LocalDate start = LocalDate.of(year + i, 1, 1);
            LocalDate end = start.plusYears(1).minusDays(1);

            List<Long> startAndEndTimestamps = Arrays.asList(
                    start.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond(),
                    end.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());

            yearsTimestamps.put(year + i, startAndEndTimestamps);
        }

        return yearsTimestamps;
    }
}
