package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.WealthDao;
import ldn.cs.fusion.pojo.wealth.Asset;
import ldn.cs.fusion.pojo.wealth.Finance;
import ldn.cs.fusion.pojo.wealth.Wealth;
import ldn.cs.fusion.pojo.wealth.WealthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WealthService {
    @Autowired
    private WealthDao wealth;

    public int addWealthInfos(List<Wealth> wealths) {
        long updateTime = System.currentTimeMillis();
        wealths.forEach(wealth -> wealth.setUpdateTime(updateTime));
        return wealth.addWealthInfos(wealths);
    }

    public int addFinanceInfos(List<Finance> finances) {
        long updateTime = System.currentTimeMillis();
        finances.forEach(finance -> finance.setUpdateTime(updateTime));
        return wealth.addFinanceInfos(finances);
    }

    public int addAssetInfos(List<Asset> assets) {
        long updateTime = System.currentTimeMillis();
        assets.forEach(asset -> asset.setUpdateTime(updateTime));
        return wealth.addAssetInfos(assets);
    }

    public WealthInfo getWealthInfos(String statement, int types, int limit, int offset) {
        WealthInfo wealthInfo = new WealthInfo();
        List<Wealth> wealths = wealth.getWealthInfos(statement, types, limit, offset);
        int total = wealth.getTotalWealth(statement, types);

        wealthInfo.setWealths(wealths);
        wealthInfo.setTotal(total);
        return wealthInfo;
    }

    public Map<String, List<Asset>> getAssetInfos(int time, int granularity) {
        return wealth.getAssetInfos(time, granularity).stream().collect(Collectors.groupingBy(Asset::getCorporation));
    }

    public Map<String, List<Finance>> getFinanceInfos(int time, int granularity) {
        return wealth.getFinanceInfos(time, granularity).stream().collect(Collectors.groupingBy(Finance::getCorporation));
    }

}
