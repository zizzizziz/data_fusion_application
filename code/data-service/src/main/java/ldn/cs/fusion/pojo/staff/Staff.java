package ldn.cs.fusion.pojo.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int id;

    private String corporation;

    private int categories;

    private int position;

    private String skill;

    private long eventTime;

    private long updateTime;
}