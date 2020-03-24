package ccuiot.iotc.pojo.bo;

import lombok.Data;

@Data
public class PublishBo {
    private String topic;
    private String payload;
    private Integer qos;
}
