package ldn.cs.decision.dao;


import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.fusion.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class DecisionThresholdDaoTest extends BaseTest {
    @Autowired
    private DecisionThresholdDao decisionThresholdDao;

    @Test
    public void updateDecisionThreshold() {
    }

    @Test
    public void getDecisionThreshold() {
        List<DecisionThreshold> decisionThreshold = decisionThresholdDao.getDecisionThreshold(1);
        Assert.assertEquals(1, decisionThreshold.size());
    }
}