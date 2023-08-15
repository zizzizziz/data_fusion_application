package ldn.cs.optimize.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedSalesDetail {
    private Integer id;

    private String corporation;

    private int types;

    private BigDecimal quantity;

    private BigDecimal income;

    private String province;

    private BigDecimal inventory;

    private long updateTime;
}
