package ldn.cs.optimize.dao;

import ldn.cs.optimize.pojo.OptimizedStaff;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OptimizedStaffDao {
    @Insert("INSERT INTO tbl_optimized_staff_info (corporation, skill, amount, updateTime) VALUES (#{corporation}, #{skill}, #{amount}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(OptimizedStaff record);

    @Select("SELECT * FROM tbl_optimized_staff_info")
    List<OptimizedStaff> selectAll();

    // 刷新提取表格
    @Update("REPLACE INTO tbl_optimized_staff_info (corporation, skill, amount) SELECT corporation, skill, SUM(amount) as amount FROM tbl_staff_skill_info GROUP BY corporation, skill")
    void refreshStaffTable();

    //优化type1
    @Update("UPDATE tbl_optimized_staff_info SET amount = amount * #{factor} WHERE corporation = #{corporationName}")
    void updateStaffAmount(double factor, String corporationName);
}
