package yx.graduation.elec.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.bo.DeviceBo;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.elec.service.UserService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Api(value = "设备", tags = "设备信息相关接口")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 查询我的设备
     */
    @ApiOperation(value = "查询我的设备", notes = "查询我的设备", httpMethod = "GET")
    @GetMapping("/queryMyDevice")
    public ApiJsonResult queryMyDevice(HttpServletRequest request) {
        //TODO 热点数据，存到Redis中
        String username = request.getHeader("username");
        if (StringUtils.isBlank(username)) {
            return ApiJsonResult.errorMsg("请求头中缺少username属性");
        }

        List<Device> deviceList;
        String redisKey = RedisKeyEnum.USER_DEVICE.value + ":" + username;
        String deviceListJson = (String) this.redisOperator.get(redisKey);
        if (StringUtils.isNotBlank(deviceListJson)) {
            deviceList = JSON.parseArray(deviceListJson, Device.class);
        } else {
            deviceList = this.deviceService.queryDeviceByUserId(username);
            this.redisOperator.set(redisKey, JSON.toJSONString(deviceList));
        }

        return ApiJsonResult.ok(deviceList);
    }

    /**
     * 手动添加设备
     */
    @ApiOperation(value = "手动添加设备", notes = "手动添加设备", httpMethod = "POST")
    @PostMapping("/addDevice")
    public ApiJsonResult addDevice(@RequestBody DeviceBo deviceBo, HttpServletRequest request) {
        String username = request.getHeader("username");
        ApiJsonResult result = this.deviceService.createDevice(username, deviceBo);
        if (result.getStatus() == 200) {
            Device device = (Device) result.getData();

            //添加单独映射到redis
            this.redisOperator.set(RedisKeyEnum.DEVICE_KV.value + ":" + device.getId(), device.getDeviceName());

            //设备列表变更，清除对应的redis数据
            this.redisOperator.del(RedisKeyEnum.USER_DEVICE.value + ":" + username);
        }
        return result;
    }

}
