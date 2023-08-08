package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.sale.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SaleDecisionDao {
    int addSalePredictionInfos(@Param("sales") List<Sale> sale);

    List<Sale> getSalePredictionInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalPredictionSale(@Param("time") long time, @Param("granularity") int granularity);

    List<Sale> getSaleWarningInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);
}
