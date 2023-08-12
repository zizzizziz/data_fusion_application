package ldn.cs.fusion.dao;

import ldn.cs.BaseTest;
import ldn.cs.fusion.pojo.production.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductionDaoTest extends BaseTest {
    @Autowired
    private ProductionDao productionDao;

    @Test
    public void addBirthInfos() {
        Birth birth = new Birth();
        birth.setCorporation("test");
        birth.setTypes(1);
        birth.setEventTime(20231112001L);
        List<Birth> births = new ArrayList<>();
        births.add(birth);

        int count = productionDao.addBirthInfos(births);
        Assert.assertEquals(1, count);
    }

    @Test
    public void addYieldInfos() {
        Yield yield = new Yield();
        yield.setCorporation("test");
        yield.setTypes(1);
        yield.setEventTime(20231112001L);
        List<Yield> yields = new ArrayList<>();
        yields.add(yield);

        int count = productionDao.addYieldInfos(yields);
        Assert.assertEquals(1, count);
    }

    @Test
    public void addTrendInfos() {
        Trend trend = new Trend();
        trend.setCorporation("test");
        trend.setEventTime(20231112001L);
        List<Trend> trends = new ArrayList<>();
        trends.add(trend);

        int count = productionDao.addTrendInfos(trends);
        Assert.assertEquals(1, count);
    }

    @Test
    public void addProductionInfos() {
        Production production = new Production();
        production.setCorporation("test");
        production.setTypes(1);
        production.setEventTime(20231112001L);
        production.setUpdateTime(202307172250L);
        List<Production> productions = new ArrayList<>();
        productions.add(production);

        int count = productionDao.addProductionInfos(productions);
        Assert.assertEquals(1, count);
    }

    @Test
    public void getProductionInfos() {
        System.out.println(productionDao.getProductionInfos("",1,5,0));
    }

    @Test
    public void getTotalProduction() {
        int count1 = productionDao.getTotalProduction("比亚迪", 1);
        System.out.println(count1);
        int count2 = productionDao.getTotalProduction("202307172250", 2);
        System.out.println(count2);
    }

    @Test
    public void getTrendInfos() {
        List<Trend> trends = productionDao.getTrendInfos(2022);//2022-01-01 00:00:00
        Map<String, List<Trend>> collect = trends.stream().collect(Collectors.groupingBy(Trend::getCorporation));
        System.out.println(collect);
    }

    @Test
    public void getBirthInfos() {
        List<Birth> births = productionDao.getBirthInfos(1640966400L,2);//2022-01-01 00:00:00
        Map<String, List<Birth>> collect = births.stream().collect(Collectors.groupingBy(Birth::getCorporation));
        System.out.println(collect);
    }

    @Test
    public void getYieldInfos() {
        List<Yield> yields = productionDao.getYieldInfos(1640966400L,2);//2022-01-01 00:00:00
        Map<String, List<Yield>> collect = yields.stream().collect(Collectors.groupingBy(Yield::getCorporation));
        System.out.println(collect);
    }
}