package ldn.cs.fusion.pojo.convey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {
    private String corporation;

    private int types;

    private long mileage;

    private long cost;

    private long eventTime;

}
