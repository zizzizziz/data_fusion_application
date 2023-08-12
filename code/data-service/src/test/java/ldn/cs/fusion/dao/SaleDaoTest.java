package ldn.cs.fusion.dao;

import ldn.cs.BaseTest;
import ldn.cs.fusion.pojo.sale.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleDaoTest extends BaseTest{
    @Autowired
    private SaleDao saleDao;

    @Test
    public void getSaleInfos() {
        System.out.println(saleDao.getSaleInfos("",1,5,0));
    }

    @Test
    public void getTotalSale() {
        int count1 = saleDao.getTotalSale("比亚迪", 1);
        System.out.println(count1);
        int count2 = saleDao.getTotalSale("202307172250", 2);
        System.out.println(count2);
    }

    @Test
    public void getExportInfos() {
        List<Export> exports = saleDao.getExportInfos(1640966400L,2);//2022-01-01 00:00:00
        Map<String, List<Export>> collect = exports.stream().collect(Collectors.groupingBy(Export::getCorporation));
        System.out.println(collect);
    }

    @Test
    public void getProfitInfos() {
        List<Profit> profits = saleDao.getProfitInfos(1690185315L,2);//2022-01-01 00:00:00
        Map<String, List<Profit>> collect = profits.stream().collect(Collectors.groupingBy(Profit::getCorporation));
        System.out.println(collect);
    }

    @Test
    public void getSaleCountInfos() {
        List<SaleCount> saleCounts = saleDao.getSaleCountInfos(1640966400L,2);//2022-01-01 00:00:00
        Map<String, List<SaleCount>> collect = saleCounts.stream().collect(Collectors.groupingBy(SaleCount::getCorporation));
        System.out.println(collect);
    }

    @Test
    public void getSaleTrendInfos() {
        List<SaleTrend> saleTrends = saleDao.getSaleTrendInfos(2022);//2022-01-01 00:00:00
        Map<String, List<SaleTrend>> collect = saleTrends.stream().collect(Collectors.groupingBy(SaleTrend::getCorporation));
        System.out.println(collect);
    }

    @Test
    public void getSaleBirthInfos() {
        List<SaleBirth> saleBirths = saleDao.getSaleBirthInfos(1640966400L,2);//2022-01-01 00:00:00
        Map<String, List<SaleBirth>> collect = saleBirths.stream().collect(Collectors.groupingBy(SaleBirth::getCorporation));
        System.out.println(collect);
    }
}