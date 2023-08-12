package ldn.cs.decision.alghthrims.production;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.pojo.production.Production;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.*;
import java.util.stream.Collectors;

public class ProductionPrediction {
    private static final ProductionPrediction instance = new ProductionPrediction();

    private ProductionPrediction() {
    }

    public static ProductionPrediction getInstance() {
        return instance;
    }

    private static final List<BiConsumer<Production, Double>> SETTERS = Arrays.asList(
            (production, value) -> production.setQuantity(BigDecimal.valueOf(value)),
            (production, value) -> production.setCost(BigDecimal.valueOf(value))
    );

    private static final List<Function<Production, Double>> GETTERS = Arrays.asList(
            production -> production.getQuantity().doubleValue(),
            production -> production.getCost().doubleValue()
    );

    public Production getNextProduction(List<Production> historyProduction, Production nowProduction, long nextTime) {
        List<Long> eventTimes = historyProduction.stream()
                .map(Production::getEventTime)
                .collect(Collectors.toList());
        eventTimes.add(nowProduction.getEventTime());

        Production predictedProduction = new Production();
        predictedProduction.setCorporation(nowProduction.getCorporation());
        predictedProduction.setCategories(nowProduction.getCategories());
        predictedProduction.setTypes(nowProduction.getTypes());
        predictedProduction.setProvince(nowProduction.getProvince());
        predictedProduction.setCountry(nowProduction.getCountry());

        for (int i = 0; i < GETTERS.size(); i++) {
            List<Double> values = historyProduction.stream()
                    .map(GETTERS.get(i))
                    .collect(Collectors.toList());
            values.add(GETTERS.get(i).apply(nowProduction));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(predictedProduction, predictor.predict(nextTime) > 0 ? predictor.predict(nextTime) : 0);
        }

        predictedProduction.setEventTime(nextTime);
        predictedProduction.setUpdateTime(System.currentTimeMillis() / 1000);

        return predictedProduction;
    }
}


