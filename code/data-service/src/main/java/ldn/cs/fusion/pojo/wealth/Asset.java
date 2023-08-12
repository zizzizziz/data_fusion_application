package ldn.cs.fusion.pojo.wealth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    private int id;

    private String corporation;

    private BigDecimal profit;

    private BigDecimal fixedAssets;

    private BigDecimal cashAssets;

    private BigDecimal finance;

    private long eventTime;

    private long updateTime;
}
