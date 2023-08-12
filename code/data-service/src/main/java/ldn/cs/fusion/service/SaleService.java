package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.SaleDao;
import ldn.cs.fusion.pojo.production.Trend;
import ldn.cs.fusion.pojo.sale.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleDao saleDao;

    /**
     * 数据融合 -- 销售链数据新增
     *
     * @param sales 销售链信息
     * @return 新增条数
     */
    public int addSaleInfos(List<Sale> sales) {
        long updateTime = System.currentTimeMillis() / 1000;
        sales.forEach(sale -> sale.setUpdateTime(updateTime));
        return saleDao.addSaleInfos(sales);
    }

    /**
     * 数据融合 -- 销售链查询
     *
     * @param statement 查询条件
     * @param types     条件类型
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 销售链信息
     */
    public SaleInfo getSaleInfos(String statement, int types, int limit, int offset) {
        SaleInfo saleInfo = new SaleInfo();
        List<Sale> sales = saleDao.getSaleInfos(statement, types, limit, offset);
        int total = saleDao.getTotalSale(statement, types);

        saleInfo.setSales(sales);
        saleInfo.setTotal(total);
        return saleInfo;
    }

    /**
     * 数据感知 -- 企业整体销量趋势数据新增
     *
     * @param saleTrends 企业整体销量信息
     * @return 新增条数
     */
    public int addSaleTrendInfos(List<SaleTrend> saleTrends) {
        long updateTime = System.currentTimeMillis() / 1000;
        saleTrends.forEach(saleTrend -> saleTrend.setUpdateTime(updateTime));
        return saleDao.addSaleTrendInfos(saleTrends);
    }

    /**
     * 数据感知 -- 企业整体销量趋势可视化查询功能
     *
     * @param time 查询条件时间年份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<SaleTrend>> getSaleTrendInfos(int time) {
        return saleDao.getSaleTrendInfos(time).stream().collect(Collectors.groupingBy(SaleTrend::getCorporation));
    }

    /**
     * 数据感知 -- 企业销量分布数据新增
     *
     * @param saleCounts 企业销量分布信息
     * @return 新增条数
     */
    public int addSaleCountInfos(List<SaleCount> saleCounts) {
        long updateTime = System.currentTimeMillis() / 1000;
        saleCounts.forEach(saleCount -> saleCount.setUpdateTime(updateTime));
        return saleDao.addSaleCountInfos(saleCounts);
    }

    /**
     * 数据感知 -- 企业销量分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<SaleCount>> getSaleCountInfos(long time, int granularity) {
        return saleDao.getSaleCountInfos(time, granularity).stream().collect(Collectors.groupingBy(SaleCount::getCorporation));
    }

    /**
     * 数据感知 -- 企业销地分布数据新增
     *
     * @param saleBirths 企业销地分布信息
     * @return 新增条数
     */
    public int addSaleBirthInfos(List<SaleBirth> saleBirths) {
        long updateTime = System.currentTimeMillis() / 1000;
        saleBirths.forEach(saleBirth -> saleBirth.setUpdateTime(updateTime));
        return saleDao.addSaleBirthInfos(saleBirths);
    }

    /**
     * 数据感知 -- 企业销地分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<SaleBirth>> getSaleBirthInfos(long time, int granularity) {
        return saleDao.getSaleBirthInfos(time, granularity).stream().collect(Collectors.groupingBy(SaleBirth::getCorporation));
    }

    /**
     * 数据感知 -- 企业出口分布数据新增
     *
     * @param exports 企业出口分布信息
     * @return 新增条数
     */
    public int addExportInfos(List<Export> exports) {
        long updateTime = System.currentTimeMillis() / 1000;
        exports.forEach(export -> export.setUpdateTime(updateTime));
        return saleDao.addExportInfos(exports);
    }

    /**
     * 数据感知 -- 企业出口分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Export>> getExportInfos(long time, int granularity) {
        return saleDao.getExportInfos(time, granularity).stream().collect(Collectors.groupingBy(Export::getCorporation));
    }

    /**
     * 数据感知 -- 企业销售收入分布数据新增
     *
     * @param profits 企业销售收入分布信息
     * @return 新增条数
     */
    public int addProfitInfos(List<Profit> profits) {
        long updateTime = System.currentTimeMillis() / 1000;
        profits.forEach(profit -> profit.setUpdateTime(updateTime));
        return saleDao.addProfitInfos(profits);
    }

    /**
     * 数据感知 -- 企业销售收入分布可视化查询功能
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Profit>> getProfitInfos(long time, int granularity) {
        return saleDao.getProfitInfos(time, granularity).stream().collect(Collectors.groupingBy(Profit::getCorporation));
    }
}
