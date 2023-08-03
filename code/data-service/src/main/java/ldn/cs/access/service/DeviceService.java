package ldn.cs.access.service;

import ldn.cs.access.dao.DeviceDao;
import ldn.cs.access.kafaka.SocketClient;
import ldn.cs.access.pojo.Device;
import ldn.cs.access.pojo.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    /**
     * 数据接入 -- 设备数据新增
     *
     * @param devices 设备信息
     * @return 新增条数
     */
    public int addDeviceInfos(List<Device> devices) {
        long updateTime = System.currentTimeMillis();
        devices.forEach(device -> device.setUpdateTime(updateTime));
        // 1. 入库
        int count = deviceDao.addDevices(devices);
        // 2. 启动对应的Socket连接
        if (count != 0) {
            devices.forEach(req -> SocketClient.getInstance().connectAndListen(req.getIp(), req.getPort()));
        }

        return count;
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
            devices.forEach(req -> SocketClient.getInstance().closeConnection(req.getIp(), req.getPort()));
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
        return new DeviceInfo(deviceDao.getDevices(statement, types, limit, offset),
                deviceDao.getTotalDevices(statement, types));
    }
}
