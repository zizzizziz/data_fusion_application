package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.convey.Convey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConveyDecisionDao {
    int addConveyPredictionInfos(@Param("conveys") List<Convey> convey);

    List<Convey> getConveyPredictionInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalPredictionConvey(@Param("time") long time, @Param("granularity") int granularity);

    List<Convey> getConveyWarningInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);
}
