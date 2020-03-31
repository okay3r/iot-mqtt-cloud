package yx.graduation.elec.service;

import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.bo.DeviceBo;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

public interface DeviceService {

    List<Device> queryDeviceByUserId(String username);

    ApiJsonResult createDevice(String username, DeviceBo deviceBo);
}
