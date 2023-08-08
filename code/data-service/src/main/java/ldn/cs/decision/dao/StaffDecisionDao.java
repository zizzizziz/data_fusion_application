package ldn.cs.decision.dao;

import ldn.cs.decision.pojo.staff.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StaffDecisionDao {
    int addStaffPredictionInfos(@Param("staffs") List<Staff> staffs);

    List<Staff> getStaffPredictionInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalPredictionStaff(@Param("time") long time, @Param("granularity") int granularity);

    List<Staff> getStaffWarningInfos(@Param("time") long time, @Param("granularity") int granularity, @Param("limit") int limit, @Param("offset") int offset);
}
