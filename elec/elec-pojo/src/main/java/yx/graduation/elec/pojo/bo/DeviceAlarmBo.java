package yx.graduation.elec.pojo.bo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class DeviceAlarmBo {
    String deviceId;
    String deviceName;
    Long paramId;
    String paramName;
    Integer upValue;
    Integer downValue;
}
