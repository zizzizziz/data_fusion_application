package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.sale.*;
import ldn.cs.fusion.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/data/element/sale")
public class SaleController {
    @Autowired
    SaleService saleService;

    @GetMapping("/fusion/query")
    public SaleInfo query(String statement, int types, int limit, int offset) {
        return saleService.getSaleInfos(statement, types, limit, offset);
    }

    @GetMapping("/perception/birth/query")
    public Map<String, List<SaleBirth>> getSaleBirthInfos(long time, int granularity) {
        return saleService.getSaleBirthInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/count/query")
    public Map<String, List<SaleCount>> getSaleCountInfos(long time, int granularity) {
        return saleService.getSaleCountInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/export/query")
    public Map<String, List<Export>> getExportInfos(long time, int granularity) {
        return saleService.getExportInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/trend/query")
    public Map<String, List<SaleTrend>> getSaleTrendInfos(int time) {
        return saleService.getSaleTrendInfos(time);
    }

    @GetMapping("/perception/profit/query")
    public Map<String, List<Profit>> getProfitInfos(long time, int granularity) {
        return saleService.getProfitInfos(time / 1000, granularity);
    }
}
