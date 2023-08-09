package ldn.cs.decision.alghthrims.convey;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.pojo.convey.Convey;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConveyPrediction {
    private static final ConveyPrediction instance = new ConveyPrediction();

    private ConveyPrediction() {
    }

    public static ConveyPrediction getInstance() {
        return instance;
    }

    private static final List<BiConsumer<Convey, Double>> SETTERS = Arrays.asList(
            (convey, value) -> convey.setQuantity(BigDecimal.valueOf(value)),
            (convey, value) -> convey.setInventory(BigDecimal.valueOf(value)),
            (convey, value) -> convey.setMileage(BigDecimal.valueOf(value)),
            (convey, value) -> convey.setCost(BigDecimal.valueOf(value))
    );

    private static final List<Function<Convey, Double>> GETTERS = Arrays.asList(
            convey -> convey.getQuantity().doubleValue(),
            convey -> convey.getInventory().doubleValue(),
            convey -> convey.getMileage().doubleValue(),
            convey -> convey.getCost().doubleValue()
    );

    public Convey getNextConvey(List<Convey> historyConvey, Convey nowConvey, long nextTime) {
        List<Long> eventTimes = historyConvey.stream()
                .map(Convey::getEventTime)
                .collect(Collectors.toList());
        eventTimes.add(nowConvey.getEventTime());

        Convey predictedConvey = new Convey();
        predictedConvey.setCorporation(nowConvey.getCorporation());
        predictedConvey.setTypes(nowConvey.getTypes());
        predictedConvey.setCategories(nowConvey.getCategories());

        for (int i = 0; i < GETTERS.size(); i++) {
            List<Double> values = historyConvey.stream()
                    .map(GETTERS.get(i))
                    .collect(Collectors.toList());
            values.add(GETTERS.get(i).apply(nowConvey));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(predictedConvey, predictor.predict(nextTime) > 0 ? predictor.predict(nextTime) : 0);
        }

        predictedConvey.setEventTime(nextTime);
        predictedConvey.setUpdateTime(System.currentTimeMillis() / 1000);

        return predictedConvey;
    }
}

