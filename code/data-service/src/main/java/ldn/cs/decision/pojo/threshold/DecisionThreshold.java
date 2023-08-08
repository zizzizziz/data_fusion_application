package ldn.cs.decision.pojo.threshold;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecisionThreshold {
    private int id;

    private int categories;

    private String attributes;

    private BigDecimal attributesValue;

    private long updateTime;
}
