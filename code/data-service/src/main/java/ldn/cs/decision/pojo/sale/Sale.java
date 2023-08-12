package ldn.cs.decision.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private BigDecimal quantity;

    private BigDecimal income;

    private BigDecimal cost;

    private String province;

    private String country;

    private BigDecimal inventory;

    private int score;

    private long eventTime;

    private long updateTime;
}
