package yx.graduation.elec.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yx.graduation.elec.mapper.AlarmMapper;
import yx.graduation.elec.pojo.Alarm;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.Parameter;
import yx.graduation.elec.pojo.bo.DeviceAlarmBo;
import yx.graduation.elec.service.AlarmService;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.elec.service.ParameterService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.ApiJsonResult;
import yx.graduation.utils.RedisOperator;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 更新或添加报警值
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiJsonResult createOrUpdateAlarm(DeviceAlarmBo deviceAlarmBo){
        Device device = new Device();
        device.setId(deviceAlarmBo.getDeviceId());
        device.setDeviceName(deviceAlarmBo.getDeviceName());
        List<Device> deviceList = this.deviceService.queryDeviceSelective(device);
        if (deviceList.isEmpty() || deviceList.size() == 0) {
            return ApiJsonResult.errorMsg("设备不存在");
        }

        Parameter parameter = new Parameter();
        parameter.setId(deviceAlarmBo.getParamId());
        parameter.setParameterName(deviceAlarmBo.getParamName());
        List<Parameter> parameterList = this.parameterService.queryParameterSelective(parameter);
        if (parameterList.isEmpty() || parameterList.size() == 0) {
            return ApiJsonResult.errorMsg("参数不存在");
        }

        Integer upValue = deviceAlarmBo.getUpValue();
        Integer downValue = deviceAlarmBo.getDownValue();

        Alarm alarm = new Alarm();
        BeanUtils.copyProperties(deviceAlarmBo, alarm);

        alarm.setUp(null);
        alarm.setDown(null);
        Alarm one = this.alarmMapper.selectOne(alarm);

        //有则更新，无则创建
        if (one == null) {
            alarm.setUp(upValue);
            alarm.setDown(downValue);
            this.alarmMapper.insertSelective(alarm);
        } else {
            one.setUp(upValue);
            one.setDown(downValue);
            this.alarmMapper.updateByPrimaryKeySelective(one);
        }

        //将报警值存储到redis中
        String upKey = RedisKeyEnum.ALARM_UP.value + ":" + deviceAlarmBo.getDeviceId() + ":" + deviceAlarmBo.getParamName();
        String downKey = RedisKeyEnum.ALARM_DOWN.value + ":" + deviceAlarmBo.getDeviceId() + ":" + deviceAlarmBo.getParamName();
        if (upValue != null) {
            this.redisOperator.set(upKey, upValue.toString());
        }
        if (downValue != null) {
            this.redisOperator.set(downKey, downValue.toString());
        }

        return ApiJsonResult.ok();
    }

    /**
     * 查询全部报警值
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Alarm> queryAll() {
        return this.alarmMapper.selectAll();
    }


}
