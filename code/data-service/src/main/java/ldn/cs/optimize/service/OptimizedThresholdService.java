package ldn.cs.optimize.service;

import ldn.cs.optimize.pojo.OptimizedCompanyData;
import ldn.cs.optimize.pojo.OptimizedThreshold;

import java.util.List;

public interface OptimizedThresholdService {
    int insertThreshold(OptimizedThreshold threshold);

    // 初始化所有公司的阈值
    void initThresholdForAllCorporations();

    void refreshAllTables ();

    void initOptimizedThreshold();

    OptimizedCompanyData getAllCompanyData();

    int updateThreshold(List<OptimizedThreshold> thresholds);

    OptimizedThreshold selectThresholdById(Integer id);

    List<OptimizedThreshold> selectAllThresholds();

    void retrieveAndUpdateThresholdsByCorporationAttributeAndType(List<OptimizedThreshold> thresholds);
}
