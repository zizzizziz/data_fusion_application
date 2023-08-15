package ldn.cs.decision.service;

import ldn.cs.decision.dao.DecisionThresholdDao;
import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DecisionThresholdService {
    @Autowired
    private DecisionThresholdDao decisionThresholdDao;

    public List<DecisionThreshold> getDecisionThreshold(int categories, String attributes) {
        return decisionThresholdDao.getDecisionThreshold(categories, attributes);
    }

    public int updateDecisionThreshold(List<DecisionThreshold> thresholds) {
        long updateTime = System.currentTimeMillis() / 1000;
        thresholds.forEach(req -> req.setUpdateTime(updateTime));
        return decisionThresholdDao.updateDecisionThreshold(thresholds);
    }
}
