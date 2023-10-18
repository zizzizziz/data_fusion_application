package ldn.cs.access.service;

import com.alibaba.fastjson.JSON;
import ldn.cs.access.dao.OriginalDao;
import ldn.cs.access.pojo.Original;
import ldn.cs.access.pojo.OriginalInfo;
import ldn.cs.fusion.common.DataAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OriginalService {

    @Autowired
    private OriginalDao originalDao;

    public void addOriginalInfos(Map<String, Object> fusionObject) {
        List<Original> originals = new ArrayList<>();
        try {
            Original original = new Original();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                original.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.staffCategories)) {
                original.setStaffCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.staffCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.positions)) {
                original.setPositions(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.positions))));
            }

            if (fusionObject.containsKey(DataAttribute.skill)) {
                original.setSkill(String.valueOf(fusionObject.get(DataAttribute.skill)));
            }

            if (fusionObject.containsKey(DataAttribute.amount)) {
                original.setAmount(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.amount))));
            }

            if (fusionObject.containsKey(DataAttribute.research)) {
                original.setResearch(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.research))));
            }

            if (fusionObject.containsKey(DataAttribute.device)) {
                original.setDevice(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.device))));
            }

            if (fusionObject.containsKey(DataAttribute.production)) {
                original.setProduction(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.production))));
            }

            if (fusionObject.containsKey(DataAttribute.storage)) {
                original.setStorage(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.storage))));
            }

            if (fusionObject.containsKey(DataAttribute.materiel)) {
                original.setMateriel(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.materiel))));
            }

            if (fusionObject.containsKey(DataAttribute.transportation)) {
                original.setTransportation(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.transportation))));
            }

            if (fusionObject.containsKey(DataAttribute.salary)) {
                original.setSalary(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.salary))));
            }

            if (fusionObject.containsKey(DataAttribute.revenue)) {
                original.setRevenue(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.revenue))));
            }

            if (fusionObject.containsKey(DataAttribute.profit)) {
                original.setProfit(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.profit))));
            }

            if (fusionObject.containsKey(DataAttribute.fixedAssets)) {
                original.setFixedAssets(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.fixedAssets))));
            }

            if (fusionObject.containsKey(DataAttribute.cashAssets)) {
                original.setCashAssets(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.cashAssets))));
            }

            if (fusionObject.containsKey(DataAttribute.finance)) {
                original.setFinance(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.finance))));
            }


            if (fusionObject.containsKey(DataAttribute.conveyCategories)) {
                original.setConveyCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.conveyCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyTypes)) {
                original.setConveyTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.conveyTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyQuantity)) {
                original.setConveyQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyInventory)) {
                original.setConveyInventory(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyInventory))));
            }

            if (fusionObject.containsKey(DataAttribute.mileage)) {
                original.setMileage(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.mileage))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyCost)) {
                original.setConveyCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyCost))));
            }

            if (fusionObject.containsKey(DataAttribute.productionCategories)) {
                original.setProductionCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.productionTypes)) {
                original.setProductionTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.productionQuantity)) {
                original.setProductionQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.productionCost)) {
                original.setProductionCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionCost))));
            }

            if (fusionObject.containsKey(DataAttribute.productionProvince)) {
                original.setProductionProvince(String.valueOf(fusionObject.get(DataAttribute.productionProvince)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCountry)) {
                original.setProductionCountry(String.valueOf(fusionObject.get(DataAttribute.productionCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.quality)) {
                original.setQuality(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.quality))));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                original.setSaleCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                original.setSaleTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.saleQuantity)) {
                original.setSaleQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.income)) {
                original.setIncome(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.income))));
            }

            if (fusionObject.containsKey(DataAttribute.saleCost)) {
                original.setSaleCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleCost))));
            }

            if (fusionObject.containsKey(DataAttribute.saleProvince)) {
                original.setSaleProvince(String.valueOf(fusionObject.get(DataAttribute.saleProvince)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCountry)) {
                original.setSaleCountry(String.valueOf(fusionObject.get(DataAttribute.saleCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.saleInventory)) {
                original.setSaleInventory(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleInventory))));
            }

            if (fusionObject.containsKey(DataAttribute.score)) {
                original.setScore(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.score))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                original.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            originals.add(original);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long updateTime = System.currentTimeMillis() / 1000;
        originals.forEach(original -> original.setUpdateTime(updateTime));
        originalDao.addOriginalInfos(originals);
    }

    public OriginalInfo getOriginal(int limit, int offset) {
        OriginalInfo originalInfo = new OriginalInfo();
        List<Original> originals = originalDao.getOriginal(limit, offset);
        int total = originalDao.getTotalOriginal();

        originalInfo.setOriginals(originals);
        originalInfo.setTotal(total);
        return originalInfo;
    }
}
