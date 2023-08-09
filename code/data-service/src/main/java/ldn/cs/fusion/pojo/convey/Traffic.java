package ldn.cs.fusion.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {
    private String corporation;

    private int categories;

    private BigDecimal mileage;

    private BigDecimal cost;

    private long eventTime;

    private long updateTime;
}
