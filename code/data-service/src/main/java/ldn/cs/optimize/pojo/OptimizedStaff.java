package ldn.cs.optimize.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedStaff {
    private Integer id;

    private String corporation;

    private String skill;

    private double amount;

    private long updateTime;
}
