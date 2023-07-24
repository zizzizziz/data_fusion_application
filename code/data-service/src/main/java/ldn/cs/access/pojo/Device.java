package ldn.cs.access.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private String id;      //IP地址
    private int port;       // 端口号
    private short status;   //机器状态
    private long updateTime;// 更新时间
}
