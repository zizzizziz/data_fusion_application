package ldn.cs.decision.enums;

public enum DecisionThresholdEnum {
    AMOUNT(1, "人员数量");

    private final int attributes;

    private final String value;

    DecisionThresholdEnum(int attributes, String value) {
        this.attributes = attributes;
        this.value = value;
    }

    public static String getThresholdAttributes(int attributes) {
        for (DecisionThresholdEnum mapping : DecisionThresholdEnum.values()) {
            if (mapping.attributes == attributes) {
                return mapping.value;
            }
        }
        return null; // or throw an exception
    }
}
