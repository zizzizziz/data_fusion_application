package ldn.cs.decision.alghthrims;

import java.util.List;

public class Predictor {
    private RegressionResult result;

    public void fit(List<Long> eventTimes, List<Double> values) {
        result = LinearRegression.fit(eventTimes, values);
    }

    public double predict(long nextEventTime) {
        return LinearRegression.predict(result, nextEventTime);
    }
}
