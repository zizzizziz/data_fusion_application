package ldn.cs.access.service;

import ldn.cs.access.dao.DeviceMapper;
import ldn.cs.access.pojo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public Device getDevices(){
        return deviceMapper.getDevices();
    }
}
