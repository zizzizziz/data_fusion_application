package ldn.cs.optimize.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedProduct {
    private Integer id;

    private String corporation;

    private int types;

    private BigDecimal cost;

    private String province;

    private BigDecimal quantity;

    private long updateTime;
}
