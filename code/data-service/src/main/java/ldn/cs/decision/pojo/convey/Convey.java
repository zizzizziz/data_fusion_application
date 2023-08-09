package ldn.cs.decision.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Convey {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private BigDecimal quantity;

    private BigDecimal inventory;

    private BigDecimal mileage;

    private BigDecimal cost;

    private long eventTime;

    private long updateTime;
}
