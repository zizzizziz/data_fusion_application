package ldn.cs.fusion.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private int id;

    private String corporation;

    private int types;

    private BigDecimal quantity;

    private BigDecimal inventory;

    private long eventTime;

    private long updateTime;
}
