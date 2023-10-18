package ldn.cs.access.dao;

import ldn.cs.access.pojo.Original;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OriginalDao {

    int addOriginalInfos(@Param("originals") List<Original> originals);

    List<Original> getOriginal( @Param("limit") int limit, @Param("offset") int offset);

    int getTotalOriginal();
}
