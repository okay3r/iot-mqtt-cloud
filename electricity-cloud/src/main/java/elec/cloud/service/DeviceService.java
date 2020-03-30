package elec.cloud.service;

import elec.cloud.entity.DeviceCategoryVo;

import java.util.List;

public interface DeviceService {
    List<DeviceCategoryVo> queryDevice(Integer userId);
}
