package ldn.cs.optimize.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedThreshold {
    private Integer id;

    private String corporation;

    private String attribute;

    private double attributeValue;

    private int optimizationType;

    private long updateTime;
}
