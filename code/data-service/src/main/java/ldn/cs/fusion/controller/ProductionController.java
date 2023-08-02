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

    /**
     * 数据融合 -- 生产链查询
     *
     * @param statement 查询条件
     * @param types     条件类型 ：1为按企业查询，2为按更新时间查询
     * @param limit     单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset    偏移量
     * @return 生产链报表数据ProductionInfo
     */
    @GetMapping("/fusion/query")
    public ProductionInfo query(String statement, int types, int limit, int offset) {
        return productionService.getProductionInfos(statement, types, limit, offset);
    }

    /**
     * 数据感知 -- 企业整体产量趋势可视化查询
     *
     * @param time 查询条件年份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/trend/query")
    public Map<String, List<Trend>> getTrendInfos(int time) {
        return productionService.getTrendInfos(time);
    }

    /**
     * 数据感知 -- 企业产量分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/yield/query")
    public Map<String, List<Yield>> getYieldInfos(long time, int granularity) {
        return productionService.getYieldInfos(time / 1000, granularity);
    }

    /**
     * 数据感知 -- 企业产地分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/birth/query")
    public Map<String, List<Birth>> getBirthInfos(long time, int granularity) {
        return productionService.getBirthInfos(time / 1000, granularity);
    }
}

