package ldn.cs.fusion.dao;

import ldn.cs.fusion.pojo.staff.Person;
import ldn.cs.fusion.pojo.staff.Position;
import ldn.cs.fusion.pojo.staff.Skill;
import ldn.cs.fusion.pojo.staff.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StaffDao {
    int addStaffInfos(@Param("staffs")List<Staff> staffs);

    int addPersonInfos(@Param("persons")List<Person> persons);

    int addPositionInfos(@Param("positions")List<Position> positions);

    int addSkillInfos(@Param("skills")List<Skill> skills);

    List<Staff> getStaffInfos(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalStaff(@Param("statement") String statement, @Param("types") int types);

    List<Person> getPersonInfos(@Param("time")int time,@Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Position> getPositionInfos(@Param("time")int time,@Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月

    List<Skill> getSkillInfos(@Param("time")int time,@Param("granularity")int granularity);//granularity: 1、年 2、季度 3、月
}
