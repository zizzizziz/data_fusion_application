package ldn.cs.fusion.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {
    private int id;

    private String corporation;

    private int type;

    private long mileage;

    private long cost;

    private long eventTime;

    private long updateTime;
}
