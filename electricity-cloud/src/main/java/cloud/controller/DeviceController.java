package cloud.controller;

import cloud.entity.DeviceCategoryVo;
import cloud.result.Result;
import cloud.service.DeviceService;
import cloud.service.impl.DeviceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService = new DeviceServiceImpl();

    /***
     * 根据用户id查找设备
     * @param userId
     * @return
     */
    @GetMapping("/user/device/{userId}")
    public Result<List<DeviceCategoryVo>> queryByUserId(@PathVariable(value = "userId") Integer userId) {
        List<DeviceCategoryVo> voList = this.deviceService.queryDevice(userId);
        return new Result<>(200, "ok", voList);
    }

}
