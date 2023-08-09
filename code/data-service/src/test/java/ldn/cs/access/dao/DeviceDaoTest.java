package ldn.cs.access.dao;

import ldn.cs.BaseTest;
import ldn.cs.access.pojo.Device;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class DeviceDaoTest extends BaseTest {
    @Autowired
    private DeviceDao deviceDao;

    @Test
    public void addDevices() {
        Device device = new Device();
        device.setIp("192.168.110.4");
        device.setPort(8882);
        List<Device> devices = new ArrayList<>();
        devices.add(device);
        deviceDao.addDevices(devices);
    }

    @Test
    public void deleteDevices() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(100, "192.168.110.4", 8882, System.currentTimeMillis() / 1000));
        devices.add(new Device(101, "192.168.110.5", 8882, System.currentTimeMillis() / 1000));
        devices.add(new Device(102, "192.168.110.5", 8883, System.currentTimeMillis() / 1000));
        devices.add(new Device(103, "192.168.110.5", 8884, System.currentTimeMillis() / 1000));
        devices.add(new Device(104, "192.168.110.5", 8885, System.currentTimeMillis() / 1000));

        int insertCount = deviceDao.addDevices(devices);
        System.out.println(insertCount);
        int deleteCount = deviceDao.deleteDevices(devices);
        System.out.println(deleteCount);
    }

    @Test
    public void getDevices() {
        System.out.println(deviceDao.getDevices("", 1, 2, 0));
    }

    @Test
    public void getTotalDevices() {
    }
}