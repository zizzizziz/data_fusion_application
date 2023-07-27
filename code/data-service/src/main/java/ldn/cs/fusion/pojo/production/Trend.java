package ldn.cs.fusion.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trend {
    private int id;

    private String corporation;

    private long eventTime;

    private long updateTime;
}
