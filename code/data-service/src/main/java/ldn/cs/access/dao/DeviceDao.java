package ldn.cs.access.dao;

import ldn.cs.access.pojo.Device;
import ldn.cs.access.pojo.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceDao {
    int addDevices(@Param("devices") List<Device> devices);

    int deleteDevices(@Param("devices") List<Device> devices);

    List<Device> getDevices(@Param("statement") String statement, @Param("types") int types, @Param("limit") int limit, @Param("offset") int offset);

    int getTotalDevices(@Param("statement") String statement, @Param("types") int types);
}
