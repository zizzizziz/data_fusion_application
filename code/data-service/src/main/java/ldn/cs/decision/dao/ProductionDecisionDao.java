package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.production.Production;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductionDecisionDao {
    int addProductionPredictionInfos(@Param("productions") List<Production> production);

    List<Production> getProductionPredictionInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalPredictionProduction(@Param("time") long time, @Param("granularity") int granularity);

    List<Production> getProductionWarningInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);
}
