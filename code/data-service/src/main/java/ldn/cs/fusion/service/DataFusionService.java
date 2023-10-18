package ldn.cs.fusion.service;

import com.alibaba.fastjson.JSON;
import ldn.cs.access.kafaka.KafkaProducer;
import ldn.cs.fusion.common.DataAttribute;
import ldn.cs.fusion.dao.*;
import ldn.cs.fusion.pojo.convey.Convey;
import ldn.cs.fusion.pojo.production.Production;
import ldn.cs.fusion.pojo.sale.Sale;
import ldn.cs.fusion.pojo.staff.Staff;
import ldn.cs.fusion.pojo.wealth.Wealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据融合提取
 */
@Service
public class DataFusionService {
    @Autowired
    private StaffService staffService;

    @Autowired
    private WealthService wealthService;

    @Autowired
    private ConveyService conveyService;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private KafkaProducer producer;

    public void StaffFusion(Map<String, Object> fusionObject) {
        List<Staff> staffs = new ArrayList<>();

        try {
            Staff staff = new Staff();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                staff.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.staffCategories)) {
                staff.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.staffCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.positions)) {
                staff.setPositions(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.positions))));
            }

            if (fusionObject.containsKey(DataAttribute.skill)) {
                staff.setSkill(String.valueOf(fusionObject.get(DataAttribute.skill)));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                staff.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            if (fusionObject.containsKey(DataAttribute.amount)) {
                staff.setAmount(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.amount))));
            }

            staffs.add(staff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        staffService.addStaffInfos(staffs);
        staffs.forEach(req -> producer. sendMessage("topic_staff_message", JSON.toJSONString(req)));
    }

    public void WealthFusion(Map<String, Object> fusionObject) {
        List<Wealth> wealths = new ArrayList<>();
        try {
            Wealth wealth = new Wealth();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                wealth.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.research)) {
                wealth.setResearch(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.research))));
            }

            if (fusionObject.containsKey(DataAttribute.device)) {
                wealth.setDevice(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.device))));
            }

            if (fusionObject.containsKey(DataAttribute.production)) {
                wealth.setProduction(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.production))));
            }

            if (fusionObject.containsKey(DataAttribute.storage)) {
                wealth.setStorage(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.storage))));
            }

            if (fusionObject.containsKey(DataAttribute.materiel)) {
                wealth.setMateriel(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.materiel))));
            }

            if (fusionObject.containsKey(DataAttribute.transportation)) {
                wealth.setTransportation(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.transportation))));
            }

            if (fusionObject.containsKey(DataAttribute.salary)) {
                wealth.setSalary(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.salary))));
            }

            if (fusionObject.containsKey(DataAttribute.revenue)) {
                wealth.setRevenue(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.revenue))));
            }

            if (fusionObject.containsKey(DataAttribute.profit)) {
                wealth.setProfit(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.profit))));
            }

            if (fusionObject.containsKey(DataAttribute.fixedAssets)) {
                wealth.setFixedAssets(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.fixedAssets))));
            }

            if (fusionObject.containsKey(DataAttribute.cashAssets)) {
                wealth.setCashAssets(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.cashAssets))));
            }

            if (fusionObject.containsKey(DataAttribute.finance)) {
                wealth.setFinance(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.finance))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                wealth.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }
            wealths.add(wealth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wealthService.addWealthInfos(wealths);
        wealths.forEach(req -> producer.sendMessage("topic_wealth_message", JSON.toJSONString(req)));
    }

    public void ConveyFusion(Map<String, Object> fusionObject) {
        List<Convey> conveys = new ArrayList<>();

        try {
            Convey convey = new Convey();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                convey.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.conveyCategories)) {
                convey.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.conveyCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyTypes)) {
                convey.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.conveyTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyQuantity)) {
                convey.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyInventory)) {
                convey.setInventory(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyInventory))));
            }

            if (fusionObject.containsKey(DataAttribute.mileage)) {
                convey.setMileage(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.mileage))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyCost)) {
                convey.setCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyCost))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                convey.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            conveys.add(convey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conveyService.addConveyInfos(conveys);
        conveys.forEach(req -> producer.sendMessage("topic_convey_message", JSON.toJSONString(req)));
    }

    public void ProductionFusion(Map<String, Object> fusionObject) {
        List<Production> productions = new ArrayList<>();

        try {
            Production production = new Production();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                production.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCategories)) {
                production.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.productionTypes)) {
                production.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.productionQuantity)) {
                production.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.productionCost)) {
                production.setCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionCost))));
            }

            if (fusionObject.containsKey(DataAttribute.productionProvince)) {
                production.setProvince(String.valueOf(fusionObject.get(DataAttribute.productionProvince)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCountry)) {
                production.setCountry(String.valueOf(fusionObject.get(DataAttribute.productionCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.quality)) {
                production.setQuality(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.quality))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                production.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            productions.add(production);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productionService.addProductionInfos(productions);
        productions.forEach(req -> producer.sendMessage("topic_production_message", JSON.toJSONString(req)));
    }

    public void SaleFusion(Map<String, Object> fusionObject) {
        List<Sale> sales = new ArrayList<>();

        try {
            Sale sale = new Sale();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                sale.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                sale.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                sale.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.saleQuantity)) {
                sale.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.income)) {
                sale.setIncome(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.income))));
            }

            if (fusionObject.containsKey(DataAttribute.saleCost)) {
                sale.setCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleCost))));
            }

            if (fusionObject.containsKey(DataAttribute.saleProvince)) {
                sale.setProvince(String.valueOf(fusionObject.get(DataAttribute.saleProvince)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCountry)) {
                sale.setCountry(String.valueOf(fusionObject.get(DataAttribute.saleCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.saleInventory)) {
                sale.setInventory(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleInventory))));
            }

            if (fusionObject.containsKey(DataAttribute.score)) {
                sale.setScore(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.score))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                sale.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            sales.add(sale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saleService.addSaleInfos(sales);
        sales.forEach(req -> producer.sendMessage("topic_sale_message", JSON.toJSONString(req)));
    }

}
