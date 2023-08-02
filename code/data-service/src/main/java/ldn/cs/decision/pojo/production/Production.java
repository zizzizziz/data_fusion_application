package ldn.cs.decision.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Production {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private long quantity;

    private long cost;

    private String province;

    private String country;

    private int quality;

    private long eventTime;

    private long updateTime;
}
