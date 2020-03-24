package ccuiot.iotc.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mqtt_sub")
@Data
public class MqttSub {
    @Id
    private Long id;

    /**
     * 主题
     */
    private String topic;

    /**
     * qos
     */
    private Integer qos;

    /**
     * 消息
     */
    private String payload;

    /**
     * 时间
     */
    private Date time;

}