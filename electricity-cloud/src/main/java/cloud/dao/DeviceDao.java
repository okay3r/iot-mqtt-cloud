package cloud.dao;

import cloud.entity.Device;

import java.util.List;

public interface DeviceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    List<Device> selectBySelective(Device device);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}