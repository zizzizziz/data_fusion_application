package ldn.cs.optimize.dao;

import ldn.cs.optimize.pojo.OptimizedSalesDetail;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OptimizedSalesDetailDao {
    @Insert("INSERT INTO tbl_optimized_sales_detail_info (corporation, types, quantity, income, province, inventory, updateTime) VALUES (#{corporation}, #{types}, #{quantity}, #{income}, #{province}, #{inventory}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(OptimizedSalesDetail record);

    @Select("SELECT * FROM tbl_optimized_sales_detail_info")
    List<OptimizedSalesDetail> selectAll();

    // 刷新提取表格
    @Update("REPLACE INTO tbl_optimized_sales_detail_info (corporation, types, quantity, income, province, inventory, updateTime) SELECT corporation, types, SUM(quantity) as quantity, SUM(income) as income, province, SUM(inventory) as inventory, MAX(updateTime) as updateTime FROM tbl_sale_info GROUP BY corporation, types, province")
    void refreshSalesDetailTable();

    //算法更新
    @Update("UPDATE tbl_optimized_sales_detail_info SET quantity = tbl_optimized_sales_detail_info.quantity * #{volumeFactor}, income = income * #{revenueFactor} WHERE corporation = #{corporationName}")
    void updateSalesVolumeAndRevenue(BigDecimal volumeFactor, BigDecimal revenueFactor, String corporationName);

    @Update("UPDATE tbl_optimized_sales_detail_info SET inventory = inventory * #{inventoryFactor} WHERE corporation = #{corporationName}")
    void updateSalesInventory(BigDecimal inventoryFactor, String corporationName);
}
