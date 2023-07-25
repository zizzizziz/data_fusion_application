package ldn.cs.fusion.pojo.wealth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    private int id;

    private String corporation;


    private long profit;

    private long fixedAssets;

    private long cashAssets;

    private long finance;

    private long eventTime;

    private long updateTime;
}
