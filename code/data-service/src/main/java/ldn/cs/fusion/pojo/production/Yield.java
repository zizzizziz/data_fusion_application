package ldn.cs.fusion.pojo.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Yield {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private long quantity;

    private long eventTime;

    private long updateTime;
}
