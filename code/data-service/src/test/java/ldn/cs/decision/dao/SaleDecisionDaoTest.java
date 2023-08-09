package ldn.cs.decision.dao;

import ldn.cs.BaseTest;
import ldn.cs.decision.pojo.sale.Sale;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class SaleDecisionDaoTest extends BaseTest {
    @Autowired
    private SaleDecisionDao saleDecisionDao;

//    @Test
//    public void addSalePredictionInfos() {
//        List<Sale> sale = new ArrayList<>();
//
//        sale.add(new Sale(1,"特斯拉", 1, 1, 100, 10000, 1000, "广东", "中国", 10, 95, 1688268377, 1690946779));
//        sale.add(new Sale(2,"特斯拉", 1, 1, 100, 10000, 1000, "广东", "中国", 10, 95, 1688268377, 1690946779));
//        sale.add(new Sale(3,"特斯拉", 1, 1, 100, 10000, 1000, "广东", "中国", 10, 95, 1688268377, 1690946779));
//
//        int count = saleDecisionDao.addSalePredictionInfos(sale);
//        Assert.assertEquals(3, count);
//    }
//
//    @Test
//    public void getSalePredictionInfos() {
//        List<Sale> getStaffInfoByYear = saleDecisionDao.getSalePredictionInfos(1690946777L, 1, 10, 0);
//        Assert.assertEquals(10, getStaffInfoByYear.size());
//
//        List<Sale> getStaffInfoByQuarter = saleDecisionDao.getSalePredictionInfos(1690946777L, 2, 10, 0);
//        Assert.assertEquals(10, getStaffInfoByQuarter.size());
//
//        List<Sale> getStaffInfoByMonth = saleDecisionDao.getSalePredictionInfos(1690946777L, 2, 5, 0);
//        Assert.assertEquals(5, getStaffInfoByMonth.size());
//    }
}