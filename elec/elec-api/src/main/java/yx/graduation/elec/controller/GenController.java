package yx.graduation.elec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yx.graduation.elec.async.AsynTaskBean;
import yx.graduation.elec.pojo.Alarm;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.Parameter;
import yx.graduation.elec.service.AlarmService;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.elec.service.ParameterService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private AlarmService alarmService;

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

        List<Alarm> alarmList = this.alarmService.queryAll();
        for (Alarm alarm : alarmList) {
            String upKey = RedisKeyEnum.ALARM_UP.value + ":" + alarm.getDeviceId() + ":" + alarm.getParamName();
            String downKey = RedisKeyEnum.ALARM_DOWN.value + ":" + alarm.getDeviceId() + ":" + alarm.getParamName();
            if (alarm.getUp() != null) {
                this.redisOperator.set(upKey, alarm.getUp().toString());
            }
            if (alarm.getDown() != null) {
                this.redisOperator.set(downKey, alarm.getDown().toString());
            }
        }
        return ApiJsonResult.ok();
    }

    /**
     * 查看当前连接情况
     */
    @ApiOperation(value = "查看当前连接情况", notes = "查看当前连接情况", httpMethod = "GET")
    @GetMapping("/getMap")
    public ApiJsonResult getHostMap() {
        Map<String, Socket> connMap = AsynTaskBean.getConnMap();
        Map<String, PrintWriter> deviceWriterMap = AsynTaskBean.getDeviceWriterMap();
        List list = new ArrayList();
        list.add(connMap.keySet());
        list.add(deviceWriterMap.keySet());
        return ApiJsonResult.ok(list);
    }

    /**
     * 测试异步任务
     */
    @ApiOperation(value = "测试异步任务", notes = "测试异步任务", httpMethod = "GET")
    @GetMapping("/doTest")
    public ApiJsonResult testAsync() {
        this.asynTaskBean.doTest();
        return ApiJsonResult.ok();
    }

}
