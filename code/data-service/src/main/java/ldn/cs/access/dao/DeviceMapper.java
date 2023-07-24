package ldn.cs.access.dao;

import ldn.cs.access.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DeviceMapper {
//    @Insert("in")
//    int addDevices(List<Device> devices);
    @Select("select * from tbl_device_info")
    Device getDevices();
}
