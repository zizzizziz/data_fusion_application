package ldn.cs.optimize.algorithms;

import ldn.cs.optimize.dao.*;
import ldn.cs.optimize.pojo.OptimizedThreshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Component
public class OptimizationAlgorithm {
    @Autowired
    private OptimizedConveyDao conveyDao;

    @Autowired
    private OptimizedProductDao productDao;

    @Autowired
    private OptimizedSalesDetailDao salesDetailDao;

    @Autowired
    private OptimizedStaffDao staffDao;

    public void performOptimization(List<OptimizedThreshold> newThresholds, List<OptimizedThreshold> originalThresholds) {
        if (newThresholds == null || newThresholds.isEmpty()) {
            System.out.println("No new thresholds to optimize.");
            return; // 或者其他适当的错误处理
        }

        // 确定优化类型
        int optimizationType = newThresholds.get(0).getOptimizationType();

        // 调用相应的优化子算法
        switch (optimizationType) {
            case 1:
                optimizeType1(newThresholds, originalThresholds);
                break;
            case 2:
                optimizeType2(newThresholds, originalThresholds);
                break;
            case 3:
                optimizeType3(newThresholds, originalThresholds);
                break;
            case 4:
                optimizeType4(newThresholds, originalThresholds);
                break;
        }
    }

    private void optimizeType1(List<OptimizedThreshold> newThresholds, List<OptimizedThreshold> originalThresholds) {
        for (OptimizedThreshold original : originalThresholds) {
            for (OptimizedThreshold updated : newThresholds) {
                if (original.getAttribute().equals(updated.getAttribute())) {
                    BigDecimal factor = BigDecimal.valueOf(updated.getAttributeValue()).divide(BigDecimal.valueOf(original.getAttributeValue()), 2, RoundingMode.HALF_UP);
                    Random rand = new Random();
                    factor = factor.add(BigDecimal.valueOf((rand.nextDouble() * 0.06) - 0.03)); // 添加随机震荡

                    System.out.println(factor);

                    switch (original.getAttribute()) {
                        case "人力费用":
                            staffDao.updateStaffAmount(factor.doubleValue()* 9 / 10, updated.getCorporation());
                            break;
                        case "生产费用":
                            productDao.updateProductCostAndQuantity(factor.multiply(BigDecimal.valueOf(0.9)), factor.multiply(BigDecimal.valueOf(0.9)), updated.getCorporation());
                            break;
                        case "销售费用":
                            salesDetailDao.updateSalesVolumeAndRevenue(factor.multiply(BigDecimal.valueOf(1.1)), factor.multiply(BigDecimal.valueOf(1.1)), updated.getCorporation());
                            salesDetailDao.updateSalesInventory(factor.multiply(BigDecimal.valueOf(0.9)), updated.getCorporation());
                            break;
                        case "物流费用":
                            conveyDao.updateConveyMethodAndCost((byte) 3, factor.multiply(BigDecimal.valueOf(0.5)), updated.getCorporation());
                            break;
                    }
                }
            }
        }
    }


    private void optimizeType2(List<OptimizedThreshold> newThresholds, List<OptimizedThreshold> originalThresholds) {
        for (OptimizedThreshold original : originalThresholds) {
            for (OptimizedThreshold updated : newThresholds) {
                if (original.getAttribute().equals(updated.getAttribute())) {
                    BigDecimal factor = BigDecimal.valueOf(updated.getAttributeValue()).divide(BigDecimal.valueOf(original.getAttributeValue()), 2, RoundingMode.HALF_UP);

                    Random rand = new Random();
                    factor = factor.add(BigDecimal.valueOf((rand.nextDouble() * 0.06) - 0.03));

                    switch (original.getAttribute()) {
                        case "人力费用":
                            staffDao.updateStaffAmount(factor.doubleValue()* 11 / 10, updated.getCorporation());
                            break;
                        case "生产费用":
                            productDao.updateProductCostAndQuantity(factor.multiply(BigDecimal.valueOf(1.1)), factor.multiply(BigDecimal.valueOf(1.1)), updated.getCorporation());
                            break;
                        case "销售费用":
                            salesDetailDao.updateSalesVolumeAndRevenue(factor.multiply(BigDecimal.valueOf(0.7)), factor.multiply(BigDecimal.valueOf(0.7)), updated.getCorporation());
                            salesDetailDao.updateSalesInventory(factor.multiply(BigDecimal.valueOf(1.2)), updated.getCorporation());
                            break;
                    }
                }
            }
        }
    }


    private void optimizeType3(List<OptimizedThreshold> newThresholds, List<OptimizedThreshold> originalThresholds) {
        for (OptimizedThreshold original : originalThresholds) {
            for (OptimizedThreshold updated : newThresholds) {
                if (original.getAttribute().equals(updated.getAttribute())) {
                    BigDecimal factor = BigDecimal.valueOf(updated.getAttributeValue()).divide(BigDecimal.valueOf(original.getAttributeValue()), 2, RoundingMode.HALF_UP);
                    Random rand = new Random();
                    factor = factor.add(BigDecimal.valueOf((rand.nextDouble() * 0.06) - 0.03));

                    switch (original.getAttribute()) {
                        case "正常":
                            staffDao.updateStaffAmount(factor.doubleValue()* 11 / 10, updated.getCorporation());
                            salesDetailDao.updateSalesVolumeAndRevenue(factor.multiply(BigDecimal.valueOf(1.2)), factor.multiply(BigDecimal.valueOf(1.2)), updated.getCorporation());
                            break;
                        case "退货产品":
                            staffDao.updateStaffAmount(factor.doubleValue()* 7 / 10, updated.getCorporation());
                            salesDetailDao.updateSalesVolumeAndRevenue(factor.multiply(BigDecimal.valueOf(0.7)), factor.multiply(BigDecimal.valueOf(0.7)), updated.getCorporation());
                            salesDetailDao.updateSalesInventory(factor.multiply(BigDecimal.valueOf(1.2)), updated.getCorporation());
                            break;
                        case "维修产品":
                            staffDao.updateStaffAmount(factor.doubleValue()* 8 / 10, updated.getCorporation());
                            salesDetailDao.updateSalesVolumeAndRevenue(factor.multiply(BigDecimal.valueOf(0.8)), factor.multiply(BigDecimal.valueOf(0.8)), updated.getCorporation());
                            salesDetailDao.updateSalesInventory(factor.multiply(BigDecimal.valueOf(1.1)), updated.getCorporation());
                            break;
                    }
                }
            }
        }
    }


    private void optimizeType4(List<OptimizedThreshold> newThresholds, List<OptimizedThreshold> originalThresholds) {
        for (OptimizedThreshold original : originalThresholds) {
            for (OptimizedThreshold updated : newThresholds) {
                if (original.getAttribute().equals(updated.getAttribute())) {
                    BigDecimal factor = BigDecimal.valueOf(updated.getAttributeValue()).divide(BigDecimal.valueOf(original.getAttributeValue()), 2, RoundingMode.HALF_UP);
                    Random rand = new Random();
                    factor = factor.add(BigDecimal.valueOf((rand.nextDouble() * 0.06) - 0.03));

                    switch (original.getAttribute()) {
                        case "航空运输":
                            conveyDao.updateConveyMethodAndCost((byte) 1, factor.multiply(BigDecimal.valueOf(1.3)), updated.getCorporation());
                            break;
                        case "铁路运输":
                            conveyDao.updateConveyMethodAndCost((byte) 2, factor, updated.getCorporation());
                            break;
                        case "公路运输":
                            conveyDao.updateConveyMethodAndCost((byte) 3, factor.multiply(BigDecimal.valueOf(0.8)), updated.getCorporation());
                            break;
                        case "水路运输":
                            conveyDao.updateConveyMethodAndCost((byte) 4, factor.multiply(BigDecimal.valueOf(0.5)), updated.getCorporation());
                            break;
                    }
                }
            }
        }
    }

}