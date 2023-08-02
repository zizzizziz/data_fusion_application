package ldn.cs.fusion.pojo.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;

    private String corporation;

    private int categories;

    private long amount;

    private long eventTime;

    private long updateTime;
}