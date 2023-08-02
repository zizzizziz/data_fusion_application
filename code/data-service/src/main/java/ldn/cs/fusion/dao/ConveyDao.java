package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.convey.Convey;
import ldn.cs.fusion.pojo.convey.Inventory;
import ldn.cs.fusion.pojo.convey.Traffic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
/**
 * H2传输参数time时可能有长度限制，在MySQL中重新测试是否有限制
 */
public interface ConveyDao {
    int addConveyInfos(@Param("conveys") List<Convey> conveys);

    int addTrafficInfos(@Param("traffics") List<Traffic> traffics);

    int addInventoryInfos(@Param("inventories") List<Inventory> inventories);

    List<Convey> getConveyInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalConvey(@Param("statement") String statement, @Param("types") int types);

    List<Traffic> getTrafficInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Inventory> getInventoryInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月
}
