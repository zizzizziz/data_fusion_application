package ldn.cs.decision.alghthrims.staff;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.alghthrims.sale.SalePrediction;
import ldn.cs.decision.pojo.staff.Staff;


import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class StaffPrediction {
    private static final StaffPrediction instance = new StaffPrediction();

    private StaffPrediction() {
    }

    public static StaffPrediction getInstance() {
        return instance;
    }

    private static final List<BiConsumer<Staff, Long>> SETTERS = Collections.singletonList(
            Staff::setAmount);

    private static final List<Function<Staff, Long>> GETTERS = Collections.singletonList(
            Staff::getAmount);

    public Staff getNextStaff(List<Staff> historyStaff, Staff nowStaff, long nextTime) {
        List<Long> eventTimes = historyStaff.stream()
                .map(Staff::getEventTime)
                .collect(Collectors.toList());
        eventTimes.add(nowStaff.getEventTime());

        Staff predictedStaff = new Staff();
        predictedStaff.setCorporation(nowStaff.getCorporation());
        predictedStaff.setCategories(nowStaff.getCategories());
        predictedStaff.setPositions(nowStaff.getPositions());
        predictedStaff.setSkill(nowStaff.getSkill());

        for (int i = 0; i < GETTERS.size(); i++) {
            List<Long> values = historyStaff.stream()
                    .map(GETTERS.get(i))
                    .collect(Collectors.toList());
            values.add(GETTERS.get(i).apply(nowStaff));

            Predictor predictor = new Predictor();
            predictor.fit(eventTimes, values);

            SETTERS.get(i).accept(predictedStaff, predictor.predict(nextTime));
        }

        predictedStaff.setEventTime(nextTime);
        predictedStaff.setUpdateTime(System.currentTimeMillis());

        return predictedStaff;
    }
}

