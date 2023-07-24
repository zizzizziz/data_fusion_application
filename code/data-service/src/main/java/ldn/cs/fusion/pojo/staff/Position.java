package ldn.cs.fusion.pojo.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private int id;

    private String corporation;

    private int position;

    private long eventTime;

    private long updateTime;
}
