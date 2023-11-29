package ldn.cs.optimize.service.impl;

import ldn.cs.optimize.algorithms.OptimizationAlgorithm;
import ldn.cs.optimize.dao.*;
import ldn.cs.optimize.pojo.*;
import ldn.cs.optimize.service.OptimizedThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OptimizedThresholdServiceImpl implements OptimizedThresholdService {
    @Autowired
    private OptimizedThresholdDao thresholdDao;

    @Autowired
    private OptimizedConveyDao conveyDao;

    @Autowired
    private OptimizedCorporationDao corporationDao;

    @Autowired
    private OptimizedProductDao productDao;

    @Autowired
    private OptimizedSalesDetailDao salesDetailDao;

    @Autowired
    private OptimizedStaffDao staffDao;

    @Autowired
    private OptimizationAlgorithm optimizationAlgorithm;

    // 更新所有表格
    @Override
    public void refreshAllTables() {
        System.out.println("Refreshing all tables...");
        conveyDao.refreshConveyTable();
        corporationDao.refreshCorporationTable();
        productDao.refreshProductTable();
        salesDetailDao.refreshSalesDetailTable();
        staffDao.refreshStaffTable();
    }

    // 初始化所有公司表中公司的阈值
    // begin
    private List<OptimizedThreshold> createDefaultThresholdDataForCorporation(String corporation) {
        List<OptimizedThreshold> thresholds = new ArrayList<>();
        long currentTimeMillis = System.currentTimeMillis() / 1000; // 使用当前时间作为更新时间

        // 创建第一组阈值
        thresholds.add(new OptimizedThreshold(null, corporation, "人力费用", 0.25, 1, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "生产费用", 0.25, 1, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "销售费用", 0.25, 1, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "物流费用", 0.25, 1, currentTimeMillis));
        // 创建第二组阈值null,
        thresholds.add(new OptimizedThreshold(null, corporation, "人力费用", 0.33, 2, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "生产费用", 0.33, 2, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "销售费用", 0.34, 2, currentTimeMillis));
        // 创建第三组阈值null,
        thresholds.add(new OptimizedThreshold(null, corporation, "正常", 0.33, 3, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "良好", 0.33, 3, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "一般",0.34, 3, currentTimeMillis));
        // 创建第四组阈值null,
        thresholds.add(new OptimizedThreshold(null, corporation, "铁路运输",0.33, 4, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "公路运输",0.33, 4, currentTimeMillis));
        thresholds.add(new OptimizedThreshold(null, corporation, "水路运输",0.34, 4, currentTimeMillis));
        return thresholds;
    }

    @Override
    public void initThresholdForAllCorporations() {
        List<String> corporations = corporationDao.selectAllCorporations();
        for (String corporation : corporations) {
            List<OptimizedThreshold> data = createDefaultThresholdDataForCorporation(corporation);
            thresholdDao.replaceIntoThreshold(data);
        }
    }

    @Override
    public void initOptimizedThreshold() {
        if (thresholdDao.selectAll().isEmpty()) { // 检查阈值表是否为空
            thresholdDao.deleteAll(); // 删除所有阈值记录
            initThresholdForAllCorporations(); // 初始化所有公司的阈值
        }
    }

    // 根据公司名获取更新的阈值
    @Override
    public int updateThreshold(List<OptimizedThreshold> thresholds) {
        int updatedRecords = 0;
        for (OptimizedThreshold threshold : thresholds) {
            updatedRecords += thresholdDao.updateThreshold(threshold);
        }
        return updatedRecords;
    }

    //前台根据公司名属性类型更新阈值
    @Override
    public void retrieveAndUpdateThresholdsByCorporationAttributeAndType(List<OptimizedThreshold> newThresholds) {
        // 用于存储原始阈值的列表
        List<OptimizedThreshold> originalThresholds = new ArrayList<>();

        if (!isValidThresholdSum(newThresholds)) {
            throw new IllegalArgumentException("Thresholds sum is not valid!");
        }

        // 获取当前时间戳
        long timestamp = System.currentTimeMillis() / 1000;

        if (timestamp == 0) {
            timestamp = 1700550635L; // 默认时间戳值
        }

        // 遍历新的阈值列表
        for (OptimizedThreshold newThreshold : newThresholds) {
            // 设置新的时间戳
            newThreshold.setUpdateTime(timestamp);
            // 根据公司名，属性名和优化类型提取原始阈值列表
            List<OptimizedThreshold> retrievedThresholds = thresholdDao.selectThresholdsByCorporationAttributeAndType(newThreshold.getCorporation(), newThreshold.getAttribute(), newThreshold.getOptimizationType());
            // 将原始阈值存储到originalThresholds列表中
            originalThresholds.addAll(retrievedThresholds);

            // 使用新阈值更新数据库中的记录
            for (OptimizedThreshold retrievedThreshold : retrievedThresholds) {
                newThreshold.setId(retrievedThreshold.getId()); // 设置ID，假设ID是主键
                thresholdDao.updateThresholdByCorporationAttributeAndType(newThreshold);
            }
        }
        // 调用优化算法
        optimizationAlgorithm.performOptimization(newThresholds, originalThresholds);
        // 你可以将原始阈值存储在其他地方
    }


    //验证更新阈值总和是否为1
    private boolean isValidThresholdSum(List<OptimizedThreshold> thresholds) {
        Map<Integer, BigDecimal> optimizationTypeSums = new HashMap<>();
        for (OptimizedThreshold threshold : thresholds) {
            optimizationTypeSums.merge(threshold.getOptimizationType(), BigDecimal.valueOf(threshold.getAttributeValue()), BigDecimal::add);
        }
        return optimizationTypeSums.values().stream().allMatch(sum -> sum.subtract(BigDecimal.ONE).abs().compareTo(new BigDecimal("0.001")) < 0);
    }

    // 返回所有表组成的对象
    @Override
    public OptimizedCompanyData getAllCompanyData() {
        OptimizedCompanyData companyData = new OptimizedCompanyData();

        List<OptimizedThreshold> thresholdList = thresholdDao.selectAll();
        companyData.setThresholdMap(thresholdList.stream().collect(Collectors.groupingBy(OptimizedThreshold::getCorporation)));

        List<OptimizedConvey> conveyList = conveyDao.selectAll();
        companyData.setConveyMap(conveyList.stream().collect(Collectors.groupingBy(OptimizedConvey::getCorporation)));

        List<OptimizedCorporation> corporationList = corporationDao.selectAll();
        companyData.setCorporationMap(corporationList.stream().collect(Collectors.groupingBy(OptimizedCorporation::getCorporation)));

        List<OptimizedProduct> productList = productDao.selectAll();
        companyData.setProductMap(productList.stream().collect(Collectors.groupingBy(OptimizedProduct::getCorporation)));

        List<OptimizedSalesDetail> salesDetailList = salesDetailDao.selectAll();
        companyData.setSalesDetailMap(salesDetailList.stream().collect(Collectors.groupingBy(OptimizedSalesDetail::getCorporation)));

        List<OptimizedStaff> staffList = staffDao.selectAll();
        companyData.setStaffMap(staffList.stream().collect(Collectors.groupingBy(OptimizedStaff::getCorporation)));

        return companyData;
    }

    @Override
    public int insertThreshold(OptimizedThreshold threshold) {
        return thresholdDao.insert(threshold);
    }

    @Override
    public OptimizedThreshold selectThresholdById(Integer id) {
        return thresholdDao.selectById(id);
    }

    @Override
    public List<OptimizedThreshold> selectAllThresholds() {
        return thresholdDao.selectAll();
    }
}
