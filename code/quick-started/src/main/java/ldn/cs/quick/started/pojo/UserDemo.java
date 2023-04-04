package ldn.cs.quick.started.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDemo {
    private String userName;

    private int userId;

    private long updateTime;
}
