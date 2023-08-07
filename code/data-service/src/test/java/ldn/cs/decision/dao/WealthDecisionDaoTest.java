package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.wealth.Wealth;
import ldn.cs.fusion.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class WealthDecisionDaoTest extends BaseTest {
    @Autowired
    private WealthDecisionDao wealthDecisionDao;

    @Test
    public void addWealthPredictionInfos() {
        List<Wealth> wealth = new ArrayList<>();
        wealth.add(new Wealth(1, "比亚迪", 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1688268377, 1688268377));
        wealth.add(new Wealth(1, "比亚迪", 3000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1688268577, 1688268577));
        wealth.add(new Wealth(1, "比亚迪", 4000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1688268677, 1688268677));

        int count = wealthDecisionDao.addWealthPredictionInfos(wealth);
        Assert.assertEquals(3, count);
    }
}