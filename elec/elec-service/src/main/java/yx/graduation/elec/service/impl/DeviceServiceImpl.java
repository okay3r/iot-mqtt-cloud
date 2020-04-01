package yx.graduation.elec.service.impl;

import io.swagger.annotations.Api;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yx.graduation.elec.mapper.DeviceMapper;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.bo.DeviceBo;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.utils.ApiJsonResult;

import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private Sid sid;


    /**
     * 根据用户id查询拥有的设备
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Device> queryDeviceByUserId(String username) {
        Device record = new Device();
        record.setUsername(username);
        return this.deviceMapper.select(record);
    }

    /**
     * 添加新的设备
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiJsonResult createDevice(String username, DeviceBo deviceBo) {

        Device device = new Device();
        device.setUsername(username);
        device.setDeviceName(deviceBo.getDeviceName());
        if (this.deviceMapper.selectOne(device) != null) {
            return ApiJsonResult.errorMsg("添加失败，该设备已经存在");
        }

        BeanUtils.copyProperties(deviceBo, device);
        String id = sid.nextShort();
        device.setId(id);
        Date now = new Date();
        device.setCreateTime(now);
        device.setUpdateTime(now);

        int res = this.deviceMapper.insert(device);
        if (res != 1) {
            return ApiJsonResult.errorMsg("添加失败");
        }
        return ApiJsonResult.ok(device);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Device> queryAll() {
        return this.deviceMapper.selectAll();
    }
}
