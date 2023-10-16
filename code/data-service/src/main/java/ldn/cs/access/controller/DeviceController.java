package ldn.cs.access.controller;

import ldn.cs.access.pojo.Device;
import ldn.cs.access.pojo.DeviceInfo;
import ldn.cs.access.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/data/access/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/action/add")
    public int add(@RequestBody List<Device> devices){
        return deviceService.addDevices(devices);
    }

    @PostMapping("/action/delete")
    public int delete(@RequestBody List<Device> devices) {
        return deviceService.deleteDevices(devices);
    }
    @GetMapping("/query")
    public DeviceInfo query(String statement, int types, int limit, int offset) {
        return deviceService.getDeviceInfos(statement, types, limit, offset);
    }
}
