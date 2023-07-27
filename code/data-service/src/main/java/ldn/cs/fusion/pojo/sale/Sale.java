package ldn.cs.fusion.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private long quantity;

    private long income;

    private long cost;

    private String province;

    private String country;

    private long inventory;

    private int score;

    private long eventTime;

    private long updateTime;
}
