package ldn.cs.access.controller;

import ldn.cs.access.pojo.Device;
import ldn.cs.access.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ldn/cs/element")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/getDevices")
    public Device getDevices(){
        return deviceService.getDevices();
    }
}
