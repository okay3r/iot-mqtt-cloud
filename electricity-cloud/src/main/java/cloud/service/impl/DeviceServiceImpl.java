package cloud.service.impl;

import cloud.mapper.CategoryMapper;
import cloud.mapper.DeviceMapper;
import cloud.entity.Category;
import cloud.entity.Device;
import cloud.entity.DeviceCategoryVo;
import cloud.service.DeviceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<DeviceCategoryVo> queryDevice(Integer userId) {
        Device device = new Device();
        device.setUserId(userId);
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>(device);
        List<Device> deviceList = this.deviceMapper.selectList(queryWrapper);
        List<DeviceCategoryVo> resList = new ArrayList<>();
        for (Device d : deviceList) {
            Category c = this.categoryMapper.selectById(d.getCategoryId());
            resList.add(new DeviceCategoryVo(userId, d, c));
        }
        return resList;
    }
}
