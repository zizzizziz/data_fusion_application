package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.ProductionDao;
import ldn.cs.fusion.pojo.production.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductionService {
    @Autowired
    private ProductionDao productionDao;

    public int addProductionInfos(List<Production> productions){
        long updateTime = System.currentTimeMillis();
        productions.forEach(production -> production.setUpdateTime(updateTime));
        return productionDao.addProductionInfos(productions);
    }

    public ProductionInfo getProductionInfos(String statement, int types, int limit, int offset) {
        ProductionInfo productionInfo = new ProductionInfo();
        List<Production> productions = productionDao.getProductionInfos(statement, types, limit, offset);
        int total = productionDao.getTotalProduction(statement, types);

        productionInfo.setProductions(productions);
        productionInfo.setTotal(total);
        return productionInfo;
    }

    public int addBirthInfos(List<Birth> births){
        return productionDao.addBirthInfos(births);
    }

    public Map<String, List<Birth>> getBirthInfos(long time, int granularity) {
        return productionDao.getBirthInfos(time, granularity).stream().collect(Collectors.groupingBy(Birth::getCorporation));
    }

    public int addYieldInfos(List<Yield> yields){
        return productionDao.addYieldInfos(yields);
    }

    public Map<String, List<Yield>> getYieldInfos(long time, int granularity) {
        return productionDao.getYieldInfos(time, granularity).stream().collect(Collectors.groupingBy(Yield::getCorporation));
    }

    public int addTrendInfos(List<Trend> trends){
        return productionDao.addTrendInfos(trends);
    }

    public Map<String, List<Trend>> getTrendInfos(int time) {
        return productionDao.getTrendInfos(time).stream().collect(Collectors.groupingBy(Trend::getCorporation));
    }
}
