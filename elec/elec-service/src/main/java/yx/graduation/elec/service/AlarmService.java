package yx.graduation.elec.service;

import yx.graduation.elec.pojo.Alarm;
import yx.graduation.elec.pojo.bo.DeviceAlarmBo;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

public interface AlarmService {

    ApiJsonResult createOrUpdateAlarm(DeviceAlarmBo deviceAlarmBo);

    List<Alarm> queryAll();

}
