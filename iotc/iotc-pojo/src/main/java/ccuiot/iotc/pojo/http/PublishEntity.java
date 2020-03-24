package ccuiot.iotc.pojo.http;

import lombok.Data;

@Data
public class PublishEntity {
    private String topic;
    private String payload;
    private Integer qos;
    private Boolean retain;
    private String client_id;
}
