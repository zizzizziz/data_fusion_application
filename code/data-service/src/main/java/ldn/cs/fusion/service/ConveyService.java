package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.ConveyDao;
import ldn.cs.fusion.pojo.convey.Convey;
import ldn.cs.fusion.pojo.convey.ConveyInfo;
import ldn.cs.fusion.pojo.convey.Inventory;
import ldn.cs.fusion.pojo.convey.Traffic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConveyService {
    @Autowired
    private ConveyDao conveyDao;

    public int addConveyInfos(List<Convey> conveys) {
        long updateTime = System.currentTimeMillis();
        conveys.forEach(convey -> convey.setUpdateTime(updateTime));
        return conveyDao.addConveyInfos(conveys);
    }

    public int addTrafficInfos(List<Traffic> traffics) {
        return conveyDao.addTrafficInfos(traffics);
    }

    public int addInventoryInfos(List<Inventory> inventories) {
        return conveyDao.addInventoryInfos(inventories);
    }

    public ConveyInfo getConveyInfos(String statement, int types, int limit, int offset) {
        ConveyInfo conveyInfo = new ConveyInfo();
        List<Convey> conveys = conveyDao.getConveyInfos(statement, types, limit, offset);
        int total = conveyDao.getTotalConvey(statement, types);

        conveyInfo.setConveys(conveys);
        conveyInfo.setTotal(total);
        return conveyInfo;
    }

    public Map<String, List<Traffic>> getTrafficInfos(int time, int granularity) {
        return conveyDao.getTrafficInfos(time, granularity).stream().collect(Collectors.groupingBy(Traffic::getCorporation));
    }

    public Map<String, List<Inventory>> getInventoryInfos(int time, int granularity) {
        return conveyDao.getInventoryInfos(time, granularity).stream().collect(Collectors.groupingBy(Inventory::getCorporation));
    }
}
