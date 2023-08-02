package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.convey.*;
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

    /**
     * 数据融合 -- 物流链查询
     *
     * @param statement 查询条件
     * @param types     条件类型 ：1为按企业查询，2为按更新时间查询
     * @param limit     单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset    偏移量
     * @return 物流链报表数据ConveyInfo
     */
    @GetMapping("/fusion/query")
    public ConveyInfo query(String statement, int types, int limit, int offset) {
        return conveyService.getConveyInfos(statement, types, limit, offset);
    }

    /**
     * 数据感知 -- 企业运输类型分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/traffic/query")
    public Map<String, List<Traffic>> getTrafficInfo(long time, int granularity) {
        return conveyService.getTrafficInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业货物库存分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/inventory/query")
    public Map<String, List<Inventory>> getInventoryInfos(long time, int granularity) {
        return conveyService.getInventoryInfos(time, granularity);
    }
}
