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

    /**
     * 数据融合 -- 财务链查询
     *
     * @param statement 查询条件
     * @param types     条件类型 ：1为按企业查询，2为按更新时间查询
     * @param limit     单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset    偏移量
     * @return 财务链报表数据 WealthInfo
     */
    @GetMapping("/fusion/query")
    public WealthInfo query(String statement, int types, int limit, int offset) {
        return wealthService.getWealthInfos(statement, types, limit, offset);
    }

    /**
     * 数据感知 -- 企业内部资产分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/asset/query")
    public Map<String, List<Asset>> getAssetInfos(long time, int granularity) {
        return wealthService.getAssetInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业财务分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/finance/query")
    public Map<String, List<Finance>> getFinanceInfos(long time, int granularity) {
        return wealthService.getFinanceInfos(time, granularity);
    }
}
