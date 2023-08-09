package ldn.cs.fusion.service;

import ldn.cs.fusion.common.DataAttribute;
import ldn.cs.fusion.dao.*;
import ldn.cs.fusion.pojo.convey.Inventory;
import ldn.cs.fusion.pojo.convey.Traffic;
import ldn.cs.fusion.pojo.production.Birth;
import ldn.cs.fusion.pojo.production.Trend;
import ldn.cs.fusion.pojo.production.Yield;
import ldn.cs.fusion.pojo.sale.*;
import ldn.cs.fusion.pojo.staff.Person;
import ldn.cs.fusion.pojo.staff.Position;
import ldn.cs.fusion.pojo.staff.Skill;
import ldn.cs.fusion.pojo.wealth.Asset;
import ldn.cs.fusion.pojo.wealth.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据感知提取
 */
@Service
public class DataExtractionService {
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

    // 员工类型分布提取
    public void PersonPerception(Map<String, Object> fusionObject) {
        List<Person> persons = new ArrayList<>();

        try {
            Person person = new Person();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                person.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                person.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            if (fusionObject.containsKey(DataAttribute.amount)) {
                person.setAmount(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.amount))));
            }

            if (fusionObject.containsKey(DataAttribute.staffCategories)) {
                person.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.staffCategories))));
            }

            persons.add(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        staffService.addPersonInfos(persons);
    }

    // 员工职位分布提取
    public void PositionPerception(Map<String, Object> fusionObject) {
        List<Position> positions = new ArrayList<>();

        try {
            Position position = new Position();
            if (fusionObject.containsKey(DataAttribute.positions)) {
                position.setPositions(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.positions))));
            }

            if (fusionObject.containsKey(DataAttribute.amount)) {
                position.setAmount(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.amount))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                position.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            if (fusionObject.containsKey(DataAttribute.corporation)) {
                position.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            positions.add(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        staffService.addPositionInfos(positions);
    }

    // 员工技能分布提取
    public void SkillPerception(Map<String, Object> fusionObject) {
        List<Skill> skills = new ArrayList<>();

        try {
            Skill skill = new Skill();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                skill.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.skill)) {
                skill.setSkill(String.valueOf(fusionObject.get(DataAttribute.skill)));
            }

            if (fusionObject.containsKey(DataAttribute.amount)) {
                skill.setAmount(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.amount))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                skill.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            skills.add(skill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        staffService.addSkillInfos(skills);
    }

    public void AssetPerception(Map<String, Object> fusionObject) {
        List<Asset> assets = new ArrayList<>();

        try {
            Asset asset = new Asset();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                asset.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.cashAssets)) {
                asset.setCashAssets(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.cashAssets))));
            }

            if (fusionObject.containsKey(DataAttribute.profit)) {
                asset.setProfit(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.profit))));
            }

            if (fusionObject.containsKey(DataAttribute.fixedAssets)) {
                asset.setFixedAssets(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.fixedAssets))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                asset.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            if (fusionObject.containsKey(DataAttribute.finance)) {
                asset.setFinance(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.finance))));
            }

            assets.add(asset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wealthService.addAssetInfos(assets);
    }

    public void FinancePerception(Map<String, Object> fusionObject) {
        List<Finance> finances = new ArrayList<>();

        try {
            Finance finance = new Finance();
            if (fusionObject.containsKey(DataAttribute.device)) {
                finance.setDevice(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.device))));
            }

            if (fusionObject.containsKey(DataAttribute.production)) {
                finance.setProduction(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.production))));
            }

            if (fusionObject.containsKey(DataAttribute.corporation)) {
                finance.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.research)) {
                finance.setResearch(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.research))));
            }

            if (fusionObject.containsKey(DataAttribute.transportation)) {
                finance.setTransportation(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.transportation))));
            }

            if (fusionObject.containsKey(DataAttribute.salary)) {
                finance.setSalary(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.salary))));
            }

            if (fusionObject.containsKey(DataAttribute.storage)) {
                finance.setStorage(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.storage))));
            }

            if (fusionObject.containsKey(DataAttribute.materiel)) {
                finance.setMateriel(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.materiel))));
            }

            if (fusionObject.containsKey(DataAttribute.revenue)) {
                finance.setRevenue(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.revenue))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                finance.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            finances.add(finance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wealthService.addFinanceInfos(finances);
    }

    public void InventoryPerception(Map<String, Object> fusionObject) {
        List<Inventory> inventories = new ArrayList<>();

        try {
            Inventory inventory = new Inventory();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                inventory.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.conveyInventory)) {
                inventory.setInventory(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyInventory))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyTypes)) {
                inventory.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.conveyTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyQuantity)) {
                inventory.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyQuantity))));
            }


            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                inventory.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            inventories.add(inventory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conveyService.addInventoryInfos(inventories);
    }

    public void TrafficPerception(Map<String, Object> fusionObject) {
        List<Traffic> traffics = new ArrayList<>();

        try {
            Traffic traffic = new Traffic();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                traffic.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.conveyCategories)) {
                traffic.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.conveyCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                traffic.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            if (fusionObject.containsKey(DataAttribute.mileage)) {
                traffic.setMileage(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.mileage))));
            }

            if (fusionObject.containsKey(DataAttribute.conveyCost)) {
                traffic.setCost(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.conveyCost))));
            }

            traffics.add(traffic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conveyService.addTrafficInfos(traffics);
    }

    public void TrendPerception(Map<String, Object> fusionObject) {
        List<Trend> trends = new ArrayList<>();

        try {
            Trend trend = new Trend();
            if (fusionObject.containsKey(DataAttribute.productionTypes)) {
                trend.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.productionQuantity)) {
                trend.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.corporation)) {
                trend.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCategories)) {
                trend.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                trend.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            trends.add(trend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productionService.addTrendInfos(trends);
    }

    public void BirthPerception(Map<String, Object> fusionObject) {
        List<Birth> births = new ArrayList<>();

        try {
            Birth birth = new Birth();
            if (fusionObject.containsKey(DataAttribute.productionTypes)) {
                birth.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.productionQuantity)) {
                birth.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.corporation)) {
                birth.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCategories)) {
                birth.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.productionProvince)) {
                birth.setProvince(String.valueOf(fusionObject.get(DataAttribute.productionProvince)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCountry)) {
                birth.setCountry(String.valueOf(fusionObject.get(DataAttribute.productionCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                birth.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            births.add(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productionService.addBirthInfos(births);
    }

    public void YieldPerception(Map<String, Object> fusionObject) {
        List<Yield> yields = new ArrayList<>();

        try {
            Yield yield = new Yield();
            if (fusionObject.containsKey(DataAttribute.productionQuantity)) {
                yield.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.productionQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.corporation)) {
                yield.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.productionCategories)) {
                yield.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.productionTypes)) {
                yield.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.productionTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                yield.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            yields.add(yield);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productionService.addYieldInfos(yields);
    }

    public void SaleTrendPerception(Map<String, Object> fusionObject) {
        List<SaleTrend> saleTrends = new ArrayList<>();

        try {
            SaleTrend saleTrend = new SaleTrend();
            if (fusionObject.containsKey(DataAttribute.saleQuantity)) {
                saleTrend.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.income)) {
                saleTrend.setIncome(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.income))));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                saleTrend.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.corporation)) {
                saleTrend.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                saleTrend.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                saleTrend.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            saleTrends.add(saleTrend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saleService.addSaleTrendInfos(saleTrends);
    }

    public void SaleCountPerception(Map<String, Object> fusionObject) {
        List<SaleCount> saleCounts = new ArrayList<>();

        try {
            SaleCount saleCount = new SaleCount();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                saleCount.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                saleCount.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                saleCount.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.saleQuantity)) {
                saleCount.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                saleCount.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            saleCounts.add(saleCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saleService.addSaleCountInfos(saleCounts);
    }

    public void ExportPerception(Map<String, Object> fusionObject) {
        List<Export> exports = new ArrayList<>();

        try {
            Export export = new Export();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                export.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                export.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                export.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.saleQuantity)) {
                export.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.saleCountry)) {
                export.setCountry(String.valueOf(fusionObject.get(DataAttribute.saleCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                export.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            exports.add(export);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saleService.addExportInfos(exports);
    }

    public void SaleBirthPerception(Map<String, Object> fusionObject) {
        List<SaleBirth> saleBirths = new ArrayList<>();

        try {
            SaleBirth saleBirth = new SaleBirth();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                saleBirth.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                saleBirth.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                saleBirth.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.saleQuantity)) {
                saleBirth.setQuantity(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.saleQuantity))));
            }

            if (fusionObject.containsKey(DataAttribute.saleProvince)) {
                saleBirth.setProvince(String.valueOf(fusionObject.get(DataAttribute.saleProvince)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCountry)) {
                saleBirth.setCountry(String.valueOf(fusionObject.get(DataAttribute.saleCountry)));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                saleBirth.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            saleBirths.add(saleBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saleService.addSaleBirthInfos(saleBirths);
    }

    public void ProfitPerception(Map<String, Object> fusionObject) {
        List<Profit> profits = new ArrayList<>();

        try {
            Profit profit = new Profit();
            if (fusionObject.containsKey(DataAttribute.corporation)) {
                profit.setCorporation(String.valueOf(fusionObject.get(DataAttribute.corporation)));
            }

            if (fusionObject.containsKey(DataAttribute.saleCategories)) {
                profit.setCategories(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleCategories))));
            }

            if (fusionObject.containsKey(DataAttribute.saleTypes)) {
                profit.setTypes(Integer.parseInt(String.valueOf(fusionObject.get(DataAttribute.saleTypes))));
            }

            if (fusionObject.containsKey(DataAttribute.income)) {
                profit.setIncome(new BigDecimal(String.valueOf(fusionObject.get(DataAttribute.income))));
            }

            if (fusionObject.containsKey(DataAttribute.eventTime)) {
                profit.setEventTime(Long.parseLong(String.valueOf(fusionObject.get(DataAttribute.eventTime))));
            }

            profits.add(profit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        saleService.addProfitInfos(profits);
    }
}
