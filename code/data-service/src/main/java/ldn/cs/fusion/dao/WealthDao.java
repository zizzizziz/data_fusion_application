package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.wealth.Asset;
import ldn.cs.fusion.pojo.wealth.Finance;
import ldn.cs.fusion.pojo.wealth.Wealth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WealthDao {
    int addFinanceInfos(@Param("finances") List<Finance> finances);

    int addAssetInfos(@Param("assets") List<Asset> assets);

    int addWealthInfos(@Param("wealths") List<Wealth> wealths);

    List<Wealth> getWealthInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalWealth(@Param("statement") String statement, @Param("types") int types);

    List<Asset> getAssetInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Finance> getFinanceInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

}
