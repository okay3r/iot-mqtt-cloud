package yx.graduation.elec.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CurDataVo {

    private String host;

    private String deviceId;

    private String deviceName;

    //paramName-value
    private Map<String, String> paramValueMap;

}
