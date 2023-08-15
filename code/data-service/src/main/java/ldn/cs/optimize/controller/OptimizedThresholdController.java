package ldn.cs.optimize.controller;

import ldn.cs.optimize.pojo.OptimizedCompanyData;
import ldn.cs.optimize.pojo.OptimizedThreshold;
import ldn.cs.optimize.service.OptimizedThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/optimized/element/threshold")
public class OptimizedThresholdController {
    @Autowired
    private OptimizedThresholdService optimizedThresholdService;

    // 刷新所有表,返回所有表集合的map，进入主页面
    @GetMapping("/query")
    public ResponseEntity<OptimizedCompanyData> getInitData() {
        optimizedThresholdService.refreshAllTables(); // 刷新所有表
        optimizedThresholdService.initOptimizedThreshold(); // 初始化,如果表为空初始化，有表则不初始化
        OptimizedCompanyData companyData = optimizedThresholdService.getAllCompanyData(); // 获取所有公司的数据
        return ResponseEntity.ok(companyData);
    }

    @PostMapping("/action/update")
    public ResponseEntity<OptimizedCompanyData> getUpdateData(@RequestBody List<OptimizedThreshold> thresholds) {
        optimizedThresholdService.refreshAllTables(); // 刷新所有表
        optimizedThresholdService.retrieveAndUpdateThresholdsByCorporationAttributeAndType(thresholds);
        OptimizedCompanyData companyData = optimizedThresholdService.getAllCompanyData(); // 获取所有公司的数据
        return ResponseEntity.ok(companyData);
    }
}
