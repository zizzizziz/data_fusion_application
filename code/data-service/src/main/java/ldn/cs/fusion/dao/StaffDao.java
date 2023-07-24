package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.Staff;
import ldn.cs.fusion.pojo.StaffInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.locks.Condition;

@Repository
@Mapper
public interface StaffDao {
    int addStaffInfos(List<Staff> staffs);

    List<Staff> getStaffInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalStaff(@Param("statement") String statement, int types);
}
