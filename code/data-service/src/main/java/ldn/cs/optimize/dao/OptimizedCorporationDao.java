package ldn.cs.optimize.dao;

import ldn.cs.optimize.pojo.OptimizedCorporation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OptimizedCorporationDao {
    @Insert("INSERT INTO tbl_optimized_corporation_info(corporation, updateTime) VALUES(#{corporation}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OptimizedCorporation optimizedCorporation);

    // 获取所有公司名称
    @Select("SELECT corporation FROM tbl_optimized_corporation_info")
    List<String> selectAllCorporations();

    @Select("SELECT * FROM tbl_optimized_corporation_info")
    List<OptimizedCorporation> selectAll();

    // 刷新提取表格
    @Update("REPLACE INTO tbl_optimized_corporation_info (corporation) SELECT corporation FROM tbl_staff_skill_info GROUP BY corporation")
    void refreshCorporationTable();
}

