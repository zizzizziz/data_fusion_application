package ldn.cs.decision.alghthrims.wealth;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.alghthrims.staff.StaffPrediction;
import ldn.cs.decision.pojo.wealth.Wealth;
import java.util.*;
import java.util.function.*;

public class WealthPrediction {
    private static final WealthPrediction instance = new WealthPrediction();

    private WealthPrediction() {
    }

    public static WealthPrediction getInstance() {
        return instance;
    }

    private static final List<BiConsumer<Wealth, Long>> SETTERS = Arrays.asList(
            Wealth::setResearch, Wealth::setDevice, Wealth::setProduction,
            Wealth::setStorage, Wealth::setMateriel, Wealth::setTransportation,
            Wealth::setSalary, Wealth::setRevenue, Wealth::setProfit,
            Wealth::setFixedAssets, Wealth::setCashAssets, Wealth::setFinance);

    private static final List<Function<Wealth, Long>> GETTERS = Arrays.asList(
            Wealth::getResearch, Wealth::getDevice, Wealth::getProduction,
            Wealth::getStorage, Wealth::getMateriel, Wealth::getTransportation,
            Wealth::getSalary, Wealth::getRevenue, Wealth::getProfit,
            Wealth::getFixedAssets, Wealth::getCashAssets, Wealth::getFinance);

    public Wealth getNextWealth(List<Wealth> historyWealth, Wealth nowWealth, long nextTime) {
        List<Long> eventTimes = new ArrayList<>();
        for (Wealth wealth : historyWealth) {
            eventTimes.add(wealth.getEventTime());
        }
        eventTimes.add(nowWealth.getEventTime());

        Wealth wealth = new Wealth();
        wealth.setCorporation(nowWealth.getCorporation());

        for (int i = 0; i < GETTERS.size(); i++) {
            List<Long> values = new ArrayList<>();
            for (Wealth w : historyWealth) {
                values.add(GETTERS.get(i).apply(w));
            }
            values.add(GETTERS.get(i).apply(nowWealth));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(wealth, predictor.predict(nextTime));
        }

        wealth.setEventTime(nextTime);
        wealth.setUpdateTime(System.currentTimeMillis());
        return wealth;
    }
}

