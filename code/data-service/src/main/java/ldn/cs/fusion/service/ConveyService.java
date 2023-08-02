package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.ConveyDao;
import ldn.cs.fusion.pojo.convey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConveyService {
    @Autowired
    private ConveyDao conveyDao;

    /**
     * 数据融合 -- 物流链数据新增
     *
     * @param conveys 物流链信息
     * @return 新增条数
     */
    public int addConveyInfos(List<Convey> conveys) {
        long updateTime = System.currentTimeMillis();
        conveys.forEach(convey -> convey.setUpdateTime(updateTime));
        return conveyDao.addConveyInfos(conveys);
    }

    /**
     * 数据融合 -- 物流链查询
     *
     * @param statement 查询条件
     * @param types     条件类型
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 物流链信息
     */
    public ConveyInfo getConveyInfos(String statement, int types, int limit, int offset) {
        ConveyInfo conveyInfo = new ConveyInfo();
        List<Convey> conveys = conveyDao.getConveyInfos(statement, types, limit, offset);
        int total = conveyDao.getTotalConvey(statement, types);

        conveyInfo.setConveys(conveys);
        conveyInfo.setTotal(total);
        return conveyInfo;
    }

    /**
     * 数据感知 -- 企业运输类型分布数据新增
     *
     * @param traffics 企业运输类型分布信息
     * @return 新增条数
     */
    public int addTrafficInfos(List<Traffic> traffics) {
        return conveyDao.addTrafficInfos(traffics);
    }

    /**
     * 数据感知 -- 企业运输类型分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Traffic>> getTrafficInfos(long time, int granularity) {
        return conveyDao.getTrafficInfos(time, granularity).stream().collect(Collectors.groupingBy(Traffic::getCorporation));
    }

    /**
     * 数据感知 -- 企业货物库存分布数据新增
     *
     * @param inventories 企业货物库存分布信息
     * @return 新增条数
     */
    public int addInventoryInfos(List<Inventory> inventories) {
        return conveyDao.addInventoryInfos(inventories);
    }

    /**
     * 数据感知 -- 企业货物库存分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Inventory>> getInventoryInfos(long time, int granularity) {
        return conveyDao.getInventoryInfos(time, granularity).stream().collect(Collectors.groupingBy(Inventory::getCorporation));
    }
}
