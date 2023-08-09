package ldn.cs.fusion.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Export {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private BigDecimal quantity;

    private String country;

    private long eventTime;

    private long updateTime;
}
