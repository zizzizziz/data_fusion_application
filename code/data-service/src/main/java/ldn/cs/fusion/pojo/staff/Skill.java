package ldn.cs.fusion.pojo.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private int id;

    private String corporation;

    private String skill;

    private long amount;

    private long eventTime;

    private long updateTime;

}
