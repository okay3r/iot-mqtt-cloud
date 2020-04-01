package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.elec.async.AsynTaskBean;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.Parameter;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.elec.service.ParameterService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import java.util.List;


@Api(value = "基础方法", tags = "基础方法——相关接口")
@RestController
@RequestMapping("/gen")
public class GenController {

    @Autowired
    private AsynTaskBean asynTaskBean;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ParameterService parameterService;

    /**
     * 测试连接
     */
    @ApiOperation(value = "测试连接", notes = "测试连接", httpMethod = "GET")
    @GetMapping("/hello")
    public ApiJsonResult login() throws InterruptedException {
        return ApiJsonResult.ok().ok("Hello ELEC！");
    }


    /**
     * 刷新redis
     */
    @ApiOperation(value = "刷新redis", notes = "刷新redis", httpMethod = "GET")
    @GetMapping("/flush")
    public ApiJsonResult flush() {
        List<Device> deviceList = this.deviceService.queryAll();
        for (Device device : deviceList) {
            this.redisOperator.set(RedisKeyEnum.DEVICE_KV.value + ":" + device.getId(), device.getDeviceName());
        }

        List<Parameter> parameterList = this.parameterService.queryAll();
        for (Parameter parameter : parameterList) {
            this.redisOperator.set(RedisKeyEnum.PARAM_KV.value + ":" + parameter.getParameterName(),
                    parameter.getParameterUnit());
        }
        return ApiJsonResult.ok();
    }





}
