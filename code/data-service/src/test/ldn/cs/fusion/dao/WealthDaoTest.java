package ldn.cs.fusion.dao;

import ldn.cs.fusion.BaseTest;
import ldn.cs.fusion.pojo.wealth.Asset;
import ldn.cs.fusion.pojo.wealth.Finance;
import ldn.cs.fusion.pojo.wealth.Wealth;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class WealthDaoTest extends BaseTest {
    @Autowired
    private WealthDao wealthDao;

    @Test
    void addFinanceInfos() {
        Finance finance = new Finance();
        finance.setCorporation("test");
        finance.setEventTime(20231112001L);
        finance.setUpdateTime(202307172250L);
        List<Finance> finances = new ArrayList<>();
        finances.add(finance);

        int count = wealthDao.addFinanceInfos(finances);
        Assert.assertEquals(1, count);
    }

    @Test
    void addAssetInfos() {
        Asset asset = new Asset();
        asset.setCorporation("test");
        asset.setEventTime(20231112001L);
        asset.setUpdateTime(202307172250L);
        List<Asset> assets = new ArrayList<>();
        assets.add(asset);

        int count = wealthDao.addAssetInfos(assets);
        Assert.assertEquals(1, count);
    }

    @Test
    void addWealthInfos() {
        Wealth wealth = new Wealth();
        wealth.setCorporation("test");
        wealth.setEventTime(20231112001L);
        wealth.setUpdateTime(202307172250L);
        List<Wealth> wealths = new ArrayList<>();
        wealths.add(wealth);

        int count = wealthDao.addWealthInfos(wealths);
        Assert.assertEquals(1, count);
        System.out.println(wealthDao.getWealthInfos("test",1,5,0));

    }

    @Test
    public void getWealthInfos() {
        System.out.println(wealthDao.getWealthInfos("",1,2,0));
        System.out.println(wealthDao.getWealthInfos("比亚迪",1,2,0));
    }

    @Test
    void getTotalWealth() {
        int count1 = wealthDao.getTotalWealth("比亚迪", 1);
        System.out.println(count1);
        int count2 = wealthDao.getTotalWealth("", 2);
        System.out.println(count2);
    }

    @Test
    void getAssetInfos() {
        List<Asset> assets = wealthDao.getAssetInfos(2023,1);
        Map<String, List<Asset>> collect = assets.stream().collect(Collectors.groupingBy(Asset::getCorporation));
        System.out.println("");
    }

    @Test
    void getFinanceInfos() {
        List<Finance> finances = wealthDao.getFinanceInfos(2023,1);
        Map<String, List<Finance>> collect = finances.stream().collect(Collectors.groupingBy(Finance::getCorporation));
        System.out.println("");
    }
}