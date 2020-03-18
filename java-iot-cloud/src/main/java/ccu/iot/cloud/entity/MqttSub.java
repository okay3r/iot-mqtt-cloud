package ccu.iot.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class MqttSub {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String topic;

    private Integer qos;

    private String payload;

    private Date time;
}
