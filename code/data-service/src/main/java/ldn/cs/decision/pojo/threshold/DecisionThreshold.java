package ldn.cs.decision.pojo.threshold;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecisionThreshold {
    private int id;

    private int categories;

    private String attributes;

    private long attributesValue;

    private long updateTime;
}
