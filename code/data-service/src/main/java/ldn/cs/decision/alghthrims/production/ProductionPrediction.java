package ldn.cs.decision.alghthrims.production;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.pojo.production.Production;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.*;
import java.util.stream.Collectors;

public class ProductionPrediction {
    private static final List<BiConsumer<Production, Long>> SETTERS = Arrays.asList(
            Production::setQuantity, Production::setCost);

    private static final List<Function<Production, Long>> GETTERS = Arrays.asList(
            Production::getQuantity, Production::getCost);

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
            List<Long> values = historyProduction.stream()
                    .map(GETTERS.get(i))
                    .collect(Collectors.toList());
            values.add(GETTERS.get(i).apply(nowProduction));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(predictedProduction, predictor.predict(nextTime));
        }

        predictedProduction.setEventTime(nextTime);
        predictedProduction.setUpdateTime(System.currentTimeMillis());

        return predictedProduction;
    }
}


