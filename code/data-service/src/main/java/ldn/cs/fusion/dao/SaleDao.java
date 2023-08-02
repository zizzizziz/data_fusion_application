package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.sale.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SaleDao {
    int addExportInfos(@Param("exports") List<Export> exports);

    int addProfitInfos(@Param("profits") List<Profit> profits);

    int addSaleInfos(@Param("sales") List<Sale> sales);

    int addSaleBirthInfos(@Param("saleBirths") List<SaleBirth> saleBirths);

    int addSaleCountInfos(@Param("saleCounts") List<SaleCount> saleCounts);

    int addSaleTrendInfos(@Param("saleTrends") List<SaleTrend> saleTrends);

    List<Sale> getSaleInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalSale(@Param("statement") String statement, @Param("types") int types);

    List<Export> getExportInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Profit> getProfitInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<SaleCount> getSaleCountInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<SaleTrend> getSaleTrendInfos(@Param("time")int time);//granularity: 1、年 2、季度 3、月

    List<SaleBirth> getSaleBirthInfos(@Param("time")long time, @Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月
}
