package ldn.cs.decision.dao;

import ldn.cs.BaseTest;
import ldn.cs.decision.pojo.convey.Convey;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ConveyDecisionDaoTest extends BaseTest {
    @Autowired
    private ConveyDecisionDao conveyDecisionDao;

    @Test
    public void addConveyPredictionInfos() {
        List<Convey> convey = new ArrayList<>();
        convey.add(new Convey(1, "小丫家电", 1, 1, BigDecimal.valueOf(200), BigDecimal.valueOf(500), BigDecimal.valueOf(1000), BigDecimal.valueOf(50000), 1666945270, 1666945270));
        convey.add(new Convey(2, "小丫家电", 1, 1, BigDecimal.valueOf(200), BigDecimal.valueOf(500), BigDecimal.valueOf(1000), BigDecimal.valueOf(50000), 1666945270, 1666945270));
        convey.add(new Convey(3, "小丫家电", 1, 1, BigDecimal.valueOf(200), BigDecimal.valueOf(500), BigDecimal.valueOf(1000), BigDecimal.valueOf(50000), 1666945270, 1666945270));

        int count = conveyDecisionDao.addConveyPredictionInfos(convey);
        Assert.assertEquals(3, count);
    }

    @Test
    public void getConveyPredictionInfos() {
        List<Convey> getConveyInfoByYear = conveyDecisionDao.getConveyPredictionInfos(1690946777L, 1, 10, 0);
        Assert.assertEquals(10, getConveyInfoByYear.size());

        List<Convey> getConveyInfoByQuarter = conveyDecisionDao.getConveyPredictionInfos(1690946777L, 2, 10, 0);
        Assert.assertEquals(10, getConveyInfoByQuarter.size());

        List<Convey> getConveyInfoByMonth = conveyDecisionDao.getConveyPredictionInfos(1690946777L, 2, 5, 0);
        Assert.assertEquals(5, getConveyInfoByMonth.size());
    }
}