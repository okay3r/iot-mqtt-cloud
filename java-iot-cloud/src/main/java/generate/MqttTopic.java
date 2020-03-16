package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * mqtt_topic
 * @author 
 */
@Data
public class MqttTopic implements Serializable {
    private Integer id;

    private String topic;

    private String host;

    private Integer port;

    private Integer qos;

    private static final long serialVersionUID = 1L;
}