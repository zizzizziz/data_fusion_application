package ldn.cs.decision.controller;

import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.service.DecisionThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/decision/element/threshold")
public class DecisionThresholdController {
    @Autowired
    private DecisionThresholdService decisionThresholdService;

    @GetMapping("/query")
    public List<DecisionThreshold> getDecisionThreshold(int categories, String attributes) {
        return decisionThresholdService.getDecisionThreshold(categories, attributes);
    }

    @PostMapping("/action/update")
    public int updateDecisionThreshold(@RequestBody List<DecisionThreshold> thresholds) {
        return decisionThresholdService.updateDecisionThreshold(thresholds);
    }
}
