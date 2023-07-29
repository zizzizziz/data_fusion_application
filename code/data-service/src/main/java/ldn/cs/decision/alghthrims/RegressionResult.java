package ldn.cs.decision.alghthrims;

public class RegressionResult {
    private final double intercept;
    private final double slope;

    public RegressionResult(double intercept, double slope) {
        this.intercept = intercept;
        this.slope = slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }
}
