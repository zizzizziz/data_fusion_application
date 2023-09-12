package ldn.cs.optimize.dao;

import ldn.cs.optimize.pojo.OptimizedThreshold;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OptimizedThresholdDao {
    @Insert("INSERT INTO tbl_optimized_threshold_info (corporation, attribute, attributeValue, optimizationType, updateTime) VALUES (#{corporation}, #{attribute}, #{attributeValue}, #{optimizationType}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(OptimizedThreshold record);

    // 更新一条记录
    @Update("UPDATE tbl_optimized_threshold_info SET corporation = #{corporation}, attribute =  #{attribute}, attributeValue = #{attributeValue}, optimizationType = #{optimizationType}, updateTime = #{updateTime} WHERE id = #{id}")
    int updateThreshold(OptimizedThreshold record);

    // 根据id查询一条记录
    @Select("SELECT * FROM tbl_optimized_threshold_info WHERE id = #{id}")
    OptimizedThreshold selectById(Integer id);

    // 查询所有记录
    @Select("SELECT * FROM tbl_optimized_threshold_info")
    List<OptimizedThreshold> selectAll();

    // 删除所有记录
    @Delete("DELETE FROM tbl_optimized_threshold_info")
    void deleteAll();

    // 初始化阈值表
    // begin
    @InsertProvider(type = ThresholdInfoSqlProvider.class, method = "replaceIntoThreshold")
    void replaceIntoThreshold(@Param("list") List<OptimizedThreshold> data);

    class ThresholdInfoSqlProvider {
        public String replaceIntoThreshold(final Map<String, Object> param) {
            List<OptimizedThreshold> data = (List<OptimizedThreshold>) param.get("list");
            final StringBuilder sql = new StringBuilder();
            sql.append("REPLACE INTO tbl_optimized_threshold_info (id, corporation, attribute, attributeValue, optimizationType, updateTime) VALUES ");
            for (OptimizedThreshold item : data) {
                sql.append(String.format("(%s, '%s', '%s', '%s', %s, %s), ",
                        item.getId(),
                        item.getCorporation(),
                        item.getAttribute(),
                        item.getAttributeValue(),
                        item.getOptimizationType(),
                        item.getUpdateTime() // 添加updateTime字段
                ));
            }
            sql.deleteCharAt(sql.length() - 2);  // 删除最后一个逗号
            return sql.toString();
        }
    }

    // 更新阈值字段，根据公司名和属性名和类型
    @Update("UPDATE tbl_optimized_threshold_info SET attributeValue = #{attributeValue}, updateTime = #{updateTime} WHERE corporation = #{corporation} AND attribute = #{attribute} AND optimizationType = #{optimizationType}")
    void updateThresholdByCorporationAttributeAndType(OptimizedThreshold record);

    // 提取阈值字段，根据公司名和属性名和类型
    @Select("SELECT * FROM tbl_optimized_threshold_info WHERE corporation = #{corporation} AND attribute = #{attribute} AND optimizationType = #{optimizationType}")
    List<OptimizedThreshold> selectThresholdsByCorporationAttributeAndType(String corporation, String attribute, int optimizationType);
}
