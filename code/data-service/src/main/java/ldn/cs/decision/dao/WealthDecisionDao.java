package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.wealth.Wealth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WealthDecisionDao {
    int addWealthPredictionInfos(@Param("wealths") List<Wealth> wealth);

    List<Wealth> getWealthPredictionInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalPredictionWealth(@Param("time") long time, @Param("granularity") int granularity);

    List<Wealth> getWealthWarningInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);
}
