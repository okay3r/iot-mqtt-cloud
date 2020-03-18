package ccu.iot.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class MqttPub {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String username;

    private String topic;

    private String payload;

    private Integer qos;

    private Boolean success;

    private Date time;
}
