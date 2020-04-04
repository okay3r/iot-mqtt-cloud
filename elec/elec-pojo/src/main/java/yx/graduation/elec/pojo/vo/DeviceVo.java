package yx.graduation.elec.pojo.vo;

import yx.graduation.elec.pojo.Alarm;
import yx.graduation.elec.pojo.Device;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class DeviceVo {

    private String id;

    private String deviceName;

    private Long categoryId;

    private String categoryName;

    private List<Alarm> alarmList;

}
