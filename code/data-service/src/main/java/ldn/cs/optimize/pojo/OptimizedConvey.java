package ldn.cs.optimize.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedConvey {
    private Integer id;

    private String corporation;

    private int types;

    private int categories;

    private BigDecimal cost;

    private BigDecimal transportVolume;

    private long updateTime;
}
