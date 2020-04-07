package yx.graduation.elec.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yx.graduation.elec.pojo.Alarm;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.bo.DeviceAlarmBo;
import yx.graduation.elec.pojo.bo.DeviceBo;
import yx.graduation.elec.service.AlarmService;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(value = "设备", tags = "设备信息相关接口")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private AlarmService alarmService;

    /**
     * 查询我的设备
     */
    @ApiOperation(value = "查询我的设备", notes = "查询我的设备", httpMethod = "GET")
    @GetMapping("/list")
    public ApiJsonResult list() {
        List<Device> deviceList;
        String redisKey = RedisKeyEnum.DEVICE_LIST.value;
        String deviceListJson = (String) this.redisOperator.get(redisKey);
        if (StringUtils.isNotBlank(deviceListJson)) {
            deviceList = JSON.parseArray(deviceListJson, Device.class);
        } else {
            deviceList = this.deviceService.queryAll();
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
            this.redisOperator.del(RedisKeyEnum.DEVICE_LIST.value);
        }
        return result;
    }

    /**
     * 设置报警值
     */
    @ApiOperation(value = "设置报警值", notes = "设置报警值", httpMethod = "POST")
    @PostMapping("/setAlarm")
    public ApiJsonResult setAlarm(@RequestBody DeviceAlarmBo deviceAlarmBo) {

        if (StringUtils.isBlank(deviceAlarmBo.getDeviceId()) ||
                StringUtils.isBlank(deviceAlarmBo.getDeviceName()) ||
                StringUtils.isBlank(deviceAlarmBo.getParamName()) ||
                deviceAlarmBo.getParamId() == null
        ) {
            return ApiJsonResult.errorMsg("参数不正确");
        }

        if (deviceAlarmBo.getUpValue() == null && deviceAlarmBo.getDownValue() == null) {
            return ApiJsonResult.errorMsg("报警值不能为空");
        }
        ApiJsonResult result = this.alarmService.createOrUpdateAlarm(deviceAlarmBo);

        return result;
    }

    /**
     * 查看设备详细信息
     *//*
    @ApiOperation(value = "查看设备详细信息", notes = "查看设备详细信息", httpMethod = "GET")
    @GetMapping("/getDevice")
    public ApiJsonResult getDevice(
            @RequestParam String deviceId,
            @RequestParam Long categoryId
    ) {

        DeviceVo deviceVo = this.deviceService.getDevice(deviceId,categoryId);

    }*/


}
