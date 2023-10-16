package ldn.cs.access.service;

import ldn.cs.access.dao.DeviceDao;
import ldn.cs.access.Socket.SocketClient;
import ldn.cs.access.kafaka.KafkaTopicConfig;
import ldn.cs.access.pojo.Device;
import ldn.cs.access.pojo.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private SocketClient socketClient;


    /**
     * 数据接入 -- 设备数据新增
     *
     * @param devices 设备信息
     * @return 成功返回新增条数，失败则返回-1
     */
    public int addDevices(List<Device> devices) {
        devices.forEach(req -> socketClient.connectAndListen(req.getIp(), req.getPort()));

        Map<String, Socket> ipPorts = socketClient.getIpPort();
        List<Device> successDevices = new ArrayList<>();

        for (String ipPort : ipPorts.keySet()) {
            String[] parts = ipPort.split(":");
            if (parts.length != 2) {
                // 处理无效的 ipPort 格式
                continue;
            }

            String ip = parts[0];
            int port;
            try {
                port = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                continue;
            }

            successDevices.add(new Device(ip, port));
        }
        long updateTime = System.currentTimeMillis() / 1000;
        successDevices.forEach(device -> device.setUpdateTime(updateTime));

        return !successDevices.isEmpty() ? deviceDao.addDevices(successDevices) : -1;
    }

    /**
     * 数据接入 -- 删除设备数据
     *
     * @param devices 设备信息
     * @return 删除条数
     */
    public int deleteDevices(List<Device> devices) {
        // 1. 删除数据库中对应数据
        int count = deviceDao.deleteDevices(devices);
        // 2. 关闭对应的Socket连接
        if (count != 0) {
            devices.forEach(req -> socketClient.closeConnection(req.getIp(), req.getPort()));
        }
        return count;
    }

    /**
     * 数据接入 -- 设备查询
     *
     * @param statement 查询条件
     * @param types     条件类型 ：1-IP地址，2-端口号
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 设备信息列表及设备总数
     */
    public DeviceInfo getDeviceInfos(String statement, int types, int limit, int offset) {
        DeviceInfo deviceInfo = new DeviceInfo();
        List<Device> devices = deviceDao.getDevices(statement, types, limit, offset);
        int total = deviceDao.getTotalDevices(statement, types);

        deviceInfo.setDevices(devices);
        deviceInfo.setTotal(total);
        return deviceInfo;
    }
}
