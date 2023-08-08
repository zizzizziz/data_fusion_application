package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Mapper
public interface DecisionThresholdDao {
    int updateDecisionThreshold(@Param("thresholds") List<DecisionThreshold> thresholds);

    List<DecisionThreshold> getDecisionThreshold(@Param("categories") int categories, @Param("attributes") String attributes);
}
