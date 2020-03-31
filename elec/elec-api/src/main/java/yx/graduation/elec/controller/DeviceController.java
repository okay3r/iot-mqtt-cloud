package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.bo.DeviceBo;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.elec.service.UserService;
import yx.graduation.utils.ApiJsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(value = "设备", tags = "设备信息相关接口")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询我的设备
     */
    @ApiOperation(value = "查询我的设备", notes = "查询我的设备", httpMethod = "GET")
    @GetMapping("/queryMyDevice")
    public ApiJsonResult queryMyDevice(HttpServletRequest request) {
        String username = request.getHeader("username");
        List<Device> deviceList = this.deviceService.queryDeviceByUserId(username);
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
        return result;
    }

}
