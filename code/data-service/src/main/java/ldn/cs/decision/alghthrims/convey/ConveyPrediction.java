package ldn.cs.decision.alghthrims.convey;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.fusion.pojo.convey.Convey;

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

    private static final List<BiConsumer<Convey, Long>> SETTERS = Arrays.asList(
            Convey::setQuantity, Convey::setInventory, Convey::setMileage, Convey::setCost);

    private static final List<Function<Convey, Long>> GETTERS = Arrays.asList(
            Convey::getQuantity, Convey::getInventory, Convey::getMileage, Convey::getCost);

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
            List<Long> values = historyConvey.stream()
                    .map(GETTERS.get(i))
                    .collect(Collectors.toList());
            values.add(GETTERS.get(i).apply(nowConvey));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(predictedConvey, predictor.predict(nextTime));
        }

        predictedConvey.setEventTime(nextTime);
        predictedConvey.setUpdateTime(System.currentTimeMillis());

        return predictedConvey;
    }
}

