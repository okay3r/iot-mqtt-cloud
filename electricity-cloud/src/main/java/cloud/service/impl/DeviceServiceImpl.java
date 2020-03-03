package cloud.service.impl;

import cloud.dao.CategoryDao;
import cloud.dao.DeviceDao;
import cloud.entity.Category;
import cloud.entity.Device;
import cloud.entity.DeviceCategoryVo;
import cloud.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<DeviceCategoryVo> queryDevice(Integer userId) {
        Device device = new Device();
        device.setUserId(userId);
        List<Device> deviceList = this.deviceDao.selectBySelective(device);
        List<DeviceCategoryVo> resList = new ArrayList<>();
        for (Device d : deviceList) {
            Category c = this.categoryDao.selectByPrimaryKey(d.getCategoryId());
            resList.add(new DeviceCategoryVo(userId, d, c));
        }
        return resList;
    }
}
