package ldn.cs.decision.dao;

import ldn.cs.BaseTest;
import ldn.cs.decision.pojo.production.Production;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductionDecisionDaoTest extends BaseTest {
    @Autowired
    private ProductionDecisionDao productionDecisionDao;

    @Test
    public void addProductionPredictionInfos() {
        List<Production> production = new ArrayList<>();
        production.add(new Production(100, "比亚迪", 1, 3,  BigDecimal.valueOf(100), BigDecimal.valueOf(2000), "中国", "福建", 1, 1666945270, 1666945270));
        production.add(new Production(101, "比亚迪", 1, 3,  BigDecimal.valueOf(100), BigDecimal.valueOf(2000), "中国", "福建", 1, 1666945270, 1666945270));
        production.add(new Production(102, "比亚迪",  1, 3, BigDecimal.valueOf(100), BigDecimal.valueOf(2000), "中国", "福建", 1, 1666945270, 1666945270));

        int count = productionDecisionDao.addProductionPredictionInfos(production);
        Assert.assertEquals(3, count);
    }

    @Test
    public void getWealthPredictionInfos() {
        List<Production> getProductionInfoByYear = productionDecisionDao.getProductionPredictionInfos(1690946777L, 1, 10, 0);
        Assert.assertEquals(10, getProductionInfoByYear.size());

        List<Production> getProductionInfoByQuarter = productionDecisionDao.getProductionPredictionInfos(1690946777L, 2, 10, 0);
        Assert.assertEquals(10, getProductionInfoByQuarter.size());

        List<Production> getProductionInfoByMonth = productionDecisionDao.getProductionPredictionInfos(1690946777L, 2, 5, 0);
        Assert.assertEquals(5, getProductionInfoByMonth.size());
    }
}