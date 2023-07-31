package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.wealth.*;
import ldn.cs.fusion.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/data/element/wealth")
public class WealthController {
    @Autowired
    WealthService wealthService;

    @GetMapping("/fusion/query")
    public WealthInfo query(String statement, int types, int limit, int offset) {
        return wealthService.getWealthInfos(statement, types, limit, offset);
    }

    @GetMapping("/perception/finance/query")
    public Map<String, List<Finance>> getFinanceInfos(long time, int granularity) {
        return wealthService.getFinanceInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/asset/query")
    public Map<String, List<Asset>> getAssetInfos(long time, int granularity) {
        return wealthService.getAssetInfos(time / 1000, granularity);
    }
}
