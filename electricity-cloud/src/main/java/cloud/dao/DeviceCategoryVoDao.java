package cloud.dao;

import cloud.entity.DeviceCategoryVo;

import java.util.List;

public interface DeviceCategoryVoDao {
    List<DeviceCategoryVo> queryDeviceCategory(Integer userId);
}
