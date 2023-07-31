package ldn.cs.fusion.pojo.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleCount {
    private String corporation;

    private int categories;

    private int types;

    private long quantity;

    private long eventTime;
}
