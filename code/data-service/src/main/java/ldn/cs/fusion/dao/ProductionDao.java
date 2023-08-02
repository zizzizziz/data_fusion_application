package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.production.Birth;
import ldn.cs.fusion.pojo.production.Production;
import ldn.cs.fusion.pojo.production.Trend;
import ldn.cs.fusion.pojo.production.Yield;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductionDao {
    int addBirthInfos(@Param("births") List<Birth> births);

    int addYieldInfos(@Param("yields") List<Yield> yields);

    int addTrendInfos(@Param("trends") List<Trend> trends);

    int addProductionInfos(@Param("productions") List<Production> productions);

    List<Production> getProductionInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalProduction(@Param("statement") String statement, @Param("types") int types);

    List<Trend> getTrendInfos(@Param("time")int time);

    List<Birth> getBirthInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Yield> getYieldInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月
}
