package ldn.cs.decision.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Convey {
    private int id;

    private String corporation;

    private int types;

    private String goods;

    private long quantity;

    private long inventory;

    private long mileage;

    private long cost;

    private long eventTime;

    private long updateTime;
}
