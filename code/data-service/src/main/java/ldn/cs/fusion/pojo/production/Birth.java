package ldn.cs.fusion.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Birth {
    private String corporation;

    private int categories;

    private int types;

    private BigDecimal quantity;

    private String province;

    private String country;

    private long eventTime;

    private long updateTime;
}
