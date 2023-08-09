package ldn.cs.access.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private int id;

    private String ip;      //IP地址

    private int port;       // 端口号

    private long updateTime;// 更新时间

    public Device(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
