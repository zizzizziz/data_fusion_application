package ldn.cs.fusion.pojo.wealth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wealth {
    private int id;

    private String corporation;

    private long research;

    private long device;

    private long production;

    private long storage;

    private long materiel;

    private long transportation;

    private long salary;

    private long revenue;

    private long profit;

    private long fixedAssets;

    private long cashAssets;

    private long finance;

    private long eventTime;

    private long updateTime;
}
