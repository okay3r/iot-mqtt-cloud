package cloud.service;

import cloud.entity.DeviceCategoryVo;

import java.util.List;

public interface DeviceService {
    List<DeviceCategoryVo> queryDevice(Integer userId);
}
