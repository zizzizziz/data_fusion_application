package ldn.cs.decision.alghthrims;

import java.util.List;

public class LinearRegression {
    public static RegressionResult fit(List<Long> x, List<Double> y) {
        int n = x.size();
        if (n != y.size() || n < 1) {
            throw new IllegalArgumentException("Input data size mismatch or too small.");
        }

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXX = 0;

        for (int i = 0; i < n; i++) {
            long xi = x.get(i);
            double yi = y.get(i);
            sumX += xi;
            sumY += yi;
            sumXY += xi * yi;
            sumXX += xi * xi;
        }

        double meanX = sumX / n;
        double meanY = sumY / n;

        double slope = (sumXY - n * meanX * meanY) / (sumXX - n * meanX * meanX);
        double intercept = meanY - slope * meanX;

        return new RegressionResult(intercept, slope);
    }

    public static double predict(RegressionResult result, long x) {
        return Math.round(result.getIntercept() + result.getSlope() * x);
    }
}
