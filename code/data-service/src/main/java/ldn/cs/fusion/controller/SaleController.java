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

    /**
     * 数据融合 -- 销售链查询
     *
     * @param statement 查询条件
     * @param types     条件类型 ：1为按企业查询，2为按更新时间查询
     * @param limit     单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset    偏移量
     * @return 销售链报表数据 SaleInfo
     */
    @GetMapping("/fusion/query")
    public SaleInfo query(String statement, int types, int limit, int offset) {
        return saleService.getSaleInfos(statement, types, limit, offset);
    }

    /**
     * 数据感知 -- 企业整体销量趋势可视化查询
     *
     * @param time 查询条件年份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/trend/query")
    public Map<String, List<SaleTrend>> getSaleTrendInfos(int time) {
        return saleService.getSaleTrendInfos(time);
    }

    /**
     * 数据感知 -- 企业销量分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/count/query")
    public Map<String, List<SaleCount>> getSaleCountInfos(long time, int granularity) {
        return saleService.getSaleCountInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业销地分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/birth/query")
    public Map<String, List<SaleBirth>> getSaleBirthInfos(long time, int granularity) {
        return saleService.getSaleBirthInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业出口销量分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/export/query")
    public Map<String, List<Export>> getExportInfos(long time, int granularity) {
        return saleService.getExportInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业销售收入分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/profit/query")
    public Map<String, List<Profit>> getProfitInfos(long time, int granularity) {
        return saleService.getProfitInfos(time, granularity);
    }
}
