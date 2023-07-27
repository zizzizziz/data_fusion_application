package ldn.cs.fusion.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Export {
    private int id;

    private String corporation;

    private int categories;

    private int types;

    private long quantity;

    private String province;

    private String country;

    private long eventTime;

    private long updateTime;
}
