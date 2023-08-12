package ldn.cs.decision.alghthrims.wealth;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.alghthrims.staff.StaffPrediction;
import ldn.cs.decision.pojo.wealth.Wealth;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;

public class WealthPrediction {
    private static final WealthPrediction instance = new WealthPrediction();

    private WealthPrediction() {
    }

    public static WealthPrediction getInstance() {
        return instance;
    }

    private static final List<BiConsumer<Wealth, Double>> SETTERS = Arrays.asList(
            (wealth, value) -> wealth.setResearch(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setDevice(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setProduction(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setStorage(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setMateriel(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setTransportation(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setSalary(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setRevenue(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setProfit(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setFixedAssets(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setCashAssets(BigDecimal.valueOf(value)),
            (wealth, value) -> wealth.setFinance(BigDecimal.valueOf(value))
    );

    private static final List<Function<Wealth, Double>> GETTERS = Arrays.asList(
            wealth -> wealth.getResearch().doubleValue(),
            wealth -> wealth.getDevice().doubleValue(),
            wealth -> wealth.getProduction().doubleValue(),
            wealth -> wealth.getStorage().doubleValue(),
            wealth -> wealth.getMateriel().doubleValue(),
            wealth -> wealth.getTransportation().doubleValue(),
            wealth -> wealth.getSalary().doubleValue(),
            wealth -> wealth.getRevenue().doubleValue(),
            wealth -> wealth.getProfit().doubleValue(),
            wealth -> wealth.getFixedAssets().doubleValue(),
            wealth -> wealth.getCashAssets().doubleValue(),
            wealth -> wealth.getFinance().doubleValue()
    );

    public Wealth getNextWealth(List<Wealth> historyWealth, Wealth nowWealth, long nextTime) {
        List<Long> eventTimes = new ArrayList<>();
        for (Wealth wealth : historyWealth) {
            eventTimes.add(wealth.getEventTime());
        }
        eventTimes.add(nowWealth.getEventTime());

        Wealth wealth = new Wealth();
        wealth.setCorporation(nowWealth.getCorporation());

        for (int i = 0; i < GETTERS.size(); i++) {
            List<Double> values = new ArrayList<>();
            for (Wealth w : historyWealth) {
                values.add(GETTERS.get(i).apply(w));
            }
            values.add(GETTERS.get(i).apply(nowWealth));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(wealth, predictor.predict(nextTime) > 0 ? predictor.predict(nextTime) : 0);
        }

        wealth.setEventTime(nextTime);
        wealth.setUpdateTime(System.currentTimeMillis() / 1000);
        return wealth;
    }
}

