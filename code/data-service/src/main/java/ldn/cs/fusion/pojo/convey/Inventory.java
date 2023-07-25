package ldn.cs.fusion.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private int id;

    private String corporation;

    private String goods;

    private long quantity;

    private long inventory;

    private long eventTime;

    private long updateTime;
}
