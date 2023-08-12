package ldn.cs.fusion.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.AutomapConstructor;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trend {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private BigDecimal quantity;

    private int months;

    private long eventTime;

    private long updateTime;
}
