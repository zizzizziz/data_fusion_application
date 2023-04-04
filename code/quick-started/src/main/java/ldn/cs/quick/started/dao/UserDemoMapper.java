package ldn.cs.quick.started.dao;

import ldn.cs.quick.started.pojo.UserDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDemoMapper {
    @Select("select * from tbl_user_info where userId = #{id}")
    List<UserDemo> findById(int id);

    // @Select("select * from tbl_user_info")
    List<UserDemo> queryAll();
}
