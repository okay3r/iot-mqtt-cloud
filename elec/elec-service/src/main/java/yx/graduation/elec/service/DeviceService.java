package yx.graduation.elec.service;

import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.bo.DeviceBo;
import yx.graduation.elec.pojo.vo.DeviceVo;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

public interface DeviceService {

    List<Device> queryDeviceSelective(Device device);

    ApiJsonResult createDevice(String username, DeviceBo deviceBo);

    List<Device> queryAll();

    User queryUserByDeviceId(String deviceId);

    Device queryDeviceById(String deviceId);

}
