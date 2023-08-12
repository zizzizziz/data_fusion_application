package ldn.cs.fusion.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleTrend {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private BigDecimal quantity;

    private BigDecimal income;

    private int months;

    private long eventTime;

    private long updateTime;
}
