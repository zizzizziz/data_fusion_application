package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.production.Birth;
import ldn.cs.fusion.pojo.production.ProductionInfo;
import ldn.cs.fusion.pojo.production.Trend;
import ldn.cs.fusion.pojo.production.Yield;
import ldn.cs.fusion.pojo.sale.*;
import ldn.cs.fusion.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/data/element/production")
public class ProductionController {
    @Autowired
    ProductionService productionService;

    @GetMapping("/fusion/query")
    public ProductionInfo query(String statement, int types, int limit, int offset) {
        return productionService.getProductionInfos(statement, types, limit, offset);
    }

    @GetMapping("/perception/birth/query")
    public Map<String, List<Birth>> getBirthInfos(long time, int granularity) {
        return productionService.getBirthInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/yield/query")
    public Map<String, List<Yield>> getYieldInfos(long time, int granularity) {
        return productionService.getYieldInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/trend/query")
    public Map<String, List<Trend>> getTrendInfos(int time) {
        return productionService.getTrendInfos(time);
    }
}
