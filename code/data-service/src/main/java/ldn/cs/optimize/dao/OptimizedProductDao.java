package ldn.cs.optimize.dao;

import ldn.cs.optimize.pojo.OptimizedProduct;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OptimizedProductDao {
    @Insert("INSERT INTO tbl_optimized_product_info (corporation, types, cost, province, quantity, updateTime) VALUES (#{corporation}, #{types}, #{cost}, #{province}, #{quantity}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(OptimizedProduct record);

    @Select("SELECT * FROM tbl_optimized_product_info")
    List<OptimizedProduct> selectAll();

    // 刷新提取表格
    @Update("REPLACE INTO tbl_optimized_product_info (corporation, types, cost, province, quantity, updateTime) SELECT corporation, types, SUM(cost) AS cost, province, SUM(quantity) AS quantity, MAX(updateTime) AS updateTime FROM tbl_production_info GROUP BY corporation, types, province")
    void refreshProductTable();

    //算法更新
    @Update("UPDATE tbl_optimized_product_info SET cost = cost * #{costFactor}, quantity = quantity * #{quantityFactor} WHERE corporation = #{corporationName}")
    void updateProductCostAndQuantity(BigDecimal costFactor, BigDecimal quantityFactor, String corporationName);
}

