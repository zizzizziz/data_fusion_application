package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.convey.ConveyInfo;
import ldn.cs.fusion.pojo.convey.Inventory;
import ldn.cs.fusion.pojo.convey.Traffic;
import ldn.cs.fusion.service.ConveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/data/element/convey")
public class ConveyController {
    @Autowired
    ConveyService conveyService;

    @GetMapping("/fusion/query")
    public ConveyInfo query(String statement, int types, int limit, int offset) {
        return conveyService.getConveyInfos(statement, types, limit, offset);
    }

    @GetMapping("/perception/traffic/query")
    public Map<String, List<Traffic>> getTrafficInfo(long time, int granularity) {
        return conveyService.getTrafficInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/inventory/query")
    public Map<String, List<Inventory>> getInventoryInfos(long time, int granularity) {
        return conveyService.getInventoryInfos(time / 1000, granularity);
    }
}
